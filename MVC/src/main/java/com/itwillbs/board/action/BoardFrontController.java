package com.itwillbs.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

@WebServlet("*.bo")
public class BoardFrontController extends HttpServlet {

	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : MemberFrontController - doProcess() 실행");
		System.out.println(" C : GET/POST 상관없이 모두 실행 \n\n");
		
		// 가상주소
		// http://localhost:8088/MVC/test.me (URL)
		//                      /MVC/test.me (URI) <-
		System.out.println(" C :-------------------- 1. 가상주소 계산 - 시작 ----------------");
		String requestURI = request.getRequestURI();
		System.out.println(" C : requestURI : "+ requestURI);
		
		// 프로젝트(컨텍스트)명을 가져오기 
		String CTXPath = request.getContextPath();
		System.out.println(" C : CTXPath : " + CTXPath);
		
		String command = requestURI.substring(CTXPath.length());
		System.out.println(" C : command : " + command);
		
		System.out.println(" C :-------------------- 1. 가상주소 계산 - 끝 ----------------");
		
		
		System.out.println(" C :-------------------- 2. 가상주소 매핑 - 시작 ----------------");
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/BoardWrite.bo")) {
			System.out.println(" C : BoardWrite.bo 매핑 ");
			System.out.println(" C : 패턴 1 - DB사용 x , 페이지 출력 ");
			
			
			forward = new ActionForward();
			forward.setPath("./board/boardWrite.jsp");
			forward.setRedirect(false);
			System.out.println(" C : " + forward.toString());
			
		} else if(command.equals("/BoardWriteAction.bo")) {
			System.out.println(" C : BoardWriteAction.bo 매핑 ");
			System.out.println(" C : 패턴 2 - DB사용 o , 페이지 이동 ");
			
			
			action = new BoardWriteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			System.out.println(" C : " + forward);
			
		}
		 else if(command.equals("/BoardList.bo")) {
				System.out.println(" C : BoardList.bo 매핑 ");
				System.out.println(" C : 패턴 3 - DB사용 o , 페이지 출력 ");
				
				// BoardListAction() 생성.
				action = new BoardListAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				System.out.println(" C : " + forward);
				
		}
		 else if(command.equals("/BoardContent.bo")) {
				System.out.println(" C : BoardContent.bo 매핑 ");
				System.out.println(" C : 패턴 3 - DB사용 o , 페이지 출력 ");
				
				// BoardContentAction() 생성.
				action = new BoardContentAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				System.out.println(" C : " + forward);
				
		}
		 else if(command.equals("/BoardUpdate.bo")) {
				System.out.println(" C : BoardUpdate.bo 매핑 ");
				System.out.println(" C : 패턴 3 - DB사용 o , 페이지 출력 ");
				
				// BoardUpdateAction() 생성.
				action = new BoardUpdateAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println(" C : " + forward);
				
		}
		 else if(command.equals("/BoardUpdateProAction.bo")) {
				System.out.println(" C : BoardUpdateProAction.bo 매핑 ");
				System.out.println(" C : 패턴 2 - DB사용 o , 페이지 이동 ");
				
				// BoardUpdateProAction() 생성.
				action = new BoardUpdateProAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println(" C : " + forward);
				
		}
		 else if(command.equals("/BoardDelete.bo")) {
				System.out.println(" C : BoardDelete.bo 매핑 ");
				System.out.println(" C : 패턴 1 - DB사용 x , 페이지 이동 ");
				
				forward = new ActionForward();
				
				forward.setPath("./board/boardDelete.jsp");
				forward.setRedirect(false);
				
				System.out.println(" C : " + forward);
				
		}
		 else if(command.equals("/BoardDeleteAction.bo")) {
				System.out.println(" C : BoardDelete.bo 매핑 ");
				System.out.println(" C : 패턴 2 - DB사용 o , 페이지 이동 ");
				
				
				action = new BoardDeleteAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				System.out.println(" C : " + forward);
				
		}
		 else if(command.equals("/BoardReWrite.bo")) {
				System.out.println(" C : BoardReWrite.bo 매핑 ");
				System.out.println(" C : 패턴 1 - DB사용 x , 페이지 이동 ");
				
				
				forward = new ActionForward();
				
				forward.setPath("./board/boardReWrite.jsp");
				forward.setRedirect(false);
				
				
				System.out.println(" C : " + forward);
				
		}
		 else if(command.equals("/BoardReWriteAction.bo")) {
				System.out.println(" C : BoardReWriteAction.bo 매핑 ");
				System.out.println(" C : 패턴 2 - DB사용 o , 페이지 이동 ");
				
				
				// BoardReWriteAction 객체 생성
				action = new BoardReWriteAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
				System.out.println(" C : " + forward);
				
		}
		 else if(command.equals("/BoardSearchListAction.bo")) {
				System.out.println(" C : BoardSearchListAction.bo 매핑 ");
				System.out.println(" C : 패턴 3 - DB사용 o , 페이지 출력 ");
				
				
				// BoardReWriteAction 객체 생성
				action = new BoardSearchListAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
				System.out.println(" C : " + forward);
				
		}
		 else if(command.equals("/BoardFileWrite.bo")) {
			 System.out.println(" C : BoardFileWrite.bo 매핑 ");
			 System.out.println(" C : 패턴 1 - DB사용 x , 페이지 이동 ");
			 
			 
			 forward = new ActionForward();
			 forward.setPath("./board/boardFileWrite.jsp");
			 forward.setRedirect(false);
			 
			 
			 System.out.println(" C : " + forward);
		 }
		 else if(command.equals("/BoardFileWriteAction.bo")) {
			 System.out.println(" C : BoardFileWriteAction.bo 매핑 ");
			 System.out.println(" C : 패턴 2 - DB사용 o , 페이지 이동 ");
			 
			 
			 action = new BoardFileWriteAction();
			 
			 try {
				 forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 
			 System.out.println(" C : " + forward);
		 }
		
		 else if(command.equals("/BoardFileDownLoadAction.bo")) {
			 System.out.println(" C : BoardFileDownLoadAction.bo 매핑 ");
			 System.out.println(" C : 패턴 3 - 처리작업 o , 페이지 출력 ");
			 
			 
			 action = new BoardFileDownLoadAction();
			 
			 try {
				 forward = action.execute(request, response);
			 } catch (Exception e) {
				 // TODO Auto-generated catch block
				 e.printStackTrace();
			 }
			 
			 
			 System.out.println(" C : " + forward);
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
		
	}
	
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
