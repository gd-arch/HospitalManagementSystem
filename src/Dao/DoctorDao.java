/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import DBUtil.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import pojo.DoctorPojo;
import pojo.PatientPojo;
import pojo.UserDetails;

/**
 *
 * @author g
 */
public class DoctorDao {
     public static String getNewId( )throws SQLException{
        Statement st=DBConnection.getConnection().createStatement();
       
        ResultSet rs =st.executeQuery("select max(doctorid) from doctor");
            int id=1;   
  if(rs.next()){
         String empid=rs.getString(1);
          System.out.println("empid"+empid);
         int eno=Integer.parseInt(empid.substring(3));
         id=id+eno;}
         String str="DOC"+id;
  
       return str;
        
    }
   
    public static HashMap<String,String> getEmpNameById()throws SQLException{
        Statement st=DBConnection.getConnection().createStatement();
        ResultSet rs=st.executeQuery("select empid,empname from employees where role='DOCTOR' and empid not in(select empid from users)");
        HashMap<String,String> nameandid=new HashMap<>();
        while(rs.next()){
            String empid=rs.getString(1);
            String empname=rs.getString(2);
            nameandid.put(empid,empname);
        }
        return nameandid;
    }
 public static boolean addDoctor(UserDetails user) throws SQLException {
PreparedStatement ps=DBConnection.getConnection().prepareStatement("insert into users values(?,?,?,?,?) ");
      ps.setString(1, user.getUserId());
ps.setString(2, user.getUserName());
ps.setString(3, user.getEmpId());
ps.setString(4, user.getPassword());
ps.setString(5, user.getUsertype());

return ps.executeUpdate()>0;
         }  
        public static String getEmployeeNamefromUserId(String userId) throws SQLException{
              Connection conn=DBConnection.getConnection();
                String s = null;
               PreparedStatement ps=conn.prepareStatement ("select username from users where userid=? ");
               ps.setString(1, userId);
              ResultSet rs=ps.executeQuery();
              while(rs.next()){
                   s=rs.getString(1);
              }
              return s;
        }
        public static boolean updateDoctor(DoctorPojo doc) throws SQLException{
            PreparedStatement ps=DBConnection.getConnection().prepareStatement("update doctor set USERID = ?,  QUALIFICATION = ?,SPECIALIST = ? where doctorid = ?");
            ps.setString(1, doc.getUserid());
            ps.setString(2,doc.getQualification());
            ps.setString(3,doc.getSpecialist());
            ps.setString(4,doc.getDoctorid());
       int ans=ps.executeUpdate();
            if(ans>0){
                return true;
            }else return false;
        }
 
    public static boolean deleteDoctor(String a) throws SQLException{
      Connection conn=DBConnection.getConnection();
      conn.setAutoCommit(false);
      PreparedStatement ps=conn.prepareStatement ("update doctor set active = 'N' where doctorid  = ?");
      ps.setString(1, a);
      int ans = ps.executeUpdate();
      if(ans>0){
          conn.commit();
          return true;
      }
      else{
          conn.rollback();
          return false;
      }
    }
    
    public static ArrayList <String> getAllDoctorId() throws SQLException{
     ArrayList <String> doclist=new ArrayList <> ();
     
        Statement st=DBConnection.getConnection().createStatement();
        ResultSet rs =st.executeQuery("select doctorid from doctor where active='A'");
        while(rs.next())
        {
        doclist.add(rs.getString(1));    
        }   
        return doclist;
    }
     public static boolean registerDoctor(DoctorPojo d)throws SQLException{
        Connection conn=DBConnection.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement ps=conn.prepareStatement("insert into users values(?,?,?,?,?)");
        ps.setString(1, d.getUserid());
        ps.setString(2, d.getDoctorname());
        ps.setString(3, d.getEmpid());
        ps.setString(4, d.getPassword());
        ps.setString(5, "DOCTOR"); 
        int ans=ps.executeUpdate();
        PreparedStatement ps1=conn.prepareStatement("insert into doctor values(?,?,?,?,?)");
        ps1.setString(1,d.getUserid());
        ps1.setString(2,d.getDoctorid());
        ps1.setString(3,d.getQualification());
        ps1.setString(4,d.getSpecialist());
        ps1.setString(5,d.getActive());
        int ans1=ps1.executeUpdate();
        if(ans>0&&ans1>0){
            conn.commit();
            return true;
        }
        else{
            conn.rollback();
            return false;
        }
    }
      public static ArrayList<DoctorPojo> getAllDoc() throws SQLException{
          ArrayList <DoctorPojo> emplist=new ArrayList<>();
        Statement st=DBConnection.getConnection().createStatement();
        ResultSet rs =st.executeQuery("select * from doctor where active='A'");
        while(rs.next()){
            DoctorPojo e=new DoctorPojo();
           e.setUserid(rs.getString(1));
           e.setDoctorid(rs.getString(2));
           e.setQualification(rs.getString(3));
           e.setSpecialist(rs.getString(4));
            emplist.add(e);
        }
        return emplist;
        
        
    }

    public static HashMap<String, DoctorPojo> getDoctorsInfo() throws SQLException{
HashMap<String, DoctorPojo> hm=new HashMap<>();
DoctorPojo user=new DoctorPojo();
Statement ps =DBConnection.getConnection().createStatement();
       ResultSet rs=ps.executeQuery("select * from doctor where active='A' ");
          while(rs.next()){
            String userid=rs.getString(1);
            String doctorid=rs.getString(2);
            String qualification=rs.getString(3);
             String specialist=rs.getString(4);
             String active=rs.getString(5);
             user.setUserid(userid);
             user.setDoctorid(doctorid);
             user.setQualification(qualification);
             user.setSpecialist(specialist);
             user.setActive(active);
            hm.put(doctorid,user);
        }
        return hm;
    }
  public static ArrayList<PatientPojo> viewAppointment(String userid) throws SQLException{
      ArrayList <PatientPojo> list=new ArrayList<>();
      String docid = null;
      PatientPojo p=new PatientPojo();
     Connection conn=DBConnection.getConnection();
      
        PreparedStatement q=conn.prepareStatement("select DOCTORID from doctor where userid=?");
        q.setString(1,userid);
         ResultSet qrs=q.executeQuery();
       
        while(qrs.next()){
          docid= qrs.getString(1);
        }System.out.println(docid);
        PreparedStatement ps=conn.prepareStatement("select P_ID,F_NAME,S_NAME,OPD from patient where DOCTOR_ID=?");
      ps.setString(1,docid);
       ResultSet rs=ps.executeQuery();
       while(rs.next()){
           PatientPojo e=new PatientPojo();
           e.setP_id(rs.getString(1));
           e.setF_name(rs.getString(2));
           e.setS_name(rs.getString(3));
           e.setOpd(rs.getString(4));
            list.add(e);
        }
        return list;
  } 
    
    
    
    
}
