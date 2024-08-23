package com.itwillbs.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;
import com.itwillbs.util.JSFunction;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : MemberDeleteAction_execute() 실행 ");
		
		
		
		// 로그인 체크 
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		ActionForward forward = new ActionForward();
		if (id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		
		
		// 한글 처리 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 회원정보 비밀번호를 저장(전달받은 파라메터)
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPw(request.getParameter("pw"));
				
		// MemberDAO 객체 - 회원정보 삭제
		MemberDAO dao = new MemberDAO();
		
		int result = dao.deleteMember(dto);
		System.out.println("result : " + result);
		
		// 회원정보 삭제 결과에 따른 페이지 이동 (JS)
		
		// 수정 결과에 따른 페이지 이동 (JS)
		if( result == -1) {
			JSFunction.alertAndBack(response , " 삭제 X - 회원정보 없음" );
			return null;
		}
		else if( result == 0 ) {
			JSFunction.alertAndBack(response , " 삭제 X - 비밀번호 오류 !" );
			return null;
		}else {
			session.invalidate();
			JSFunction.alertAndLocation(response, " 삭제 O - 정상 삭제 " ,"./Main.me" );
		}
		
		
		return null;
	}

}
