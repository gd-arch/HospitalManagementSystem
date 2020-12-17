/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 *
 * @author g
 */
public class DBConnection {
 private  static Connection conn;//   this is made available to dao object
   static{
     try {
         Class.forName("oracle.jdbc.OracleDriver");
          conn=DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe","myhms","student");
               JOptionPane.showMessageDialog(null, "Connection done successfully","Success!", JOptionPane.INFORMATION_MESSAGE);

     } catch (ClassNotFoundException ex) {
    JOptionPane.showMessageDialog(null, "Cannot load driver"+ex,"Error!",JOptionPane.ERROR_MESSAGE);
     }  catch (SQLException e) {
    JOptionPane.showMessageDialog(null, "Problem in DB"+e,"Error!",JOptionPane.ERROR_MESSAGE);
    }

   
    
   } 
   public static Connection  getConnection(){
       
       return conn;
       
   }
    

    public static void closeConnection() {
 try{  
        if (conn!=null)
        conn.close();
        JOptionPane.showMessageDialog(null,"connection closed successfully!");
      } catch (SQLException e) {
    JOptionPane.showMessageDialog(null, "Problem in closing connection"+e,"Error!",JOptionPane.ERROR_MESSAGE);
    }    }

   
    
}
