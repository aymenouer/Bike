package com.google.zxing.client.result;


/**
 *  Represents a parsed result that encodes a geographic coordinate, with latitude,
 *  longitude and altitude.
 * 
 *  @author Sean Owen
 */
public final class GeoParsedResult extends ParsedResult {

	public String getGeoURI() {
	}

	/**
	 *  @return latitude in degrees
	 */
	public double getLatitude() {
	}

	/**
	 *  @return longitude in degrees
	 */
	public double getLongitude() {
	}

	/**
	 *  @return altitude in meters. If not specified, in the geo URI, returns 0.0
	 */
	public double getAltitude() {
	}

	/**
	 *  @return query string associated with geo URI or null if none exists
	 */
	public String getQuery() {
	}

	@java.lang.Override
	public String getDisplayResult() {
	}
}
