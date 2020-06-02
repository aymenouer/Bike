package com.google.zxing.common;


/**
 *  <p>Encapsulates the result of decoding a matrix of bits. This typically
 *  applies to 2D barcode formats. For now it contains the raw bytes obtained,
 *  as well as a String interpretation of those bytes, if applicable.</p>
 * 
 *  @author Sean Owen
 */
public final class DecoderResult {

	public DecoderResult(byte[] rawBytes, String text, java.util.List byteSegments, String ecLevel) {
	}

	public DecoderResult(byte[] rawBytes, String text, java.util.List byteSegments, String ecLevel, int saSequence, int saParity) {
	}

	/**
	 *  @return raw bytes representing the result, or {@code null} if not applicable
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
	 *  @param numBits overrides the number of bits that are valid in {@link #getRawBytes()}
	 *  @since 3.3.0
	 */
	public void setNumBits(int numBits) {
	}

	/**
	 *  @return text representation of the result
	 */
	public String getText() {
	}

	/**
	 *  @return list of byte segments in the result, or {@code null} if not applicable
	 */
	public java.util.List getByteSegments() {
	}

	/**
	 *  @return name of error correction level used, or {@code null} if not applicable
	 */
	public String getECLevel() {
	}

	/**
	 *  @return number of errors corrected, or {@code null} if not applicable
	 */
	public Integer getErrorsCorrected() {
	}

	public void setErrorsCorrected(Integer errorsCorrected) {
	}

	/**
	 *  @return number of erasures corrected, or {@code null} if not applicable
	 */
	public Integer getErasures() {
	}

	public void setErasures(Integer erasures) {
	}

	/**
	 *  @return arbitrary additional metadata
	 */
	public Object getOther() {
	}

	public void setOther(Object other) {
	}

	public boolean hasStructuredAppend() {
	}

	public int getStructuredAppendParity() {
	}

	public int getStructuredAppendSequenceNumber() {
	}
}
