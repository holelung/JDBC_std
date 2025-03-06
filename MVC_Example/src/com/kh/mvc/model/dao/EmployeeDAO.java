package com.kh.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.mvc.model.dto.DynamicDTO;
import com.kh.mvc.util.JdbcUtil;

public class EmployeeDAO {
	
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
		} catch (ClassNotFoundException e) {
			System.out.println("ojdbc확인");
		}
	}

	/**
	 * 입력받은 컬럼명을 특정하여 SELECT 문을 실행하는 메소드
	 * @param conn 연결
	 * @param sql StringBuilder로 작성한 sql문을 toString()으로 변환하여 받음
	 * @return List<DynamicDTO>를 반환
	 */
	public List<DynamicDTO> selectByInput(Connection conn, String sql) {
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 넘겨줄 list 생성
		List<DynamicDTO> results = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				 DynamicDTO data = new DynamicDTO();
				 // metaData 출력을 위한 변수
				 ResultSetMetaData metaData = rs.getMetaData();
				
				for(int i=1; i<=metaData.getColumnCount(); i++) {
					String columnName = metaData.getColumnName(i);
					Object value = rs.getObject(i);
					data.set(columnName, value);
				}
				
				results.add(data);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.dqlClose(rs, pstmt, conn);
		}
		
		return results;
	}
	
	
	
}
