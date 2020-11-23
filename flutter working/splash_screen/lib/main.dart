import 'dart:async';
import 'package:flutter/material.dart';
import 'package:splash_screen/Secondscree.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return (MaterialApp(
      home: SplashScreen(),
    ));
  }
}

class SplashScreen extends StatefulWidget {
  @override
  _SplashScreenState createState() => _SplashScreenState();
}

class _SplashScreenState extends State<SplashScreen> {
  @override
  void initState() {
    super.initState();
    Timer(
        Duration(seconds: 5),
        () => Navigator.of(context).pushReplacement(MaterialPageRoute(
            builder: (BuildContext context) => SecondScreen())));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body:Container(
        decoration: BoxDecoration(
          gradient: LinearGradient(
              begin: Alignment.topLeft,
              end: Alignment(0.9, 1.2),
              colors: [const Color(0xfee00000), const Color(0xffeeee00)],
              tileMode: TileMode.repeated),
        ),
        child: Column(

          children:<Widget> [
            Padding(
            padding: EdgeInsets.all(200.0),
        ),
            Text('HIKAI',
                style: TextStyle(
                    color: Colors.black,
                fontWeight: FontWeight.bold,
                  fontSize: 50,
                  letterSpacing: 10,
                  fontFamily: 'Schoolbell',

                )
            ),

            Container(
              child: Center(
                child: Image.asset('logo.png'),
              ),
            ),
            Text('Hybrid Interactive Knowledge Assimilation Initiative',
                 textAlign: TextAlign.center,
                style: TextStyle(

                  color: Colors.black,
                  fontWeight: FontWeight.bold,
                  fontSize: 40,
                  letterSpacing: 10,
                  fontFamily: 'Schoolbell',

                )
            ),

          ],
        ),
      ),
    );
  }
}
