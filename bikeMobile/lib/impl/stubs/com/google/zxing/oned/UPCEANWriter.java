package com.google.zxing.oned;


/**
 *  <p>Encapsulates functionality and implementation that is common to UPC and EAN families
 *  of one-dimensional barcodes.</p>
 * 
 *  @author aripollak@gmail.com (Ari Pollak)
 *  @author dsbnatut@gmail.com (Kazuki Nishiura)
 */
public abstract class UPCEANWriter extends OneDimensionalCodeWriter {

	public UPCEANWriter() {
	}

	@java.lang.Override
	public int getDefaultMargin() {
	}
}
