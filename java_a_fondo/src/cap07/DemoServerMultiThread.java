package cap07;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class DemoServerMultiThread {

	public static void main(String[] args) throws Exception {
		Socket s = null;
		ServerSocket ss = new ServerSocket(5432);
		
		// Always listening
		while (true) {
			try {
				// When we have a new connection
				s = ss.accept();
				
				// Instantiate a thread
				new Tarea(s).start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	static class Tarea extends Thread {
		private Socket s = null;
		private ObjectInputStream ois = null;
		private ObjectOutputStream oos = null;
		
		// constructor: we receive the socket
		public Tarea(Socket socket) {
			this.s = socket;
		}
		
		public void run() {
			try {
				System.out.println("Connected from: " + s.getInetAddress());
				
				// mask in and out bytes
				ois = new ObjectInputStream(s.getInputStream());
				oos = new ObjectOutputStream(s.getOutputStream());
				
				String name = (String)ois.readObject();
				
				String greet = "Hello " + name;
				
				oos.writeObject(greet);
				System.out.println("Greet sent!");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (oos != null) oos.close();
					if (ois != null) ois.close();
					if (s != null) s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
