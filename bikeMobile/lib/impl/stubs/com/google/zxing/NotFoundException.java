package com.google.zxing;


/**
 *  Thrown when a barcode was not found in the image. It might have been
 *  partially detected but could not be confirmed.
 * 
 *  @author Sean Owen
 */
public final class NotFoundException extends ReaderException {

	public static NotFoundException getNotFoundInstance() {
	}
}
