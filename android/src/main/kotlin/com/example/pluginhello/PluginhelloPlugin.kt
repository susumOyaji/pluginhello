package com.example.pluginhello

import androidx.annotation.NonNull;
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar
import android.os.BatteryManager

/** PluginhelloPlugin */
public class PluginhelloPlugin: FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "pluginhello")
    channel.setMethodCallHandler(this);
  }

  // This static function is optional and equivalent to onAttachedToEngine. It supports the old
  // pre-Flutter-1.12 Android projects. You are encouraged to continue supporting
  // plugin registration via this function while apps migrate to use the new Android APIs
  // post-flutter-1.12 via https://flutter.dev/go/android-project-migration.
  //
  // It is encouraged to share logic between onAttachedToEngine and registerWith to keep
  // them functionally equivalent. Only one of onAttachedToEngine or registerWith will be called
  // depending on the user's project. onAttachedToEngine or registerWith must both be defined
  // in the same class.
  companion object {
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      val channel = MethodChannel(registrar.messenger(), "pluginhello")
      channel.setMethodCallHandler(PluginhelloPlugin())
    }
  }



  //メソッド名は method プロパティで取得できています。
  //これによって、同じチャネルを使って、 複数の種類の呼び出しが可能になります。
  //メソッド呼び出しの結果は onMethodCall に渡される MethodChannel.Result オブジェクトを使って返します。

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    
    
    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    } else {
      result.notImplemented()
    }
    
    /* 
    if (call.method == "ggetBatteryLevel") {
     
    
      result.success("GetBattry ${BatteryManager.EXTRA_SCALE}")
    } else {
      result.notImplemented()
    }
    */
     //val batteryPct: Float? = batteryStatus?.let { intent ->
     //   val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
     //   val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
     //   level * 100 / scale.toFloat()
    //}


    
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }
}
