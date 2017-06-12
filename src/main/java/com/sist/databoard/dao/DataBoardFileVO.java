package com.sist.databoard.dao;

/**
 * @author user
 *	출력할때 for문 쉽게 가기 위해 List로 따로 만들어 보낸다.
 */
public class DataBoardFileVO {
	private String filename;
	private long filesize;
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	
	
}
