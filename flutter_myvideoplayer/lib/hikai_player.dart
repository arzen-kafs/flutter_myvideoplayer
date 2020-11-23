import 'package:chewie/chewie.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:video_player/video_player.dart';

const ASPECT_RATIO = 3 / 2;

class VideoPlayerWidget extends StatefulWidget {
  final VideoPlayerController controller;
  final String videoTitle;

  const VideoPlayerWidget({
    Key key,
    this.controller,
    this.videoTitle,
  })  : assert(controller != null),
        assert(videoTitle != null),
        super(key: key);

  @override
  _VideoPlayerWidgetState createState() => _VideoPlayerWidgetState();
}

class _VideoPlayerWidgetState extends State<VideoPlayerWidget> {
  ChewieController _chewieController;

  @override
  Widget build(BuildContext context) {
    final screenWidth = MediaQuery.of(context).size.width;
    final containerHeight = screenWidth / ASPECT_RATIO;
    return Stack(
      children: <Widget>[
        Container(
          height: containerHeight,
          child: Chewie(
            controller: ChewieController(
              videoPlayerController: widget.controller,
              aspectRatio: ASPECT_RATIO,
              autoInitialize: true,
              autoPlay: true,
              deviceOrientationsAfterFullScreen: [DeviceOrientation.portraitUp],
              materialProgressColors: ChewieProgressColors(
                playedColor: Colors.purple,
                handleColor: Colors.purple,
                backgroundColor: Colors.grey,
                bufferedColor: Colors.purple[100],
              ),
              placeholder: Container(
                color: Colors.orangeAccent,
              ),
            ),
          ),
        ),
        Container(
          width: screenWidth,
          padding: EdgeInsets.all(4.0),
          decoration: BoxDecoration(
            color: Colors.green.withOpacity(0.5),
          ),
          child: Text(
            widget.videoTitle,
            style: Theme.of(context)
                .textTheme
                .title
                .copyWith(color: Color.fromRGBO(14, 26, 92, 1)),
          ),
        ),
      ],
    );
  }

  @override
  void dispose() {
    widget.controller.dispose();
    _chewieController.dispose();
    super.dispose();
  }
}
