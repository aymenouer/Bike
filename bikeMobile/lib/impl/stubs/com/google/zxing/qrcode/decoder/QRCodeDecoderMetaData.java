package com.google.zxing.qrcode.decoder;


/**
 *  Meta-data container for QR Code decoding. Instances of this class may be used to convey information back to the
 *  decoding caller. Callers are expected to process this.
 * 
 *  @see com.google.zxing.common.DecoderResult#getOther()
 */
public final class QRCodeDecoderMetaData {

	/**
	 *  @return true if the QR Code was mirrored.
	 */
	public boolean isMirrored() {
	}

	/**
	 *  Apply the result points' order correction due to mirroring.
	 * 
	 *  @param points Array of points to apply mirror correction to.
	 */
	public void applyMirroredCorrection(com.google.zxing.ResultPoint[] points) {
	}
}
