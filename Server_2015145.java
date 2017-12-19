public class Server_2015145{
	public static void main(String[] args){
		if(args.length!=1){
			System.out.println("Enter a single port number");
			System.exit(1);
		}
		multithreadedserver_2015145 server = new multithreadedserver_2015145(Integer.parseInt(args[0]));
		Thread mainthread = new Thread(server);
		mainthread.start();
	}
}