package com.google.zxing.oned.rss;


/**
 *  Superclass of {@link OneDReader} implementations that read barcodes in the RSS family
 *  of formats.
 */
public abstract class AbstractRSSReader extends com.google.zxing.oned.OneDReader {

	protected AbstractRSSReader() {
	}

	protected final int[] getDecodeFinderCounters() {
	}

	protected final int[] getDataCharacterCounters() {
	}

	protected final float[] getOddRoundingErrors() {
	}

	protected final float[] getEvenRoundingErrors() {
	}

	protected final int[] getOddCounts() {
	}

	protected final int[] getEvenCounts() {
	}

	protected static int parseFinderValue(int[] counters, int[][] finderPatterns) {
	}

	/**
	 *  @param array values to sum
	 *  @return sum of values
	 *  @deprecated call {@link MathUtils#sum(int[])}
	 */
	@java.lang.Deprecated
	protected static int count(int[] array) {
	}

	protected static void increment(int[] array, float[] errors) {
	}

	protected static void decrement(int[] array, float[] errors) {
	}

	protected static boolean isFinderPattern(int[] counters) {
	}
}
