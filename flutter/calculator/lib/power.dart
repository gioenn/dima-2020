import 'package:flutter/material.dart';

class Power extends StatefulWidget {
  PowerState createState() => PowerState();
}

class PowerState extends State<Power> {
  final myController1 = TextEditingController();
  final myController2 = TextEditingController();

  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        Row(
            crossAxisAlignment: CrossAxisAlignment.center,
            mainAxisSize: MainAxisSize.max,
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Container(
                  width: 100,
                  child: TextField(
                    textAlign: TextAlign.center,
                    controller: myController1,
                    style: TextStyle(
                        fontSize: 25,
                        fontWeight: FontWeight.w500,
                        color: Colors.blue),
                  )),
              Text("^"),
              Container(
                  width: 100,
                  child: TextField(
                    textAlign: TextAlign.center,
                    controller: myController2,
                    style: TextStyle(
                        fontSize: 25,
                        fontWeight: FontWeight.w500,
                        color: Colors.blue),
                  ))
            ]),
        FlatButton(
            color: Colors.greenAccent, onPressed: null, child: Text("POWER"))
      ],
    );
  }
}
