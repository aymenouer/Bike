package com.google.zxing;


/**
 *  Enumerates barcode formats known to this package. Please keep alphabetized.
 * 
 *  @author Sean Owen
 */
public final class BarcodeFormat extends Enum {

	/**
	 * Aztec 2D barcode format. 
	 */
	public static final BarcodeFormat AZTEC;

	/**
	 * CODABAR 1D format. 
	 */
	public static final BarcodeFormat CODABAR;

	/**
	 * Code 39 1D format. 
	 */
	public static final BarcodeFormat CODE_39;

	/**
	 * Code 93 1D format. 
	 */
	public static final BarcodeFormat CODE_93;

	/**
	 * Code 128 1D format. 
	 */
	public static final BarcodeFormat CODE_128;

	/**
	 * Data Matrix 2D barcode format. 
	 */
	public static final BarcodeFormat DATA_MATRIX;

	/**
	 * EAN-8 1D format. 
	 */
	public static final BarcodeFormat EAN_8;

	/**
	 * EAN-13 1D format. 
	 */
	public static final BarcodeFormat EAN_13;

	/**
	 * ITF (Interleaved Two of Five) 1D format. 
	 */
	public static final BarcodeFormat ITF;

	/**
	 * MaxiCode 2D barcode format. 
	 */
	public static final BarcodeFormat MAXICODE;

	/**
	 * PDF417 format. 
	 */
	public static final BarcodeFormat PDF_417;

	/**
	 * QR Code 2D barcode format. 
	 */
	public static final BarcodeFormat QR_CODE;

	/**
	 * RSS 14 
	 */
	public static final BarcodeFormat RSS_14;

	/**
	 * RSS EXPANDED 
	 */
	public static final BarcodeFormat RSS_EXPANDED;

	/**
	 * UPC-A 1D format. 
	 */
	public static final BarcodeFormat UPC_A;

	/**
	 * UPC-E 1D format. 
	 */
	public static final BarcodeFormat UPC_E;

	/**
	 * UPC/EAN extension format. Not a stand-alone format. 
	 */
	public static final BarcodeFormat UPC_EAN_EXTENSION;

	public static BarcodeFormat[] values() {
	}

	public static BarcodeFormat valueOf(String name) {
	}
}
