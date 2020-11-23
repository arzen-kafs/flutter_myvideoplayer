
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';


class StreamTaken extends StatefulWidget {
  StreamTaken({Key key}) : super(key: key);

  @override
  _StreamTakenState createState() => _StreamTakenState();
}


class _StreamTakenState extends State<StreamTaken> {
  String dropdownValue = 'Not Applicable';

  @override
  Widget build(BuildContext context) {
    return Padding(
          padding: EdgeInsets.only(right: 100),
      child: ListTile(
        title: Text(
          'Stream',
          textAlign: TextAlign.center,
          style: TextStyle(
              fontSize: 20,
              fontWeight: FontWeight.bold,
              color: Colors.deepPurple),
        ),
        trailing: DropdownButton<String>(
          value: dropdownValue,


          elevation: 16,
          style: TextStyle(color: Colors.deepPurple,),
          underline: Container(
            height: 2,

            color: Colors.deepPurpleAccent,
          ),
          onChanged: (String newValue) {
            setState(() {
              dropdownValue = newValue;
            });
          },
          items: <String>['Not Applicable', 'Science','Commerce','Arts']
              .map<DropdownMenuItem<String>>((String value) {
            return DropdownMenuItem<String>(
              value: value,
              child: Text(value),
            );
          }).toList(),
        ),
      ),
    );
  }
}
