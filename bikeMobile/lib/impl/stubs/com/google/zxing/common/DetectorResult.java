package com.google.zxing.common;


/**
 *  <p>Encapsulates the result of detecting a barcode in an image. This includes the raw
 *  matrix of black/white pixels corresponding to the barcode, and possibly points of interest
 *  in the image, like the location of finder patterns or corners of the barcode in the image.</p>
 * 
 *  @author Sean Owen
 */
public class DetectorResult {

	public DetectorResult(BitMatrix bits, com.google.zxing.ResultPoint[] points) {
	}

	public final BitMatrix getBits() {
	}

	public final com.google.zxing.ResultPoint[] getPoints() {
	}
}
