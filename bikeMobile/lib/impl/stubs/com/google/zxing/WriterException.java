package com.google.zxing;


/**
 *  A base class which covers the range of exceptions which may occur when encoding a barcode using
 *  the Writer framework.
 * 
 *  @author dswitkin@google.com (Daniel Switkin)
 */
public final class WriterException extends Exception {

	public WriterException() {
	}

	public WriterException(String message) {
	}

	public WriterException(Throwable cause) {
	}
}
