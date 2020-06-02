package com.google.zxing.oned;


/**
 *  <p>Encapsulates functionality and implementation that is common to UPC and EAN families
 *  of one-dimensional barcodes.</p>
 * 
 *  @author dswitkin@google.com (Daniel Switkin)
 *  @author Sean Owen
 *  @author alasdair@google.com (Alasdair Mackintosh)
 */
public abstract class UPCEANReader extends OneDReader {

	protected UPCEANReader() {
	}

	@java.lang.Override
	public com.google.zxing.Result decodeRow(int rowNumber, com.google.zxing.common.BitArray row, java.util.Map hints) {
	}

	/**
	 *  <p>Like {@link #decodeRow(int, BitArray, Map)}, but
	 *  allows caller to inform method about where the UPC/EAN start pattern is
	 *  found. This allows this to be computed once and reused across many implementations.</p>
	 * 
	 *  @param rowNumber row index into the image
	 *  @param row encoding of the row of the barcode image
	 *  @param startGuardRange start/end column where the opening start pattern was found
	 *  @param hints optional hints that influence decoding
	 *  @return {@link Result} encapsulating the result of decoding a barcode in the row
	 *  @throws NotFoundException if no potential barcode is found
	 *  @throws ChecksumException if a potential barcode is found but does not pass its checksum
	 *  @throws FormatException if a potential barcode is found but format is invalid
	 */
	public com.google.zxing.Result decodeRow(int rowNumber, com.google.zxing.common.BitArray row, int[] startGuardRange, java.util.Map hints) {
	}

	/**
	 *  Subclasses override this to decode the portion of a barcode between the start
	 *  and end guard patterns.
	 * 
	 *  @param row row of black/white values to search
	 *  @param startRange start/end offset of start guard pattern
	 *  @param resultString {@link StringBuilder} to append decoded chars to
	 *  @return horizontal offset of first pixel after the "middle" that was decoded
	 *  @throws NotFoundException if decoding could not complete successfully
	 */
	protected abstract int decodeMiddle(com.google.zxing.common.BitArray row, int[] startRange, StringBuilder resultString) {
	}
}
