// GENERATED CODE - DO NOT MODIFY BY HAND

// ignore_for_file: prefer_expression_function_bodies, use_super_parameters, require_trailing_commas

part of 'request_snapshot.dart';

// **************************************************************************
// RiverpodGenerator
// **************************************************************************

String _$requestSnapshotHash() => r'f5c39f792bff490199b8679b81a3edb7fec0f100';

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

typedef RequestSnapshotRef = AutoDisposeFutureProviderRef<RequestSnapshot>;

/// See also [requestSnapshot].
@ProviderFor(requestSnapshot)
const requestSnapshotProvider = RequestSnapshotFamily();

/// See also [requestSnapshot].
class RequestSnapshotFamily extends Family<AsyncValue<RequestSnapshot>> {
  /// See also [requestSnapshot].
  const RequestSnapshotFamily();

  /// See also [requestSnapshot].
  RequestSnapshotProvider call(
    int id,
  ) {
    return RequestSnapshotProvider(
      id,
    );
  }

  @override
  RequestSnapshotProvider getProviderOverride(
    covariant RequestSnapshotProvider provider,
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
  String? get name => r'requestSnapshotProvider';
}

/// See also [requestSnapshot].
class RequestSnapshotProvider
    extends AutoDisposeFutureProvider<RequestSnapshot> {
  /// See also [requestSnapshot].
  RequestSnapshotProvider(
    this.id,
  ) : super.internal(
          (ref) => requestSnapshot(
            ref,
            id,
          ),
          from: requestSnapshotProvider,
          name: r'requestSnapshotProvider',
          debugGetCreateSourceHash:
              const bool.fromEnvironment('dart.vm.product')
                  ? null
                  : _$requestSnapshotHash,
          dependencies: RequestSnapshotFamily._dependencies,
          allTransitiveDependencies:
              RequestSnapshotFamily._allTransitiveDependencies,
        );

  final int id;

  @override
  bool operator ==(Object other) {
    return other is RequestSnapshotProvider && other.id == id;
  }

  @override
  int get hashCode {
    var hash = _SystemHash.combine(0, runtimeType.hashCode);
    hash = _SystemHash.combine(hash, id.hashCode);

    return _SystemHash.finish(hash);
  }
}
// ignore_for_file: unnecessary_raw_strings, subtype_of_sealed_class, invalid_use_of_internal_member, do_not_use_environment, prefer_const_constructors, public_member_api_docs, avoid_private_typedef_functions
