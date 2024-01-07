package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.UUID;

import model.ThiSinh;

public class ThreadServer extends Thread {
	Socket socket;
	BufferedReader netIn;
	PrintWriter netOut;
	RandomAccessFile raf;
	int count;
	int recSize;
	int headSize = 8;
	public ThreadServer(Socket socket) throws IOException {
		super();
		this.socket = socket;
		netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		netOut = new PrintWriter(socket.getOutputStream(), true);
		recSize = 100232;
		raf = new RandomAccessFile("D:\\ltm\\thisinh.dai", "rw");
		if(raf.length() == 0) {
			raf.writeInt(count);
			raf.writeInt(recSize);
		}
	}

	@Override
	public void run() {
		netOut.println("welcome client!");
		ThiSinh ts = new ThiSinh();
		long pos = 0;
		try {
			while(true) {
				raf.seek(0);
				count = raf.readInt();
				recSize = raf.readInt();
				String line = netIn.readLine();
				if(line.equalsIgnoreCase("quit"))break;
				StringTokenizer stk = new StringTokenizer(line, "\t");
				String command = null, param1 = null, param2 = null, param3 = null;
				command = stk.nextToken().toUpperCase();
				if(stk.hasMoreTokens()) {
					param1 = stk.nextToken();
				}
				if(stk.hasMoreTokens()) {
					param2 = stk.nextToken();
				}
				if(stk.hasMoreTokens()) {
					param3 = stk.nextToken();
				}
				
				String res = "";
				switch (command) {
				case "REGISTER": {
						StringTokenizer stk2 = new StringTokenizer(param2, "/");
						int day = Integer.parseInt(stk2.nextToken());
						int month = Integer.parseInt(stk2.nextToken());
						int bYear = Integer.parseInt(stk2.nextToken());
						if(2022 - bYear <= 10) {
							Random rd = new Random();
							String s1 = rd.nextInt(10) + "";
							String s2 = rd.nextInt(10) + "";
							String s3 = rd.nextInt(10) + "";
							String s4 = rd.nextInt(10) + "";
							String s5 = rd.nextInt(10) + "";
							String s6 = rd.nextInt(10) + "";
							String id =  s1+s2+s3+s4+s5+s6;
							ts = new ThiSinh(Integer.parseInt(id), param1, param2, param3);
							count++;
							raf.seek(0);
							raf.writeInt(count);
							raf.seek(raf.length());
							ts.regis(raf);
							res = "dang ky thanh cong thi sinh thu " + count + " postion hien tai "+raf.getFilePointer()+ " ma thi sinh la : " + id;
							pos = raf.getFilePointer() - 100108;
					}else {
						res = "dang ky that bai, phai tren 10 tuoi";
					}
					break;
				}
				case "FOTO": {
					File file = new File(param1);
					if(file.length() <= 100000 && param1.endsWith(".png")) {
						ts.setFileName(param1);
						System.out.println(pos);
						raf.seek(pos);
					ts.foto(raf, file);
						raf.seek(raf.length());
					res = "Up anh thanh cong" + ", postion hien tai "+raf.getFilePointer();
					}else {
						res = "Up anh that bai";
					}
					break;
				}
				case "VIEW": {
					raf.seek(4);
					System.out.println(raf.readInt());
					for (int i = 0; i < count; i++) {
						raf.seek(headSize + recSize*i);	
						System.out.println("lan" + i + " " + recSize);

						System.out.println("lan" + i + " " + raf.getFilePointer());
						String temp = raf.readInt() +"";
						if(param1.equals(temp+ "")) {
						String name = ts.readFixedName(raf);
						String date = ts.readFixedDate(raf);
						raf.seek(headSize + recSize*i + (124 + 100));
						long size = raf.readLong();
						res = name + ", " + date + ", " + size +"";
						break;
						}else {
							res = "not exits!";
						}
					}
					break;
				}

				default:
					break;
				}
				netOut.println(res);
			}
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
}
