package method;

import models.Admin;
import views.AdminView;
import views.MainTerminal;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import connections.JDBC;




public class AdminFunction{

  
  public void addAdmin() throws IOException, SQLException, ParseException, ClassNotFoundException{
    Scanner io=new Scanner(System.in);
    System.out.println("Please enter your name: ");
    String name= io.nextLine();
    System.out.println("Please enter your national-ID: ");
    String nationalId=io.nextLine();
    System.out.println("Please enter your email: ");
    String email=io.nextLine();
    System.out.println("Please enter your phone number: ");
    String phoneNo=io.nextLine();
    System.out.println("please set a password: ");
    String password=io.nextLine();
    Admin newAdmin=new Admin(name,nationalId,email,password,phoneNo);
    AdminJDBC.addAdminToJDBC(newAdmin);
    System.out.println("Admin added successfully");
    
    System.out.println("Press 0 to exit or any key to continue");
    String cont= io.nextLine();
    if(cont.equals("0")){
      io.close();
      System.exit(0);
    }else{
      adminTerminal();
    }
    
  }

  public void displayAllAdmin() throws IOException, SQLException, ParseException, ClassNotFoundException{
    ArrayList<Admin> adminList= AdminJDBC.readAllFromAdmin();
    if(adminList !=null){
     AdminView.displayAdmin(adminList);
    } else{
      System.out.println("There is not any admin");
    }
    Scanner io= new Scanner(System.in);
    System.out.println("Press 0 to exit or any key to continue");
    String cont= io.nextLine();
    if(cont.equals("0")){
      io.close();
      System.exit(0);
    }else{
      adminTerminal();
    }
  
  }

  public void displayOneAdmin() throws IOException, SQLException, ParseException, ClassNotFoundException{
    ArrayList<Admin> adminList= AdminJDBC.readAllFromAdmin();
    Scanner io=new Scanner(System.in);
    if (adminList!=null){
      System.out.println("Enter id of the admin you want to see: ");
      String id=io.nextLine();
      System.out.println();
      boolean found=false;
      for(Admin admin:adminList){
        if(admin.getNationalId().equals(id)){
          AdminView adminView=new AdminView();
          adminView.displayOneAdmin(admin);
          found=true;
        }
      }
      if(!found){
        System.out.println("This admin doesn't exist");
      }
    }else{
      System.err.println("There is not any admin\n");
    }
    System.out.println("Press 0 to exit or any key to continue");
    String cont= io.nextLine();
    if(cont.equals("0")){
      io.close();
      System.exit(0);
    }else{
      adminTerminal();
    }
  }

  public void removeAdmin() throws IOException, SQLException, ParseException, ClassNotFoundException{
    ArrayList<Admin> adminList= AdminJDBC.readAllFromAdmin();
    Scanner io=new Scanner(System.in);
    if(adminList!=null){
      System.out.println("Enter the national-ID that you want to remove: ");
      String id=io.nextLine();
      boolean deleted=false;
      for(Admin admin:adminList){
        if(admin.getNationalId().equals(id)){
          AdminJDBC.removeAdminById(id);
          deleted=true;
        }
      }
      if(deleted){
        System.out.println("admin Successfully Deleted");
      } else{
        System.out.println("We couldn't find an admin whith such id");
      }

    }else{
      System.out.println("There is not any admin");
    }
    System.out.println("Press 0 to exit or any key to continue");
    String cont= io.nextLine();
    if(cont.equals("0")){
      io.close();
      System.exit(0);
    }else{
      adminTerminal();
    }
  }
  

  public void editAdmin() throws IOException, SQLException, ParseException, ClassNotFoundException{
    ArrayList<Admin> adminList = AdminJDBC.readAllFromAdmin();
    Scanner io=new Scanner(System.in);
    if(adminList!=null){
      System.out.println("Enter the national-ID that you want to edit: ");
      String id=io.nextLine();
      boolean found=false;
      String nationalId=null;
      for (Admin admin : adminList) {
          if( admin.getNationalId().equals(id)){
            found= true;
            nationalId= admin.getNationalId();
          }
      }
      if(found){
        System.out.println("What do you want to change ?\n1-Email\n2-Mobile Number\n3-password");
        int option=io.nextInt();
        String sql=null;
        switch(option){
          case 1:
          System.out.println("Enter the new Email:");
          String newEmail=io.next();
          sql = "UPDATE `admins` SET  email = '"+newEmail+"' WHERE nationalId = '"+nationalId+"'";
          break;

          case 2:
          System.out.println("Enter the new mobile number:");
          String newMobileNumber=io.next();
          sql = "UPDATE `admins` SET phoneNumber = '"+newMobileNumber+"' WHERE nationalId = '"+nationalId+"'";
          break;

          case 3:
          System.out.println("Enter the new password :");
          String newPassword=io.next();
          sql = "UPDATE `admins` SET password = '"+newPassword+"' WHERE nationalId = '"+nationalId+"'";
          break;
        }
        if(sql!=null){
          JDBC.update(sql);
          System.out.println("Admin updated successfully");
          System.out.println();
        }
      }else{
        System.out.println("There is not an admin with such id");
      }

    } else{
      System.out.println("There is not any admin");
    }
    System.out.println("Press 0 to exit or any key to continue");
    String cont= io.nextLine();
    if(cont.equals("0")){
      io.close();
      System.exit(0);
    }else{
      adminTerminal();
    }
    
  }
  
  public void adminTerminal() throws IOException, SQLException, ParseException, ClassNotFoundException{
    Scanner io=new Scanner(System.in);
    System.out.println("Please Choose a number:"+
    "\n1. Display admins."+
    "\n2. Display one admin"+
    "\n3. Add admin."+
    "\n4. Edit admin."+
    "\n5. delete admin."+
    "\n6. go back"
    );
    
    String inputKey="0";
    while (inputKey!="6") {
      inputKey=io.nextLine();
      switch (inputKey) {
        case "1":
        this.displayAllAdmin();
        break;

        case "2":
        this.displayOneAdmin();
        break;

        case "3":
        this.addAdmin();
  
        break;
        case "4":
        this.editAdmin();
  
        break;
        case "5":
        this.removeAdmin();
        break;

        case "6":
        MainTerminal.adminMainTerminal();
        break;
      
        case "0":
        io.close();
        System.exit(0);
        break;
      }
    }
  }

}