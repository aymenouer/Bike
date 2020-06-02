package com.google.zxing;


/**
 *  Implementations of this interface can decode an image of a barcode in some format into
 *  the String it encodes. For example, {@link com.google.zxing.qrcode.QRCodeReader} can
 *  decode a QR code. The decoder may optionally receive hints from the caller which may help
 *  it decode more quickly or accurately.
 * 
 *  See {@link MultiFormatReader}, which attempts to determine what barcode
 *  format is present within the image as well, and then decodes it accordingly.
 * 
 *  @author Sean Owen
 *  @author dswitkin@google.com (Daniel Switkin)
 */
public interface Reader {

	/**
	 *  Locates and decodes a barcode in some format within an image.
	 * 
	 *  @param image image of barcode to decode
	 *  @return String which the barcode encodes
	 *  @throws NotFoundException if no potential barcode is found
	 *  @throws ChecksumException if a potential barcode is found but does not pass its checksum
	 *  @throws FormatException if a potential barcode is found but format is invalid
	 */
	public Result decode(BinaryBitmap image);

	/**
	 *  Locates and decodes a barcode in some format within an image. This method also accepts
	 *  hints, each possibly associated to some data, which may help the implementation decode.
	 * 
	 *  @param image image of barcode to decode
	 *  @param hints passed as a {@link Map} from {@link DecodeHintType}
	 *  to arbitrary data. The
	 *  meaning of the data depends upon the hint type. The implementation may or may not do
	 *  anything with these hints.
	 *  @return String which the barcode encodes
	 *  @throws NotFoundException if no potential barcode is found
	 *  @throws ChecksumException if a potential barcode is found but does not pass its checksum
	 *  @throws FormatException if a potential barcode is found but format is invalid
	 */
	public Result decode(BinaryBitmap image, java.util.Map hints);

	/**
	 *  Resets any internal state the implementation has after a decode, to prepare it
	 *  for reuse.
	 */
	public void reset();
}
