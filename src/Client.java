import java.net.*;
import java.io.*;
import java.time.Instant;

public class Client{
    private Socket socket            = null;
    // private DataInputStream  input   = null;
    private DataOutputStream out     = null;
    public  Client(String address, int port,int time){
        try{
            int bytesSent = 0;
            // System.out.println(x);
            socket = new Socket(address, port);
            out    = new DataOutputStream(socket.getOutputStream());
            long unixTimestamp = Instant.now().getEpochSecond();
            long time_limit = time+unixTimestamp;
            while(Instant.now().getEpochSecond()<time_limit){
                for(int i=0;i<1000;i++){
                    out.writeByte(0);
                    bytesSent=bytesSent+1;
                }
                out.flush();
            }
            // System.out.print("the rate is");
            System.out.println("rate=" + (8 * bytesSent / (time)) + " Mbps");
            System.out.println("Sent=" + bytesSent / 1000 + " KB");
            out.close();
            socket.close();

        }
        catch(UnknownHostException u){
            System.out.print(u);
        } 
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

}