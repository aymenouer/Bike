package com.google.zxing;


/**
 *  <p>Encapsulates the result of decoding a barcode within an image.</p>
 * 
 *  @author Sean Owen
 */
public final class Result {

	public Result(String text, byte[] rawBytes, ResultPoint[] resultPoints, BarcodeFormat format) {
	}

	public Result(String text, byte[] rawBytes, ResultPoint[] resultPoints, BarcodeFormat format, long timestamp) {
	}

	public Result(String text, byte[] rawBytes, int numBits, ResultPoint[] resultPoints, BarcodeFormat format, long timestamp) {
	}

	/**
	 *  @return raw text encoded by the barcode
	 */
	public String getText() {
	}

	/**
	 *  @return raw bytes encoded by the barcode, if applicable, otherwise {@code null}
	 */
	public byte[] getRawBytes() {
	}

	/**
	 *  @return how many bits of {@link #getRawBytes()} are valid; typically 8 times its length
	 *  @since 3.3.0
	 */
	public int getNumBits() {
	}

	/**
	 *  @return points related to the barcode in the image. These are typically points
	 *          identifying finder patterns or the corners of the barcode. The exact meaning is
	 *          specific to the type of barcode that was decoded.
	 */
	public ResultPoint[] getResultPoints() {
	}

	/**
	 *  @return {@link BarcodeFormat} representing the format of the barcode that was decoded
	 */
	public BarcodeFormat getBarcodeFormat() {
	}

	/**
	 *  @return {@link Map} mapping {@link ResultMetadataType} keys to values. May be
	 *    {@code null}. This contains optional metadata about what was detected about the barcode,
	 *    like orientation.
	 */
	public java.util.Map getResultMetadata() {
	}

	public void putMetadata(ResultMetadataType type, Object value) {
	}

	public void putAllMetadata(java.util.Map metadata) {
	}

	public void addResultPoints(ResultPoint[] newPoints) {
	}

	public long getTimestamp() {
	}

	@java.lang.Override
	public String toString() {
	}
}
