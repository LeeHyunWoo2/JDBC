package com.board.www.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.board.www.dao.MemberDAO;
import com.board.www.dto.MemberDTO;

public class MemberService {

	// 회원에 대한 처리 C(회원가입) R(로그인) U(정보수정) D(회원탈퇴)

	public MemberDTO memberMenu(Scanner scanner, MemberDTO loginMember, Connection connection) throws SQLException {
		boolean run = true;
		while (run) {
			System.out.println("1.가입 2.로그인 3.로그아웃 4.수정 5.탈퇴 6.메인메뉴로");
			int select = scanner.nextInt();
			switch (select) {
			case 1: // 회원가입
				join(connection, scanner);
				System.out.println("환영합니다. 로그인 해주세요.");
				break;

			case 2: // 로그인
				loginMember = login(loginMember, connection, scanner);
				if (loginMember.getMid() != null) {
					System.out.println("유저 ID : " + loginMember.getMid() + " 확인");
				}
				break;

			case 3: // 로그아웃
				loginMember = logout(loginMember, scanner);
				break;
				
			case 4: // 회원정보수정
				loginMember = modify(loginMember, connection, scanner);
				break;

			case 5: // 회원탈퇴
				loginMember = delete(scanner, connection, loginMember);
				break;
			case 6:
				System.out.println("메인메뉴로 돌아갑니다");
				run = false;
				break;

			default:
				System.out.println("1~5");
			} // switch

		} // while
		return loginMember;

	} // membermenu

	public void join(Connection connection, Scanner scanner) throws SQLException { // 회원가입용 메서드

		System.out.println("회원가입 메서드로 진입");
		System.out.print("id : ");
		String joinId = scanner.next();
		System.out.print("pw : ");
		String joinPw = scanner.next();

		MemberDTO joinMemberDTO = new MemberDTO(joinId, joinPw);
		MemberDAO memberDAO = new MemberDAO();
		memberDAO.register(connection, joinMemberDTO);

	} // join() 메서드 종료

	public MemberDTO login(MemberDTO loginMember, Connection connection, Scanner scanner) { // 로그인용 메서드

		System.out.println("로그인 메서드로 진입");
		System.out.print("id : ");
		String loginId = scanner.next();
		System.out.print("pw : ");
		String loginPw = scanner.next();

		MemberDTO loginMemberDTO = new MemberDTO(loginId, loginPw);
		MemberDAO memberDAO = new MemberDAO();
		return memberDAO.login(connection, loginMemberDTO);

	} // login() 메서드 종료

	public MemberDTO logout(MemberDTO loginMember, Scanner scanner) {

		if (loginMember == null) {
			System.out.println("로그인 되지 않았습니다.");
		} else if (loginMember != null) {
			boolean run = true;
			while (run) {
				System.out.println("로그아웃 하시겠습니까?");
				System.out.println("1번 : 예\n2번 : 아니오");
				int logoutSelect = scanner.nextInt();
				switch (logoutSelect) {
				case 1:
					loginMember = null;
					System.out.println("로그아웃 되었습니다.");
					run = false;
					break;
				case 2:
					System.out.println("메뉴로 복귀합니다.");
					run = false;
					break;
				default:
				} // switch

			} // while

		}
		return loginMember;
	}

	public MemberDTO modify(MemberDTO loginMember, Connection connection, Scanner scanner) { // 회원정보수정용 메서드
		if (loginMember == null) {
			System.out.println("로그인 후 사용가능한 메뉴입니다.");
		} else if (loginMember != null) {
			String updateChoice = null;
			System.out.println("1. id 2. pw 3.나가기");
			int select = scanner.nextInt();
			scanner.nextLine();
			switch (select) {
			case 1:
				updateChoice = "mid";
				System.out.println("id를 변경합니다.");
				break;
			case 2:
				updateChoice = "mpw";
				System.out.println("pw를 변경합니다.");
				break;
			case 3:
				break;
			default:
			}
			System.out.println("바꾸고싶은 값으로 입력해주세요.");
			String request = scanner.nextLine();
			MemberDAO memberDAO = new MemberDAO();
			memberDAO.update(connection, updateChoice, request, loginMember);
			loginMember = null;

		}
		return loginMember;

	} // modify() 메서드 종료

	public MemberDTO delete(Scanner scanner, Connection connection, MemberDTO loginMember) { // 회원탈퇴용 메서드
		if (loginMember == null) {
			System.out.println("로그인 되지 않았습니다.");
		} else if (loginMember != null) {
			System.out.println("탈퇴 하시겠습니까?");
			System.out.println("1번 : 예\n2번 : 아니오");
			int delSelect = scanner.nextInt();
			if (delSelect == 1) {
				System.out.println("회원탈퇴 메서드로 진입");
				MemberDAO memberDAO = new MemberDAO();
				memberDAO.delete(connection, loginMember);
				loginMember = null;
				System.out.println("로그아웃 되었습니다.");
			} else if (delSelect == 2) {
				System.out.println("메뉴로 복귀합니다.");
			}
		}
		return loginMember;

	} // delete() 메서드 종료

}
