/*
 Code By John-Patrick Mueller
 12/14/21
 SE 320
 */

//Required imports
import java.awt.GridLayout;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.logging.Level;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import javax.swing.JFrame;
import java.awt.event.WindowEvent;
import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.logging.Logger;
import java.awt.event.WindowAdapter;

//Defining what server is
public class Server {
	
	public JFrame mainFrame;
	static TextArea text = null;

//Making a server object to use
Server() {
	
	mainFrame = new JFrame("BMIServer");
	mainFrame.setLayout(new GridLayout(3, 1));
	mainFrame.setSize(500, 400);
	mainFrame.addWindowListener(new WindowAdapter() {
		
		//Used if the window is closed
		public void windowClosing(WindowEvent windowEvent) {
			System.exit(0);
		}
	});
	
	text = new TextArea();
	text.setBounds(20, 20, 450, 300);
	
	mainFrame.setLayout(null);
	mainFrame.add(text);
	mainFrame.setLocationRelativeTo(null);
	mainFrame.setVisible(true);
}

public static void main(String args[]) {
	Socket socket = null;
	
	try {
		new Server();
		ServerSocket serverSocket = new ServerSocket(2525);
		
		//Tells when server was started
		text.append("\nServer Started at " + new Date());
		
		//While loop to ensure server runs forever
		while (true) 
		{
			socket = serverSocket.accept();
			text.append("\nConnection to the client at: " + new Date());
			
			InputStream IS = socket.getInputStream();
			InputStreamReader ISR = new InputStreamReader(IS);
			
			BufferedReader BR = new BufferedReader(ISR);
			String WeightHeight = BR.readLine();
			
			//This is to get info from client
			Double weight = Double.parseDouble(WeightHeight.substring(0, WeightHeight.indexOf(" ")));
			Double height = Double.parseDouble(WeightHeight.substring(WeightHeight.indexOf(" ") + 1, WeightHeight.length()));
			text.append("\nWeight: " + weight);
			text.append("\nHeight: " + height);

			//This is to calculate BMI
			
			Double BMI = (weight / (height * height));
			
			String clientInfo = "";
			
			//If you are obese...
			if (BMI > 30) {
				clientInfo = "You are currently obese";
			}
			
			//If you are overweight
			if (BMI > 25 && BMI < 29.9) {
				clientInfo = "You are currently overweight";
			}
			
			//If you are in a normal weight range..
			if (BMI > 18.5 && BMI < 24.9) {
				clientInfo = "You are currently in a normal BMI range";
			}
			
			//if you are underweight...
			if (BMI < 18.5) {
				clientInfo = "You are currently underweight";
			}
			
			//this is used to send info back to client
			
			OutputStream OS = socket.getOutputStream();
			
			OutputStreamWriter OSW = new OutputStreamWriter(OS);
			
			BufferedWriter BW = new BufferedWriter(OSW);
			
			BW.write("The clients BMI is " + BMI + ". " + clientInfo + "\n");
			text.append("\nThe clients BMI is " + BMI + ". " + clientInfo);
			
			//flush data
			BW.flush();
		}
	 //Used to catch exception
	} catch (Exception ex) {
		Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
	}
}
}