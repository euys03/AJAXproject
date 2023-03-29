package com.kh.marchpring.board.store.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.marchpring.board.domain.Board;
import com.kh.marchpring.board.store.BoardStore;

@Repository
public class BoardStoreLogic implements BoardStore{

	/* 게시글 등록 */
	@Override
	public int insertBoard(SqlSession session, Board board) {
		int result = session.insert("BoardMapper.insertBoard", board);
		return result;
	}

	/* 목록 조회 */
	@Override
	public List<Board> selctAllBoard(SqlSession session) {
		List<Board> bList = session.selectList("BoardMapper.selectBoardList");
		return bList;
	}

	/* 게시글 상세조회 */
	@Override
	public Board selectOneBoard(SqlSession session, Integer boardNo) {
		Board board = session.selectOne("BoardMapper.selectBoardOne", boardNo);
		return board;
	}

}
