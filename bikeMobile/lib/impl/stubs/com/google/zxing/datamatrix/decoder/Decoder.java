package com.google.zxing.datamatrix.decoder;


/**
 *  <p>The main class which implements Data Matrix Code decoding -- as opposed to locating and extracting
 *  the Data Matrix Code from an image.</p>
 * 
 *  @author bbrown@google.com (Brian Brown)
 */
public final class Decoder {

	public Decoder() {
	}

	/**
	 *  <p>Convenience method that can decode a Data Matrix Code represented as a 2D array of booleans.
	 *  "true" is taken to mean a black module.</p>
	 * 
	 *  @param image booleans representing white/black Data Matrix Code modules
	 *  @return text and bytes encoded within the Data Matrix Code
	 *  @throws FormatException if the Data Matrix Code cannot be decoded
	 *  @throws ChecksumException if error correction fails
	 */
	public com.google.zxing.common.DecoderResult decode(boolean[][] image) {
	}

	/**
	 *  <p>Decodes a Data Matrix Code represented as a {@link BitMatrix}. A 1 or "true" is taken
	 *  to mean a black module.</p>
	 * 
	 *  @param bits booleans representing white/black Data Matrix Code modules
	 *  @return text and bytes encoded within the Data Matrix Code
	 *  @throws FormatException if the Data Matrix Code cannot be decoded
	 *  @throws ChecksumException if error correction fails
	 */
	public com.google.zxing.common.DecoderResult decode(com.google.zxing.common.BitMatrix bits) {
	}
}
