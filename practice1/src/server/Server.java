package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import model.StudentDAO;
import model.StudentDTO;

public class Server {



	public static void main(String[] args) {
		
		try {
			//서버오픈, 클라이언트의 접속 확인 
			ServerSocket server = new ServerSocket(20000);

			System.out.println("#접속자를 기다리는 중입니다..");
			Socket client = server.accept();

			System.out.println("#" + client.getInetAddress() + " 로 부터 접속 확인..");

			DataInputStream dis = new DataInputStream(client.getInputStream());
			DataOutputStream dos = new DataOutputStream(client.getOutputStream());

			//학생 정보를 관리하기 위한 인스턴스 호출 
			StudentDAO studentDao = new StudentDAO(); 

			//클라이언트에서 요청받아 해당 기능 조건문으로 실행하는 로직 
			while (true) {
				int menuNum = dis.readInt();
				/** int형 보다는 Enum 형태가 좋음. 그리고 if문이 4개 이상이라면 switch 문 고려도 괜찮음
				 Enum 예시는 코드로 두었음. Enum의 장단점 찾아보기 [중요]
				 참고로 Enum에 메서드를 둘 수도 있음.
				 */
				Menu menu = Menu.fromCode(menuNum);

				if (menu == null) {
					dos.writeUTF("잘못 입력하셨습니다.");
					dos.flush();
					continue;
				}

				switch (menu) {
					case EXIT:
						dos.writeUTF("서버를 종료합니다.");
						dos.flush();
						server.close();
						return;
					case ADD_STUDENT:
						studentDao.addStudent(dis, dos); // 현재
						break;
					case GET_STUDENT_INFO:
						studentDao.getStudentInfo(dis, dos);
						break;
					case DELETE_STUDENT:
						studentDao.getStudentInfo(dis, dos);
						studentDao.deleteStudent(dis, dos);
						break;
					case EDIT_STUDENT:
						studentDao.getStudentInfo(dis, dos);
						studentDao.editStudent(dis, dos);
						break;
					case SEARCH_STUDENT:
						studentDao.getStudentInfo(dis, dos);
						studentDao.searchStudent(dis, dos);
						break;
					case UPLOAD_FILE:
						studentDao.uploadFile(dis, dos);
						break;
					default:
						dos.writeUTF("알 수 없는 메뉴입니다.");
						dos.flush();
						break;
				}
			}

		}catch(Exception e ) {
			/** 의식 하지는 않았겠지만, 추 후 정확한 예외를 잡아주는게 좋음. 예를 들어 소켓이 말소 된 경우 예외를 잡는 경우 SocketException 과 같이 적절하게 분리
			* 예외를 정확히 잡은 후에, 잡은 예외가 checked, non checked exception 인지 구분하고 checked exception 인 경우 runtime exception 으로 변경
			* checked, non checked exception 공부
			 runtime exception 으로 변경 후에는, 해당 예외를 최상단으로 던져주고 최상단에서 특정 예외를 잡아줄거임 - 스프링부틔인 경우 ControllerAdvice
			 */

			/**
			 * 정리
			 * 1. 정확한 예외 캐치
			 * 2. checked exception 인 경우, runtimeException으로 변경 후 최상단으로 던지기
			 * 3. 최상단에서 해당 예외를 한번에 관리. - 참고로 현재 최상단은 Server.class 이라서 이 부분은 상관없음 (참고만)
			 */
			System.out.println("서버에 연결할 수 없습니다. ");
			e.printStackTrace();
		}
	}
	
	

	


}
