package server;


import method.VoterJDBC;
import models.Voter;
import views.MainTerminal;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;


// Client class
class VoterClient {

    // driver code
    public static void main(String[] args) throws ClassNotFoundException
    {
        try (Socket socket = new Socket("localhost", 1234)) {

            // writing to server
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            // reading from server
            BufferedReader in
                    = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));

            // object of scanner class
            Scanner sc = new Scanner(System.in);
            String line = "A voter connected!";
            out.println(line);
            out.flush();
            System.out.println("Welcome voter");
            while (!socket.isClosed()) {
                // sending the user input to server
                String id,password;
                System.out.println("Enter your ID: ");
                id=sc.nextLine();
                System.out.println("Enter your Password: ");
                password= sc.nextLine();
                ArrayList<Voter> voterList= VoterJDBC.readAllFromVoter();
                if(voterList!=null){
                    boolean foundVoter=false;
                    for (Voter voter: voterList){
                        if(voter.getNationalID().equals(id) && voter.getPassword().equals(password)){
                            foundVoter=true;
                            System.out.println("Welcome: "+ voter.getFullName());
                            break;
                        }
                    }
                    if(foundVoter){
                        MainTerminal.voterMainTerminal(id);
                    }if(!foundVoter) System.out.println("Incorect password or id!");
                }else {
                    System.out.println("no voter found");
                }
            }

            // closing the scanner object
            sc.close();
        }
        catch (IOException | SQLException | ParseException e) {
            e.printStackTrace();
        }
    }
}
