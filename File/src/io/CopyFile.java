package io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;

public class CopyFile {

	public static boolean fileCopyOrMove(String source, String des, boolean move) throws IOException {
		File fileSource = new File(source);
		InputStream inT = new BufferedInputStream(new FileInputStream(fileSource));
		OutputStream outT =  new BufferedOutputStream(new FileOutputStream(new File(des)));
		byte[] array = new byte[1024 * 1000];
		int data;
		try {
			while ((data = inT.read(array)) != -1) {
				// System.out.println(data);
				outT.write(array, 0, data);
			}
			inT.close();
			outT.close();
			if (move)
				fileSource.delete();

			return true;
		} catch (Exception e) {
			System.out.println("Error!!!!!!!!!!");
			return false;
		}

	}

	public static boolean folderCopyOrMove(String source, String des, boolean move) throws FileNotFoundException {
		try {
			File fileSource = new File(source);
			File fileDes = new File(des);
			if (!fileSource.exists() || !fileSource.isDirectory()) {
				System.out.println("Thư mục nguồn không tồn tại!");
				return false;
			}
			if (!fileDes.exists()) {
				fileDes.mkdir();
			}
			byte[] array = new byte[1024 * 1000];
			int data;
			File[] lists = fileSource.listFiles();
			if (lists != null) {
				for (File file : lists) {
					if (file.isFile()) {
						InputStream inT = new FileInputStream(file);
						String pathFileDes = des + File.separator + file.getName(); // File.separator để lấy ra dấu \\
																					// đối với window
						OutputStream outT = new FileOutputStream(pathFileDes);
						while ((data = inT.read(array)) != -1) {
							outT.write(array, 0, data);
						}

					} else if (file.isDirectory()) {
						String pathSourceSub = source + File.separator + file.getName();
						String pathFileDesSub = des + File.separator + file.getName();
						folderCopyOrMove(pathSourceSub, pathFileDesSub, move);
					}
				}

			}
			if (move)
				fileSource.delete();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public static void main(String[] args) throws IOException {
//		write();
//		read();
		//readFile("D:\\ltm\\text.txt");
		//readFileChar("D:\\ltm\\text.txt");
		System.out.println(fileCopyOrMove("D:\\ltm\\text.txt", "D:\\ltm\\text-copy.txt", false));
		// System.out.println(folderCopyOrMove("D:\\ltm\\text", "D:\\ltm\\text-copy",false));
     
	}
}
