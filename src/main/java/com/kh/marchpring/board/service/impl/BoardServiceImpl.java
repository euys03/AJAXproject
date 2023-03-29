package com.kh.marchpring.board.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.marchpring.board.domain.Board;
import com.kh.marchpring.board.service.BoardService;
import com.kh.marchpring.board.store.BoardStore;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardStore bStore;
	@Autowired
	private SqlSession session;

	
	/* 게시글 등록 */
	@Override
	public int registerBoard(Board board) {
		int result = bStore.insertBoard(session, board);
		return result;
	}

	/* 게시글 목록 조회 */
	@Override
	public List<Board> selectAllBoard() {
		List<Board> bList = bStore.selctAllBoard(session);
		return bList;
	}

	/* 게시글 상세조회 */
	@Override
	public Board selectOneBoard(Integer boardNo) {
		Board board = bStore.selectOneBoard(session, boardNo); 
		return board;
	}

}
