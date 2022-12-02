package views;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

import connections.JDBC;
import connections.JDBCDRIVER;
import connections.JDBCLINK;
import method.*;
import models.Admin;
import models.Candidate;
import models.Voter;

public class MainTerminal {

    public static void loginTerminal() throws IOException, SQLException, ParseException, ClassNotFoundException{
        Scanner io= new Scanner(System.in);
        System.out.println("Welcome to the Open Voting System"+
        "\nPlease go ahead and choose your role: "+
        "\n1- Voter"+
        "\n2- Admin"
        );
        String role= io.nextLine();
        String input="1";
        while(!input.equals("0")){

            String id, password;
            switch (role) {
                case "1":
                    System.out.println("Enter your ID: ");
                    id=io.nextLine();
                    System.out.println("Enter your Password: ");
                    password= io.nextLine();
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
                            voterMainTerminal(id);
                        }if(!foundVoter) System.out.println("Incorect password or id!");
                    }else {
                        System.out.println("no voter found");
                    }
                    break;
                case "2":
    
                    System.out.println("Enter your ID: ");
                    id=io.nextLine();
                    System.out.println("Enter your Password: ");
                    password= io.nextLine();
                    ArrayList<Admin> adminList = AdminJDBC.readAllFromAdmin();
                    if(adminList!=null){
                        boolean found=false;
                        for (Admin admin : adminList) {
                          if( admin.getNationalId().equals(id)&&admin.getPassword().equals(password)){
                          found= true;
                          System.out.println("Welcome: " + admin.getFullname());
                          break;
                        }
                    }
                    if(found){
                        adminMainTerminal();
                    
                    }else{
                        System.out.println("Incorrect Id or Password!");
                    }
                    }
                     break;
                default:
                     break;
            }
            System.out.println("Press 0 to exit or any other key to continue");
            input= io.nextLine();
        }
    }

    public static void adminMainTerminal() throws SQLException, IOException, ParseException, ClassNotFoundException{
        Scanner io= new Scanner(System.in);
        System.out.println("""
        Open Voting System
          1- Voter section.
          2- Candidate section.
          3- Admin section.
          4- Logout.
          5- final results
          """
        );

        String section= io.nextLine();

        switch (section) {
            case "1":
                VoterFunction voterFunction= new VoterFunction();
                voterFunction.voterTerminal();
                break;
            case "2":
                CandidateFunction candidateFunction = new CandidateFunction();
                candidateFunction.candidateTerminal();
                break;
            case "3":
                AdminFunction adminFunction = new AdminFunction();
                adminFunction.adminTerminal();
                break;
            case "4":
                loginTerminal();
                break;
            case "5":
                countResult();
                break;
            default:
                break;
        }
    }

    public static void countResult() throws SQLException {

        HashMap<String,Integer> candidates=new HashMap<>();
        String query="SELECT (SELECT name FROM candidates WHERE nationalId= firstVote) AS 'name', COUNT(firstVote)*3 AS 'count' FROM voter GROUP BY firstVote;";
        ResultSet rst=JDBC.display(query);
        while (rst.next()){
            String name=rst.getString("name");
            int count=rst.getInt("count");
            if(name==null) continue; 
                candidates.put(name,count);
        }
        
        query="SELECT (SELECT name FROM candidates WHERE nationalId= secondVote) AS 'name', COUNT(secondVote)*2 AS 'count' FROM voter GROUP BY secondVote;";
        rst=JDBC.display(query);
        while (rst.next()){
            String name=rst.getString("name");
            int count=rst.getInt("count");
            if(name==null) continue; 
            candidates.computeIfPresent(name,(k,v)->v+count);
            candidates.computeIfAbsent(name,(k)->count);
        }
    
        query="SELECT (SELECT name FROM candidates WHERE nationalId= thirdVote) AS 'name', COUNT(thirdVote) AS 'count' FROM voter GROUP BY thirdVote;";
        rst=JDBC.display(query);
        while (rst.next()){
            String name=rst.getString("name");
            int count=rst.getInt("count");
            if(name==null) continue; 
            candidates.computeIfPresent(name,(k,v)->v+count);
            candidates.computeIfAbsent(name,(k)->count);
        }
        
        for (Map.Entry<String,Integer> candidate: candidates.entrySet()){
            System.out.println(candidate.getKey()+": "+candidate.getValue());
        }

        
        int max = Collections.max(candidates.values());
        for (Map.Entry<String,Integer> candidate: candidates.entrySet()){
            if(candidate.getValue()==max){
                System.out.println("The winner is: "+candidate.getKey()+" with "+candidate.getValue()+" votes");
            }
        }

    }

    public static void voterMainTerminal(String id) throws SQLException, IOException, ParseException, ClassNotFoundException {
        Scanner io=new Scanner(System.in);
        while (true){
            String out= """
                    1- vote
                    2- change password
                    3- delete account
                    4- logout
                    5- exit
                    """;
            System.out.println(out);
            int selected=io.nextInt();
            switch (selected){
                case 1:
                    String isVoted="SELECT isVoted FROM voter where nationalId = '"+id+"';";
                    ResultSet rst=JDBC.display(isVoted);
                    rst.next();
                    isVoted=rst.getString("isVoted");
                    if(isVoted.equals("1")){
                        System.out.println("You have voted!");
                        voterMainTerminal(id);
                    }
                    CandidateFunction candidateFunction=new CandidateFunction();
                    ArrayList<Candidate> candidates= candidateFunction.allCandidate();
                    for(Candidate candidate:candidates){
                        System.out.println(candidate.getNationalID()+": "+ candidate.getName());
                    }
                  System.out.println("Enter your first vote");
                    int firstVote=io.nextInt();
                    for(Candidate candidate:candidates){
                        if(!Integer.toString(firstVote).equals(candidate.getNationalID())) System.out.println(candidate.getNationalID()+": "+ candidate.getName());
                    }
                    System.out.println("Enter your second vote");
                    int secondVote=io.nextInt();
                    while (secondVote==firstVote){
                        System.out.println("You can't vote the same person");

                        System.out.println("Enter your second vote");
                        secondVote=io.nextInt();
                    }
                    System.out.println("Enter your third vote");
                    for(Candidate candidate:candidates){
                        if(!Integer.toString(secondVote).equals(candidate.getNationalID()) && !Integer.toString(firstVote).equals(candidate.getNationalID())) System.out.println(candidate.getNationalID()+": "+ candidate.getName());
                    }
                    int thirdvote= io.nextInt();
                    while (firstVote==thirdvote||secondVote==thirdvote){
                        System.out.println("You can't vote the same person");
                        System.out.println("Enter your third vote");
                        thirdvote=io.nextInt();
                    }
                    String query="UPDATE voter SET isVoted = true, firstVote = "+firstVote+", secondVote = "+secondVote+", thirdVote = "+thirdvote+" WHERE nationalId = "+id+";";
                    JDBC.update(query);
                    System.out.println("Thank you for voting");
                    voterMainTerminal(id);


                case 2:
                    String queryForChangingPassword="UPDATE voter SET password = ? WHERE nationalId = "+id+";";

                    Class.forName(JDBCDRIVER.getClassName().className);
                    Connection connection = DriverManager.getConnection(JDBCLINK.getUrl().finalUrl,"root",""); 
                    PreparedStatement pst=connection.prepareStatement(queryForChangingPassword);
                    System.out.println("Enter your new password");
                    String newPassword=io.next();
                    pst.setString(1, newPassword);
                    pst.executeUpdate();
                    System.out.println("Password changed successfully");
                    break;
                case 3:
                    String queryForDeletingAccount="DELETE FROM voter WHERE nationalId = "+id+";";
                    JDBC.update(queryForDeletingAccount);
                    System.out.println("Account deleted successfully");
                    System.exit(0);
                    break;
                case 4:
                     loginTerminal();
                     break;
                case 5:
                     System.exit(0);
                     break;
                default:
                    break;


            }
        }

    }

}
