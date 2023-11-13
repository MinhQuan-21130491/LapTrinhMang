package ThucHanh;

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
		List<File> re = new ArrayList<>();
		File file = new File(source);
		File[] files = file.listFiles();
		for (File f : files) {
			if(f.isFile()) {
				re.add(f);
			}
		}
		return re;
	}
	
	public static void pack(String source, String des) throws IOException {
	     List<File> files = getListFile(source);
	     File fileSource = new File(source);
	     File fileDes = new File(des);
	     RandomAccessFile raf = new RandomAccessFile(fileDes, "rw");
	     //header
	     int index = 0;
	     long[] hasPos = new long[files.size()];
	     raf.writeInt(files.size());
			for (File file : files) {
				hasPos[index++] = raf.getFilePointer();
				raf.writeLong(raf.getFilePointer());
				raf.writeLong(file.length());
				raf.writeUTF(file.getName());
			}
	     index = 0;
	     long temp = 0;
	     for (File file : files) {
	    	temp =raf.getFilePointer();
	    	raf.seek(hasPos[index++]);
			raf.writeLong(temp);
			raf.seek(temp);
		    InputStream is = new BufferedInputStream(new FileInputStream(file));
		    byte[] data = new byte[102400];
		    int rb;
		    while((rb = is.read(data)) != -1) raf.write(data, 0, rb);
		    is.close();
		}
	     raf.close();
}
	public static void unPack(String packFile, String extractFile, String desFile ) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(packFile, "r");
		long pos, length;
		String name;
		int fileNumber = raf.readInt();
		for (int i = 0; i <fileNumber; i++) {
			pos = raf.readLong();
			length = raf.readLong();
			name = raf.readUTF();
			if(name.equalsIgnoreCase(extractFile)) {
				raf.seek(pos);
				OutputStream os = new BufferedOutputStream(new FileOutputStream(desFile));
				transferTo(raf, os, length);
				os.close();
				break;
		}
		}
		raf.close();
		
		
		
	}
	private static boolean transferTo(RandomAccessFile is, OutputStream os,long pSize) throws IOException {
	byte[] arrByte= new byte[1000*1024];
	int byteRead ;
	int byteReq;
	long remain = pSize;
	while(remain > 0) {
		byteReq = (int) ((remain > arrByte.length)?arrByte.length:remain);
		byteRead = is.read(arrByte, 0, byteReq);
		if(byteRead == -1) {os.close() ;return false;}
		os.write(arrByte, 0, byteRead);
		remain -= byteRead;
	}
	os.close();
	return true;
}
	public static void main(String[] args) throws IOException {
		String source = "D:\\ltm\\folder";
		String extract ="New Text Document.txt";
		String des = "D:\\ltm\\folder\\text-copy.txt";
		String pack = "D:\\ltm\\pack\\pack.txt";
	// pack(source, pack);
		unPack(pack, extract, des);
	
	}
}
