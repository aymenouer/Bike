package com.google.zxing.oned;


/**
 *  Encapsulates functionality and implementation that is common to all families
 *  of one-dimensional barcodes.
 * 
 *  @author dswitkin@google.com (Daniel Switkin)
 *  @author Sean Owen
 */
public abstract class OneDReader implements com.google.zxing.Reader {

	public OneDReader() {
	}

	@java.lang.Override
	public com.google.zxing.Result decode(com.google.zxing.BinaryBitmap image) {
	}

	@java.lang.Override
	public com.google.zxing.Result decode(com.google.zxing.BinaryBitmap image, java.util.Map hints) {
	}

	@java.lang.Override
	public void reset() {
	}

	/**
	 *  Records the size of successive runs of white and black pixels in a row, starting at a given point.
	 *  The values are recorded in the given array, and the number of runs recorded is equal to the size
	 *  of the array. If the row starts on a white pixel at the given start point, then the first count
	 *  recorded is the run of white pixels starting from that point; likewise it is the count of a run
	 *  of black pixels if the row begin on a black pixels at that point.
	 * 
	 *  @param row row to count from
	 *  @param start offset into row to start at
	 *  @param counters array into which to record counts
	 *  @throws NotFoundException if counters cannot be filled entirely from row before running out
	 *   of pixels
	 */
	protected static void recordPattern(com.google.zxing.common.BitArray row, int start, int[] counters) {
	}

	protected static void recordPatternInReverse(com.google.zxing.common.BitArray row, int start, int[] counters) {
	}

	/**
	 *  Determines how closely a set of observed counts of runs of black/white values matches a given
	 *  target pattern. This is reported as the ratio of the total variance from the expected pattern
	 *  proportions across all pattern elements, to the length of the pattern.
	 * 
	 *  @param counters observed counters
	 *  @param pattern expected pattern
	 *  @param maxIndividualVariance The most any counter can differ before we give up
	 *  @return ratio of total variance between counters and pattern compared to total pattern size
	 */
	protected static float patternMatchVariance(int[] counters, int[] pattern, float maxIndividualVariance) {
	}

	/**
	 *  <p>Attempts to decode a one-dimensional barcode format given a single row of
	 *  an image.</p>
	 * 
	 *  @param rowNumber row number from top of the row
	 *  @param row the black/white pixel data of the row
	 *  @param hints decode hints
	 *  @return {@link Result} containing encoded string and start/end of barcode
	 *  @throws NotFoundException if no potential barcode is found
	 *  @throws ChecksumException if a potential barcode is found but does not pass its checksum
	 *  @throws FormatException if a potential barcode is found but format is invalid
	 */
	public abstract com.google.zxing.Result decodeRow(int rowNumber, com.google.zxing.common.BitArray row, java.util.Map hints) {
	}
}
