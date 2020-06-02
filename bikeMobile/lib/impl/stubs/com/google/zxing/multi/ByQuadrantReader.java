package com.google.zxing.multi;


/**
 *  This class attempts to decode a barcode from an image, not by scanning the whole image,
 *  but by scanning subsets of the image. This is important when there may be multiple barcodes in
 *  an image, and detecting a barcode may find parts of multiple barcode and fail to decode
 *  (e.g. QR Codes). Instead this scans the four quadrants of the image -- and also the center
 *  'quadrant' to cover the case where a barcode is found in the center.
 * 
 *  @see GenericMultipleBarcodeReader
 */
public final class ByQuadrantReader implements com.google.zxing.Reader {

	public ByQuadrantReader(com.google.zxing.Reader delegate) {
	}

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
