package server;



import method.AdminJDBC;
import models.Admin;
import views.MainTerminal;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;


// Client class
class AdminClient {

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
            String line = "An admin connected!";
            out.println(line);
            out.flush();
            System.out.println("Welcome Admin");
            while (!"exit".equalsIgnoreCase(line)) {
                // sending the user input to server
                String id,password;
                System.out.println("Enter your ID: ");
                id=sc.nextLine();
                System.out.println("Enter your Password: ");
                password= sc.nextLine();
                ArrayList<Admin> adminList = AdminJDBC.readAllFromAdmin();
                if(adminList!=null){
                    boolean found=false;
                    for (Admin admin : adminList) {
                        // System.out.println(admin.getNationalId());
                        if( admin.getNationalId().equals(id)&&admin.getPassword().equals(password)){
                            found= true;
                            System.out.println("Welcome: " + admin.getFullname());
                            break;
                        }
                    }
                    if(found){
                        MainTerminal.adminMainTerminal();

                    }else{
                        System.out.println("Incorrect Id or Password!");
                    }
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
