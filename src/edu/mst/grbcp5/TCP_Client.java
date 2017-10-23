package edu.mst.grbcp5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCP_Client extends Thread {

  private String serverAddress;
  private int portNumber;
  private String response;

  public TCP_Client() {
    this( null, -1 );
  }

  public TCP_Client( String serverAddress, int portNumber ) {
    this.serverAddress = serverAddress;
    this.portNumber = portNumber;
    this.response = null;
  }

  public String getServerAddress() {
    return serverAddress;
  }

  public void setServerAddress( String serverAddress ) {
    this.serverAddress = serverAddress;
  }

  public int getPortNumber() {
    return portNumber;
  }

  public void setPortNumber( int portNumber ) {
    this.portNumber = portNumber;
  }

  public String getResponse() {
    return response;
  }

  @Override
  public void run() {

    if( this.serverAddress == null ) {
      System.out.println( "No server address specified." );
    }

    if( this.portNumber == -1 ) {
      System.out.println( "No port number specified" );
    }

    /* Local variables */
    Socket socket;
    BufferedReader input;

    try {

      socket = new Socket( this.serverAddress, this.portNumber );
      input = new BufferedReader(
        new InputStreamReader(
          socket.getInputStream()
        )
      );
      this.response = input.readLine();


    } catch ( IOException e ) {
      e.printStackTrace();
    }

  } /* run */

}
