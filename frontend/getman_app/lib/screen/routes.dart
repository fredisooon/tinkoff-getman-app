import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';

import 'home.dart';
import 'home/initial.dart';
import 'home/request.dart';
import 'home/response/initial.dart';
import 'home/response/response.dart';
import 'home/response_holder.dart';

part 'routes.gr.dart';


@AutoRouterConfig()
class AppRouter extends _$AppRouter {
  @override
  RouteType get defaultRouteType => const RouteType.material();

  @override    
  final List<AutoRoute> routes = [
    RedirectRoute(path: '/', redirectTo: '/home'),
    AutoRoute(
      path: '/home',
      page: HomeRoute.page,
      children: [
        AutoRoute(
          path: '',
          page: InitialRoute.page,
        ),
        AutoRoute(
          path: 'request/:id',
          page: RequestRoute.page,
          children: [
            RedirectRoute(path: '', redirectTo: 'response'),
            AutoRoute(
              path: 'response',
              page: ResponseInitialRoute.page, 
            ),
            AutoRoute(
              path: 'response/:id',
              page: ResponseRoute.page,
            ),
          ],
        ),
      ],
    ),
  ];
}
