package com.kh.mvc.controller;

import java.util.List;

import com.kh.mvc.model.dto.DynamicDTO;
import com.kh.mvc.model.dto.EmployeeDTO;
import com.kh.mvc.model.service.EmployeeService;



public class EmployeeController {
	private EmployeeService employeeService = new EmployeeService();
	
	// 인덱스 번호로 받은 Input을 인덱스번호에 해당하는 컬럼명으로바꾸기 위한 서비스로 전달
	public List<String> checkColumns(List<String> column) {
		return employeeService.checkColumns(column);
	}
	
	// 컬럼명으로 바꾼 List를 통해 sql문을 생성하기 위한 service로 전달
	public List<DynamicDTO> selectByInput(List<String> list) {	
		
		return employeeService.selectByInput(list);
	}
}
