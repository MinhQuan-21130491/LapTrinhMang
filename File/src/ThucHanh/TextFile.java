package ThucHanh;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TextFile {
	
	public static ArrayList<Student> load(String fileThongTin, String fileDiem) throws IOException {
		ArrayList<Student> re = new ArrayList<>();
	
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(fileThongTin), "UTF-8"));
		String line;
		while ((line = bf.readLine()) != null) {
			Student st = new Student();
		    st.importStudent(line);
		    re.add(st);
		}
		bf.close();
		BufferedReader bfGrade = new BufferedReader(new InputStreamReader(new FileInputStream(fileDiem), "UTF-8"));
		while((line = bfGrade.readLine()) != null) {
			StringTokenizer str = new StringTokenizer(line,"\t");
			int count = 0;
			int id =Integer.parseInt(str.nextToken());
			double totalGrade = 0;
			while(str.hasMoreTokens()) {
			count++;
			totalGrade += Double.parseDouble(str.nextToken());
			}
			double avg = totalGrade / count;
			for (int i = 0; i < re.size(); i++) {
				if(re.get(i).getId() == id) {
					re.get(i).setGrade(avg);
				}
			}
			}
		bfGrade.close();
		return re;
		
	}
	public static void xuatExcel(ArrayList<Student> list, String des, String charSet) throws UnsupportedEncodingException, FileNotFoundException {
		PrintWriter pr = new PrintWriter(new OutputStreamWriter(new FileOutputStream(des), charSet));
		String line;
		for (Student student : list) {
			line = student.export();
			pr.println(line);
		}
		pr.close();
		
	}
	public static void main(String[] args) throws IOException {
		String listSv = "D:\\ltm\\sv.txt";
		String listDiem = "D:\\ltm\\grade.txt";
		String des = "D:\\ltm\\text.txt";
		ArrayList<Student> result = new ArrayList<>();
		result = load(listSv, listDiem);
	
		xuatExcel(result, des, "UTF-8");
		
	}

}
