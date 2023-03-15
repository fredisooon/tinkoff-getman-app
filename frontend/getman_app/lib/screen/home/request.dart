import 'dart:async';
import 'dart:convert';

import 'package:auto_route/auto_route.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:multi_split_view/multi_split_view.dart';
import 'package:reactive_forms/reactive_forms.dart';

import '../../../providers/request.dart';
import '../../api/client.dart';
import '../../data.dart';
import '../routes.dart';
import 'request_inner/body.dart';
import 'request_inner/headers.dart';
import 'request_inner/query.dart';


final methods = [
  'GET',
  'HEAD',
  'POST',
  'PUT',
  'DELETE',
  'OPTIONS',
  'TRACE',
  'PATCH',
];

@RoutePage()
class RequestPage extends ConsumerStatefulWidget {
  const RequestPage({
    @PathParam() required this.id,
    super.key, 
  });

  final int id;
  
  @override
  ConsumerState<ConsumerStatefulWidget> createState() => _RequestPageState();

  @override
  void debugFillProperties(DiagnosticPropertiesBuilder properties) {
    super.debugFillProperties(properties);
    properties.add(IntProperty('id', id));
  }
}

const validationErrorKey = 'invalid_method';
const validationErrorObject = { validationErrorKey: true, };
final validationErrorMessages = { validationErrorKey: (_) => 'Remove body', };

ValidatorFunction _validatePayloadMethod() => (control) {
    final form = control as FormGroup;

    final payloadControl = form.control('payload');
    final methodControl = form.control('method');

    payloadControl.removeError(validationErrorKey);
    methodControl.removeError(validationErrorKey);

    final payloadValue = payloadControl.value as String?;
    if ((payloadValue?.isNotEmpty ?? false) && ['GET', 'HEAD', 'DELETE'].contains(methodControl.value)) {
      methodControl
        ..setErrors(validationErrorObject)
        ..markAllAsTouched();
      payloadControl
        ..setErrors(validationErrorObject)
        ..markAllAsTouched();
    }

    return null;
  };

class _RequestPageState extends ConsumerState<RequestPage> with TickerProviderStateMixin {
  late final TabController _requestInnerTabController;
  
  final _form = FormGroup({
    'payload': FormControl<String>(),
    'method': FormControl<String>(),
    'url': FormControl<String>(),
    'query': FormArray([]),
    'headers': FormArray([]),
  }, validators: [
    _validatePayloadMethod(),
  ],);

  FormArray<Object?> get _query =>
    _form.control('query') as FormArray<Object?>;

  FormArray<Object?> get _headers =>
    _form.control('headers') as FormArray<Object?>;

  FormGroup createFormGroup(List<String> pair) => 
    FormGroup({
      'key': FormControl<String>(value: pair[0]),
      'value': FormControl<String>(value: pair[1]),
    });

  @override
  void initState() {
    _requestInnerTabController = TabController(length: 3, vsync: this);
    unawaited(loadData());
    super.initState();
  }

  Future<void> loadData() async {
    final query = await ref.read(requestQueryProvider(widget.id).future) ?? [];
    final headers = await ref.read(requestHeadersProvider(widget.id).future) ?? [];
    _form.reset();
    _form.control('method').value = await ref.read(requestMethodProvider(widget.id).future);
    _form.control('url').value = (await ref.read(requestUriProvider(widget.id).future)).toString();

    _query
      ..clear()
      ..addAll([
        for (final pair in query)
          createFormGroup(pair)
      ]);
    _headers
      ..clear()
      ..addAll([
      for (final pair in headers)
        createFormGroup(pair)
    ]);
    _form.control('payload').value = await ref.read(requestPayloadDecodedProvider(widget.id).future);
    _form.markAllAsTouched();
    setState(() {
      
    });
  }

  @override
  void didUpdateWidget(covariant RequestPage oldWidget) {
    unawaited(loadData());
    super.didUpdateWidget(oldWidget);
  }

  @override
  void dispose() {
    _form.dispose();
    _requestInnerTabController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) =>
    ref
      .watch(requestProvider(widget.id))
      .when(
        data: (request) => ReactiveForm(
          formGroup: _form,
          child: Scaffold(
              backgroundColor: Colors.transparent,
              appBar: AppBar(
                toolbarHeight: 84,
                automaticallyImplyLeading: false,
                backgroundColor: Colors.transparent,
                flexibleSpace: Row(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    SizedBox(
                      width: 120,
                      child: Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: ReactiveDropdownField<String>(
                          validationMessages: validationErrorMessages,
                          formControlName: 'method',
                          focusColor: Theme.of(context).scaffoldBackgroundColor,
                          items: [
                            for (final method in methods)
                              DropdownMenuItem(
                                value: method,
                                child: Text(method),
                              ),
                          ],
                        ),
                      ),
                    ),
                    Expanded(
                      flex: 9,
                      child: Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: ReactiveTextField<String>(
                          formControlName: 'url',
                          decoration: const InputDecoration(
                            hintText: 'Type url here',
                          ),
                        ),
                      ),
                    ),
                    ReactiveFormConsumer(
                      builder: (context, formGroup, child) => Row(
                        mainAxisSize: MainAxisSize.min,
                        children: [
                          Padding(
                            padding: const EdgeInsets.all(8.0),
                            child: ElevatedButton(
                              onPressed: !formGroup.valid ? null : () async {
                                await saveRequest(request);
                                try {
                                  final responseId = await client.executeRequest(widget.id);
                                  if (context.mounted) 
                                    await AutoRouter.of(context).push(ResponseRoute(id: responseId.id));
                                } catch (e) {
                                  if (context.mounted)
                                    ScaffoldMessenger.of(context).showSnackBar(
                                      SnackBar(
                                        content: Column(
                                          crossAxisAlignment: CrossAxisAlignment.start,
                                          mainAxisSize: MainAxisSize.min,
                                          children: [
                                            const Text('Failed to execute request'),
                                            Text(e.toString()),
                                          ],
                                        ),
                                      ),
                                    );
                                }
                              },
                              child: const Text('Send'),
                            ),
                          ),
                          Padding(
                            padding: const EdgeInsets.all(8.0),
                            child: ElevatedButton(
                              onPressed: !formGroup.valid ? null : () async => saveRequest(request),
                              child: const Text('Save'),
                            ),
                          ),
                        ],
                      ),
                    ),
                  ],
                ),
              ),
              body: MultiSplitViewTheme(
                data: MultiSplitViewThemeData(
                  dividerPainter: DividerPainters.grooved1(
                    color: Colors.indigo[100]!,
                    highlightedColor: Colors.indigo[900]!,
                  ),
                ),
                child: MultiSplitView(
                  axis: Axis.vertical,
                  controller: MultiSplitViewController(
                    areas: [
                      Area(
                        weight: 0.5,
                      ),
                      Area(
                        weight: 0.5,
                      )
                    ],
                  ),
                  children: [
                    Scaffold(
                      backgroundColor: Colors.transparent,
                      appBar: TabBar(
                        physics: const NeverScrollableScrollPhysics(),
                        controller: _requestInnerTabController,
                        tabs: const [
                          Tab(
                            text: 'Query parameters',
                          ),
                          Tab(
                            text: 'Headers',
                          ),
                          Tab(
                            text: 'Body',
                          ),
                        ],
                      ),
                      body: TabBarView(
                        physics: const NeverScrollableScrollPhysics(),
                        controller: _requestInnerTabController,
                        children: [
                          RequestQueryPage(id: widget.id),
                          RequestHeadersPage(id: widget.id),
                          RequestBodyPage(id: widget.id),
                        ],
                      ),
                    ),
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.stretch,
                      children: [
                        DecoratedBox(
                          decoration: BoxDecoration(
                            border: Border(
                              bottom: BorderSide(
                                color: Theme.of(context).dividerColor,
                                width: 1,
                              ),
                            ),
                          ),
                          child: const Padding(
                            padding: EdgeInsets.only(left: 8.0, bottom: 2.0),
                            child: Text('Response'),
                          ),
                        ),
                        const Expanded(
                          child: AutoRouter(
                            // navigatorObservers: AutoRouterDelegate(),
                            inheritNavigatorObservers: false,
                          ),
                        ),
                      ],
                    ),
                  ],
                ),
              ),
            ),
        ),
        error: (error, stackTrace) => const Center(
          child: Text('Error.'),
        ), 
        loading: () => const CircularProgressIndicator(),
      );

  Future<void> saveRequest(Request request) async {
    final value = _form.value;
    final headers = value['headers']! as List<dynamic>;
    final query = value['query']! as List<dynamic>;
    final payload = value['payload'] as String?;
    final Uri url;
    if (value['url'] is String)
      url = Uri.parse(value['url']! as String);
    else
      url = request.uri;
    
    await client.editRequest(widget.id,
      method: value['method'] as String? ?? request.method,
      scheme: url.scheme,
      host: url.host,
      path: url.path,
      port: url.port.toString(),
      headers: [
        for (final header in headers)
          [header['key'] as String, header['value'] as String],
      ],
      query: [
        for (final parameter in query)
          [parameter['key'] as String, parameter['value'] as String],
      ],
      payload: (payload == null || payload == '') ? null : Payload(
        type: PayloadType.plain,
        data: base64Encode(utf8.encode(payload)),
      ),
    );
    
    ref.invalidate(requestProvider(widget.id));
  }
}
