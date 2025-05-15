import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'chat_ui_sample_method_channel.dart';

abstract class ChatUiSamplePlatform extends PlatformInterface {
  /// Constructs a ChatUiSamplePlatform.
  ChatUiSamplePlatform() : super(token: _token);

  static final Object _token = Object();

  static ChatUiSamplePlatform _instance = MethodChannelChatUiSample();

  /// The default instance of [ChatUiSamplePlatform] to use.
  ///
  /// Defaults to [MethodChannelChatUiSample].
  static ChatUiSamplePlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [ChatUiSamplePlatform] when
  /// they register themselves.
  static set instance(ChatUiSamplePlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
