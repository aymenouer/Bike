package org.littlemonkey.qrscanner;


/**
 *  QRScanner uses built in ZBar on Android
 *  Falls back to CodeScanner on other platforms where supported
 *  
 *  Removes dependence on a third party scanner being installed.
 *  
 *  Needs additional build hints:
 *  
 *  android.xapplication = <activity android:name="com.dm.zbar.android.scanner.ZBarScannerActivity" android:screenOrientation="landscape" android:label="@string/app_name" />
 *  android.xpermissions = <uses-permission android:name="android.permission.CAMERA"/><uses-feature android:name="android.hardware.camera" android:required="false"/>
 *  
 *  And for release builds (only)
 *  android.proguardKeep =  -keep class net.sourceforge.zbar.** {*;}  
 *  
 *  NOTE THERE NEEDS TO BE A SPACE BEFORE AND AFTER THIS VALUE
 *  
 *  @see CodeScanner
 *  
 *  @author nick
 */
public class QRScanner {

	public QRScanner() {
	}

	/**
	 *  Scan a QR Code with callback
	 *  
	 *  @param callback 
	 */
	public static void scanQRCode(com.codename1.ext.codescan.ScanResult callback) {
	}

	/**
	 *  Scan a barcode with callback,
	 *  Currently scans for EAN13 codes on Android.
	 *  Change this in the native implementation.
	 *  
	 *  @param callback 
	 */
	public static void scanBarCode(com.codename1.ext.codescan.ScanResult callback) {
	}

	public static com.codename1.ext.codescan.ScanResult getCallback() {
	}
}
