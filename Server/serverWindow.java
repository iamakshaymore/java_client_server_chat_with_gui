import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class serverWindow extends JFrame {

	private JPanel contentPane;
	private JTextField message;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					serverWindow frame = new serverWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public serverWindow() throws IOException {
		setTitle("Server Chat Window");		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		message = new JTextField();

		message.setBounds(10, 230, 414, 20);
		contentPane.add(message);
		message.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 208);
		contentPane.add(scrollPane);
		
		JTextArea chatscn = new JTextArea();


		scrollPane.setViewportView(chatscn);
        chatscn.setEditable(false);
		int port = 1232;
	      ServerSocket serverSocket;
			serverSocket = new ServerSocket(port);
			Socket clientSocket = serverSocket.accept();
		      PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		      BufferedReader in = new BufferedReader(
		new InputStreamReader(clientSocket.getInputStream()));
			   out.println("Hi You are now connected!");
			   chatscn.setText("\nYou : Hi You are now connected!");


		      
		Thread receiver=new Threadreceiver(in,chatscn,clientSocket);
		receiver.start();
	message.addKeyListener(new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				String cs=chatscn.getText();
				String m=message.getText();
				if(!m.equalsIgnoreCase("bye")) {
					if(!m.equalsIgnoreCase("")) {
			    out.println(m);
				cs=cs+"\nYou : "+m;
				chatscn.setText(cs);
				message.setText("");
					}
				}
				else
				{
				    out.println(m);
					System.exit(0);
				}

			}
		}
	});
	}
}
	
