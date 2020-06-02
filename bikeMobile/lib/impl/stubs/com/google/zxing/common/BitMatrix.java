package com.google.zxing.common;


/**
 *  <p>
 *  Represents a 2D matrix of bits. In function arguments below, and throughout
 *  the common module, x is the column position, and y is the row position. The
 *  ordering is always x, y. The origin is at the top-left.
 *  </p>
 * 
 *  <p>
 *  Internally the bits are represented in a 1-D array of 32-bit ints. However,
 *  each row begins with a new int. This is done intentionally so that we can
 *  copy out a row into a BitArray very efficiently.
 *  </p>
 * 
 *  <p>
 *  The ordering of bits is row-major. Within each int, the least significant
 *  bits are used first, meaning they represent lower x values. This is
 *  compatible with BitArray's implementation.
 *  </p>
 * 
 *  @author Sean Owen
 *  @author dswitkin@google.com (Daniel Switkin)
 */
@java.lang.SuppressWarnings("deprecation")
public final class BitMatrix implements Cloneable {

	/**
	 *  Creates an empty square {@code BitMatrix}.
	 * 
	 *  @param dimension height and width
	 */
	public BitMatrix(int dimension) {
	}

	/**
	 *  Creates an empty {@code BitMatrix}.
	 * 
	 *  @param width bit matrix width
	 *  @param height bit matrix height
	 */
	public BitMatrix(int width, int height) {
	}

	/**
	 *  Interprets a 2D array of booleans as a {@code BitMatrix}, where "true"
	 *  means an "on" bit.
	 * 
	 *  @param image bits of the image, as a row-major 2D array. Elements are
	 *  arrays representing rows
	 *  @return {@code BitMatrix} representation of image
	 */
	public static BitMatrix parse(boolean[][] image) {
	}

	public static BitMatrix parse(String stringRepresentation, String setString, String unsetString) {
	}

	/**
	 *  <p>
	 *  Gets the requested bit, where true means black.
	 *  </p>
	 * 
	 *  @param x The horizontal component (i.e. which column)
	 *  @param y The vertical component (i.e. which row)
	 *  @return value of given bit in matrix
	 */
	public boolean get(int x, int y) {
	}

	/**
	 *  <p>
	 *  Sets the given bit to true.
	 *  </p>
	 * 
	 *  @param x The horizontal component (i.e. which column)
	 *  @param y The vertical component (i.e. which row)
	 */
	public void set(int x, int y) {
	}

	public void unset(int x, int y) {
	}

	/**
	 *  <p>
	 *  Flips the given bit.
	 *  </p>
	 * 
	 *  @param x The horizontal component (i.e. which column)
	 *  @param y The vertical component (i.e. which row)
	 */
	public void flip(int x, int y) {
	}

	/**
	 *  Exclusive-or (XOR): Flip the bit in this {@code BitMatrix} if the
	 *  corresponding mask bit is set.
	 * 
	 *  @param mask XOR mask
	 */
	public void xor(BitMatrix mask) {
	}

	/**
	 *  Clears all bits (sets to false).
	 */
	public void clear() {
	}

	/**
	 *  <p>
	 *  Sets a square region of the bit matrix to true.
	 *  </p>
	 * 
	 *  @param left The horizontal position to begin at (inclusive)
	 *  @param top The vertical position to begin at (inclusive)
	 *  @param width The width of the region
	 *  @param height The height of the region
	 */
	public void setRegion(int left, int top, int width, int height) {
	}

	/**
	 *  A fast method to retrieve one row of data from the matrix as a BitArray.
	 * 
	 *  @param y The row to retrieve
	 *  @param row An optional caller-allocated BitArray, will be allocated if
	 *  null or too small
	 *  @return The resulting BitArray - this reference should always be used
	 *  even when passing your own row
	 */
	public BitArray getRow(int y, BitArray row) {
	}

	/**
	 *  @param y row to set
	 *  @param row {@link BitArray} to copy from
	 */
	public void setRow(int y, BitArray row) {
	}

	/**
	 *  Modifies this {@code BitMatrix} to represent the same but rotated 180
	 *  degrees
	 */
	public void rotate180() {
	}

	/**
	 *  This is useful in detecting the enclosing rectangle of a 'pure' barcode.
	 * 
	 *  @return {@code left,top,width,height} enclosing rectangle of all 1 bits,
	 *  or null if it is all white
	 */
	public int[] getEnclosingRectangle() {
	}

	/**
	 *  This is useful in detecting a corner of a 'pure' barcode.
	 * 
	 *  @return {@code x,y} coordinate of top-left-most 1 bit, or null if it is
	 *  all white
	 */
	public int[] getTopLeftOnBit() {
	}

	public int[] getBottomRightOnBit() {
	}

	/**
	 *  @return The width of the matrix
	 */
	public int getWidth() {
	}

	/**
	 *  @return The height of the matrix
	 */
	public int getHeight() {
	}

	/**
	 *  @return The row size of the matrix
	 */
	public int getRowSize() {
	}

	@java.lang.Override
	public boolean equals(Object o) {
	}

	@java.lang.Override
	public int hashCode() {
	}

	/**
	 *  @return string representation using "X" for set and " " for unset bits
	 */
	@java.lang.Override
	public String toString() {
	}

	/**
	 *  @param setString representation of a set bit
	 *  @param unsetString representation of an unset bit
	 *  @return string representation of entire matrix utilizing given strings
	 */
	public String toString(String setString, String unsetString) {
	}

	/**
	 *  @param setString representation of a set bit
	 *  @param unsetString representation of an unset bit
	 *  @param lineSeparator newline character in string representation
	 *  @return string representation of entire matrix utilizing given strings
	 *  and line separator
	 *  @deprecated call {@link #toString(String,String)} only, which uses \n
	 *  line separator always
	 */
	@java.lang.Deprecated
	public String toString(String setString, String unsetString, String lineSeparator) {
	}

	/**
	 *  CN1 does not support cloning. For implementation only
	 * 
	 *  @return
	 */
	public BitMatrix clone() {
	}

	/**
	 *  CN1 compatibility...
	 * 
	 *  @return Image representing the Bitmap, minimum size
	 */
	public com.codename1.ui.Image toImage() {
	}
}
