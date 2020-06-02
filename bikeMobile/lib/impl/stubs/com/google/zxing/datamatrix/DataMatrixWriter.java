package com.google.zxing.datamatrix;


/**
 *  This object renders a Data Matrix code as a BitMatrix 2D array of greyscale values.
 * 
 *  @author dswitkin@google.com (Daniel Switkin)
 *  @author Guillaume Le Biller Added to zxing lib.
 */
public final class DataMatrixWriter implements com.google.zxing.Writer {

	public DataMatrixWriter() {
	}

	@java.lang.Override
	public com.google.zxing.common.BitMatrix encode(String contents, com.google.zxing.BarcodeFormat format, int width, int height) {
	}

	@java.lang.Override
	public com.google.zxing.common.BitMatrix encode(String contents, com.google.zxing.BarcodeFormat format, int width, int height, java.util.Map hints) {
	}
}
