import 'dart:async';

import 'package:flutter/services.dart';

class Pluginhello {
  static const MethodChannel _channel = const MethodChannel('pluginhello');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
}

class PlatformOriginDialog {
  static const MethodChannel _channel =
      const MethodChannel('platform_origin_dialog');

  static Future<String> showDialog(String title, String message) async {
    final String result =
        await _channel.invokeMethod('show_dialog', <String, dynamic>{
      'title': title,
      'message': message,
    });
    return result;
  }
}
