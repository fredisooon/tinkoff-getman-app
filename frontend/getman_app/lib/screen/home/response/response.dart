import 'package:auto_route/auto_route.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import 'body.dart';
import 'headers.dart';
import 'info.dart';


@RoutePage()
class ResponsePage extends ConsumerStatefulWidget {
  const ResponsePage({
    @PathParam() required this.id,
    super.key,
  });

  final int id;

  @override
  ConsumerState<ConsumerStatefulWidget> createState() => _ResponsePageState();
  
  @override
  void debugFillProperties(DiagnosticPropertiesBuilder properties) {
    super.debugFillProperties(properties);
    properties.add(IntProperty('id', id));
  }
}

class _ResponsePageState extends ConsumerState<ResponsePage> with TickerProviderStateMixin {
  late final TabController _tabController;

  @override
  void initState() {
    _tabController = TabController(length: 3, vsync: this);
    super.initState();
  }

  @override
  void dispose() {
    _tabController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) => Scaffold(
    backgroundColor: Colors.transparent,
    appBar: TabBar(
      physics: const NeverScrollableScrollPhysics(),
      controller: _tabController,
      tabs: const [
        Tab(
          text: 'Body',
        ),
        Tab(
          text: 'Headers',
        ),
        Tab(
          text: 'Info',
        )
      ],
    ),
    body: TabBarView(
      physics: const NeverScrollableScrollPhysics(),
      controller: _tabController,
      children: [
        ResponseBodyPage(id: widget.id),
        ResponseHeadersPage(id: widget.id),
        ResponseInfoPage(id: widget.id),
      ],
    ),
  );
}
