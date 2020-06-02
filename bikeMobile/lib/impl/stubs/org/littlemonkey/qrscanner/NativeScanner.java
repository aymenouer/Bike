package org.littlemonkey.qrscanner;


/**
 * 
 *  @author nick
 */
public interface NativeScanner extends com.codename1.system.NativeInterface {

	/**
	 *  Scans based on the settings in this class and returns the results
	 */
	public void scanQRCode();

	/**
	 *  Scans based on the settings in this class and returns the results
	 */
	public void scanBarCode();
}
