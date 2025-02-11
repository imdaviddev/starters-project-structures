import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:namer_app/components/big_card.dart';
import 'package:namer_app/providers/global_provider.dart';

class MyHomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    var appState = context.watch<MyAppState>();
    var pair = appState.current;

    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            BigCard(pair: pair),
            ElevatedButton(
              onPressed: () {
                appState.next();
              },
              style: ButtonStyle(
                backgroundColor: WidgetStateProperty.all<Color>(Colors.white),
                side: WidgetStateProperty.all<BorderSide>(
                  BorderSide(color: Colors.black),
                ),
              ),
              child: Text(
                'Next',
                style: TextStyle(color: Colors.black),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
