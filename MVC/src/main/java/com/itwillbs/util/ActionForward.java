package com.itwillbs.util;

/**
 * ActionForward (티켓) : 페이지 이동 정보를 저장하는 객체
 *  - 이동할 페이지 주소 (path)
 *  - 이동방식 (isRedirect)
 * 
 */
public class ActionForward {
	
	private String path;
	private boolean isRedirect;
	// true  - sendRedirect() 방식으로 이동 
	// false - forward() 방식으로 이동

	
	public ActionForward() {
		System.out.println(" 페이지 이동정보를 저장 객체 생성.");
		System.out.println(" 이동티켓 정보 생성.");
		
	
	}
	
	// get/set 메서드 생성.
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	// boolean 타입의 경우 get이 아니라 is 라고 표기된다.
	// get이랑 똑같다.
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

	
	@Override
	public String toString() {
		return "ActionForward [path(이동주소)=" + path + ", isRedirect(이동방식)=" + isRedirect + "]";
	}
	
	
	 
}// class
