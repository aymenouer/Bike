/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.ext.codescan;



import com.codename1.media.Media;

/**
 *
 * @author Chen
 */
public class CodeScannerImpl {

    private BarCodeScanner bs;
    
    public CodeScannerImpl(Media recorder) {
        bs = new BarCodeScanner(recorder);
    }
    
    public void scanQRCode(ScanResult callback) {
        bs.startScaningQRcode(callback);
    }

    public void scanBarCode(ScanResult callback) {
        bs.startScaningBarCode(callback);
    }
    
}
