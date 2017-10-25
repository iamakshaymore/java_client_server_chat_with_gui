import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Scrollbar;
import java.awt.ScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class clientWindow extends JFrame {

	private JPanel contentPane;
	private JTextField message;
	private JScrollPane scrollPane;
	/**
	 */
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					clientWindow frame = new clientWindow();
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
	 * @throws UnknownHostException 
	 */
	public clientWindow() throws UnknownHostException, IOException {
		setTitle("Client Chat Window");



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
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 208);
		contentPane.add(scrollPane);
		
		JTextArea chatscn = new JTextArea();
		scrollPane.setViewportView(chatscn);
        chatscn.setEditable(false);

		
		int port = 1232;
	      String host ="localhost";//("vulcan.seidenberg.pace.edu");
	      Socket clientSocket;
			clientSocket = new Socket(host, port);
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		    BufferedReader in = new BufferedReader(
		new InputStreamReader(clientSocket.getInputStream()));
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
	
