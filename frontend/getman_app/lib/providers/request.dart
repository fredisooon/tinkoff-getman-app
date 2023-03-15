import 'dart:convert';

import 'package:riverpod_annotation/riverpod_annotation.dart';
import '/api.dart';
import '/data.dart';

part 'request.g.dart';


@riverpod
Future<Request> request(RequestRef ref, int id) =>
  client.getRequest(id);

@riverpod
Future<String> requestHttpVersion(RequestHttpVersionRef ref, int id) =>
  ref.watch(requestProvider(id).selectAsync((data) => data.httpVersion));

@riverpod
Future<String> requestMethod(RequestMethodRef ref, int id) =>
  ref.watch(requestProvider(id).selectAsync((data) => data.method));

@riverpod
Future<String> requestScheme(RequestSchemeRef ref, int id) =>
  ref.watch(requestProvider(id).selectAsync((data) => data.scheme));

@riverpod
Future<String> requestHost(RequestHostRef ref, int id) =>
  ref.watch(requestProvider(id).selectAsync((data) => data.host));

@riverpod
Future<String> requestPort(RequestPortRef ref, int id) =>
  ref.watch(requestProvider(id).selectAsync((data) => data.port));

@riverpod
Future<String> requestPath(RequestPathRef ref, int id) =>
  ref.watch(requestProvider(id).selectAsync((data) => data.path));

@riverpod
Future<Headers?> requestHeaders(RequestHeadersRef ref, int id) =>
  ref.watch(requestProvider(id).selectAsync((data) => data.headers));

@riverpod
Future<Query?> requestQuery(RequestQueryRef ref, int id) =>
  ref.watch(requestProvider(id).selectAsync((data) => data.query));

@riverpod
Future<Payload?> requestPayload(RequestPayloadRef ref, int id) =>
  ref.watch(requestProvider(id).selectAsync((data) => data.payload));

@riverpod
Future<PayloadType?> requestPayloadType(RequestPayloadTypeRef ref, int id) =>
  ref.watch(requestPayloadProvider(id).selectAsync((data) => data?.type));

@riverpod
Future<String?> requestPayloadData(RequestPayloadDataRef ref, int id) =>
  ref.watch(requestPayloadProvider(id).selectAsync((data) => data?.data));

@riverpod
Future<String?> requestPayloadDecoded(RequestPayloadDecodedRef ref, int id) =>
  ref.watch(
    requestPayloadProvider(id).selectAsync(
      (data) => data == null ? null 
        : utf8.decode(base64.decode(base64.normalize(data.data)),
      ),
    ),
  );

@riverpod
Future<String> requestComposedName(RequestComposedNameRef ref, int id) async {
  final method = await ref.watch(requestMethodProvider(id).future);
  final uri = await ref.watch(requestUriProvider(id).future);

  return '$method $uri';
}

@riverpod
Future<Uri> requestUri(RequestUriRef ref, int id) async {
  final scheme = await ref.watch(requestSchemeProvider(id).future);
  final host = await ref.watch(requestHostProvider(id).future);
  final path = await ref.watch(requestPathProvider(id).future);
  final port = await ref.watch(requestPortProvider(id).future);

  return Uri(
    scheme: scheme,
    host: host,
    path: path,
    port: int.parse(port),
  );
}
