package com.google.zxing.oned;


/**
 *  <p>Implements decoding of the ITF format, or Interleaved Two of Five.</p>
 * 
 *  <p>This Reader will scan ITF barcodes of certain lengths only.
 *  At the moment it reads length 6, 8, 10, 12, 14, 16, 18, 20, 24, and 44 as these have appeared "in the wild". Not all
 *  lengths are scanned, especially shorter ones, to avoid false positives. This in turn is due to a lack of
 *  required checksum function.</p>
 * 
 *  <p>The checksum is optional and is not applied by this Reader. The consumer of the decoded
 *  value will have to apply a checksum if required.</p>
 * 
 *  <p><a href="http://en.wikipedia.org/wiki/Interleaved_2_of_5">http://en.wikipedia.org/wiki/Interleaved_2_of_5</a>
 *  is a great reference for Interleaved 2 of 5 information.</p>
 * 
 *  @author kevin.osullivan@sita.aero, SITA Lab.
 */
public final class ITFReader extends OneDReader {

	public ITFReader() {
	}

	@java.lang.Override
	public com.google.zxing.Result decodeRow(int rowNumber, com.google.zxing.common.BitArray row, java.util.Map hints) {
	}
}
