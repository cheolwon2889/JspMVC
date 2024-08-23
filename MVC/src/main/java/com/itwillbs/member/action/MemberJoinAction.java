package com.itwillbs.member.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

/**
 * MemberJoinAction
 * 회원가입에 대한 처리동작을 하는 클래스 (insertPro.jsp 동작을 대신 수행)
 * 
 * 
 * 왜 클래스 대신 인터페이스를 쓰는걸까?
 * 
 * 강제로 재정의 해서 사용하게 할려고
 * 
 * 클래스로 하면 깜빡할 수 있으니.
 */
public class MemberJoinAction implements Action {
	
	
	@Override
	// 컨트롤러에서 받아오네
	// request ,response
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : MemberJoinAction_execute() 실행 ");
		
		// insertPro.jsp 페이지에서 차리한 동작을 수행.
		
		// 한글처리 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 전달정보(파라메터) 저장 => MemberDTO에 저장
		// String id = request.getParameter("id"); X
		MemberDTO mdto = new MemberDTO();
		
		mdto.setAge(Integer.parseInt(request.getParameter("age")));
		mdto.setEmail(request.getParameter("email"));
		mdto.setGender(request.getParameter("gender"));
		mdto.setId(request.getParameter("id"));
		mdto.setName(request.getParameter("name"));
		mdto.setPw(request.getParameter("pw"));
		mdto.setRegdate(new Timestamp(System.currentTimeMillis()));
		
		System.out.println(" M : 전달정보 " + mdto);
		
		// DB연결 - insert 처리
		// DAO 객체 생성
		MemberDAO mdao = new MemberDAO();
		mdao.insertMember(mdto);
		System.out.println(" M : DAO처리 완료 ");
		
		// 로그인 페이지로 이동 (x) => 페이지 이동 정보 생성 
		// 실제 이동이 아닐 수 있음.
		ActionForward forward = new ActionForward();
		forward.setPath("./MemberLogin.me");
		// sendRedirect 기준은 현재의 주소창에 위치에 따라 true인지 false인지 결정됨.
		forward.setRedirect(true);
		System.out.println(" M : "+ forward);
		
		return forward;
	}
	
}
