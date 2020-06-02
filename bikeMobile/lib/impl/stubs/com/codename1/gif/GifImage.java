package com.codename1.gif;


/**
 *  <p>This is a GIF image implementation based on the code from https://gist.github.com/devunwired/4479231
 *  To use this just use  {@code Image img = GifImage.decode(inputStream, lengthOfStream); }
 *  You can then use the image as any other Codename One image. Looping, timing etc. is determined by
 *  the internal loop definitions in the GIF.</p>
 * 
 *  <p>
 *  Reads frame data from a GIF image source and decodes it into individual frames
 *  for animation purposes.  Image data can be read from either and InputStream source
 *  or a byte[].</p>
 * 
 *  This class is optimized for running animations with the frames, there
 *  are no methods to get individual frame images, only to decode the next frame in the
 *  animation sequence.  Instead, it lowers its memory footprint by only housing the minimum
 *  data necessary to decode the next frame in the animation sequence.
 * 
 *  The animation must be manually moved forward using {@link #advance()} before requesting the next
 *  frame.  This method must also be called before you request the first frame or an error will
 *  occur.
 * 
 *  Implementation adapted from sample code published in Lyons. (2004). <em>Java for Programmers</em>,
 *  republished under the MIT Open Source License
 */
public class GifImage extends com.codename1.ui.Image {

	/**
	 *  File read status: No errors.
	 */
	public static final int STATUS_OK = 0;

	/**
	 *  File read status: Error decoding file (may be partially decoded)
	 */
	public static final int STATUS_FORMAT_ERROR = 1;

	/**
	 *  File read status: Unable to open source.
	 */
	public static final int STATUS_OPEN_ERROR = 2;

	/**
	 *  max decoder pixel stack size
	 */
	protected static final int MAX_STACK_SIZE = 4096;

	/**
	 *  Global status code of GIF data parsing
	 */
	protected int status;

	protected int width;

	protected int height;

	protected boolean gctFlag;

	protected int gctSize;

	protected int loopCount;

	protected int[] gct;

	protected int[] act;

	protected int bgIndex;

	protected int bgColor;

	protected int pixelAspect;

	protected boolean lctFlag;

	protected int lctSize;

	protected GifImage.ByteBuffer rawData;

	protected byte[] block;

	protected int blockSize;

	protected short[] prefix;

	protected byte[] suffix;

	protected byte[] pixelStack;

	protected byte[] mainPixels;

	protected int[] mainScratch;

	protected int[] copyScratch;

	protected java.util.ArrayList frames;

	protected GifImage.GifFrame currentFrame;

	protected com.codename1.ui.Image previousImage;

	protected com.codename1.ui.Image currentImage;

	protected com.codename1.ui.Image renderImage;

	protected int framePointer;

	protected int frameCount;

	/**
	 *  Move the animation frame counter forward
	 */
	public void advance() {
	}

	/**
	 *  Gets display duration for specified frame.
	 * 
	 *  @param n int index of frame
	 *  @return delay in milliseconds
	 */
	public int getDelay(int n) {
	}

	/**
	 *  Gets display duration for the upcoming frame
	 */
	public int getNextDelay() {
	}

	/**
	 *  Gets the number of frames read from file.
	 * 
	 *  @return frame count
	 */
	public int getFrameCount() {
	}

	/**
	 *  Gets the current index of the animation frame, or -1 if animation hasn't not yet started
	 * 
	 *  @return frame index
	 */
	public int getCurrentFrameIndex() {
	}

	/**
	 *  Gets the "Netscape" iteration count, if any. A count of 0 means repeat indefinitiely.
	 * 
	 *  @return iteration count if one was specified, else 1.
	 */
	public int getLoopCount() {
	}

	/**
	 *  Get the next frame in the animation sequence.
	 * 
	 *  @return Bitmap representation of frame
	 */
	public com.codename1.ui.Image getNextFrame() {
	}

	/**
	 *  Reads GIF image from stream
	 * 
	 *  @param is containing GIF file.
	 *  @return read status code (0 = no errors)
	 */
	public int read(java.io.InputStream is, int contentLength) {
	}

	/**
	 *  Reads GIF image from byte array
	 * 
	 *  @param data containing GIF file.
	 *  @return read status code (0 = no errors)
	 */
	public int read(byte[] data) {
	}

	/**
	 *  Creates new frame image from current data (and previous frames as specified by their disposition codes).
	 */
	protected void setPixels(int frameIndex) {
	}

	/**
	 *  Decodes LZW image data into pixel array. Adapted from John Cristy's BitmapMagick.
	 */
	protected void decodeBitmapData(GifImage.GifFrame frame, byte[] dstPixels) {
	}

	/**
	 *  Returns true if an error was encountered during reading/decoding
	 */
	protected boolean err() {
	}

	/**
	 *  Initializes or re-initializes reader
	 */
	protected void init() {
	}

	/**
	 *  Reads a single byte from the input stream.
	 */
	protected int read() {
	}

	/**
	 *  Reads next variable length block from input.
	 * 
	 *  @return number of bytes stored in "buffer"
	 */
	protected int readBlock() {
	}

	/**
	 *  Reads color table as 256 RGB integer values
	 * 
	 *  @param ncolors int number of colors to read
	 *  @return int array containing 256 colors (packed ARGB with full alpha)
	 */
	protected int[] readColorTable(int ncolors) {
	}

	/**
	 *  Main file parser. Reads GIF content blocks.
	 */
	protected void readContents() {
	}

	/**
	 *  Reads GIF file header information.
	 */
	protected void readHeader() {
	}

	/**
	 *  Reads Graphics Control Extension values
	 */
	protected void readGraphicControlExt() {
	}

	/**
	 *  Reads next frame image
	 */
	protected void readBitmap() {
	}

	/**
	 *  Reads Logical Screen Descriptor
	 */
	protected void readLSD() {
	}

	/**
	 *  Reads Netscape extenstion to obtain iteration count
	 */
	protected void readNetscapeExt() {
	}

	/**
	 *  Reads next 16-bit value, LSB first
	 */
	protected int readShort() {
	}

	/**
	 *  Skips variable length blocks up to and including next zero length block.
	 */
	protected void skip() {
	}

	public static GifImage decode(java.io.InputStream is, int contentLength) {
	}

	@java.lang.Override
	public int getWidth() {
	}

	@java.lang.Override
	public int getHeight() {
	}

	@java.lang.Override
	public void scale(int width, int height) {
	}

	@java.lang.Override
	public boolean isAnimation() {
	}

	@java.lang.Override
	public boolean requiresDrawImage() {
	}

	@java.lang.Override
	protected void drawImage(com.codename1.ui.Graphics g, Object nativeGraphics, int x, int y) {
	}

	@java.lang.Override
	protected void drawImage(com.codename1.ui.Graphics g, Object nativeGraphics, int x, int y, int w, int h) {
	}

	@java.lang.Override
	public boolean animate() {
	}
}
