package ThucHanh;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RafList {
	private static final int LENGTH_NAME = 50;
	 RandomAccessFile raf;
	int count;
	int headSize = 8;
	int recSize;
	 int len;
	public RafList(String file) throws IOException {
		raf = new RandomAccessFile(file, "rw");
		if(raf.length() > 0) { //exists ==> read header
			count = raf.readInt();
			recSize = raf.readInt();
			len = (recSize-16)/2;
		}else { // create new 
			len = LENGTH_NAME;
			recSize = 16 + len *2;
			raf.writeInt(count);
			raf.writeInt(recSize);
		}
	}
	public void addStudent(Student st) throws IOException {
				count++;
			    raf.seek(raf.length());
				st.writeStudent(raf, len);
				raf.seek(0);
				raf.writeInt(count);
	}
	public void updateStudent(int index, Student st) throws IOException {
		int pos = headSize + index * recSize;
		raf.seek(pos);
		st.writeStudent(raf, len);
	}
	public Student readStudent(int index) throws IOException {
		int pos = headSize + index * recSize;
		raf.seek(pos);
		Student st = new Student();
		return st.readStudent(raf, len);
	}
	public Student searchById(int id) throws IOException {
		Student result = null;
		Student st = new Student();
		int pos;
		for (int i = 0; i < count; i++) {
			pos = headSize + i * recSize;
			raf.seek(pos);
			st = st.readStudent(raf, len);
			if(st.getId() == id) {
				result = st;
				break;
			}	
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		Student st1 = new Student(1, "quan", 2003, 8);
		Student st2 = new Student(2, "ngoc", 2003, 8);
		
		RafList rl = new RafList("D:\\ltm\\st.txt");
		///rl.addStudent(st1);
		//System.out.println(rl.readStudent(0));
		//rl.addStudent(st2);
		//System.out.println(rl.readStudent(0));
		//Student st3 = new Student(3, "Long", 2003, 8);
		//rl.updateStudent(1, st3);
		//System.out.println(rl.readStudent(0));
		System.out.println(rl.searchById(2));
	}

}
