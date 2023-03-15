import 'dart:async';

import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:reactive_forms/reactive_forms.dart';

import '../../../providers/request.dart';
import '../../widgets/sure.dart';


class RequestHeadersPage extends ConsumerStatefulWidget {
  const RequestHeadersPage({
    required this.id,
    super.key,
  });

  final int id;

  @override
  ConsumerState<ConsumerStatefulWidget> createState() => _RequestHeadersState();
  
  @override
  void debugFillProperties(DiagnosticPropertiesBuilder properties) {
    super.debugFillProperties(properties);
    properties.add(IntProperty('id', id));
  }
}

class _RequestHeadersState extends ConsumerState<RequestHeadersPage> with AutomaticKeepAliveClientMixin<RequestHeadersPage> {
  FormGroup get _parentFormGroup => ReactiveForm.of(context)! as FormGroup;
  FormArray<Object?> get _formArray =>
    _parentFormGroup.control('headers') as FormArray<Object?>;

  static FormGroup createQueryFormGroup(List<String> pair) =>
    FormGroup({
      'key': FormControl<String>(value: pair[0], validators: [
        Validators.required,
      ],),
      'value': FormControl<String>(value: pair[1]),
    });

  @override
  void initState() {
    unawaited(loadData());
    super.initState();
  }

  Future<void> loadData() async {
    final queryPairs = await ref.read(requestHeadersProvider(widget.id).future) ?? [];

    setState(() {
      _formArray
        ..reset()
        ..clear()
        ..addAll([
          for (final pair in queryPairs)
            createQueryFormGroup(pair)
        ]);
    });
  }

  @override
  void didUpdateWidget(covariant RequestHeadersPage oldWidget) {
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
                                    formControlName: 'key',
                                    decoration: const InputDecoration(
                                      labelText: 'Name',
                                    ),
                                  ),
                                ),
                                Flexible(
                                  child: ReactiveTextField<String>(
                                    key: ValueKey('value: ${widget.id}_${formArray.controls[i].hashCode}'),
                                    formControlName: 'value',
                                    decoration: const InputDecoration(
                                      labelText: 'Value',
                                    ),
                                  ),
                                ),
                                IconButton(
                                  onPressed: () => formArray.removeAt(i),
                                  icon: const Icon(Icons.remove,
                                    color: Colors.red,
                                  ),
                                )
                              ],
                            ),
                          ),
                      ],
                    ),
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: FilledButton.tonalIcon(
                          onPressed: () {
                            _formArray.add(createQueryFormGroup(['', '']));
                          },
                          icon: const Icon(Icons.add,
                            color: Colors.greenAccent,
                          ),
                          label: const Text('Add'),
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: FilledButton.tonalIcon(
                          onPressed: () async {
                            final answer = await showDialog<bool>(
                              context: context, 
                              builder: (context) => const SurePage(text: 'Are you sure?'),
                            ) ?? false;
                            
                            if (!answer)
                              return;
                            _formArray.clear();
                          },
                          icon: const Icon(Icons.delete_forever,
                            color: Colors.red,
                          ),
                          label: const Text('Clear all'),
                        ),
                      ),
                    ],
                  )
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
