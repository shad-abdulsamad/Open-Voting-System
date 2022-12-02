package method;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connections.JDBC;
import models.Candidate;

public class CandidateJDBC {
   
   public static ArrayList<Candidate> readAllFromCandidate() throws SQLException{
        ResultSet result = JDBC.display("SELECT * FROM candidates");
        ArrayList<Candidate> candidateList = new ArrayList<>();
        
        while (result.next()) {

            String ID = result.getString("nationalId");
            String Name = result.getString("name");
            String Email = result.getString("email");
            String MobileNumber = result.getString("phoneNumber");
            String Password = result.getString("password");
            String address = result.getString("address");
            String group = result.getString("groupp");
            String zone = result.getString("zone");
            Candidate newCandidate=new Candidate(ID,Name,Email,MobileNumber,Password, address,group, zone);
            candidateList.add(newCandidate);
        }
        return candidateList;
        
   }

   public static void addCandidateToJDBC(Candidate candidate) throws SQLException{
    String sql ="INSERT INTO `candidates` VALUES ('"+candidate.getNationalID()+"', '"+candidate.getName()+"', '"+candidate.getEmail()+"', '"+candidate.getMobileNumber()+"', '"+candidate.getPassword()+"', '"+candidate.getAddress()+"', '"+candidate.getGroup()+"', '"+candidate.getZone()+"')";
    JDBC.insert(sql);

   }

   public static void removeCandidateById(String nationalId) throws SQLException{
    String sql = "DELETE FROM `candidates` WHERE nationalId = '"+nationalId+"'";
    JDBC.remove(sql);
   }

}
