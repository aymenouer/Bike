package com.google.zxing.pdf417.detector;


/**
 *  <p>Encapsulates logic that can detect a PDF417 Code in an image, even if the
 *  PDF417 Code is rotated or skewed, or partially obscured.</p>
 * 
 *  @author SITA Lab (kevin.osullivan@sita.aero)
 *  @author dswitkin@google.com (Daniel Switkin)
 *  @author Guenther Grau
 */
public final class Detector {

	/**
	 *  <p>Detects a PDF417 Code in an image. Only checks 0 and 180 degree rotations.</p>
	 * 
	 *  @param image barcode image to decode
	 *  @param hints optional hints to detector
	 *  @param multiple if true, then the image is searched for multiple codes. If false, then at most one code will
	 *  be found and returned
	 *  @return {@link PDF417DetectorResult} encapsulating results of detecting a PDF417 code
	 *  @throws NotFoundException if no PDF417 Code can be found
	 */
	public static PDF417DetectorResult detect(com.google.zxing.BinaryBitmap image, java.util.Map hints, boolean multiple) {
	}
}
