// GENERATED CODE - DO NOT MODIFY BY HAND

// ignore_for_file: prefer_expression_function_bodies, use_super_parameters, require_trailing_commas

part of 'response.dart';

// **************************************************************************
// RiverpodGenerator
// **************************************************************************

String _$responseHash() => r'ccbff580302549459c151655f7160155eb63bbd7';

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

typedef ResponseRef = AutoDisposeFutureProviderRef<Response>;

/// See also [response].
@ProviderFor(response)
const responseProvider = ResponseFamily();

/// See also [response].
class ResponseFamily extends Family<AsyncValue<Response>> {
  /// See also [response].
  const ResponseFamily();

  /// See also [response].
  ResponseProvider call(
    int id,
  ) {
    return ResponseProvider(
      id,
    );
  }

  @override
  ResponseProvider getProviderOverride(
    covariant ResponseProvider provider,
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
  String? get name => r'responseProvider';
}

/// See also [response].
class ResponseProvider extends AutoDisposeFutureProvider<Response> {
  /// See also [response].
  ResponseProvider(
    this.id,
  ) : super.internal(
          (ref) => response(
            ref,
            id,
          ),
          from: responseProvider,
          name: r'responseProvider',
          debugGetCreateSourceHash:
              const bool.fromEnvironment('dart.vm.product')
                  ? null
                  : _$responseHash,
          dependencies: ResponseFamily._dependencies,
          allTransitiveDependencies: ResponseFamily._allTransitiveDependencies,
        );

  final int id;

  @override
  bool operator ==(Object other) {
    return other is ResponseProvider && other.id == id;
  }

  @override
  int get hashCode {
    var hash = _SystemHash.combine(0, runtimeType.hashCode);
    hash = _SystemHash.combine(hash, id.hashCode);

    return _SystemHash.finish(hash);
  }
}

String _$responsePayloadHash() => r'65054a918b442bb518d029d0f68562b96528ef7f';
typedef ResponsePayloadRef = AutoDisposeFutureProviderRef<Payload?>;

/// See also [responsePayload].
@ProviderFor(responsePayload)
const responsePayloadProvider = ResponsePayloadFamily();

/// See also [responsePayload].
class ResponsePayloadFamily extends Family<AsyncValue<Payload?>> {
  /// See also [responsePayload].
  const ResponsePayloadFamily();

  /// See also [responsePayload].
  ResponsePayloadProvider call(
    int id,
  ) {
    return ResponsePayloadProvider(
      id,
    );
  }

  @override
  ResponsePayloadProvider getProviderOverride(
    covariant ResponsePayloadProvider provider,
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
  String? get name => r'responsePayloadProvider';
}

/// See also [responsePayload].
class ResponsePayloadProvider extends AutoDisposeFutureProvider<Payload?> {
  /// See also [responsePayload].
  ResponsePayloadProvider(
    this.id,
  ) : super.internal(
          (ref) => responsePayload(
            ref,
            id,
          ),
          from: responsePayloadProvider,
          name: r'responsePayloadProvider',
          debugGetCreateSourceHash:
              const bool.fromEnvironment('dart.vm.product')
                  ? null
                  : _$responsePayloadHash,
          dependencies: ResponsePayloadFamily._dependencies,
          allTransitiveDependencies:
              ResponsePayloadFamily._allTransitiveDependencies,
        );

  final int id;

  @override
  bool operator ==(Object other) {
    return other is ResponsePayloadProvider && other.id == id;
  }

  @override
  int get hashCode {
    var hash = _SystemHash.combine(0, runtimeType.hashCode);
    hash = _SystemHash.combine(hash, id.hashCode);

    return _SystemHash.finish(hash);
  }
}

String _$responseHeadersHash() => r'd5b07737669f464e93639359bcf026e151d3a149';
typedef ResponseHeadersRef = AutoDisposeFutureProviderRef<List<List<String>>?>;

/// See also [responseHeaders].
@ProviderFor(responseHeaders)
const responseHeadersProvider = ResponseHeadersFamily();

/// See also [responseHeaders].
class ResponseHeadersFamily extends Family<AsyncValue<List<List<String>>?>> {
  /// See also [responseHeaders].
  const ResponseHeadersFamily();

  /// See also [responseHeaders].
  ResponseHeadersProvider call(
    int id,
  ) {
    return ResponseHeadersProvider(
      id,
    );
  }

  @override
  ResponseHeadersProvider getProviderOverride(
    covariant ResponseHeadersProvider provider,
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
  String? get name => r'responseHeadersProvider';
}

/// See also [responseHeaders].
class ResponseHeadersProvider
    extends AutoDisposeFutureProvider<List<List<String>>?> {
  /// See also [responseHeaders].
  ResponseHeadersProvider(
    this.id,
  ) : super.internal(
          (ref) => responseHeaders(
            ref,
            id,
          ),
          from: responseHeadersProvider,
          name: r'responseHeadersProvider',
          debugGetCreateSourceHash:
              const bool.fromEnvironment('dart.vm.product')
                  ? null
                  : _$responseHeadersHash,
          dependencies: ResponseHeadersFamily._dependencies,
          allTransitiveDependencies:
              ResponseHeadersFamily._allTransitiveDependencies,
        );

  final int id;

  @override
  bool operator ==(Object other) {
    return other is ResponseHeadersProvider && other.id == id;
  }

  @override
  int get hashCode {
    var hash = _SystemHash.combine(0, runtimeType.hashCode);
    hash = _SystemHash.combine(hash, id.hashCode);

    return _SystemHash.finish(hash);
  }
}
// ignore_for_file: unnecessary_raw_strings, subtype_of_sealed_class, invalid_use_of_internal_member, do_not_use_environment, prefer_const_constructors, public_member_api_docs, avoid_private_typedef_functions
