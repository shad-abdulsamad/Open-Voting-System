package connections;
import java.sql.*;


public class JDBC {

    public static void insert(String sql){
                try {
                    Class.forName(JDBCDRIVER.getClassName().className);
                    Connection connection = DriverManager.getConnection(JDBCLINK.getUrl().finalUrl,"root",""); 
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(sql);
                    
                } catch (ClassNotFoundException e) {
        
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
        }
    }
    public static ResultSet display(String sql) {
        ResultSet resultSet = null;
        try {
            Class.forName(JDBCDRIVER.getClassName().className);
            Connection connection = DriverManager.getConnection(JDBCLINK.getUrl().finalUrl,"root",""); 
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);//read only
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static void remove(String sql) throws SQLException {
        try {
            Class.forName(JDBCDRIVER.getClassName().className);
            Connection connection = DriverManager.getConnection(JDBCLINK.getUrl().finalUrl,"root",""); 
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }
    }   
    
    public static void update(String sql) throws SQLException {
        try {
            Class.forName(JDBCDRIVER.getClassName().className);
            Connection connection = DriverManager.getConnection(JDBCLINK.getUrl().finalUrl,"root",""); 
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }
    }    

}
