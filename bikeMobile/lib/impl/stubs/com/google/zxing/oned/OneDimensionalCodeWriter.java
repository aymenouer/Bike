package com.google.zxing.oned;


/**
 *  <p>
 *  Encapsulates functionality and implementation that is common to
 *  one-dimensional barcodes.
 *  </p>
 * 
 *  @author dsbnatut@gmail.com (Kazuki Nishiura)
 */
public abstract class OneDimensionalCodeWriter implements com.google.zxing.Writer {

	public OneDimensionalCodeWriter() {
	}

	@java.lang.Override
	public final com.google.zxing.common.BitMatrix encode(String contents, com.google.zxing.BarcodeFormat format, int width, int height) {
	}

	/**
	 *  Encode the contents following specified format. {@code width} and
	 *  {@code height} are required size. This method may return bigger size
	 *  {@code BitMatrix} when specified size is too small. The user can set both
	 *  {@code width} and {@code height} to zero to get minimum size barcode. If
	 *  negative value is set to {@code width} or {@code height},
	 *  {@code IllegalArgumentException} is thrown.
	 */
	@java.lang.Override
	public com.google.zxing.common.BitMatrix encode(String contents, com.google.zxing.BarcodeFormat format, int width, int height, java.util.Map hints) {
	}

	protected java.util.Collection getSupportedWriteFormats() {
	}

	/**
	 *  @param contents string to check for numeric characters
	 *  @throws IllegalArgumentException if input contains characters other than
	 *                                   digits 0-9.
	 */
	protected static void checkNumeric(String contents) {
	}

	/**
	 *  @param target     encode black/white pattern into this array
	 *  @param pos        position to start encoding at in {@code target}
	 *  @param pattern    lengths of black/white runs to encode
	 *  @param startColor starting color - false for white, true for black
	 *  @return the number of elements added to target.
	 */
	protected static int appendPattern(boolean[] target, int pos, int[] pattern, boolean startColor) {
	}

	public int getDefaultMargin() {
	}

	/**
	 *  Encode the contents to boolean array expression of one-dimensional barcode.
	 *  Start code and end code should be included in result, and side margins should
	 *  not be included.
	 * 
	 *  @param contents barcode contents to encode
	 *  @return a {@code boolean[]} of horizontal pixels (false = white, true =
	 *          black)
	 */
	public abstract boolean[] encode(String contents) {
	}
}
