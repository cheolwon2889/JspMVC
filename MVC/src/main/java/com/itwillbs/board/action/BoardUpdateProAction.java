package com.itwillbs.board.action;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;
import com.itwillbs.util.JSFunction;

public class BoardUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : BoardUpdateProAction_execute() 실행 ");
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		String pageNum = request.getParameter("pageNum");
		//System.out.println(" M : bno = " + bno+ ", pageNum = " + pageNum);
		
		BoardDTO dto = new BoardDTO();
		
		dto.setBno(bno);
		dto.setContent(request.getParameter("content"));
		dto.setName(request.getParameter("name"));
		dto.setPass(request.getParameter("pass"));
		dto.setSubject(request.getParameter("subject"));
		System.out.println(" M : 수정할 내용 dto : " + dto);
		
		BoardDAO dao = new BoardDAO();
		int result = dao.updateBoard(dto);
		System.out.println(" M : result = " + result);

		//                  문제 해결.
		//         파라미터를 URL에 추가하여 이동
		// code += " location.href='"+ location +"?bno="+ bno +"&pageNum="+pageNum+"' ;";
		// =================================================
		// ActionForward forward = new ActionForward();		
		// 포워딩이 아니기 때문에 request영역에 보낼 수 없는데...
		// request.setAttribute("bno", bno);
		// request.setAttribute("pageNum", pageNum);
		// forward.setPath("./BoardContent.bo");
		// forward.setRedirect(true);
		// return forward;
		// ==================================================
		
		// 수정 결과에 따른 페이지 이동 (JS)
		
		if(result == -1) {
			JSFunction.alertAndBack(response , " 수정 X - 글정보 없음" );
			return null;
		}
		else if( result == 0 ) {
			JSFunction.alertAndBack(response , " 수정 X - 비밀번호 오류 !" );
			return null;
		}else {
			JSFunction.alertAndLocation(response, " 수정 O - 정상 수정 " ,"./BoardList.bo?pageNum="+ pageNum +"" );
			// 오버로딩 해서 하나 더 만들면 되지 않을까?
			// JSFunction.alertAndLocation(response, " 수정 O - 정상 수정 " ,"./BoardContent.bo?bno="+bno+"&pageNum="+pageNum+"");
		}
		
		return null;
	}

}
