
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class ChkUdpClient {

	public static void main(String[] args) throws Exception {
		DatagramSocket datagramSocket = new DatagramSocket();
		byte[] byteArray = new byte[1024];
		byte[] responseBytes = new byte[1024];
		byte[] b = new byte[1024];
		DatagramPacket clientdata, serverRes;
		int t=0;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		String inputStr, serverResStr;
		
		do{
			t=0;
			System.out.println("Enter the String which has to be sent : ");
			//read data to be sent
			inputStr = bufferedReader.readLine();
			//quit the session upon sending bye
			if (inputStr.equals("bye"))
			{
				System.out.println("Session ended");
				break;
			}
			//calculate the chechsum of input data
			b = inputStr.getBytes();
			//add all the characters of the data
			for (byte d: b) 
			{
				t=t+d;
			}
			System.out.println("Checksum of the data = "+t);
			//compliment the checksum
			t=t*-1;
			//concatenate the data and ckecksum also a special character,so that we can split
			inputStr = inputStr +"/"+t;

			byteArray = new byte[1024];
			byteArray = inputStr.getBytes();
			//datagrampacket to send data
			clientdata = new DatagramPacket(byteArray,inputStr.length(), InetAddress.getByName(args[0]),Integer.parseInt(args[1]));
			datagramSocket.send(clientdata);
			
			//datagrampacket to recieve server's response
			serverRes = new DatagramPacket(responseBytes, responseBytes.length);
			datagramSocket.receive(serverRes);
			responseBytes = new byte[1024];
			responseBytes = serverRes.getData();
			//convert the response into string and display the same
			serverResStr = new String(responseBytes);
			System.out.println("Response String from Server : " + serverResStr);
			
			//reset the buffers for next interaction
			for (int i=0; i<responseBytes.length; i++)
	      			responseBytes[i] = 0;
	      		for (int i=0; i< byteArray.length; i++)
	      			byteArray[i] = 0;
		}while(true);
	}

}
