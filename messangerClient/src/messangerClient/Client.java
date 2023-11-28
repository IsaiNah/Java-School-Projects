package messangerClient;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Client extends JFrame{

	private JTextField userText; // msg
	private JTextArea chatWindow;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String message = "";
	private String serverIP;
	private Socket connection;
	
	public Client(String host)
	{
		super("MSGER client");
		serverIP = host;
		userText = new JTextField();
		userText.setEditable(false);
		userText.addActionListener(
					new ActionListener()
					{
						public void actionPerformed(ActionEvent event)
						{
							sendMessage(event.getActionCommand());
							userText.setText("");
						}
					}
				);
			add(userText, BorderLayout.NORTH);
			chatWindow = new JTextArea();
			add(new JScrollPane(chatWindow),BorderLayout.CENTER);
			setSize(300, 150);
			setVisible(true);
	}
	
	// connect to server
	public void startRunning()
	{
		try{
			connectToServer();
			setupStreams();
			whileChatting();
		}catch(EOFException eofExcep)
		{
			showMessage("\n Client ended connection");
		}catch(IOException ioExcept)
		{
			ioExcept.printStackTrace();
		}finally{
			closeAll();
		}
	}
	
	//connect to server
	private void connectToServer() throws IOException
	{
		showMessage("\n Attempting to connect...\n");
		connection = new Socket(InetAddress.getByName(serverIP),6789);
		showMessage("Connected to :" + connection.getInetAddress().getHostName());
	}
	
	// set up streams to send and recieve
	private void setupStreams() throws IOException
	{
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());	
		showMessage("\n You're streamed and connected." );
	}
	
	private void whileChatting() throws IOException
	{
		ableToType(true);
		do{
			try{
				message = (String) input.readObject();
				showMessage("\n"+ message);
			}catch(ClassNotFoundException cnfExcept)
			{
				showMessage("\n I dont know that object type.");
			}
			
		}while(!message.equals(" SERVER - END"));
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
	
	//send message to server
	private void sendMessage(String msg)
	{
		try{
			output.writeObject(" CLIENT - " + msg);
			output.flush();
			showMessage("\n CLIENT - " + msg);
		}catch(IOException ioExcep)
		{
			chatWindow.append("\n Something messed up!");
		}
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
