package com.google.zxing;


/**
 *  Thrown when a barcode was successfully detected, but some aspect of
 *  the content did not conform to the barcode's format rules. This could have
 *  been due to a mis-detection.
 * 
 *  @author Sean Owen
 */
public final class FormatException extends ReaderException {

	public static FormatException getFormatInstance() {
	}

	public static FormatException getFormatInstance(Throwable cause) {
	}
}
