package Dao;

import DBUtil.DBConnection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import pojo.PatientPojo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author g
 */
public class PatientDao {
   
    
    public static String getNewId() throws SQLException{
        
        
        Statement st=DBConnection.getConnection().createStatement();
        ResultSet rs=st.executeQuery("select max(P_ID) from patient");
     int id=1;   if(rs.next()){
         String str=rs.getString(1);
         int pno=Integer.parseInt(str.substring(1));
   id=pno+id;
     String sr = "P" + id;
        System.out.println(sr);
        return sr;
        }
        else 
        return "P101";
        
    }
    
    public static boolean updatePatient(PatientPojo p) throws SQLException{ 
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("update patient set P_ID = ? ,F_NAME = ?, S_NAME = ?, AGE = ?,OPD = ?, GENDER = ?,M_STATUS = ?,P_DATE =?,ADDRESS = ?, CITY = ?, PHONE_NO = ?");
        ps.setString(1, p.getP_id());
        ps.setString(2, p.getF_name());
        ps.setString(3, p.getS_name());
        ps.setInt(4, p.getAge());
        ps.setString(5, p.getOpd());
        ps.setString(6, p.getGender());
        ps.setString(7, p.getM_status());
        ps.setDate(8,p.getDate());
        ps.setString(9, p.getAddress());
        ps.setString(10, p.getCity());
        ps.setString(11,p.getMno());
   int result=ps.executeUpdate();
   return (result>0);
    }
    
    public static boolean addPatient(PatientPojo p )throws SQLException{
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("insert into patient values (?,?,?,?,?,?,?,?,?,?,?,?)");
     
       
        ps.setString(1, p.getP_id());
        ps.setString(2, p.getF_name());
        ps.setString(3, p.getS_name());
           ps.setInt(4, p.getAge());
        ps.setString(5, p.getOpd());
        ps.setString(6, p.getGender());
        ps.setString(7, p.getM_status());
          ps.setDate(8,p.getDate());
        ps.setString(9, p.getAddress());
        ps.setString(10, p.getCity());
        ps.setString(11,p.getMno());
        ps.setString(12,p.getDoctor_id());
        return (ps.executeUpdate()!=0);   
        
    }
    public static boolean removePatient(String pid) throws SQLException{
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("delete from patient  where P_ID = ? ");
       ps.setString(1,pid);
     
       int x=ps.executeUpdate();
        return x>0;
            
        
        
    }
      public static ArrayList<String>  getAllPatientList() throws SQLException{
      ArrayList<String> patlist=new ArrayList<>();
      Statement st= DBConnection.getConnection().createStatement();
      ResultSet rs =st.executeQuery("select P_ID from patient ");
      while(rs.next()){
          String empname=rs.getString(1);
          patlist.add(empname);
      }
      return patlist;
  }
      public static ArrayList<PatientPojo> getAllPatient() throws SQLException{
         Statement st = DBConnection .getConnection().createStatement();
   ResultSet rs  = st.executeQuery("Select P_ID, F_NAME ,S_NAME ,AGE,OPD,GENDER ,M_STATUS,P_DATE,ADDRESS,CITY,PHONE_NO,DOCTOR_ID  from patient");
    ArrayList<PatientPojo> patient = new ArrayList<>();
    while(rs.next()){
        PatientPojo p = new PatientPojo();
        p.setP_id(rs.getString(1));
        p.setF_name(rs.getString(2));
        p.setS_name(rs.getString(3));
        p.setAge(rs.getInt(4));
        p.setOpd(rs.getString(5));
        p.setGender(rs.getString(6));
        p.setM_status(rs.getString(7));
        p.setDate(rs.getDate(8));
        p.setAddress(rs.getString(9));
        p.setCity(rs.getString(10));
        p.setMno(rs.getString(11));
        p.setDoctor_id(rs.getString(12));
        patient.add(p);
    }
    return patient;
    }
}
