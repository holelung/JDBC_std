package com.kh.mvc.controller;

import java.util.List;

import com.kh.mvc.model.dao.UserDAO;
import com.kh.mvc.model.dto.UserDTO;

/**
 * View에서 온 요청을 처리해주는 클래스
 * 메서드로 전달된 데이터값을 가공처리한 후 DAO로 전달
 * DAO로부터 반환받은 결과를 View에 반환
 */
public class UserController {
	
	private UserDAO userDao = new UserDAO();
	
	// 회원 전체 정보를 조회해주는 기능 
	public List<UserDTO> findAll() {
		return userDao.findAll();
	}
	
	public int insertUser(String userId, String userPw, String userName) {
		UserDTO user = new UserDTO();
		user.setUserId(userId);
		user.setUserPW(userPw);
		user.setUserName(userName);
		return userDao.insertUser(user);
		
	}
}