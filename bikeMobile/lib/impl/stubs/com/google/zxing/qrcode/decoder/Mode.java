package com.google.zxing.qrcode.decoder;


/**
 *  <p>See ISO 18004:2006, 6.4.1, Tables 2 and 3. This enum encapsulates the various modes in which
 *  data can be encoded to bits in the QR code standard.</p>
 * 
 *  @author Sean Owen
 */
public final class Mode extends Enum {

	public static final Mode TERMINATOR;

	public static final Mode NUMERIC;

	public static final Mode ALPHANUMERIC;

	public static final Mode STRUCTURED_APPEND;

	public static final Mode BYTE;

	public static final Mode ECI;

	public static final Mode KANJI;

	public static final Mode FNC1_FIRST_POSITION;

	public static final Mode FNC1_SECOND_POSITION;

	/**
	 * See GBT 18284-2000; "Hanzi" is a transliteration of this mode name. 
	 */
	public static final Mode HANZI;

	public static Mode[] values() {
	}

	public static Mode valueOf(String name) {
	}

	/**
	 *  @param bits four bits encoding a QR Code data mode
	 *  @return Mode encoded by these bits
	 *  @throws IllegalArgumentException if bits do not correspond to a known mode
	 */
	public static Mode forBits(int bits) {
	}

	/**
	 *  @param version version in question
	 *  @return number of bits used, in this QR Code symbol {@link Version}, to encode the
	 *          count of characters that will follow encoded in this Mode
	 */
	public int getCharacterCountBits(Version version) {
	}

	public int getBits() {
	}
}
