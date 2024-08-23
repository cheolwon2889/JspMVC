package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;
import com.itwillbs.util.JSFunction;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : BoardDeleteAction_execute 실행 ");
		
		HttpSession session = request.getSession();
		
		
		String id = session.getId();
		ActionForward forward = null;
		if(id == null ) {
			forward = new ActionForward();
			forward.setPath("./BoardList.bo");
			forward.setRedirect(true);
			return forward;
		}
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		String pageNum = request.getParameter("pageNum");
		System.out.println(" M : bno : "+ bno +", pageNum : " + pageNum );
		
		
		
		BoardDTO dto = new BoardDTO();
		dto.setBno(bno);
		dto.setPass(request.getParameter("pass"));
		
		BoardDAO dao = new BoardDAO();
		int result = dao.deleteBoard(dto);
		
		if(result == -1) {
			JSFunction.alertAndBack(response , " 삭제 X - 글정보 없음" );
			return null;
		}
		else if( result == 0 ) {
			JSFunction.alertAndBack(response , " 삭제 X - 비밀번호 오류 !" );
			return null;
		}else {
			JSFunction.alertAndLocation(response, " 삭제 O - 정상 삭제 " ,"./BoardList.bo?pageNum="+ pageNum +"" );
			// 오버로딩 해서 하나 더 만들면 되지 않을까?
			// JSFunction.alertAndLocation(response, " 수정 O - 정상 수정 " ,"./BoardContent.bo?bno="+bno+"&pageNum="+pageNum+"");
		}
		
		
		
		
		
		return null;
	}

}
