package com.itwillbs.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

/**
 * Controller
 * 
 * 사용자의 요청을 가장먼저 처리하는 객체 (서블릿 형태)
 * Servlet은 중간단계
 * 
 * jsp가 servlet이 되고 자바가 되는 중간단계
 * 
 * 
 * 
 * 
 */


public class MemberFrontController extends HttpServlet {


	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : MemberFrontController - doProcess() 실행");
		System.out.println(" C : GET/POST 상관없이 모두 실행 \n\n");
		
		// 가상주소
		// http://localhost:8088/MVC/test.me (URL)
		//                      /MVC/test.me (URI) <-
		System.out.println(" C :-------------------- 1. 가상주소 계산 - 시작 ----------------");
		
		// 가상주소를 가져오기 
		// -> requestURI : /MVC/MemberJoin.me
		String requestURI = request.getRequestURI();
		System.out.println(" C : requestURI : "+ requestURI);
		
		// 프로젝트(컨텍스트)명을 가져오기 
		// -> CTXPath : /MVC
		String CTXPath = request.getContextPath();
		System.out.println(" C : CTXPath : " + CTXPath);
		
		// 가상주소(URI) - 프로젝트(컨텍스트)명 
		// -> command : /MemberJoin.me
		String command = requestURI.substring(CTXPath.length());
		System.out.println(" C : command : " + command);
		System.out.println(" C :-------------------- 1. 가상주소 계산 - 끝 ----------------");
		
		
		System.out.println(" C :-------------------- 2. 가상주소 매핑 - 시작 ----------------");
		
		Action action = null;
		ActionForward forward = null; // 페이지 이동정보(티켓)
		
		// 회원가입 - 정보 입력
		if(command.equals("/MemberJoin.me")) {
			System.out.println(" C : /MemberJoin.me 매핑");
			System.out.println(" C : 패턴 1 - DB사용X, view 페이지 이동 ");
			
			forward = new ActionForward();
			forward.setPath("./member/insertForm.jsp");
			forward.setRedirect(false);
			System.out.println(" C : " + forward.toString());
			
		} 
		else if(command.equals("/MemberJoinAction.me")) {
			System.out.println(" C : /MemberJoinAction.me 매핑 ");
			System.out.println(" C : 패턴 2 - DB사용O,페이지 이동 ");
			
			// MemberJoinAction 객체 생성.
			// MemberJoinAction mjAction = new MemberJoinAction(); -> 강한 결합
			// 의존 관계.
			// 인터페이스를 업캐스팅 해서 쓰는 이유를 찾아보자리
			action = new MemberJoinAction();
			
			try {
				// mjAction.execute(request, response);
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(" C : " + forward);
			
		} 
		else if (command.equals("/MemberLogin.me")) {
			System.out.println(" C : /MemberLogin.me 매핑 ");
			System.out.println(" C : 패턴 1 - DB사용 X, View 페이지 이동 ");
			
			forward = new ActionForward();
			forward.setPath("./member/loginForm.jsp");
			forward.setRedirect(false);
			System.out.println(" C : " + forward.toString());
			
		} 
		else if (command.equals("/MemberLoginAction.me")) {
			System.out.println(" C : /MemberLoginAction.me 매핑 ");
			System.out.println(" C : 패턴 2 - DB사용 O ,페이지 이동 ");
			
			
			// MemberLoginAction 객체 생성.
			action = new MemberLoginAction();
			
			try {
				// 객체의 execute() 메서드 호출, forward 리턴
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(" C : " + forward);
		}
		else if(command.equals("/Main.me")) {
			System.out.println(" C : /Main.me 매핑 ");
			System.out.println(" C : 패턴 1 - DB사용 X, View 페이지 이동 ");
			
			forward = new ActionForward();
			forward.setPath("./member/main.jsp");
			forward.setRedirect(false);
			System.out.println(" C : " + forward.toString());
			
		} 
		else if(command.equals("/MemberLogout.me")) {
			
			System.out.println(" C : /MemberLogout.me 매핑 ");
			System.out.println(" C : 패턴 2 - 작업 처리O(DB사용 X), View 페이지 이동 ");
			
			
			// MemberLogoutAction() 객체 생성
			action = new MemberLogoutAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/MemberInfo.me")) {
			System.out.println(" C : /MemberInfo.me 매핑 ");
			System.out.println(" C : 패턴 3 - 작업 처리O(DB사용 X), View 페이지 출력 ");
			
			
			// MemberInfoAction() 객체 생성
			action = new MemberInfoAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		else if(command.equals("/MemberUpdate.me")) {
			System.out.println(" C : /MemberUpdate.me 매핑 ");
			System.out.println(" C : 패턴 3 - 작업 처리O(DB사용 X), View 페이지 출력 ");
			
			
			// MemberInfoAction() 객체 생성
			action = new MemberUpdateAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(command.equals("/MemberUpdateProAction.me")) {
			System.out.println(" C : /MemberUpdateProAction.me 매핑 ");
			System.out.println(" C : 패턴 2 - 작업 처리O(DB사용 X), View 페이지 이동 ");
			
			
			// MemberInfoAction() 객체 생성
			action = new MemberUpdateProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(command.equals("/MemberDelete.me")) {
			System.out.println(" C : /MemberDelete.me 매핑 ");
			System.out.println(" C : 패턴 1 - 작업 처리O(DB사용 X), View 페이지 출력 ");
			
						
			forward = new ActionForward();
			forward.setPath("./member/deleteForm.jsp");
			forward.setRedirect(false);
					
		}
		else if(command.equals("/MemberDeletePro.me")) {
			System.out.println(" C : /MemberDelete.me 매핑 ");
			System.out.println(" C : 패턴 2 - 작업 처리O(DB사용 O), View 페이지 이동 ");
			
			action = new MemberDeleteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/MemberList.me")) {
			System.out.println(" C : /MemberList.me 매핑 ");
			System.out.println(" C : 패턴 3 - 작업 처리O(DB사용 O), View 페이지 출력 ");
			
			action = new MemberListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		System.out.println(" C :-------------------- 2. 가상주소 매핑 - 끝 ----------------");
		
		System.out.println(" C :-------------------- 3. 가상주소 이동 - 시작 ----------------");
		if(forward != null ) {
			// 이동정보 객체가 있다. (티켓이 있음)
			System.out.println(" C : "+ forward.isRedirect() + " 방식으로, " + forward.getPath()+" 이동 " );
			if(forward.isRedirect()) {
				// true
				response.sendRedirect(forward.getPath());
			}else {
				// false
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				
				dis.forward(request, response);
			}
			
		}
		
		
		
		System.out.println(" C :-------------------- 3. 가상주소 이동 - 끝 ---------------- \n\n");
		
		System.out.println(" C : doProcess() 동작 끝 \n\n");
	} // doProcess()
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : MemberFrontController - doGet() 실행");
		doProcess(request, response);
		
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : MemberFrontController - doPost() 실행");
		doProcess(request, response);
		
		
		
	}

	
	
}
