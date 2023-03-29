package com.kh.marchpring.ajax.domain;

public class Member {
	private String memberId;
	private String memberPw;
	
	public Member() {}
	
	public Member(String memberId, String memberPw) {
		this.memberId = memberId;
		this.memberPw = memberPw;
	}

	// getter/setter
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	
	// toString
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberPw=" + memberPw + "]";
	}
	
	
	
	
	
}
