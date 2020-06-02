#import <Foundation/Foundation.h>

@interface org_littlemonkey_qrscanner_NativeScannerImpl : NSObject {
}

-(void)scanQRCode;
-(void)scanBarCode;
-(BOOL)isSupported;
@end
