package com.google.zxing.client.result;


/**
 *  Parses the "URLTO" result format, which is of the form "URLTO:[title]:[url]".
 *  This seems to be used sometimes, but I am not able to find documentation
 *  on its origin or official format?
 * 
 *  @author Sean Owen
 */
public final class URLTOResultParser extends ResultParser {

	public URLTOResultParser() {
	}

	@java.lang.Override
	public URIParsedResult parse(com.google.zxing.Result result) {
	}
}
