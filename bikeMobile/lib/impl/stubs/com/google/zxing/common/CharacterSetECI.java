package com.google.zxing.common;


/**
 *  Encapsulates a Character Set ECI, according to "Extended Channel Interpretations" 5.3.1.1
 *  of ISO 18004.
 * 
 *  @author Sean Owen
 */
public final class CharacterSetECI extends Enum {

	public static final CharacterSetECI Cp437;

	public static final CharacterSetECI ISO8859_1;

	public static final CharacterSetECI ISO8859_2;

	public static final CharacterSetECI ISO8859_3;

	public static final CharacterSetECI ISO8859_4;

	public static final CharacterSetECI ISO8859_5;

	public static final CharacterSetECI ISO8859_6;

	public static final CharacterSetECI ISO8859_7;

	public static final CharacterSetECI ISO8859_8;

	public static final CharacterSetECI ISO8859_9;

	public static final CharacterSetECI ISO8859_10;

	public static final CharacterSetECI ISO8859_11;

	public static final CharacterSetECI ISO8859_13;

	public static final CharacterSetECI ISO8859_14;

	public static final CharacterSetECI ISO8859_15;

	public static final CharacterSetECI ISO8859_16;

	public static final CharacterSetECI SJIS;

	public static final CharacterSetECI Cp1250;

	public static final CharacterSetECI Cp1251;

	public static final CharacterSetECI Cp1252;

	public static final CharacterSetECI Cp1256;

	public static final CharacterSetECI UnicodeBigUnmarked;

	public static final CharacterSetECI UTF8;

	public static final CharacterSetECI ASCII;

	public static final CharacterSetECI Big5;

	public static final CharacterSetECI GB18030;

	public static final CharacterSetECI EUC_KR;

	public static CharacterSetECI[] values() {
	}

	public static CharacterSetECI valueOf(String name) {
	}

	public int getValue() {
	}

	/**
	 *  @param value character set ECI value
	 *  @return {@code CharacterSetECI} representing ECI of given value, or null if it is legal but
	 *    unsupported
	 *  @throws FormatException if ECI value is invalid
	 */
	public static CharacterSetECI getCharacterSetECIByValue(int value) {
	}

	/**
	 *  @param name character set ECI encoding name
	 *  @return CharacterSetECI representing ECI for character encoding, or null if it is legal
	 *    but unsupported
	 */
	public static CharacterSetECI getCharacterSetECIByName(String name) {
	}
}
