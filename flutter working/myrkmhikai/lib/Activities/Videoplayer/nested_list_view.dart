import 'package:flutter/material.dart';
import 'chewei_list_item.dart';
import 'package:video_player/video_player.dart';
class ExampleOne extends StatefulWidget {
  final String title;

  ExampleOne({Key key, this.title}) : super(key: key);

  @override
  _ExampleOneState createState() => new _ExampleOneState(url: null);
}

String item;
var arr = [
  "https://html5demos.com/assets/dizzy.mp4",
  "https://flutter.github.io/assets-for-api-docs/assets/videos/butterfly.mp4"
];

class _ExampleOneState extends State<ExampleOne> {
  var url;

  _ExampleOneState({@required this.url});
  @override
  Widget build(BuildContext context) {
    final screenHeight = MediaQuery.of(context).size.height;
    return SizedBox(
      height: screenHeight,
      child: ListView.builder(
        scrollDirection: Axis.vertical,
        shrinkWrap: true,
        itemBuilder: (context, index) {
          return Padding(
            padding: EdgeInsets.symmetric(vertical: 16.0),
            child: Column(
              children: <Widget>[
                ListTile(
                  leading: Text(
                    'Chapter Name',
                    style: TextStyle(fontSize: 30, fontWeight: FontWeight.bold),
                  ),
                  //title: Text('$index',textAlign: TextAlign.center,),
                  trailing: Text(
                    '$index',
                    style: TextStyle(fontSize: 10),
                  ),
                  //shape: Theme.of(context).textTheme.body2,
                ),
                Card(
                  child: ListView.builder(
                    itemBuilder: (context, index) {
                      return Padding(
                          padding: EdgeInsets.symmetric(
                            horizontal: 16.0,
                            vertical: 8.0,
                          ),
                          child: ListTile(
                            title: Text(
                                arr[index],
                                style: TextStyle(
                                    fontSize: 10, fontWeight: FontWeight.bold)),
                            trailing: Wrap(
                              spacing: 12, // space between two icons
                              children: <Widget>[
                                Icon(Icons.download_sharp), // icon-1
                                Icon(Icons.share), // icon-2
                              ],
                            ),
                            onTap: () => {url = arr[index],
                            ChewieListItem(
                            videoPlayerController: VideoPlayerController.network(
                                arr[index]),
                            ),
                              print(url)},
                          ));
                    },
                    itemCount: arr.length,
                    shrinkWrap:
                        true, // todo comment this out and check the result
                    physics: ClampingScrollPhysics(), // todo comment this out and check the result
                  ),
                  elevation: 8,
                ),
              ],
            ),
          );
        },
        itemCount: 9,
      ),
    );
  }
// ignore: non_constant_identifier_names
}

class VideoUrl {
  final String url;
  VideoUrl(this.url);
}
