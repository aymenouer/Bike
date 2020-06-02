package com.google.zxing.aztec;


/**
 *  This implementation can detect and decode Aztec codes in an image.
 * 
 *  @author David Olivier
 */
public final class AztecReader implements com.google.zxing.Reader {

	public AztecReader() {
	}

	/**
	 *  Locates and decodes a Data Matrix code in an image.
	 * 
	 *  @return a String representing the content encoded by the Data Matrix code
	 *  @throws NotFoundException if a Data Matrix code cannot be found
	 *  @throws FormatException if a Data Matrix code cannot be decoded
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
