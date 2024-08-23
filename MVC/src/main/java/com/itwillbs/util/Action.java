package com.itwillbs.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	// 상수
	// 추상메서드
	
	// execute() 정의 : 실행시 request, response 정보가 필요하고.
	//                  실행후 ActionForward 객체 리턴
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)throws Exception ;
	
	
}
