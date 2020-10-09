import 'package:flutter/material.dart';
import 'package:calculator/calculator.dart';

class Factorial extends StatefulWidget {
  final Function update;
  Factorial(this.update);
  FactorialState createState() => FactorialState();
}

class FactorialState extends State<Factorial> {
  final myController = TextEditingController();
  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        TextField(
          textAlign: TextAlign.center,
          controller: myController,
          style: TextStyle(
              fontSize: 25, fontWeight: FontWeight.w500, color: Colors.blue),
        ),
        FlatButton(
            color: Colors.greenAccent,
            onPressed: () =>
                widget.update(factorial(int.parse(myController.text))),
            child: Text("FACTORIAL"))
      ],
    );
  }
}
