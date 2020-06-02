package com.google.zxing;


/**
 *  This class is used to help decode images from files which arrive as RGB data from
 *  an ARGB pixel array. It does not support rotation.
 * 
 *  @author dswitkin@google.com (Daniel Switkin)
 *  @author Betaminos
 */
public final class RGBLuminanceSource extends LuminanceSource {

	public RGBLuminanceSource(int width, int height, int[] pixels) {
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
}
