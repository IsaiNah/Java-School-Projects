package messanger;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Server extends JFrame{ // Jframe for GUI
	
	private JTextField userText;//Message Area
	private JTextArea chatWindow;//Displays the convo
	private ObjectOutputStream output;// output stream, goes out
	private ObjectInputStream input; // input stream, comes in
	private ServerSocket server; 
	private Socket connection; // Connection is a socket
	
	//CTOR
	public Server()
	{
		super("MESSENGER");
		userText = new JTextField();
		userText.setEditable(false);//Default - message box disabled, if not connected
		userText.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						sendMessage(event.getActionCommand());//retruns string typed in
						userText.setText("");
					}
				}
				);
			add(userText, BorderLayout.NORTH);
			chatWindow = new JTextArea();
			add(new JScrollPane(chatWindow));
			setSize(300,150);
			setVisible(true);
	
	}

	// set up and run the server
	
	public void startRunning()
	{
		try{
			server = new ServerSocket(6789, 1);//port, numConnections
			while(true)
			{
				try{
					//connect and conversate
					waitForConnection();
					setupStreams();//Setup IO streams
					whileChatting();//Sending messages
				}catch(EOFException eofExcept)
				{
					
					showMessage("\n Server ended the connection. ");
				}
				finally{
					closeAll(); // Cleanup
				}
			}
		}catch(IOException ioE)
		{
			ioE.printStackTrace();
		}
	}
	
	public void waitForConnection() throws IOException//Wait for connection, display connection info
	{	
		showMessage(" Setup complete.\n Waiting for someone to connect... ");
		connection = server.accept();//Only done once
		showMessage(" Connected to :" + connection.getInetAddress().getHostAddress());//converts ip to string
	}

	//get stream to send and recieve data
	private void setupStreams() throws IOException
	{
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush(); //Clears buffer of any left over bytes.
		input = new ObjectInputStream(connection.getInputStream());
		showMessage("\n Streams setup complete.");
	}
	public void showMessage(final String s)
	{
		//System.out.println(s);
		SwingUtilities.invokeLater(
				/// using thread to update text in GUI
				new Runnable()
				{
					public void run()
					{
						chatWindow.append(s); //adds messsage to end of doc and updates the chat window
					}
				}
				
				);
	}
	
	//during the convo 
	public void whileChatting() throws IOException
	{
		String message = " You are connected! \n Type: ''END'' to end connection. \n";
		sendMessage(message);
		ableToType(true);
		do{
			try{
				message = (String) input.readObject();
				showMessage("\n" + message);
			}catch(ClassNotFoundException classExcept)
			{
				showMessage("\n Not sure what is send " +classExcept);
			}
			
		}while(!message.equals(" CLIENT - END"));
		
	}
	
	private void closeAll()
	{
		showMessage("\n Closing connection.. \n");
		ableToType(false);
		try{
			output.close();
			input.close();
			connection.close();	//close socket
		}catch(IOException ioExcept)
		{
			ioExcept.printStackTrace();
		}
	}
	
	// send message sents message to client
	private void sendMessage(String msg)
	{
		try{
			output.writeObject(" SERVER - " + msg);//build in method, will create object and send to oupt stream and send to clinet
			output.flush();
			showMessage("\n SERVER - " + msg);
		}catch(IOException ioExcept)
		{
			chatWindow.append("\n Error: Could not send that message! \n");
		}
	}
	
	//is the user able to type in the textbox or not
	private void ableToType(final boolean able)
	{
		//chatWindow.setEditable(able);
		
		//System.out.println(s);
				SwingUtilities.invokeLater(
						/// using thread to update text in GUI
						new Runnable()
						{
							public void run()
							{
								userText.setEditable(able);
							}
						}
						
						);
	}

}
