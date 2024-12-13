package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {

		ServerSocket server = null;
		Socket client = null;
		DataInputStream dis = null;
		DataOutputStream dos = null;

		try {
			// 서버 소켓 생성
			server = new ServerSocket(20000);
			System.out.println("# 접속자를 기다리는 중입니다..");

			// 클라이언트 연결 대기
			client = server.accept();
			System.out.println(client.getInetAddress() + " 로부터 접속 확인..");

			// 클라이언트의 입력 및 출력 스트림 설정
			dis = new DataInputStream(client.getInputStream());
			dos = new DataOutputStream(client.getOutputStream());

			// 파일 목록이 있는 디렉토리 설정
			File file = new File("/Users/mir/Documents/project");

			// 디렉토리 존재 여부 확인
			if (!file.exists() || !file.isDirectory()) {
				System.out.println("파일 또는 디렉토리가 존재하지 않습니다.");
				return;
			}

			// 파일 목록 전송
			File[] files = file.listFiles();
			
			int fileListCnt = (int)files.length;
			dos.writeInt(files.length);
			
			for (File f : files) {
				System.out.println(f.getName());
				dos.writeUTF(f.getName());
			}
			dos.flush();
			
			
			String fileName = dis.readUTF();
			System.out.println(fileName);
			

			File targetFile = new File("/Users/mir/Documents/project/" + fileName );
			
			int fileSize = (int)targetFile.length();
			byte[] fileArr =  new byte[fileSize];
			
			dos.writeInt(fileSize);
			dos.flush();

			System.out.println(targetFile.getPath());
			FileInputStream fis = new FileInputStream(targetFile); 
			DataInputStream fileDis = new DataInputStream(fis);
			
			fileDis.readFully(fileArr);
			dos.write(fileArr);
			dos.flush();

			fileDis.close();
			dos.close();
			client.close();


		} catch (IOException e) {
			System.err.println("오류 발생: " + e.getMessage());
		} finally {
			// 리소스 해제
			try {
				
				if (dis != null) dis.close();
				if (dos != null) dos.close();
				if (client != null) client.close();
				if (server != null) server.close();
			} catch (IOException e) {
				System.err.println("리소스 종료 중 오류 발생: " + e.getMessage());
			}
		}
	}
}
