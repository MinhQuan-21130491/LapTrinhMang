package packAndExtract;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class PackAndExtract {
	public static List<File> getListFile(String source) {
		ArrayList<File> list = new ArrayList<>();
		File file = new File(source);
		File[] lf = file.listFiles();
		for (File f : lf) {
			list.add(f);
		}
		return list;
	}
	public static void pack(String source, String dest) throws IOException {
		List<File> files = getListFile(source);
		RandomAccessFile raf = new RandomAccessFile(dest, "rw");
		raf.writeInt(files.size());
		long[] posTemp = new long[files.size()];
		int index = 0;
		long pos = 0;
		//header 
		for (File f : files) {
			posTemp[index++] = raf.getFilePointer();
			raf.writeLong(pos);
			raf.writeLong(f.length());
			raf.writeUTF(f.getName());
		}
		//write data
		index = 0;
		for (File f : files) {
			pos = raf.getFilePointer();
			raf.seek(posTemp[index++]);
			raf.writeLong(pos);
			raf.seek(pos);
			
			byte[] buff = new byte[100*1024];
			int data = 0;
			InputStream is = new BufferedInputStream(new FileInputStream(f));
			while((data = is.read(buff)) != -1 ) {
				raf.write(buff, 0, data);
			}
			is.close();
		}
		raf.close();
	}
	public static void unPack(String source, String needUn, String dest) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(source, "r");
		int size = raf.readInt();
		for (int i = 0; i < size; i++) {
			long pos = raf.readLong();
			long len = raf.readLong();
			String name = raf.readUTF();
			if(name.equalsIgnoreCase(needUn)) {
				raf.seek(pos);
				OutputStream os = new BufferedOutputStream(new FileOutputStream(dest));
				for(long k=0; k<len; k++) {
					os.write(raf.read());
				}
				os.close();
				break;
			}
		}
		raf.close();
		
	}
	public static void main(String[] args) throws IOException {
		String source = "D:\\ltm\\folder";
		String extract ="New Text Document.txt";
		String des = "D:\\ltm\\folder\\text-copy.txt";
		String pack = "D:\\ltm\\pack\\pack.txt";
		//pack(source, pack);
		unPack(pack, extract, des);
	}
}
