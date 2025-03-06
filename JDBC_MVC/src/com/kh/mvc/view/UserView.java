package com.kh.mvc.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.kh.mvc.controller.UserController;
import com.kh.mvc.model.dto.UserDTO;

/**
 * 해당 UserView 클래스는 JDBC 실습을 위해 생성하였으며
 * 사용자에게 입력 및 출력을 수행하는 메서드를 제공
 * 
 * @author: 종로 C강의장
 * @version: 0.1
 * @date: 2025-03-04
 */
public class UserView {
	private Scanner sc = new Scanner(System.in);
	private UserController userController = new UserController();
	
	
	
	/**
	 * 프로그램 시작 시 사용자에게 보여줄 메인화면을 출력하는 메서드
	 */
	public void mainMenu() {
		
		while(true) {
			try {
				System.out.println("\n--- USER테이블 관리 프로그램 ---");
				
				System.out.println();
				
				System.out.println("1. 회원 전체 조회");
				System.out.println("2. 회원 추가");
				System.out.println("3. 비밀번호수정");
				System.out.println("4. 회원 탈퇴");
				System.out.println("5. 회원 조회(회원번호)");
				System.out.println("6. 회원 조회(회원아이디)");
				System.out.println("0. 프로그램 종료");
				
				System.out.println();
				
				System.out.print("이용할 메뉴 번호 입력>> ");
				int menuNo = sc.nextInt();
				sc.nextLine();
				
				switch(menuNo) {
				case 1: findAll(); break;
				case 2: insertUser(); break;
				case 3:	modifyPw(); break;
				case 4: deleteUser(); break;
				case 5: findByUserNo(); break;
				case 6: findByUserId(); break;
				case 0: System.out.println("\n프로그램 종료"); return;
				default: System.out.println("\n잘못된 입력입니다.");
				}
			}catch(InputMismatchException e) {
				sc.nextLine();
				System.out.println("숫자만 입력해주세요");
				continue;
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// 회원 전체 정보를 조회해주는 기능
	private void findAll() {
		System.out.println("\n--- 회원 전체 목록 ---");
		// controller 에게 DB가서 회원 전체 목록좀 가지고 오라고 요청
		List<UserDTO> list = userController.findAll();
		System.out.println("\n조회된 총 회원 수 :" + list.size() + "명 입니다.");
		
		if(!!!list.isEmpty()) {
			System.out.println("==========================================");
			for(UserDTO user : list) {
				System.out.println(user.getUserName()+" 님의 정보");
				System.out.print("아이디 : " + user.getUserId());
				System.out.print("\t가입일 : " + user.getEnrollDate());
				System.out.println();
			}
			System.out.println("==========================================");
			
		} else {
			System.out.println("\n회원이 존재하지 않습니다!!");
		}
	}
	
	
	/** 
	 * TB_USER에 INSERT할 값을 사용자에게 입력받도록 유도하는 화면
	 */
	private void insertUser() {
		System.out.println("\n--- 회원 추가 페이지입니다. ---");
		
		
		String userId = null;
		while(true) {
			System.out.print("아이디를 입력하세요 > ");
			userId = sc.nextLine();
			
			if(userId.length() > 30 ) {
				System.out.println("아이디는 30자 이내로 입력해주세요");
				continue;
			}
			
			// 중복검사 대충 만들기
			UserDTO user = userController.findByUserId(userId);
			if(user!=null) {
				System.out.println("존재하는 아이디 입니다.");
				continue;
			}
			// UNIQUE 했다고 치고 입력받은 아이디 가지고 DB가서 WHERE조건절에 사용자가 입력한 아이디 넣어서
			// 조회결과 있으면 다시 입력받게하든 뭘 하든
			// SELECT USER_ID FROM TB_USER WHERE USER_ID = 사용자가 입력한 아이디값
 	 	 	
			// userId.matches("^[a-zA-z]")
			break;
		}
		
		System.out.print("비밀번호를 입력하세요 > ");
		String userPw = sc.nextLine();
		System.out.print("이름을 입력하세요 > ");
		String userName = sc.nextLine();
		
		int result = userController.insertUser(userId, userPw, userName);
		
		if(result!=0) {
			System.out.println("회원 추가 성공");
		}else {
			System.out.println("회원 추가 실패");
		}
		
		
	}
	
	
	/**
	 * 비밀번호 수정
	 * - 회원선택(아이디,비밀번호)을 진행 후
	 * - 회원의 비밀 번호를 변경
	 */
	private void modifyPw() {
		System.out.println("\n---비밀번호 수정 페이지---");
		System.out.print("수정할 회원의 아이디를 입력하세요 > ");
		String userId = sc.nextLine();
		UserDTO user;
		user = userController.findByUserId(userId);
		
		if(user == null) {
			System.out.println("회원이 존재하지 않습니다.");
			return;
		}
		
		System.out.print("원래 비밀번호를 입력하세요 > ");
		String userPw = sc.nextLine();
		if(!!!user.getUserPW().equals(userPw)) {
			System.out.println("비밀번호가 맞지 않습니다.");
			return;
		}
		
		System.out.print("바꿀 비밀번호를 입력하세요 > ");
		String newPw = sc.nextLine();
		int result = userController.modifyPw(user, newPw);
		
		if(result!=0) {
			System.out.println("비밀번호가 변경되었습니다.");
		}else {
			System.out.println("변경실패!");
		}
	}
	
	/**
	 * 회원 삭제
	 * - 회원선택(아이디,번호?)
	 * - 회원을 삭제(삭제 확인필요AutoCommit끄기)
	 */
	private void deleteUser() {
		System.out.println("\n--- 회원삭제 페이지 ---");
		System.out.print("아이디를 입력하세요 > ");
		String userId = sc.nextLine();
		UserDTO user;
		user = userController.findByUserId(userId);
		
		if(user == null) {
			System.out.println("회원이 존재하지 않습니다.");
			return;
		}
		System.out.printf("---- 회원정보 ----\n아이디:%s\t이름:%s\t가입날짜:%s\n",
				user.getUserId(), user.getUserName(), user.getEnrollDate());

		// 삭제 여부 확인
		String check;
		while (true) {
            System.out.print("\n회원정보를 삭제하시겠습니까?(Y/N) > ");
            check = sc.nextLine();  // 전체 입력받기
            if (check.length() == 1) {  // 한 글자면 반복문 종료
            	if (check.toUpperCase().equals("N")) {
            		System.out.println("회원 삭제를 취소합니다.");
            		return;
            	}
            	if(check.toUpperCase().equals("Y")) {
        			break;
        		}
            	System.out.println("Y/N 중 하나만 입력하세요");
            	continue;
            }
            System.out.println("한 글자만 입력해야 합니다.");
        }
		
		int result = userController.deleteUser(user);
		
		if(result != 0) {
			System.out.println("회원정보 삭제 완료");
		}else {
			System.out.println("회원정보 삭제 실패");
		}
		
	}
	
	/**
	 * 유저 번호로 회원찾기
	 */
	private void findByUserNo() throws Exception{
		System.out.println("\n--- 회원검색(번호)페이지 ---");
		System.out.print("회원번호를 입력하세요 > ");
		int userNo = sc.nextInt();
		sc.nextLine();
		
		UserDTO result;
		
		result = userController.findByUserNo(userNo);
		
		System.out.println("\n---조회 결과---");
		if(result != null) {
			System.out.printf("번호가 %d인 회원님의 정보는"
					+ "\n아이디: %s\t가입일: %s",userNo, result.getUserName(), result.getEnrollDate());
			return;
		}
		System.out.println("해당하는 회원정보가 없습니다.");
	}
	
	/**
	 * 유저 아이디로 회원찾기
	 */
	private void findByUserId() {
		System.out.println("\n--- 회원검색(아이디)페이지 ---");
		System.out.print("아이디를 입력하세요 > ");
		String userId = sc.nextLine();
		
		UserDTO result;
		result = userController.findByUserId(userId);
		if(result != null) {
			System.out.printf("\n아이디가 %s인 회원님의 정보는"
					+ "\n아이디: %s\t가입일: %s\n",userId, result.getUserName(), result.getEnrollDate());
			return;
		}
		System.out.println("해당하는 회원정보가 없습니다.");
		
		
	}
	
}