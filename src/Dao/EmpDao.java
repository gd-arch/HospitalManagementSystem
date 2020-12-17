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
import java.util.HashMap;
import pojo.EmpPojo;

/**
 *
 * @author g
 */
public class EmpDao {
     public static String getNewId( )throws SQLException{
        Statement st=DBConnection.getConnection().createStatement();
       
        ResultSet rs =st.executeQuery("select max(Empid) from employees");
            int id=1;   
  if(rs.next()){
         String empid=rs.getString(1);
         int eno=Integer.parseInt(empid.substring(1));
        id=id+eno;}
         String str="E"+id;
         
       return str;
        
    }
    public static boolean addEmployee(EmpPojo emp) throws SQLException{
          PreparedStatement ps=DBConnection.getConnection().prepareStatement("insert into employees values ( ? , ? , ? , ? ) ");
      
          ps.setString(1,emp.getEmpid());
       ps.setString(2,emp.getEmpname());
        ps.setString(3,emp.getJob().toUpperCase());
        ps.setDouble(4,emp.getSal());
          
        int x=ps.executeUpdate();
       
         return x>0;
        
    }
     public static ArrayList<EmpPojo> getAllReceptionistList() throws SQLException{
          ArrayList <EmpPojo> emplist=new ArrayList<>();
        Statement st=DBConnection.getConnection().createStatement();
        ResultSet rs =st.executeQuery("select * from employees where role='RECEPTIONIST'");
        while(rs.next()){
            EmpPojo e=new EmpPojo();
            e.setEmpid(rs.getString(1));
            e.setEmpname(rs.getString(2));
            e.setJob(rs.getString(3));
            e.setSal(rs.getDouble(4));
            emplist.add(e);
        }
        return emplist;
        
        
    }
     
    public static ArrayList<EmpPojo> getAllEmp() throws SQLException{
          ArrayList <EmpPojo> emplist=new ArrayList<>();
        Statement st=DBConnection.getConnection().createStatement();
        ResultSet rs =st.executeQuery("select * from employees");
        while(rs.next()){
            EmpPojo e=new EmpPojo();
            e.setEmpid(rs.getString(1));
            e.setEmpname(rs.getString(2));
            e.setJob(rs.getString(3));
            e.setSal(rs.getDouble(4));
            emplist.add(e);
        }
        return emplist;
        
        
    }
    public static boolean updateEmployee(EmpPojo emp) throws SQLException{
       PreparedStatement ps=DBConnection.getConnection().prepareStatement("update  employees set empname =? , role=? , sal=? where empid = ?");
       ps.setString(4,emp.getEmpid());
       ps.setString(1,emp.getEmpname());
        ps.setString(2,emp.getJob().toUpperCase());
        ps.setDouble(3,emp.getSal());
       int x=ps.executeUpdate();
        return x>0;
            
        }
    public static boolean deleteEmployee(String empid) throws SQLException{
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("delete from employees  where empid = ? ");
       ps.setString(1,empid);
       int x=ps.executeUpdate();
        return x>0;
            
        
        
    }
    
    public static HashMap<String,String> getNonRegisteredEmployeeList() throws SQLException{
        Statement st=DBConnection.getConnection().createStatement();
        ResultSet rs =st.executeQuery("select empid,empname from employees where empid not in(select empid from users)");
        HashMap <String,String> hm=new HashMap<>();
        while(rs.next()){
            String id=rs.getString(1);
            String name=rs.getString(2);
            hm.put(id,name);
        }
        return hm;
    }
    public static HashMap<String,String> getEmployeeList() throws SQLException{
        Statement st=DBConnection.getConnection().createStatement();
        ResultSet rs =st.executeQuery("select empid,empname from employees ");
        HashMap <String,String> hm=new HashMap<>();
            while(rs.next()){
            String id=rs.getString(1);
            String name=rs.getString(2);
            hm.put(id,name);
        }
        return hm;
    }
 public static HashMap<String,String> getNonRegisteredReceptionistList() throws SQLException{
        Statement st=DBConnection.getConnection().createStatement();
        ResultSet rs =st.executeQuery("select empid,empname from employees where role ='RECEPTIONIST' and empid not in (select empid from users where usertype='RECEPTIONIST')");
    HashMap<String,String> receptionlist=new HashMap<>();
    while(rs.next()){
      String id=  rs.getString(1);
          String name=  rs.getString(2);
          receptionlist.put(id,name);
    }
     return receptionlist;
     
     
 } 
    public static HashMap<String,String> getNonRegisteredDoctorsList() throws SQLException{
        
        Statement st =DBConnection.getConnection().createStatement();
        HashMap <String,String> hm=new HashMap<>();
        ResultSet rs=st.executeQuery("select empid,empname from employees where role='DOCTOR' and empid not in (select empid from users where usertype='DOCTOR')");
    HashMap<String,String> receptionlist=new HashMap<>();
        while (rs.next()){
            String id=rs.getString(1);
            String name=rs.getString(2);
            hm.put(id, name);
        }
        return hm;
    }
    
    
    
}
