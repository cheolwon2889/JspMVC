package com.itwillbs.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * JSFuncion : 자바스크립트 페이지 이동을 처리하는 객체
 */
public class JSFunction {
	
	// alert + back
	public static void alertAndBack(HttpServletResponse response, String msg) {
		
		try {
			response.setContentType(" text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
//			out.println("<script>");
//			out.println(" alert(' "+ msg +" '); ");
//			out.println("history.back();");
//			out.println("</script>");
//			out.close();
			
			String code = "";
			code += "<script>";
			code += "alert(' "+ msg +" ');";
			code += "history.back();";
			code += "</script>";
			
			out.print(code);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// alert + location
	public static void alertAndLocation(HttpServletResponse response, String msg, String location) {
		try {
			response.setContentType(" text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			// int bno= 17;
			// String pageNum = "1";
			// code += " location.href='"+ location +"?bno="+ bno +"&pageNum="+pageNum+"' ;";
			
			String code = "";
			code += "<script>";
			code += "alert(' "+ msg +" ');";
			code += " location.href='"+ location +"' ;";
			code += "</script>";
			
			out.print(code);
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
