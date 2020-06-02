package com.google.zxing.multi.qrcode;


/**
 *  This implementation can detect and decode multiple QR Codes in an image.
 * 
 *  @author Sean Owen
 *  @author Hannes Erven
 */
public final class QRCodeMultiReader extends com.google.zxing.qrcode.QRCodeReader implements com.google.zxing.multi.MultipleBarcodeReader {

	public QRCodeMultiReader() {
	}

	@java.lang.Override
	public com.google.zxing.Result[] decodeMultiple(com.google.zxing.BinaryBitmap image) {
	}

	@java.lang.Override
	public com.google.zxing.Result[] decodeMultiple(com.google.zxing.BinaryBitmap image, java.util.Map hints) {
	}
}
