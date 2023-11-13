package ThucHanh;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lab3 {
	public static void split(String source, int pSize) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(source));
		OutputStream os;
		File fileSource = new File(source);
		File newFile = new File(fileSource.getParent() + "\\split");
		newFile.mkdir();
		boolean flag =  true;
		String des;
		int count = 0;
		do {
			count++;
			des = getDesFileName(newFile.getAbsolutePath() + "\\"+ fileSource.getName(),count);
			os =  new BufferedOutputStream(new FileOutputStream(des));
			flag = transferTo(is, os, pSize);
			
		} while (flag);
		is.close();
	}
	private static String getDesFileName(String source, int count) {
		String des = "";
		if(count < 10) {
			des = source + ".00" + count;
		}else if(count < 100) {
			des = source + ".0" + count;
		}else {
			des = source + "." + count;
		}
		return des;
	}
	private static boolean transferTo(InputStream is, OutputStream os, int pSize) throws IOException {
		int readByte;
		for (int i = 0; i < pSize; i++) {
			 readByte = is.read();
			if(readByte == -1) {
				os.close();
				return false;
			}
			
			os.write(readByte);
			
		}
		os.close();
		
		return true;
	}

//	private static boolean transferTo(InputStream is, OutputStream os, int pSize) throws IOException {
//		byte[] arrByte= new byte[1000*1024];
//		int byteRead ;
//		int byteReq;
//		int remain = pSize;
//		while(remain > 0) {
//			byteReq = (remain > arrByte.length)?arrByte.length:remain;
//			byteRead = is.read(arrByte, 0, byteReq);
//			if(byteRead == -1) {os.close() ;return false;}
//			os.write(arrByte, 0, byteRead);
//			remain -= byteRead;
//		}
//		os.close();
//		return true;
//	}
	public static void join(String source, String des) throws IOException {
		File file = new File(source);
		File[] listFile = file.listFiles();
		OutputStream ops = new BufferedOutputStream(new FileOutputStream(des));
		byte[] arr = new byte[102400];
		int data;
		for (File f : listFile) {
			InputStream ips = new BufferedInputStream(new FileInputStream(f));
			while((data = ips.read(arr)) != -1) {
				ops.write(arr, 0, data);
			}
			ips.close();
		}
		ops.close();
	}

	public static void main(String[] args) throws IOException {
		String source = "D:\\ltm\\text.txt.txt.001";
//		int pSize = 3;
//		split(source, pSize);
		join("D:\\ltm\\split", "D:\\ltm\\join\\join.txt");
		

	
		
	}
   
 
}
