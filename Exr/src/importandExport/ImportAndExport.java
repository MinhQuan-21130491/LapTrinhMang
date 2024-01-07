package importandExport;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ImportAndExport {
	public static ArrayList<Student> load(String fileDiem, String fileSv) throws IOException {
		ArrayList<Student> re = new ArrayList<>();
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(fileSv), "utf-8"));
		String line;
		while((line = bf.readLine()) != null) {
			Student st = new Student();
			st.importStudent(line);
			re.add(st);
		}
		BufferedReader bf2 = new BufferedReader(new InputStreamReader(new FileInputStream(fileDiem), "utf-8"));
		while((line = bf2.readLine()) != null) {
			StringTokenizer stk = new StringTokenizer(line, "\t");
			int id = Integer.parseInt(stk.nextToken());
			double avgGrade = 0;
			int count = 0;
			while(stk.hasMoreTokens()) {
				count++;
				avgGrade += Double.parseDouble(stk.nextToken());
			}
			avgGrade = avgGrade / count;
			for (Student student : re) {
				if(student.id == id) student.grade = avgGrade;
			}
		}
		bf.close();
		bf2.close();
		return re;
	}
	public static void exportSt(ArrayList<Student> listSt, String des) throws UnsupportedEncodingException, FileNotFoundException {
		PrintWriter pr = new PrintWriter(new OutputStreamWriter(new FileOutputStream(des), "utf-8"));
		for(Student st: listSt) {
			System.out.println(st.export());
			pr.println(st.export());
		}
		pr.close();
		
	}
	public static void main(String[] args) throws IOException {
		String fileSv = "D:\\ltm\\listSt.txt";
		String fileDiem = "D:\\ltm\\listGrade.txt";
		//System.out.println(ImportAndExport.load(fileDiem, fileSv));
		ImportAndExport.exportSt(ImportAndExport.load(fileDiem, fileSv), "D:\\ltm\\result.txt");
	}

}
