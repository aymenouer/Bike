package com.google.zxing.common.detector;


/**
 *  General math-related and numeric utility functions.
 */
public final class MathUtils {

	/**
	 *  Ends up being a bit faster than {@link Math#round(float)}. This merely rounds its
	 *  argument to the nearest int, where x.5 rounds up to x+1. Semantics of this shortcut
	 *  differ slightly from {@link Math#round(float)} in that half rounds down for negative
	 *  values. -2.5 rounds to -3, not -2. For purposes here it makes no difference.
	 * 
	 *  @param d real value to round
	 *  @return nearest {@code int}
	 */
	public static int round(float d) {
	}

	/**
	 *  @param aX point A x coordinate
	 *  @param aY point A y coordinate
	 *  @param bX point B x coordinate
	 *  @param bY point B y coordinate
	 *  @return Euclidean distance between points A and B
	 */
	public static float distance(float aX, float aY, float bX, float bY) {
	}

	/**
	 *  @param aX point A x coordinate
	 *  @param aY point A y coordinate
	 *  @param bX point B x coordinate
	 *  @param bY point B y coordinate
	 *  @return Euclidean distance between points A and B
	 */
	public static float distance(int aX, int aY, int bX, int bY) {
	}

	/**
	 *  @param array values to sum
	 *  @return sum of values in array
	 */
	public static int sum(int[] array) {
	}
}
