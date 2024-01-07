package raf;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;


public class RafListStudent {
	final int name_length = 50;
	int headSize = 8;
	int len;
	int count;
	int recSize;
	RandomAccessFile raf;
	public RafListStudent(String file) throws IOException {
		raf = new RandomAccessFile(file, "rw");
		// if raf exits
		if(raf.length() > 0) {
			count = raf.readInt();
			recSize = raf.readInt();
			len = (recSize - 16)/2;
		}else { //create raf
			len = name_length;
			recSize = 16 + len*2;
			raf.writeInt(count);
			raf.writeInt(recSize);
		}
	}
	public void addStudent(Student st) throws IOException {
		count++; //so sinh vien
		System.out.println(count);
		raf.seek(raf.length());
		st.writeStudent(len, raf);
		raf.seek(0);
		raf.writeInt(count);
	}
	public Student readStudent(int index) throws IOException {
	int pos = headSize + index * recSize;
	raf.seek(pos);
	Student st = new Student();
	return st.readStudent(raf, len);
	}
	public void updateStudent(int index, Student st) throws IOException {
		int pos = headSize + index * recSize;
		raf.seek(pos);
		st.writeStudent(len, raf);
	}
	public ArrayList<Student> searchByName(String name) throws IOException {
		ArrayList<Student> re = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			Student st = readStudent(i);
			if(st.name.equalsIgnoreCase(name)) {
				re.add(st);
			}
		}
		return re;
	}
	public static void main(String[] args) throws IOException {
		RafListStudent raf = new RafListStudent("D:\\ltm\\st.txt");
		Student st1 = new Student(1, "MINHQUAN", 2003, 10);
		//Student st2 = new Student(2, "MINHQUAN", 2003, 10);
		raf.addStudent(st1);
		//System.out.println(raf.readStudent(0));
		//raf.updateStudent(0, st2);
		//System.out.println(raf.searchByName("MINHQUAN"));
	}

}
