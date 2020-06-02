/**
 * 
 *         <p>
 *             Bar/QR code scanning API, currently based on the zxing implementation
 *         </p>
 *     
 */
package com.codename1.ext.codescan;


/**
 *  Callback for the code scanner indicating the result of a scan operation,
 *  the methods of this call will always be invoked on the EDT!
 * 
 *  @author Shai Almog
 */
public interface ScanResult {

	/**
	 *  Called upon a successful scan operation
	 *  
	 *  @param contents the contents of the data
	 *  @param formatName the format of the scan
	 *  @param rawBytes the bytes of data
	 */
	public void scanCompleted(String contents, String formatName, byte[] rawBytes);

	/**
	 *  Invoked if the user canceled the scan
	 */
	public void scanCanceled();

	/**
	 *  Invoked if an error occurred during the scanning process
	 *  
	 *  @param errorCode code
	 *  @param message descriptive message
	 */
	public void scanError(int errorCode, String message);
}
