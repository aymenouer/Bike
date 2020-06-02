//
//  ScanCodeImplExt.m
//  KitchenSink
//
//  Created by Shai Almog on 05/11/12.
//  Adapted by Steve Hannah for cn1lib on 23/12/15
//
//

#import "ScanCodeImplExt.h"
#import "CodenameOne_GLViewController.h"
#include "com_codename1_impl_ios_IOSImplementation.h"
#import "com_codename1_ext_codescan_CodeScanner.h"
#include "xmlvm.h"


@implementation ScanCodeImplExt
#if !TARGET_IPHONE_SIMULATOR
- (void) imagePickerController: (UIImagePickerController*) reader didFinishPickingMediaWithInfo: (NSDictionary*) info
{
        // ADD: get the decode results
        id<NSFastEnumeration> results = [info objectForKey: ZBarReaderControllerResults];
        ZBarSymbol *symbol = nil;
        for(symbol in results)
            // EXAMPLE: just grab the first barcode
            break;
        
        // EXAMPLE: do something useful with the barcode data
        //resultText.text = symbol.data;
        
        // EXAMPLE: do something useful with the barcode image
        //resultImage.image = [info objectForKey: UIImagePickerControllerOriginalImage];
        
        // ADD: dismiss the controller (NB dismiss from the *reader*!)
        [reader dismissModalViewControllerAnimated: YES];
    
        com_codename1_ext_codescan_CodeScanner_scanCompletedCallback___java_lang_String_java_lang_String_byte_1ARRAY(CN1_THREAD_GET_STATE_PASS_ARG fromNSString(CN1_THREAD_GET_STATE_PASS_ARG symbol.data), fromNSString(CN1_THREAD_GET_STATE_PASS_ARG symbol.typeName), JAVA_NULL);
        //com_codename1_impl_ios_IOSImplementation_scanCompleted___java_lang_String_java_lang_String(CN1_THREAD_GET_STATE_PASS_ARG fromNSString(CN1_THREAD_GET_STATE_PASS_ARG symbol.data), fromNSString(CN1_THREAD_GET_STATE_PASS_ARG symbol.typeName));
}
#endif

/*- (void)zxingController:(ZXingWidgetController*)controller didScanResult:(NSString *)result {
    com_codename1_impl_ios_IOSImplementation_scanCompleted___java_lang_String_java_lang_String(fromNSString(result), nil);
    [[CodenameOne_GLViewController instance] dismissModalViewControllerAnimated:NO];
}

- (void)zxingControllerDidCancel:(ZXingWidgetController*)controller {
    com_codename1_impl_ios_IOSImplementation_scanCanceled__();
    [[CodenameOne_GLViewController instance] dismissModalViewControllerAnimated:YES];
}*/

@end
