package com.itwillbs.member.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;
import com.itwillbs.util.JSFunction;

public class MemberUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : MemberUpdateProAction_execute() 실행 ");
		
		
		// 로그인 여부 확인.
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		ActionForward forward = new ActionForward();
		if(id == null ) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// 한글처리 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 수정 정보(전달된 파라메터) 저장.
		MemberDTO dto = new MemberDTO();
		
		dto.setAge(Integer.parseInt(request.getParameter("age")));
		dto.setEmail(request.getParameter("email"));
		dto.setGender(request.getParameter("gender"));
		dto.setId(request.getParameter("id"));
		dto.setName(request.getParameter("name"));
		dto.setPw(request.getParameter("pw"));
		
		System.out.println(" M : MemberDTO " + dto);
		
		MemberDAO dao = new MemberDAO();
		
		int result = dao.updateMember(dto);
		System.out.println("result : " + result );
		
		
		// 수정 결과에 따른 페이지 이동 (JS)
		if(result == -1) {
			JSFunction.alertAndBack(response , " 수정 X - 아이디 정보 없음" );
			return null;
		}
		else if( result == 0 ) {
			JSFunction.alertAndBack(response , " 수정 X - 비밀번호 오류 !" );
			return null;
		}else {
			JSFunction.alertAndLocation(response, " 수정 O - 정상 수정 " ,"./Main.me" );
		}
		
		return null;
	}

}
