package com.board.www.dto;

import java.sql.Date;

public class BoardDTO {
	// board 객체 처리, 세터, 게터

	// 필
	private int bno;
	private String btitle;
	private String bcontent;
	private String bwriter;
	private Date bdate; // date는 Date 타입으로 해야하고
	// import는 sql에 있는 Date로 가져와야함
	
	

	// 생 (안쓰면 기본 생성자)
	public BoardDTO() {

	} // 이거도 기본 생성자
	
	

	public BoardDTO(int bno, String btitle, String bcontent, String bwriter, Date bdate) {
		// 이건 커스텀 생성자. insert할때, select할때 주로 활용

		super();
		this.bno = bno;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bwriter = bwriter;
		this.bdate = bdate;
	}

	// 메

	public int getBno() {
		return bno;
	}

	public String getBtitle() {
		return btitle;
	}

	public String getBcontent() {
		return bcontent;
	}

	public String getBwriter() {
		return bwriter;
	}

	public Date getBdate() {
		return bdate;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

}
