// GENERATED CODE - DO NOT MODIFY BY HAND

// **************************************************************************
// AutoRouterGenerator
// **************************************************************************

part of 'routes.dart';

abstract class _$AppRouter extends RootStackRouter {
  _$AppRouter([GlobalKey<NavigatorState>? navigatorKey]) : super(navigatorKey);

  @override
  final Map<String, PageFactory> pagesMap = {
    InitialRoute.name: (routeData) {
      return AutoRoutePage<dynamic>(
        routeData: routeData,
        child: const InitialPage(),
      );
    },
    RequestRoute.name: (routeData) {
      final pathParams = routeData.inheritedPathParams;
      final args = routeData.argsAs<RequestRouteArgs>(
          orElse: () => RequestRouteArgs(id: pathParams.getInt('id')));
      return AutoRoutePage<dynamic>(
        routeData: routeData,
        child: RequestPage(
          id: args.id,
          key: args.key,
        ),
      );
    },
    ResponseInitialRoute.name: (routeData) {
      return AutoRoutePage<dynamic>(
        routeData: routeData,
        child: const ResponseInitialPage(),
      );
    },
    ResponseRoute.name: (routeData) {
      final pathParams = routeData.inheritedPathParams;
      final args = routeData.argsAs<ResponseRouteArgs>(
          orElse: () => ResponseRouteArgs(id: pathParams.getInt('id')));
      return AutoRoutePage<dynamic>(
        routeData: routeData,
        child: ResponsePage(
          id: args.id,
          key: args.key,
        ),
      );
    },
    ResponseHolderRoute.name: (routeData) {
      return AutoRoutePage<dynamic>(
        routeData: routeData,
        child: const ResponseHolderPage(),
      );
    },
    HomeRoute.name: (routeData) {
      return AutoRoutePage<dynamic>(
        routeData: routeData,
        child: const HomePage(),
      );
    },
  };
}

/// generated route for
/// [InitialPage]
class InitialRoute extends PageRouteInfo<void> {
  const InitialRoute({List<PageRouteInfo>? children})
      : super(
          InitialRoute.name,
          initialChildren: children,
        );

  static const String name = 'InitialRoute';

  static const PageInfo<void> page = PageInfo<void>(name);
}

/// generated route for
/// [RequestPage]
class RequestRoute extends PageRouteInfo<RequestRouteArgs> {
  RequestRoute({
    required int id,
    Key? key,
    List<PageRouteInfo>? children,
  }) : super(
          RequestRoute.name,
          args: RequestRouteArgs(
            id: id,
            key: key,
          ),
          rawPathParams: {'id': id},
          initialChildren: children,
        );

  static const String name = 'RequestRoute';

  static const PageInfo<RequestRouteArgs> page =
      PageInfo<RequestRouteArgs>(name);
}

class RequestRouteArgs {
  const RequestRouteArgs({
    required this.id,
    this.key,
  });

  final int id;

  final Key? key;

  @override
  String toString() {
    return 'RequestRouteArgs{id: $id, key: $key}';
  }
}

/// generated route for
/// [ResponseInitialPage]
class ResponseInitialRoute extends PageRouteInfo<void> {
  const ResponseInitialRoute({List<PageRouteInfo>? children})
      : super(
          ResponseInitialRoute.name,
          initialChildren: children,
        );

  static const String name = 'ResponseInitialRoute';

  static const PageInfo<void> page = PageInfo<void>(name);
}

/// generated route for
/// [ResponsePage]
class ResponseRoute extends PageRouteInfo<ResponseRouteArgs> {
  ResponseRoute({
    required int id,
    Key? key,
    List<PageRouteInfo>? children,
  }) : super(
          ResponseRoute.name,
          args: ResponseRouteArgs(
            id: id,
            key: key,
          ),
          rawPathParams: {'id': id},
          initialChildren: children,
        );

  static const String name = 'ResponseRoute';

  static const PageInfo<ResponseRouteArgs> page =
      PageInfo<ResponseRouteArgs>(name);
}

class ResponseRouteArgs {
  const ResponseRouteArgs({
    required this.id,
    this.key,
  });

  final int id;

  final Key? key;

  @override
  String toString() {
    return 'ResponseRouteArgs{id: $id, key: $key}';
  }
}

/// generated route for
/// [ResponseHolderPage]
class ResponseHolderRoute extends PageRouteInfo<void> {
  const ResponseHolderRoute({List<PageRouteInfo>? children})
      : super(
          ResponseHolderRoute.name,
          initialChildren: children,
        );

  static const String name = 'ResponseHolderRoute';

  static const PageInfo<void> page = PageInfo<void>(name);
}

/// generated route for
/// [HomePage]
class HomeRoute extends PageRouteInfo<void> {
  const HomeRoute({List<PageRouteInfo>? children})
      : super(
          HomeRoute.name,
          initialChildren: children,
        );

  static const String name = 'HomeRoute';

  static const PageInfo<void> page = PageInfo<void>(name);
}
