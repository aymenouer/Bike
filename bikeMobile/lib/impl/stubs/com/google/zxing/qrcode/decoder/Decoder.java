package com.google.zxing.qrcode.decoder;


/**
 *  <p>The main class which implements QR Code decoding -- as opposed to locating and extracting
 *  the QR Code from an image.</p>
 * 
 *  @author Sean Owen
 */
public final class Decoder {

	public Decoder() {
	}

	public com.google.zxing.common.DecoderResult decode(boolean[][] image) {
	}

	/**
	 *  <p>Convenience method that can decode a QR Code represented as a 2D array of booleans.
	 *  "true" is taken to mean a black module.</p>
	 * 
	 *  @param image booleans representing white/black QR Code modules
	 *  @param hints decoding hints that should be used to influence decoding
	 *  @return text and bytes encoded within the QR Code
	 *  @throws FormatException if the QR Code cannot be decoded
	 *  @throws ChecksumException if error correction fails
	 */
	public com.google.zxing.common.DecoderResult decode(boolean[][] image, java.util.Map hints) {
	}

	public com.google.zxing.common.DecoderResult decode(com.google.zxing.common.BitMatrix bits) {
	}

	/**
	 *  <p>Decodes a QR Code represented as a {@link BitMatrix}. A 1 or "true" is taken to mean a black module.</p>
	 * 
	 *  @param bits booleans representing white/black QR Code modules
	 *  @param hints decoding hints that should be used to influence decoding
	 *  @return text and bytes encoded within the QR Code
	 *  @throws FormatException if the QR Code cannot be decoded
	 *  @throws ChecksumException if error correction fails
	 */
	public com.google.zxing.common.DecoderResult decode(com.google.zxing.common.BitMatrix bits, java.util.Map hints) {
	}
}
