package com.board.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.board.www.dto.MemberDTO;

public class MemberDAO {
	// 회원 DB에 대한 C(회원가입) R(로그인) U(회원정보수정) D(회원탈퇴)
	// DAO는 쿼리문 처리만 하는곳

	public MemberDAO() {

	} // 기본 생성자 (커스텀 생성자가 있어서 기본생성자가 안만들어졌으니 내가만듦)

	public MemberDAO(Connection connection) {
		// 커스텀 생성자
	}

	public void register(Connection connection, MemberDTO joinMemberDTO) throws SQLException {

		String sql = "insert into member(mno, mid, mpw, mdate) values(board_seq.nextval, ?,?,sysdate)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		// 이렇게 선언을 try 밖에서 할 수도 있음. 이럴경우 SQLException 해줘야함
		// 이렇게 하면 마지막에 finally에서 close하는게 가능함
		try {
			preparedStatement.setString(1, joinMemberDTO.getMid());
			preparedStatement.setString(2, joinMemberDTO.getMpw());
			preparedStatement.executeUpdate();
			// 딱히 사용자한테 뭐 보여줄게 없어서 그냥 리절트없이 실행만함
		} catch (SQLException e) {
			System.out.println("잘못된 값 입력 or 쿼리문 문제");
			e.printStackTrace();
		} finally {
			preparedStatement.close();
			// 굳이 이렇게 할 필요는 없음. sql하고 prepare..도 try안에 선언하면됨
			// finally 도 쓰지말고 try 안에 pre.close() 하면됨
			// 그냥 가능하니까 시도를 해본것이고 당장 이 메서드에서 굳이 이럴 가치는 없음
		}

	} // register 종료

	public MemberDTO login(Connection connection, MemberDTO loginMemberDTO) { // 로그인
		// DB에 있는 로그인 값을 찾아 옴 = id pw가 있으면 가져옴
		// Connection -> main에서 넘어온 JDBC 1, 2단계
		// MemberDTO -> 로그인시 키보드로 입력받은 id, pw값 (loginId, loginPw)

		MemberDTO loginDTO = new MemberDTO();
		// 리턴용 빈 객체 (밑에다 만드니까 안되서 트라이 위에 생성함)

		try {
			String sql = "select mno, mid, mpw, mdate from member where mid=? and mpw=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginMemberDTO.getMid());
			// service에서 받은 id가 첫번째 물음표에 적용
			preparedStatement.setString(2, loginMemberDTO.getMpw());
			// service에서 받은 pw가 두번째 물음표에 적용

			ResultSet resultSet = preparedStatement.executeQuery();
			// 위에서 만든 쿼리문을 실행하고 결과를 resultSet 표로 받는다.

			while (resultSet.next()) {
				loginDTO.setMno(resultSet.getInt("mno"));
				loginDTO.setMid(resultSet.getString("mid"));
				loginDTO.setMpw(resultSet.getString("mpw"));
				loginDTO.setMdate(resultSet.getDate("mdate"));
				// resultSet 표로 받은거를 MemberDTO 객체에 넣음

			}
			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			System.out.println("찾는 id와 pw가 없습니다");
			System.out.println("관리자 : sql문을 확인하세요.");
			System.out.println("회원 : id와 pw를 확인하세요");
			e.printStackTrace();
		}

		return loginDTO; // 로그인 완성용 객체

	}

	public void update(Connection connection, String updateChoice, String request, MemberDTO loginMember) { // 회원정보수정

		try {
			String sql = "update member set " + updateChoice + " = ? where mid =? ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, request);
			preparedStatement.setString(2, loginMember.getMid());
			preparedStatement.executeUpdate();
			System.out.println("변경 완료. 바뀐 정보로 다시 로그인 해주세요.");
			preparedStatement.close();

		} catch (SQLException e) {
			System.out.println("실패. 관리자를 부르세요");
			e.printStackTrace();
		}

	}

	public void delete(Connection connection, MemberDTO loginMember) { // 회원탈퇴

		try {
			String sql = "delete from member where mid=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginMember.getMid());
			preparedStatement.executeUpdate();
			System.out.println("탈퇴 완료");
			preparedStatement.close();

		} catch (SQLException e) {
			System.out.println("탈퇴실패 쿼리문 문제 가능성");
			e.printStackTrace();
		}

	}

} // class