package com.google.zxing.oned;


/**
 *  <p>Implements decoding of the UPC-A format.</p>
 * 
 *  @author dswitkin@google.com (Daniel Switkin)
 *  @author Sean Owen
 */
public final class UPCAReader extends UPCEANReader {

	public UPCAReader() {
	}

	@java.lang.Override
	public com.google.zxing.Result decodeRow(int rowNumber, com.google.zxing.common.BitArray row, int[] startGuardRange, java.util.Map hints) {
	}

	@java.lang.Override
	public com.google.zxing.Result decodeRow(int rowNumber, com.google.zxing.common.BitArray row, java.util.Map hints) {
	}

	@java.lang.Override
	public com.google.zxing.Result decode(com.google.zxing.BinaryBitmap image) {
	}

	@java.lang.Override
	public com.google.zxing.Result decode(com.google.zxing.BinaryBitmap image, java.util.Map hints) {
	}

	@java.lang.Override
	protected int decodeMiddle(com.google.zxing.common.BitArray row, int[] startRange, StringBuilder resultString) {
	}
}
