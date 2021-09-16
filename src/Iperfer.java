class Iperfer {
  public static void main(String args[]){
    boolean isClient = false;
    boolean isServer = false;

    String hostname;
    int port;
    int time;

    boolean fail = false;

    try{
      // client
      if(args[0] == "-c") {
        isClient = true;

        // hostname
        if (args[1] == "-h") {
          hostname = args[2];
        }
        else {
          System.out.println("Error: invalid arguments");
          fail = true;
        }

        // port
        if (args[3] == "-p") {
          port = Integer.parseInt(args[4]);

          if(port < 1024 || port > 65535) {
            System.out.println("Error: port number must be in the range 1024 to 65535");
            fail = true;
          }
        }
        else {
          System.out.println("Error: invalid arguments");
          fail = true;
        }

        // time
        if (args[5] == "-t") {
          time = Integer.parseInt(args[6]);
        }
        else {
          System.out.println("Error: invalid arguments");
          fail = true;
        }

      }
      // server
      else if(args[0] == "-s") {
        isServer = true;

        // port
        if (args[1] == "-p") {
          port = Integer.parseInt(args[2]);

          if(port < 1024 || port > 65535) {
            System.out.println("Error: port number must be in the range 1024 to 65535");
            fail = true;
          }
        }
        else {
          System.out.println("Error: invalid arguments");
          fail = true;
        }
      }
    }
    catch(Exception e) {
      System.out.println("Error: invalid arguments");
      fail = true;
    }

    if(fail) {
      return;
    }
    else if(isClient) {
      // TODO run client
    }
    else if(isServer) {
      // TODO run server
    }
  }
}