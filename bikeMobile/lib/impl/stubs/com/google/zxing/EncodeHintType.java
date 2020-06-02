package com.google.zxing;


/**
 *  These are a set of hints that you may pass to Writers to specify their behavior.
 * 
 *  @author dswitkin@google.com (Daniel Switkin)
 */
public final class EncodeHintType extends Enum {

	/**
	 *  Specifies what degree of error correction to use, for example in QR Codes.
	 *  Type depends on the encoder. For example for QR codes it's type
	 *  {@link com.google.zxing.qrcode.decoder.ErrorCorrectionLevel ErrorCorrectionLevel}.
	 *  For Aztec it is of type {@link Integer}, representing the minimal percentage of error correction words.
	 *  For PDF417 it is of type {@link Integer}, valid values being 0 to 8.
	 *  In all cases, it can also be a {@link String} representation of the desired value as well.
	 *  Note: an Aztec symbol should have a minimum of 25% EC words.
	 */
	public static final EncodeHintType ERROR_CORRECTION;

	/**
	 *  Specifies what character encoding to use where applicable (type {@link String})
	 */
	public static final EncodeHintType CHARACTER_SET;

	/**
	 *  Specifies the matrix shape for Data Matrix (type {@link com.google.zxing.datamatrix.encoder.SymbolShapeHint})
	 */
	public static final EncodeHintType DATA_MATRIX_SHAPE;

	/**
	 *  Specifies a minimum barcode size (type {@link Dimension}). Only applicable to Data Matrix now.
	 * 
	 *  @deprecated use width/height params in
	 *  {@link com.google.zxing.datamatrix.DataMatrixWriter#encode(String, BarcodeFormat, int, int)}
	 */
	@java.lang.Deprecated
	public static final EncodeHintType MIN_SIZE;

	/**
	 *  Specifies a maximum barcode size (type {@link Dimension}). Only applicable to Data Matrix now.
	 * 
	 *  @deprecated without replacement
	 */
	@java.lang.Deprecated
	public static final EncodeHintType MAX_SIZE;

	/**
	 *  Specifies margin, in pixels, to use when generating the barcode. The meaning can vary
	 *  by format; for example it controls margin before and after the barcode horizontally for
	 *  most 1D formats. (Type {@link Integer}, or {@link String} representation of the integer value).
	 */
	public static final EncodeHintType MARGIN;

	/**
	 *  Specifies whether to use compact mode for PDF417 (type {@link Boolean}, or "true" or "false"
	 *  {@link String} value).
	 */
	public static final EncodeHintType PDF417_COMPACT;

	/**
	 *  Specifies what compaction mode to use for PDF417 (type
	 *  {@link com.google.zxing.pdf417.encoder.Compaction Compaction} or {@link String} value of one of its
	 *  enum values).
	 */
	public static final EncodeHintType PDF417_COMPACTION;

	/**
	 *  Specifies the minimum and maximum number of rows and columns for PDF417 (type
	 *  {@link com.google.zxing.pdf417.encoder.Dimensions Dimensions}).
	 */
	public static final EncodeHintType PDF417_DIMENSIONS;

	/**
	 *  Specifies the required number of layers for an Aztec code.
	 *  A negative number (-1, -2, -3, -4) specifies a compact Aztec code.
	 *  0 indicates to use the minimum number of layers (the default).
	 *  A positive number (1, 2, .. 32) specifies a normal (non-compact) Aztec code.
	 *  (Type {@link Integer}, or {@link String} representation of the integer value).
	 */
	public static final EncodeHintType AZTEC_LAYERS;

	/**
	 *  Specifies the exact version of QR code to be encoded.
	 *  (Type {@link Integer}, or {@link String} representation of the integer value).
	 */
	public static final EncodeHintType QR_VERSION;

	/**
	 *  Specifies the QR code mask pattern to be used. Allowed values are
	 *  0..QRCode.NUM_MASK_PATTERNS-1. By default the code will automatically select
	 *  the optimal mask pattern.
	 *  * (Type {@link Integer}, or {@link String} representation of the integer value).
	 */
	public static final EncodeHintType QR_MASK_PATTERN;

	/**
	 *  Specifies whether the data should be encoded to the GS1 standard (type {@link Boolean}, or "true" or "false"
	 *  {@link String } value).
	 */
	public static final EncodeHintType GS1_FORMAT;

	public static EncodeHintType[] values() {
	}

	public static EncodeHintType valueOf(String name) {
	}
}
