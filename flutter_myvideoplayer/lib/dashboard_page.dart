import 'package:flutter/material.dart';
import 'package:flutter_myvideoplayer/lecture_bloc.dart';
import 'package:flutter_myvideoplayer/main1.dart';

import 'chapter_bloc.dart';

class DashboardPage extends StatefulWidget {
  DashboardPage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _DashboardPageState createState() => _DashboardPageState();
}

class _DashboardPageState extends State<DashboardPage> {
  final chapterBloc = ChapterBloc();
  final lectureBloc = LectureBloc();
  bool apiCall = false;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text(widget.title),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              FutureBuilder(
                future: chapterBloc.fetchChapter(),
                builder: (BuildContext context, AsyncSnapshot snapshot) {
                  switch (snapshot.connectionState) {
                    case ConnectionState.none:
                      return Text('Press button to start.');
                    case ConnectionState.active:
                    case ConnectionState.waiting:
                      return CircularProgressIndicator();
                    case ConnectionState.done:
                      if (snapshot.hasError)
                        return Text('Error: ${snapshot.error}');
                      if (snapshot.data == null)
                        return CircularProgressIndicator();
                  }
                  print("==============CAHPTER=============");
                  print(snapshot.data);
                  return Column(
                    children: [
                      Text("Chapter Sync Successfully to the server"),
                      RaisedButton(
                        onPressed: () {
                          Navigator.pushReplacement(
                            context,
                            MaterialPageRoute(
                                builder: (context) => MyHomePage(
                                      title: "ClassRoom",
                                    )),
                          );
                        },
                        child:
                            Text('Class Room', style: TextStyle(fontSize: 20)),
                      ),
                    ],
                  );
                },
              ),
              FutureBuilder(
                future: lectureBloc.fetchLecture(),
                builder: (BuildContext context, AsyncSnapshot snapshot) {
                  switch (snapshot.connectionState) {
                    case ConnectionState.none:
                      return Text('Press button to start.');
                    case ConnectionState.active:
                    case ConnectionState.waiting:
                      return CircularProgressIndicator();
                    case ConnectionState.done:
                      if (snapshot.hasError)
                        return Text('Error: ${snapshot.error}');
                      if (snapshot.data == null)
                        return CircularProgressIndicator();
                  }
                  print("==============LECTURE=============");
                  print(snapshot.data);
                  return Column(
                    children: [
                      Text("Lecture Sync Successfully to the server"),
                      RaisedButton(
                        onPressed: () {
                          Navigator.pushReplacement(
                            context,
                            MaterialPageRoute(
                                builder: (context) => MyHomePage(
                                      title: "ClassRoom",
                                    )),
                          );
                        },
                        child:
                            Text('Class Room', style: TextStyle(fontSize: 20)),
                      ),
                    ],
                  );
                },
              )
            ],
          ),
        ));
  }
}
