package com.example.myapplication;
    import android.content.Context;
    import android.os.AsyncTask;
    import android.os.Handler;
    import android.os.Looper;
    import android.util.Log;
    import android.widget.TextView;

    import org.w3c.dom.Text;

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
    import java.nio.Buffer;

//Open a socket.
//Open an input stream and output stream to the socket.
//Read from and write to the stream according to the server's protocol.
//Close the streams.
//Close the socket.

    public class Connection extends AsyncTask {
        static Context myContext;
        static TextView test;
        Socket sock;
        BufferedReader keyRead;
        OutputStream ostream;
       static PrintWriter pwrite;

        public static Context getMyContext() {
            return myContext;
        }

        public static void setMyContext(Context myContext) {
            Connection.myContext = myContext;
        }

        public static TextView getTest() {
            return test;
        }

        public static void setTest(TextView test) {
            Connection.test = test;
        }

        public Socket getSock() {
            return sock;
        }

        public void setSock(Socket sock) {
            this.sock = sock;
        }

        public BufferedReader getKeyRead() {
            return keyRead;
        }

        public void setKeyRead(BufferedReader keyRead) {
            this.keyRead = keyRead;
        }

        public OutputStream getOstream() {
            return ostream;
        }

        public void setOstream(OutputStream ostream) {
            this.ostream = ostream;
        }

        public static PrintWriter getPwrite() {
            return pwrite;
        }

        public void setPwrite(PrintWriter pwrite) {
            this.pwrite = pwrite;
        }

        public InputStream getIstream() {
            return istream;
        }

        public void setIstream(InputStream istream) {
            this.istream = istream;
        }

        public static BufferedReader getReceiveRead() {
            return receiveRead;
        }

        public void setReceiveRead(BufferedReader receiveRead) {
            this.receiveRead = receiveRead;
        }

        InputStream istream;
        static BufferedReader receiveRead;

        public Connection(TextView tv) {
            test = tv;


        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                Log.d("TEST", "inizio thread");
                //Socket sock = new Socket("127.0.0.1", 10011);
                //Socket sock = new Socket("192.168.1.127", 10011);

                //Throws ConnectException if the network is off or the server is unreachable
                sock = new Socket();
                //Throws SocketTimeoutException after 1s if server is unreachable
                sock.connect(new InetSocketAddress("40.68.217.34", 18000), 1000);
                //mMessageListener.onConnectionEstablished();

                Log.d("TEST", "Socket creato");
                // reading from keyboard (keyRead object)
                keyRead = new BufferedReader(new InputStreamReader(System.in));
                // sending to client (pwrite object)
                ostream = sock.getOutputStream();
                pwrite = new PrintWriter(ostream, true);

                // receiving from server ( receiveRead  object)
                istream = sock.getInputStream();
                receiveRead = new BufferedReader(new InputStreamReader(istream), 1024);

                Log.d("TEST", "Send a message: type and press Enter key");

                String receiveMessage, sendMessage;
                String messages;
                int i = 0;

                while (true) {
                    receiveMessage = null;
                    //sendMessage = keyRead.readLine();  // keyboard reading
                    //pwrite.println(sendMessage);
                    //pwrite.println("login####nome12345#test26789");// sending to server
                    //pwrite.flush();                    // flush the data

                    Log.d("TEST", "pre readline");
                    //messages = receiveRead.readLine();

                    Log.d("TEST", "dopo readline \n");
                    //if (messages != "0") //receive from server
                    {
                        //messages = removeNonAscii(messages);
                        //messages = replaceUnreadable(messages);
                        //messages = messages.replaceAll("�", "");

//                        Log.d("TEST", messages + "\n"); // displaying at DOS prompt
                        //                      //MainActivity.setText_Testing(messages[i+1]);
                        //                    final String mx=messages;

                        //                  new Handler(Looper.getMainLooper()).post(new Runnable(){
                        //                    @Override
                        //                  public void run() {
                        //                    test.setText(mx.toString());
                        //              }
                        //        });

                        // receiveMessage = receiveMessage.substring(0,0);
                        //      Log.d("TEST", "fine thread \n");

                        //}
                    }
                    // } catch (UnknownHostException unknownHostException) {
                    //   unknownHostException.printStackTrace();
                    // Log.d("TEST ERROR", "UNKNOWN HOST");
                    //} catch (IOException ioException) {
                    //  ioException.printStackTrace();
                    //Log.d("TEST ERROR", "IO EXCEPTION");
                    //}
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
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

        private void sendMessageToServer(String messaggio){
            pwrite.println("register#prova#test");// sending to server
            pwrite.flush();                    // flush the data
            String result=null;
            try {
                while ((result = receiveMessageFromServer()) == null) ;
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        private String receiveMessageFromServer() throws IOException {
            String messages = receiveRead.readLine();
            if (messages != "0") //receive from server
            {
                messages = removeNonAscii(messages);
                messages = replaceUnreadable(messages);
                messages = messages.replaceAll("�", "");
                Log.d("TEST", messages + "\n"); // displaying at DOS prompt
                final String mx=messages;
                new Handler(Looper.getMainLooper()).post(new Runnable(){
                    @Override
                    public void run() {
                        test.setText(mx.toString());
                    }
                });
                Log.d("TEST", "fine metodo invocato \n");
            }
            Client.gesticiOperazione(messages);
            return null;
        }
    }
