package com.google.zxing.client.result;


/**
 *  <p>Parses a WIFI configuration string. Strings will be of the form:</p>
 * 
 *  <p>{@code WIFI:T:[network type];S:[network SSID];P:[network password];H:[hidden?];;}</p>
 * 
 *  <p>For WPA2 enterprise (EAP), strings will be of the form:</p>
 * 
 *  <p>{@code WIFI:T:WPA2-EAP;S:[network SSID];H:[hidden?];E:[EAP method];H:[Phase 2 method];A:[anonymous identity];I:[username];P:[password];;}</p>
 * 
 *  <p>"EAP method" can e.g. be "TTLS" or "PWD" or one of the other fields in <a href="https://developer.android.com/reference/android/net/wifi/WifiEnterpriseConfig.Eap.html">WifiEnterpriseConfig.Eap</a> and "Phase 2 method" can e.g. be "MSCHAPV2" or any of the other fields in <a href="https://developer.android.com/reference/android/net/wifi/WifiEnterpriseConfig.Phase2.html">WifiEnterpriseConfig.Phase2</a></p>
 * 
 *  <p>The fields can appear in any order. Only "S:" is required.</p>
 * 
 *  @author Vikram Aggarwal
 *  @author Sean Owen
 *  @author Steffen Kieß
 */
public final class WifiResultParser extends ResultParser {

	public WifiResultParser() {
	}

	@java.lang.Override
	public WifiParsedResult parse(com.google.zxing.Result result) {
	}
}
