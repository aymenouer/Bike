package com.google.zxing.oned;


/**
 *  <p>Implements decoding of the UPC-E format.</p>
 *  <p><a href="http://www.barcodeisland.com/upce.phtml">This</a> is a great reference for
 *  UPC-E information.</p>
 * 
 *  @author Sean Owen
 */
public final class UPCEReader extends UPCEANReader {

	public UPCEReader() {
	}

	@java.lang.Override
	protected int decodeMiddle(com.google.zxing.common.BitArray row, int[] startRange, StringBuilder result) {
	}

	@java.lang.Override
	protected int[] decodeEnd(com.google.zxing.common.BitArray row, int endStart) {
	}

	@java.lang.Override
	protected boolean checkChecksum(String s) {
	}

	/**
	 *  Expands a UPC-E value back into its full, equivalent UPC-A code value.
	 * 
	 *  @param upce UPC-E code as string of digits
	 *  @return equivalent UPC-A code as string of digits
	 */
	public static String convertUPCEtoUPCA(String upce) {
	}
}
