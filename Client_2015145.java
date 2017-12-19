import java.net.*;
import java.io.*;

public class Client_2015145{

	public static void main(String[] arguments){

        if (arguments.length != 2) {
            System.out.println("Enter ip and portn");
            System.exit(0);
        }

        int portn = Integer.parseInt(arguments[1]);
        String ipAddr = arguments[0];

        try{
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            Socket clientSocket = new Socket(ipAddr,portn);
            String datafrom_server,datato_server;
            BufferedReader inStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter outStream = new PrintWriter(clientSocket.getOutputStream(),true);

            while ((datafrom_server = inStream.readLine())!= null) {
                System.out.println("Recieved from Server : "+datafrom_server);
                if (datafrom_server.equals("exit")){
                   break;
                 }
                if ((datato_server = stdIn.readLine())!= null)	 {
                    outStream.println(datato_server);
                    System.out.println("Client : "+datato_server);
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
