package com.kh.marchpring.board.service;

import java.util.List;

import com.kh.marchpring.board.domain.Board;
import com.kh.marchpring.board.domain.Reply;

public interface BoardService {

	/**
	 * 게시글 등록
	 * @param board
	 * @return
	 */
	public int registerBoard(Board board);

	/**
	 * 게시글 목록 조회
	 * @return
	 */
	public List<Board> selectAllBoard();

	
	/**
	 * 게시글 상세조회
	 * @param boardNo
	 * @return 
	 */
	public Board selectOneBoard(Integer boardNo);

	
	
	/********** 댓글 ***************/
	
	/**
	 * 댓글 등록
	 * @param reply
	 * @return int
	 */
	public int insertReply(Reply reply);

	
	/**
	 * 댓글 목록
	 * @param boardNo
	 * @return
	 */
	public List<Reply> selectAllReply(Integer boardNo);


}
