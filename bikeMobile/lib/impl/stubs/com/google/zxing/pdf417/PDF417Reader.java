package com.google.zxing.pdf417;


/**
 *  This implementation can detect and decode PDF417 codes in an image.
 * 
 *  @author Guenther Grau
 */
public final class PDF417Reader implements com.google.zxing.Reader, com.google.zxing.multi.MultipleBarcodeReader {

	public PDF417Reader() {
	}

	/**
	 *  Locates and decodes a PDF417 code in an image.
	 * 
	 *  @return a String representing the content encoded by the PDF417 code
	 *  @throws NotFoundException if a PDF417 code cannot be found,
	 *  @throws FormatException if a PDF417 cannot be decoded
	 */
	@java.lang.Override
	public com.google.zxing.Result decode(com.google.zxing.BinaryBitmap image) {
	}

	@java.lang.Override
	public com.google.zxing.Result decode(com.google.zxing.BinaryBitmap image, java.util.Map hints) {
	}

	@java.lang.Override
	public com.google.zxing.Result[] decodeMultiple(com.google.zxing.BinaryBitmap image) {
	}

	@java.lang.Override
	public com.google.zxing.Result[] decodeMultiple(com.google.zxing.BinaryBitmap image, java.util.Map hints) {
	}

	@java.lang.Override
	public void reset() {
	}
}
