package com.google.zxing.pdf417;


/**
 *  @author Guenther Grau
 */
public final class PDF417ResultMetadata {

	public PDF417ResultMetadata() {
	}

	/**
	 *  The Segment ID represents the segment of the whole file distributed over different symbols.
	 * 
	 *  @return File segment index
	 */
	public int getSegmentIndex() {
	}

	public void setSegmentIndex(int segmentIndex) {
	}

	/**
	 *  Is the same for each related PDF417 symbol
	 * 
	 *  @return File ID
	 */
	public String getFileId() {
	}

	public void setFileId(String fileId) {
	}

	/**
	 *  @return always null
	 *  @deprecated use dedicated already parsed fields
	 */
	@java.lang.Deprecated
	public int[] getOptionalData() {
	}

	/**
	 *  @param optionalData old optional data format as int array
	 *  @deprecated parse and use new fields
	 */
	@java.lang.Deprecated
	public void setOptionalData(int[] optionalData) {
	}

	/**
	 *  @return true if it is the last segment
	 */
	public boolean isLastSegment() {
	}

	public void setLastSegment(boolean lastSegment) {
	}

	/**
	 *  @return count of segments, -1 if not set
	 */
	public int getSegmentCount() {
	}

	public void setSegmentCount(int segmentCount) {
	}

	public String getSender() {
	}

	public void setSender(String sender) {
	}

	public String getAddressee() {
	}

	public void setAddressee(String addressee) {
	}

	/**
	 *  Filename of the encoded file
	 * 
	 *  @return filename
	 */
	public String getFileName() {
	}

	public void setFileName(String fileName) {
	}

	/**
	 *  filesize in bytes of the encoded file
	 * 
	 *  @return filesize in bytes, -1 if not set
	 */
	public long getFileSize() {
	}

	public void setFileSize(long fileSize) {
	}

	/**
	 *  16-bit CRC checksum using CCITT-16
	 * 
	 *  @return crc checksum, -1 if not set
	 */
	public int getChecksum() {
	}

	public void setChecksum(int checksum) {
	}

	/**
	 *  unix epock timestamp, elapsed seconds since 1970-01-01
	 * 
	 *  @return elapsed seconds, -1 if not set
	 */
	public long getTimestamp() {
	}

	public void setTimestamp(long timestamp) {
	}
}
