package com.kh.marchpring.board.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.marchpring.board.domain.Board;
import com.kh.marchpring.board.domain.Reply;
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

	/*********** 댓글 **********/
	/* 댓글 등록 */
	@Override
	public int insertReply(Reply reply) {
		int result = bStore.insertReply(session, reply);
		return result;
	}

	/* 댓글 목록 */
	@Override
	public List<Reply> selectAllReply(Integer boardNo) {
		List<Reply> rList = bStore.selectAllReply(session, boardNo);
		return rList;
	}

	/* 댓글 수정 */
	@Override
	public int updateReply(Reply reply) {
		int result = bStore.updateReply(session, reply);
		return result;
	}

	/* 댓글 삭제 */
	@Override
	public int deleteReply(Integer replyNo) {
		int result = bStore.deleteReply(session, replyNo);
		return result;
	}

}
