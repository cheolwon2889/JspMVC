package com.itwillbs.board.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



/**
 * DB - itwill_board 테이블에 처리되는 모든 동작 처리하는 객체
 */

public class BoardDAO {

	// 공통변수 선언
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
	// 디비 연결 메서드 - getConnect()
	// Calss , Driver이 두개를 쓸려면 예외처리를 해야함.
	// 밑의 방법이 효과적이지 않을 수 있다. 
	// 외부 리소스에 접근을 하는게 메모리가 가장 많이 들고
	// 그 다음으로 드는게 연결을 해제하는게 메모리가 많이 든다.
	
	private Connection getConnect() throws Exception {
		
		// 디비 연결 정보 - MATA-INF/context.xml 파일
		// java:comp/env/ 까지는 고정 이후는 xml에서 설정한 name에 따라 달라짐.
		Context initCTX = new InitialContext();
		DataSource ds =  (DataSource)initCTX.lookup("java:comp/env/jdbc/MVC");
		
		// 1. 드라이버 로드 + 2. 디비 연결
		con = ds.getConnection();
		System.out.println(" DAO : 디비 연결 성공 (CP) ");
		System.out.println(" DAO : " + con);
		
		
		
		return con;
	}
	// 디비 연결 메서드 - getConnect()
	
	// 디비 자원해제 매서드 closeDB()
	public void closeDB() {
		System.out.println(" DAO : 자원해제 코드 - 시작 ");
		
		try {
			// 코드 사용 역순 해제
			if(rs != null) rs.close();
			if(pstmt !=null) pstmt.close();
			if(con !=null) con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(" DAO : 자원해제 코드 - 끝 ");
	}
	
	// 게시판 글쓰기
	public void insertBoard(BoardDTO dto) {
		int bno = 0;
		try {
			
			// 이전과 달라진 CP를 사용해서 받아옴.
			// 1.2. 디비연결 (CP)
			con = getConnect();
			// 3. sql 구문(insert) & pstmt 객체
			// bno 계산
			sql = "select max(bno) from itwill_board";
			// 4. SQL 구문 실행
			pstmt = con.prepareStatement(sql);
			
			rs =  pstmt.executeQuery();
			// 5. 데이터 처리
			if(rs.next()) {
				// 하나만 불러오니깐 굳이 컬럼명을 입력할 필요가 없다.
				//bno = rs.getInt("max(bno)") + 1;
				
				// sql 내장함수를 실행하게 되면 값이 null 이여도 0을 리턴을 함.
				// sql 문을 먼저 실행을 시키고 커서가 생기는지 생기지 않는지 확인을 해야함.
				
				bno = rs.getInt(1) + 1;
			}
//			else { 
//				bno = 1;
//			}
			
			System.out.println(" DAO : bno : " + bno);
			
			// 글쓰기 시작
			sql = "insert into itwill_board (bno,name, pass, subject, content,"
					+ "readcount,re_ref,re_lev, re_seq,date, ip,file) "
					+ " values(?,?,?,?,?,?,?,?,?,now(),?,?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bno);
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getPass());
			pstmt.setString(4, dto.getSubject());
			pstmt.setString(5, dto.getContent());
			
			pstmt.setInt(6, 0);    // 모든 글 조회수는 0 초기화
			pstmt.setInt(7, bno);  // re_ref 는 그룹번호 == bno
			pstmt.setInt(8, 0);    // re_lev 0 초기화
			pstmt.setInt(9, 0);    // re_seq 0 초기화
			
			pstmt.setString(10, dto.getIp());
			pstmt.setString(11, dto.getFile());
			
			
			// 4. sql 실행
			pstmt.executeUpdate();
			
			System.out.println(" DAO : 글쓰기 정보 저장 완료! ");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// 게시판 글쓰기
	
	// 게시판 저장된 전체 글 개수 조회 getBoardCount()
	public int getBoardCount() {
		int count = 0;
		
		// 1.2. 디비연결(CP)
		try {
			con = getConnect();
			
			sql = "select count(bno) from itwill_board";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
			System.out.println(" DAO : 전체 글 개수 : " + count +"개");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		// 3. SQL 구문(select) & pstmt 객체
		// 4. SQL 실행
		
		
		return count;
	}
	
	
	// 게시판 저장된 전체 글 개수 조회 getBoardCount()
	
	
	// 게시판 저장된 전체 글 개수 조회 getBoardCount(search)
	public int getBoardCount(String search) {
		int count = 0;

		// 1.2. 디비연결(CP)
		try {
			con = getConnect();

			sql = "select count(bno) from itwill_board where subject like ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}

			System.out.println(" DAO : 전체 글 개수 : " + count + "개");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		// 3. SQL 구문(select) & pstmt 객체
		// 4. SQL 실행

		return count;
	}// 게시판 저장된 전체 글 개수 조회 getBoardCount(search)
	
	
	
	// 게시판 전체 리스트 조회 - getBoardList()
	public List<BoardDTO> getBoardList() {
		List<BoardDTO> boardList = new ArrayList<BoardDTO>(); 
		
		try {
			con = getConnect();
			
			// sql = "select * from itwill_board";
			 sql = "select * from itwill_board order by bno desc limit 10, 10 ";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				
				dto.setBno(rs.getInt("bno"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));
				
				dto.setFile(rs.getString("file"));
				dto.setIp(rs.getString("ip"));
				dto.setName(rs.getString("name"));
				dto.setPass(rs.getString("pass"));
				
				dto.setRe_lev(rs.getInt("re_lev"));
				dto.setRe_ref(rs.getInt("re_ref"));
				dto.setRe_seq(rs.getInt("re_seq"));
				
				dto.setReadcount(rs.getInt("readcount"));
				
				dto.setSubject(rs.getString("subject"));
				
				boardList.add(dto);
			}
			
			System.out.println(" DAO : 게시판 전체 글 조회 성공! ");
			System.out.println(" DAO :  " + boardList.size()+ "개");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return boardList; 
	}
	// 게시판 전체 리스트 조회 - getBoardList()
	
	// 게시판 전체 리스트 조회 - getBoardList(startRow , pageSize)
	public List<BoardDTO> getBoardList(int startRow, int pageSize) {
			List<BoardDTO> boardList = new ArrayList<BoardDTO>(); 
			
			try {
				con = getConnect();
				
				// sql = "select * from itwill_board";
				// sql = "select * from itwill_board order by bno desc limit 10, 10 ";
	//			sql = "select * from itwill_board order by bno desc limit ? , ? ";
				sql = "SELECT * FROM itwill_board ORDER BY re_ref DESC, re_seq ASC LIMIT ?, ?";
	
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, startRow - 1); // 시작행 -1
				pstmt.setInt(2, pageSize);    // 페이지 사이즈
				
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					BoardDTO dto = new BoardDTO();
					
					dto.setBno(rs.getInt("bno"));
					dto.setContent(rs.getString("content"));
					dto.setDate(rs.getDate("date"));

					dto.setFile(rs.getString("file"));
					dto.setIp(rs.getString("ip"));
					dto.setName(rs.getString("name"));
					dto.setPass(rs.getString("pass"));

					dto.setRe_ref(rs.getInt("re_ref"));
					dto.setRe_lev(rs.getInt("re_lev"));
					dto.setRe_seq(rs.getInt("re_seq"));
					
					dto.setReadcount(rs.getInt("readcount"));
					dto.setSubject(rs.getString("subject"));
					
					boardList.add(dto);
				}
				
				System.out.println(" DAO : 게시판 전체 글 조회 성공! ");
				System.out.println(" DAO :  " + boardList.size()+ "개");
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeDB();
			}
			
			return boardList; 
		}
	// 게시판 전체 리스트 조회 - getBoardList(startRow , pageSize)
	
	// 게시판 전체 리스트 조회 - getBoardList(startRow , pageSize , search)
		public List<BoardDTO> getBoardList(int startRow, int pageSize,String search) {
				List<BoardDTO> boardList = new ArrayList<BoardDTO>(); 
				
				try {
					con = getConnect();
					
					// sql = "select * from itwill_board";
					// sql = "select * from itwill_board order by bno desc limit 10, 10 ";
		//			sql = "select * from itwill_board order by bno desc limit ? , ? ";
					sql = "SELECT * FROM itwill_board "
							+ " where subject like ? "
							+ " ORDER BY re_ref DESC, re_seq ASC "
							+ " LIMIT ?, ?";
		
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, "%"+search+"%"); // 제목검색
					pstmt.setInt(2, startRow - 1); // 시작행 -1
					pstmt.setInt(3, pageSize);    // 페이지 사이즈
					
					
					rs = pstmt.executeQuery();
					
					while (rs.next()) {
						BoardDTO dto = new BoardDTO();
						
						dto.setBno(rs.getInt("bno"));
						dto.setContent(rs.getString("content"));
						dto.setDate(rs.getDate("date"));

						dto.setFile(rs.getString("file"));
						dto.setIp(rs.getString("ip"));
						dto.setName(rs.getString("name"));
						dto.setPass(rs.getString("pass"));

						dto.setRe_ref(rs.getInt("re_ref"));
						dto.setRe_lev(rs.getInt("re_lev"));
						dto.setRe_seq(rs.getInt("re_seq"));
						
						dto.setReadcount(rs.getInt("readcount"));
						dto.setSubject(rs.getString("subject"));
						
						boardList.add(dto);
					}
					
					System.out.println(" DAO : 게시판 전체 글 조회 성공! ");
					System.out.println(" DAO :  " + boardList.size()+ "개");
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					closeDB();
				}
				
				return boardList; 
			}
		// 게시판 전체 리스트 조회 - getBoardList(startRow , pageSize , search)

	// 조회수 1증가 - updateReadcount(bno)
	public void updateReadcount(int bno) {
		
		try {
			// 1.2. 디비연결(CP)
			con = getConnect();
			// 3. SQL 구문 (update) & pstmt 객체
			
			sql = "UPDATE itwill_board SET readcount = readcount + 1 WHERE bno = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bno);
			
			// 4. SQL 실행
			pstmt.executeUpdate();
			System.out.println(" DAO : 조회수 1 증가.");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
	} // 조회수 1증가 - updateReadcount(bno) 
	
	
	// 특정 글정보 조회 - getBoard(bno)
	public BoardDTO getBoard(int bno) {
		BoardDTO dto = null;
		
		try {
			con = getConnect();
			
			sql = "select * from itwill_board where bno = ? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
					
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new BoardDTO();
				
				dto.setBno(rs.getInt("bno"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));

				dto.setFile(rs.getString("file"));
				dto.setIp(rs.getString("ip"));
				dto.setName(rs.getString("name"));
				dto.setPass(rs.getString("pass"));

				dto.setRe_ref(rs.getInt("re_ref"));
				dto.setRe_lev(rs.getInt("re_lev"));
				dto.setRe_seq(rs.getInt("re_seq"));
				
				dto.setReadcount(rs.getInt("readcount"));
				dto.setSubject(rs.getString("subject"));
				
				
				System.out.println(" DAO : 특정 글 조회 성공! ");			
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return dto;
	}
	// 특정 글정보 조회 - getBoard(bno)
	
	// 이전글 제목 조회 - prevSubject(bno)
	public String prevSubject(int bno) {
		String result = ""; // 일반적으로 공백으로 초기화 함.
		// String result = null; 으론 하지 않는다 일반적으로. 
		try {
			// 1.2 디비 연결(CP)
			con = getConnect();
			// 3. SQL 구분 & pstmt 객체
			
			// FIXME::
			 // SELECT subject FROM posts WHERE ㅠㅜㅐ > your_last_id LIMIT 1; 이전글
			sql = "select subject from itwill_board where bno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno - 1);
			
			// 4. SQL 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				result = rs.getString(1);
				System.out.println(" DAO : 이전글 제목 조회 성공! ");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return result;
	}
	
	// 이전글 제목 조회 - prevSubject(bno)
	
	// 다음글 제목 조회 - nextSubject(bno)
	public String nextSubject(int bno) {
		String result = "";
		try {
			con = getConnect();
			sql = "select subject from itwill_board where bno = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bno + 1 );
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getString(1);
				System.out.println(" DAO : 다음글 제목 조회 성공! ");
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
	}
	
	
	// 다음글 제목 조회 - nextSubject(bno)
	
	// 게시판 글 수정 - updateBoard(dto)
	public int updateBoard(BoardDTO dto) {
		int result = -1;
		try {
			con = getConnect();
			
			sql = "select pass from itwill_board where bno = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getBno());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// 글 정보가 있을때
				if(dto.getPass().equals(rs.getString("pass"))) {
					// 비밀번호가 맞을때
					sql = "update itwill_board set name = ? , pass = ? , subject = ? , content = ? where bno = ?";
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, dto.getName());
					pstmt.setString(2, dto.getPass());
					pstmt.setString(3, dto.getSubject());
					pstmt.setString(4, dto.getContent());
					pstmt.setInt(5, dto.getBno());
					
					pstmt.executeUpdate();
					result = 1;
					System.out.println(" DAO : 글 정보 수정 완료. ");
					
				}else {
					// 비밀번호가 틀릴때
					result = 0;
				}
				
			}else {
				// 글 정보가 없을때.
				result = -1 ;
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
	}
	// 게시판 글 수정 - updateBoard(dto)
	
	
	// 게시판 글 정보 삭제 - deleteBoard(dto)
	public int deleteBoard(BoardDTO dto) {
		int result = -1;
		
		try {
			con = getConnect();
			sql = "select pass from itwill_board where bno = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getBno());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// bno가 있을때
				if(dto.getPass().equals(rs.getString("pass"))) {
					// 비밀번호가 맞을때
					sql = "delete from itwill_board where bno = ?";
					pstmt = con.prepareStatement(sql);
					
					pstmt.setInt(1, dto.getBno());
					
					pstmt.executeUpdate();
					
					System.out.println(" DAO : 글삭제 완료 ");
					result = 1;
					
					
				}else {
					// 비밀번호가 틀릴때 
					result = 0;
				}
				
			}else {
				// bno가 없을때
				result= -1;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeDB();
		}
		return result;
	}
	
	
	// 게시판 글 정보 삭제 - deleteBoard(dto)
	
	
	// 답글쓰기 메서드 - reInsertBoard(dto)
	public void reInsertBoard(BoardDTO dto) {
		int bno = 0; // 답글 번호
		
		try {
			// 1.2 디비 연결
			con = getConnect();
			/****************** 1. 답글 번호 계산 **************************/
			// 3. sql 구문 & pstmt 객체
			sql = "select max(bno) from itwill_board";
			pstmt = con.prepareStatement(sql);
			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bno = rs.getInt(1)+1;
			}
			System.out.println(" DAO : 답글번호 : "+ bno);
			
			/****************** 1. 답글 번호 계산 **************************/
			/****************** 2. 답글 seq 계산 **************************/
			// 답글 순서 재배치
			// 3. SQL 구문 & pstmt 객체
			// 부모글과 ref값이 같으면서, seq 값이 더 큰값만 1씩 증가.
			sql = "update itwill_board set re_seq = re_seq + 1 "
					+ " where re_ref = ? and re_seq > ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getRe_ref()); // 같은 그룹 번호
			pstmt.setInt(2, dto.getRe_seq()); // 부모의 그룹순서
			
			// 4. sql 실행
			int check = pstmt.executeUpdate();
			
			if( check > 0 ) {
				System.out.println(" DAO : 답글 순서 재배치 완료! ");
			}
			
			/****************** 2. 답글 seq 계산 **************************/
			/****************** 3. 답글 내용 저장 **************************/
			// 3. SQL 구문 & pstmt 객체
			// 글쓰기 시작
			sql = "insert into itwill_board (bno,name, pass, subject, content,"
					+ "readcount,re_ref,re_lev, re_seq,date, ip,file) "
					+ " values(?,?,?,?,?,?,?,?,?,now(),?,?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bno);
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getPass());
			pstmt.setString(4, dto.getSubject());
			pstmt.setString(5, dto.getContent());
			
			pstmt.setInt(6, 0);    // 모든 글 조회수는 0 초기화
			pstmt.setInt(7, dto.getRe_ref());  // re_ref 는 그룹번호 == 부모글의 ref
			pstmt.setInt(8, dto.getRe_lev()+1);    // re_lev 부모글 lev + 1
			pstmt.setInt(9, dto.getRe_seq()+1);    // re_seq 부모글 seq + 1
			
			pstmt.setString(10, dto.getIp());
			pstmt.setString(11, dto.getFile());
			
			
			// 4. sql 실행
			pstmt.executeUpdate();
			
			System.out.println(" DAO : 글쓰기 정보 저장 완료! ");
		
			
			
			
			/****************** 3. 답글 내용 저장 **************************/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		
		
	}
	// 답글쓰기 메서드 - reInsertBoard(dto)
	
}








