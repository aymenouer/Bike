package com.google.zxing.qrcode.detector;


/**
 *  <p>Encapsulates logic that can detect a QR Code in an image, even if the QR Code
 *  is rotated or skewed, or partially obscured.</p>
 * 
 *  @author Sean Owen
 */
public class Detector {

	public Detector(com.google.zxing.common.BitMatrix image) {
	}

	protected final com.google.zxing.common.BitMatrix getImage() {
	}

	protected final com.google.zxing.ResultPointCallback getResultPointCallback() {
	}

	/**
	 *  <p>Detects a QR Code in an image.</p>
	 * 
	 *  @return {@link DetectorResult} encapsulating results of detecting a QR Code
	 *  @throws NotFoundException if QR Code cannot be found
	 *  @throws FormatException if a QR Code cannot be decoded
	 */
	public com.google.zxing.common.DetectorResult detect() {
	}

	/**
	 *  <p>Detects a QR Code in an image.</p>
	 * 
	 *  @param hints optional hints to detector
	 *  @return {@link DetectorResult} encapsulating results of detecting a QR Code
	 *  @throws NotFoundException if QR Code cannot be found
	 *  @throws FormatException if a QR Code cannot be decoded
	 */
	public final com.google.zxing.common.DetectorResult detect(java.util.Map hints) {
	}

	protected final com.google.zxing.common.DetectorResult processFinderPatternInfo(FinderPatternInfo info) {
	}

	/**
	 *  <p>Computes an average estimated module size based on estimated derived from the positions
	 *  of the three finder patterns.</p>
	 * 
	 *  @param topLeft detected top-left finder pattern center
	 *  @param topRight detected top-right finder pattern center
	 *  @param bottomLeft detected bottom-left finder pattern center
	 *  @return estimated module size
	 */
	protected final float calculateModuleSize(com.google.zxing.ResultPoint topLeft, com.google.zxing.ResultPoint topRight, com.google.zxing.ResultPoint bottomLeft) {
	}

	/**
	 *  <p>Attempts to locate an alignment pattern in a limited region of the image, which is
	 *  guessed to contain it. This method uses {@link AlignmentPattern}.</p>
	 * 
	 *  @param overallEstModuleSize estimated module size so far
	 *  @param estAlignmentX x coordinate of center of area probably containing alignment pattern
	 *  @param estAlignmentY y coordinate of above
	 *  @param allowanceFactor number of pixels in all directions to search from the center
	 *  @return {@link AlignmentPattern} if found, or null otherwise
	 *  @throws NotFoundException if an unexpected error occurs during detection
	 */
	protected final AlignmentPattern findAlignmentInRegion(float overallEstModuleSize, int estAlignmentX, int estAlignmentY, float allowanceFactor) {
	}
}
