import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.time.Instant;


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
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    ) {
      String line;
      long unixTimestamp = Instant.now().getEpochSecond();

      while ((line = in.readLine()) != null) {
        bytesReceived += line.getBytes().length;
      }

      System.out.println("rate=" + (8 * bytesReceived / (Instant.now().getEpochSecond() - unixTimestamp)*1000) + " Mbps");
      System.out.println("received=" + bytesReceived / 1000 + " KB");

    }

    catch (Exception e) {
      System.out.println("excpetion: " + e);
    }
  }
}
