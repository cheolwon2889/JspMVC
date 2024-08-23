package com.itwillbs.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

public class MemberLogoutAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : MemberLogoutAction_execute() 실행 ");
		
		System.out.println(" M : 로그아웃 처리 로직 수행 ");
		HttpSession session = request.getSession();
		session.invalidate();
		
		// Main 페이지로 이동 js
		response.setContentType(" text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.print("<script>");
		out.print(" alert('회원님의 정보가 안전하게 로그아웃 되었습니다.'); ");
		out.print(" location.href= './Main.me'; ");
		out.print("</script>");
		out.close();
		return null;
		
		
		
		
		// 페이지 이동(정보 생성)
		
		// ActionForward forward = new ActionForward();
		// forward.setPath("./MemberLogin.me");
		// forward.setRedirect(true);
		// return forward;
				
	}
}
