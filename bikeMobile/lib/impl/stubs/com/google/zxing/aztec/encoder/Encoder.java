package com.google.zxing.aztec.encoder;


/**
 *  Generates Aztec 2D barcodes.
 * 
 *  @author Rustam Abdullaev
 */
public final class Encoder {

	public static final int DEFAULT_EC_PERCENT = 33;

	public static final int DEFAULT_AZTEC_LAYERS = 0;

	/**
	 *  Encodes the given binary content as an Aztec symbol
	 * 
	 *  @param data input data string
	 *  @return Aztec symbol matrix with metadata
	 */
	public static AztecCode encode(byte[] data) {
	}

	/**
	 *  Encodes the given binary content as an Aztec symbol
	 * 
	 *  @param data input data string
	 *  @param minECCPercent minimal percentage of error check words (According to ISO/IEC 24778:2008,
	 *                       a minimum of 23% + 3 words is recommended)
	 *  @param userSpecifiedLayers if non-zero, a user-specified value for the number of layers
	 *  @return Aztec symbol matrix with metadata
	 */
	public static AztecCode encode(byte[] data, int minECCPercent, int userSpecifiedLayers) {
	}
}
