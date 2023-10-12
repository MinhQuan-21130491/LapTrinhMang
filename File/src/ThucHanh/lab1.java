package ThucHanh;

import java.io.File;

public class lab1 {
	public static boolean remove(String path) {
		File file = new File(path);
		if (!file.exists()) return false;
		if (file.isDirectory()) {
			File[] lists = file.listFiles();
			if(lists != null) for (File file2 : lists) remove(file2.getAbsolutePath());
		}
		return file.delete();
	}

	public static void main(String[] args) {
		System.out.println(remove("D:\\Directory1"));
	}
}
