import 'package:auto_route/auto_route.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';


class SurePage extends StatelessWidget {
  const SurePage({
    required this.text,
    super.key,
  });

  final String text;

  @override
  Widget build(BuildContext context) => AlertDialog(
    content: Column(
      mainAxisSize: MainAxisSize.min,
      children: [
        Text(text,
          style: Theme.of(context).textTheme.headlineMedium,
        ),
        const SizedBox(
          height: 18.0,
        ),
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: [
            FilledButton.tonal(
              onPressed: () async => AutoRouter.of(context).pop(false), 
              child: const Text('No'),
            ),
            FilledButton.tonal(
              onPressed: () async => AutoRouter.of(context).pop(true), 
              child: const Text('Yes',
                style: TextStyle(
                  color: Colors.red,
                ),
              ),
            ),
          ],
        ),
      ],
    ),
  );
  
  @override
  void debugFillProperties(DiagnosticPropertiesBuilder properties) {
    super.debugFillProperties(properties);
    properties.add(StringProperty('text', text));
  }
}
