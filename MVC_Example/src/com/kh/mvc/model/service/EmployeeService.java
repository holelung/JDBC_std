package com.kh.mvc.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.mvc.model.dao.EmployeeDAO;
import com.kh.mvc.model.dto.DynamicDTO;
import com.kh.mvc.model.dto.EmployeeDTO;
import com.kh.mvc.util.Columns;
import com.kh.mvc.util.JdbcUtil;

public class EmployeeService {

	private EmployeeDAO employeeDao = new EmployeeDAO();
	private Columns cols = new Columns();
	
	/**
	 * C1,C2,..으로 저장된 리스트를 "컬럼명"으로된 리스트로 변경
	 * @param columns 사용자에게 입력받은 List
	 * @return 기존 리스트 값의 인덱스 번호와 맞는 컬럼명으로 변경된 리스트
	 */
	public List<String> checkColumns(List<String> columns) {
		// 새로저장할 list
		List<String> list = new ArrayList<>();
		
		for(String n : columns) {
			list.add(cols.getCols(n));
		}
		
		return list;
	}
	
	/**
	 * SQL구문 생성및 Connection 연결 메소드
	 * @param list C1,C2...로 입력된 값을 인덱스 순서에 맞춰서 String Type의 컬럼명의 List로 변환해서 받아옴
	 * @return Dao에서 받은 List<DynamicDTO>를 반환
	 */
	public List<DynamicDTO> selectByInput(List<String> list){
		
		Connection conn = JdbcUtil.getConnect();
		
		// SQL 구문 생성
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		for(String col : list) {
			sql.append(col).append(", ");
		}
		sql.deleteCharAt(sql.lastIndexOf(", "));
		sql.append("FROM EMPLOYEE");
		
		return employeeDao.selectByInput(conn, sql.toString());
	}
	
	
	
}
