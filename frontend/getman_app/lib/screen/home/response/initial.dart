import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';


@RoutePage()
class ResponseInitialPage extends StatelessWidget {
  const ResponseInitialPage({super.key});

  @override
  Widget build(BuildContext context) => const Center(
    child: Text('Click Send to get response.'),
  );
}
