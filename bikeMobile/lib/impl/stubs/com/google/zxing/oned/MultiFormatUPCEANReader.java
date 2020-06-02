package com.google.zxing.oned;


/**
 *  <p>A reader that can read all available UPC/EAN formats. If a caller wants to try to
 *  read all such formats, it is most efficient to use this implementation rather than invoke
 *  individual readers.</p>
 * 
 *  @author Sean Owen
 */
public final class MultiFormatUPCEANReader extends OneDReader {

	public MultiFormatUPCEANReader(java.util.Map hints) {
	}

	@java.lang.Override
	public com.google.zxing.Result decodeRow(int rowNumber, com.google.zxing.common.BitArray row, java.util.Map hints) {
	}

	@java.lang.Override
	public void reset() {
	}
}
