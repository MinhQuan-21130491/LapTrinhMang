package file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;
import java.util.Iterator;

public class Test {
	public static void main(String[] args) throws IOException {
//		String name = "Lê Minh Quân";
//		String path = "D:\\ltm\\data.txt";
//		DataOutputStream os = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path)));
//		os.writeChars(name);
//		os.close();
//		
//		DataInputStream is = new DataInputStream(new BufferedInputStream(new FileInputStream("D:\\ltm\\data.txt")));
//		for (int i = 0; i < 12; i++) {
//			System.out.println(is.readChar());
//		}
//		
//		System.out.println("Nguyễn Thị Hoa Hồng");
//		
////		OutputStream os = new BufferedOutputStream(new FileOutputStream("D:\\ltm\\data.txt"));
////		os.write(null);
//		
////		InputStream is = new BufferedInputStream(new FileInputStream("D:\\ltm\\data.txt"));
////		byte[] arr = new byte[20];
////		int bytesRead;
////		while ((bytesRead = is.read(arr)) != -1) {
////		    String str = new String(arr, 0, bytesRead, "UTF-8");
////		    System.out.print(str);
////		}
////		is.close();
////		
		 // create a new RandomAccessFile with filename Example
//        RandomAccessFile raf = new RandomAccessFile("D:/test.txt", "rw");
//
//        // write something in the file
//        raf.writeUTF("Hello World");
//		try {
//            // Tạo một đối tượng RandomAccessFile với chế độ "rw" (read/write)
//            RandomAccessFile raf = new RandomAccessFile("D:\\test.dat", "rw");
//
//            // Ghi một số long vào tệp
//            long numberToWrite = 123456789;
//            raf.writeLong(numberToWrite);
//
//            // Đặt con trỏ tệp về đầu tệp
//            raf.seek(0);
//            System.out.println(raf.getFilePointer());
//
//            // Đọc số long từ tệp
//            long numberRead = raf.readLong();
//            System.out.println("Số long được đọc: " + numberRead);
//            System.out.println(raf.getFilePointer());
//            // Đóng tệp và giải phóng tài nguyên
//            raf.close();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//
//	FileOutputStream fos = new FileOutputStream("D:\\ltm\\testFromBinaryToText.txt");
//	String text = "MinhQuan";
//	byte[] arr = text.getBytes();
//	fos.write(arr);

	
	}

}
