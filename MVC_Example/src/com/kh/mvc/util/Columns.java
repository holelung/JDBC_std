package com.kh.mvc.util;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Columns {

	public final String C1 = "EMP_ID";
	public final String C2 = "EMP_NAME";
	public final String C3 = "EMP_NO";
	public final String C4 = "EMAIL";
	public final String C5 = "PHONE";
	public final String C6 = "DEPT_CODE";
	public final String C7 = "JOB_CODE";
	public final String C8 = "SAL_LEVEL";
	public final String C9 = "SALARY";
	public final String C10 = "BONUS";
	public final String C11 = "MANAGER_ID";
	public final String C12 = "HIRE_DATE";
	public final String C13 = "ENT_DATE";
	public final String C14 = "ENT_YN";
	
	
	
	public String getCols(String s) {
		if(s.equals("C1")) { 
			return getC1();
		}
		if(s.equals("C2")) return getC2();
		if(s.equals("C3")) return getC3();
		if(s.equals("C4")) return getC4();
		if(s.equals("C5")) return getC5();
		if(s.equals("C6")) return getC6();
		if(s.equals("C7")) return getC7();
		if(s.equals("C8")) return getC8();
		if(s.equals("C9")) return getC9();
		if(s.equals("C10")) return getC10();
		if(s.equals("C11")) return getC11();
		if(s.equals("C12")) return getC12();
		if(s.equals("C13")) return getC13();
		if(s.equals("C14")) return getC14();
		
		return null;
	}
	
	
	public String getC1() {
		return C1;
	}
	public String getC2() {
		return C2;
	}
	public String getC3() {
		return C3;
	}
	public String getC4() {
		return C4;
	}
	public String getC5() {
		return C5;
	}
	public String getC6() {
		return C6;
	}
	public String getC7() {
		return C7;
	}
	public String getC8() {
		return C8;
	}
	public String getC9() {
		return C9;
	}
	public String getC10() {
		return C10;
	}
	public String getC11() {
		return C11;
	}
	public String getC12() {
		return C12;
	}
	public String getC13() {
		return C13;
	}
	public String getC14() {
		return C14;
	}
	
	
}

