import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Local {

	public static void main(String[] args) {

		try {
			ServerSocket s = new ServerSocket();
			Socket cS = s.accept();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(cS.getInputStream()

					));
			
			InetAddress me = InetAddress.getLocalHost(); 
			
			System.out.println("My name is " + me.getHostName());
			System.out.println("My local address is " + me.getHostAddress());
		} catch (UnknownHostException e) {
			System.err.println("Could not determine local address.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}