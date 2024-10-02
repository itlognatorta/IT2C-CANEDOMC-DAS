
package it2c.canedomc.das;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class config {
   
   
public static Connection connectDB() {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC"); 
            con = DriverManager.getConnection("jdbc:sqlite:christian.db"); 
            System.out.println("Connection Successful");
        } catch (Exception e) {
            System.out.println("Connection Failed: " + e);
        }
        return con;
    }

  public void addCustomers (String sql, String... values){
      try(Connection conn = this.connectDB();
      PreparedStatement pstmt = conn.prepareStatement(sql)){
    
    for(int i = 0; i < values.length; i++){
          pstmt.setString(i + 1, values[i]);
      }
    
    pstmt.executeUpdate();
            System.out.println("Record added Successfully!");
}catch (SQLException e){
            System.out.println("Error adding Record, Please Try Again: " + e.getMessage());
}
}

    
    
              
    
        
       

    
    
    
}
