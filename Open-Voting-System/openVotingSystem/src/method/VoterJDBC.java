package method;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connections.JDBC;
import models.Voter;

public class VoterJDBC {
   
   public static ArrayList<Voter> readAllFromVoter() throws SQLException{
        ResultSet result = JDBC.display("SELECT * FROM voter");
        ArrayList<Voter> voterList = new ArrayList<>();
        
        while (result.next()) {
            String ID = result.getString("nationalId");
            String Name = result.getString("name");
            String Email = result.getString("email");
            String MobileNumber = result.getString("phoneNumber");
            String Password = result.getString("password");
            String address = result.getString("address");
            String age = result.getString("age");
            String zone = result.getString("zone");
            
            Voter newVoter=new Voter(ID,Name,Email,MobileNumber,Password,age, address,zone);
            // Voter newVoter=new Voter(Name,age,Email,MobileNumber,Password, address,zone, ID);
            voterList.add(newVoter);
        }
        return voterList;
        
   }

   public static void addVoterToJDBC(Voter voter) throws SQLException{
    // String sql ="INSERT INTO `voter` VALUES ('"+voter.getNationalID()+"', '"+voter.getFullName()+"', '"+voter.getEmail()+"', '"+voter.getPassword()+"', '"+voter.getMobileNumber()+"', '"+voter.getAge()+"', '"+voter.getAddress()+"', '"+voter.getZone()+"')";
    String sql ="INSERT INTO `voter` VALUES ('"+voter.getNationalID()+"', '"+voter.getFullName()+"', '"+voter.getEmail()+"', '"+voter.getPassword()+"', '"+voter.getMobileNumber()+"', '"+voter.getAge()+"', '"+voter.getAddress()+"', '"+voter.getZone()+"', 'false','0','0','0')";
    JDBC.insert(sql);

   }

   public static void removeVoterById(String nationalId) throws SQLException{
    String sql = "DELETE FROM `voter` WHERE nationalId = '"+nationalId+"'";
    JDBC.remove(sql);
   }

}
