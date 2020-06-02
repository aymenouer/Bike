package com.google.zxing.common;


/**
 *  <p>A simple, fast array of bits, represented compactly by an array of ints internally.</p>
 * 
 *  @author Sean Owen
 */
@java.lang.SuppressWarnings("deprecation")
public final class BitArray implements Cloneable {

	public BitArray() {
	}

	public BitArray(int size) {
	}

	public int getSize() {
	}

	public int getSizeInBytes() {
	}

	/**
	 *  @param i bit to get
	 *  @return true iff bit i is set
	 */
	public boolean get(int i) {
	}

	/**
	 *  Sets bit i.
	 * 
	 *  @param i bit to set
	 */
	public void set(int i) {
	}

	/**
	 *  Flips bit i.
	 * 
	 *  @param i bit to set
	 */
	public void flip(int i) {
	}

	/**
	 *  @param from first bit to check
	 *  @return index of first bit that is set, starting from the given index, or size if none are set
	 *   at or beyond this given index
	 *  @see #getNextUnset(int)
	 */
	public int getNextSet(int from) {
	}

	/**
	 *  @param from index to start looking for unset bit
	 *  @return index of next unset bit, or {@code size} if none are unset until the end
	 *  @see #getNextSet(int)
	 */
	public int getNextUnset(int from) {
	}

	/**
	 *  Sets a block of 32 bits, starting at bit i.
	 * 
	 *  @param i first bit to set
	 *  @param newBits the new value of the next 32 bits. Note again that the least-significant bit
	 *  corresponds to bit i, the next-least-significant to i+1, and so on.
	 */
	public void setBulk(int i, int newBits) {
	}

	/**
	 *  Sets a range of bits.
	 * 
	 *  @param start start of range, inclusive.
	 *  @param end end of range, exclusive
	 */
	public void setRange(int start, int end) {
	}

	/**
	 *  Clears all bits (sets to false).
	 */
	public void clear() {
	}

	/**
	 *  Efficient method to check if a range of bits is set, or not set.
	 * 
	 *  @param start start of range, inclusive.
	 *  @param end end of range, exclusive
	 *  @param value if true, checks that bits in range are set, otherwise checks that they are not set
	 *  @return true iff all bits are set or not set in range, according to value argument
	 *  @throws IllegalArgumentException if end is less than start or the range is not contained in the array
	 */
	public boolean isRange(int start, int end, boolean value) {
	}

	public void appendBit(boolean bit) {
	}

	/**
	 *  Appends the least-significant bits, from value, in order from most-significant to
	 *  least-significant. For example, appending 6 bits from 0x000001E will append the bits
	 *  0, 1, 1, 1, 1, 0 in that order.
	 * 
	 *  @param value {@code int} containing bits to append
	 *  @param numBits bits from value to append
	 */
	public void appendBits(int value, int numBits) {
	}

	public void appendBitArray(BitArray other) {
	}

	public void xor(BitArray other) {
	}

	/**
	 * 
	 *  @param bitOffset first bit to start writing
	 *  @param array array to write into. Bytes are written most-significant byte first. This is the opposite
	 *   of the internal representation, which is exposed by {@link #getBitArray()}
	 *  @param offset position in array to start writing
	 *  @param numBytes how many bytes to write
	 */
	public void toBytes(int bitOffset, byte[] array, int offset, int numBytes) {
	}

	/**
	 *  @return underlying array of ints. The first element holds the first 32 bits, and the least
	 *          significant bit is bit 0.
	 */
	public int[] getBitArray() {
	}

	/**
	 *  Reverses all bits in the array.
	 */
	public void reverse() {
	}

	@java.lang.Override
	public boolean equals(Object o) {
	}

	@java.lang.Override
	public int hashCode() {
	}

	@java.lang.Override
	public String toString() {
	}

	/**
	 *  CN1 does not support cloning, for implementation only
	 */
	public BitArray clone() {
	}
}
