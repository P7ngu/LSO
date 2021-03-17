package com.example.myapplication;
    import android.util.Log;

    import java.io.BufferedReader;
    import java.io.BufferedWriter;
    import java.io.DataOutputStream;
    import java.io.IOException;
    import java.io.InputStream;
    import java.io.InputStreamReader;
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
               // Socket sock = new Socket("127.0.0.1", 10011);
                Socket sock = new Socket("192.168.1.127", 10011);
                // reading from keyboard (keyRead object)
                BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
                // sending to client (pwrite object)
                OutputStream ostream = sock.getOutputStream();
                PrintWriter pwrite = new PrintWriter(ostream, true);

                // receiving from server ( receiveRead  object)
                InputStream istream = sock.getInputStream();
                BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream), 1024);

                System.out.println("Send a message: type and press Enter key");

                String receiveMessage, sendMessage;
                while(true)
                {
                    //sendMessage = keyRead.readLine();  // keyboard reading
                    //pwrite.println(sendMessage);
                    pwrite.println("Testtttt\n");// sending to server
                    pwrite.flush();                    // flush the data


                    receiveMessage = receiveRead.readLine();
                    if((receiveMessage) != "0") //receive from server
                    {
                        System.out.println(receiveMessage); // displaying at DOS prompt
                    }
                    //removeNonAscii(receiveMessage);
                    //replaceUnreadable(receiveMessage);
                    receiveMessage = receiveMessage.substring(0,0);


                }

            } catch (UnknownHostException unknownHostException) {
                unknownHostException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }

        private static String removeNonAscii(String s){
            StringBuffer sb = new StringBuffer();
            for(int i=0; i<s.length(); ++i){
                if(s.charAt(i) < 128){
                    sb.append(s.charAt(i));
                }
            }
            return sb.toString();
        }
        private static String replaceUnreadable(String s){
            String clean = s.replaceAll("\\P{Print}", "");
            return clean;
        }
    }
