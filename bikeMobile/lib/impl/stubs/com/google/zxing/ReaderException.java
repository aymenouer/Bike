package com.google.zxing;


/**
 *  The general exception class throw when something goes wrong during decoding
 *  of a barcode. This includes, but is not limited to, failing checksums / error
 *  correction algorithms, being unable to locate finder timing patterns, and so
 *  on.
 * 
 *  @author Sean Owen
 *  //@deprecated CN1 doesn't seems like it can manipulate stacks. Throwing
 *              exception could be expensive
 */
public abstract class ReaderException extends Exception {

	protected static final boolean isStackTrace = false;

	public final synchronized Throwable fillInStackTrace() {
	}
}
