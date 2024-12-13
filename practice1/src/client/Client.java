package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import model.StudentDTO;

public class Client {

	static Scanner sc = new Scanner(System.in);
	
	
	public static void uploadFile(DataOutputStream dos, DataInputStream dis) {

		try {
			System.out.println("<<현재 파일 목록 출력>>");
			
			int dicSize = dis.readInt();

			for(int i=0;i<dicSize;i++) {
				String f = dis.readUTF();
				System.out.println(f); 
			}
			
			System.out.print("업로드 할 파일의 이름을 입력하시오: ");
			//파일 이름 전달 
			String fileName = sc.nextLine();
			dos.writeUTF(fileName);
			dos.flush();
			
			int fileSize = dis.readInt();
			System.out.println(fileSize);

			
			byte[] fileContents = new byte[fileSize];

			dis.readFully(fileContents);

			FileOutputStream fos = new FileOutputStream(new File("/Users/mir/Documents/project2/" + fileName)); 
			DataOutputStream fileDos = new DataOutputStream(fos);

			fileDos.write(fileContents);
			fileDos.flush();

			fileDos.close();
			fos.close();
			
			System.out.println("업로드 완료되었습니다. ");
			
		}catch(Exception e) {
			
			System.out.println("오류가 있습니다. \n다시 시도해주세요.");
		}
		
	}
	
	
	public static void searchStudent(DataOutputStream dos, DataInputStream dis) {

		System.out.println("검색할 ID를 입력해주세요:");
		int searchId = Integer.parseInt(sc.nextLine());
		try {
			dos.writeInt(searchId);
			dos.flush();
			
			System.out.println("ID: " + dis.readInt() +"\nName: " + dis.readUTF() +"\nKor: " + dis.readInt() 
							+"\nEng: " + dis.readInt() +"\nMath: " + dis.readInt() +"\nSum: " + dis.readInt() +"\nAvg: " + dis.readDouble());
			System.out.println();
			
		}catch(Exception e) {
			
			System.out.println("오류가 있습니다. \n다시 시도해주세요.");
		}
		
	}
	
	
	
	public static void editStudent(DataOutputStream dos, DataInputStream dis) {

		System.out.println("수정할 ID를 입력해주세요:");
		int editId = Integer.parseInt(sc.nextLine());
		try {
			dos.writeInt(editId);
			dos.flush();
			
			System.out.println("수정하고 싶은 목록의 번호를 입력해주세요.");
			System.out.println("1. 이름 ");
			System.out.println("2. 국어 점수 ");
			System.out.println("3. 영어 점수 ");
			System.out.println("4. 수학 점수 ");
			
			int editItem = Integer.parseInt(sc.nextLine());
			dos.writeInt(editItem);
			dos.flush();
			
			System.out.print("수정할 내용을 다시 입력해주세요: ");
			dos.writeUTF(sc.nextLine());
			dos.flush();
			
			if(dis.readBoolean()) System.out.println(editId + "번이 수정되었습니다. ");
			else System.out.println("수정 실패하였습니다.");
			
			
			
		}catch(Exception e) {
			
			System.out.println("오류가 있습니다. \n다시 시도해주세요.");
		}
		
	}
	
	
	public static void deleteStudent(DataOutputStream dos, DataInputStream dis) {

		System.out.println("삭제할 ID를 입력해주세요:");
		int deleteId = Integer.parseInt(sc.nextLine());
		try {
			dos.writeInt(deleteId);
			dos.flush();
			
			if(dis.readBoolean()) System.out.println(deleteId + "번이 삭제되었습니다. ");
			else System.out.println("삭제 실패하였습니다.");
			
		}catch(Exception e) {
			
			System.out.println("오류가 있습니다. \n다시 시도해주세요.");
		}
		
	}

	
	
	public static void printStudent(DataOutputStream dos, DataInputStream dis) {

		try {
			boolean emptyCheck = dis.readBoolean();

			if(!emptyCheck) {
				System.out.println("목록이 비어있습니다. ");
				return;
			}
			
			String[][] answers = {{"id :", "1"}, {"name :", "2"},{"kor :", "1"},{"eng :", "1"}, 
													{"math :", "1"}, {"sum :", "1"}, {"avg :", "0"}}; 

			int studentCnt = dis.readInt();
			for(int i=0;i<studentCnt;i++) {
				
				for(int j=0;j<7;j++) {
					if(answers[j][1].equals("1")) System.out.println(answers[j][0] + dis.readInt() + "");      
					else if(answers[j][1].equals("2")) System.out.println(answers[j][0] + dis.readUTF());
					else System.out.println(answers[j][0] + dis.readDouble());
				}
				System.out.println();
				
			}

		}catch(Exception e) {
			System.out.println("입력에 오류가 있습니다. ");
		}

	}

	
	
	public static void addStudent(DataOutputStream dos, DataInputStream dis) {

		try {
			String[] answers = {"학생의 이름을 입력하시오: ", "학생의 국어점수를 입력하시오: ", 
					"학생의 영어점수를 입력하시오: ", "학생의 수학점수를 입력하시오: "}; 
			
			for(int i=0;i<4;i++) {
				System.out.print(answers[i]);
				
				if(i==0) {
					String name = sc.nextLine();
					dos.writeUTF(name);
				}
				else{
					int score = Integer.parseInt(sc.nextLine());
					dos.writeInt(score);
				}
				dos.flush();

			}

			if(dis.readBoolean()) {
				System.out.println("학생 정보가 등록되었습니다. ");
			}
			else {
				System.out.println("학생 정보가 등록되지 않았습니다.");
			}

		}catch(Exception e) {
			System.out.println("입력에 오류가 있습니다. ");
		}

	}
	
	
	public static void printMenuNum() {

		System.out.println("<< 학생 관리 시스템 >>");
		System.out.println("1. 신규 학생 정보 등록 ");
		System.out.println("2. 학생 목록 출력 "); 
		System.out.println("3. 학생 삭제 ");
		System.out.println("4. 학생 수정 ");
		System.out.println("5. 학생 검색 ");
		System.out.println("6. 파일 업로드 ");
		System.out.println("0. 연결 종료 ");
		System.out.print(">>");

	}

	

	public static void main(String[] args) {

		try {
			//			String ipAddress = "10.5.5.12";
			InetAddress localAddress = InetAddress.getLocalHost();

			//			Socket client = new Socket(ipAddress, 20000);
			Socket client = new Socket(localAddress, 20000);

			DataInputStream dis = new DataInputStream(client.getInputStream());
			DataOutputStream dos = new DataOutputStream(client.getOutputStream());

			while(true) {

				printMenuNum();

				int menuNum = Integer.parseInt(sc.nextLine());
				dos.writeInt(menuNum);
				dos.flush();


				if(menuNum == 0) {
					System.out.println("서버를 종료하겠습니다.");
					System.exit(0);
				}
				else if(menuNum == 1) {
					addStudent(dos, dis);
				}
				else if(menuNum == 2) {
					printStudent(dos, dis);
				}
				else if(menuNum == 3) {
					printStudent(dos, dis);
					deleteStudent(dos, dis);
				}
				else if(menuNum == 4) {
					printStudent(dos, dis);
					editStudent(dos, dis);
				}
				else if(menuNum == 5) {
					printStudent(dos, dis);
					searchStudent(dos, dis);
				}
				else if(menuNum == 6) {
//					printStudent(dos, dis);
					uploadFile(dos, dis);
				}
				else {
					System.out.println("잘못입력하셨습니다. ");
				}

			}

		}catch(Exception e) {
			System.out.println("서버에 연결할 수 없습니다. ");
			e.printStackTrace();
		}


	}
	

	


}
