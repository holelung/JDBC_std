package com.kh.mvc.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.mvc.model.dto.UserDTO;
import com.kh.mvc.util.JdbcUtil;

/**
 * DAO(Data Access Object)
 * 
 * 데이터베이스 관련된 작업(CRUD)을 전문적으로 담당하는 객체
 * DAO안에 모든 메서드들은 데이터베이스와 관련된 기능으로 만들 것
 * 
 * Controller를 통해 호출된 기능을 수행
 * DB에 직접 접근한 후 SQL문을 수행하고 결과 반환(JDBC)
 */
public class UserDAO {
	/* JDBC용 객체
	 * 
	 * Connection: DB와의 연결정보를 담고 있는 객체(IP, Port, UserName, PW, SID)
	 * Statement: Connection이 가지고 있는 연결정보 DB에 SQL문을 전달하고 실행하고 결과도 받아오는 객체
	 * ResultSet: 실행한 SQL문이 + SELECT문일 경우 조회된 결과가 처음 담기는 객체
	 * 
	 * Prepared Statement: SQL을 미리 준비하는 개념 ? (플레이스 홀더)로 확보해놓은 공간을 사용자가 입력한 값들과 바인딩해서 SQL문 수행
	 * 
	 * 처리 절차
	 * 1) JDBC Driver 등록: 해당 DBMA에서 제공하는 클래스 정보를 동적으로 등록
	 * 2) Connection 객체 생성: 접속하고자 하는 DB의 정보를 입력해서 생성 (url, username, pw)
	 * 3-1) PreparedStatement 객체 생성: Connection 객체를 이용해서 생성(미완성된 SQL문을 미리 전달)
	 * 3-2) SQL문이 미완성일 경우, 미완성된 SQL문을 완성 형태로 만들어주어야 함. 완성되어있는 경우 생략
	 * 4) SQL문을 실행: executeXXX() => SQL을 인자로 전달하지 않음!
	 * 			SELECT(DQL): executeQuery()
	 * 			DML: executeUpdate()
	 * 5) 결과받기:
	 * 			SELECT: ResultSet타입 객체(조회 데이터 담김)
	 * 			DML: int(처리된 행의 개수)
	 * 6-1) ResultSet에 담겨있는 데이터들을 하나하나씩 뽑아서 DTO객체 필드에 옮겨담은 후, 조회 결과가 여러 행일 경우 List로 관리
	 * 6-2) 트랜잭션 처리
	 * 7) (생략가능) 자원반납(close) => 생성의 역순
	 * 8) Controller로 결과 반환
	 * 			SELECT: 6-1에서 만든 거
	 * 			DML: 처리된 행의 개수
	 */
	
//	private final String URL = "jdbc:oracle:thin:@112.221.156.34:12345:XE";
//	private final String USERNAME = "KH19_JJH";
//	private final String PASSWORD = "KH1234";
	
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
		} catch (ClassNotFoundException e) {
			System.out.println("ojdbc확인");
		}
	}
	
	public List<UserDTO> findAll(Connection conn) {
		
		/* VO / DTO / Entity
		 * 
		 * 1명의 회원의 정보는 1개의 UserDTO 객체의 필드의 값을 담아야 함
		 * 
		 * 사용 목적 : 테이블의 한 행에 있는 데이터를 담기 위해서 사용한다.
		 * 
		 * 문제점 : userDTO가 몇개가 나올지 알 수 없음
		 */
		List<UserDTO> list = new ArrayList<UserDTO>();
		
		String sql = "SELECT USER_NO, USER_ID, USER_PW, USER_NAME, ENROLL_DATE FROM TB_USER ORDER BY ENROLL_DATE DESC";
		
//		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			// 연결을 했다.

//			conn= DriverManager.getConnection(URL, USERNAME, PASSWORD);	
			pstmt = conn.prepareStatement(sql); // 도화지를 펼쳤다.(편집기 실행)
			rset = pstmt.executeQuery(); // 실행해서 결과를 받는다.
			
			while(rset.next()) {
				// 조회 결과 컬럼 값을 DTO필드에 담는 작업 및 리스트에 요소로 추가
				UserDTO user = new UserDTO();
				user.setUserNo(rset.getInt("USER_NO"));
				user.setUserId(rset.getString("USER_ID"));
				user.setUserPW(rset.getString("USER_PW"));
				user.setUserName(rset.getString("USER_NAME"));
				user.setEnrollDate(rset.getDate("ENROLL_DATE"));

				list.add(user);
			}
		
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("오타가 나지 않았나요? 확인하셨나요? 두 번 봤나요?");
		} finally {
			JdbcUtil.dqlClose(rset, pstmt, conn);
			
		}
		
		
		return list;
	}
	
	
	/**
	 * @param user 사용자가 입력한 아이디/비밀번호/이름이 각각 필드에 대입되어있음
	 * @return 추가 결과 int 로 반환(0: 실패 / 나머지: 성공)
	 */
	public int insertUser(UserDTO user, Connection conn) {
		
//		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = """
				INSERT INTO TB_USER (USER_ID, USER_PW, USER_NAME)
				VALUES (?,?,?)
				""";
		
		try {
//			conn= DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			// conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPW());
			pstmt.setString(3, user.getUserName());
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace(); // 개발할 때 디버깅 용. 서비스할 때는 이렇게 쓰지마삼. 예외처리 해야됨.
		} finally {
			JdbcUtil.dmlClose(pstmt, conn);
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param userNo 입력받은 유저번호
	 * @param conn DB 커넥션
	 * @return 검색결과 UserDTO로 반환
	 */
	public UserDTO findByUserNo(int userNo, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		UserDTO user = null;
		
		String sql = """
				SELECT USER_NO, USER_ID, USER_PW, USER_NAME, ENROLL_DATE FROM TB_USER WHERE USER_NO = ?
				""";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				user = new UserDTO();
				user.setUserNo(rset.getInt("USER_NO"));
				user.setUserId(rset.getString("USER_ID"));
				user.setUserPW(rset.getString("USER_PW"));
				user.setUserName(rset.getString("USER_NAME"));
				user.setEnrollDate(rset.getDate("ENROLL_DATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.dqlClose(rset, pstmt, conn);
		}
			
		return user;
	}
	
	/**
	 * USER_ID로 회원조회
	 * @param userId 입력받은 아이디
	 * @param conn DB연결
	 * @return 조회된 유저 정보
	 */
	public UserDTO findByUserId(String userId, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		UserDTO user = null;
		
		String sql = """
				SELECT USER_NO, USER_ID, USER_PW, USER_NAME, ENROLL_DATE FROM TB_USER WHERE USER_ID = ?
				""";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				user = new UserDTO();
				user.setUserNo(rset.getInt("USER_NO"));
				user.setUserId(rset.getString("USER_ID"));
				user.setUserPW(rset.getString("USER_PW"));
				user.setUserName(rset.getString("USER_NAME"));
				user.setEnrollDate(rset.getDate("ENROLL_DATE"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.dqlClose(rset, pstmt, conn);
		}
		
		return user;
	}
	
	
	/**
	 * 비밀번호 변경
	 * @param userId 유저 아이디
	 * @param userPw 바꿀 비밀번호
	 * @param conn 
	 * @return excuteUpdate 결과값
	 */
	public int modifyPw(String userId, String userPw, Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		// USER_ID가 PK든 UN든 걸려있다고 가정함(지금없음)
		String sql =  """
				UPDATE TB_USER SET
					USER_PW = ?
				WHERE 
					USER_ID = ?
				""";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userPw);
			pstmt.setString(2, userId);
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.dmlClose(pstmt, conn);
		}
		
		return result;
	}
	
	
	/**
	 * 회원 삭제
	 * @param user UserDTO 객체
	 * @param conn 
	 * @return excuteUpdate 결과값
	 */
	public int deleteUser(UserDTO user, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = """
				DELETE FROM TB_USER WHERE USER_ID = ?
				""";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getUserId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.dmlClose(pstmt, conn);
		}
		
		return result;
	}
	
}
















