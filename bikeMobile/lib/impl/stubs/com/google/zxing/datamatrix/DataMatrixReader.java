package com.google.zxing.datamatrix;


/**
 *  This implementation can detect and decode Data Matrix codes in an image.
 * 
 *  @author bbrown@google.com (Brian Brown)
 */
public final class DataMatrixReader implements com.google.zxing.Reader {

	public DataMatrixReader() {
	}

	/**
	 *  Locates and decodes a Data Matrix code in an image.
	 * 
	 *  @return a String representing the content encoded by the Data Matrix code
	 *  @throws NotFoundException if a Data Matrix code cannot be found
	 *  @throws FormatException if a Data Matrix code cannot be decoded
	 *  @throws ChecksumException if error correction fails
	 */
	@java.lang.Override
	public com.google.zxing.Result decode(com.google.zxing.BinaryBitmap image) {
	}

	@java.lang.Override
	public com.google.zxing.Result decode(com.google.zxing.BinaryBitmap image, java.util.Map hints) {
	}

	@java.lang.Override
	public void reset() {
	}
}
