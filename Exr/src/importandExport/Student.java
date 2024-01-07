package importandExport;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Student {
	int id;
	String name;
	int bYear;
	double grade;
	public Student() {
		// TODO Auto-generated constructor stub
	}
	public Student(int id, String name, int bYear, double grade) {
		super();
		this.id = id;
		this.name = name;
		this.bYear = bYear;
		this.grade = grade;
	}

	public void importStudent(String line) {
		StringTokenizer stk = new StringTokenizer(line, "\t");
//		while(stk.hasMoreTokens()) {
			id = Integer.parseInt(stk.nextToken());
			name = stk.nextToken();
			bYear = Integer.parseInt(stk.nextToken());
		//}
	}
	public String export() {
		return id + "\t" + name + "\t" + bYear + "\t" + grade;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", bYear=" + bYear + ", grade=" + grade + "]";
	}
	

}
