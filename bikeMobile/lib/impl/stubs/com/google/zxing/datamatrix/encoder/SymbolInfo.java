package com.google.zxing.datamatrix.encoder;


/**
 *  Symbol info table for DataMatrix.
 * 
 *  @version $Id$
 */
public class SymbolInfo {

	public final int matrixWidth;

	public final int matrixHeight;

	public SymbolInfo(boolean rectangular, int dataCapacity, int errorCodewords, int matrixWidth, int matrixHeight, int dataRegions) {
	}

	/**
	 *  Overrides the symbol info set used by this class. Used for testing purposes.
	 * 
	 *  @param override the symbol info set to use
	 */
	public static void overrideSymbolSet(SymbolInfo[] override) {
	}

	public static SymbolInfo lookup(int dataCodewords) {
	}

	public static SymbolInfo lookup(int dataCodewords, SymbolShapeHint shape) {
	}

	public static SymbolInfo lookup(int dataCodewords, boolean allowRectangular, boolean fail) {
	}

	public static SymbolInfo lookup(int dataCodewords, SymbolShapeHint shape, com.google.zxing.Dimension minSize, com.google.zxing.Dimension maxSize, boolean fail) {
	}

	public final int getSymbolDataWidth() {
	}

	public final int getSymbolDataHeight() {
	}

	public final int getSymbolWidth() {
	}

	public final int getSymbolHeight() {
	}

	public int getCodewordCount() {
	}

	public int getInterleavedBlockCount() {
	}

	public final int getDataCapacity() {
	}

	public final int getErrorCodewords() {
	}

	public int getDataLengthForInterleavedBlock(int index) {
	}

	public final int getErrorLengthForInterleavedBlock(int index) {
	}

	@java.lang.Override
	public final String toString() {
	}
}
