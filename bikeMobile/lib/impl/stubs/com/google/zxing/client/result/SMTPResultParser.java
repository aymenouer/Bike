package com.google.zxing.client.result;


/**
 *  <p>Parses an "smtp:" URI result, whose format is not standardized but appears to be like:
 *  {@code smtp[:subject[:body]]}.</p>
 * 
 *  @author Sean Owen
 */
public final class SMTPResultParser extends ResultParser {

	public SMTPResultParser() {
	}

	@java.lang.Override
	public EmailAddressParsedResult parse(com.google.zxing.Result result) {
	}
}
