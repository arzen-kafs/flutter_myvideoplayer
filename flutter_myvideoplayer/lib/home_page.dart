import 'package:flutter/material.dart';
import 'package:flutter_myvideoplayer/lecture_bloc.dart';
import 'package:flutter_myvideoplayer/video_bloc.dart';
import 'package:video_player/video_player.dart';

import 'chapter.dart';
import 'chapter_bloc.dart';
import 'hikai_player.dart';
import 'lecture.dart';

const ASPECT_RATIO = 3 / 2;

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  final videobloc = VideoBloc();
  final chapterBloc = ChapterBloc();
  final lectureBloc = LectureBloc();

  @override
  Widget build(BuildContext context) {
    final screenWidth = MediaQuery.of(context).size.width;
    final containerHeight = screenWidth / ASPECT_RATIO;
    // final screenHeight = MediaQuery.of(context).size.height;
    return DefaultTabController(
      length: 1,
      child: Scaffold(
        appBar: AppBar(
          title: Text(widget.title),
        ),
        body: SafeArea(
          child: Column(
            children: <Widget>[
              Container(
                color: Colors.white,
                height: containerHeight,
                child: StreamBuilder(
                  stream: videobloc.urlStream,
                  initialData: [
                    "https://file-examples-com.github.io/uploads/2017/04/file_example_MP4_480_1_5MG.mp4",
                    "Sample"
                  ],
                  builder: (content, snapshot) {
                    var videoPlayer = VideoPlayerWidget(
                      controller:
                          VideoPlayerController.network(snapshot.data[0]),
                      videoTitle: snapshot.data[1],
                    );

                    return videoPlayer;
                  },
                ),
              ),
              PreferredSize(
                preferredSize: Size.fromHeight(50.0),
                child: TabBar(
                  labelColor: Colors.black,
                  tabs: [
                    Tab(
                      text: 'Lectures',
                    ),
                  ], // list of tabs
                ),
              ),
              //TabBarView(children: [ImageList(),])
              Expanded(
                child: TabBarView(
                  children: [
                    Container(
                      color: Colors.white,
                      child: getChapterWidget(),
                    ),
                  ],
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  Widget getChapterWidget() {
    /*The StreamBuilder widget,
    basically this widget will take stream of data (todos)
    and construct the UI (with state) based on the stream
    */
    return StreamBuilder(
      stream: chapterBloc.chapter,
      builder:
          (BuildContext context, AsyncSnapshot<List<Chapter>> snapshotChapter) {
        return StreamBuilder(
          stream: lectureBloc.lecture,
          builder: (BuildContext context,
              AsyncSnapshot<List<Lecture>> snapshotLecture) {
            if (snapshotChapter.hasData && snapshotLecture.hasData) {
              print(snapshotChapter.data.length);
              print(snapshotLecture.data.length);
              videobloc.urlSink.add([
                "${snapshotLecture.data[0].file}",
                "${snapshotLecture.data[0].title}"
              ]);
              return getChapterListWidget(snapshotChapter, snapshotLecture);
            } else {
              return Text("Please wait");
            }
          },
        );
      },
    );
  }

//getChapterListWidget(snapshot);
  Widget getChapterListWidget(AsyncSnapshot<List<Chapter>> snapshotChapter,
      AsyncSnapshot<List<Lecture>> snapshotLecture) {
    /*Since most of our operations are asynchronous
    at initial state of the operation there will be no stream
    so we need to handle it if this was the case
    by showing users a processing/loading indicator*/
    if (snapshotChapter.hasData && snapshotLecture.hasData) {
      print("${snapshotChapter.data.length}");
      print("#########################################");
      return ListView.builder(
        itemCount: snapshotChapter.data.length,
        itemBuilder: (context, chapterIndex) {
          Chapter chapter = snapshotChapter.data[chapterIndex];
          List<Lecture> snapshotLectureByChapter = [];
          for (int i = 0; i < snapshotLecture.data.length; i++) {
            if (chapter.chapterNo == snapshotLecture.data[i].chapterNo) {
              print(snapshotLecture.data[i].file);
              snapshotLectureByChapter.add(snapshotLecture.data[i]);
            }
          }
          return Column(
            children: [
              ListTile(
                leading: Icon(Icons.local_play_outlined),
                title: Text(
                  "${chapterIndex + 1}  ${chapter.title}",
                  style: TextStyle(
                    fontWeight: FontWeight.bold,
                  ),
                ),
                trailing: Text(
                  "Total Lectures: ${chapter.items}",
                  style: TextStyle(
                    fontWeight: FontWeight.bold,
                  ),
                ),
              ),
              ListView.builder(
                itemBuilder: (context, lectureIndex) {
                  Lecture lecture = snapshotLectureByChapter[lectureIndex];
                  return ListTile(
                    leading: lecture.content == "video"
                        ? Icon(Icons.play_circle_fill)
                        : Icon(Icons.book),
                    title: Text("${lecture.title}"),
                    trailing: IconButton(
                      icon: Icon(Icons.download_sharp),
                      onPressed: () {
                        print("Download...............");
                      },
                    ),
                    onTap: () {
                      print("${lecture.file}");

                      videobloc.urlSink
                          .add(["${lecture.file}", "${lecture.title}"]);
                    },
                  );
                },
                itemCount: snapshotLectureByChapter.length,
                physics: ClampingScrollPhysics(),
                shrinkWrap: true,
              )
            ],
          );
        },
      );
    } else {
      return Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            CircularProgressIndicator(),
          ],
        ),
      );
    }
  }
}
