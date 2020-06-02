/**
 * 
 *         <p>
 *             Bar/QR code scanning API, currently based on the zxing implementation
 *         </p>
 *     
 */
package com.codename1.ext.codescan;


/**
 *  A barcode/qrcode scanner API, this class is a singleton, notice that this
 *  API might not be implemented for all platforms in which case the getInstance()
 *  method will return null!
 * 
 *  @author Shai Almog
 */
public class CodeScanner {

	public static boolean isSupported() {
	}

	/**
	 *  Returns the instance of the code scanner, notice that this method is equivalent 
	 *  to Display.getInstance().getCodeScanner().
	 *  
	 *  @return instance of the code scanner
	 */
	public static CodeScanner getInstance() {
	}

	/**
	 *  Scans based on the settings in this class and returns the results
	 *  
	 *  @param callback scan results
	 */
	public void scanQRCode(ScanResult callback) {
	}

	/**
	 *  Scans based on the settings in this class and returns the results
	 *  
	 *  @param callback scan results
	 */
	public void scanBarCode(ScanResult callback) {
	}
}
