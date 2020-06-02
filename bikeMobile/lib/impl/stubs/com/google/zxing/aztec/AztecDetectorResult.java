package com.google.zxing.aztec;


/**
 *  <p>Extends {@link DetectorResult} with more information specific to the Aztec format,
 *  like the number of layers and whether it's compact.</p>
 * 
 *  @author Sean Owen
 */
public final class AztecDetectorResult extends com.google.zxing.common.DetectorResult {

	public AztecDetectorResult(com.google.zxing.common.BitMatrix bits, com.google.zxing.ResultPoint[] points, boolean compact, int nbDatablocks, int nbLayers) {
	}

	public int getNbLayers() {
	}

	public int getNbDatablocks() {
	}

	public boolean isCompact() {
	}
}
