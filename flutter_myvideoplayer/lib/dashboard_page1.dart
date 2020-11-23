import 'package:flutter/material.dart';

import 'chapter_bloc.dart';

class DashboardPage extends StatefulWidget {
  DashboardPage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _DashboardPageState createState() => _DashboardPageState();
}

class _DashboardPageState extends State<DashboardPage> {
  final chapterBloc = ChapterBloc();
  bool apiCall = false;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text(widget.title),
        ),
        body: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          children: [
            apiCall
                ? syncDatabase()
                : RaisedButton(
                    onPressed: () {
                      setState(() {
                        apiCall = true;
                      });
                    },
                    child: Text('Load Data', style: TextStyle(fontSize: 20)),
                  ),
          ],
        ));
  }

  syncDatabase() async {
    FutureBuilder(
      future: chapterBloc.fetchChapter(),
      builder: (BuildContext context, AsyncSnapshot snapshot) {
        switch (snapshot.connectionState) {
          case ConnectionState.none:
            return Text('Press button to start.');
          case ConnectionState.active:
          case ConnectionState.waiting:
            return Text('Awaiting result...');
          case ConnectionState.done:
            if (snapshot.hasError) return Text('Error: ${snapshot.error}');
        }
        return Text("aaaaa"); // unreachable
      },
    );
  }

  getProperWidget() {
    if (apiCall)
      return new CircularProgressIndicator();
    else
      return Text("Uploaded");
  }
}
