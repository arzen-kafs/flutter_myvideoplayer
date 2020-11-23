import 'package:firebase_messaging/firebase_messaging.dart';
import 'package:flutter/cupertino.dart';


class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String textValue = 'Hello World !';
  FirebaseMessaging firebaseMessaging = new FirebaseMessaging();

  @override
  void initState() {
    super.initState();


    firebaseMessaging.configure(
      // ignore: missing_return
      onLaunch: (Map<String, dynamic> msg) {
        print(" onLaunch called ${(msg)}");
      },
      // ignore: missing_return
      onResume: (Map<String, dynamic> msg) {
        print(" onResume called ${(msg)}");
      },
      // ignore: missing_return
      onMessage: (Map<String, dynamic> msg) {
        print(" onMessage called ${(msg)}");
      },
    );
    firebaseMessaging.requestNotificationPermissions(
        const IosNotificationSettings(sound: true, alert: true, badge: true));
    firebaseMessaging.onIosSettingsRegistered
        .listen((IosNotificationSettings setting) {
      print('IOS Setting Registed');
    });
    firebaseMessaging.getToken().then((token) {
      ;
    });
  }

  @override
  Widget build(BuildContext context) {
    return (
        null
    );
  }

}