package file;

import java.io.File;

public class CreateFolder {
	  public static void main(String[] args) {
	        File file = new File("D:\\Directory1\\child1\\child2");
	        if (!file.exists()) {
	            if (file.mkdirs()) {
	                System.out.println("Directory is created!");
	            } else {
	                System.out.println("Failed to create directory!");
	            }
	        }
		//  System.out.println(file.length());
	    }
	  
}
