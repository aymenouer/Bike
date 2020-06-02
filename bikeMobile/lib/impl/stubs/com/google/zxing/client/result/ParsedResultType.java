package com.google.zxing.client.result;


/**
 *  Represents the type of data encoded by a barcode -- from plain text, to a
 *  URI, to an e-mail address, etc.
 * 
 *  @author Sean Owen
 */
public final class ParsedResultType extends Enum {

	public static final ParsedResultType ADDRESSBOOK;

	public static final ParsedResultType EMAIL_ADDRESS;

	public static final ParsedResultType PRODUCT;

	public static final ParsedResultType URI;

	public static final ParsedResultType TEXT;

	public static final ParsedResultType GEO;

	public static final ParsedResultType TEL;

	public static final ParsedResultType SMS;

	public static final ParsedResultType CALENDAR;

	public static final ParsedResultType WIFI;

	public static final ParsedResultType ISBN;

	public static final ParsedResultType VIN;

	public static ParsedResultType[] values() {
	}

	public static ParsedResultType valueOf(String name) {
	}
}
