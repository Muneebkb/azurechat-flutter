flutter pub run pigeon \
  --input pigeons/chat-api-sample.dart \
  --dart_out lib/src/acs_pigeons.dart \
  --objc_header_out ios/Classes/pigeon.h \
  --objc_source_out ios/Classes/pigeon.m \
  --objc_prefix FLT \
  --java_out ./android/src/main/java/com/example/chat-sample-test/Pigeon.java \
  --java_package "com.example.chat_ui_sample"
