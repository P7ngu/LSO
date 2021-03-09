package com.example.myapplication;
    import android.util.Log;

    import java.io.BufferedWriter;
    import java.io.DataOutputStream;
    import java.io.IOException;
    import java.io.InputStream;
    import java.io.ObjectInputStream;
    import java.io.OutputStream;
    import java.io.OutputStreamWriter;
    import java.io.PrintWriter;
    import java.net.*;

//Open a socket.
//Open an input stream and output stream to the socket.
//Read from and write to the stream according to the server's protocol.
//Close the streams.
//Close the socket.

    public class Connection {
        public Connection() {
            try {
                Socket socket = null;
                //InetAddress inetAddress = InetAddress.getByName("40.68.217.34");
                InetAddress inetAddress = InetAddress.getByName("192.168.1.129");
                int port = 18000;

                socket= new Socket(inetAddress, port);
                Log.d("SocketDebug", "1");
                String message = "Hello";

                //PrintWriter out = new PrintWriter(new BufferedWriter((new OutputStreamWriter(socket.getOutputStream()))), true);
                //out.println(message);
                OutputStream outputStream = socket.getOutputStream();
                // create a data output stream from the output stream so we can send data through it
               DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

                // write the message we want to send
                byte[] arr = message.getBytes();
                //dataOutputStream.writeBytes(arr);
                dataOutputStream.write(arr);
               // dataOutputStream.flush(); // send the message


                //read the server response message
                InputStream ois = socket.getInputStream();
                int message1 = ois.read();
                Log.d("SocketDebug", message1+" 4");



                System.out.println("Inet address: " + socket.getInetAddress());
                System.out.println("Port number: " + socket.getLocalPort());
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("SocketDebug", "errore");
            }

        }
    }
