package com.google.zxing;


/**
 *  This object extends LuminanceSource around an array of YUV data returned from the camera driver,
 *  with the option to crop to a rectangle within the full data. This can be used to exclude
 *  superfluous pixels around the perimeter and speed up decoding.
 * 
 *  It works for any pixel format where the Y channel is planar and appears first, including
 *  YCbCr_420_SP and YCbCr_422_SP.
 * 
 *  @author dswitkin@google.com (Daniel Switkin)
 */
public final class PlanarYUVLuminanceSource extends LuminanceSource {

	public PlanarYUVLuminanceSource(byte[] yuvData, int dataWidth, int dataHeight, int left, int top, int width, int height, boolean reverseHorizontal) {
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

	public int[] renderThumbnail() {
	}

	/**
	 *  @return width of image from {@link #renderThumbnail()}
	 */
	public int getThumbnailWidth() {
	}

	/**
	 *  @return height of image from {@link #renderThumbnail()}
	 */
	public int getThumbnailHeight() {
	}
}
