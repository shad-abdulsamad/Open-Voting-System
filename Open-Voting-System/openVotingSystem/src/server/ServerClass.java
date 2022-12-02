package server;

import java.io.*;
import java.net.*;

// Server class
class ServerClass {
    private static int totalClients=0;

    public static void main(String[] args)
    {
        ServerSocket server = null;
        try {

            // server is listening on port 1234
            server = new ServerSocket(1234);
            server.setReuseAddress(true);
            System.out.println("The server is running!");

            while (true) {

                totalClients++;

                Socket client = server.accept();

                BufferedReader in=new BufferedReader(new InputStreamReader(client.getInputStream()));


                System.out.println("New client connected!");
                System.out.println("Number of clients: "+totalClients);
                System.out.println(in.readLine());
                // create a new thread object
                ClientHandler clientSock
                        = new ClientHandler(client);


                new Thread(clientSock).start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (server != null) {
                try {
                    server.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // ClientHandler class
    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        // Constructor
        public ClientHandler(Socket socket)
        {
            this.clientSocket = socket;
        }

        public void run()
        {
            PrintWriter out = null;
            BufferedReader in = null;
            try {

                // get the outputstream of client
                out = new PrintWriter(
                        clientSocket.getOutputStream(), true);
                out.println("Seccesfuly connected to the server 1-admin 2-voter");

                // get the inputstream of client
                in = new BufferedReader(
                        new InputStreamReader(
                                clientSocket.getInputStream()));

                String line;
                while ((line = in.readLine()) != null) {

                    if(line.equals("1")) out.println("admin");
                    if(line.equals("2")) out.println("voter");
                }
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
            finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                        totalClients--;
                        System.out.println("A client has exit the server!");
                        clientSocket.close();
                    }
                }
                catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}