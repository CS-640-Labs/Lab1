import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStreamReader;
import java.io.BufferedReader;


public class IperferServer {
  int port;
  int bytesReceived = 0;

  public IperferServer(int port) {
    this.port = port;
    this.startSocket();
  }

  private void startSocket() {
    try (
        ServerSocket server = new ServerSocket(this.port);
        Socket socket = server.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    ) {
      long unixTimestamp = System.currentTimeMillis();

      while (in.read() != -1) {
        bytesReceived += 1;
      }
      double var = ((8 * bytesReceived) / (((System.currentTimeMillis() - unixTimestamp)/1000.0)*1000000.0));
      System.out.printf("rate= %.3f Mbps\n",var);
      System.out.println("received=" + bytesReceived / 1000 + " KB");

    }

    catch (Exception e) {
      System.out.println("excpetion: " + e);
    }
  }
}
