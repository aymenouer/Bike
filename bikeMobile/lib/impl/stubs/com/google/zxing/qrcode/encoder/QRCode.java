package com.google.zxing.qrcode.encoder;


/**
 *  @author satorux@google.com (Satoru Takabayashi) - creator
 *  @author dswitkin@google.com (Daniel Switkin) - ported from C++
 */
public final class QRCode {

	public static final int NUM_MASK_PATTERNS = 8;

	public QRCode() {
	}

	public com.google.zxing.qrcode.decoder.Mode getMode() {
	}

	public com.google.zxing.qrcode.decoder.ErrorCorrectionLevel getECLevel() {
	}

	public com.google.zxing.qrcode.decoder.Version getVersion() {
	}

	public int getMaskPattern() {
	}

	public ByteMatrix getMatrix() {
	}

	@java.lang.Override
	public String toString() {
	}

	public void setMode(com.google.zxing.qrcode.decoder.Mode value) {
	}

	public void setECLevel(com.google.zxing.qrcode.decoder.ErrorCorrectionLevel value) {
	}

	public void setVersion(com.google.zxing.qrcode.decoder.Version version) {
	}

	public void setMaskPattern(int value) {
	}

	public void setMatrix(ByteMatrix value) {
	}

	public static boolean isValidMaskPattern(int maskPattern) {
	}
}
