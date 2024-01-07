package raf;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class Student {
	int mssv;
	String name;
	int bYear;
	double grade;
	public Student(int mssv, String name, int bYear, double grade) {
		super();
		this.mssv = mssv;
		this.name = name;
		this.bYear = bYear;
		this.grade = grade;
	}
	public Student() {
		// TODO Auto-generated constructor stub
	}
	public  void writeFixedName(DataOutput dos, int len) throws IOException {
		char c;
		for (int i = 0; i < len; i++) {
			c = i < name.length()?c = name.charAt(i):0;
			dos.writeChar(c);
		}
	}
	public String readFixedName(DataInput dis, int len) throws IOException {
		String res = "";
		char c;
		for (int i = 0; i < len; i++) {
			c = dis.readChar();
			if(c != 0) {
				res +=c;
			}
		}
		return res;
	}
	public void writeStudent(int len, DataOutput dos) throws IOException {
		dos.writeInt(mssv);
		writeFixedName(dos,len );
		dos.writeInt(bYear);
		dos.writeDouble(grade);
	}
	public Student readStudent(DataInput dis, int len) throws IOException {
		mssv = dis.readInt();
		name = readFixedName(dis, len);
		bYear = dis.readInt();
		grade = dis.readDouble();
		return new Student(mssv, name, bYear, grade);
	}
	@Override
	public String toString() {
		return "Student [mssv=" + mssv + ", name=" + name + ", bYear=" + bYear + ", grade=" + grade + "]";
	}
	
}
