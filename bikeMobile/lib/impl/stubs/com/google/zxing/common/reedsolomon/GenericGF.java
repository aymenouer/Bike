package com.google.zxing.common.reedsolomon;


/**
 *  <p>This class contains utility methods for performing mathematical operations over
 *  the Galois Fields. Operations use a given primitive polynomial in calculations.</p>
 * 
 *  <p>Throughout this package, elements of the GF are represented as an {@code int}
 *  for convenience and speed (but at the cost of memory).
 *  </p>
 * 
 *  @author Sean Owen
 *  @author David Olivier
 */
public final class GenericGF {

	public static final GenericGF AZTEC_DATA_12;

	public static final GenericGF AZTEC_DATA_10;

	public static final GenericGF AZTEC_DATA_6;

	public static final GenericGF AZTEC_PARAM;

	public static final GenericGF QR_CODE_FIELD_256;

	public static final GenericGF DATA_MATRIX_FIELD_256;

	public static final GenericGF AZTEC_DATA_8;

	public static final GenericGF MAXICODE_FIELD_64;

	/**
	 *  Create a representation of GF(size) using the given primitive polynomial.
	 * 
	 *  @param primitive irreducible polynomial whose coefficients are represented by
	 *   the bits of an int, where the least-significant bit represents the constant
	 *   coefficient
	 *  @param size the size of the field
	 *  @param b the factor b in the generator polynomial can be 0- or 1-based
	 *   (g(x) = (x+a^b)(x+a^(b+1))...(x+a^(b+2t-1))).
	 *   In most cases it should be 1, but for QR code it is 0.
	 */
	public GenericGF(int primitive, int size, int b) {
	}

	public int getSize() {
	}

	public int getGeneratorBase() {
	}

	@java.lang.Override
	public String toString() {
	}
}
