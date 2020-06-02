package com.google.zxing.qrcode.encoder;


/**
 *  @author satorux@google.com (Satoru Takabayashi) - creator
 *  @author dswitkin@google.com (Daniel Switkin) - ported from C++
 */
public final class Encoder {

	/**
	 *  @param content text to encode
	 *  @param ecLevel error correction level to use
	 *  @return {@link QRCode} representing the encoded QR code
	 *  @throws WriterException if encoding can't succeed, because of for example invalid content
	 *    or configuration
	 */
	public static QRCode encode(String content, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel ecLevel) {
	}

	public static QRCode encode(String content, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel ecLevel, java.util.Map hints) {
	}

	public static com.google.zxing.qrcode.decoder.Mode chooseMode(String content) {
	}
}
