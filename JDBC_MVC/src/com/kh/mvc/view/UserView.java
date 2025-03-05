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
			System.out.println("--- USER테이블 관리 프로그램 ---");
			
			System.out.println();
			
			System.out.println("1. 회원 전체 조회");
			System.out.println("2. 회원 추가");
			System.out.println("0. 프로그램 종료");
			
			System.out.println();
			
			System.out.print("이용할 메뉴 번호 입력>> ");
			int menuNo = 0;
			try {
				menuNo = sc.nextInt();
				
			} catch (InputMismatchException e) {
					sc.nextLine();
					continue;
			}
			sc.nextLine();
			
			switch(menuNo) {
			case 1: findAll(); break;
			case 2: insertUser(); break;
			case 0: System.out.println("프로그램 종료"); return;
			default: System.out.println("잘못된 입력입니다.");
			}
		}
	}
	
	// 회원 전체 정보를 조회해주는 기능
	private void findAll() {
		System.out.println("--- 회원 전체 목록 ---");
		// controller에게 DB가서 회원 전체 목록좀 가지고 오라고 요청
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
		System.out.println("--- 회원 추가 페이지입니다. ---");
		
		System.out.print("아이디를 입력하세요 > ");
		String userId = null;
		while(true) {
			userId = sc.nextLine();
			// UNIQUE 했다고 치고 입력받은 아이디 가지고 DB가서 WHERE조건절에 사용자가 입력한 아이디 넣어서
			// 조회결과 있으면 다시 입력받게하든 뭘 하든
			// SELECT USER_ID FROM TB_USER WHERE USER_ID = 사용자가 입력한 아이디값
 	 	 	
			// userId.matches("^[a-zA-z]")
			if(userId.length() > 30 ) {
				System.out.println("아이디는 30자 이내로 입력해주세요");
				continue;
			}
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
}