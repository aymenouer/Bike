package com.google.zxing.qrcode;


/**
 *  This implementation can detect and decode QR Codes in an image.
 * 
 *  @author Sean Owen
 */
public class QRCodeReader implements com.google.zxing.Reader {

	public QRCodeReader() {
	}

	protected final decoder.Decoder getDecoder() {
	}

	/**
	 *  Locates and decodes a QR code in an image.
	 * 
	 *  @return a String representing the content encoded by the QR code
	 *  @throws NotFoundException if a QR code cannot be found
	 *  @throws FormatException if a QR code cannot be decoded
	 *  @throws ChecksumException if error correction fails
	 */
	@java.lang.Override
	public com.google.zxing.Result decode(com.google.zxing.BinaryBitmap image) {
	}

	@java.lang.Override
	public final com.google.zxing.Result decode(com.google.zxing.BinaryBitmap image, java.util.Map hints) {
	}

	@java.lang.Override
	public void reset() {
	}
}
