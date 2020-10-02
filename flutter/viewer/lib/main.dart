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
              title: Text("Remote Images Viewer"),
            ),
            body: Center(child: Main())));
  }
}

class Main extends StatelessWidget {
  final textController = TextEditingController();

  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        Text("Insert some image URLs, one per line."),
        TextField(
          decoration: InputDecoration(contentPadding: EdgeInsets.all(20.0)),
          keyboardType: TextInputType.multiline,
          maxLines: null,
          controller: textController,
        ),
        ImageDialogWrapper(() => textController.text.split('\n'))
      ],
    );
  }
}

class ImageDialogWrapper extends StatelessWidget {
  final Function getUrls; // should return a list of strings

  ImageDialogWrapper(this.getUrls);

  Widget build(BuildContext context) {
    return FlatButton(
      color: Colors.greenAccent,
      child: Text("Show"),
      onPressed: () => showDialog(
        context: context,
        builder: (context) => ImageDialog(getUrls()),
      ),
    );
  }
}

class ImageDialog extends StatelessWidget {
  final List<String> urls;

  ImageDialog(this.urls);

  Widget build(BuildContext context) {
    List<Image> images = urls.map((e) => Image.network(e)).toList();
    return ListView(
      children: [
        ...images,
        FlatButton(
          color: Colors.greenAccent,
          child: Text("Ok"),
          onPressed: () => Navigator.of(context).pop(),
        )
      ],
    );
  }
}
