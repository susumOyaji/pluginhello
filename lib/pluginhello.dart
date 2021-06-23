import 'dart:async';

import 'package:flutter/services.dart';

//プロジェクトを作る
//プロジェクトを作成する時に、--template=pluginを付与することで、plugin用のプロジェクトが作られます。
//flutter create --org com.yamadaryo --template=plugin -i swift -a kotlin hogehoge
//他のパラメータについての解説を載せておきます。
//--org: organaizationを指定します。通常、Reverse domain nameで指定します
//-i swift -a kotlin: デフォルトでプロジェクトのネイティブコードは、Objective-CとJavaで生成されますが、SwiftとKotlinを使いたい場合は、このオプションで指定します。

//チャネルのクライアント側とホスト側は、チャネルコンストラクターで渡されたチャネル名を介して接続されます。
//1つのアプリで使用されるすべてのチャンネル名は一意である必要があります。
//チャネル名の前に一意の「ドメインプレフィックス」又は"アプリパッケージ名/チャンネル名"を付けます
class Pluginhello {
  static const MethodChannel _channel = const MethodChannel('pluginhello');
  //static const platform = const MethodChannel('samples.flutter.dev/battery');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<String> get batteryLevel async {
    final String level = await _channel.invokeMethod('ggetBatteryLevel');
    return level;
  }

  static Future<int> onKeyDown(int key) async {
    final int numNotesOn = await _channel.invokeMethod('onKeyDown', [key]);
    return numNotesOn;
  }

  static Future<int> onKeyUp(int key) async {
    final int numNotesOn = await _channel.invokeMethod('onKeyUp', [key]);
    return numNotesOn;
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
