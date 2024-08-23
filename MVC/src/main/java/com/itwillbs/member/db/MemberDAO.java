package com.itwillbs.member.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;




/**
 * MemDAO : 회원정보(itwill_member테이블)관한 모든 디비 동작을 처리하는 객체
 * 
 * DAO(Data Access Object)
 * 
 * 각 동작별 메서드를 생성(디비 처리 1개당 하나의 메서드)
 * 
 */

public class MemberDAO {
	// 공통변수 선언
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
	// 디비 연결 메서드 - getConnect()
	// Calss , Driver이 두개를 쓸려면 예외처리를 해야함.
	private Connection getConnect() throws Exception {
		// 디비 연결 정보
		final String DRIVER = "com.mysql.cj.jdbc.Driver";
		final String DBURL = "jdbc:mysql://localhost:3306/jspdb";
		final String DBID = "root";
		final String DBPW = "1234";
		
		// 1. 드라이버 로드
		Class.forName(DRIVER);
		System.out.println(" DAO : 드라이버 로드 성공! ");
		
		// 2. 디비 연결
		con = DriverManager.getConnection(DBURL,DBID,DBPW);
		System.out.println(" DAO : 디비 연결 성공! ");
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
	
	// 디비 자원해제 매서드 closeDB() 
	
	
	
	
	public MemberDAO() {
		System.out.println(" DAO : MemberDAO 객체 생성 - 디비 처리 가능 ");
	}
	
	
	
	// 회원가입 메서드
	public void insertMember(MemberDTO dto) throws Exception {
		
		//
		con = getConnect();
		
		// 3. sql 구문(insert) & pstmt 객체
		sql = "insert into itwill_member (id,pw,name,gender,age,email,regdate) values(?,?,?,?,?,?,?)";
		
		
		pstmt = con.prepareStatement(sql);
		
		// ???
		pstmt.setString(1, dto.getId());
		pstmt.setString(2, dto.getPw());
		pstmt.setString(3, dto.getName());
		pstmt.setString(4, dto.getGender());
		pstmt.setInt(5, dto.getAge());
		pstmt.setString(6, dto.getEmail());
		pstmt.setTimestamp(7, dto.getRegdate());
		
		// 4. sql 실행
		pstmt.executeUpdate();
		
		System.out.println(" DAO : 회원가입 완료! ");
		
	} // insertMember()
	
	// 로그인 여부 체크 - loginCheck() 
	public int loginCheck(MemberDTO dto) {
		int result = -1;
		// 아이디 X : -1 / 아이디O , 비밀번호 X : 0 / 아이디 O , 비밀번호 O = 1
		// 약속 포워딩을 하기 위해.
			
		// 1. 드라이버 로드
		// 2. 디비연결
		try {
			con = getConnect();
			// 3. sql 구문(select) & pstmt 객체
			sql = "select pw from itwill_member where id = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			
			// 4. sql 실행 & rs 저장
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				// 아이디에 해당하는 비밀번호가 있다.
				if(dto.getPw().equals(rs.getString("pw"))) {
					// 아이디가 같으면서 비밀번호 같음
					result = 1;
				} else {
					// 아이디가 같으면서 비밀번호 다름
					result = 0;
				}
			} else {
				// 아이디에 해당하는 비밀번호가 없다.
				// => 회원정보 없음.
				result = -1;
			}

			
			System.out.println(" DAO : SQL구문 실행완료 ");
			System.out.println(" DAO : 로그인 체크 완료 :" + result );
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		
		
		return result;
	}// 로그인 여부 체크 - loginCheck() 
	
	
	// 회원정보 조회 getMember(id);
	public MemberDTO getMember(String id) {
		MemberDTO dto = null;
		try {
			// 1. 드라이버 로드
			// 2. 디비연결
			con = getConnect();
			// 3. sql 구문(select) & pstmt 객체
			sql = "select * from itwill_member where id = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			// 4. sql 실행 & rs 저장
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				// 데이터가 있음  rs - > DTO 저장
				dto = new MemberDTO();
				
				dto.setAge(rs.getInt("age"));
				dto.setEmail(rs.getString("email"));
				dto.setGender(rs.getString("gender"));
				dto.setName(rs.getString("name"));
				
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setRegdate(rs.getTimestamp("regdate"));
				
				
				
			}
			System.out.println(" DAO : SQL구문 실행완료 ");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return dto;
	}
	// 회원정보 조회 getMember(id);
	
	
	// 회원정보 수정 updateMember(dto);
	public int updateMember(MemberDTO dto) {
		// 여기서는 페이지 이동을 못하니 리턴타입을 반환을 해서 action 페이지에서 포워딩 해준다.
		int result = -1;
		// -1 : 아이디 x , 0 : 아이디 O , 비밀번호 x , 1 : 정상처리
		
		
		
		try {
			// 1. 드라이버 로드
			// 2. 디비 연결
			con = getConnect();
			
			
			// 3. SQL 구문 & pstmt 객체
			// 4. SQL 실행
			sql = "select pw from itwill_member where id = ? ";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1 ,dto.getId());
			
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// 아이디가 있을때
				if(dto.getPw().equals(rs.getString("pw"))) {
					// 3. sql 구문(insert) & pstmt 객체
					sql = "update itwill_member set name=? , gender=?, age=? where id=? ";
					
					
					pstmt = con.prepareStatement(sql);
					
					// ???
					pstmt.setString(1, dto.getName());
					pstmt.setString(2, dto.getGender());
					pstmt.setInt(3, dto.getAge());
					pstmt.setString(4, dto.getId());
					
					
					// 4. sql 실행
					
					
					System.out.println(" DAO : 회원 정보수정 완료! ");
					// 반환값 = 아이디는 하나이기에 변하는 행의 수는 1
					result = pstmt.executeUpdate();
					
				}
				// 아이디는 맞지만 비밀번호가 틀릴때.
				else {
					result = 0;
				}
				
			}else {
				// 아이디가 없을때
				result = -1;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
		
	}
	// 회원정보 수정 updateMember(id);
	
	
	// 회원정보 삭제 deleteMember(id);
	public int deleteMember(MemberDTO dto) {
		// 여기서는 페이지 이동을 못하니 리턴타입을 반환을 해서 action 페이지에서 포워딩 해준다.
		int result = -1;
		// -1 : 아이디 x , 0 : 아이디 O , 비밀번호 x , 1 : 정상처리
		
		
		
		try {
			// 1. 드라이버 로드
			// 2. 디비 연결
			con = getConnect();
			
			
			// 3. SQL 구문 & pstmt 객체
			// 4. SQL 실행
			sql = "select pw from itwill_member where id = ? ";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1 ,dto.getId());
			
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// 아이디가 있을때
				if(dto.getPw().equals(rs.getString("pw"))) {
					// 3. sql 구문(insert) & pstmt 객체
					sql = "delete from itwill_member where id = ?";
					
					
					pstmt = con.prepareStatement(sql);
					
					// ???
				
					pstmt.setString(1, dto.getId());
					
					
					// 4. sql 실행
					
					
					// 반환값 = 아이디는 하나이기에 변하는 행의 수는 1
					result = pstmt.executeUpdate();
					
					System.out.println(" DAO : 회원 정보삭제 완료! ");
					
				}
				// 아이디는 맞지만 비밀번호가 틀릴때.
				else {
					result = 0;
				}
				
			}else {
				// 아이디가 없을때
				result = -1;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
		
	}
	
	
	// 회원정보 삭제 deleteMember(id);
	
	// 회원목록 리스트 - getMemberList() 
	public ArrayList<MemberDTO> getMemberList() {
		
		
		// 회원 목록정보 저장하는 가변길이 배열 생성
		ArrayList<MemberDTO> memberList = new ArrayList<MemberDTO>();
		
		
		
		try {
			// 1. 드라이버 로드
			// 2. 디비 연결
			con = getConnect();
			

			// 3. SQL 구문 & pstmt 객체
			// 4. SQL 실행
			sql = "select * from itwill_member where id != ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, "admin" );
			
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setAge(rs.getInt("age")); 
				dto.setEmail(rs.getString("email")); 
				dto.setGender(rs.getString("gender")); 
				dto.setId(rs.getString("id")); 
				dto.setName(rs.getString("name")); 
				dto.setPw(rs.getString("pw"));
				dto.setRegdate(rs.getTimestamp("regdate"));
				
				memberList.add(dto);
			}
			System.out.println(" DAO : 회원 목록 조회성공! ");
			System.out.println(" DAO : " +memberList.size());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		
		
		return memberList;
	}
	// 회원목록 리스트 - getMemberList() 
	
	
	
} // class
