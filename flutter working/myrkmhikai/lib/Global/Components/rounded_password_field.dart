import 'package:flutter/material.dart';
import 'package:myrkmhikai/Global/Components/text_field_container.dart';


import 'package:myrkmhikai/Resources/colors.dart';

class RoundedPasswordField extends StatelessWidget {
  final String hintText;
  final ValueChanged<String> onChanged;
  final TextEditingController controller;
  RoundedPasswordField({
    Key key,
    this.hintText,
    this.onChanged,
    this.controller,

  }) : super(key: key);

 bool _passwordVisible=false;
  void initState() {
    _passwordVisible = false;
  }
  @override
  Widget build(BuildContext context) {
    return TextFieldContainer(
      child: TextField(
        obscureText: !_passwordVisible,//This will obscure text dynamically
        controller: controller,
        onChanged: onChanged,
        cursorColor: PrimaryColor,
        decoration: InputDecoration(

          hintText: hintText,
          icon: Icon(
            Icons.lock,
            color: PrimaryColor,
          ),
          suffixIcon: IconButton(
            icon: Icon(
              // Based on passwordVisible state choose the icon
              _passwordVisible
                  ? Icons.visibility_off
                  : Icons.visibility,
              color: Theme.of(context).primaryColorDark,
            ),
            onPressed: () {
              // Update the state i.e. toogle the state of passwordVisible variable
                _passwordVisible = !_passwordVisible;
              },
          ),
          border: InputBorder.none,

        ),
      ),
    );
  }
}
