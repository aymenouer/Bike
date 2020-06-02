package com.google.zxing.pdf417.encoder;


/**
 *  Top-level class for the logic part of the PDF417 implementation.
 */
public final class PDF417 {

	public PDF417() {
	}

	public PDF417(boolean compact) {
	}

	public BarcodeMatrix getBarcodeMatrix() {
	}

	/**
	 *  @param msg message to encode
	 *  @param errorCorrectionLevel PDF417 error correction level to use
	 *  @throws WriterException if the contents cannot be encoded in this format
	 */
	public void generateBarcodeLogic(String msg, int errorCorrectionLevel) {
	}

	/**
	 *  Sets max/min row/col values
	 * 
	 *  @param maxCols maximum allowed columns
	 *  @param minCols minimum allowed columns
	 *  @param maxRows maximum allowed rows
	 *  @param minRows minimum allowed rows
	 */
	public void setDimensions(int maxCols, int minCols, int maxRows, int minRows) {
	}

	/**
	 *  @param compaction compaction mode to use
	 */
	public void setCompaction(Compaction compaction) {
	}

	/**
	 *  @param compact if true, enables compaction
	 */
	public void setCompact(boolean compact) {
	}

	/**
	 *  @param encoding sets character encoding to use
	 */
	public void setEncoding(String encoding) {
	}
}
