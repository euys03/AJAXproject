package com.kh.marchpring.board.service;

import java.util.List;

import com.kh.marchpring.board.domain.Board;

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


}
