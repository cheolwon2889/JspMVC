package com.itwillbs.member.action;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

public class MemberInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : MemberInfoAction_execute() 실행");
		// 한글처리 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 세션정보 확인(로그인 체크)
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// 사용자의 아이디정보가 있음.
		// MemberDAO 객체 생성
		MemberDAO dao = new MemberDAO();
		// - 사용자 아이디에 해당하는 회원정보를 조회하는 메서드 생성.
		MemberDTO dto = dao.getMember(id);
		System.out.println(" M : "+ dto);
		
		// 페이지 출력 (화면, 내용)
		// 결과 데이터를 request 영역객체에 저장
		request.setAttribute("dto", dto);
		
		forward.setPath("./member/info.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
