import 'dart:async';

import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:reactive_forms/reactive_forms.dart';

import '../../../providers/response.dart';


class ResponseHeadersPage extends ConsumerStatefulWidget {
  const ResponseHeadersPage({
    required this.id,
    super.key,
  });

  final int id;

  @override
  ConsumerState<ConsumerStatefulWidget> createState() => _ResponseHeadersPageState();

  @override
  void debugFillProperties(DiagnosticPropertiesBuilder properties) {
    super.debugFillProperties(properties);
    properties.add(IntProperty('id', id));
  }
}

class _ResponseHeadersPageState extends ConsumerState<ResponseHeadersPage> with AutomaticKeepAliveClientMixin<ResponseHeadersPage> {
  final FormGroup _parentFormGroup = FormGroup({
    'headers': FormArray([]),
  });

  FormArray<Object?> get _formArray =>
    _parentFormGroup.control('headers') as FormArray<Object?>;

  static FormGroup createFormGroup(List<String> pair) =>
    FormGroup({
      'key': FormControl<String>(value: pair[0]),
      'value': FormControl<String>(value: pair[1]),
    });

  @override
  void initState() {
    unawaited(loadData());
    super.initState();
  }

  Future<void> loadData() async {
    final response = await ref.read(responseProvider(widget.id).future);
    final headers = response.headers ?? [];

    setState(() {
      _formArray
        ..reset()
        ..clear()
        ..addAll([
          for (final pair in headers)
            createFormGroup(pair)
        ]);
    });
  }

  @override
  void didUpdateWidget(covariant ResponseHeadersPage oldWidget) {
    unawaited(loadData());
    super.didUpdateWidget(oldWidget);
  }

  @override
  Widget build(BuildContext context) {
    super.build(context);

    return Column(
      crossAxisAlignment: CrossAxisAlignment.stretch,
      children: [
        Flexible(
          child: Padding(
            padding: const EdgeInsets.all(8.0),
            child: ReactiveForm(
              formGroup: _parentFormGroup,
              child: ListView(
                children: [
                  ReactiveFormArray(
                    formArrayName: 'headers',
                    builder: (context, formArray, child) => Wrap(
                      children: [
                        for (var i = 0; i < formArray.controls.length; i++)
                          ReactiveForm(
                            formGroup: formArray.controls[i] as FormGroup, 
                            child: Row(
                              children: [
                                Flexible(
                                  child: ReactiveTextField<String>(
                                    key: ValueKey('key: ${widget.id}_${formArray.controls[i].hashCode}'),
                                    readOnly: true,
                                    formControlName: 'key',
                                    decoration: const InputDecoration(
                                      labelText: 'Name',
                                    ),
                                  ),
                                ),
                                Flexible(
                                  child: ReactiveTextField<String>(
                                    key: ValueKey('value: ${widget.id}_${formArray.controls[i].hashCode}'),
                                    readOnly: true,
                                    formControlName: 'value',
                                    decoration: const InputDecoration(
                                      labelText: 'Value',
                                    ),
                                  ),
                                ),
                              ],
                            ),
                          ),
                      ],
                    ),
                  ),
                ],
              ),
            ),
          ),
        ),
      ],
    );
  }
  
  @override
  bool get wantKeepAlive => true;
}
