#import "com_codename1_ext_codescan_NativeCodeScannerImpl.h"
#import "ScanCodeImplExt.h"
#import "CodenameOne_GLViewController.h"

@implementation com_codename1_ext_codescan_NativeCodeScannerImpl

-(void)scanQRCode{
    [self scanBarCode];
}

-(void)scanBarCode{
#if !TARGET_IPHONE_SIMULATOR
    dispatch_async(dispatch_get_main_queue(), ^{
        POOL_BEGIN();
        CVZBarReaderViewControllerExt *reader = [CVZBarReaderViewControllerExt new];
        ScanCodeImplExt* scanCall = [[ScanCodeImplExt alloc] init];
        reader.readerDelegate = scanCall;
        reader.supportedOrientationsMask = ZBarOrientationMaskAll;
        
        //ZBAR_CONFIGURATIONS
        
        ZBarImageScanner *scanner = reader.scanner;
        // TODO: (optional) additional reader configuration here
        
        // EXAMPLE: disable rarely used I2/5 to improve performance
        [scanner setSymbology: ZBAR_I25
                       config: ZBAR_CFG_ENABLE
                           to: 0];
        
        // present and release the controller
        [[CodenameOne_GLViewController instance] presentModalViewController:reader animated:NO];
#ifndef CN1_USE_ARC
        [reader release];
#endif
        POOL_END();
    });
#endif
}

-(BOOL)isSupported{
    return YES;
}

@end
