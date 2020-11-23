import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';
import 'package:myrkmhikai/Activities/Videoplayer/main.dart';
import 'package:percent_indicator/circular_percent_indicator.dart';

// ignore: must_be_immutable
class DashBoard extends StatelessWidget {
  var _mysize;
  @override
  Widget build(BuildContext context) {
    _mysize = MediaQuery.of(context).size;
    return MaterialApp(
        debugShowCheckedModeBanner: false,
        home: Scaffold(
          body: Stack(
            children: <Widget>[dashBg, content],
          ),
        ));
  }

  get dashBg => Column(
        children: <Widget>[
          Expanded(
            child: Container(
              decoration: BoxDecoration(
                gradient: LinearGradient(
                    begin: Alignment.topLeft,
                    end: Alignment(1.0, 1.0),
                    colors: [
                      const Color(0xfee00000),
                      const Color(0xfee05000),
                      const Color(0xffeeee00)
                    ],
                    tileMode: TileMode.repeated),
                // image: DecorationImage(
                //     image: AssetImage("assets/images/illustration.png"),
                //     fit: BoxFit.cover),
              ),
            ),
            flex: 2,
          ),
          Expanded(
            child: Container(color: Colors.white70),
            flex: 5,
          ),
        ],
      );

  get content => Container(
        child: Column(
          children: <Widget>[
            header,
            grid,
          ],
        ),
      );

  get header => ListTile(
        contentPadding: EdgeInsets.only(left: 50, right: 50, top: 30),
        title: Text(
          'Dashboard',
          style: TextStyle(color: Colors.black),
        ),
        subtitle: Text(
          '10 items',
          style: TextStyle(color: Colors.black),
        ),
        trailing: CircleAvatar(
          radius: 50,
          backgroundColor: Colors.white,
        ),
      );

  get grid => Expanded(
        child: Container(
          //color: Colors.redAccent,
          padding: EdgeInsets.only(left: 16, right: 16, bottom: 5),
          child: GridView.count(
            crossAxisSpacing: 20,
            mainAxisSpacing: 20,
            crossAxisCount: 2,
            childAspectRatio: 0.7,
            children: List.generate(6, (_) {
              return Card(
                shadowColor: Colors.red,
                elevation: 5,
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(10)),
                child: InkWell(
                  onTap:() {
                    print("Clicked");
                    Navigator.push(
                      this.content,
                      MaterialPageRoute(
                        builder: (context) {
                          return HikaiVideoPlayer();
                        },
                      ),
                    );

                  },
                  child: Column(
                    children: <Widget>[
                      Center(
                        child: Column(
                          mainAxisSize: MainAxisSize.min,
                          children: <Widget>[
                            SvgPicture.asset(
                              'assets/icons/book.svg',
                              height: _mysize.height * 0.1,
                              width: _mysize.height * 0.1,
                            ),
                            CircularPercentIndicator(
                                radius: 100,
                                animation: true,
                                animationDuration: 1200,
                                lineWidth: 15.0,
                                percent: 0.4,
                                center: new Text(
                                  "40% Completed",
                                  style: new TextStyle(
                                      fontWeight: FontWeight.bold,
                                      fontSize: 20.0),
                                  textAlign: TextAlign.center,
                                ),
                                circularStrokeCap: CircularStrokeCap.butt,
                                backgroundColor: Colors.blueGrey,
                                progressColor: Colors.deepOrange),
                            Text('English')
                          ],
                        ),
                      ),
                    ],
                  ),
                ),
              );
            }),
          ),
        ),
      );
}
