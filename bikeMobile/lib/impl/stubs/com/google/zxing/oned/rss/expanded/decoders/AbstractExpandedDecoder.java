package com.google.zxing.oned.rss.expanded.decoders;


/**
 *  @author Pablo Orduña, University of Deusto (pablo.orduna@deusto.es)
 *  @author Eduardo Castillejo, University of Deusto (eduardo.castillejo@deusto.es)
 */
public abstract class AbstractExpandedDecoder {

	protected final com.google.zxing.common.BitArray getInformation() {
	}

	protected final GeneralAppIdDecoder getGeneralDecoder() {
	}

	public abstract String parseInformation() {
	}

	public static AbstractExpandedDecoder createDecoder(com.google.zxing.common.BitArray information) {
	}
}
