import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';


@RoutePage()
class InitialPage extends StatelessWidget {
  const InitialPage({super.key});

  @override
  Widget build(BuildContext context) => 
    const Center(
      child: Text('Select or create request.'),
    );
}
