package com.itwillbs.board.action;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardFileWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : BoardFileWriteAction_execute() 호출 ");
		
		// 파일 업로드
		// 1) 파일이 저장될 장소(webapp/upload 폴더)를 지정\
		// 실제 작업파일 위치
		// C:\Users\ITWILL\eclipse-workspace_jsp6\MVC\src\main\webapp
		// realPath(서버에 배포된 작업파일 위
		// C:\Users\ITWILL\eclipse-workspace_jsp6\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\MVC\ upload
		
		String realPath = request.getRealPath("/upload");
		System.out.println(" M : realPath : " + realPath);
		
		// 2) 업로드할 파일의 크기 제한 (5MB)
		// bit > byte > KB > MB > GB > TB 
		int maxSize = 5 * 1024 * 1024;
		// 3) 파일 업로드
		MultipartRequest multi = new MultipartRequest(request, realPath,maxSize, "UTF-8", new DefaultFileRenamePolicy());
		// request : request 영역객체 정보를 전달 (+ parameter)
		// realPath : 서버에 배포된 업로드 폴더의 위치
		// maxSize : 업로드시 파일 크기제한
		// "UTF-8" : 인코딩 정보 설정
		// new DefaultFileRenamePolicy() : 중복파일 이름 처리
		
		System.out.println(" M : 파일 업로드 성공! ");
		
		
		// 전달 정보(파라메터) 저장
		// BoardDTO 객체 생성
		BoardDTO dto = new BoardDTO();
		// dto.setName(request.getParameter("name")); (form 태그 - encType: multipart/form-data) 
		//             => 사용불가
		
		dto.setName(multi.getParameter("name"));
		dto.setPass(multi.getParameter("pass"));
		dto.setSubject(multi.getParameter("subject"));
		dto.setContent(multi.getParameter("content"));
		dto.setIp(request.getRemoteAddr());
		// 밑에 있는 방식은 일반 form방식일때 사용가능.
		// dto.setFile(multi.getParameter("file"));
		
		// encType을 사용했을때는
		dto.setFile(multi.getFilesystemName("file"));
		System.out.println(" M : dto : "+ dto );
		// 저장한 파일과 올린 파일의 원래 이름이 다를 경우 이미 서버에 같은이름으로된 파일이 있음.
		// 원래는 두개의 이름을 저장해야됨.
		System.out.println(" M : oFile : "+ multi.getOriginalFileName("file"));
		
		// BoardDAO 객체 생성.
		BoardDAO dao = new BoardDAO();
		// 글정보 + 파일이름 저장하는 메서드
		dao.insertBoard(dto);
		
		// 페이지 이동준비
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true);
		
		return forward;
	}

}
