package com.google.zxing.qrcode.detector;


/**
 *  <p>
 *  This class attempts to find finder patterns in a QR Code. Finder patterns are
 *  the square markers at three corners of a QR Code.</p>
 * 
 *  <p>
 *  This class is thread-safe but not reentrant. Each thread must allocate its
 *  own object.
 * 
 *  @author Sean Owen
 */
public class FinderPatternFinder {

	protected static final int MIN_SKIP = 3;

	protected static final int MAX_MODULES = 97;

	/**
	 *  <p>
	 *  Creates a finder that will search the image for three finder
	 *  patterns.</p>
	 * 
	 *  @param image image to search
	 */
	public FinderPatternFinder(com.google.zxing.common.BitMatrix image) {
	}

	public FinderPatternFinder(com.google.zxing.common.BitMatrix image, com.google.zxing.ResultPointCallback resultPointCallback) {
	}

	protected final com.google.zxing.common.BitMatrix getImage() {
	}

	protected final java.util.List getPossibleCenters() {
	}

	/**
	 *  @param stateCount count of black/white/black/white/black pixels just read
	 *  @return true if the proportions of the counts is close enough to the
	 *  1/1/3/1/1 ratios used by finder patterns to be considered a match
	 */
	protected static boolean foundPatternCross(int[] stateCount) {
	}

	/**
	 *  @param stateCount count of black/white/black/white/black pixels just read
	 *  @return true iff the proportions of the counts is close enough to the
	 *  1/1/3/1/1 ratios used by finder patterns to be considered a match
	 */
	protected static boolean foundPatternDiagonal(int[] stateCount) {
	}

	protected final void clearCounts(int[] counts) {
	}

	protected final void shiftCounts2(int[] stateCount) {
	}

	/**
	 *  <p>
	 *  This is called when a horizontal scan finds a possible alignment pattern.
	 *  It will cross check with a vertical scan, and if successful, will, ah,
	 *  cross-cross-check with another horizontal scan. This is needed primarily
	 *  to locate the real horizontal center of the pattern in cases of extreme
	 *  skew. And then we cross-cross-cross check with another diagonal scan.</p>
	 * 
	 *  <p>
	 *  If that succeeds the finder pattern location is added to a list that
	 *  tracks the number of times each location has been nearly-matched as a
	 *  finder pattern. Each additional find is more evidence that the location
	 *  is in fact a finder pattern center
	 * 
	 *  @param stateCount reading state module counts from horizontal scan
	 *  @param i row where finder pattern may be found
	 *  @param j end of possible finder pattern in row
	 *  @return true if a finder pattern candidate was found this time
	 */
	protected final boolean handlePossibleCenter(int[] stateCount, int i, int j) {
	}
}
