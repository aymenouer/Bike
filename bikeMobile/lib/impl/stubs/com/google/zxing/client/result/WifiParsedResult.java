package com.google.zxing.client.result;


/**
 *  Represents a parsed result that encodes wifi network information, like SSID and password.
 * 
 *  @author Vikram Aggarwal
 */
public final class WifiParsedResult extends ParsedResult {

	public WifiParsedResult(String networkEncryption, String ssid, String password) {
	}

	public WifiParsedResult(String networkEncryption, String ssid, String password, boolean hidden) {
	}

	public WifiParsedResult(String networkEncryption, String ssid, String password, boolean hidden, String identity, String anonymousIdentity, String eapMethod, String phase2Method) {
	}

	public String getSsid() {
	}

	public String getNetworkEncryption() {
	}

	public String getPassword() {
	}

	public boolean isHidden() {
	}

	public String getIdentity() {
	}

	public String getAnonymousIdentity() {
	}

	public String getEapMethod() {
	}

	public String getPhase2Method() {
	}

	@java.lang.Override
	public String getDisplayResult() {
	}
}
