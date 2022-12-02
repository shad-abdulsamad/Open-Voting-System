package method;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connections.JDBC;
import models.Admin;

public class AdminJDBC {
   
   public static ArrayList<Admin> readAllFromAdmin() throws SQLException{
        ResultSet result = JDBC.display("SELECT * FROM admins");
        ArrayList<Admin> adminList = new ArrayList<>();
        
        while (result.next()) {
            String ID = result.getString("nationalId");
            String Name = result.getString("name");
            String Email = result.getString("email");
            String MobileNumber = result.getString("phoneNumber");
            String Password = result.getString("password");
            Admin admin = new Admin(ID, Name, Email, MobileNumber, Password);
            adminList.add(admin);
        }
        return adminList;
        
   }

   public static void addAdminToJDBC(Admin admin) throws SQLException{
    String sql ="INSERT INTO `admins` VALUES ('"+admin.getNationalId()+"', '"+admin.getFullname()+"', '"+admin.getEmail()+"', '"+admin.getPassword()+"', '"+admin.getPhoneNo()+"')";
    JDBC.insert(sql);

   }

   public static void removeAdminById(String nationalId) throws SQLException{
    String sql = "DELETE FROM `admins` WHERE nationalId = '"+nationalId+"'";
    JDBC.remove(sql);
   }

}
