package com.itwillbs.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

public class MemberLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : MemberLoginAction_execute() 호출 ");
		
		// 한글처리 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 전달된 정보(파라메터)를 저장
		MemberDTO dto = new MemberDTO();
		
		dto.setId(request.getParameter("id"));
		dto.setPw(request.getParameter("pw"));
		
		System.out.println(" M : " +dto);
		
		MemberDAO dao = new MemberDAO();
		
		// 로그인 여부 체크 메서드 호출
		
		int result = dao.loginCheck(dto);
		
		System.out.println(" M : result : " + result);
		
		
		// 아이디 없을때 ( -1 ) => JS 페이지 이동
		if(result == -1 ) {
			response.setContentType("text/html; charset=UTF-8");
			System.out.println(" 살려주셈 " + result);
			
			//response.getWriter().append("Hello!");
			
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('아이디 정보 없음! ')");
			out.println("history.back();");
			out.println("</script>");
			// 입출력 관련 된 애들이 메모리 누수가 큼.
			// io 들은 예외 처리 및 종료를 항상 해줘야 한다.
			out.close();
			return null;
		}
		// 비밀번호 오류 ( 0 ) => JS 페이지 이동
		if(result == 0 ) {
			response.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert(' 비밀번호 오류 ! ')");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null; // js 페이지 이동 O  , 컨트롤러 페이지 이동 X
		}
		// 정상처리(1)
		// 사용자의 아이디를 세션영역에 저장.
		HttpSession session = request.getSession();
		session.setAttribute("id", request.getParameter("id"));
		
		
		// 페이지 이동(정보 생성)
		
		ActionForward forward = new ActionForward();
		forward.setPath("./Main.me");
		forward.setRedirect(true);
		return forward;
		
		
		
		
	}

}
