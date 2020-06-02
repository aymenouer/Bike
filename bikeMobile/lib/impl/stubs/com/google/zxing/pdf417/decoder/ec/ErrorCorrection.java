package com.google.zxing.pdf417.decoder.ec;


/**
 *  <p>PDF417 error correction implementation.</p>
 * 
 *  <p>This <a href="http://en.wikipedia.org/wiki/Reed%E2%80%93Solomon_error_correction#Example">example</a>
 *  is quite useful in understanding the algorithm.</p>
 * 
 *  @author Sean Owen
 *  @see com.google.zxing.common.reedsolomon.ReedSolomonDecoder
 */
public final class ErrorCorrection {

	public ErrorCorrection() {
	}

	/**
	 *  @param received received codewords
	 *  @param numECCodewords number of those codewords used for EC
	 *  @param erasures location of erasures
	 *  @return number of errors
	 *  @throws ChecksumException if errors cannot be corrected, maybe because of too many errors
	 */
	public int decode(int[] received, int numECCodewords, int[] erasures) {
	}
}
