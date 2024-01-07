package splitAndJoin;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Split {
	private String makeDestFileName(String source, int order) {
		String ext;
		if(order < 10) ext = ".00" + order;
		else if(order < 100) ext = ".0" +order;
		else ext ="."+order;
		return source + ext;
	}
	private boolean transfer(InputStream is, OutputStream os, int pSize) throws IOException {
		byte[] buff = new byte[100*1024];
		int remain = pSize;
		int readByte;
		int reqByte;
		while(remain > 0) {
			reqByte = (remain > buff.length)?buff.length: remain;
			readByte = is.read(buff, 0, reqByte);
			if(readByte == -1) return false;
			os.write(buff, 0, readByte);
			remain -= readByte;
		}
		return true;
	}
	public void split(String source, int pSize) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(source));
		OutputStream os;
		String dest;
		boolean res = false;
		int order = 0;
		do {
			order++;
			dest = makeDestFileName(source, order);
			os = new FileOutputStream(dest);
			res = transfer(is, os, pSize);		
			os.close();
			
		} while (res);
		is.close();
	}

}
