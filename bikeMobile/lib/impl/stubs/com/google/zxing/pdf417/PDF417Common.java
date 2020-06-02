package com.google.zxing.pdf417;


/**
 *  @author SITA Lab (kevin.osullivan@sita.aero)
 *  @author Guenther Grau
 */
public final class PDF417Common {

	public static final int NUMBER_OF_CODEWORDS = 929;

	public static final int MAX_CODEWORDS_IN_BARCODE = 928;

	public static final int MIN_ROWS_IN_BARCODE = 3;

	public static final int MAX_ROWS_IN_BARCODE = 90;

	public static final int MODULES_IN_CODEWORD = 17;

	public static final int MODULES_IN_STOP_PATTERN = 18;

	public static final int BARS_IN_MODULE = 8;

	/**
	 *  The sorted table of all possible symbols. Extracted from the PDF417
	 *  specification. The index of a symbol in this table corresponds to the
	 *  index into the codeword table.
	 */
	public static final int[] SYMBOL_TABLE;

	/**
	 *  @param moduleBitCount values to sum
	 *  @return sum of values
	 *  @deprecated call {@link MathUtils#sum(int[])}
	 */
	@java.lang.Deprecated
	public static int getBitCountSum(int[] moduleBitCount) {
	}

	public static int[] toIntArray(java.util.Collection list) {
	}

	/**
	 *  @param symbol encoded symbol to translate to a codeword
	 *  @return the codeword corresponding to the symbol.
	 */
	public static int getCodeword(int symbol) {
	}
}
