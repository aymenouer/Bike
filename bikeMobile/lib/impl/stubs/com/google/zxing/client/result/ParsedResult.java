package com.google.zxing.client.result;


/**
 *  <p>Abstract class representing the result of decoding a barcode, as more than
 *  a String -- as some type of structured data. This might be a subclass which represents
 *  a URL, or an e-mail address. {@link ResultParser#parseResult(com.google.zxing.Result)} will turn a raw
 *  decoded string into the most appropriate type of structured representation.</p>
 * 
 *  <p>Thanks to Jeff Griffin for proposing rewrite of these classes that relies less
 *  on exception-based mechanisms during parsing.</p>
 * 
 *  @author Sean Owen
 */
public abstract class ParsedResult {

	protected ParsedResult(ParsedResultType type) {
	}

	public final ParsedResultType getType() {
	}

	public abstract String getDisplayResult() {
	}

	@java.lang.Override
	public final String toString() {
	}

	public static void maybeAppend(String value, StringBuilder result) {
	}

	public static void maybeAppend(String[] values, StringBuilder result) {
	}
}
