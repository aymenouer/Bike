package com.google.zxing.aztec.encoder;


/**
 *  Aztec 2D code representation
 * 
 *  @author Rustam Abdullaev
 */
public final class AztecCode {

	public AztecCode() {
	}

	/**
	 *  @return {@code true} if compact instead of full mode
	 */
	public boolean isCompact() {
	}

	public void setCompact(boolean compact) {
	}

	/**
	 *  @return size in pixels (width and height)
	 */
	public int getSize() {
	}

	public void setSize(int size) {
	}

	/**
	 *  @return number of levels
	 */
	public int getLayers() {
	}

	public void setLayers(int layers) {
	}

	/**
	 *  @return number of data codewords
	 */
	public int getCodeWords() {
	}

	public void setCodeWords(int codeWords) {
	}

	/**
	 *  @return the symbol image
	 */
	public com.google.zxing.common.BitMatrix getMatrix() {
	}

	public void setMatrix(com.google.zxing.common.BitMatrix matrix) {
	}
}
