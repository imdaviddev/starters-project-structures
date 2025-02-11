import 'package:english_words/english_words.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class MyAppState extends ChangeNotifier {
  var _current = WordPair.random();

  WordPair get current => _current;

  void next() {
    _current = WordPair.random();
    notifyListeners();
  }
}

class GlobalProvider extends StatelessWidget {
  final StatelessWidget child;

  const GlobalProvider({super.key, required this.child});

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (context) => MyAppState(),
      child: MaterialApp(
        title: 'GymSchedule',
        theme: ThemeData(
          useMaterial3: true,
          colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepOrange),
        ),
        home: child,
      ),
    );
  }
}
