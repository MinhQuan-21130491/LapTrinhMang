package ThucHanh;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.StringTokenizer;

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
	public Student() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", bYear=" + bYear + ", grade=" + grade + "]";
	}
	private void writeFixedLengthName(DataOutput dos, int len) throws IOException {
		char c;
		for (int i = 0; i < len; i++) {
			c = (i< name.length())?name.charAt(i):0;
			dos.writeChar(c);
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
	public Student readStudent(DataInput dis, int len) throws IOException {
		id = dis.readInt();
		name = readFixedLengthName(dis, len);
		bYear = dis.readInt();
		grade = dis.readDouble();
		return new Student(id, name, bYear, grade);
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
	public void importStudent(String line) {
		Student re = new Student();
		StringTokenizer str = new StringTokenizer(line, "\t");
		while(str.hasMoreTokens()) {
			setId(Integer.parseInt(str.nextToken()));
			setName(str.nextToken());
			setbYear(Integer.parseInt(str.nextToken()));
					}
	}
	public String export() {
		return id + "\t" + name + "\t" + bYear + "\t" + grade;
	}
	
	

}
