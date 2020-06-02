package com.google.zxing.multi;


/**
 *  Implementation of this interface attempt to read several barcodes from one image.
 * 
 *  @see com.google.zxing.Reader
 *  @author Sean Owen
 */
public interface MultipleBarcodeReader {

	public com.google.zxing.Result[] decodeMultiple(com.google.zxing.BinaryBitmap image);

	public com.google.zxing.Result[] decodeMultiple(com.google.zxing.BinaryBitmap image, java.util.Map hints);
}
