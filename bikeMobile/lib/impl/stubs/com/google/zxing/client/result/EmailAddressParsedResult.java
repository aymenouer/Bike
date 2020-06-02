package com.google.zxing.client.result;


/**
 *  Represents a parsed result that encodes an email message including recipients, subject
 *  and body text.
 * 
 *  @author Sean Owen
 */
public final class EmailAddressParsedResult extends ParsedResult {

	/**
	 *  @return first elements of {@link #getTos()} or {@code null} if none
	 *  @deprecated use {@link #getTos()}
	 */
	@java.lang.Deprecated
	public String getEmailAddress() {
	}

	public String[] getTos() {
	}

	public String[] getCCs() {
	}

	public String[] getBCCs() {
	}

	public String getSubject() {
	}

	public String getBody() {
	}

	/**
	 *  @return "mailto:"
	 *  @deprecated without replacement
	 */
	@java.lang.Deprecated
	public String getMailtoURI() {
	}

	@java.lang.Override
	public String getDisplayResult() {
	}
}
