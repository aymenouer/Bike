package com.google.zxing.common;


/**
 *  This Binarizer implementation uses the old ZXing global histogram approach. It is suitable
 *  for low-end mobile devices which don't have enough CPU or memory to use a local thresholding
 *  algorithm. However, because it picks a global black point, it cannot handle difficult shadows
 *  and gradients.
 * 
 *  Faster mobile devices and all desktop applications should probably use HybridBinarizer instead.
 * 
 *  @author dswitkin@google.com (Daniel Switkin)
 *  @author Sean Owen
 */
public class GlobalHistogramBinarizer extends com.google.zxing.Binarizer {

	public GlobalHistogramBinarizer(com.google.zxing.LuminanceSource source) {
	}

	@java.lang.Override
	public BitArray getBlackRow(int y, BitArray row) {
	}

	@java.lang.Override
	public BitMatrix getBlackMatrix() {
	}

	@java.lang.Override
	public com.google.zxing.Binarizer createBinarizer(com.google.zxing.LuminanceSource source) {
	}
}
