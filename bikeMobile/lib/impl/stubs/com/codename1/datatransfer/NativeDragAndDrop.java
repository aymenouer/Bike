package com.codename1.datatransfer;


/**
 * 
 *  @author shannah
 */
public interface NativeDragAndDrop extends com.codename1.system.NativeInterface {

	/**
	 *  Starts the native drop listener on the application's canvas
	 */
	public void startGlobalDropListener();

	/**
	 *  Stops teh native drop listener on the application's canvas.
	 */
	public void stopGlobalDropListener();
}
