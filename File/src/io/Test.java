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
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class Test {
public static void main(String[] args) throws IOException {
//	InputStream is = new FileInputStream(new File("D:\\ltm\\t.txt"));
//byte [] ar = new byte[4];
//is.read(ar);
//String str = new String(ar);
//System.out.println(str);
    BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\ltm\\t.txt"), "UTF-8"));
System.out.println(bf.readLine());
}
}
	