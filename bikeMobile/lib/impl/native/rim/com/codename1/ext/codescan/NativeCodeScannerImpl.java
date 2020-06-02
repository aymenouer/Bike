package com.codename1.ext.codescan;

public class NativeCodeScannerImpl {
    public void scanQRCode() {
        new CodeScannerImpl(new MultimediaManager()).scanQRCode(new ScanResult() {

            public void scanCompleted(String contents, String formatName, byte[] rawBytes) {
                CodeScanner.scanCompletedCallback(contents, formatName, rawBytes);
            }

            public void scanCanceled() {
                CodeScanner.scanCanceledCallback();
            }

            public void scanError(int errorCode, String message) {
                CodeScanner.scanErrorCallback(errorCode, message);
            }
            
        });
    }

    public void scanBarCode() {
        new CodeScannerImpl(new MultimediaManager()).scanBarCode(new ScanResult() {

            public void scanCompleted(String contents, String formatName, byte[] rawBytes) {
                CodeScanner.scanCompletedCallback(contents, formatName, rawBytes);
            }

            public void scanCanceled() {
                CodeScanner.scanCanceledCallback();
            }

            public void scanError(int errorCode, String message) {
                CodeScanner.scanErrorCallback(errorCode, message);
            }
            
        });
    }

    public boolean isSupported() {
        return true;
    }

}
