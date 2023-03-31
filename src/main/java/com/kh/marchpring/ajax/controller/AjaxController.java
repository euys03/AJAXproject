package com.kh.marchpring.ajax.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kh.marchpring.ajax.domain.Member;


@Controller
public class AjaxController {
	
	// 원래 코드
//	@RequestMapping(value="/ajax/sample", method=RequestMethod.GET)
//	public String showAjaxWrapUp(Model model) {
//		return "ajax/wrapup";
//	}

	@RequestMapping(value="/ajax/wrapup", method=RequestMethod.GET)
	public ModelAndView showAjaxWrapUp(ModelAndView mv) {
		// ModelAndView는 return "ajax/wrapup.jsp"; 가 아닌 'setViewName'메소드를 사용하여 return함.
		mv.setViewName("ajax/wrapup");
		return mv;
	}
	

	// 1. ajax로 서버에 전송값 보내기
	@ResponseBody
	@RequestMapping(value="/ajax/first", method = RequestMethod.GET)
	public void ajaxFirst(@RequestParam("msg") String msg) {
		System.out.println("전송받은 데이터 : " + msg);

	}
	
	// 2. ajax(jQuery)로 서버에 전송값 보내기
	@ResponseBody
	@RequestMapping(value="/ajax/second", method = {RequestMethod.GET, RequestMethod.POST})
	public void ajaxSecond(@RequestParam("msg") String msg) {
		System.out.println("전송받은 데이터 : " + msg);
		
	}
	
	// 3. 버튼 클릭시 서버에서 보낸 값 수신
	@ResponseBody 
	@RequestMapping(value="/ajax/third", method=RequestMethod.GET, produces="text/plain;charset=utf-8")
	public String ajaxThird() {
		return "서버에서 왔습니다..";
		// /WEB-INF/views/서버에서 왔습니다..jsp 이동 => 404 에러 !!
		// view/reserve 에 해당 jsp파일이 존재하지 않기 때문
	}
	
	// 4. 서버로 전송값 보내고 결과 문자열 받아서 처리
	@ResponseBody
	@RequestMapping(value="/ajax/fourth", method=RequestMethod.GET)
	public String ajaxFourth(@RequestParam("No1") Integer num1, @RequestParam("No2") Integer num2) {
		Integer result = num1 + num2;
		return String.valueOf(result);
		// "이름 : 일용자, 나이 : 22, 주소 : 서울시 중구, ..."
	}
	
	
	// 5. 서버로 전송값 보내고 결과 JSON으로 받아서 처리 (json으로 넘어가고 utf-8로 )
	@ResponseBody
	@RequestMapping(value="/ajax/fifth", method=RequestMethod.GET, produces="application/json;charset=utf-8")
	public String ajaxFifth(@RequestParam("memberId") String memberId) {
		List<Member> mList = new ArrayList<Member>();
		mList.add(new Member("khuser01", "pass01"));
		mList.add(new Member("khuser02", "pass02"));
		mList.add(new Member("khuser03", "pass03"));
		mList.add(new Member("khuser04", "pass04"));
		mList.add(new Member("khuser05", "pass05"));
		Member member = null;
		for(Member mOne : mList) {
			if(mOne.getMemberId().equals(memberId)) {
				member = mOne;
				break;
			}
		}
		// java -> json을 바꿀 때 꼭 사용 (pom.xml에 추가)
		JSONObject jsonObj = new JSONObject();	// json 객체 생성 -> { } 생성 완료
		// var data = {};와 동일
		jsonObj.put("memberId", member.getMemberId());
		jsonObj.put("memberPw", member.getMemberPw()); // {"memberId" : "khuser01", "memberPw" : "pass01"}과 동일
		return jsonObj.toString();
	}
	
	
	// 6. 서버로 전송값을 보내고 JSONArray로 결과 받아서 처리
	@ResponseBody
	@RequestMapping(value="/ajax/sixth", method = RequestMethod.GET, produces="application/json;charset=utf-8")
	public String ajaxSixth(@RequestParam("userId") String userId) {
		List<Member> mList = new ArrayList<Member>();
		mList.add(new Member("khuser01", "pass01"));
		mList.add(new Member("khuser02", "pass02"));
		mList.add(new Member("khuser03", "pass03"));
		mList.add(new Member("khuser04", "pass04"));
		mList.add(new Member("khuser05", "pass05"));
		boolean checkFlag = false;
		Member member = null;
		JSONArray jsonArr = new JSONArray(); // [ ]
		for(Member mOne : mList) {
			// 존재하면 하나만
			if(mOne.getMemberId().equals(userId)) {
				//member = mOne;
				JSONObject jsonObj = new JSONObject(); // { }
				jsonObj.put("memberId", mOne.getMemberId());
				jsonObj.put("memberPw", mOne.getMemberPw());
				jsonArr.add(jsonObj);	// [ { memberId : "khuser01", memberPw : "pass01" } ]
				checkFlag = true;
				break;
			}
		}
		// 존재하지 않으면 여러개
		if (!checkFlag) {
			for(Member mOne : mList) {
				JSONObject jOne = new JSONObject();
				jOne.put("memberId", mOne.getMemberId());
				jOne.put("memberPw", mOne.getMemberPw());
				jsonArr.add(jOne);
			}	// [ {}, {}, {}, {}, {} ]
		}
		return jsonArr.toString();
		
	}
	
	// 7. GSON을 이용한 List 변환
	// JSON 객체 가져오기
	@ResponseBody
	@RequestMapping(value="/ajax/seventh", method = RequestMethod.GET, produces="application/json;charset=utf-8")
	public String ajaxSeventh() {
		List<Member> mList = new ArrayList<Member>();
		mList.add(new Member("khuser01", "pass01"));
		mList.add(new Member("khuser02", "pass02"));
		mList.add(new Member("khuser03", "pass03"));
		mList.add(new Member("khuser04", "pass04"));
		mList.add(new Member("khuser05", "pass05"));
		// 수동 ver.
//		JSONArray jsonArr = new JSONArray(); // [ ]
//		for(Member mOne : mList) {
//			JSONObject jsonObj = new JSONObject(); // { }
//			jsonObj.put("memberId", mOne.getMemberId());
//			jsonObj.put("memberPw", mOne.getMemberPw());
//			jsonArr.add(jsonObj);	// [ { memberId : "khuser01", memberPw : "pass01" } ]
//		}
//		return jsonArr.toString();
		
		// 자동 ver.
		return new Gson().toJson(mList);
	}
	
}
