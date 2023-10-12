package ThucHanh;

import java.io.File;
import java.io.IOException;

public class FileCheck {

	// • Xác định file/folder có tồn tại hay không?
	public static boolean checkExists(String name) {
		File file = new File(name);
		return file.exists();
	}

	// • Xác định các thuộc tính hệ thống (quyền đọc, ghi,..)của file/folder
	public static void checkPer(String name) {
		File file = new File(name);
		if (file.canRead()) {
			System.out.println("File/folder có quyền đọc");
		}
		if (file.canWrite()) {
			System.out.println("File/folder có quyền ghi	");
		}
	}

	// tạo xóa file bất kì
	public static boolean createFile(String name) {
		File file = new File(name);
		if (file.exists()) {
			try {
				if (file.createNewFile())
					return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public static boolean deleteFileAndFolder(String name) {
		File file = new File(name);
		if (file.exists()) {
			if (file.delete())
				return true;
		}
		return false;
	}

	// tạo folder bất kì
	public static boolean createFolder(String name) {
		File file = new File(name);
		if (file.exists()) {
			if (file.mkdir())
				return true;
		}
		return false;
	}
	
	//duyệt cây thư mục
//	public static void dirTree_copy(String rootFile) {
//		int level = ++level;
//		File file = new File(rootFile);
//		if(file.isDirectory()) {
//			String upperCase = file.getName().toUpperCase();
//			getPadding(level, upperCase);
//			File[] list = file.listFiles();
//			for (File child : list) {
//				dirTree_copy(child.getAbsolutePath());
//			}
//		}
//		else {
//			String lowerCase = file.getName().toLowerCase();
//			getPadding(level,lowerCase );
//		}
//	}
//	public static void dirTree(String rootFile) {
//		File file = new File(rootFile);
//		if(file.isDirectory()) {
//			String upperCase = file.getName().toUpperCase();
//		    System.out.println(upperCase);
//			File[] list = file.listFiles();
//			for (File child : list) {
//				dirTreeHelper(1,child);
//			}
//		}
//	}
//	public static void dirTreeHelper(int level, File file) {
//		StringBuffer 
//		System.out.println(file.getName());
//		if(file.isDirectory()) 
//		
//	}

	public static void printListFile(File[] list) {
		for (File file : list) {
			System.out.println(file.getAbsolutePath());
		}
	}

	public static void main(String[] args) {
		// System.out.println(checkExists("filename.txt"));
		// checkPer("D:\\Directory1");
		// System.out.println(deleteFileAndFolder("D:\\Directory1"));
		// deleteAll("D:\\Directory1");
		// File file = new File("D:\\directory1");
		// File[] lists = file.listFiles();
//		System.out.println(lists.length);
		// System.out.println(file.isDirectory());
		// printListFile(lists);
		
		//dirTree("D:\\ltm\\text");
		

	}

}
