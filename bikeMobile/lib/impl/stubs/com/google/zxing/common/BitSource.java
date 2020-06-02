package com.google.zxing.common;


/**
 *  <p>This provides an easy abstraction to read bits at a time from a sequence of bytes, where the
 *  number of bits read is not often a multiple of 8.</p>
 * 
 *  <p>This class is thread-safe but not reentrant -- unless the caller modifies the bytes array
 *  it passed in, in which case all bets are off.</p>
 * 
 *  @author Sean Owen
 */
public final class BitSource {

	/**
	 *  @param bytes bytes from which this will read bits. Bits will be read from the first byte first.
	 *  Bits are read within a byte from most-significant to least-significant bit.
	 */
	public BitSource(byte[] bytes) {
	}

	/**
	 *  @return index of next bit in current byte which would be read by the next call to {@link #readBits(int)}.
	 */
	public int getBitOffset() {
	}

	/**
	 *  @return index of next byte in input byte array which would be read by the next call to {@link #readBits(int)}.
	 */
	public int getByteOffset() {
	}

	/**
	 *  @param numBits number of bits to read
	 *  @return int representing the bits read. The bits will appear as the least-significant
	 *          bits of the int
	 *  @throws IllegalArgumentException if numBits isn't in [1,32] or more than is available
	 */
	public int readBits(int numBits) {
	}

	/**
	 *  @return number of bits that can be read successfully
	 */
	public int available() {
	}
}
