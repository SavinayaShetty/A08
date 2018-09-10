# A08
CHECKSUM

TASKS TO BE PERFORMED:-

1)The program should be able to Send data to the server and server should response with the same data twice.
    example: if client sends ABCD, sever response with ABCDABCD.
    
2)Calculate the checksum of the original data and send it to server.At server end verify the message using checksum.

3)modify the original data such that,it gives the same checksum value.


PROGRAM DESCRIPTION :-

Client end: Take the address and port through the command line.Read the input data and calculate the checksum for the same.Bind the 
             input data and checksum and send through the datagram packet.
          
Server end: Recieve the data sent by client through the datagram packet.Split the Recieved data to extract the data string and the 
            checksum.add respective ASCII value of all characters and the checksum value.If the result is "zero" the recieved data 
            data is not corrupted.Dispaly the messages as per the result and send the data to client by concatinating the same data
            twice.
            
            
Challenges faced:-

            Understanding the problem.
            
            Deciding a method for checksum calculation and how to send checksum.
            
            There are many possible messages which gives the same checksum value as original data,so we couldn't find out how to 
            modify the message in a certain way so that it gives same checksum value.

MEMBERS OF THE TEAM:-

            1.Savinaya shetty 1KS16CS086
            2.Shreelekha R N  1KS16CS093
            3.Sheela          1KS16CS091
