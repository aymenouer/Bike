package com.google.zxing.client.result;


/**
 *  Represents a parsed result that encodes extended product information as encoded
 *  by the RSS format, like weight, price, dates, etc.
 * 
 *  @author Antonio Manuel Benjumea Conde, Servinform, S.A.
 *  @author Agustín Delgado, Servinform, S.A.
 */
public final class ExpandedProductParsedResult extends ParsedResult {

	public static final String KILOGRAM = "KG";

	public static final String POUND = "LB";

	public ExpandedProductParsedResult(String rawText, String productID, String sscc, String lotNumber, String productionDate, String packagingDate, String bestBeforeDate, String expirationDate, String weight, String weightType, String weightIncrement, String price, String priceIncrement, String priceCurrency, java.util.Map uncommonAIs) {
	}

	@java.lang.Override
	public boolean equals(Object o) {
	}

	@java.lang.Override
	public int hashCode() {
	}

	public String getRawText() {
	}

	public String getProductID() {
	}

	public String getSscc() {
	}

	public String getLotNumber() {
	}

	public String getProductionDate() {
	}

	public String getPackagingDate() {
	}

	public String getBestBeforeDate() {
	}

	public String getExpirationDate() {
	}

	public String getWeight() {
	}

	public String getWeightType() {
	}

	public String getWeightIncrement() {
	}

	public String getPrice() {
	}

	public String getPriceIncrement() {
	}

	public String getPriceCurrency() {
	}

	public java.util.Map getUncommonAIs() {
	}

	@java.lang.Override
	public String getDisplayResult() {
	}
}
