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
			while(true) {
				int menuNum = dis.readInt();

				if(menuNum == 0) {
					//서버 종료
					server.close();
					break;

				}
				else if(menuNum==1){
					//등록 
					 studentDao.addStudent(dis, dos);
				}
				else if(menuNum==2){
					 studentDao.getStudentInfo(dis, dos);

				}
				else if(menuNum==3) {
					 studentDao.getStudentInfo(dis, dos);
					 studentDao.deleteStudent(dis, dos);
					
				}
				else if(menuNum==4) {
					 studentDao.getStudentInfo(dis, dos);
					 studentDao.editStudent(dis, dos);

				}
				else if(menuNum==5) {
					 studentDao.getStudentInfo(dis, dos);
					 studentDao.searchStudent(dis, dos);

				}
				else if(menuNum==6) {
					 studentDao.uploadFile(dis, dos);
				}
				else {
					dos.writeUTF("잘못 입력하셨습니다. ");
					dos.flush();
				}


			}

		}catch(Exception e) {
			System.out.println("서버에 연결할 수 없습니다. ");
			e.printStackTrace();
		}
	}
	
	

	


}
