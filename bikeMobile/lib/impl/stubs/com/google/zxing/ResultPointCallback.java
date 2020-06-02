package com.google.zxing;


/**
 *  Callback which is invoked when a possible result point (significant
 *  point in the barcode image such as a corner) is found.
 * 
 *  @see DecodeHintType#NEED_RESULT_POINT_CALLBACK
 */
public interface ResultPointCallback {

	public void foundPossibleResultPoint(ResultPoint point);
}
