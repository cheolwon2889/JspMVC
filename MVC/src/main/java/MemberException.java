
public class MemberException {

	public static void main(String[] args) {
		
		
		// 예외 처리 : 개발자가 예상하지 못하는 상황(에러)를 처리
		// 컴파일 에러 : 문법 오류 (빨간줄)
		
		
		// * 자바에서는 에러/예외를 객체로 관리한다.
		
		//      Throwable 객체
		//        /      \
		// Error 객체    Exception 객체
		
		// * 예외처리 방법
		// 1 ) try ~ catch 구문
		try {
			// 예외가 발생할 수도 있는 코드 작성. (DB연결, 외부 시스템과 연결)
		} catch (ArrayIndexOutOfBoundsException e) {
			// 예외처리 => 예외 메세지 출력
			e.getMessage();
			e.printStackTrace();
			
		}
		catch (Exception e) {
			// 예외처리 => 예외 메세지 출력
			e.getMessage();
			e.printStackTrace();
			
		}
		
		// + try ~ catch~finally 구문
		try {
			// 예외가 발생 할수 있고, 하지 않는 구문
		} catch(Exception e) {
			// 예외 처리(출력)
			
		} finally {
			// 예외 여부에 상관없이 반드시 실행해야하는 구문
			// => DB연결 해제(자원해제)
			// 자바에서 리소스/메모리를 많이 쓰는 작업이 외부 시스템과 연결하는 동작이다고 함.
			// 
			
		}
		
		// try - with 구문
//		try (자원해제가 필요한 객체) {
//			
//		} catch (Exception e) {
//
//		}
		
		// 2 ) throws 구문 : 해당 메서드를 실행할때 예외처리를 강제
		
//		public void method() throws Exception {
//			// 예외처리가 필요한 구문
//		}
//		// 예외 처리를 수행해야함.
//		try {
//			method(); // 실행
//		} catch (Exception e) {
//		}
		
	}

}
