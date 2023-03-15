import 'dart:async';

import 'package:code_text_field/code_text_field.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter_highlight/themes/vs2015.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:highlight/languages/xml.dart';

import '../../../providers/response.dart';


class ResponseBodyPage extends ConsumerStatefulWidget {
  const ResponseBodyPage({
    required this.id, 
    super.key,
  });

  final int id;

  @override
  ConsumerState<ConsumerStatefulWidget> createState() => _ResponseBodyPageState();
  
  @override
  void debugFillProperties(DiagnosticPropertiesBuilder properties) {
    super.debugFillProperties(properties);
    properties.add(IntProperty('id', id));
  }
}

class _ResponseBodyPageState extends ConsumerState<ResponseBodyPage> with AutomaticKeepAliveClientMixin<ResponseBodyPage> {
  final _codeController = CodeController(
    language: xml,
  );

  @override
  void initState() {
    unawaited(loadData());
    super.initState();
  }

  Future<void> loadData() async {
    final response = await ref.read(responseProvider(widget.id).future);

    _codeController.text = response.payload?.decodedData ?? '';
  }

  @override
  void didUpdateWidget(covariant ResponseBodyPage oldWidget) {
    unawaited(loadData());
    super.didUpdateWidget(oldWidget);
  }

  @override
  Widget build(BuildContext context) {
    super.build(context);

    return CodeTheme(
      data: const CodeThemeData(styles: vs2015Theme),
      child: CodeField(
        controller: _codeController,
        maxLines: null,
        expands: true,
      ),
    );
  }
  
  @override
  bool get wantKeepAlive => true;
}
