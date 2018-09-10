
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ChkUdpServer {
	public static void main(String args[]) throws Exception {
		int sport = Integer.parseInt(args[0]);
		DatagramSocket datagramSocket = new DatagramSocket(sport);
		byte[] byteArray= new byte[1024];
		byte[] responseBytes= new byte[1024];
		DatagramPacket recvclient,response;
		String str, responseStr;
		
		do {
			//create datagrampacket to recieve data from client
			recvclient = new DatagramPacket(byteArray,byteArray.length);
			datagramSocket.receive(recvclient);
			byteArray = recvclient.getData();
			str = new String(byteArray).trim();
			//verifying data
			int c=0;
			//splitting data to extract checksum
			String ar[] = str.split("/");
			//convert string type into bytes and integer value respectively
			byte x[] = ar[0].getBytes();
			int n = Integer.parseInt(ar[1]);
			//adding ascii values of all the characters in recieved data
			for(byte a:x)
			{
				c = c+a;
			}
			//adding the checksum value been sent
			c=c+n;
			//check if calcualted result is 0,and print messages
			if(c==0)
	 		System.out.println("checksum value = "+c+" ,so No error");
			else
	  		System.out.println("data is corrupted,invalid checksum");

			System.out.println("Received Data from Client is : " + ar[0]);
			//concatenate the recieved data to itself,to echoe the result back to client
			responseStr = ar[0] + ""+ ar[0];
			responseBytes = responseStr.getBytes();
			//datagram packet to send response
			response = new DatagramPacket(responseBytes,responseStr.length(), recvclient.getAddress(),recvclient.getPort());
			datagramSocket.send(response);
			//clear the buffers for next operation
			 for(int i=0; i< byteArray.length; i++)
	   	 		byteArray[i]  = 0;
	   		for (int i=0; i< responseBytes.length; i++)
	      	 		responseBytes[i] = 0;
		}while(true);
		
	}

}
