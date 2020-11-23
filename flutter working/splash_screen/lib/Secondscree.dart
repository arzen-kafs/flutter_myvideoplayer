import 'package:flutter/material.dart';

class SecondScreen extends StatelessWidget {
  @override
  // ignore: missing_return
  Widget build(BuildContext context) {
    return(
    MaterialApp(
      home: Scaffold(
        appBar:AppBar(
          title: Text('Second Screen'),
        ) ,
      ),
      )
    );
  }
}
