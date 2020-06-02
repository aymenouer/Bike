package com.google.zxing.datamatrix.detector;


/**
 *  <p>Encapsulates logic that can detect a Data Matrix Code in an image, even if the Data Matrix Code
 *  is rotated or skewed, or partially obscured.</p>
 * 
 *  @author Sean Owen
 */
public final class Detector {

	public Detector(com.google.zxing.common.BitMatrix image) {
	}

	/**
	 *  <p>Detects a Data Matrix Code in an image.</p>
	 * 
	 *  @return {@link DetectorResult} encapsulating results of detecting a Data Matrix Code
	 *  @throws NotFoundException if no Data Matrix Code can be found
	 */
	public com.google.zxing.common.DetectorResult detect() {
	}
}
