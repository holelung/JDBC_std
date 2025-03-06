package com.kh.mvc.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.mvc.model.dao.UserDAO;
import com.kh.mvc.model.dto.UserDTO;
import com.kh.mvc.util.JdbcUtil;

/**
 * Service : 비즈니스 로직 / 의사결정코드를 작성하는 부분
 * 
 * Controller 에서는 Service단의 메소드를 호출
 * Service에서 실질적인 동작시켜야 하는 코드를 작성
 * => Service단을 추가함으로 DAO는 순수하게 SQL문을 처리하는 부분만남겨놓을 것
 */
public class MemberService {
	
	private UserDAO userDao = new UserDAO();
	
	// 회원 전체조회
	public List<UserDTO> findAll() {
		
		Connection conn = JdbcUtil.getConnect(); 
		
		List<UserDTO> list = userDao.findAll(conn);
		
		
		return list;
	}
	
	// 회원 추가
	public int insertUser(UserDTO user) {
		
		Connection conn = JdbcUtil.getConnect();
		int result = userDao.insertUser(user, conn);
		
		return result;
	}
	
	// 회원번호로 회원 조회
	public UserDTO findByUserNo(int userNo) {
		Connection conn = JdbcUtil.getConnect();
		
		return userDao.findByUserNo(userNo, conn);
	}
	
	
	// 아이디로 회원 조회
	public UserDTO findByUserId(String userId) {
		Connection conn = JdbcUtil.getConnect();
		
		return userDao.findByUserId(userId, conn);
	}
	
	// 비밀 번호 변경
	public int modifyPw(UserDTO user, String userPw) {
		
		Connection conn = JdbcUtil.getConnect();
		return userDao.modifyPw(user.getUserId(), userPw, conn);
	}
	
	public int deleteUser(UserDTO user) {
		Connection conn = JdbcUtil.getConnect();
		return userDao.deleteUser(user, conn);
	}
}
