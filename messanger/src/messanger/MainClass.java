package messanger;

import javax.swing.JFrame;

public class MainClass {

	public static void main(String[] args) {
		
		Server server = new Server();
		
		server.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Close on x
		server.startRunning();
		
	}

}
