package com.codename1.datatransfer;


/**
 *  Provides drag and drop support for dragging files from the native environment (e.g. Desktop) into the app.
 *  
 *  <p>The API is designed to be similar to the <a href="https://www.codenameone.com/javadoc/com/codename1/ui/Display.html#openGallery-com.codename1.ui.events.ActionListener-int-">Display.openGallery() API</a>.</p>
 *  
 *  <h3>Usage</h3>
 *  <p>See <a href="https://github.com/shannah/cn1-native-data-transfer/blob/master/DNDDemo/src/com/codename1/demos/dnd/DNDDemo.java">DNDDemo</a></p>
 *  @author shannah
 */
public class DropTarget {

	/**
	 *  Registers a listener to accept drop events onto the app.
	 *  @param response Listener called when files (of the allowed type) is dropped on the app.  The {@code source} will be a {@code String} with the path to a 
	 *  temporary file in FileSystemStorage.  The {@code x} and {@code y} coordinates are the absolute coordinates within the application window where the drop occurred.
	 *  @param type The types of files that are accepted by this drop target.  One of {@code Display.GALLERY_IMAGE}, {@code Display.GALLERY_VIDEO}, and {@code Display.GALLERY_ALL}.
	 *  @return The {@link DropTarget} object that was created.  You can use this to manipulate the target later.
	 */
	public static DropTarget create(com.codename1.ui.events.ActionListener response, int type) {
	}

	/**
	 *  Restarts drag and drop after if has been stopped.
	 */
	public void start() {
	}

	/**
	 *  Disables the drop target so that it is no longer accepting file drops.
	 */
	public void stop() {
	}

	/**
	 *  Called from native code to fire the actual drop event.
	 *  @param x
	 *  @param y
	 *  @param filePath 
	 */
	public static void fireDropEvent(int x, int y, String filePath) {
	}

	/**
	 *  Check whether drag and drop is supported on the current platform.
	 *  @return 
	 */
	public static boolean isSupported() {
	}

	/**
	 *  Used by native code.  Checks to see if a particular type is currently being accepted by ANY active DropTarget.
	 *  @param type One of {@code Display.GALLERY_IMAGE}, {@code Display.GALLERY_VIDEO}, {@code Display.GALLERY_ALL}.
	 *  @return True if there are ANY active drop targets accepting the specified type of file.
	 */
	public static boolean isTypeAccepted(int type) {
	}

	/**
	 *  Used by native code.  Checks to see if there are any active drop targets that will accept the given mimetype.
	 *  @param mimetype The mimetype to check (e.g. image/jpeg).
	 *  @return True if there exists at least one active drop target that will accept files of the provided mimetype.
	 */
	public static boolean isMimetypeAccepted(String mimetype) {
	}
}
