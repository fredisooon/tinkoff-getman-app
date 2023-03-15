import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';


@RoutePage()
class ResponseHolderPage extends ConsumerStatefulWidget {
  const ResponseHolderPage({
    super.key,
  });

  @override
  ConsumerState<ConsumerStatefulWidget> createState() => _ResponseHolderPageState();
}

class _ResponseHolderPageState extends ConsumerState<ResponseHolderPage> {
  @override
  Widget build(BuildContext context) => Column(
    crossAxisAlignment: CrossAxisAlignment.stretch,
    children: [
      DecoratedBox(
        decoration: BoxDecoration(
          border: Border(
            bottom: BorderSide(
              color: Theme.of(context).dividerColor,
              width: 1,
            ),
          ),
        ),
        child: const Padding(
          padding: EdgeInsets.only(left: 8.0, bottom: 2.0),
          child: Text('Response'),
        ),
      ),
      const Expanded(
        child: AutoRouter(),
      ),
    ],
  );
}
