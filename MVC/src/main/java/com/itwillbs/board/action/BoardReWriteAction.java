package com.itwillbs.board.action;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

public class BoardReWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : BoardReWriteAction_execute () 실행");
		
		
		// 한글처리 인코딩
		// 전달된 정보 (파라메터) 저장
		// bno, re_ref, re_lev, re_seq, pageNum, name, subject, pass,content
		// BoardDTO 객체
		BoardDTO dto = new BoardDTO();
		dto.setBno(Integer.parseInt(request.getParameter("bno")));
		dto.setRe_lev(Integer.parseInt(request.getParameter("re_lev")));
		dto.setRe_ref(Integer.parseInt(request.getParameter("re_ref")));
		dto.setRe_seq(Integer.parseInt(request.getParameter("re_seq")));
		
		dto.setName(request.getParameter("name"));
		dto.setPass(request.getParameter("pass"));
		dto.setSubject(request.getParameter("subject"));
		dto.setContent(request.getParameter("content"));
		// IP
		dto.setIp(request.getRemoteAddr());
		
		String pageNum = request.getParameter("pageNum");
		System.out.println(" M : dto : " +dto);
		
		// BoardDAO 객체 생성 -
		BoardDAO dao = new BoardDAO();
		dao.reInsertBoard(dto);
		
		// 페이지 이동준비
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo?pageNum=" +pageNum);
		forward.setRedirect(true);
		
		return forward;
	}

}
