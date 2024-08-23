package com.itwillbs.member.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : MemberListAction_execute()실행");
		
		// 로그인 체크 (로그인 O 아이디 "admin")
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		ActionForward forward = new ActionForward();
		if(id == null || !id.equals("admin")) {
			forward.setPath("./Main.me");
			forward.setRedirect(true);
			return forward;
		}
		
		MemberDAO dao = new MemberDAO();
		System.out.println(" M : " + dao.getMemberList().size());
		request.setAttribute("dto", dao.getMemberList());
		
		forward.setPath("./member/list.jsp");
		forward.setRedirect(false);
		
		
		
		return forward;
	}

}
