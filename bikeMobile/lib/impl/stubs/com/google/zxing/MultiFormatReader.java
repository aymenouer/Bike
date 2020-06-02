package com.google.zxing;


/**
 *  MultiFormatReader is a convenience class and the main entry point into the library for most uses.
 *  By default it attempts to decode all barcode formats that the library supports. Optionally, you
 *  can provide a hints object to request different behavior, for example only decoding QR codes.
 * 
 *  @author Sean Owen
 *  @author dswitkin@google.com (Daniel Switkin)
 */
public final class MultiFormatReader implements Reader {

	public MultiFormatReader() {
	}

	/**
	 *  This version of decode honors the intent of Reader.decode(BinaryBitmap) in that it
	 *  passes null as a hint to the decoders. However, that makes it inefficient to call repeatedly.
	 *  Use setHints() followed by decodeWithState() for continuous scan applications.
	 * 
	 *  @param image The pixel data to decode
	 *  @return The contents of the image
	 *  @throws NotFoundException Any errors which occurred
	 */
	@java.lang.Override
	public Result decode(BinaryBitmap image) {
	}

	/**
	 *  Decode an image using the hints provided. Does not honor existing state.
	 * 
	 *  @param image The pixel data to decode
	 *  @param hints The hints to use, clearing the previous state.
	 *  @return The contents of the image
	 *  @throws NotFoundException Any errors which occurred
	 */
	@java.lang.Override
	public Result decode(BinaryBitmap image, java.util.Map hints) {
	}

	/**
	 *  Decode an image using the state set up by calling setHints() previously. Continuous scan
	 *  clients will get a <b>large</b> speed increase by using this instead of decode().
	 * 
	 *  @param image The pixel data to decode
	 *  @return The contents of the image
	 *  @throws NotFoundException Any errors which occurred
	 */
	public Result decodeWithState(BinaryBitmap image) {
	}

	/**
	 *  This method adds state to the MultiFormatReader. By setting the hints once, subsequent calls
	 *  to decodeWithState(image) can reuse the same set of readers without reallocating memory. This
	 *  is important for performance in continuous scan clients.
	 * 
	 *  @param hints The set of hints to use for subsequent calls to decode(image)
	 */
	public void setHints(java.util.Map hints) {
	}

	@java.lang.Override
	public void reset() {
	}
}
