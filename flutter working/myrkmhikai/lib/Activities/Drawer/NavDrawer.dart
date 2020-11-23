import 'package:flutter/material.dart';
import 'package:myrkmhikai/Activities/ClassRoom/DashBoard.dart';
import 'package:myrkmhikai/Activities/Login/login_screen.dart';

class NavDrawer extends StatefulWidget {
  @override
  _NavDrawerState createState() => _NavDrawerState();
}

class _NavDrawerState extends State<NavDrawer> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Nav Drawer")),
      drawer: Drawer(
        child: ListView(
          children: <Widget>[
            UserAccountsDrawerHeader(
              accountName: Text("Bependra Kafley"),
              accountEmail: Text("kprathap23@gmail.com"),
              decoration: BoxDecoration(
                image: DecorationImage(
                  image: ExactAssetImage('assets/pictures/'),
                  fit: BoxFit.cover,
                ),
              ),
              currentAccountPicture: CircleAvatar(
                  backgroundImage: NetworkImage(
                      "https://randomuser.me/api/portraits/men/46.jpg")),
            ),
            ListTile(
                leading: Icon(Icons.library_music),
                title: Text("Music"),
                // onTap: () {
                //   Navigator.pop(context);
                // }
              ),
            ListTile(
                leading: Icon(Icons.movie),
                title: Text("Movies"),
                // onTap: () {
                //   Navigator.pop(context);
                // }
                ),
            ListTile(
                leading: Icon(Icons.shopping_cart),
                title: Text("Shopping"),
                // onTap: () {
                //   Navigator.pop(context);
                // }
                ),
            ListTile(
                leading: Icon(Icons.apps),
                title: Text("Apps"),
                // onTap: () {
                //   Navigator.pop(context);
                // }
                ),
            ListTile(
                leading: Icon(Icons.dashboard),
                title: Text("Docs"),
                // onTap: () {
                //   Navigator.pop(context);
                // }
                ),
            ListTile(
                leading: Icon(Icons.settings),
                title: Text("Settings"),
                // onTap: () {
                //   Navigator.pop(context);
                // }
                ),
            Padding(
              padding: EdgeInsets.symmetric(horizontal: 10.0),
              child: Container(
                height: 1.0,
                width: 130.0,
                color: Colors.black,
              ),
            ),
            ListTile(
                leading: Icon(Icons.info),
                title: Text("About"),
                // onTap: () {
                //   Navigator.pop(context);
                // }
                ),
            ListTile(
                leading: Icon(Icons.power_settings_new),
                title: Text("Logout"),
                // onTap: () {
                //   Navigator.pop(context);
                // }
                ),
          ],
        ),
      ),
      body:DashBoard()
    );
  }
}