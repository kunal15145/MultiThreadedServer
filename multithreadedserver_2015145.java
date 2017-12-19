import java.net.*;
import java.io.*;

public class multithreadedserver_2015145 implements Runnable{
	
	// default value for server

	public int count = 0 ;
	private int port_current;
	private ServerSocket serverSocket = null;
	private boolean serverStatus = false;
	private Thread runningThread = null;

	// contructor

	public multithreadedserver_2015145(int port){
		this.port_current = port;
	}

	// run main loop

	public void run(){
		synchronized(this){
			this.runningThread = Thread.currentThread();
		}
		startServer();
		while(!(this.serverStatus))
		{
			Socket clientSocket = null;
			try{
				clientSocket = this.serverSocket.accept();
			}catch(IOException e){
				if(this.serverStatus){
					System.out.println("Server Stopped");
					return;
				}
			}
			count+=1;
			new Thread(new ClientThread_2015145(clientSocket,count)).start();
		}
		System.out.println("Server Stopped Working");
	}
	
	private void startServer(){
		try{
			this.serverSocket = new ServerSocket(this.port_current);
		}catch (IOException e) {
			throw new RuntimeException("Port "+this.port_current+" cannot be opened",e);
		}
	}

	private void stopServer(){
		this.serverStatus = true ;
	}
}