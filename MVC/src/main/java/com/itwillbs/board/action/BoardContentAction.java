package com.itwillbs.board.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

public class BoardContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : BoardContentAction_execute() 실행 ");
		
		// 작성된 글의 내용을 화면에 출력
		
		// 전달된 정보(파라메터) 저장(bno, pageNum)
		int bno = Integer.parseInt(request.getParameter("bno"));
		String pageNum = request.getParameter("pageNum");
		
		System.out.println(" M : bno:" + bno + ", pageNum : " + pageNum);
		
		// BoardDAO 객체 생성
		// DAO 안에서는 쿼리만 실행 하게 최소화 한다.
		// 연산을 해야한다면 Action에서 연산을 한 후 dao에 전달해서 실행을 하게 한다.
		BoardDAO dao = new BoardDAO();
		
		// 조회수를 증가 가능한 상태인지 체크
		HttpSession session = request.getSession();
		boolean isUpdate = (Boolean)session.getAttribute("isUpdate");
		
		if(isUpdate) {
			// DAO 객체 - 조회수 1증가(update)
			dao.updateReadcount(bno);
			System.out.println(" M : 조회수 1증가 완료! ");
			session.setAttribute("isUpdate", false);
		}
		
		
		
		
		// DAO 객체 - 작성된 글의 정보를 가져오기(select)
		
		BoardDTO dto =  dao.getBoard(bno);
		System.out.println(" M : DTO : " + dto);
				
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
		
		
		// ===================== 변수명과 함수명이 같으면 좋지 않다.============================
		// ===================== 변수명과 함수명이 같으면 좋지 않다.============================
		
		// 이전글 제목 조회
		String prevTitle = dao.prevSubject(bno);
		System.out.println(" M : prevTitle " + prevTitle  );
		// 다음글 제목 조회
		String nextTitle = dao.nextSubject(bno);
		System.out.println(" M : nextTitle " + nextTitle  );
		
		// ===================== 변수명과 함수명이 같으면 좋지 않다.============================
		// ===================== 변수명과 함수명이 같으면 좋지 않다.============================
		// 공백인지 아닌지 비교
		if (prevTitle != "") {
			request.setAttribute("prevTitle", prevTitle);
		}
		if (nextTitle != "") {
			request.setAttribute("nextTitle", nextTitle);
		}
		
		
		
		ActionForward forward = new ActionForward();
		
		forward.setPath("./board/boardContent.jsp");
		forward.setRedirect(false);
		
		
		
		return forward;
	}

}
