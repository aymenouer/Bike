package com.google.zxing.common.detector;


/**
 *  <p>A somewhat generic detector that looks for a barcode-like rectangular region within an image.
 *  It looks within a mostly white region of an image for a region of black and white, but mostly
 *  black. It returns the four corners of the region, as best it can determine.</p>
 * 
 *  @author Sean Owen
 *  @deprecated without replacement since 3.3.0
 */
@java.lang.Deprecated
public final class MonochromeRectangleDetector {

	public MonochromeRectangleDetector(com.google.zxing.common.BitMatrix image) {
	}

	/**
	 *  <p>Detects a rectangular region of black and white -- mostly black -- with a region of mostly
	 *  white, in an image.</p>
	 * 
	 *  @return {@link ResultPoint}[] describing the corners of the rectangular region. The first and
	 *   last points are opposed on the diagonal, as are the second and third. The first point will be
	 *   the topmost point and the last, the bottommost. The second point will be leftmost and the
	 *   third, the rightmost
	 *  @throws NotFoundException if no Data Matrix Code can be found
	 */
	public com.google.zxing.ResultPoint[] detect() {
	}
}
