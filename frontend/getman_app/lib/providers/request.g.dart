// GENERATED CODE - DO NOT MODIFY BY HAND

// ignore_for_file: prefer_expression_function_bodies, use_super_parameters, require_trailing_commas

part of 'request.dart';

// **************************************************************************
// RiverpodGenerator
// **************************************************************************

String _$requestHash() => r'44d8ffddf59db9615a3214eee01bb68bfaf967bd';

/// Copied from Dart SDK
class _SystemHash {
  _SystemHash._();

  static int combine(int hash, int value) {
    // ignore: parameter_assignments
    hash = 0x1fffffff & (hash + value);
    // ignore: parameter_assignments
    hash = 0x1fffffff & (hash + ((0x0007ffff & hash) << 10));
    return hash ^ (hash >> 6);
  }

  static int finish(int hash) {
    // ignore: parameter_assignments
    hash = 0x1fffffff & (hash + ((0x03ffffff & hash) << 3));
    // ignore: parameter_assignments
    hash = hash ^ (hash >> 11);
    return 0x1fffffff & (hash + ((0x00003fff & hash) << 15));
  }
}

typedef RequestRef = AutoDisposeFutureProviderRef<Request>;

/// See also [request].
@ProviderFor(request)
const requestProvider = RequestFamily();

/// See also [request].
class RequestFamily extends Family<AsyncValue<Request>> {
  /// See also [request].
  const RequestFamily();

  /// See also [request].
  RequestProvider call(
    int id,
  ) {
    return RequestProvider(
      id,
    );
  }

  @override
  RequestProvider getProviderOverride(
    covariant RequestProvider provider,
  ) {
    return call(
      provider.id,
    );
  }

  static const Iterable<ProviderOrFamily>? _dependencies = null;

  @override
  Iterable<ProviderOrFamily>? get dependencies => _dependencies;

  static const Iterable<ProviderOrFamily>? _allTransitiveDependencies = null;

  @override
  Iterable<ProviderOrFamily>? get allTransitiveDependencies =>
      _allTransitiveDependencies;

  @override
  String? get name => r'requestProvider';
}

/// See also [request].
class RequestProvider extends AutoDisposeFutureProvider<Request> {
  /// See also [request].
  RequestProvider(
    this.id,
  ) : super.internal(
          (ref) => request(
            ref,
            id,
          ),
          from: requestProvider,
          name: r'requestProvider',
          debugGetCreateSourceHash:
              const bool.fromEnvironment('dart.vm.product')
                  ? null
                  : _$requestHash,
          dependencies: RequestFamily._dependencies,
          allTransitiveDependencies: RequestFamily._allTransitiveDependencies,
        );

  final int id;

  @override
  bool operator ==(Object other) {
    return other is RequestProvider && other.id == id;
  }

  @override
  int get hashCode {
    var hash = _SystemHash.combine(0, runtimeType.hashCode);
    hash = _SystemHash.combine(hash, id.hashCode);

    return _SystemHash.finish(hash);
  }
}

String _$requestHttpVersionHash() =>
    r'0e4098c4beb5e6411fddac2e2bb62487eeb1d767';
typedef RequestHttpVersionRef = AutoDisposeFutureProviderRef<String>;

/// See also [requestHttpVersion].
@ProviderFor(requestHttpVersion)
const requestHttpVersionProvider = RequestHttpVersionFamily();

/// See also [requestHttpVersion].
class RequestHttpVersionFamily extends Family<AsyncValue<String>> {
  /// See also [requestHttpVersion].
  const RequestHttpVersionFamily();

  /// See also [requestHttpVersion].
  RequestHttpVersionProvider call(
    int id,
  ) {
    return RequestHttpVersionProvider(
      id,
    );
  }

  @override
  RequestHttpVersionProvider getProviderOverride(
    covariant RequestHttpVersionProvider provider,
  ) {
    return call(
      provider.id,
    );
  }

  static const Iterable<ProviderOrFamily>? _dependencies = null;

  @override
  Iterable<ProviderOrFamily>? get dependencies => _dependencies;

  static const Iterable<ProviderOrFamily>? _allTransitiveDependencies = null;

  @override
  Iterable<ProviderOrFamily>? get allTransitiveDependencies =>
      _allTransitiveDependencies;

  @override
  String? get name => r'requestHttpVersionProvider';
}

/// See also [requestHttpVersion].
class RequestHttpVersionProvider extends AutoDisposeFutureProvider<String> {
  /// See also [requestHttpVersion].
  RequestHttpVersionProvider(
    this.id,
  ) : super.internal(
          (ref) => requestHttpVersion(
            ref,
            id,
          ),
          from: requestHttpVersionProvider,
          name: r'requestHttpVersionProvider',
          debugGetCreateSourceHash:
              const bool.fromEnvironment('dart.vm.product')
                  ? null
                  : _$requestHttpVersionHash,
          dependencies: RequestHttpVersionFamily._dependencies,
          allTransitiveDependencies:
              RequestHttpVersionFamily._allTransitiveDependencies,
        );

  final int id;

  @override
  bool operator ==(Object other) {
    return other is RequestHttpVersionProvider && other.id == id;
  }

  @override
  int get hashCode {
    var hash = _SystemHash.combine(0, runtimeType.hashCode);
    hash = _SystemHash.combine(hash, id.hashCode);

    return _SystemHash.finish(hash);
  }
}

String _$requestMethodHash() => r'91186b8ba3feb0b44a30ba5957d3cc30419a53eb';
typedef RequestMethodRef = AutoDisposeFutureProviderRef<String>;

/// See also [requestMethod].
@ProviderFor(requestMethod)
const requestMethodProvider = RequestMethodFamily();

/// See also [requestMethod].
class RequestMethodFamily extends Family<AsyncValue<String>> {
  /// See also [requestMethod].
  const RequestMethodFamily();

  /// See also [requestMethod].
  RequestMethodProvider call(
    int id,
  ) {
    return RequestMethodProvider(
      id,
    );
  }

  @override
  RequestMethodProvider getProviderOverride(
    covariant RequestMethodProvider provider,
  ) {
    return call(
      provider.id,
    );
  }

  static const Iterable<ProviderOrFamily>? _dependencies = null;

  @override
  Iterable<ProviderOrFamily>? get dependencies => _dependencies;

  static const Iterable<ProviderOrFamily>? _allTransitiveDependencies = null;

  @override
  Iterable<ProviderOrFamily>? get allTransitiveDependencies =>
      _allTransitiveDependencies;

  @override
  String? get name => r'requestMethodProvider';
}

/// See also [requestMethod].
class RequestMethodProvider extends AutoDisposeFutureProvider<String> {
  /// See also [requestMethod].
  RequestMethodProvider(
    this.id,
  ) : super.internal(
          (ref) => requestMethod(
            ref,
            id,
          ),
          from: requestMethodProvider,
          name: r'requestMethodProvider',
          debugGetCreateSourceHash:
              const bool.fromEnvironment('dart.vm.product')
                  ? null
                  : _$requestMethodHash,
          dependencies: RequestMethodFamily._dependencies,
          allTransitiveDependencies:
              RequestMethodFamily._allTransitiveDependencies,
        );

  final int id;

  @override
  bool operator ==(Object other) {
    return other is RequestMethodProvider && other.id == id;
  }

  @override
  int get hashCode {
    var hash = _SystemHash.combine(0, runtimeType.hashCode);
    hash = _SystemHash.combine(hash, id.hashCode);

    return _SystemHash.finish(hash);
  }
}

String _$requestSchemeHash() => r'09e27a52e8dad44d7d59369401f20973024bd4e8';
typedef RequestSchemeRef = AutoDisposeFutureProviderRef<String>;

/// See also [requestScheme].
@ProviderFor(requestScheme)
const requestSchemeProvider = RequestSchemeFamily();

/// See also [requestScheme].
class RequestSchemeFamily extends Family<AsyncValue<String>> {
  /// See also [requestScheme].
  const RequestSchemeFamily();

  /// See also [requestScheme].
  RequestSchemeProvider call(
    int id,
  ) {
    return RequestSchemeProvider(
      id,
    );
  }

  @override
  RequestSchemeProvider getProviderOverride(
    covariant RequestSchemeProvider provider,
  ) {
    return call(
      provider.id,
    );
  }

  static const Iterable<ProviderOrFamily>? _dependencies = null;

  @override
  Iterable<ProviderOrFamily>? get dependencies => _dependencies;

  static const Iterable<ProviderOrFamily>? _allTransitiveDependencies = null;

  @override
  Iterable<ProviderOrFamily>? get allTransitiveDependencies =>
      _allTransitiveDependencies;

  @override
  String? get name => r'requestSchemeProvider';
}

/// See also [requestScheme].
class RequestSchemeProvider extends AutoDisposeFutureProvider<String> {
  /// See also [requestScheme].
  RequestSchemeProvider(
    this.id,
  ) : super.internal(
          (ref) => requestScheme(
            ref,
            id,
          ),
          from: requestSchemeProvider,
          name: r'requestSchemeProvider',
          debugGetCreateSourceHash:
              const bool.fromEnvironment('dart.vm.product')
                  ? null
                  : _$requestSchemeHash,
          dependencies: RequestSchemeFamily._dependencies,
          allTransitiveDependencies:
              RequestSchemeFamily._allTransitiveDependencies,
        );

  final int id;

  @override
  bool operator ==(Object other) {
    return other is RequestSchemeProvider && other.id == id;
  }

  @override
  int get hashCode {
    var hash = _SystemHash.combine(0, runtimeType.hashCode);
    hash = _SystemHash.combine(hash, id.hashCode);

    return _SystemHash.finish(hash);
  }
}

String _$requestHostHash() => r'b5858611ef23b4c27b8e4273365f1a3d8e772741';
typedef RequestHostRef = AutoDisposeFutureProviderRef<String>;

/// See also [requestHost].
@ProviderFor(requestHost)
const requestHostProvider = RequestHostFamily();

/// See also [requestHost].
class RequestHostFamily extends Family<AsyncValue<String>> {
  /// See also [requestHost].
  const RequestHostFamily();

  /// See also [requestHost].
  RequestHostProvider call(
    int id,
  ) {
    return RequestHostProvider(
      id,
    );
  }

  @override
  RequestHostProvider getProviderOverride(
    covariant RequestHostProvider provider,
  ) {
    return call(
      provider.id,
    );
  }

  static const Iterable<ProviderOrFamily>? _dependencies = null;

  @override
  Iterable<ProviderOrFamily>? get dependencies => _dependencies;

  static const Iterable<ProviderOrFamily>? _allTransitiveDependencies = null;

  @override
  Iterable<ProviderOrFamily>? get allTransitiveDependencies =>
      _allTransitiveDependencies;

  @override
  String? get name => r'requestHostProvider';
}

/// See also [requestHost].
class RequestHostProvider extends AutoDisposeFutureProvider<String> {
  /// See also [requestHost].
  RequestHostProvider(
    this.id,
  ) : super.internal(
          (ref) => requestHost(
            ref,
            id,
          ),
          from: requestHostProvider,
          name: r'requestHostProvider',
          debugGetCreateSourceHash:
              const bool.fromEnvironment('dart.vm.product')
                  ? null
                  : _$requestHostHash,
          dependencies: RequestHostFamily._dependencies,
          allTransitiveDependencies:
              RequestHostFamily._allTransitiveDependencies,
        );

  final int id;

  @override
  bool operator ==(Object other) {
    return other is RequestHostProvider && other.id == id;
  }

  @override
  int get hashCode {
    var hash = _SystemHash.combine(0, runtimeType.hashCode);
    hash = _SystemHash.combine(hash, id.hashCode);

    return _SystemHash.finish(hash);
  }
}

String _$requestPortHash() => r'2f5e4cd11d94b6949ef00acf1bc14acb39c3c95e';
typedef RequestPortRef = AutoDisposeFutureProviderRef<String>;

/// See also [requestPort].
@ProviderFor(requestPort)
const requestPortProvider = RequestPortFamily();

/// See also [requestPort].
class RequestPortFamily extends Family<AsyncValue<String>> {
  /// See also [requestPort].
  const RequestPortFamily();

  /// See also [requestPort].
  RequestPortProvider call(
    int id,
  ) {
    return RequestPortProvider(
      id,
    );
  }

  @override
  RequestPortProvider getProviderOverride(
    covariant RequestPortProvider provider,
  ) {
    return call(
      provider.id,
    );
  }

  static const Iterable<ProviderOrFamily>? _dependencies = null;

  @override
  Iterable<ProviderOrFamily>? get dependencies => _dependencies;

  static const Iterable<ProviderOrFamily>? _allTransitiveDependencies = null;

  @override
  Iterable<ProviderOrFamily>? get allTransitiveDependencies =>
      _allTransitiveDependencies;

  @override
  String? get name => r'requestPortProvider';
}

/// See also [requestPort].
class RequestPortProvider extends AutoDisposeFutureProvider<String> {
  /// See also [requestPort].
  RequestPortProvider(
    this.id,
  ) : super.internal(
          (ref) => requestPort(
            ref,
            id,
          ),
          from: requestPortProvider,
          name: r'requestPortProvider',
          debugGetCreateSourceHash:
              const bool.fromEnvironment('dart.vm.product')
                  ? null
                  : _$requestPortHash,
          dependencies: RequestPortFamily._dependencies,
          allTransitiveDependencies:
              RequestPortFamily._allTransitiveDependencies,
        );

  final int id;

  @override
  bool operator ==(Object other) {
    return other is RequestPortProvider && other.id == id;
  }

  @override
  int get hashCode {
    var hash = _SystemHash.combine(0, runtimeType.hashCode);
    hash = _SystemHash.combine(hash, id.hashCode);

    return _SystemHash.finish(hash);
  }
}

String _$requestPathHash() => r'09a3586987c805631edf38c7492b37f4e4dad857';
typedef RequestPathRef = AutoDisposeFutureProviderRef<String>;

/// See also [requestPath].
@ProviderFor(requestPath)
const requestPathProvider = RequestPathFamily();

/// See also [requestPath].
class RequestPathFamily extends Family<AsyncValue<String>> {
  /// See also [requestPath].
  const RequestPathFamily();

  /// See also [requestPath].
  RequestPathProvider call(
    int id,
  ) {
    return RequestPathProvider(
      id,
    );
  }

  @override
  RequestPathProvider getProviderOverride(
    covariant RequestPathProvider provider,
  ) {
    return call(
      provider.id,
    );
  }

  static const Iterable<ProviderOrFamily>? _dependencies = null;

  @override
  Iterable<ProviderOrFamily>? get dependencies => _dependencies;

  static const Iterable<ProviderOrFamily>? _allTransitiveDependencies = null;

  @override
  Iterable<ProviderOrFamily>? get allTransitiveDependencies =>
      _allTransitiveDependencies;

  @override
  String? get name => r'requestPathProvider';
}

/// See also [requestPath].
class RequestPathProvider extends AutoDisposeFutureProvider<String> {
  /// See also [requestPath].
  RequestPathProvider(
    this.id,
  ) : super.internal(
          (ref) => requestPath(
            ref,
            id,
          ),
          from: requestPathProvider,
          name: r'requestPathProvider',
          debugGetCreateSourceHash:
              const bool.fromEnvironment('dart.vm.product')
                  ? null
                  : _$requestPathHash,
          dependencies: RequestPathFamily._dependencies,
          allTransitiveDependencies:
              RequestPathFamily._allTransitiveDependencies,
        );

  final int id;

  @override
  bool operator ==(Object other) {
    return other is RequestPathProvider && other.id == id;
  }

  @override
  int get hashCode {
    var hash = _SystemHash.combine(0, runtimeType.hashCode);
    hash = _SystemHash.combine(hash, id.hashCode);

    return _SystemHash.finish(hash);
  }
}

String _$requestHeadersHash() => r'e25c721b80182c73b4ad7ca094d7a901936f6c47';
typedef RequestHeadersRef = AutoDisposeFutureProviderRef<List<List<String>>?>;

/// See also [requestHeaders].
@ProviderFor(requestHeaders)
const requestHeadersProvider = RequestHeadersFamily();

/// See also [requestHeaders].
class RequestHeadersFamily extends Family<AsyncValue<List<List<String>>?>> {
  /// See also [requestHeaders].
  const RequestHeadersFamily();

  /// See also [requestHeaders].
  RequestHeadersProvider call(
    int id,
  ) {
    return RequestHeadersProvider(
      id,
    );
  }

  @override
  RequestHeadersProvider getProviderOverride(
    covariant RequestHeadersProvider provider,
  ) {
    return call(
      provider.id,
    );
  }

  static const Iterable<ProviderOrFamily>? _dependencies = null;

  @override
  Iterable<ProviderOrFamily>? get dependencies => _dependencies;

  static const Iterable<ProviderOrFamily>? _allTransitiveDependencies = null;

  @override
  Iterable<ProviderOrFamily>? get allTransitiveDependencies =>
      _allTransitiveDependencies;

  @override
  String? get name => r'requestHeadersProvider';
}

/// See also [requestHeaders].
class RequestHeadersProvider
    extends AutoDisposeFutureProvider<List<List<String>>?> {
  /// See also [requestHeaders].
  RequestHeadersProvider(
    this.id,
  ) : super.internal(
          (ref) => requestHeaders(
            ref,
            id,
          ),
          from: requestHeadersProvider,
          name: r'requestHeadersProvider',
          debugGetCreateSourceHash:
              const bool.fromEnvironment('dart.vm.product')
                  ? null
                  : _$requestHeadersHash,
          dependencies: RequestHeadersFamily._dependencies,
          allTransitiveDependencies:
              RequestHeadersFamily._allTransitiveDependencies,
        );

  final int id;

  @override
  bool operator ==(Object other) {
    return other is RequestHeadersProvider && other.id == id;
  }

  @override
  int get hashCode {
    var hash = _SystemHash.combine(0, runtimeType.hashCode);
    hash = _SystemHash.combine(hash, id.hashCode);

    return _SystemHash.finish(hash);
  }
}

String _$requestQueryHash() => r'dc9e05d31e586c0f65f51168832479cc13c11b2d';
typedef RequestQueryRef = AutoDisposeFutureProviderRef<List<List<String>>?>;

/// See also [requestQuery].
@ProviderFor(requestQuery)
const requestQueryProvider = RequestQueryFamily();

/// See also [requestQuery].
class RequestQueryFamily extends Family<AsyncValue<List<List<String>>?>> {
  /// See also [requestQuery].
  const RequestQueryFamily();

  /// See also [requestQuery].
  RequestQueryProvider call(
    int id,
  ) {
    return RequestQueryProvider(
      id,
    );
  }

  @override
  RequestQueryProvider getProviderOverride(
    covariant RequestQueryProvider provider,
  ) {
    return call(
      provider.id,
    );
  }

  static const Iterable<ProviderOrFamily>? _dependencies = null;

  @override
  Iterable<ProviderOrFamily>? get dependencies => _dependencies;

  static const Iterable<ProviderOrFamily>? _allTransitiveDependencies = null;

  @override
  Iterable<ProviderOrFamily>? get allTransitiveDependencies =>
      _allTransitiveDependencies;

  @override
  String? get name => r'requestQueryProvider';
}

/// See also [requestQuery].
class RequestQueryProvider
    extends AutoDisposeFutureProvider<List<List<String>>?> {
  /// See also [requestQuery].
  RequestQueryProvider(
    this.id,
  ) : super.internal(
          (ref) => requestQuery(
            ref,
            id,
          ),
          from: requestQueryProvider,
          name: r'requestQueryProvider',
          debugGetCreateSourceHash:
              const bool.fromEnvironment('dart.vm.product')
                  ? null
                  : _$requestQueryHash,
          dependencies: RequestQueryFamily._dependencies,
          allTransitiveDependencies:
              RequestQueryFamily._allTransitiveDependencies,
        );

  final int id;

  @override
  bool operator ==(Object other) {
    return other is RequestQueryProvider && other.id == id;
  }

  @override
  int get hashCode {
    var hash = _SystemHash.combine(0, runtimeType.hashCode);
    hash = _SystemHash.combine(hash, id.hashCode);

    return _SystemHash.finish(hash);
  }
}

String _$requestPayloadHash() => r'828424e277202ebbe5701618c2ac9e7019b25f55';
typedef RequestPayloadRef = AutoDisposeFutureProviderRef<Payload?>;

/// See also [requestPayload].
@ProviderFor(requestPayload)
const requestPayloadProvider = RequestPayloadFamily();

/// See also [requestPayload].
class RequestPayloadFamily extends Family<AsyncValue<Payload?>> {
  /// See also [requestPayload].
  const RequestPayloadFamily();

  /// See also [requestPayload].
  RequestPayloadProvider call(
    int id,
  ) {
    return RequestPayloadProvider(
      id,
    );
  }

  @override
  RequestPayloadProvider getProviderOverride(
    covariant RequestPayloadProvider provider,
  ) {
    return call(
      provider.id,
    );
  }

  static const Iterable<ProviderOrFamily>? _dependencies = null;

  @override
  Iterable<ProviderOrFamily>? get dependencies => _dependencies;

  static const Iterable<ProviderOrFamily>? _allTransitiveDependencies = null;

  @override
  Iterable<ProviderOrFamily>? get allTransitiveDependencies =>
      _allTransitiveDependencies;

  @override
  String? get name => r'requestPayloadProvider';
}

/// See also [requestPayload].
class RequestPayloadProvider extends AutoDisposeFutureProvider<Payload?> {
  /// See also [requestPayload].
  RequestPayloadProvider(
    this.id,
  ) : super.internal(
          (ref) => requestPayload(
            ref,
            id,
          ),
          from: requestPayloadProvider,
          name: r'requestPayloadProvider',
          debugGetCreateSourceHash:
              const bool.fromEnvironment('dart.vm.product')
                  ? null
                  : _$requestPayloadHash,
          dependencies: RequestPayloadFamily._dependencies,
          allTransitiveDependencies:
              RequestPayloadFamily._allTransitiveDependencies,
        );

  final int id;

  @override
  bool operator ==(Object other) {
    return other is RequestPayloadProvider && other.id == id;
  }

  @override
  int get hashCode {
    var hash = _SystemHash.combine(0, runtimeType.hashCode);
    hash = _SystemHash.combine(hash, id.hashCode);

    return _SystemHash.finish(hash);
  }
}

String _$requestPayloadTypeHash() =>
    r'f057fff856b129f0c75958efc5bed3225f5cc2e7';
typedef RequestPayloadTypeRef = AutoDisposeFutureProviderRef<PayloadType?>;

/// See also [requestPayloadType].
@ProviderFor(requestPayloadType)
const requestPayloadTypeProvider = RequestPayloadTypeFamily();

/// See also [requestPayloadType].
class RequestPayloadTypeFamily extends Family<AsyncValue<PayloadType?>> {
  /// See also [requestPayloadType].
  const RequestPayloadTypeFamily();

  /// See also [requestPayloadType].
  RequestPayloadTypeProvider call(
    int id,
  ) {
    return RequestPayloadTypeProvider(
      id,
    );
  }

  @override
  RequestPayloadTypeProvider getProviderOverride(
    covariant RequestPayloadTypeProvider provider,
  ) {
    return call(
      provider.id,
    );
  }

  static const Iterable<ProviderOrFamily>? _dependencies = null;

  @override
  Iterable<ProviderOrFamily>? get dependencies => _dependencies;

  static const Iterable<ProviderOrFamily>? _allTransitiveDependencies = null;

  @override
  Iterable<ProviderOrFamily>? get allTransitiveDependencies =>
      _allTransitiveDependencies;

  @override
  String? get name => r'requestPayloadTypeProvider';
}

/// See also [requestPayloadType].
class RequestPayloadTypeProvider
    extends AutoDisposeFutureProvider<PayloadType?> {
  /// See also [requestPayloadType].
  RequestPayloadTypeProvider(
    this.id,
  ) : super.internal(
          (ref) => requestPayloadType(
            ref,
            id,
          ),
          from: requestPayloadTypeProvider,
          name: r'requestPayloadTypeProvider',
          debugGetCreateSourceHash:
              const bool.fromEnvironment('dart.vm.product')
                  ? null
                  : _$requestPayloadTypeHash,
          dependencies: RequestPayloadTypeFamily._dependencies,
          allTransitiveDependencies:
              RequestPayloadTypeFamily._allTransitiveDependencies,
        );

  final int id;

  @override
  bool operator ==(Object other) {
    return other is RequestPayloadTypeProvider && other.id == id;
  }

  @override
  int get hashCode {
    var hash = _SystemHash.combine(0, runtimeType.hashCode);
    hash = _SystemHash.combine(hash, id.hashCode);

    return _SystemHash.finish(hash);
  }
}

String _$requestPayloadDataHash() =>
    r'31e7b2574531199adbf1aec9f0fde59360424bad';
typedef RequestPayloadDataRef = AutoDisposeFutureProviderRef<String?>;

/// See also [requestPayloadData].
@ProviderFor(requestPayloadData)
const requestPayloadDataProvider = RequestPayloadDataFamily();

/// See also [requestPayloadData].
class RequestPayloadDataFamily extends Family<AsyncValue<String?>> {
  /// See also [requestPayloadData].
  const RequestPayloadDataFamily();

  /// See also [requestPayloadData].
  RequestPayloadDataProvider call(
    int id,
  ) {
    return RequestPayloadDataProvider(
      id,
    );
  }

  @override
  RequestPayloadDataProvider getProviderOverride(
    covariant RequestPayloadDataProvider provider,
  ) {
    return call(
      provider.id,
    );
  }

  static const Iterable<ProviderOrFamily>? _dependencies = null;

  @override
  Iterable<ProviderOrFamily>? get dependencies => _dependencies;

  static const Iterable<ProviderOrFamily>? _allTransitiveDependencies = null;

  @override
  Iterable<ProviderOrFamily>? get allTransitiveDependencies =>
      _allTransitiveDependencies;

  @override
  String? get name => r'requestPayloadDataProvider';
}

/// See also [requestPayloadData].
class RequestPayloadDataProvider extends AutoDisposeFutureProvider<String?> {
  /// See also [requestPayloadData].
  RequestPayloadDataProvider(
    this.id,
  ) : super.internal(
          (ref) => requestPayloadData(
            ref,
            id,
          ),
          from: requestPayloadDataProvider,
          name: r'requestPayloadDataProvider',
          debugGetCreateSourceHash:
              const bool.fromEnvironment('dart.vm.product')
                  ? null
                  : _$requestPayloadDataHash,
          dependencies: RequestPayloadDataFamily._dependencies,
          allTransitiveDependencies:
              RequestPayloadDataFamily._allTransitiveDependencies,
        );

  final int id;

  @override
  bool operator ==(Object other) {
    return other is RequestPayloadDataProvider && other.id == id;
  }

  @override
  int get hashCode {
    var hash = _SystemHash.combine(0, runtimeType.hashCode);
    hash = _SystemHash.combine(hash, id.hashCode);

    return _SystemHash.finish(hash);
  }
}

String _$requestPayloadDecodedHash() =>
    r'e65d819cfdce28cca641c8bfc62fd63fcda4f763';
typedef RequestPayloadDecodedRef = AutoDisposeFutureProviderRef<String?>;

/// See also [requestPayloadDecoded].
@ProviderFor(requestPayloadDecoded)
const requestPayloadDecodedProvider = RequestPayloadDecodedFamily();

/// See also [requestPayloadDecoded].
class RequestPayloadDecodedFamily extends Family<AsyncValue<String?>> {
  /// See also [requestPayloadDecoded].
  const RequestPayloadDecodedFamily();

  /// See also [requestPayloadDecoded].
  RequestPayloadDecodedProvider call(
    int id,
  ) {
    return RequestPayloadDecodedProvider(
      id,
    );
  }

  @override
  RequestPayloadDecodedProvider getProviderOverride(
    covariant RequestPayloadDecodedProvider provider,
  ) {
    return call(
      provider.id,
    );
  }

  static const Iterable<ProviderOrFamily>? _dependencies = null;

  @override
  Iterable<ProviderOrFamily>? get dependencies => _dependencies;

  static const Iterable<ProviderOrFamily>? _allTransitiveDependencies = null;

  @override
  Iterable<ProviderOrFamily>? get allTransitiveDependencies =>
      _allTransitiveDependencies;

  @override
  String? get name => r'requestPayloadDecodedProvider';
}

/// See also [requestPayloadDecoded].
class RequestPayloadDecodedProvider extends AutoDisposeFutureProvider<String?> {
  /// See also [requestPayloadDecoded].
  RequestPayloadDecodedProvider(
    this.id,
  ) : super.internal(
          (ref) => requestPayloadDecoded(
            ref,
            id,
          ),
          from: requestPayloadDecodedProvider,
          name: r'requestPayloadDecodedProvider',
          debugGetCreateSourceHash:
              const bool.fromEnvironment('dart.vm.product')
                  ? null
                  : _$requestPayloadDecodedHash,
          dependencies: RequestPayloadDecodedFamily._dependencies,
          allTransitiveDependencies:
              RequestPayloadDecodedFamily._allTransitiveDependencies,
        );

  final int id;

  @override
  bool operator ==(Object other) {
    return other is RequestPayloadDecodedProvider && other.id == id;
  }

  @override
  int get hashCode {
    var hash = _SystemHash.combine(0, runtimeType.hashCode);
    hash = _SystemHash.combine(hash, id.hashCode);

    return _SystemHash.finish(hash);
  }
}

String _$requestComposedNameHash() =>
    r'c201207ca2a94017820a24d6719c0cffa52450ea';
typedef RequestComposedNameRef = AutoDisposeFutureProviderRef<String>;

/// See also [requestComposedName].
@ProviderFor(requestComposedName)
const requestComposedNameProvider = RequestComposedNameFamily();

/// See also [requestComposedName].
class RequestComposedNameFamily extends Family<AsyncValue<String>> {
  /// See also [requestComposedName].
  const RequestComposedNameFamily();

  /// See also [requestComposedName].
  RequestComposedNameProvider call(
    int id,
  ) {
    return RequestComposedNameProvider(
      id,
    );
  }

  @override
  RequestComposedNameProvider getProviderOverride(
    covariant RequestComposedNameProvider provider,
  ) {
    return call(
      provider.id,
    );
  }

  static const Iterable<ProviderOrFamily>? _dependencies = null;

  @override
  Iterable<ProviderOrFamily>? get dependencies => _dependencies;

  static const Iterable<ProviderOrFamily>? _allTransitiveDependencies = null;

  @override
  Iterable<ProviderOrFamily>? get allTransitiveDependencies =>
      _allTransitiveDependencies;

  @override
  String? get name => r'requestComposedNameProvider';
}

/// See also [requestComposedName].
class RequestComposedNameProvider extends AutoDisposeFutureProvider<String> {
  /// See also [requestComposedName].
  RequestComposedNameProvider(
    this.id,
  ) : super.internal(
          (ref) => requestComposedName(
            ref,
            id,
          ),
          from: requestComposedNameProvider,
          name: r'requestComposedNameProvider',
          debugGetCreateSourceHash:
              const bool.fromEnvironment('dart.vm.product')
                  ? null
                  : _$requestComposedNameHash,
          dependencies: RequestComposedNameFamily._dependencies,
          allTransitiveDependencies:
              RequestComposedNameFamily._allTransitiveDependencies,
        );

  final int id;

  @override
  bool operator ==(Object other) {
    return other is RequestComposedNameProvider && other.id == id;
  }

  @override
  int get hashCode {
    var hash = _SystemHash.combine(0, runtimeType.hashCode);
    hash = _SystemHash.combine(hash, id.hashCode);

    return _SystemHash.finish(hash);
  }
}

String _$requestUriHash() => r'5e150588cf2cf918a6966650bef86945732643e3';
typedef RequestUriRef = AutoDisposeFutureProviderRef<Uri>;

/// See also [requestUri].
@ProviderFor(requestUri)
const requestUriProvider = RequestUriFamily();

/// See also [requestUri].
class RequestUriFamily extends Family<AsyncValue<Uri>> {
  /// See also [requestUri].
  const RequestUriFamily();

  /// See also [requestUri].
  RequestUriProvider call(
    int id,
  ) {
    return RequestUriProvider(
      id,
    );
  }

  @override
  RequestUriProvider getProviderOverride(
    covariant RequestUriProvider provider,
  ) {
    return call(
      provider.id,
    );
  }

  static const Iterable<ProviderOrFamily>? _dependencies = null;

  @override
  Iterable<ProviderOrFamily>? get dependencies => _dependencies;

  static const Iterable<ProviderOrFamily>? _allTransitiveDependencies = null;

  @override
  Iterable<ProviderOrFamily>? get allTransitiveDependencies =>
      _allTransitiveDependencies;

  @override
  String? get name => r'requestUriProvider';
}

/// See also [requestUri].
class RequestUriProvider extends AutoDisposeFutureProvider<Uri> {
  /// See also [requestUri].
  RequestUriProvider(
    this.id,
  ) : super.internal(
          (ref) => requestUri(
            ref,
            id,
          ),
          from: requestUriProvider,
          name: r'requestUriProvider',
          debugGetCreateSourceHash:
              const bool.fromEnvironment('dart.vm.product')
                  ? null
                  : _$requestUriHash,
          dependencies: RequestUriFamily._dependencies,
          allTransitiveDependencies:
              RequestUriFamily._allTransitiveDependencies,
        );

  final int id;

  @override
  bool operator ==(Object other) {
    return other is RequestUriProvider && other.id == id;
  }

  @override
  int get hashCode {
    var hash = _SystemHash.combine(0, runtimeType.hashCode);
    hash = _SystemHash.combine(hash, id.hashCode);

    return _SystemHash.finish(hash);
  }
}
// ignore_for_file: unnecessary_raw_strings, subtype_of_sealed_class, invalid_use_of_internal_member, do_not_use_environment, prefer_const_constructors, public_member_api_docs, avoid_private_typedef_functions
