package com.board.www.dto;

import java.sql.Date;

public class MemberDTO {

	// 필
	private int mno;
	private String mid;
	private String mpw;
	private Date mdate; // java.sql 파일로 import

	// 생
	public MemberDTO() {

	} // 기본생성자 -> new MemberDTO();

	public MemberDTO(String loginId, String loginPw) {
		// 커스텀 생성자 -> id와 pw처리용
		this.mid = loginId;
		this.mpw = loginPw;
		// this로 올려줘야 제대로 상호작용이 됨
		// 왜냐면 값을 mid 에 넣어야되는데 loginId하고 변수명이 다르니까
		// 생성자 만들기 했을때 이클립스가 따로 안적어줌
	}

	// 메서드 -> 게터 / 세터

	public int getMno() {
		return mno;
	}

	public String getMid() {
		return mid;
	}

	public String getMpw() {
		return mpw;
	}

	public Date getMdate() {
		return mdate;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public void setMpw(String mpw) {
		this.mpw = mpw;
	}

	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}

}
