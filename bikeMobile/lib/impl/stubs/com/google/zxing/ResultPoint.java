package com.google.zxing;


/**
 *  <p>
 *  Encapsulates a point of interest in an image containing a barcode. Typically,
 *  this would be the location of a finder pattern or the corner of the barcode,
 *  for example.
 *  </p>
 * 
 *  @author Sean Owen
 */
public class ResultPoint {

	public ResultPoint(float x, float y) {
	}

	public final float getX() {
	}

	public final float getY() {
	}

	@java.lang.Override
	public final boolean equals(Object other) {
	}

	@java.lang.Override
	public final int hashCode() {
	}

	@java.lang.Override
	public final String toString() {
	}

	/**
	 *  Orders an array of three ResultPoints in an order [A,B,C] such that AB is
	 *  less than AC and BC is less than AC, and the angle between BC and BA is less
	 *  than 180 degrees.
	 * 
	 *  @param patterns array of three {@code ResultPoint} to order
	 */
	public static void orderBestPatterns(ResultPoint[] patterns) {
	}

	/**
	 *  @param pattern1 first pattern
	 *  @param pattern2 second pattern
	 *  @return distance between two points
	 */
	public static float distance(ResultPoint pattern1, ResultPoint pattern2) {
	}
}
