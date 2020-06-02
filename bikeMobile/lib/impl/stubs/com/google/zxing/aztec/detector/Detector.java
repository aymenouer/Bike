package com.google.zxing.aztec.detector;


/**
 *  Encapsulates logic that can detect an Aztec Code in an image, even if the Aztec Code
 *  is rotated or skewed, or partially obscured.
 * 
 *  @author David Olivier
 *  @author Frank Yellin
 */
public final class Detector {

	public Detector(com.google.zxing.common.BitMatrix image) {
	}

	public com.google.zxing.aztec.AztecDetectorResult detect() {
	}

	/**
	 *  Detects an Aztec Code in an image.
	 * 
	 *  @param isMirror if true, image is a mirror-image of original
	 *  @return {@link AztecDetectorResult} encapsulating results of detecting an Aztec Code
	 *  @throws NotFoundException if no Aztec Code can be found
	 */
	public com.google.zxing.aztec.AztecDetectorResult detect(boolean isMirror) {
	}
}
