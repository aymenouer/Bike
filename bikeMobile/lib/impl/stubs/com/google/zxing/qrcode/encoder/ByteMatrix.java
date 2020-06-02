package com.google.zxing.qrcode.encoder;


/**
 *  JAVAPORT: The original code was a 2D array of ints, but since it only ever gets assigned
 *  -1, 0, and 1, I'm going to use less memory and go with bytes.
 * 
 *  @author dswitkin@google.com (Daniel Switkin)
 */
public final class ByteMatrix {

	public ByteMatrix(int width, int height) {
	}

	public int getHeight() {
	}

	public int getWidth() {
	}

	public byte get(int x, int y) {
	}

	/**
	 *  @return an internal representation as bytes, in row-major order. array[y][x] represents point (x,y)
	 */
	public byte[][] getArray() {
	}

	public void set(int x, int y, byte value) {
	}

	public void set(int x, int y, int value) {
	}

	public void set(int x, int y, boolean value) {
	}

	public void clear(byte value) {
	}

	@java.lang.Override
	public String toString() {
	}
}
