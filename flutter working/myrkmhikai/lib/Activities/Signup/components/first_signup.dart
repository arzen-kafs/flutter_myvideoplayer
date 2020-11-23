import 'dart:convert';

import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';

import 'package:flutter_svg/svg.dart';
import 'package:myrkmhikai/Activities/Login/login_screen.dart';
import 'package:myrkmhikai/Global/Components/already_have_an_account_acheck.dart';
import 'package:myrkmhikai/Global/Components/phone_number.dart';
import 'package:myrkmhikai/Global/Components/rounded_button.dart';
import 'package:myrkmhikai/Global/Components/rounded_input_field.dart';
import 'package:myrkmhikai/Global/Components/rounded_password_field.dart';
import 'package:myrkmhikai/Resources/colors.dart';

import '../Signup_screen2.dart';
import '../signup_screen.dart';
import 'background.dart';
import 'or_divider.dart';
import 'social_icon.dart';

class FirstpageSignup extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    return Background(
      child: SingleChildScrollView(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            SizedBox(height: size.height * 0.02),
            Text(
              "Sign Up Now",
              style:  TextStyle(fontWeight: FontWeight.bold,fontFamily: 'Raleway',fontSize: 40),
            ),
            SizedBox(height: size.height * 0.02),
            SvgPicture.asset(
              "assets/icons/signup.svg",
              height: size.height * 0.35,
            ),

            RoundedInputField(
              icon: Icons.person,
              controller: (firstName),
              hintText: "First Name",
              onChanged: (value) {},
            ),
            RoundedInputField(
              controller: (lastName),
              icon: Icons.person,
              hintText: "Last Name",
              onChanged: (value) {},
            ),
            RoundedInputField(
              icon: Icons.email_outlined,
              controller: (emailController),
              hintText: "Email Adress",
              onChanged: (value) {},
            ),
            PhoneNumber(
              controller: (phoneController),
              hintText: "Phone Number",
              onChanged: (value) {},
            ),
            RoundedPasswordField(
              controller: (passwordController),
              onChanged: (value) {},
              hintText: "Password",
            ),
            RoundedPasswordField(
              controller: (passwordController),
              onChanged: (value) {},
              hintText: "Confirm Password",
            ),
            RoundedButton(
              color: Color(0xff0062cc),
              text: "Create your account",
              press: () {
                signUp(emailController.text, passwordController.text,
                    firstName.text, lastName.text, phoneController.text);
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) {
                      return SignUpScreen2();
                    },
                  ),
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
            )
          ],
        ),
      ),
    );
  }
}

//Controllers for the username and the password
final TextEditingController emailController = new TextEditingController();
final TextEditingController passwordController = new TextEditingController();
final TextEditingController firstName = new TextEditingController();
final TextEditingController phoneController = new TextEditingController();
final TextEditingController lastName = new TextEditingController();
//Code for signing the user in
Future signUp(String email, pass, fName, lName, phone) async {
  //SharedPreferences sharedPreferences ;
  //sharedPreferences=await SharedPreferences.getInstance();
  //Left hand side of the map should be the same as the fields in the server
  Map data = {
    'email': email,
    'password': pass,
    'firstName': fName,
    'lastName': lName,
    'phone': phone
  };

  var jsonResponse;

  var response = await http
      .post("http://192.168.75.190/ClassRoom/authentication/api", body: data);
  print(data);
  if (response.statusCode == 200) {
    jsonResponse = json.decode(response.body);
    print(jsonResponse);
    // var jdata =jsonResponse["data"];
    // print(jdata);
    // var res=jdata.map<Datac>((json) => Datac.fromJson(jdata)).toString();
    // print(res);
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
