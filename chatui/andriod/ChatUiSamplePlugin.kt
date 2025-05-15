package com.example.chat_ui_sample

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.annotation.NonNull
import com.azure.android.communication.common.CommunicationTokenCredential
import com.azure.android.communication.ui.chat.ChatAdapter
import com.azure.android.communication.ui.chat.ChatAdapterBuilder

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import kotlin.contracts.contract

/** ChatUiSamplePlugin */
class ChatUiSamplePlugin: FlutterPlugin, MethodCallHandler, Pigeon.AzureCommunicationUIHostApi, ActivityAware {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private var activity: Activity? = null
  private lateinit var channel : MethodChannel

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
      Pigeon.AzureCommunicationUIHostApi.setup(flutterPluginBinding.binaryMessenger, this)
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "chat_ui_sample")
    channel.setMethodCallHandler(this)
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    } else {
      result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }


  override fun startChatComposite(options: Pigeon.Input) {
    if (activity != null && options != null) {
      Toast.makeText(activity!!, options.threadId ?: "Nothing Receiver/Null", Toast.LENGTH_LONG)
        .show()

      val communicationTokenCredential =
        CommunicationTokenCredential(options.acsToken)

      val chatAdapter = ChatAdapterBuilder()
        .endpointUrl(options.endpointUrl)
        .communicationTokenCredential(communicationTokenCredential)
        .identity(options.identity)
        .displayName(options.displayName)
        .build()

      // Connect to ACS service, starts realtime notifications
      chatAdapter.connect(activity, options.threadId).get()


      val activityLauncherClass =
        Class.forName("com.azure.android.communication.ui.chat.presentation.ChatCompositeActivity")
      val constructor = activityLauncherClass.getDeclaredConstructor(Context::class.java)
      constructor.isAccessible = true
      val instance = constructor.newInstance(this)
      val launchMethod =
        activityLauncherClass.getDeclaredMethod("launch", ChatAdapter::class.java)
      launchMethod.isAccessible = true
      launchMethod.invoke(instance, chatAdapter)

    }
  }

  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
    activity = binding.activity;
  }

  override fun onDetachedFromActivityForConfigChanges() {
    activity = null
  }

  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    activity = binding.activity
  }

  override fun onDetachedFromActivity() {
    activity = null
  }
}
