import 'dart:async';

import 'package:code_text_field/code_text_field.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter_highlight/themes/vs2015.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:highlight/languages/json.dart';
import 'package:reactive_forms/reactive_forms.dart';

import '../../../providers/request.dart';


class RequestBodyPage extends ConsumerStatefulWidget {
  const RequestBodyPage({
    required this.id,
    super.key,
  });

  final int id;

  @override
  ConsumerState<ConsumerStatefulWidget> createState() => _RequestParamsState();
  @override
  void debugFillProperties(DiagnosticPropertiesBuilder properties) {
    super.debugFillProperties(properties);
    properties.add(IntProperty('id', id));
  }
}

class _RequestParamsState extends ConsumerState<RequestBodyPage> with AutomaticKeepAliveClientMixin<RequestBodyPage> {
  FormGroup get _parentFormGroup => ReactiveForm.of(context)! as FormGroup;
  FormControl<String> get _payloadFormControl =>
    _parentFormGroup.control('payload') as FormControl<String>;

  final CodeController _codeController = CodeController(
    language: json,
  );

  @override
  void initState() {
    unawaited(loadData());
    super.initState();
  }

  Future<void> loadData() async {
    final payload = await ref.read(requestPayloadDecodedProvider(widget.id).future);
    
    _payloadFormControl.value = payload ?? '';
    _codeController.text = _payloadFormControl.value!;
  }

  @override
  void didUpdateWidget(covariant RequestBodyPage oldWidget) {
    unawaited(loadData());
    super.didUpdateWidget(oldWidget);
  }

  @override
  void dispose() {
    _codeController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    super.build(context);

    return ReactiveCodeField(
      formControl: _payloadFormControl,
      codeController: _codeController,
    );
  }
  
  @override
  bool get wantKeepAlive => true;
}

class ReactiveCodeField extends ReactiveFormField<String, String> {
  ReactiveCodeField({
    // required super.formControlName,
    required super.formControl,
    required CodeController codeController,
    super.key, 
  }) : super(
    builder: (form) => CodeTheme(
      data: const CodeThemeData(styles: vs2015Theme),
      child: CodeField(
        controller: codeController,
        onChanged: form.didChange,
      ),
    ),
  );
}
