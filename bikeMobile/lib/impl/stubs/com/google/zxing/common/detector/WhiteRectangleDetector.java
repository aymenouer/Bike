package com.google.zxing.common.detector;


/**
 *  <p>
 *  Detects a candidate barcode-like rectangular region within an image. It
 *  starts around the center of the image, increases the size of the candidate
 *  region until it finds a white rectangular region. By keeping track of the
 *  last black points it encountered, it determines the corners of the barcode.
 *  </p>
 * 
 *  @author David Olivier
 */
public final class WhiteRectangleDetector {

	public WhiteRectangleDetector(com.google.zxing.common.BitMatrix image) {
	}

	/**
	 *  @param image barcode image to find a rectangle in
	 *  @param initSize initial size of search area around center
	 *  @param x x position of search center
	 *  @param y y position of search center
	 *  @throws NotFoundException if image is too small to accommodate {@code initSize}
	 */
	public WhiteRectangleDetector(com.google.zxing.common.BitMatrix image, int initSize, int x, int y) {
	}

	/**
	 *  <p>
	 *  Detects a candidate barcode-like rectangular region within an image. It
	 *  starts around the center of the image, increases the size of the candidate
	 *  region until it finds a white rectangular region.
	 *  </p>
	 * 
	 *  @return {@link ResultPoint}[] describing the corners of the rectangular
	 *          region. The first and last points are opposed on the diagonal, as
	 *          are the second and third. The first point will be the topmost
	 *          point and the last, the bottommost. The second point will be
	 *          leftmost and the third, the rightmost
	 *  @throws NotFoundException if no Data Matrix Code can be found
	 */
	public com.google.zxing.ResultPoint[] detect() {
	}
}
