import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:multi_split_view/multi_split_view.dart';

import 'home/superior_tree_view.dart';


@RoutePage()
class HomePage extends ConsumerStatefulWidget {
  const HomePage({super.key});

  @override
  ConsumerState<ConsumerStatefulWidget> createState() => _WorkspaceState();
}

class _WorkspaceState extends ConsumerState<HomePage> {
  @override
  Widget build(BuildContext context) => Material(
    child: Scaffold(
      appBar: AppBar(
        title: const Text('getman'),
      ),
      body: MultiSplitViewTheme(
        data: MultiSplitViewThemeData(
          dividerPainter: DividerPainters.grooved1(
            color: Colors.indigo[100]!,
            highlightedColor: Colors.indigo[900]!,
          ),
        ),
        child: MultiSplitView(
          controller: MultiSplitViewController(
            areas: [
              Area(
                weight: 0.3,
                minimalWeight: 0.2,
              ),
              Area(
                weight: 0.7,
                minimalWeight: 0.4,
              ),
            ],
          ),
          children: [
            ListView(
              children: const [
                SuperiorTreeItem(id: 0, parent: 0,),
              ],
            ),
            const AutoRouter(),
          ],
        ),
      ),
    ),
  );
}
