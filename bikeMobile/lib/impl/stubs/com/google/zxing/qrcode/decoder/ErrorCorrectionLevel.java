package com.google.zxing.qrcode.decoder;


/**
 *  <p>See ISO 18004:2006, 6.5.1. This enum encapsulates the four error correction levels
 *  defined by the QR code standard.</p>
 * 
 *  @author Sean Owen
 */
public final class ErrorCorrectionLevel extends Enum {

	/**
	 * L = ~7% correction 
	 */
	public static final ErrorCorrectionLevel L;

	/**
	 * M = ~15% correction 
	 */
	public static final ErrorCorrectionLevel M;

	/**
	 * Q = ~25% correction 
	 */
	public static final ErrorCorrectionLevel Q;

	/**
	 * H = ~30% correction 
	 */
	public static final ErrorCorrectionLevel H;

	public static ErrorCorrectionLevel[] values() {
	}

	public static ErrorCorrectionLevel valueOf(String name) {
	}

	public int getBits() {
	}

	/**
	 *  @param bits int containing the two bits encoding a QR Code's error correction level
	 *  @return ErrorCorrectionLevel representing the encoded error correction level
	 */
	public static ErrorCorrectionLevel forBits(int bits) {
	}
}
