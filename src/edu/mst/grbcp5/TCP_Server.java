package edu.mst.grbcp5;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class TCP_Server extends Thread {

  public final static int TIMEOUT_DEFAULT = 5000;

  private final int serverSocketPort;
  private int requestNumber;
  private int timeout;

  public TCP_Server( int portNumber ) {
    this.serverSocketPort = portNumber;
    this.requestNumber = 0;
    this.timeout = TIMEOUT_DEFAULT;
  }

  public int getTimeout() {
    return timeout;
  }

  public void setTimeout( int timeout ) {
    this.timeout = timeout;
  }

  @Override
  public void run() {

    ServerSocket listener;
    Socket socket;
    PrintWriter out;

    try {
      listener = new ServerSocket( this.serverSocketPort );
      listener.setSoTimeout( this.timeout );
    } catch ( IOException e ) {
      e.printStackTrace();
      return;
    }

    try {

      while ( true ) {

        try {
          socket = listener.accept();
        } catch ( SocketTimeoutException e ) {
          if ( Thread.interrupted() ) {
            return;
          } else {
            continue;
          }
        }

        try {

          out = new PrintWriter( socket.getOutputStream(), true );
          out.println( this.requestNumber );
          this.requestNumber++;

        } finally {
          socket.close();
        }

      } /* Main procedure */

    } catch ( IOException e ) {

      e.printStackTrace();

    } finally {

      try {
        listener.close();
      } catch ( IOException e ) {
        e.printStackTrace();
      }

    } /* Try main procedure */

  } /* run */

}
