package com.kh.mvc.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.kh.mvc.controller.EmployeeController;
import com.kh.mvc.model.dto.DynamicDTO;


public class EmployeeView {
	private Scanner sc = new Scanner(System.in);
	private EmployeeController employeeController = 
			new EmployeeController();
	
	public void mainMenu(){
		while(true) {
			try {
				System.out.println("\n--- EMPLOYEE DML 해보기 ---");
				
				System.out.println();
				
				System.out.println("1. SELECT");
				System.out.println("2. INSERT");
				System.out.println("3. UPDATE");
				System.out.println("4. DELETE");
				System.out.println("0. 프로그램 종료");
				
				System.out.println();
				
				System.out.print("이용할 메뉴 번호 입력>> ");
				int menuNo = sc.nextInt();
				sc.nextLine();
				
				switch(menuNo) {
				case 1: selectSQL(); break;
				case 2: insertSQL(); break;
				case 3:	updateSQL(); break;
				case 4: deleteSQL(); break;
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
	} // mainMenu
	
	/**
	 * 원하는 컬럼명을 입력(인덱스번호)해 출력받기
	 */
	private void selectSQL() {
		List<String> inputColumns = new ArrayList<String>();
		System.out.println("\n-------------컬럼 목록---------------");
		System.out.println("1.EMP_ID\t 2.EMP_NAME\t 3.EMP_NO\n"
							+ "4.EMAIL \t 5.PHONE\t 6.DEPT_CODE\n"
							+ "7.JOB_CODE\t 8.SAL_LEVEL\t 9.SALARY\n"
							+ "10.BONUS\t 11.MANAGER_ID\t 12.HIRE_DATE\n"
							+ "13.ENT_DATE\t 14.ENT_YN");
		System.out.println("컬럼을 입력하시오 엔터로 구분하기!.\n!wq입력시 종료\n"
				+ "1-14의 범위만 작성이 가능합니다. >> ");
		while(true) {
			// Input을 1-14로 제한해야되는데..
			String input = sc.nextLine();
			if(input.equals("!wq")) {
				break;
			}
			if(Integer.parseInt(input)>= 1 && Integer.parseInt(input)<=14) {
				inputColumns.add("C"+input);
			}
		}
		
		List<String> list = employeeController.checkColumns(inputColumns);
		
		List<DynamicDTO> results = employeeController.selectByInput(list);
		
		if(!!!results.isEmpty()) {
			// KEY값(컬럼명)만 가지고 오기
			Set<String> columns = results.get(0).getData().keySet();
			
			// column명 출력
			for(String col : columns) {
				System.out.printf("%-18s",col);
			}
			System.out.printf("\n"+"-".repeat(columns.size()*17)); // 컬럼갯수*15 만큼 구분선
			System.out.println();
			
			for(DynamicDTO dto : results) {
				// 진짜 쓰기 싫은데 안쓰면 {K=V, K=V,....} 이렇게 나옴
				for(String col : columns) {
					System.out.printf("%-17s",dto.get(col)); // value 만 출력
				}
				System.out.println();
			}
			return;
		}
		System.out.println("값이 없습니다!");
	}
	
	
	private void insertSQL() {
		System.out.println("-----INSERT SQL------");
		System.out.print("ID 입력 > ");
		String empId = sc.nextLine();
		System.out.print("이름 입력 >" );
		String empName = sc.nextLine();
		System.out.print("주민번호 입력(-포함14자리) > ");
		String empNo = sc.nextLine();
		System.out.print("직급 코드 입력 > ");
		String jobCode = sc.nextLine();
		System.out.print("급여등급 입력 > ");
		String salLevel = sc.nextLine();
		
	}
	
	private void updateSQL() {
		
	}
	
	private void deleteSQL() {
		
	}
}

