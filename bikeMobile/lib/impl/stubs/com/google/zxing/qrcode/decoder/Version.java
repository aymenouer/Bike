package com.google.zxing.qrcode.decoder;


/**
 *  See ISO 18004:2006 Annex D
 * 
 *  @author Sean Owen
 */
public final class Version {

	public int getVersionNumber() {
	}

	public int[] getAlignmentPatternCenters() {
	}

	public int getTotalCodewords() {
	}

	public int getDimensionForVersion() {
	}

	public Version.ECBlocks getECBlocksForLevel(ErrorCorrectionLevel ecLevel) {
	}

	/**
	 *  <p>Deduces version information purely from QR Code dimensions.</p>
	 * 
	 *  @param dimension dimension in modules
	 *  @return Version for a QR Code of that dimension
	 *  @throws FormatException if dimension is not 1 mod 4
	 */
	public static Version getProvisionalVersionForDimension(int dimension) {
	}

	public static Version getVersionForNumber(int versionNumber) {
	}

	@java.lang.Override
	public String toString() {
	}

	/**
	 *  <p>Encapsulates a set of error-correction blocks in one symbol version. Most versions will
	 *  use blocks of differing sizes within one version, so, this encapsulates the parameters for
	 *  each set of blocks. It also holds the number of error-correction codewords per block since it
	 *  will be the same across all blocks within one version.</p>
	 */
	public static final class ECBlocks {


		public int getECCodewordsPerBlock() {
		}

		public int getNumBlocks() {
		}

		public int getTotalECCodewords() {
		}

		public Version.ECB[] getECBlocks() {
		}
	}

	/**
	 *  <p>Encapsulates the parameters for one error-correction block in one symbol version.
	 *  This includes the number of data codewords, and the number of times a block with these
	 *  parameters is used consecutively in the QR code version's format.</p>
	 */
	public static final class ECB {


		public int getCount() {
		}

		public int getDataCodewords() {
		}
	}
}
