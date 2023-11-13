package file;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class Student {
	private int id;
	private String name;
	private int bYear;
	private double grade;
	public Student(int id, String name, int bYear, double grade) {
		super();
		this.id = id;
		this.name = name;
		this.bYear = bYear;
		this.grade = grade;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getbYear() {
		return bYear;
	}
	public void setbYear(int bYear) {
		this.bYear = bYear;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", bYear=" + bYear + ", grade=" + grade + "]";
	}
	public void writeFixedLengthName(DataOutput dt, int len) throws IOException {
		char c;
		for (int i = 0; i < len; i++) {
			c = (i< name.length())?name.charAt(i):0;
			dt.writeChar(c);
		}
	}
	private String readFixedLengthName(DataInput dt, int len) throws IOException {
		String res = "";
		char c;
		for (int i = 0; i < len; i++) {
			c = dt.readChar();
			if(c!= 0) {
				res +=c;
			}
		}
		return res;
	}
	public void writeStudent(DataOutput dos, int len) throws IOException {
		dos.writeInt(id);
		writeFixedLengthName(dos, len);
		dos.writeInt(bYear);
		dos.writeDouble(grade);
	}
	public void readStudent(DataInput dis, int len) throws IOException {
		id = dis.readInt();
		name = readFixedLengthName(dis, len);
		bYear = dis.readInt();
		grade = dis.readDouble();
	}
	//hsize + index + recosize
	
	

}
