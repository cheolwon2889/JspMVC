package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

public class BoardWriteAction implements Action{

	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : BoardWriteAction_execute() 호출 ");
		
		// 한글처리 인코딩
		// request.setCharacterEncoding("UTF-8"); 이거 말고 
		// web.xml 파일에 인코딩 했음.
		
		// 전달 정보 (파라메터) BoardDTO 저장
		// System.out.println(" M : " +request.getParameter("name"));
		
		// 작성자, 비밀번호, 제목, 내용
		BoardDTO dto = new BoardDTO();
		
		dto.setName(request.getParameter("name"));
		dto.setPass(request.getParameter("pass"));
		dto.setSubject(request.getParameter("subject"));
		dto.setContent(request.getParameter("content"));
		
		// + IP 주소 우리가 못만듬. 
		// getRemoteAddr 클라이언트의 ip 주소를 가져올 수 있음.
		// 127.0.0.1 -> IPv4
		// 0:0:0:0:0:0:0:1 -> IPv6
		dto.setIp(request.getRemoteAddr());
		System.out.println("dto : "+ dto );
		
		BoardDAO dao = new BoardDAO();
		dao.insertBoard(dto);
		
		System.out.println();
		
		
		ActionForward forward = new ActionForward();
		
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true);
		
		return forward;
	}
}
