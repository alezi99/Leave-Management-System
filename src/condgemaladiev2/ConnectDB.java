/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package condgemaladiev2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ikerfah
 */
public class ConnectDB {
    
    public static Connection con;
    public static Connection getConnection()
    {
         
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String unicode= "?useUnicode=yes&characterEncoding=UTF-8";
            con = DriverManager.getConnection("jdbc:mysql://localhost/congev2"+unicode, "root", "toor");
        } catch (ClassNotFoundException | SQLException ex) {
           
        }
        
        return con;
       
    }
    
}
