package io;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public class Test {
public static void test() {
	boolean a = false;
	if(a) {
	System.out.println("minhquan");
	}else {
		System.out.println("huyenngoc");
	}
	
}
public static void main(String[] args) throws IOException {
//PrintWriter bf = new PrintWriter(new File("D:\\ltm\\text3.txt"));
//char[] arr = new char[10];
//String str = "Minhquan";
//for (int i = 0; i < str.length(); i++) {
//	arr[i] = str.charAt(i);
//}
//bf.write(arr);
//bf.close();

FileInputStream ips = new FileInputStream(new File("D:\\ltm\\text3.txt"));
byte[] arr = new byte[10];
ips.read(arr);
String string = new String(arr);
System.out.print(string);
}
}
