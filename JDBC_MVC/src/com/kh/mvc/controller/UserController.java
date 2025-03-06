package com.kh.mvc.controller;

import java.util.List;

import com.kh.mvc.model.dao.UserDAO;
import com.kh.mvc.model.dto.UserDTO;
import com.kh.mvc.model.service.MemberService;

/**
 * View에서 온 요청을 처리해주는 클래스
 * 메서드로 전달된 데이터값을 가공처리한 후 DAO로 전달
 * DAO로부터 반환받은 결과를 View에 반환
 */
public class UserController {
	
	private UserDAO userDao = new UserDAO();
	private MemberService memberService = new MemberService();
	
	// 회원 전체 정보를 조회해주는 기능 
	public List<UserDTO> findAll() {
		return memberService.findAll();
	}
	
	// 회원 추가 기능
	public int insertUser(String userId, String userPw, String userName) {
		UserDTO user = new UserDTO();
		user.setUserId(userId);
		user.setUserPW(userPw);
		user.setUserName(userName);
		int result = memberService.insertUser(user);
		user = null; // 명시적으로 제거 (훌륭한 개발자라면..)
		return result;
		
	}
	
	// 회원 번호로 회원 조회기능
	public UserDTO findByUserNo(int userNo) {
		
		return memberService.findByUserNo(userNo);
		
	}
	
	// 아이디로 회원 조회
	public UserDTO findByUserId(String userId) {
		
		return memberService.findByUserId(userId);
	}
	
	// 비밀번호 변경
	public int modifyPw(UserDTO user, String userPw) {
		
		return memberService.modifyPw(user, userPw);
	}
	
	// 회원 삭제
	public int deleteUser(UserDTO user) {
		return memberService.deleteUser(user);
	}
	
	
}