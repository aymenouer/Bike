package com.google.zxing;


/**
 *  Encapsulates a type of hint that a caller may pass to a barcode reader to help it
 *  more quickly or accurately decode it. It is up to implementations to decide what,
 *  if anything, to do with the information that is supplied.
 * 
 *  @author Sean Owen
 *  @author dswitkin@google.com (Daniel Switkin)
 *  @see Reader#decode(BinaryBitmap,java.util.Map)
 */
public final class DecodeHintType extends Enum {

	/**
	 *  Unspecified, application-specific hint. Maps to an unspecified {@link Object}.
	 */
	public static final DecodeHintType OTHER;

	/**
	 *  Image is a pure monochrome image of a barcode. Doesn't matter what it maps to;
	 *  use {@link Boolean#TRUE}.
	 */
	public static final DecodeHintType PURE_BARCODE;

	/**
	 *  Image is known to be of one of a few possible formats.
	 *  Maps to a {@link List} of {@link BarcodeFormat}s.
	 */
	public static final DecodeHintType POSSIBLE_FORMATS;

	/**
	 *  Spend more time to try to find a barcode; optimize for accuracy, not speed.
	 *  Doesn't matter what it maps to; use {@link Boolean#TRUE}.
	 */
	public static final DecodeHintType TRY_HARDER;

	/**
	 *  Specifies what character encoding to use when decoding, where applicable (type String)
	 */
	public static final DecodeHintType CHARACTER_SET;

	/**
	 *  Allowed lengths of encoded data -- reject anything else. Maps to an {@code int[]}.
	 */
	public static final DecodeHintType ALLOWED_LENGTHS;

	/**
	 *  Assume Code 39 codes employ a check digit. Doesn't matter what it maps to;
	 *  use {@link Boolean#TRUE}.
	 */
	public static final DecodeHintType ASSUME_CODE_39_CHECK_DIGIT;

	/**
	 *  Assume the barcode is being processed as a GS1 barcode, and modify behavior as needed.
	 *  For example this affects FNC1 handling for Code 128 (aka GS1-128). Doesn't matter what it maps to;
	 *  use {@link Boolean#TRUE}.
	 */
	public static final DecodeHintType ASSUME_GS1;

	/**
	 *  If true, return the start and end digits in a Codabar barcode instead of stripping them. They
	 *  are alpha, whereas the rest are numeric. By default, they are stripped, but this causes them
	 *  to not be. Doesn't matter what it maps to; use {@link Boolean#TRUE}.
	 */
	public static final DecodeHintType RETURN_CODABAR_START_END;

	/**
	 *  The caller needs to be notified via callback when a possible {@link ResultPoint}
	 *  is found. Maps to a {@link ResultPointCallback}.
	 */
	public static final DecodeHintType NEED_RESULT_POINT_CALLBACK;

	/**
	 *  Allowed extension lengths for EAN or UPC barcodes. Other formats will ignore this.
	 *  Maps to an {@code int[]} of the allowed extension lengths, for example [2], [5], or [2, 5].
	 *  If it is optional to have an extension, do not set this hint. If this is set,
	 *  and a UPC or EAN barcode is found but an extension is not, then no result will be returned
	 *  at all.
	 */
	public static final DecodeHintType ALLOWED_EAN_EXTENSIONS;

	/**
	 *  Distance between finder patterns must be approximately equal to the ratio 
	 *  supplied. By default it should be somehow from top-left to top-right then 
	 *  down. Most of the codes detector does not implement this...
	 *  @since 20.08.2019
	 *  @author Roy Wang
	 */
	public static final DecodeHintType FINDER_PATTERN_DISTANCE_APPROX_RATIO;

	/**
	 *  Check for all points in finder pattern. May increase accuracy at the cost of 
	 *  performance. Default behavior is to drop connection when false pattern 
	 *  found. Doesn't matter what it maps to; use {@link Boolean#TRUE}
	 *  @since 20.08.2019
	 *  @author Roy Wang
	 */
	public static final DecodeHintType QR_CODE_CHECK_FULL_FINDER_PATTERN;

	public static DecodeHintType[] values() {
	}

	public static DecodeHintType valueOf(String name) {
	}

	public Class getValueType() {
	}
}
