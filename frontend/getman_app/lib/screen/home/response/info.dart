import 'dart:async';

import 'package:code_text_field/code_text_field.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter_highlight/themes/vs2015.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:highlight/languages/http.dart';
import 'package:reactive_forms/reactive_forms.dart';

import '../../../providers.dart';


class ResponseInfoPage extends ConsumerStatefulWidget {
  const ResponseInfoPage({
    required this.id,
    super.key,
  });

  final int id;

  @override
  ConsumerState<ConsumerStatefulWidget> createState() => _ResponseInfoPageState();
  
  @override
  void debugFillProperties(DiagnosticPropertiesBuilder properties) {
    super.debugFillProperties(properties);
    properties.add(IntProperty('id', id));
  }
}

class _ResponseInfoPageState extends ConsumerState<ResponseInfoPage> {
  final _form = FormGroup({
    'executed_at': FormControl<String>(),
    'closed_at': FormControl<String>(),
    'status': FormGroup({
      'code': FormControl<String>(),
      'text': FormControl<String>(),
    }),
  });

  FormGroup get _status => _form.control('status') as FormGroup;
  late final CodeController _codeController;

  @override
  void initState() {
    _codeController = CodeController(
      language: http,
    );
    unawaited(loadData());
    super.initState();
  }

  Future<void> loadData() async {
    final rq = await ref.read(requestSnapshotProvider(widget.id).future);
    final response = await ref.read(responseProvider(widget.id).future);
    
    _form.reset();
    _form.control('executed_at').value = response.executedAt.toIso8601String();
    _form.control('closed_at').value = response.closedAt?.toIso8601String();

    _status.control('code').value = response.status!.code.toString();
    _status.control('text').value = response.status!.text;

    final startLine = '${rq.method} ${rq.path}${rq.query?.map((e) => e.map(Uri.encodeQueryComponent).join('=')).join ('&') ?? ''} HTTP/${rq.httpVersion}';
    final String headers;
    if (rq.headers == null || rq.headers!.isEmpty)
      headers = '';
    else
      headers = '${rq.headers!.map((e) => e.join(': ')).join('\n')}\n';
    
    final body = rq.payload?.decodedData ?? '';
    _codeController.text = '''$startLine
Host: ${rq.host}:${rq.port}
$headers
$body''';
  }

  @override
  void didUpdateWidget(covariant ResponseInfoPage oldWidget) {
    unawaited(loadData());
    super.didUpdateWidget(oldWidget);
  }

  @override
  Widget build(BuildContext context) => ReactiveForm(
    formGroup: _form, 
    child: Column(
      mainAxisSize: MainAxisSize.min,
      children: [
        Row(
          mainAxisSize: MainAxisSize.min,
          children: [
            Flexible(
              child: ReactiveTextField<String>(
                formControlName: 'status.code',
                readOnly: true,
                decoration: const InputDecoration(
                  labelText: 'Code',
                ),
              ),
            ),
            Flexible(
              child: ReactiveTextField<String>(
                formControlName: 'status.text',
                readOnly: true,
                decoration: const InputDecoration(
                  labelText: 'Text',
                ),
              ),
            ),
            Flexible(
              child: ReactiveTextField<String>(
                formControlName: 'executed_at',
                readOnly: true,
                decoration: const InputDecoration(
                  labelText: 'Executed at',
                ),
              ),
            ),
            Flexible(
              child: ReactiveTextField<String>(
                formControlName: 'closed_at',
                readOnly: true,
                decoration: const InputDecoration(
                  labelText: 'Closed at',
                ),
              ),
            ),
          ],
        ),
        const SizedBox(
          height: 18,
        ),
        Align(
          alignment: Alignment.centerLeft,
          child: Text('Request snapshot',
            style: Theme.of(context).textTheme.headlineSmall,
          ),
        ),
        Expanded(
          child: CodeTheme(
            data: const CodeThemeData(styles: vs2015Theme), 
            child: CodeField(
              controller: _codeController,
              maxLines: null,
              expands: true,
              readOnly: true,
            ),
          ),
        ),
      ],
    ),
  );
}
