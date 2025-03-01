package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class JDBCExample6 {

	public static void main(String[] args) throws ClassNotFoundException {
		
		// 아이디, 비밀번호를 입력받아 
		// 일치하는 사용자를 삭제 
		// 단, 아이디 또는 비밀번호가 일치하지 않으면
		// "아이디 또는 비밀번호가 일치하지 않습니다." 출력
		
		// 일치하면
		// "정말 삭제하시겠습니까? (Y/N)" 출력
		// -> 'Y' 입력 시 삭제	-> "삭제 되었습니다."
		// -> 'N' 삭제 취소	-> "삭제 취소됨."
		
		/* DB 연결을 위한 url, userName, password */
		
		String url = "jdbc:oracle:thin:@112.221.156.34:12345:XE";
		String userName = "KH19_JJH"; // 사용자 계정명
		String password = "KH1234"; // 계정 비밀번호
		
		/* JDBC Driver를 메모리에 load */
		Class.forName("oracle.jdbc.OracleDriver");
		
		/* SQL 작성 */
		String sql = """
					DELETE FROM TB_USER 
					WHERE
					  	USER_ID = ?
					AND
						USER_PW = ?
					""";
		
		try(Connection conn = DriverManager.getConnection(url, userName, password);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			Scanner sc = new Scanner(System.in)) {
			
			conn.setAutoCommit(false);
			
			System.out.println("--- 계정 삭제하기 ---");
			System.out.print("아이디 입력 : ");
			String id = sc.next();
			
			System.out.print("비밀번호 입력 : ");
			String pw = sc.next();
		
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) { // 1행 수정 
				while(true) {
					System.out.print("정말 삭제하시겠습니까? (Y/N) ");
					String check = sc.next().toUpperCase();
					if(check.equals("Y")) {
						System.out.println("삭제 되었습니다.");
						conn.commit();
						break;
					}else if(check.equals("N")) {
						System.out.println("삭제 취소됨.");
						conn.rollback();
						break;
					}else {
						System.out.println("뭘 쓴거야 이 머저리야");
						continue;
					}
				}
			} else {
				System.out.println("아이디 또는 비밀번호가 일치하지 않습니다");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
