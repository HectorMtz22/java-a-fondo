package cap07;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DemoServerBytes {
	private static final int BUFFER_LENGTH = 3;

	public static void main(String[] args) throws Exception {
		BufferedReader br = null;
		BufferedWriter bw = null;
		
		Socket s = null;
		ServerSocket ss = new ServerSocket(5432);
		
		while (true) {
			try {
				System.out.println("Waiting connections...");
				s = ss.accept();
				
				System.out.println("Connected from: " + s.getInetAddress());
				
				br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				bw = new BufferedWriter(new PrintWriter(s.getOutputStream()));
				
				char bEnviar[];
				char bRecive[] = new char[BUFFER_LENGTH];
				
				StringBuffer sb = new StringBuffer();
				
				// read name
				int n;
				while ((n = br.read(bRecive)) == BUFFER_LENGTH) {
					sb.append(bRecive);
				}
				sb.append(bRecive, 0, n);
				
				String greet = "Hello " + sb + " " + System.currentTimeMillis(); 
				
				bEnviar = greet.toCharArray();
				bw.write(bEnviar);
				bw.flush();
				
				System.out.println("Greet sent!");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (bw != null) bw.close();
				if (br != null) br.close();
				if (s != null) s.close();
				System.out.println("Connection closed");
			}
		}
	}
}
