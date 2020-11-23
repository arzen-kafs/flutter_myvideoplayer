//Modal class
import 'dart:convert';

//Method nfrom json that converts json to string
Datac dataFromJson(String str) => Datac.fromJson(json.decode(str));
class Datac {

  //Constructor of the modal class
  Datac({
    this.session_id,
    this.email,
    this.firstName,
    this.lastName,
    this.userType
  });

  String session_id;
  DateTime email;
  String firstName;
  String lastName;
  String userType;
  //Factory method for mapping yhe values of the json to string
  factory Datac.fromJson(Map<String, dynamic> json) => Datac(
    session_id: json["session_id"],
    email: json["email"],
    firstName: json["firstName"],
    lastName: json["lastName"],
    userType: json["userType"],
  );



}