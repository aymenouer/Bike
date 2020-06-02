package com.google.zxing.client.result;


/**
 *  Represents a parsed result that encodes a calendar event at a certain time,
 *  optionally with attendees and a location.
 * 
 *  @author Sean Owen
 *  
 *          TODO Check if CN1 {@link com.codename1.l10n.SimpleDateFormat} can
 *          handle the parsing directly
 */
public final class CalendarParsedResult extends ParsedResult {

	public CalendarParsedResult(String summary, String startString, String endString, String durationString, String location, String organizer, String[] attendees, String description, double latitude, double longitude) {
	}

	public String getSummary() {
	}

	/**
	 *  @return start time
	 *  @deprecated use {@link #getStartTimestamp()}
	 */
	@java.lang.Deprecated
	public java.util.Date getStart() {
	}

	/**
	 *  @return start time
	 *  @see #getEndTimestamp()
	 */
	public long getStartTimestamp() {
	}

	/**
	 *  @return true if start time was specified as a whole day
	 */
	public boolean isStartAllDay() {
	}

	/**
	 *  @return event end {@link Date}, or {@code null} if event has no duration
	 *  @deprecated use {@link #getEndTimestamp()}
	 */
	@java.lang.Deprecated
	public java.util.Date getEnd() {
	}

	/**
	 *  @return event end {@link Date}, or -1 if event has no duration
	 *  @see #getStartTimestamp()
	 */
	public long getEndTimestamp() {
	}

	/**
	 *  @return true if end time was specified as a whole day
	 */
	public boolean isEndAllDay() {
	}

	public String getLocation() {
	}

	public String getOrganizer() {
	}

	public String[] getAttendees() {
	}

	public String getDescription() {
	}

	public double getLatitude() {
	}

	public double getLongitude() {
	}

	@java.lang.Override
	public String getDisplayResult() {
	}
}
