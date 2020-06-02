package com.google.zxing.oned;


/**
 *  <p>Decodes Code 39 barcodes. Supports "Full ASCII Code 39" if USE_CODE_39_EXTENDED_MODE is set.</p>
 * 
 *  @author Sean Owen
 *  @see Code93Reader
 */
public final class Code39Reader extends OneDReader {

	/**
	 *  Creates a reader that assumes all encoded data is data, and does not treat the final
	 *  character as a check digit. It will not decoded "extended Code 39" sequences.
	 */
	public Code39Reader() {
	}

	/**
	 *  Creates a reader that can be configured to check the last character as a check digit.
	 *  It will not decoded "extended Code 39" sequences.
	 * 
	 *  @param usingCheckDigit if true, treat the last data character as a check digit, not
	 *  data, and verify that the checksum passes.
	 */
	public Code39Reader(boolean usingCheckDigit) {
	}

	/**
	 *  Creates a reader that can be configured to check the last character as a check digit,
	 *  or optionally attempt to decode "extended Code 39" sequences that are used to encode
	 *  the full ASCII character set.
	 * 
	 *  @param usingCheckDigit if true, treat the last data character as a check digit, not
	 *  data, and verify that the checksum passes.
	 *  @param extendedMode if true, will attempt to decode extended Code 39 sequences in the
	 *  text.
	 */
	public Code39Reader(boolean usingCheckDigit, boolean extendedMode) {
	}

	@java.lang.Override
	public com.google.zxing.Result decodeRow(int rowNumber, com.google.zxing.common.BitArray row, java.util.Map hints) {
	}
}
