import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

class Gender extends StatefulWidget {
  Gender({Key key}) : super(key: key);

  @override
  _GenderState createState() => _GenderState();
}

class _GenderState extends State<Gender> {
  String dropdownValue = 'Male';

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.only(right: 150),
      child: ListTile(
        title: Text(
          'Gender',
          textAlign: TextAlign.center,
          style: TextStyle(
              fontSize: 20,
              fontWeight: FontWeight.bold,
              color: Colors.deepPurple),
        ),
        trailing: DropdownButton<String>(
          value: dropdownValue,
          elevation: 16,
          style: TextStyle(
            color: Colors.deepPurple,
          ),
          underline: Container(
            height: 2,
            color: Colors.deepPurpleAccent,
          ),
          onChanged: (String newValue) {
            setState(() {
              dropdownValue = newValue;
            });
          },
          items: <String>['Male', 'Female']
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
