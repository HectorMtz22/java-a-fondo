package cap07;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class DemoCliente {
	static String IP = "127.0.0.1";
	static int PORT = 5432;
	public static void main(String[] args) throws Exception {
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		Socket s = null;
		
		try {
			// instantiate socket with ip and port
			s = new Socket(IP, PORT);
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
			
			// send name
			oos.writeObject("Fernando");
			
			// receive greeting
			String res = (String)ois.readObject();
			
			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (s != null) s.close();
			if (ois != null) ois.close();
			if (oos != null) oos.close();
		}
	}

}
