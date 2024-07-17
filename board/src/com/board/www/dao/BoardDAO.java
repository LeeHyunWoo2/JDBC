package com.board.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.board.www.dto.BoardDTO;

public class BoardDAO {
	// 데이터베이스 처리용 (CRUD)
	// 쿼리문 처리해서 DTO로 보내주는 역할을 하는게 DAO

	public void list(Connection connection) { // BoardDTO로 리턴해야함
		// 근데 맨아래쯤 주석에 달린 이유로 void로 만들고 DTO안쓰는 방식으로 해보기로 함

		BoardDTO boardDTO = null;

		try {
			String sql = "select bno, btitle, bcontent, bwriter, bdate from board order by bno desc";
			// board테이블에 있는 데이터(번호 제목 내용 작성자 작성일)를 가져옴. order by = 정렬함 bno를 기준으로 내림차순
			// 여기서 실행된 쿼리문이 BoardDTO로 가야함

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			// 3단계
			ResultSet resultSet = preparedStatement.executeQuery();
			// 4단계
			boardDTO = new BoardDTO();

			while (resultSet.next()) { // 표 형식으로 리턴된 값 유무 판단
				// 여기서 DTO를 받아와서 넣음
				System.out.print(resultSet.getInt("bno") + "\t");
				System.out.print(resultSet.getString("btitle") + "\t");
//				System.out.print(resultSet.getString("bcontent") + "\t");
				System.out.print(resultSet.getString("bwriter") + "\t");
				System.out.print(resultSet.getDate("bdate") + "\t");
				System.out.println();

//				boardDTO.setBno(resultSet.getInt("bno"));
//				// 보드객체에 set으로 넣어줌 이 내용물은 리절트셋에서 get으로 가져온 int타입의 "bno"
//				boardDTO.setBtitle(resultSet.getString("btitle"));
//				boardDTO.setBcontent(resultSet.getString("bcontent"));
//				boardDTO.setBwriter(resultSet.getString("bwriter"));
//				boardDTO.setBdate(resultSet.getDate("bdate"));
				// 오타 안나게 그냥 위에 괄호안에 복사해서 붙이는게 좋음
			}
			// 5단계
			resultSet.close();
			preparedStatement.close();
			// connect는 끊으면 안됨 메인메서드에서 계속 연결되야함

		} catch (SQLException e) {
			System.out.println("BoardDTO.list() sql문 오류");
			e.printStackTrace();
		} // 여기서 finally로 끊으면 제대로 안끊길떄가 있다고 함

//		return boardDTO;
		// 하나씩밖에 안넘어가서 제대로 안나와서 여기서 그냥 출력해버릴경우도 있음
	}

}
