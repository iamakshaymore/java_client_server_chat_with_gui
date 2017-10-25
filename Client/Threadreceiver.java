import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class Threadreceiver extends Thread {
	BufferedReader in;
	JTextArea chatscn;
	Socket clientSocket;
	JButton btnSend;
	public Threadreceiver(BufferedReader in, JTextArea chatscn, Socket clientSocket) {
		this.in=in;
		this.chatscn=chatscn;
		this.clientSocket=clientSocket;
	}
	public void run() {
		//if(!btnSend.getModel().isPressed()) {
		String text="";
		while(true) {
			try {
				text=in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!text.equalsIgnoreCase("bye")) {
			chatscn.setText(chatscn.getText()+"\nServer : "+text);
			chatscn.setCaretPosition(chatscn.getDocument().getLength());
			}
			else {
				System.exit(0);
			}

	}
	}
}
	
