package com.sist.databoard.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author user ����Ŭ �ڹ� CHAR(2000) => BYTE VARCHAR2(4000) CLOB (4G) ==>String
 * 
 *         8i, 9i ==> InputStream ======== 10g, 11g ==> String
 *
 *         Number ==> int, double
 *
 *         (defaultsms 14�̴�.)Number ==> int, long Number(4) ==> int Number(7, 2)
 *         ==> double
 *
 *         Date, Timestamp ==> Date, TimeStamp ==== java.sql.Date
 *         java.util.Date(o)
 *
 *         BFILE, BLOB(4G) : inputstream ������������� �����Ҷ� �ַ� ����.
 *
 */
public class DataBoardVO {
	private int no;
	private String name;
	private String subject;
	private String content;
	private String pwd;
	//private String regdate; //Date�������� �޾ƾ� formating�� �� �ִ�.
	private Date regdate;
	private int hit;
	private String filename;
	private String filesize;
	private int filecount;
	
	private int replycount;
	
	private List<MultipartFile> upload;//���⼭ new�� �ϸ� null���� �ȵ�� ����.
	private List<DataBoardFileVO> fileList=new ArrayList<DataBoardFileVO>(); //����ϱ� ���� filename�� filesize�� ,�� �߶� ����
		

	public int getReplycount() {
		return replycount;
	}

	public void setReplycount(int replycount) {
		this.replycount = replycount;
	}

	public List<DataBoardFileVO> getFileList() {
		return fileList;
	}

	public void setFileList(List<DataBoardFileVO> fileList) {
		this.fileList = fileList;
	}

	public List<MultipartFile> getUpload() {
		return upload;
	}

	public void setUpload(List<MultipartFile> upload) {
		this.upload = upload;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilesize() {
		return filesize;
	}

	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}

	public int getFilecount() {
		return filecount;
	}

	public void setFilecount(int filecount) {
		this.filecount = filecount;
	}

}
