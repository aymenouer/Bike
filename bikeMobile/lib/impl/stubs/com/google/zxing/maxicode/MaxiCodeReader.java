package com.google.zxing.maxicode;


/**
 *  This implementation can detect and decode a MaxiCode in an image.
 */
public final class MaxiCodeReader implements com.google.zxing.Reader {

	public MaxiCodeReader() {
	}

	/**
	 *  Locates and decodes a MaxiCode in an image.
	 * 
	 *  @return a String representing the content encoded by the MaxiCode
	 *  @throws NotFoundException if a MaxiCode cannot be found
	 *  @throws FormatException if a MaxiCode cannot be decoded
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
