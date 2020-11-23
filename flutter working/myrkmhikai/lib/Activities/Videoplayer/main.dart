import 'dart:math';

import 'package:flutter/material.dart';

import 'package:video_player/video_player.dart';
import 'chewei_list_item.dart';
import 'nested_list_view.dart';




class HikaiVideoPlayer extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(),
    );
  }
}

// ignore: must_be_immutable
class MyHomePage extends StatelessWidget {
  String item;

 MyHomePage({Key key, this.item}) : super(key: key);

  @override
  Widget build(BuildContext context) {

    return Scaffold(
      appBar: AppBar(
        title: Text('Video Player'),
      ),
      body: ListView(
        children: <Widget>[
          ChewieListItem(
            videoPlayerController: VideoPlayerController.network(
            'https://flutter.github.io/assets-for-api-docs/assets/videos/butterfly.mp4'),
          ),
          ExampleOne(title: 'List'),
         // Text('$item')
        ],

      ),
    );
  }
}
