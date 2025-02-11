import 'package:english_words/english_words.dart';
import 'package:flutter/material.dart';

class BigCard extends StatelessWidget {
  final WordPair pair;
  late final String text;
  final style =
      TextStyle(fontSize: 30, color: Colors.white, fontWeight: FontWeight.bold);

  BigCard({
    super.key,
    required this.pair,
  }) {
    text = pair.asPascalCase;
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(4),
        color: Colors.blue,
        border: Border.all(color: Colors.white),
      ),
      padding: const EdgeInsets.all(14),
      margin: const EdgeInsets.all(8.0),
      child: Text(
        text,
        style: style,
        semanticsLabel: '${pair.first} ${pair.second}',
      ),
    );
  }
}
