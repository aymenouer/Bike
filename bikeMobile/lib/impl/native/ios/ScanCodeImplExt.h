//
//  ScanCodeImplExt.h
//  KitchenSink
//
//  Created by Shai Almog on 05/11/12.
//
//

#import <Foundation/Foundation.h>
//#import "ZXingWidgetController.h"
#if !TARGET_IPHONE_SIMULATOR
#import "ZBarSDK.h"
#import "CVZBarReaderViewControllerExt.h"
#endif

@interface ScanCodeImplExt
#if !TARGET_IPHONE_SIMULATOR
: UIViewController<ZBarReaderDelegate>//, ZXingDelegate>
#else
: NSObject
#endif

@end
