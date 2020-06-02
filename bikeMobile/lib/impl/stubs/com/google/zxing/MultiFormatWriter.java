package com.google.zxing;


/**
 *  This is a factory class which finds the appropriate Writer subclass for the BarcodeFormat
 *  requested and encodes the barcode with the supplied contents.
 * 
 *  @author dswitkin@google.com (Daniel Switkin)
 */
public final class MultiFormatWriter implements Writer {

	public MultiFormatWriter() {
	}

	@java.lang.Override
	public common.BitMatrix encode(String contents, BarcodeFormat format, int width, int height) {
	}

	@java.lang.Override
	public common.BitMatrix encode(String contents, BarcodeFormat format, int width, int height, java.util.Map hints) {
	}
}
