package file;

import java.util.ArrayList;

import java.io.*;

public class FileExt {
	public static ArrayList<String> getFileByExt (String folder, String ext) throws IOException {
		ArrayList<String> result = new ArrayList<>();
		ext = "." + ext;
		File file = new File(folder);
		File[] list = file.listFiles();
		for (File string : list) {
			String dir = string.getCanonicalPath();
			if(dir.endsWith(ext)) {
				result.add(dir);
			}
		}
		
		return result;
	}
	public static void main(String[] args) throws IOException {
		String folder = "D:\\test";
		String ext= "txt"; 
		ArrayList<String> result = getFileByExt(folder, ext);
		for (String string : result) {
			System.out.println(string);
		}
	}

}
