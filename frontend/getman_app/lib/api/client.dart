import 'package:dio/dio.dart';
import 'package:path/path.dart';
import 'package:pretty_dio_logger/pretty_dio_logger.dart';
import 'package:uri/uri.dart';

import '/data.dart' as models;


extension UriBuilderTemplate on UriBuilder {
  Uri expand(Map<String, Object?> variables) =>
    Uri.parse(
      UriTemplate(Uri.decodeFull(build().toString()))
        .expand(variables),
    );
}

class ApiClient {
  const ApiClient({
    required this.dio,
    required this.baseUri,
  });

  final Dio dio;
  final Uri baseUri;

  UriBuilder get _uriBuilder => UriBuilder.fromUri(baseUri);
  Uri _buildUri(String path, Map<String, String> query) =>
    (_uriBuilder
      ..path = join('api/v/1', path)
      ..queryParameters = query).build();
      

  Future<String> _request(
    String path, 
    Map<String, String> query, 
    Map<String, Object?> bodyVariables,
    String method,
  ) async {
    final response = await dio.requestUri<String>(
      _buildUri(path, query),
      options: Options(
        method: method,
        responseType: ResponseType.plain,
      ),
      data: bodyVariables,
    );
    return response.data!;
  }

  Future<T> _getModel<T>(
    String path, {
    Map<String, String> query = const {}, 
    Map<String, Object?> body = const {},
    String method = 'GET',
    }
  ) async =>
    models.parseJson<T>(await _request(path, query, body, method,));

  Future<models.Request> getRequest(int id) async =>
    _getModel('request/$id');

  Future<models.ID> createRequest(int workspaceId, models.Request request) async => 
    _getModel('request', 
      query: {
        'workspace': workspaceId.toString(),
      }, 
      body: {
        ...request.toMap(),
        if (request.payload != null)
          'payload': [
            ['type', request.payload!.type.toString()],
            ['data', request.payload!.data],
          ],
      },
      method: 'POST',
    );

  /// Edits request with [id] with [request].
  Future<models.Response> putRequest(int id, models.Request request) async => 
    _getModel('request/$id',
      body: request.toMap(),
      method: 'PUT',
    );

  /// Partially edits request with given [id].
  Future<models.Request> editRequest(int id, {
    String? httpVersion,
    String? method,
    String? scheme,
    String? host,
    String? port,
    String? path,
    List<List<String>>? headers,
    List<List<String>>? query,
    models.Payload? payload,
  }) => _getModel('request/$id',
      body: {
        if (httpVersion != null)
          'http_version': httpVersion,
        if (method != null)
          'method': method,
        if (scheme != null)
          'scheme': scheme,
        if (host != null)
          'host': host,
        if (port != null)
          'port': port,
        if (path != null)
          'path': path,
        if (headers != null)
          'headers': headers,
        if (query != null)
          'query': query,
        if (payload != null)
          'payload': payload.toMap(),
      },
      method: 'PUT',
    );

  Future<bool> deleteRequest(int id) async =>
    _getModel('request/$id',
      method: 'DELETE',
    );

  Future<bool> deleteWorkspace(int id) async =>
    _getModel('workspace/$id',
      query: {
        'cascade': 'true',
      },
      method: 'DELETE',
    );

  Future<bool> moveRequest(int requestId, int workspaceId) async =>
    _getModel('request/$requestId/move', 
      body: {
        'workspace': workspaceId,
      },
      method: 'POST',
    );

  Future<models.ID> executeRequest(int id) async =>
    _getModel('request/$id/execute',
      method: 'POST',
    );

  Future<models.Workspace> getWorkspace(int id) async =>
    _getModel('workspace/$id');

  
  Future<models.Workspace> editWorkspace(int id, {
    String? name,
    String? description,
  }) => _getModel('workspace/$id',
    body: {
      'name': name,
      'description': description,
    },
    method: 'PUT',
  );

  Future<models.ID> createWorkspace(int rootWorkspaceId, models.Workspace workspace) async => 
    _getModel('workspace',
      query: {
        'workspace': rootWorkspaceId.toString(),
      },
      body: workspace.toMap()
        ..removeWhere((key, value) => const ['id', 'requests', 'workspaces'].contains(key)),
      method: 'POST',
    );

  Future<models.Response> getResponse(int id) =>
    _getModel('response/$id');

  Future<models.RequestSnapshot> getSnapshot(int id) =>
    _getModel('request_snapshot/$id');
}

final client = ApiClient(
  dio: Dio()
    ..interceptors.add(PrettyDioLogger(
      requestHeader: true,
      requestBody: true,
      responseBody: true,
      responseHeader: false,
      error: true,
      compact: true,
      maxWidth: 90,
      logPrint: print,
    ),)
  ,
  baseUri: Uri(),
);
