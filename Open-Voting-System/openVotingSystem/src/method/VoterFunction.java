package method;

import models.Voter;
import views.MainTerminal;
import views.VoterView;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import connections.JDBC;


public class VoterFunction{

    public void addVoter() throws IOException, ParseException, SQLException, ClassNotFoundException {
        Scanner io = new Scanner(System.in);
        System.out.println("Enter the national id:");
        String id = io.nextLine();
        System.out.println("Enter Voter Name:");
        String name = io.nextLine();
        System.out.println("Enter the email:");
        String email = io.nextLine();
        System.out.println("Enter the mobile number:");
        String mobileNumber = io.nextLine();
        System.out.println("Enter the password:");
        String password = io.nextLine();
        System.out.println("Enter the address:");
        String address = io.nextLine();
        System.out.println("Enter the voter's age:");
        String age = io.nextLine();
        System.out.println("Enter the zone:");
        String zone = io.nextLine();
        Voter newVoter = new Voter(id,name,email,mobileNumber,password,age, address,zone); 
        VoterJDBC.addVoterToJDBC(newVoter);
        System.out.println("Voter added successfully");

        System.out.println("Press 0 to exit or any key to continue");
         String cont= io.nextLine();
        if(cont.equals("0")){
        System.exit(0);
        }else{
        voterTerminal();
        }
    }

    public void displayAllVoter() throws IOException, ParseException, SQLException, ClassNotFoundException{
        ArrayList<Voter> voterList= VoterJDBC.readAllFromVoter();
        if(voterList !=null){
            VoterView.displayVoters(voterList);
        } else{
          System.out.println("There is not any Voter");
        }
        Scanner io= new Scanner(System.in);
        System.out.println("Press 0 to exit or any key to continue");
        String cont= io.nextLine();
        if(cont.equals("0")){
          System.exit(0);
        }else{
          voterTerminal();
        }
      
      }

    public void displayOneVoter() throws IOException, ParseException, SQLException, ClassNotFoundException{
        ArrayList<Voter> voterList= VoterJDBC.readAllFromVoter();
        Scanner io=new Scanner(System.in);
        if (voterList!=null){
          System.out.println("Enter id of the Voter you want to see: ");
          String id=io.nextLine();
          
          boolean found=false;
          System.out.println();
          for(Voter voter:voterList){
            if(voter.getNationalID().equals(id)){
              found=true;
              VoterView voterView=new VoterView();
              voterView.displayOneVoter(voter);
            }
          }
          if(!found){
            System.out.println("This Voter doesn't exist");
          }
        }else{
          System.err.println("There is not any Voter\n");
        }
        System.out.println("Press 0 to exit or any key to continue");
        String cont= io.nextLine();
        if(cont.equals("0")){
          System.exit(0);
        }else{
          voterTerminal();
        }
      }  

      public void editVoter() throws IOException, ParseException, SQLException, ClassNotFoundException{
        ArrayList<Voter> voterList = VoterJDBC.readAllFromVoter();
        Scanner io=new Scanner(System.in);
        if(voterList!=null){
          System.out.println("Enter the national-ID that you want to edit: ");
          String id=io.nextLine();
          boolean found=false;
          String nationalId= null;
          for (Voter voter : voterList) {
              if( voter.getNationalID().equals(id)){
                found= true;
                nationalId= voter.getNationalID();
              }
          }
          if(found){
            System.out.println("What do you want to change ?"+
            "\n1-Name"+
            "\n2-age"+
            "\n3-Zone"+
            "\n4-Address"+
            "\n5-Email"+
            "\n6-Mobile Number"+
            "\n7-password"
            );
            String option=io.nextLine();
            String sql = null;
            switch(option){
              case "1":
              System.out.println("Enter the new Name:");
              String newName=io.nextLine();
              sql="UPDATE voter SET name='"+newName+"' WHERE nationalID='"+nationalId+"'";
              break;
    
              case "2":
              System.out.println("Enter the new age:");
              String newAge=io.next();
              sql="UPDATE voter SET age='"+newAge+"' WHERE nationalID='"+nationalId+"'";
              break;
    
              case "3":
              System.out.println("Enter the new zone :");
              String newZone=io.nextLine();
              sql="UPDATE voter SET zone='"+newZone+"' WHERE nationalID='"+nationalId+"'";
              break;

              case "4":
              System.out.println("Enter the new address :");
              String newAddress=io.nextLine();
              sql="UPDATE voter SET address='"+newAddress+"' WHERE nationalID='"+nationalId+"'";
              break;

              case "5":
              System.out.println("Enter the new Email :");
              String newEmail=io.nextLine();
              sql="UPDATE voter SET email='"+newEmail+"' WHERE nationalID='"+nationalId+"'";
              break;

              case "6":
              System.out.println("Enter the new Mobile Number :");
              String newMobileNumber=io.nextLine();
              sql="UPDATE voter SET phoneNumber='"+newMobileNumber+"' WHERE nationalID='"+nationalId+"'";
              break;

              case "7":
              System.out.println("Enter the new password :");
              String newPassord=io.nextLine();
              sql="UPDATE voter SET password='"+newPassord+"' WHERE nationalID='"+nationalId+"'";
              break;
            }
            System.out.println("Voter Successfully updated");
            JDBC.insert(sql);
          }else{
            System.out.println("There is not a Voter with such id");
          }
    
        } else{
          System.out.println("There is not any Voter");
        }
        System.out.println("Press 0 to exit or any key to continue");
        String cont= io.nextLine();
        if(cont.equals("0")){
          System.exit(0);
        }else{
          voterTerminal();
        }
        
      }


    public void removeVoter() throws IOException, ParseException, SQLException, ClassNotFoundException{
        ArrayList<Voter> voterList= VoterJDBC.readAllFromVoter();
        Scanner io=new Scanner(System.in);
        if(voterList!=null){
          System.out.println("Enter the national-ID that you want to remove: ");
          String id=io.nextLine();
          boolean found=false;
          for (Voter voter : voterList) {
              if( voter.getNationalID().equals(id)){
                VoterJDBC.removeVoterById(id);
                found= true;
              }
          }
          if(found){
            System.out.println("Voter Successfully Deleted");
          } else{
            System.out.println("We couldn't find a Voter whith such id");
          }
    
        }else{
          System.out.println("There is not any Voter");
        }
        System.out.println("Press 0 to exit or any key to continue");
        String cont= io.nextLine();
        if(cont.equals("0")){
          System.exit(0);
        }else{
          voterTerminal();
        }
      }


public void voterTerminal() throws IOException, ParseException, SQLException, ClassNotFoundException{
    Scanner io=new Scanner(System.in);

    String inputKey="Start";
    while (inputKey!="0") {
        System.out.println("Please Choose a number:"+
        "\n1. Display voters."+
        "\n2. Display one voter"+
        "\n3. Add voter."+
        "\n4. Edit voter."+
        "\n5. delete voter."+
        "\n6. go back"
        );
      inputKey=io.nextLine();
      switch (inputKey) {
        case "1":
        this.displayAllVoter();
        break;

        case "2":
        this.displayOneVoter();
        break;

        case "3":
        this.addVoter();

        break;
        case "4":
        this.editVoter();

        break;
        case "5":
        this.removeVoter();
        break;

        case "6":
        MainTerminal.adminMainTerminal();
        break;

        case "0":
        System.exit(0);
        break;
      }
    }
  }

}