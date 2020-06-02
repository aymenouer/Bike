package com.google.zxing.multi;


/**
 *  <p>Attempts to locate multiple barcodes in an image by repeatedly decoding portion of the image.
 *  After one barcode is found, the areas left, above, right and below the barcode's
 *  {@link ResultPoint}s are scanned, recursively.</p>
 * 
 *  <p>A caller may want to also employ {@link ByQuadrantReader} when attempting to find multiple
 *  2D barcodes, like QR Codes, in an image, where the presence of multiple barcodes might prevent
 *  detecting any one of them.</p>
 * 
 *  <p>That is, instead of passing a {@link Reader} a caller might pass
 *  {@code new ByQuadrantReader(reader)}.</p>
 * 
 *  @author Sean Owen
 */
public final class GenericMultipleBarcodeReader implements MultipleBarcodeReader {

	public GenericMultipleBarcodeReader(com.google.zxing.Reader delegate) {
	}

	@java.lang.Override
	public com.google.zxing.Result[] decodeMultiple(com.google.zxing.BinaryBitmap image) {
	}

	@java.lang.Override
	public com.google.zxing.Result[] decodeMultiple(com.google.zxing.BinaryBitmap image, java.util.Map hints) {
	}
}
