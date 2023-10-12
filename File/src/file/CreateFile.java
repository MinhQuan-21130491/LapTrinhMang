package file;

import java.io.File;
import java.io.IOException;

public class CreateFile {
	public static boolean createFile (String fileName) {
		boolean flag = false;
		File newFile = new File(fileName);
		try {
			if(newFile.createNewFile()) {
				flag = true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	public static void main(String[] args) {
		System.out.println(createFile("D:\\Directory1\\child1\\child2\\filename1.txt"));
	}

}