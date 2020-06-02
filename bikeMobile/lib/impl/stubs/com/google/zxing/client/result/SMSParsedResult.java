package com.google.zxing.client.result;


/**
 *  Represents a parsed result that encodes an SMS message, including recipients, subject
 *  and body text.
 * 
 *  @author Sean Owen
 */
public final class SMSParsedResult extends ParsedResult {

	public SMSParsedResult(String number, String via, String subject, String body) {
	}

	public SMSParsedResult(String[] numbers, String[] vias, String subject, String body) {
	}

	public String getSMSURI() {
	}

	public String[] getNumbers() {
	}

	public String[] getVias() {
	}

	public String getSubject() {
	}

	public String getBody() {
	}

	@java.lang.Override
	public String getDisplayResult() {
	}
}
