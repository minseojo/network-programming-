package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		try {
//			String ipAddress = "10.5.5.5";
			InetAddress localAddress = InetAddress.getLocalHost();

			//			Socket client = new Socket(ipAddress, 20000);
			Socket client = new Socket(localAddress, 20000);

		
			DataInputStream dis = new DataInputStream(client.getInputStream());
			DataOutputStream dos = new DataOutputStream(client.getOutputStream());

			int dicSize = dis.readInt();

			for(int i=0;i<dicSize;i++) {
				String f = dis.readUTF();
				System.out.println(f); 
			}

			//			while (dis.available() > 0) { 
			//				String f = dis.readUTF();
			//				System.out.println(f); 
			//			}
			
			String inputFileName = sc.nextLine();
			dos.writeUTF(inputFileName);
			dos.flush();

			//server에선 파일크기의 바이트 배열에 filestream으로 받아온 file을 datastream으로 변환하여 전달 
			//readfully로 byte[] client로 전달 

			int fileSize = dis.readInt();
			System.out.println(fileSize);

			byte[] fileContents = new byte[fileSize];

			dis.readFully(fileContents);

			String OutputFileName = sc.nextLine();
			FileOutputStream fos = new FileOutputStream(new File("/Users/mir/Documents/project/" + OutputFileName)); 
			DataOutputStream fileDos = new DataOutputStream(fos);

			fileDos.write(fileContents);
			fileDos.flush();

			fileDos.close();
			fos.close();
//			client.close();

		}catch(Exception e) {
			System.out.println("서버에 연결할 수 없습니다. ");
			e.printStackTrace();
		}
	}


}
