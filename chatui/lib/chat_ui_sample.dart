
import 'chat_ui_sample_platform_interface.dart';

class ChatUiSample {
  Future<String?> getPlatformVersion() {
    return ChatUiSamplePlatform.instance.getPlatformVersion();
  }
}
