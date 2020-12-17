/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author g
 */
public class DoctorPojo {

    public DoctorPojo(String userid, String doctorid, String qualification, String specialist, String active, String doctorname, String password, String Empid) {
        this.userid = userid;
        this.doctorid = doctorid;
        this.qualification = qualification;
        this.specialist = specialist;
        this.active = active;
        this.doctorname = doctorname;
        this.password = password;
        this.Empid = Empid;
    }

    public DoctorPojo() {
       
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmpid() {
        return Empid;
    }

    public void setEmpid(String Empid) {
        this.Empid = Empid;
    }

   
    private String userid;
    private String doctorid;
    private String qualification;
    private String specialist;
    private String active;
    private String doctorname;
  private String password;
  private String Empid;
}
