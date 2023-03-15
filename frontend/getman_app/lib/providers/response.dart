import 'package:riverpod_annotation/riverpod_annotation.dart';

import '../api/client.dart';
import '../data.dart';

part 'response.g.dart';


@riverpod
Future<Response> response(ResponseRef ref, int id) async =>
  client.getResponse(id);

@riverpod
Future<Payload?> responsePayload(ResponsePayloadRef ref, int id) async => 
  ref.watch(responseProvider(id).selectAsync((data) => data.payload));

@riverpod
Future<List<List<String>>?> responseHeaders(ResponseHeadersRef ref, int id) async => 
  ref.watch(responseProvider(id).selectAsync((data) => data.headers));
