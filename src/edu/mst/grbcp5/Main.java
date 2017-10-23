package edu.mst.grbcp5;

public class Main {

  private static final int PORT_NUM = 9090;
  private static final int NUM_CLIENTS = 5;

  public static void main( String[] args ) {

    TCP_Server server;
    TCP_Client[] clients;
    String serverResponse;

    server = new TCP_Server( PORT_NUM );

    clients = new TCP_Client[ NUM_CLIENTS ];
    for ( int i = 0; i < NUM_CLIENTS; i++ ) {
      clients[ i ] = new TCP_Client( "127.0.0.1", PORT_NUM );
    }

    server.start();

    for ( TCP_Client client : clients ) {
      client.start();
    }


    try {

      for( TCP_Client client : clients ) {
        client.join();
        serverResponse = client.getResponse();
        System.out.println( "Server response: " + serverResponse );
      }

    } catch ( InterruptedException e ) {
      e.printStackTrace();
      return;
    }

    server.interrupt();
    try {
      System.out.println( "Waiting for server" );
      server.join();
    } catch ( InterruptedException e ) {
      e.printStackTrace();
    }

    System.out.println( "Process terminating." );
  }

}
