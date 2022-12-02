package method;

import models.Candidate;
import views.CandidateView;
import views.MainTerminal;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

import connections.JDBC;

public class CandidateFunction {

    public void addCandidate() throws IOException, SQLException, ParseException, ClassNotFoundException {
        Scanner io = new Scanner(System.in);
        System.out.println("Enter the national id:");
        String nationalID = io.nextLine();
        System.out.println("Enter Candidate Name:");
        String name = io.nextLine();
        System.out.println("Enter the email:");
        String email = io.nextLine();
        System.out.println("Enter the mobile number:");
        String mobileNumber = io.nextLine();
        System.out.println("Enter the password:");
        String password = io.nextLine();
        System.out.println("Enter the address:");
        String address = io.nextLine();
        System.out.println("Enter the candidate's group:");
        String group = io.nextLine();
        System.out.println("Enter the zone:");
        String zone = io.nextLine();
        Candidate newCandidate = new Candidate(nationalID, name, email
        , mobileNumber, password, address, group, zone); 
        CandidateJDBC.addCandidateToJDBC(newCandidate);
        System.out.println("Candidate added successfully");

        System.out.println("Press 0 to exit or any key to continue");
         String cont= io.nextLine();
        if(cont.equals("0")){
        System.exit(0);
        }else{
        candidateTerminal();
        }
    }

    public void displayAllCandidate() throws IOException, SQLException, ParseException, ClassNotFoundException{
        ArrayList<Candidate> candidateList= CandidateJDBC.readAllFromCandidate();
        if(candidateList !=null){
        CandidateView.displayCandidates(candidateList);
        } else{
          System.out.println("There is not any candidate");
        }
        Scanner io= new Scanner(System.in);
        System.out.println("Press 0 to exit or any key to continue");
        String cont= io.nextLine();
        if(cont.equals("0")){
          System.exit(0);
        }else{
          candidateTerminal();
        }
      
      }

      public ArrayList<Candidate> allCandidate() throws SQLException {
        ArrayList<Candidate> candidates= CandidateJDBC.readAllFromCandidate();
        return  candidates;
      }

    public void displayOneCandidate() throws IOException, SQLException, ParseException, ClassNotFoundException{
        ArrayList<Candidate> candidateList= CandidateJDBC.readAllFromCandidate();
        Scanner io=new Scanner(System.in);
        if (candidateList!=null){
          System.out.println("Enter id of the candidate you want to see: ");
          String id=io.nextLine();
          boolean found=false;
          for(Candidate candidate:candidateList){
            if(candidate.getNationalID().equals(id)){
              CandidateView candidateView=new CandidateView();
              candidateView.displayOneCandidate(candidate);
              found=true;
            }
          }
          if(!found){
            System.out.println("This candidate doesn't exist");
          }
        }else{
          System.err.println("There is not any candidate\n");
        }
        System.out.println("Press 0 to exit or any key to continue");
        String cont= io.nextLine();
        if(cont.equals("0")){
          System.exit(0);
        }else{
          candidateTerminal();
        }
      }  

      public void editCandidate() throws IOException, SQLException, ParseException, ClassNotFoundException{
        ArrayList<Candidate> candidateList = CandidateJDBC.readAllFromCandidate();
        Scanner io=new Scanner(System.in);
        if(candidateList!=null){
          System.out.println("Enter the national-ID that you want to edit: ");
          String id=io.nextLine();
          boolean found=false;
          String nationalId= null;
          for (Candidate candidate : candidateList) {
              if( candidate.getNationalID().equals(id)){
                found= true;
                nationalId= candidate.getNationalID();
              }
          }
          if(found){
            System.out.println("What do you want to change ?"+
            "\n1-Name"+
            "\n2-Group"+
            "\n3-Zone"+
            "\n4-Address"+
            "\n5-Email"+
            "\n6-Mobile Number"+
            "\n7-password"
            );
            String option=io.nextLine();
            String sql=null;
            switch(option){
              case "1":
              System.out.println("Enter the new Name:");
              String newName=io.nextLine();
              sql="UPDATE candidates SET name='"+newName+"' WHERE nationalID='"+nationalId+"'";
              break;
    
              case "2":
              System.out.println("Enter the new group:");
              String newGroup=io.next();
              sql="UPDATE candidates SET groupp='"+newGroup+"' WHERE nationalID='"+nationalId+"'";
              break;
    
              case "3":
              System.out.println("Enter the new zone :");
              String newZone=io.nextLine();
              sql="UPDATE candidates SET zone='"+newZone+"' WHERE nationalID='"+nationalId+"'";
              break;

              case "4":
              System.out.println("Enter the new address :");
              String newAddress=io.nextLine();
              sql="UPDATE candidates SET address='"+newAddress+"' WHERE nationalID='"+nationalId+"'";
              break;

              case "5":
              System.out.println("Enter the new Email :");
              String newEmail=io.nextLine();
              sql="UPDATE candidates SET email='"+newEmail+"' WHERE nationalID='"+nationalId+"'";
              break;

              case "6":
              System.out.println("Enter the new Mobile Number :");
              String newMobileNumber=io.nextLine();
              sql="UPDATE candidates SET phoneNumber='"+newMobileNumber+"' WHERE nationalID='"+nationalId+"'";
              break;

              case "7":
              System.out.println("Enter the new password :");
              String newPassord=io.nextLine();
              sql="UPDATE candidates SET password='"+newPassord+"' WHERE nationalID='"+nationalId+"'";
              break;
            }
            System.out.println("Candidate Successfully updated");
            JDBC.update(sql);
          }else{
            System.out.println("There is not a candidate with such id");
          }
    
        } else{
          System.out.println("There is not any candidate");
        }
        System.out.println("Press 0 to exit or any key to continue");
        String cont= io.nextLine();
        if(cont.equals("0")){
          System.exit(0);
        }else{
          candidateTerminal();
        }
        
      }

    public void removeCandidate() throws IOException, SQLException, ParseException, ClassNotFoundException{
        ArrayList<Candidate> candidateList= CandidateJDBC.readAllFromCandidate();
        Scanner io=new Scanner(System.in);
        if(candidateList!=null){
          System.out.println("Enter the national-ID that you want to remove: ");
          String id=io.nextLine();
          boolean deleted=false;
          for (Candidate candidate : candidateList) {
            if( candidate.getNationalID().equals(id)){
              deleted= true;
              CandidateJDBC.removeCandidateById(id);
            }
          }

          if(deleted){
            System.out.println("candidate Successfully Deleted");
          } else{
            System.out.println("We couldn't find a candidate whith such id");
          }
    
        }else{
          System.out.println("There is not any candidate");
        }
        System.out.println("Press 0 to exit or any key to continue");
        String cont= io.nextLine();
        if(cont.equals("0")){
          System.exit(0);
        }else{
          candidateTerminal();
        }
      }


    public void candidateTerminal() throws IOException, SQLException, ParseException, ClassNotFoundException{
        Scanner io=new Scanner(System.in);
        System.out.println("Please Choose a number:"+
        "\n1. Display candidates."+
        "\n2. Display one candidate"+
        "\n3. Add candidate."+
        "\n4. Edit candidate."+
        "\n5. delete candidate."+
        "\n6. go back"
        );
        
        String inputKey="Start";
        while (inputKey!="0") {
          inputKey=io.nextLine();
          switch (inputKey) {
            case "1":
            this.displayAllCandidate();
            break;
    
            case "2":
            this.displayOneCandidate();
            break;
    
            case "3":
            this.addCandidate();
      
            break;
            case "4":
            this.editCandidate();
      
            break;
            case "5":
            this.removeCandidate();
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




