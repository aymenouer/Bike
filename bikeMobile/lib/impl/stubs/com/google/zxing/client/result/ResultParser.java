package com.google.zxing.client.result;


/**
 *  <p>
 *  Abstract class representing the result of decoding a barcode, as more than a
 *  String -- as some type of structured data. This might be a subclass which
 *  represents a URL, or an e-mail address. {@link #parseResult(Result)} will
 *  turn a raw decoded string into the most appropriate type of structured
 *  representation.
 *  </p>
 * 
 *  <p>
 *  Thanks to Jeff Griffin for proposing rewrite of these classes that relies
 *  less on exception-based mechanisms during parsing.
 *  </p>
 * 
 *  @author Sean Owen
 */
public abstract class ResultParser {

	public ResultParser() {
	}

	/**
	 *  Attempts to parse the raw {@link Result}'s contents as a particular type of
	 *  information (email, URL, etc.) and return a {@link ParsedResult}
	 *  encapsulating the result of parsing.
	 * 
	 *  @param theResult the raw {@link Result} to parse
	 *  @return {@link ParsedResult} encapsulating the parsing result
	 */
	public abstract ParsedResult parse(com.google.zxing.Result theResult) {
	}

	protected static String getMassagedText(com.google.zxing.Result result) {
	}

	public static ParsedResult parseResult(com.google.zxing.Result theResult) {
	}

	protected static void maybeAppend(String value, StringBuilder result) {
	}

	protected static void maybeAppend(String[] value, StringBuilder result) {
	}

	protected static String[] maybeWrap(String value) {
	}

	protected static String unescapeBackslash(String escaped) {
	}

	protected static int parseHexDigit(char c) {
	}

	protected static boolean isStringOfDigits(CharSequence value, int length) {
	}

	protected static boolean isSubstringOfDigits(CharSequence value, int offset, int length) {
	}
}
