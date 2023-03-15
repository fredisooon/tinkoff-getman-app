import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import 'color_schemes.g.dart';
import 'globals.dart';
import 'screen/routes.dart';


class App extends StatelessWidget {
  App({super.key});

  final _appRouter = AppRouter();

  @override
  Widget build(BuildContext context) =>
    ProviderScope(
      parent: container,
      child: MaterialApp.router(
        theme: ThemeData(useMaterial3: true, colorScheme: lightColorScheme),
        darkTheme: ThemeData(useMaterial3: true, colorScheme: darkColorScheme),
        routerConfig: _appRouter.config(),
      ),
    );
}
