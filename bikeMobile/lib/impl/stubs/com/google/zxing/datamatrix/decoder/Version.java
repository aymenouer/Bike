package com.google.zxing.datamatrix.decoder;


/**
 *  The Version object encapsulates attributes about a particular
 *  size Data Matrix Code.
 * 
 *  @author bbrown@google.com (Brian Brown)
 */
public final class Version {

	public int getVersionNumber() {
	}

	public int getSymbolSizeRows() {
	}

	public int getSymbolSizeColumns() {
	}

	public int getDataRegionSizeRows() {
	}

	public int getDataRegionSizeColumns() {
	}

	public int getTotalCodewords() {
	}

	/**
	 *  <p>Deduces version information from Data Matrix dimensions.</p>
	 * 
	 *  @param numRows Number of rows in modules
	 *  @param numColumns Number of columns in modules
	 *  @return Version for a Data Matrix Code of those dimensions
	 *  @throws FormatException if dimensions do correspond to a valid Data Matrix size
	 */
	public static Version getVersionForDimensions(int numRows, int numColumns) {
	}

	@java.lang.Override
	public String toString() {
	}
}
