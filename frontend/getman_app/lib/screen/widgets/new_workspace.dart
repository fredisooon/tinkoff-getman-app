import 'package:auto_route/auto_route.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:reactive_forms/reactive_forms.dart';

import '../../api/client.dart';
import '../../data/models/id.dart';
import '../../data/models/workspace.dart';


class NewWorkspaceDialog extends StatefulWidget {
  const NewWorkspaceDialog({
    required this.parentId,
    super.key,
  });

  final int parentId;

  @override
  State<NewWorkspaceDialog> createState() => _NewWorkspaceDialogState();
  
  @override
  void debugFillProperties(DiagnosticPropertiesBuilder properties) {
    super.debugFillProperties(properties);
    properties.add(IntProperty('parentId', parentId));
  }
}

class _NewWorkspaceDialogState extends State<NewWorkspaceDialog> {
  final _form = FormGroup({
    'name': FormControl<String>(validators: [Validators.required]),
    'description': FormControl<String>(),
  });
  
  @override
  Widget build(BuildContext context) => AlertDialog(
    content: Column(
      mainAxisSize: MainAxisSize.min,
      children: [
        Row(
          children: [
            Text('Create new workspace',
              style: Theme.of(context).textTheme.headlineMedium,
            ),
            const Spacer(),
            IconButton.filledTonal(
              onPressed: () async => AutoRouter.of(context).pop(null),
              icon: const Icon(Icons.close),
            ),
          ],
        ),
        const Divider(),
        ReactiveForm(
          formGroup: _form, 
          child: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              ReactiveTextField<String>(
                formControlName: 'name',
                decoration: const InputDecoration(
                  labelText: 'Name',
                ),
              ),
              ReactiveTextField<String>(
                formControlName: 'description',
                decoration: const InputDecoration(
                  labelText: 'Description',
                ),
              ),
              Align(
                alignment: Alignment.centerRight,
                child: Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: ReactiveFormConsumer(
                    builder: (context, formGroup, child) => FilledButton.tonal(
                      onPressed: !formGroup.valid ? null : () async {                   
                        final value = formGroup.value;
                        
                        await client.createWorkspace(
                          widget.parentId, 
                          Workspace(
                            id: const ID.dummy(), 
                            name: value['name'] as String? ?? '',
                            description: value['description'] as String? ?? '',
                            requests: const [], 
                            workspaces: const [],
                          ),
                        ).then(AutoRouter.of(context).pop);
                      },  
                      child: const Text('Save'),
                    ),
                  ),
                ),
              )
            ],
          ),
        )
      ],
    ),
  );
}
