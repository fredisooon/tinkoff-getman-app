// GENERATED CODE - DO NOT MODIFY BY HAND

// ignore_for_file: prefer_expression_function_bodies, use_super_parameters, require_trailing_commas

part of 'workspace.dart';

// **************************************************************************
// RiverpodGenerator
// **************************************************************************

String _$workspaceHash() => r'a3ba93d3cc0c69e2834850a733a4628c580a807c';

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

typedef WorkspaceRef = AutoDisposeFutureProviderRef<Workspace>;

/// See also [workspace].
@ProviderFor(workspace)
const workspaceProvider = WorkspaceFamily();

/// See also [workspace].
class WorkspaceFamily extends Family<AsyncValue<Workspace>> {
  /// See also [workspace].
  const WorkspaceFamily();

  /// See also [workspace].
  WorkspaceProvider call(
    int id,
  ) {
    return WorkspaceProvider(
      id,
    );
  }

  @override
  WorkspaceProvider getProviderOverride(
    covariant WorkspaceProvider provider,
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
  String? get name => r'workspaceProvider';
}

/// See also [workspace].
class WorkspaceProvider extends AutoDisposeFutureProvider<Workspace> {
  /// See also [workspace].
  WorkspaceProvider(
    this.id,
  ) : super.internal(
          (ref) => workspace(
            ref,
            id,
          ),
          from: workspaceProvider,
          name: r'workspaceProvider',
          debugGetCreateSourceHash:
              const bool.fromEnvironment('dart.vm.product')
                  ? null
                  : _$workspaceHash,
          dependencies: WorkspaceFamily._dependencies,
          allTransitiveDependencies: WorkspaceFamily._allTransitiveDependencies,
        );

  final int id;

  @override
  bool operator ==(Object other) {
    return other is WorkspaceProvider && other.id == id;
  }

  @override
  int get hashCode {
    var hash = _SystemHash.combine(0, runtimeType.hashCode);
    hash = _SystemHash.combine(hash, id.hashCode);

    return _SystemHash.finish(hash);
  }
}

String _$workspaceNameHash() => r'5f8af0bdbff7379dc8a0483f258597a549be3675';
typedef WorkspaceNameRef = AutoDisposeFutureProviderRef<String>;

/// See also [workspaceName].
@ProviderFor(workspaceName)
const workspaceNameProvider = WorkspaceNameFamily();

/// See also [workspaceName].
class WorkspaceNameFamily extends Family<AsyncValue<String>> {
  /// See also [workspaceName].
  const WorkspaceNameFamily();

  /// See also [workspaceName].
  WorkspaceNameProvider call(
    int id,
  ) {
    return WorkspaceNameProvider(
      id,
    );
  }

  @override
  WorkspaceNameProvider getProviderOverride(
    covariant WorkspaceNameProvider provider,
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
  String? get name => r'workspaceNameProvider';
}

/// See also [workspaceName].
class WorkspaceNameProvider extends AutoDisposeFutureProvider<String> {
  /// See also [workspaceName].
  WorkspaceNameProvider(
    this.id,
  ) : super.internal(
          (ref) => workspaceName(
            ref,
            id,
          ),
          from: workspaceNameProvider,
          name: r'workspaceNameProvider',
          debugGetCreateSourceHash:
              const bool.fromEnvironment('dart.vm.product')
                  ? null
                  : _$workspaceNameHash,
          dependencies: WorkspaceNameFamily._dependencies,
          allTransitiveDependencies:
              WorkspaceNameFamily._allTransitiveDependencies,
        );

  final int id;

  @override
  bool operator ==(Object other) {
    return other is WorkspaceNameProvider && other.id == id;
  }

  @override
  int get hashCode {
    var hash = _SystemHash.combine(0, runtimeType.hashCode);
    hash = _SystemHash.combine(hash, id.hashCode);

    return _SystemHash.finish(hash);
  }
}

String _$workspaceDescriptionHash() =>
    r'76523b8ebfed1316dd23408068937a05b6bf310a';
typedef WorkspaceDescriptionRef = AutoDisposeFutureProviderRef<String>;

/// See also [workspaceDescription].
@ProviderFor(workspaceDescription)
const workspaceDescriptionProvider = WorkspaceDescriptionFamily();

/// See also [workspaceDescription].
class WorkspaceDescriptionFamily extends Family<AsyncValue<String>> {
  /// See also [workspaceDescription].
  const WorkspaceDescriptionFamily();

  /// See also [workspaceDescription].
  WorkspaceDescriptionProvider call(
    int id,
  ) {
    return WorkspaceDescriptionProvider(
      id,
    );
  }

  @override
  WorkspaceDescriptionProvider getProviderOverride(
    covariant WorkspaceDescriptionProvider provider,
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
  String? get name => r'workspaceDescriptionProvider';
}

/// See also [workspaceDescription].
class WorkspaceDescriptionProvider extends AutoDisposeFutureProvider<String> {
  /// See also [workspaceDescription].
  WorkspaceDescriptionProvider(
    this.id,
  ) : super.internal(
          (ref) => workspaceDescription(
            ref,
            id,
          ),
          from: workspaceDescriptionProvider,
          name: r'workspaceDescriptionProvider',
          debugGetCreateSourceHash:
              const bool.fromEnvironment('dart.vm.product')
                  ? null
                  : _$workspaceDescriptionHash,
          dependencies: WorkspaceDescriptionFamily._dependencies,
          allTransitiveDependencies:
              WorkspaceDescriptionFamily._allTransitiveDependencies,
        );

  final int id;

  @override
  bool operator ==(Object other) {
    return other is WorkspaceDescriptionProvider && other.id == id;
  }

  @override
  int get hashCode {
    var hash = _SystemHash.combine(0, runtimeType.hashCode);
    hash = _SystemHash.combine(hash, id.hashCode);

    return _SystemHash.finish(hash);
  }
}

String _$workspaceRequestsIdsHash() =>
    r'd3803a59b81790ccceff9bfc296709f787ef16a7';
typedef WorkspaceRequestsIdsRef = AutoDisposeFutureProviderRef<List<int>>;

/// See also [workspaceRequestsIds].
@ProviderFor(workspaceRequestsIds)
const workspaceRequestsIdsProvider = WorkspaceRequestsIdsFamily();

/// See also [workspaceRequestsIds].
class WorkspaceRequestsIdsFamily extends Family<AsyncValue<List<int>>> {
  /// See also [workspaceRequestsIds].
  const WorkspaceRequestsIdsFamily();

  /// See also [workspaceRequestsIds].
  WorkspaceRequestsIdsProvider call(
    int id,
  ) {
    return WorkspaceRequestsIdsProvider(
      id,
    );
  }

  @override
  WorkspaceRequestsIdsProvider getProviderOverride(
    covariant WorkspaceRequestsIdsProvider provider,
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
  String? get name => r'workspaceRequestsIdsProvider';
}

/// See also [workspaceRequestsIds].
class WorkspaceRequestsIdsProvider
    extends AutoDisposeFutureProvider<List<int>> {
  /// See also [workspaceRequestsIds].
  WorkspaceRequestsIdsProvider(
    this.id,
  ) : super.internal(
          (ref) => workspaceRequestsIds(
            ref,
            id,
          ),
          from: workspaceRequestsIdsProvider,
          name: r'workspaceRequestsIdsProvider',
          debugGetCreateSourceHash:
              const bool.fromEnvironment('dart.vm.product')
                  ? null
                  : _$workspaceRequestsIdsHash,
          dependencies: WorkspaceRequestsIdsFamily._dependencies,
          allTransitiveDependencies:
              WorkspaceRequestsIdsFamily._allTransitiveDependencies,
        );

  final int id;

  @override
  bool operator ==(Object other) {
    return other is WorkspaceRequestsIdsProvider && other.id == id;
  }

  @override
  int get hashCode {
    var hash = _SystemHash.combine(0, runtimeType.hashCode);
    hash = _SystemHash.combine(hash, id.hashCode);

    return _SystemHash.finish(hash);
  }
}

String _$workspaceWorkspacesIdsHash() =>
    r'312aebaf485c36559e69f582b613cc3384110a4c';
typedef WorkspaceWorkspacesIdsRef = AutoDisposeFutureProviderRef<List<int>>;

/// See also [workspaceWorkspacesIds].
@ProviderFor(workspaceWorkspacesIds)
const workspaceWorkspacesIdsProvider = WorkspaceWorkspacesIdsFamily();

/// See also [workspaceWorkspacesIds].
class WorkspaceWorkspacesIdsFamily extends Family<AsyncValue<List<int>>> {
  /// See also [workspaceWorkspacesIds].
  const WorkspaceWorkspacesIdsFamily();

  /// See also [workspaceWorkspacesIds].
  WorkspaceWorkspacesIdsProvider call(
    int id,
  ) {
    return WorkspaceWorkspacesIdsProvider(
      id,
    );
  }

  @override
  WorkspaceWorkspacesIdsProvider getProviderOverride(
    covariant WorkspaceWorkspacesIdsProvider provider,
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
  String? get name => r'workspaceWorkspacesIdsProvider';
}

/// See also [workspaceWorkspacesIds].
class WorkspaceWorkspacesIdsProvider
    extends AutoDisposeFutureProvider<List<int>> {
  /// See also [workspaceWorkspacesIds].
  WorkspaceWorkspacesIdsProvider(
    this.id,
  ) : super.internal(
          (ref) => workspaceWorkspacesIds(
            ref,
            id,
          ),
          from: workspaceWorkspacesIdsProvider,
          name: r'workspaceWorkspacesIdsProvider',
          debugGetCreateSourceHash:
              const bool.fromEnvironment('dart.vm.product')
                  ? null
                  : _$workspaceWorkspacesIdsHash,
          dependencies: WorkspaceWorkspacesIdsFamily._dependencies,
          allTransitiveDependencies:
              WorkspaceWorkspacesIdsFamily._allTransitiveDependencies,
        );

  final int id;

  @override
  bool operator ==(Object other) {
    return other is WorkspaceWorkspacesIdsProvider && other.id == id;
  }

  @override
  int get hashCode {
    var hash = _SystemHash.combine(0, runtimeType.hashCode);
    hash = _SystemHash.combine(hash, id.hashCode);

    return _SystemHash.finish(hash);
  }
}

String _$workspaceRequestsHash() => r'e129a0c70457bf3e892b44f22332a564e333e1fc';
typedef WorkspaceRequestsRef = AutoDisposeFutureProviderRef<List<Request>>;

/// See also [workspaceRequests].
@ProviderFor(workspaceRequests)
const workspaceRequestsProvider = WorkspaceRequestsFamily();

/// See also [workspaceRequests].
class WorkspaceRequestsFamily extends Family<AsyncValue<List<Request>>> {
  /// See also [workspaceRequests].
  const WorkspaceRequestsFamily();

  /// See also [workspaceRequests].
  WorkspaceRequestsProvider call(
    int id,
  ) {
    return WorkspaceRequestsProvider(
      id,
    );
  }

  @override
  WorkspaceRequestsProvider getProviderOverride(
    covariant WorkspaceRequestsProvider provider,
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
  String? get name => r'workspaceRequestsProvider';
}

/// See also [workspaceRequests].
class WorkspaceRequestsProvider
    extends AutoDisposeFutureProvider<List<Request>> {
  /// See also [workspaceRequests].
  WorkspaceRequestsProvider(
    this.id,
  ) : super.internal(
          (ref) => workspaceRequests(
            ref,
            id,
          ),
          from: workspaceRequestsProvider,
          name: r'workspaceRequestsProvider',
          debugGetCreateSourceHash:
              const bool.fromEnvironment('dart.vm.product')
                  ? null
                  : _$workspaceRequestsHash,
          dependencies: WorkspaceRequestsFamily._dependencies,
          allTransitiveDependencies:
              WorkspaceRequestsFamily._allTransitiveDependencies,
        );

  final int id;

  @override
  bool operator ==(Object other) {
    return other is WorkspaceRequestsProvider && other.id == id;
  }

  @override
  int get hashCode {
    var hash = _SystemHash.combine(0, runtimeType.hashCode);
    hash = _SystemHash.combine(hash, id.hashCode);

    return _SystemHash.finish(hash);
  }
}

String _$workspaceWorkspacesHash() =>
    r'821ae3adef5017f087fda0f637707a052bc8b254';
typedef WorkspaceWorkspacesRef = AutoDisposeFutureProviderRef<List<Workspace>>;

/// See also [workspaceWorkspaces].
@ProviderFor(workspaceWorkspaces)
const workspaceWorkspacesProvider = WorkspaceWorkspacesFamily();

/// See also [workspaceWorkspaces].
class WorkspaceWorkspacesFamily extends Family<AsyncValue<List<Workspace>>> {
  /// See also [workspaceWorkspaces].
  const WorkspaceWorkspacesFamily();

  /// See also [workspaceWorkspaces].
  WorkspaceWorkspacesProvider call(
    int id,
  ) {
    return WorkspaceWorkspacesProvider(
      id,
    );
  }

  @override
  WorkspaceWorkspacesProvider getProviderOverride(
    covariant WorkspaceWorkspacesProvider provider,
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
  String? get name => r'workspaceWorkspacesProvider';
}

/// See also [workspaceWorkspaces].
class WorkspaceWorkspacesProvider
    extends AutoDisposeFutureProvider<List<Workspace>> {
  /// See also [workspaceWorkspaces].
  WorkspaceWorkspacesProvider(
    this.id,
  ) : super.internal(
          (ref) => workspaceWorkspaces(
            ref,
            id,
          ),
          from: workspaceWorkspacesProvider,
          name: r'workspaceWorkspacesProvider',
          debugGetCreateSourceHash:
              const bool.fromEnvironment('dart.vm.product')
                  ? null
                  : _$workspaceWorkspacesHash,
          dependencies: WorkspaceWorkspacesFamily._dependencies,
          allTransitiveDependencies:
              WorkspaceWorkspacesFamily._allTransitiveDependencies,
        );

  final int id;

  @override
  bool operator ==(Object other) {
    return other is WorkspaceWorkspacesProvider && other.id == id;
  }

  @override
  int get hashCode {
    var hash = _SystemHash.combine(0, runtimeType.hashCode);
    hash = _SystemHash.combine(hash, id.hashCode);

    return _SystemHash.finish(hash);
  }
}
// ignore_for_file: unnecessary_raw_strings, subtype_of_sealed_class, invalid_use_of_internal_member, do_not_use_environment, prefer_const_constructors, public_member_api_docs, avoid_private_typedef_functions
