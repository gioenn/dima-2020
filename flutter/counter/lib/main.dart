import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        home: Scaffold(
            appBar: AppBar(
              title: Text("Counter"),
            ),
            body: Center(child: Counter())));
  }
}

class Counter extends StatefulWidget {
  CounterState createState() => CounterState();
}

class CounterState extends State<Counter> {
  int counter = 0;

  void decrementCounter() {
    setState(() {
      if (counter > 0) counter--;
    });
  }

  void incrementCounter() {
    setState(() {
      if (counter < 100) counter++;
    });
  }

  void setCounter(double val) {
    setState(() {
      counter = val.toInt();
    });
  }

  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.all(20),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        mainAxisSize: MainAxisSize.max,
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Container(
              color: Colors.purple,
              child: Center(
                  child: Text("$counter",
                      style: TextStyle(
                          fontSize: 40,
                          fontWeight: FontWeight.w500,
                          color: Colors.white)))),
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              FlatButton(
                color: Colors.pinkAccent,
                child: Icon(Icons.remove),
                onPressed: decrementCounter,
              ),
              FlatButton(
                color: Colors.greenAccent,
                child: Icon(Icons.add),
                onPressed: incrementCounter,
              )
            ],
          ),
          Slider(
              value: counter.toDouble(),
              activeColor: Colors.blueAccent,
              inactiveColor: Colors.blueGrey,
              min: 0,
              max: 100,
              onChanged: setCounter)
        ],
      ),
    );
  }
}
