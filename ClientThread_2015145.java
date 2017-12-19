import java.util.regex.Pattern;
import java.lang.*;
import java.io.*;
import java.net.*;

public class ClientThread_2015145 implements Runnable{

	private Socket clientSocket = null;
	public int clientID = 0;
	private boolean clientState = false;
	public PrintWriter output;
	public BufferedReader input;

	public ClientThread_2015145(Socket clientSocket1,int count){
		this.clientSocket = clientSocket1;
		this.clientState = true;
		this.clientID = count;
		System.out.println("Client "+this.clientID+" connected");
	}

	public void run(){
		try{
			output = new PrintWriter(clientSocket.getOutputStream(),true);
			input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		}
		catch(IOException e){
			System.out.println(e);
		}
		String inputToServer,outputFromServer;
		try{
			output.println("Welcome To Server");
			while((inputToServer=input.readLine())!=null){
				System.out.println("Recieved from :"+clientID+" "+inputToServer);
				outputFromServer = reverseWord(inputToServer);
				output.println(outputFromServer);
				if(inputToServer.equals("exit")){
					break;
				}
			}
		}catch(IOException e){
				System.out.println(e);
			}
		System.out.println("Client "+clientID+" disconnected");
	}

	public synchronized String reverseWord(String inputClient){
        String[] temp = inputClient.split(" ");
        String result = "";
        for (int i = 0; i < temp.length; i++) {
            if (i == temp.length - 1)
                result = temp[i] + result;
            else
                result = " " + temp[i] + result;
        }
        return result;
	}
}