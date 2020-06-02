package com.google.zxing.datamatrix.encoder;


/**
 *  DataMatrix ECC 200 data encoder following the algorithm described in ISO/IEC 16022:200(E) in
 *  annex S.
 */
public final class HighLevelEncoder {

	/**
	 *  Performs message encoding of a DataMatrix message using the algorithm described in annex P
	 *  of ISO/IEC 16022:2000(E).
	 * 
	 *  @param msg the message
	 *  @return the encoded message (the char values range from 0 to 255)
	 */
	public static String encodeHighLevel(String msg) {
	}

	/**
	 *  Performs message encoding of a DataMatrix message using the algorithm described in annex P
	 *  of ISO/IEC 16022:2000(E).
	 * 
	 *  @param msg     the message
	 *  @param shape   requested shape. May be {@code SymbolShapeHint.FORCE_NONE},
	 *                 {@code SymbolShapeHint.FORCE_SQUARE} or {@code SymbolShapeHint.FORCE_RECTANGLE}.
	 *  @param minSize the minimum symbol size constraint or null for no constraint
	 *  @param maxSize the maximum symbol size constraint or null for no constraint
	 *  @return the encoded message (the char values range from 0 to 255)
	 */
	public static String encodeHighLevel(String msg, SymbolShapeHint shape, com.google.zxing.Dimension minSize, com.google.zxing.Dimension maxSize) {
	}

	/**
	 *  Determines the number of consecutive characters that are encodable using numeric compaction.
	 * 
	 *  @param msg      the message
	 *  @param startpos the start position within the message
	 *  @return the requested character count
	 */
	public static int determineConsecutiveDigitCount(CharSequence msg, int startpos) {
	}
}
