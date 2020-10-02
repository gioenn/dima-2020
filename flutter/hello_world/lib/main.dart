import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        home: Scaffold(
            appBar: AppBar(title: Text("HelloWorld App")),
            body: Center(
                child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                HelloWorld(),
                HelloWorldGenerator(9),
                RepeatedHelloWorld(10, color: Colors.green)
              ],
            ))));
  }
}

class HelloWorld extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Text("Hello World!!!",
        style: TextStyle(fontSize: 20, fontWeight: FontWeight.w500));
  }
}

class RepeatedHelloWorld extends StatelessWidget {
  final int times;
  final Color color;

  RepeatedHelloWorld(this.times, {this.color = Colors.red});

  @override
  Widget build(BuildContext context) {
    return Text("Hello World $times ${times == 1 ? "time" : "times"}",
        style:
            TextStyle(fontSize: 20, fontWeight: FontWeight.w500, color: color));
  }
}

class HelloWorldGenerator extends StatelessWidget {
  final int count;

  HelloWorldGenerator(this.count);

  @override
  Widget build(BuildContext context) {
    List<Widget> children = [];
    for (int i = 0; i < count; i++) {
      children.add(RepeatedHelloWorld(i + 1,
          color:
              Color.fromRGBO(16 * i % 255, 32 * i % 255, 64 * i % 255, 1.0)));
    }
    return Column(children: children);
  }
}
