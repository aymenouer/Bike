package com.google.zxing.common;


/**
 *  Common string-related functions.
 * 
 *  @author Sean Owen
 *  @author Alex Dupre
 */
public final class StringUtils {

	public static final String SHIFT_JIS = "SJIS";

	public static final String GB2312 = "GB2312";

	public static final String EUC_JP = "EUC_JP";

	public static final String UTF8 = "UTF8";

	public static final String ISO88591 = "ISO8859_1";

	/**
	 *  @param bytes bytes encoding a string, whose encoding should be guessed
	 *  @param hints decode hints if applicable
	 *  @return name of guessed encoding; at the moment will only guess one of:
	 *          {@link #SHIFT_JIS}, {@link #UTF8}, {@link #ISO88591}, or the platform
	 *          default encoding if none of these can possibly be correct
	 */
	public static String guessEncoding(byte[] bytes, java.util.Map hints) {
	}
}
