import 'package:riverpod_annotation/riverpod_annotation.dart';
import '/api.dart';
import '/data.dart';
import 'request.dart';

part 'workspace.g.dart';


@riverpod
Future<Workspace> workspace(WorkspaceRef ref, int id) =>
  client.getWorkspace(id);

@riverpod
Future<String> workspaceName(WorkspaceNameRef ref, int id) =>
  ref.watch(workspaceProvider(id).selectAsync((data) => data.name));

@riverpod
Future<String> workspaceDescription(WorkspaceDescriptionRef ref, int id) =>
  ref.watch(workspaceProvider(id).selectAsync((data) => data.description));

@riverpod
Future<List<int>> workspaceRequestsIds(WorkspaceRequestsIdsRef ref, int id) =>
  ref.watch(workspaceProvider(id).selectAsync((data) => data.requests));

@riverpod
Future<List<int>> workspaceWorkspacesIds(WorkspaceWorkspacesIdsRef ref, int id) =>
  ref.watch(workspaceProvider(id).selectAsync((data) => data.workspaces));

@riverpod
Future<List<Request>> workspaceRequests(WorkspaceRequestsRef ref, int id) async {
  final ids = await ref.watch(workspaceRequestsIdsProvider(id).future);
  return [
    for (final requestId in ids)
      await ref.watch(requestProvider(requestId).future),
  ];
}

@riverpod
Future<List<Workspace>> workspaceWorkspaces(WorkspaceWorkspacesRef ref, int id) async {
  final ids = await ref.watch(workspaceWorkspacesIdsProvider(id).future);
  return [
    for (final workspaceId in ids)
      await ref.watch(workspaceProvider(workspaceId).future),
  ];
}
