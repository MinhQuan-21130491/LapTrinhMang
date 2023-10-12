package ThucHanh;

import java.io.File;
import java.util.ArrayList;

public class DirTree_2 {
	public static ArrayList<Long> listSize = new ArrayList<>();
	public static void dirTree(String path) {
		File file = new File(path);
		if(!file.exists()) return;
		if(file.isDirectory()) {
			dirTreeHelper(file, 0);
		}else {
			printFile(file, 0);
		}
	}
	private static long dirTreeHelper(File file, int level) {
		long total = 0;
		printFolder(file, level);
	
		File[] list = file.listFiles();
		for (File f : list) {
			if(f.isDirectory()) {	
				total += dirTreeHelper(f, level+1);
				System.out.println(total);
			}
		}
		for (File f : list) {
			if(!f.isDirectory()) {			
				total += printFile(f, level+1);	
				System.out.println(total);
			}
		}
		
		listSize.add(total);
		System.out.println(listSize);
		return total;
	}
	private static void printFolder(File file, int level) {
		StringBuilder sb = getIndent(file.getName().toUpperCase(), level);
		//sb.append(":").append(size);
		System.out.println(sb.toString());
	}
	private static long printFile(File file, int level) {
		StringBuilder sb = getIndent(file.getName().toLowerCase(), level);
		System.out.println(sb.toString());
		System.out.println(file.length());
		return file.length();
	}
	private static StringBuilder getIndent(String fileName, int level) {
		StringBuilder sb = new StringBuilder();
		if(level < 1) {return sb.append(fileName);}
		sb.append("   ");
		for (int i = 1; i < level; i++) {
			sb.append("|   ");
		}
		sb.append("+-" +fileName);
		
		return sb;
	}
	public static void main(String[] args) {
		dirTree("D:\\ltm\\text");
	//	System.out.println(listSize);
	}
}