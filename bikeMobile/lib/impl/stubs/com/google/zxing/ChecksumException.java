package com.google.zxing;


/**
 *  Thrown when a barcode was successfully detected and decoded, but
 *  was not returned because its checksum feature failed.
 * 
 *  @author Sean Owen
 */
public final class ChecksumException extends ReaderException {

	public static ChecksumException getChecksumInstance() {
	}

	public static ChecksumException getChecksumInstance(Throwable cause) {
	}
}
