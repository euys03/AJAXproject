package com.kh.marchpring.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kh.marchpring.board.domain.Board;
import com.kh.marchpring.board.domain.Reply;
import com.kh.marchpring.board.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService bService;
	
	
	/* 게시글 등록 */
	@RequestMapping(value="/board/register", method=RequestMethod.GET)
	public ModelAndView showBoardRegister(ModelAndView mv) {
		mv.setViewName("board/register");
		return mv;
	}
	
	@RequestMapping(value="/board/register", method=RequestMethod.POST)
	public ModelAndView doBoardInsert(
			ModelAndView mv
			, @ModelAttribute Board board ) {
		try {
			int result = bService.registerBoard(board);
			mv.setViewName("redirect:/board/list");
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage()).setViewName("common/error");
		}
		return mv;
	}
	
	
	/* 게시글 목록 조회 */
	@RequestMapping(value="/board/list", method=RequestMethod.GET)
	public ModelAndView viewBoardAllList(ModelAndView mv) {
		try {
			List<Board> bList = bService.selectAllBoard();
			mv.addObject("bList", bList).setViewName("board/list");
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage()).setViewName("common/error");
			e.printStackTrace();
		}
		return mv;
	}
	
	
	/* 게시글 상세페이지 */
	@RequestMapping(value="/board/detail/{boardNo}", method=RequestMethod.GET)
	public ModelAndView viewBoardDetail(ModelAndView mv, @PathVariable Integer boardNo) {
		try {
			Board board = bService.selectOneBoard(boardNo);
			mv.addObject("board", board).setViewName("board/detail");
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("msg", e.getMessage()).setViewName("common/error");
		}
		return mv;
	}
	
	
	
	// -------------------- 댓글
	/* 댓글 등록 */
	@ResponseBody
	@RequestMapping(value="/reply/register", method = RequestMethod.POST)
	public String doReplyInsert(@ModelAttribute Reply reply) {
		try {
			int result = bService.insertReply(reply);
			if(result > 0) {
				return "1";	// 성공(success)
			}else {
				return "0";	// 실패(fail)
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	/* 댓글 목록 */
	@ResponseBody
	@RequestMapping(value="/reply/list", method = RequestMethod.GET, produces="application/json;charset=utf-8")
	public String viewReplyList(Integer boardNo) {
		List<Reply> rList = bService.selectAllReply(boardNo);
		// JSONArray <- JSONObject 로 원래 해결했지만
		// => GSON을 사용하여 한번에 처리
		return new Gson().toJson(rList);
	}
	
	
	
}
