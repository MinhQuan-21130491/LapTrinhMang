package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class ThiSinh {
	int id;
	String name;
	String date;
	String address;
	String fileName;
	
	public ThiSinh(int id, String name, String date, String address) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.address = address;
	}
	public ThiSinh() {
		// TODO Auto-generated constructor stub
	}
	public void fixedName(DataOutput d) throws IOException {
		char c;
		for (int i = 0; i < 25; i++) {
			c = i < name.length()?name.charAt(i):0;
			d.writeChar(c);
		}
	}
	public String readFixedName(DataInput d) throws IOException {
		char c;
		String res = "";
		for (int i = 0; i < 25; i++) {
			c = d.readChar();
			if(c!=0) {
				res += c;
			}
		}
		return res;
	}
	public String readFixedDate(DataInput d) throws IOException {
		char c;
		String res = "";
		for (int i = 0; i < 10; i++) {
			c = d.readChar();
			if(c!=0) {
				res += c;
			}
		}
		return res;
	}
	public void fixedNameFile(DataOutput d) throws IOException {
		char c;
		for (int i = 0; i < 50; i++) {
			c = i < fileName.length()?fileName.charAt(i):0;
			d.writeChar(c);
		}
	}
	public void fixedAddress(DataOutput d) throws IOException {
		char c;
		for (int i = 0; i < 25; i++) {
			c = i < address.length()?address.charAt(i):0;
			d.writeChar(c);
		}
	}
	public void fixedDate(DataOutput d) throws IOException {
		char c;
		for (int i = 0; i < 10; i++) {
			c = i < date.length()?date.charAt(i):0;
			d.writeChar(c);
		}
	}
	public void regis(DataOutput d) throws IOException {
		d.writeInt(id);
		fixedName(d);
		fixedDate(d);
		fixedAddress(d);
		 for (int i = 0; i < 100108; i++) {
			 d.writeByte(0); // Ghi các byte 0 để lấp đầy
         }
	}
	public void foto(DataOutput d, File file) throws IOException {
		fixedNameFile(d);
		d.writeLong(file.length());
		InputStream is = new BufferedInputStream(new FileInputStream(file));
		int data;
		long temp = 0;
		byte[] buffer = new byte[100*1024];
		while((data = is.read(buffer)) != -1) {
			temp = data;
			d.write(buffer, 0, data);
		}
		 for (int i = 0; i < 100000-temp; i++) {
			 d.writeByte(0); // Ghi các byte 0 để lấp đầy
         }
	;
		
	}
	public static void main(String[] args) throws IOException {
		System.out.println("ballchair-3.jpg".endsWith(".png"));
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
	

}
