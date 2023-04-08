package cap07;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class DemoClienteBytes {
	static final String IP = "127.0.0.1";
	static final int PORT = 5432;

	private static final int BUFFER_LENGTH = 3;
	public static void main(String[] args) throws Exception {
		BufferedReader br = null;
		BufferedWriter bw = null;
		
		Socket s = null;
		
		try {
			s = new Socket(IP, PORT);
			System.out.println("Connected");
			
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			bw = new BufferedWriter(new PrintWriter(s.getOutputStream()));
			
			char bEnvia[] = "12345".toCharArray();
			char bRecibe[] = new char[BUFFER_LENGTH];
			System.out.println("Enviando...");
			bw.write(bEnvia);
			bw.flush();
			
			System.out.println("Enviado!");
			int n;
			StringBuffer sb = new StringBuffer();
			while ((n = br.read(bRecibe)) == BUFFER_LENGTH) {
				System.out.println(n);
				sb.append(bRecibe);
			}
			sb.append(bRecibe, 0, n);
			
			System.out.println(sb);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) br.close();
			if (bw != null) bw.close();
			if (s != null) s.close();
		}
		
	}

}
