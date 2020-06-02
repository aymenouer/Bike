package com.google.zxing.oned;


/**
 *  This object renders an EAN8 code as a {@link BitMatrix}.
 * 
 *  @author aripollak@gmail.com (Ari Pollak)
 */
public final class EAN8Writer extends UPCEANWriter {

	public EAN8Writer() {
	}

	@java.lang.Override
	protected java.util.Collection getSupportedWriteFormats() {
	}

	/**
	 *  @return a byte array of horizontal pixels (false = white, true = black)
	 */
	@java.lang.Override
	public boolean[] encode(String contents) {
	}
}
