import java.net.*;
import java.io.*;
import java.time.Instant;
import java.lang.Math;

public class Client{
    private Socket socket            = null;
    private DataOutputStream out     = null;
    public  Client(String address, int port,int time){
        try{
            int bytesSent = 0;
            long time_now=0;
            socket = new Socket(address, port);
            out    = new DataOutputStream(socket.getOutputStream());
            long unixTimestamp = Instant.now().getEpochSecond();
            long time_limit = time+unixTimestamp;
            while(Instant.now().getEpochSecond()<time_limit){
                if(time_now==0){
                time_now=Instant.now().getEpochSecond();}
                for(int i=0;i<1000;i++){
                    out.writeByte(0);
                    bytesSent=bytesSent+1;
                }
                out.flush();
            }

            double var = ((8 * bytesSent) / (time*1000000.0));
            System.out.printf("rate= %.3f Mbps\n",var);
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