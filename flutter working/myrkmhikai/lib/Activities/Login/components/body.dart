import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/painting.dart';
import 'package:flutter_svg/svg.dart';
import 'package:myrkmhikai/Activities/Drawer/NavDrawer.dart';
import 'package:myrkmhikai/Activities/Signup/components/or_divider.dart';
import 'package:myrkmhikai/Activities/Signup/signup_screen.dart';
import 'package:myrkmhikai/Global/Components/already_have_an_account_acheck.dart';
import 'package:myrkmhikai/Global/Components/rounded_button.dart';
import 'package:myrkmhikai/Global/Components/rounded_input_field.dart';
import 'package:myrkmhikai/Global/Components/rounded_password_field.dart';
import 'package:myrkmhikai/Resources/colors.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:http/http.dart' as http;
import '../Model.dart';
import 'background.dart';

class Body extends StatelessWidget {
  const Body({
    Key key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    return Background(
      child: SingleChildScrollView(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            SizedBox(height: size.height * 0.04),
            Text(
              "WELCOME BACK",
              style: TextStyle(fontWeight: FontWeight.bold,fontFamily: 'Raleway',fontSize: 40),
            ),
            SizedBox(height: size.height * 0.03),
            SvgPicture.asset(
              "assets/icons/login.svg",
              height: size.height * 0.35,
            ),
            SizedBox(height: size.height * 0.03),
            RoundedInputField(
              controller: (emailController),
              hintText: "Your Email",
              onChanged: (value) {},
            ),
            RoundedPasswordField(
              controller: (passwordController),
              onChanged: (value) {},
            ),

               FlatButton(
                padding: EdgeInsets.only(right: size.width*0.1),
                child: Align(
                  alignment: Alignment.centerRight,
                  child: Text(
                    "Forgot password?",
                    style: TextStyle(
                      fontWeight: FontWeight.normal,color: Colors.blue,fontSize: 15,
                    ),
                    textAlign: TextAlign.left,

                  ),
                ),

              ),
            RoundedButton(
              color: Color(0xff0062cc),
              text: "Sign in",
              textColor: Colors.white,
              press: () {
                signIn(emailController.text, passwordController.text);
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) {
                    return NavDrawer();
                  }),
                );
              },
            ),
            SizedBox(height: size.height * 0.01),
            OrDivider(),
            SizedBox(height: size.height * 0.01),
            Container(
              color: GoogleColor,
              width: size.width * 0.8,
              child: FlatButton.icon(
                onPressed: null,
                icon: SvgPicture.asset(
                  'assets/icons/google-plus.svg',
                  height: 20,
                  width: 20,
                ),
                label: Text(
                  'Continue with google',

                  style: TextStyle(
                      color: Colors.white,
                      fontFamily: 'Raleway', fontWeight: FontWeight.bold),
                ),
              ),
            ),
            SizedBox(height: size.height * 0.01),
            Container(
              color: FacebookColor,
              width: size.width * 0.8,
              child: FlatButton.icon(
                onPressed: null,
                icon: SvgPicture.asset(
                  'assets/icons/facebook.svg',
                  height: 20,
                  width: 20,
                ),
                label: Text(

                  'Continue with facebook',
                  style: TextStyle(
                    color: Colors.white,
                      fontFamily: 'Raleway', fontWeight: FontWeight.bold),
                ),
              ),
            ),
            SizedBox(height: size.height * 0.03),
            AlreadyHaveAnAccountCheck(
              press: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) {
                      return SignUpScreen1();
                    },
                  ),
                );
              },
            ),
          ],
        ),
      ),
    );
  }
}

//Controllers for the username and the password
final TextEditingController emailController = new TextEditingController();
final TextEditingController passwordController = new TextEditingController();

//Code for signing the user in
Future signIn(String email, pass) async {
  SharedPreferences sharedPreferences;
  sharedPreferences = await SharedPreferences.getInstance();
  //Left hand side of the map should be the same as the fields in the server
  Map data = {'userName': email, 'password': pass};
  var jsonResponse;

  var response = await http
      .post("http://192.168.75.190/ClassRoom/AuthServer/api", body: data);
  print(data);
  if (response.statusCode == 200) {
    jsonResponse = json.decode(response.body);
    print(jsonResponse);
    var jdata = jsonResponse["data"];
    print(jdata);
    var res = jdata.map<Datac>((json) => Datac.fromJson(jdata)).toString();
    print(res);
    //setCredentials(jsonResponse);
    // sharedPreferences.setString("email",jsonResponse["data"]);
    // var check=sharedPreferences.get("email");
    // print(check);
    //
    // if(jsonResponse != null) {
    //   // setState(() {
    //   //   _isLoading = false;
    //   // });
    //
    //   // Navigator.of(context).pushAndRemoveUntil(MaterialPageRoute(builder: (BuildContext context) => MainPage()), (Route<dynamic> route) => false);
    // }
  }
  // else {
  //   // setState(() {
  //   //   _isLoading = false;
  //   // });
  //
  // }
}
