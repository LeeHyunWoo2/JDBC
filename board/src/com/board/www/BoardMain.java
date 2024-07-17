package com.board.www;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.board.www.dao.BoardDAO;
import com.board.www.dto.MemberDTO;
import com.board.www.service.BoardService;
import com.board.www.service.MemberService;

public class BoardMain {
	// 필

	public static Scanner scanner = new Scanner(System.in);
	// 여기저기서 이거 하나만 쓸거니까 static 으로 해주면 더 좋다.
	public static BoardDAO boardDAO = new BoardDAO(); // JDBC 담당
	public static Connection connection = null; // java.sql꺼 가져옴

	public static MemberDTO loginMember = null; // 로그인 후 객체

	// 생 (객체생성 / dao나 dto에서도 할수있음)

	public BoardMain() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 1단계
			// 이게 없을수도 있으니 트라이캐치로 둘러쌈
			connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.112:1521:orcl", "boardtest",
					"boardtest");
			// 2단계 (url, id, pw가 필요함)

		} catch (ClassNotFoundException e) { // 못찾을경우
			System.out.println("드라이버 이름 혹은 ojdbc6.jar를 확인해주세요.");
			e.printStackTrace();
		} catch (SQLException e) { // 2단계 실패시 혹은 쿼리문이 잘못될경우
			System.out.println("url, id, pw나 쿼리문이 잘못됨");
			e.printStackTrace();
			System.exit(0); // 프로세스 강제종료
			// 생성자에 적은거라서 finaly 말고 이거 쓰는듯 원래는 finaly하고 각종 close씀
		}

	}

	// 메

	public static void main(String[] args) throws SQLException {
		// 기본적인 설정 : 스캐너, JDBC연동, 주메뉴 구현

		BoardMain boardMain = new BoardMain(); // 위에 저 생성자 호출
		// 구동하자마자 main 부터 돌리기 시작하기 때문에 여기서 호출해줘야함

		boolean run = true;
		while (run) {
			
			System.out.println("MBC 아카데미 대나무숲에 오신걸 환영합니다.");
			// 여기서 각종 service로 넘어갈 메뉴 생성
			System.out.println("1. 회원 | 2. 게시판 | 3. 종료");
			System.out.print(">>>");
			int select = scanner.nextInt();
			switch (select) {
			case 1:
				System.out.println("회원용 서비스로 진입합니다.");
				MemberService memberService = new MemberService();
				loginMember = memberService.memberMenu(scanner, loginMember, connection);
				if (loginMember == null) {
					System.out.println();
				} else if (loginMember.getMid() != null) {
					System.out.println(loginMember.getMid() + "님 환영합니다.");
				}
				// 회원 서비스에서 나올 때 로그인 정보가 유지되야 함.
				break;
			case 2:
				System.out.println("게시판 서비스로 진입합니다.");
				BoardService boardService = new BoardService();
				boardService.list(connection);
				break;
			case 3:
				System.out.println("끕니다.");
				run = false;
				break;
			default:
				System.out.println("1~3");
			} // switch 종료

		} // while 종료

	} // main 종료

} // class 종료