package socketcopyfilefolder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class Sever {
	private ServerSocket severSocket;
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	private BufferedInputStream bis;
	private BufferedOutputStream bos;
	public Sever() {
		 try {
			severSocket = new ServerSocket(7);
			System.out.println("Waiting for client connect....");
			socket = severSocket.accept();
			System.out.println("Client " + socket.getInetAddress() + " is connected");
			dis = new DataInputStream(socket.getInputStream());
			 dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void copyFileOrFolder() {
				try {
					String source = dis.readUTF() ;
					String des = dis.readUTF();
					copyFileOrFolderHelper(source, des);
					socket.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	public void copyFileOrFolderHelper(String source, String des) {
		try {
			File fileSource = new File(source);
			File fileDes = new File(des);
			if(fileSource.isDirectory()) {
				if (!fileDes.exists()) {
					fileDes.mkdir();
				}
			}	
			byte[] array = new byte[1024 * 1000];
			int data;
			File[] listFile = fileSource.listFiles();
			if(listFile != null) {
				for (File file : listFile) {
					if(!file.isDirectory()) {
					 bis = new BufferedInputStream(new FileInputStream(file));
					String pathFileDes = des + file.separator + file.getName();
					 bos = new BufferedOutputStream(new FileOutputStream(pathFileDes));
					while((data = bis.read(array) )!= -1) {
						bos.write(array, 0, data);
						bos.flush();
					}
					}else {
					String pathSourceSub = source + File.separator + file.getName();
					String pathFileDesSub = des + File.separator + file.getName();
					copyFileOrFolderHelper(pathSourceSub, pathFileDesSub);
					}
				}
			}else {
				 bis = new BufferedInputStream(new FileInputStream(fileSource));
					 bos = new BufferedOutputStream(new FileOutputStream(des));
					while((data = bis.read(array) )!= -1) {
						bos.write(array, 0, data);
						bos.flush();
					}
			}
			dis.close();
			dos.close();
			bis.close();
			bos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Sever().copyFileOrFolder();
	}

}
