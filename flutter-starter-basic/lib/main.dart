import 'package:flutter/material.dart';
import 'package:namer_app/providers/index.dart';
import 'package:namer_app/views/home_view.dart';

void main() {
  runApp(MyApp());
}

class MaterialAppWrapper extends StatelessWidget {
  const MaterialAppWrapper({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: MyHomePage(),
    );
  }
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return GlobalProvider(
      child: MaterialAppWrapper(),
    );
  }
}
