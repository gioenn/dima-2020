import 'package:flutter/material.dart';
import 'calculator.dart';

class Power extends StatefulWidget {
  final Function update;
  Power(this.update);
  PowerState createState() => PowerState();
}

class PowerState extends State<Power> {
  final baseController = TextEditingController();
  final expController = TextEditingController();

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
                    controller: baseController,
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
                    controller: expController,
                    style: TextStyle(
                        fontSize: 25,
                        fontWeight: FontWeight.w500,
                        color: Colors.blue),
                  ))
            ]),
        FlatButton(
            color: Colors.greenAccent,
            onPressed: () => widget.update(power(
                int.parse(baseController.text), int.parse(expController.text))),
            child: Text("POWER"))
      ],
    );
  }
}
