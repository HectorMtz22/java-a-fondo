package cap07;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class DemoServer {
	public static void main(String[] args) throws Exception {
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		
		Socket s = null;
		ServerSocket ss = new ServerSocket(5432);
		
		// This is for always listening
		while (true) {
			try {
				s = ss.accept(); 
			
				System.out.println("Connection from: " + s.getInetAddress());
				
				// mask in and out bytes
				ois = new ObjectInputStream(s.getInputStream());
				oos = new ObjectOutputStream(s.getOutputStream());
				
				// read name from client
				// and cast it to a string
				String name = (String)ois.readObject();
				
				// make the output
				String greet = "Hello " + name + " " + System.currentTimeMillis();
				// send greet
				oos.writeObject(greet);
				
				System.out.println("Greet Send!");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (ois != null) ois.close();
				if (oos != null) oos.close();
				if (s != null) s.close();
				System.out.println("Connection closed");
			}
		
		}	
		
	}

}
