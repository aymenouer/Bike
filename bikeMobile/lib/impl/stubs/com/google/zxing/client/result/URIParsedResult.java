package com.google.zxing.client.result;


/**
 *  A simple result type encapsulating a URI that has no further interpretation.
 * 
 *  @author Sean Owen
 */
public final class URIParsedResult extends ParsedResult {

	public URIParsedResult(String uri, String title) {
	}

	public String getURI() {
	}

	public String getTitle() {
	}

	/**
	 *  @return true if the URI contains suspicious patterns that may suggest it intends to
	 *   mislead the user about its true nature
	 *  @deprecated see {@link URIResultParser#isPossiblyMaliciousURI(String)}
	 */
	@java.lang.Deprecated
	public boolean isPossiblyMaliciousURI() {
	}

	@java.lang.Override
	public String getDisplayResult() {
	}
}
