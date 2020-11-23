import 'dart:convert';
import 'package:flutter/material.dart';

import 'package:http/http.dart' as http;

import 'package:flutter_svg/svg.dart';
import 'package:myrkmhikai/Global/Components/Gender.dart';
import 'package:myrkmhikai/Global/Components/phone_number.dart';
import 'package:myrkmhikai/Global/Components/rounded_button.dart';
import 'package:myrkmhikai/Global/Components/rounded_input_field.dart';
import 'package:myrkmhikai/Global/Components/stream_taken.dart';

import '../Signupscreen3.dart';
import 'background.dart';
class VitalInfoPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    return Background(
      child: SingleChildScrollView(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              "Next step",
              style: TextStyle(fontWeight: FontWeight.bold),
            ),
            SizedBox(height: size.height * 0.03),
            SvgPicture.asset(
              "assets/icons/signup.svg",
              height: size.height * 0.35,
            ),
            Gender(

            ),
            RoundedInputField(
              controller: (lastName),
              hintText: "class",
              icon: Icons.class_,
              onChanged: (value){},
            ),
            StreamTaken(

            ),
            RoundedInputField(
              controller: (emailController),
              icon: Icons.school,
              hintText: "School",
              onChanged: (value) {},
            ),

            RoundedInputField(
              controller: (firstName),
              hintText: "Adress",
              icon: Icons.location_on,
              onChanged: (value){},
            ),

            RoundedInputField(
              controller: (lastName),
              icon:Icons.location_city,
              hintText: "city",
              onChanged: (value){},
            ),

            PhoneNumber(
              controller: (phoneController),
              icon:Icons.confirmation_num_outlined,
              hintText: "Pincode",
              onChanged: (value) {},

            ),

            RoundedButton(
              text: "Next Step",
              press: (
                  ) {
                signUp(emailController.text, passwordController.text, firstName.text,lastName.text,phoneController.text);
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) {
                      return SignUpScreen3();
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
final TextEditingController firstName = new TextEditingController();
final TextEditingController phoneController = new TextEditingController();
final TextEditingController lastName = new TextEditingController();
//Code for signing the user in
Future signUp(String email, pass,fName,lName,phone) async {
  //SharedPreferences sharedPreferences ;
  //sharedPreferences=await SharedPreferences.getInstance();
  //Left hand side of the map should be the same as the fields in the server
  Map data = {
    'email': email,
    'password': pass,
    'firstName': fName,
    'lastName':lName,
    'phone':phone
  };

  var jsonResponse;

  var response = await http.post("http://192.168.75.190/ClassRoom/authentication/api", body: data);
  print(data);
  if(response.statusCode == 200) {
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
