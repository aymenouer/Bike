package com.google.zxing.client.result;


/**
 *  Represents a parsed result that encodes contact information, like that in an address book
 *  entry.
 * 
 *  @author Sean Owen
 */
public final class AddressBookParsedResult extends ParsedResult {

	public AddressBookParsedResult(String[] names, String[] phoneNumbers, String[] phoneTypes, String[] emails, String[] emailTypes, String[] addresses, String[] addressTypes) {
	}

	public AddressBookParsedResult(String[] names, String[] nicknames, String pronunciation, String[] phoneNumbers, String[] phoneTypes, String[] emails, String[] emailTypes, String instantMessenger, String note, String[] addresses, String[] addressTypes, String org, String birthday, String title, String[] urls, String[] geo) {
	}

	public String[] getNames() {
	}

	public String[] getNicknames() {
	}

	/**
	 *  In Japanese, the name is written in kanji, which can have multiple readings. Therefore a hint
	 *  is often provided, called furigana, which spells the name phonetically.
	 * 
	 *  @return The pronunciation of the getNames() field, often in hiragana or katakana.
	 */
	public String getPronunciation() {
	}

	public String[] getPhoneNumbers() {
	}

	/**
	 *  @return optional descriptions of the type of each phone number. It could be like "HOME", but,
	 *   there is no guaranteed or standard format.
	 */
	public String[] getPhoneTypes() {
	}

	public String[] getEmails() {
	}

	/**
	 *  @return optional descriptions of the type of each e-mail. It could be like "WORK", but,
	 *   there is no guaranteed or standard format.
	 */
	public String[] getEmailTypes() {
	}

	public String getInstantMessenger() {
	}

	public String getNote() {
	}

	public String[] getAddresses() {
	}

	/**
	 *  @return optional descriptions of the type of each e-mail. It could be like "WORK", but,
	 *   there is no guaranteed or standard format.
	 */
	public String[] getAddressTypes() {
	}

	public String getTitle() {
	}

	public String getOrg() {
	}

	public String[] getURLs() {
	}

	/**
	 *  @return birthday formatted as yyyyMMdd (e.g. 19780917)
	 */
	public String getBirthday() {
	}

	/**
	 *  @return a location as a latitude/longitude pair
	 */
	public String[] getGeo() {
	}

	@java.lang.Override
	public String getDisplayResult() {
	}
}
