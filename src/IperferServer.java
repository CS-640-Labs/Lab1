import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;


public class IperferServer {
  int port;
  int bytesReceived = 0;

  public IperferServer(int port) {
    this.port = port;
    this.startSocket();
  }

  public static void main(String args[]) {

  }

  private void startSocket() {
    try (
        ServerSocket server = new ServerSocket(this.port);
        Socket socket = server.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    ) {
      String line;

      while ((line = in.readLine()) != null) {
        outputLine = kkp.processInput(inputLine);
        out.println(outputLine);
      }
    }
    catch (Exception e) {
      System.out.println(e);
    }
  }
}
