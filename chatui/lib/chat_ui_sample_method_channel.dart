import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'chat_ui_sample_platform_interface.dart';

/// An implementation of [ChatUiSamplePlatform] that uses method channels.
class MethodChannelChatUiSample extends ChatUiSamplePlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('chat_ui_sample');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
