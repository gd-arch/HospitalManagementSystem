/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import DBUtil.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import pojo.UserDetails;

/**
 *
 * @author g
 */
public class ReceptionistDao {
   public static boolean addReceptionist(UserDetails user) throws SQLException{
       
       PreparedStatement ps=DBConnection.getConnection().prepareStatement("insert into users values(?,?,?,?,?)");
         ps.setString(1,user.getUserId());
       ps.setString(2,user.getUserName());
        ps.setString(3,user.getEmpId());
         ps.setString(4,user.getPassword());
  ps.setString(5,user.getUsertype());
         int x=ps.executeUpdate();
       
         return x>0;
   }
    public static boolean removeReceptionist( String recid ) throws SQLException{
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("delete from employees where empid= ? and role='RECEPTIONIST'");
        ps.setString(1, recid);
        int result=ps.executeUpdate();
        return result>0;
        
    }
  public static ArrayList<String>  getAllReceptionistListId() throws SQLException{
      ArrayList<String> reclist=new ArrayList<>();
      Statement st= DBConnection.getConnection().createStatement();
      ResultSet rs =st.executeQuery("select empid from employees where role='RECEPTIONIST'");
      while(rs.next()){
          String empname=rs.getString(1);
          reclist.add(empname);
      }
      return reclist;
  }
  
}
