package socketLogin;

public class Student {
	int id;
	String name;
	int age;
	int bYear;
	double grade;
	public Student(int id, String name, int age, int bYear, double grade) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.bYear = bYear;
		this.grade = grade;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", bYear=" + bYear + ", grade=" + grade + "]";
	}
	
	

}
