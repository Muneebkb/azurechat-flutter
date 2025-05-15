#import "ChatUiSamplePlugin.h"
#if __has_include(<chat_ui_sample/chat_ui_sample-Swift.h>)
#import <chat_ui_sample/chat_ui_sample-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "chat_ui_sample-Swift.h"
#endif

@implementation ChatUiSamplePlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftChatUiSamplePlugin registerWithRegistrar:registrar];
}
@end
