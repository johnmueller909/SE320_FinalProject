/*
 Code By John-Patrick Mueller
 12/14/21
 SE 320
 */

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.io.OutputStreamWriter;
import java.awt.event.WindowEvent;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import javax.swing.JButton;
import java.net.InetAddress;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionListener;

//Define Client class
public class Client {
	
	public JFrame mainFrame;
	static TextArea text = null;
	
//Make client object
Client() {
	mainFrame = new JFrame("Client");
	mainFrame.setSize(600, 600);
	mainFrame.setLayout(new GridLayout(3, 1));
	mainFrame.addWindowListener(new WindowAdapter() {
		
		//Used in case window is closed
		public void windowClosing(WindowEvent windowEvent) {
			System.exit(0);
		}
	});
	
	//Height Label
	JLabel heightLabel = new JLabel("Height (m): ");
	heightLabel.setBounds(20, 60, 200, 20);
	JTextField heightText = new JTextField(20);
	heightText.setBounds(130, 60, 200, 20);

	//weight label
	JLabel weightLabel = new JLabel("Weight (kg): ");
	weightLabel.setBounds(20, 20, 200, 20);
	JTextField weightText = new JTextField(20);
	weightText.setBounds(130, 20, 200, 20);
    
	//Submit Button
	JButton submitButton = new JButton("Submit");
	submitButton.setBounds(200, 100, 100, 20);
	submitButton.addActionListener(new ActionListener() {
		
		//Used to pass parameters in
		public void actionPerformed(ActionEvent e) {
			connect(weightText.getText(), heightText.getText()); 
		}
	});
	
	
	text = new TextArea();
	text.setBounds(20, 130, 450, 300);
	
	mainFrame.setLayout(null);
	
	//Add labels and buttons
	mainFrame.add(submitButton);
	mainFrame.add(text);
	mainFrame.add(weightLabel);
	mainFrame.add(weightText);
	mainFrame.add(heightLabel);
	mainFrame.add(heightText);
	
	mainFrame.setLocationRelativeTo(null);
	mainFrame.setVisible(true);
}

public static void main(String args[]) {
	new Client();
}

public static void connect(String weight, String height) {
	try {
		
		Socket socket = null;
		String host = "localhost";
		InetAddress address = InetAddress.getByName(host);
		
		//sets up socket
		socket = new Socket(address, 2525);

		text.append("\nWeight: " + weight);
		text.append("\nHeight: " + height);
		
		
        //Sends message to server
		OutputStream OS = socket.getOutputStream();
		OutputStreamWriter OSW = new OutputStreamWriter(OS);
		
		BufferedWriter BW = new BufferedWriter(OSW);
		String WeightHeight = weight + " " + height + "\n";
		BW.write(WeightHeight);
		//flush data
		BW.flush();

        //Get return data from server
		InputStream IS = socket.getInputStream();
		InputStreamReader ISR = new InputStreamReader(IS);
		
		BufferedReader BR = new BufferedReader(ISR);
		String message = BR.readLine();
		
		text.append("\n" + message);
		
		//used to catch certain exceptions
	} catch (UnknownHostException ex) {
		Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
	} catch (Exception ex) {
		Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
	}

}
}