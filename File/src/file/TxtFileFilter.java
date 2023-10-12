package file;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;

public class TxtFileFilter implements FileFilter {

	@Override
	public boolean accept(File pathname) {
		
		return pathname.getAbsolutePath().endsWith(".txt");
	}
	public static ArrayList<String> check(FileFilter fileFilter, String folder) throws IOException {
		ArrayList<String> result = new ArrayList<>();
		File file = new File(folder);
		File[]  files = file.listFiles(fileFilter);
		for (File file2 : files) {
			String path = file2.getCanonicalPath();
			result.add(path);
		}
		
		
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		String folder = "D:\\test";
		TxtFileFilter txtFileFilter = new TxtFileFilter();
		ArrayList<String> result = check(txtFileFilter, folder);
		for (String string : result) {
			System.out.println(string);
		}
	}

}
