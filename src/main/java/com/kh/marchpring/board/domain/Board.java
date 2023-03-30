package com.kh.marchpring.board.domain;

import java.sql.Timestamp;

public class Board {
	// 번호, 제목, 내용, 작성자, 파일이름, 파일 리네임, 파일  경로, 조회수, 작성날짜, 수정날짜, 상태
	private int boardNo;
	private String boardTitle;
	private String boardContents;
	private String boardWriter;
	private String boardFilename;
	private String boardFileRename;
	private String boardFilepath;
	private int boardCount;
	private Timestamp bCreateDate;
	private Timestamp bUpdateDate;
	private String bStatus;
	
	
	// getter/setter()
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContents() {
		return boardContents;
	}
	public void setBoardContents(String boardContents) {
		this.boardContents = boardContents;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public String getBoardFilename() {
		return boardFilename;
	}
	public void setBoardFilename(String boardFilename) {
		this.boardFilename = boardFilename;
	}
	public String getBoardFileRename() {
		return boardFileRename;
	}
	public void setBoardFileRename(String boardFileRename) {
		this.boardFileRename = boardFileRename;
	}
	public String getBoardFilepath() {
		return boardFilepath;
	}
	public void setBoardFilepath(String boardFilepath) {
		this.boardFilepath = boardFilepath;
	}
	public int getBoardCount() {
		return boardCount;
	}
	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}
	public Timestamp getbCreateDate() {
		return bCreateDate;
	}
	public void setbCreateDate(Timestamp bCreateDate) {
		this.bCreateDate = bCreateDate;
	}
	public Timestamp getbUpdateDate() {
		return bUpdateDate;
	}
	public void setbUpdateDate(Timestamp bUpdateDate) {
		this.bUpdateDate = bUpdateDate;
	}
	public String getbStatus() {
		return bStatus;
	}
	public void setbStatus(String bStatus) {
		this.bStatus = bStatus;
	}
	
	
	// toString() 
	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContents=" + boardContents
				+ ", boardWriter=" + boardWriter + ", boardFilename=" + boardFilename + ", boardFileRename="
				+ boardFileRename + ", boardFilepath=" + boardFilepath + ", boardCount=" + boardCount + ", bCreateDate="
				+ bCreateDate + ", bUpdateDate=" + bUpdateDate + ", bStatus=" + bStatus + "]";
	}
	
	
	
	
}



