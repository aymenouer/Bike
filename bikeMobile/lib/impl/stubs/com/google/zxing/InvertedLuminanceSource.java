package com.google.zxing;


/**
 *  A wrapper implementation of {@link LuminanceSource} which inverts the luminances it returns -- black becomes
 *  white and vice versa, and each value becomes (255-value).
 * 
 *  @author Sean Owen
 */
public final class InvertedLuminanceSource extends LuminanceSource {

	public InvertedLuminanceSource(LuminanceSource delegate) {
	}

	@java.lang.Override
	public byte[] getRow(int y, byte[] row) {
	}

	@java.lang.Override
	public byte[] getMatrix() {
	}

	@java.lang.Override
	public boolean isCropSupported() {
	}

	@java.lang.Override
	public LuminanceSource crop(int left, int top, int width, int height) {
	}

	@java.lang.Override
	public boolean isRotateSupported() {
	}

	/**
	 *  @return original delegate {@link LuminanceSource} since invert undoes itself
	 */
	@java.lang.Override
	public LuminanceSource invert() {
	}

	@java.lang.Override
	public LuminanceSource rotateCounterClockwise() {
	}

	@java.lang.Override
	public LuminanceSource rotateCounterClockwise45() {
	}
}
