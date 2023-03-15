import 'package:auto_route/auto_route.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../api/client.dart';
import '../../data.dart';
import '../../providers.dart';
import '../routes.dart';
import '../widgets/new_workspace.dart';
import '../widgets/sure.dart';


extension A on AsyncValue<String> {
  Widget get textWidget => when(
    data: Text.new,
    error: (error, stackTrace) => Text('Error $error'),
    loading: () => const Text('Loading...'),
  );
}

class SuperiorTreeItem extends ConsumerWidget {
  const SuperiorTreeItem({
    required this.id,
    required this.parent,
    super.key,
  });

  final int id;
  final int parent;

  @override
  Widget build(BuildContext context, WidgetRef ref) =>
    ExpansionTile(
      title: ref.watch(workspaceNameProvider(id)).textWidget,
      initiallyExpanded: id == 0,
      subtitle: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          ref.watch(workspaceDescriptionProvider(id)).textWidget,
          _WorkspaceButtons(id: id),
        ],
      ),
      trailing: id == 0 ? null : IconButton(
        onPressed: () async {
          final answer = await showDialog<bool>(
            context: context, 
            builder: (context) => const SurePage(text: 'Are you sure?'),
          ) ?? false;
          
          if (!answer)
            return;

          await client.deleteWorkspace(id);
          ref.invalidate(workspaceProvider(parent));
          if (context.mounted)
            await (AutoRouter.of(context)..popUntilRoot()).push(const InitialRoute());
        },
        icon: const Icon(Icons.delete_outline,
          color: Colors.red,
        ),
      ),
      controlAffinity: ListTileControlAffinity.leading,
      children: [
        ...ref.watch(workspaceRequestsIdsProvider(id)).when(
          data: (ids) => ids.map((requestId) => 
            Consumer(
              builder: (context, ref, child) =>
                ListTile(
                  onTap: () async => (AutoRouter.of(context)..popUntilRoot()).push(RequestRoute(id: requestId)),
                  title: ref.watch(requestComposedNameProvider(requestId)).textWidget,
                  trailing: IconButton(
                    onPressed: () async {
                      final answer = await showDialog<bool>(
                        context: context, 
                        builder: (context) => const SurePage(text: 'Are you sure?'),
                      ) ?? false;
                      
                      if (!answer)
                        return;
                      await client.deleteRequest(requestId);
                      ref.invalidate(workspaceProvider(id));

                      if (context.mounted) {
                        final requestRoute = AutoRouter.of(context).innerRouterOf(HomeRoute.name)?.innerRouterOf(RequestRoute.name);
                        final args = requestRoute?.routeData.args as RequestRouteArgs?;
                        if (requestRoute == null || !(args != null && args.id != requestId))
                          await (AutoRouter.of(context)..popUntilRoot()).push(const InitialRoute());
                      }
                    },
                    icon: const Icon(Icons.delete_outline,
                      color: Colors.red,
                    ),
                  ),
                ),
            ),
          ),
          error: (error, stackTrace) => const [
            ListTile(
              title: Text('Error!'),
            ),
          ],
          loading: () => const [
            ListTile(
              title: Text('Loading...'),
            ),
          ],
        ),
        ...ref.watch(workspaceWorkspacesIdsProvider(id)).when(
          data: (ids) => ids.map((childId) => SuperiorTreeItem(
            id: childId,
            parent: id,
          ),),
          error: (error, stackTrace) => const [
            ListTile(
              title: Text('Error!'),
            ),
          ],
          loading: () => const [
            ListTile(
              title: Text('Loading...'),
            ),
          ],
        ),
      ],
    );
    
  @override
  void debugFillProperties(DiagnosticPropertiesBuilder properties) {
    super.debugFillProperties(properties);
    properties
      ..add(IntProperty('id', id))
      ..add(IntProperty('parent', parent));
  }
}

class _WorkspaceButtons extends ConsumerWidget {
  const _WorkspaceButtons({
    required this.id,
  });

  final int id;

  @override
  Widget build(BuildContext context, WidgetRef ref) => Wrap(
    alignment: WrapAlignment.end,
    children: [
      if (id != 0)
        TextButton.icon(
          onPressed: () async {
            await client.createRequest(id, const Request.empty());

            ref.invalidate(workspaceProvider(id));
          }, 
          icon: const Icon(Icons.add,
            color: Colors.greenAccent,
          ),
          label: const Text('Request'),
        ),
      TextButton.icon(
        onPressed: () async {
          final workspaceId = await showDialog<ID?>(
            context: context, 
            builder: (context) => NewWorkspaceDialog(parentId: id),
          );
          if (null == workspaceId)
            return;
          ref.invalidate(workspaceProvider(id));
        },
        icon: const Icon(Icons.add,
          color: Colors.greenAccent,
        ),
        label: const Text('Workspace'),
      ),
    ],
  );

  @override
  void debugFillProperties(DiagnosticPropertiesBuilder properties) {
    super.debugFillProperties(properties);
    properties.add(IntProperty('id', id));
  }
}
