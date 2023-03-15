import 'package:riverpod_annotation/riverpod_annotation.dart';

import '../api/client.dart';
import '../data.dart';

part 'request_snapshot.g.dart';


@riverpod
Future<RequestSnapshot> requestSnapshot(RequestSnapshotRef ref, int id) async => 
  client.getSnapshot(id);
