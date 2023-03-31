package com.kh.marchpring.board.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.marchpring.board.domain.Board;
import com.kh.marchpring.board.domain.Reply;

public interface BoardStore {
	
	
	/* 게시글 등록 */
	public int insertBoard(SqlSession session, Board board);

	
	/* 게시글 목록 조회 */
	public List<Board> selctAllBoard(SqlSession session);

	
	/* 게시글 상세조회 */
	public Board selectOneBoard(SqlSession session, Integer boardNo);


	
	/********* 댓글 *********/
	/* 댓글 등록 */
	public int insertReply(SqlSession session, Reply reply);

	/* 댓글 목록 */
	public List<Reply> selectAllReply(SqlSession session, Integer boardNo);

	/* 댓글 수정 */
	public int updateReply(SqlSession session, Reply reply);

	/* 댓글 삭제 */
	public int deleteReply(SqlSession session, Integer replyNo);

}
