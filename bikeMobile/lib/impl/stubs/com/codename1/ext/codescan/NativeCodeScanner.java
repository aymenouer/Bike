/**
 * 
 *         <p>
 *             Bar/QR code scanning API, currently based on the zxing implementation
 *         </p>
 *     
 */
package com.codename1.ext.codescan;


/**
 * 
 *  @author shannah
 */
public interface NativeCodeScanner extends com.codename1.system.NativeInterface {

	public void scanQRCode();

	public void scanBarCode();
}
