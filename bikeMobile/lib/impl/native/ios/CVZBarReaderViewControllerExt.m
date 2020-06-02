#import "CVZBarReaderViewControllerExt.h"
#if !TARGET_IPHONE_SIMULATOR

@implementation CVZBarReaderViewControllerExt
- (void) loadView
{
#ifdef CN1_USE_ARC
    self.view = [[UIView alloc] initWithFrame: CGRectMake(0, 0, 320, 480)];
#else
    self.view = [[[UIView alloc] initWithFrame: CGRectMake(0, 0, 320, 480)] autorelease];
#endif
}
@end
#endif
