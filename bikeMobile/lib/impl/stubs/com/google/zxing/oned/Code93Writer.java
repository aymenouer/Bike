package com.google.zxing.oned;


/**
 *  This object renders a CODE93 code as a BitMatrix
 */
public class Code93Writer extends OneDimensionalCodeWriter {

	public Code93Writer() {
	}

	@java.lang.Override
	protected java.util.Collection getSupportedWriteFormats() {
	}

	/**
	 *  @param contents barcode contents to encode. It should not be encoded for extended characters.
	 *  @return a {@code boolean[]} of horizontal pixels (false = white, true = black)
	 */
	@java.lang.Override
	public boolean[] encode(String contents) {
	}

	/**
	 *  @param target output to append to
	 *  @param pos start position
	 *  @param pattern pattern to append
	 *  @param startColor unused
	 *  @return 9
	 *  @deprecated without replacement; intended as an internal-only method
	 */
	@java.lang.Deprecated
	protected static int appendPattern(boolean[] target, int pos, int[] pattern, boolean startColor) {
	}
}
