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
import java.util.HashMap;
import pojo.UserDetails;
import pojo.UserPojo;

   public class UserDao {
    
    public static String  validateUser(UserPojo user ) throws SQLException {
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("select username from users "
                + "where userid = ? and  Password = ? and usertype = ? ");
        
        ps.setString(1,user.getUserId());
       ps.setString(2,user.getPassword());
        ps.setString(3,user.getUsertype());
         ResultSet rs =ps.executeQuery();
         String userName=null;
      if (rs.next())
     {
    userName =rs.getString(1);
    }
    return userName;
        
}
   public static boolean changePassword(String userid,String pwd) throws SQLException{
           PreparedStatement ps=DBConnection.getConnection().prepareStatement("Update Users set password=? where userid=?");
       ps.setString(2,userid);
        ps.setString(1,pwd);
        return (ps.executeUpdate()!=0) ;
   }
   public static HashMap <String,String> getReceptionistList() throws SQLException{
        HashMap <String,String> hm=new HashMap<>();
       Statement ps =DBConnection.getConnection().createStatement();
       ResultSet rs=ps.executeQuery("select empid,username from users where usertype='RECEPTIONIST' ");
          while(rs.next()){
            String id=rs.getString(1);
            String name=rs.getString(2);
            hm.put(id,name);
        }
        return hm;
    }
 public static HashMap <String,String> getDoctorList() throws SQLException{
        HashMap <String,String> hm=new HashMap<>();
       Statement ps =DBConnection.getConnection().createStatement();
       ResultSet rs=ps.executeQuery("select doctorid,userid from doctor");
          while(rs.next()){
            String id=rs.getString(1);
            String name=rs.getString(2);
            hm.put(id,name);
        }
        return hm;
    }
    /*public static HashMap<String, UserDetails> getDoctorsList() throws SQLException{
HashMap<String, UserDetails> hm=new HashMap<>();
UserDetails user=new UserDetails();
Statement ps =DBConnection.getConnection().createStatement();
       ResultSet rs=ps.executeQuery("select * from users where usertype='DOCTOR' ");
          while(rs.next()){
            String userid=rs.getString(1);
            String name=rs.getString(2);
            String empid=rs.getString(3);
             String password=rs.getString(4);
             String UserType=rs.getString(5);
             user.setUserId(userid);
             user.setEmpId(empid);
             user.setPassword(password);
             user.setUserName(name);
             user.setUsertype(UserType);
            hm.put(empid,user);
        }
        return hm;
    }*/


    }
       
    
    
