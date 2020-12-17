/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Dao.PatientDao;
import Dao.DoctorDao;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import oracle.net.aso.d;
import pojo.PatientPojo;
import java.awt.Toolkit;

/**
 *
 * @author g
 */
public class AddPatientFrame extends javax.swing.JFrame {
    private int refs;
    private String opd;
    private String F_name;
    private String  S_name;
    private int age;
    private String p_id;
      private String  doctor_id;
    private String gender;
    private String m_status;
    private String address;
    private String city;
    private String m_Status;
     private String mno;
    private ArrayList<String> doctor;
   private java.sql.Date date;
    private java.util.Date d;
    
    public void loadDoctorId(){
        try{
            doctor =DoctorDao.getAllDoctorId();
                  for (String str: doctor){
            jcDoctorId.addItem(str);
        }   } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null,"Sql Exception in adding Employee id");
       }
        }
        private boolean validateInput(){
            
            F_name=txtName.getText();
            doctor_id=(String)jcDoctorId.getSelectedItem();
            S_name=txtSName.getText();
            age=Integer.parseInt(txtage.getText());
            p_id=txtPID.getText();
            opd=txtOpd.getText();
            gender =(String)jcGender.getSelectedItem();
            m_Status=(String)jcStatus.getSelectedItem();
            address=txtAddress.getText();
            city=txtCity.getText();
            mno=txtMno.getText();
            date=new java.sql.Date(d.getTime());
            if(F_name.isEmpty()||S_name.isEmpty()||age<=0||opd.isEmpty()||address.isEmpty()||opd.isEmpty()||city.isEmpty()||mno.isEmpty()){
                return false;
            }
            
            return true;
        }

    private void newPatientIdandDate(){
        
      d=new java.util.Date();
          SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
          String str=sdf.format(d);
        txtDate.setText(str);
        try{
            String str1=PatientDao.getNewId();
            txtPID.setText(str1);
            
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null,"Sql Exception in adding patient id");
       }

    
    }
            public String sendSms(){
                try{
            String apiKey="apiKey="+"8H84bYHjxx0-1wsrcYLk6T4AxhwTaeScq8xorBU6JP";
            String message="&message="+"your OTP is "+refs+" and Patient id: "+p_id+" from sanjeevani app ";
            String sender="&sender="+"TXTLCL";
            String numbers="&numbers="+mno;
            URL url=new URL("https://api.textlocal.in/send/?");
          HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
            String data = apiKey + numbers + message + sender;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }
            rd.close();
            return stringBuffer.toString();
        } catch (Exception e) {
            System.out.println("Error SMS "+e);
            return "Error "+e;
        }
        }
    public void addPatientDetails(){
            int ans ;
            int count=3;
            refs=1000+(int) (Math.random() * 28);
            String message=sendSms();
            System.out.println(message);
            if(message.contains("Invalid number")){
             JOptionPane.showMessageDialog(null, "please enter valid mobile no","Wrong",JOptionPane.ERROR_MESSAGE);
            return;
            }
            do{
                ans=Integer.parseInt(JOptionPane.showInputDialog(null,"Enter One time password","OTP",JOptionPane.INFORMATION_MESSAGE));
                if(ans==refs){
                 PatientPojo pp=new PatientPojo(opd,doctor_id,F_name,S_name,age,p_id,gender,m_status,address,city,mno,date);
                   try
               {
                   boolean result=PatientDao.addPatient(pp);
                   if(result)
                   {
                       JOptionPane.showMessageDialog(null, "Success!", "Appointment book successfully", JOptionPane.INFORMATION_MESSAGE);
                       ReceptionistOptionsFrame pof=new ReceptionistOptionsFrame();
                       pof.setVisible(true);
                       this.dispose();
                       break;
                   }
                   else
                   {
                       JOptionPane.showMessageDialog(null, "Failed", "somethimg wrong while inserting please try again later!", JOptionPane.ERROR_MESSAGE);
                       return;
                   }
               
               }
               catch(SQLException ex)
               {
                   ex.printStackTrace();
                   JOptionPane.showMessageDialog(null, "Exception", "Error in DB while inserting!", JOptionPane.ERROR_MESSAGE);
               }
                   
             }
           else
           {
               JOptionPane.showMessageDialog(null, "Please enter valid OTP", "wrong", JOptionPane.ERROR_MESSAGE);
               count--;
           }
       }while(count!=0);
   }
                        
        
        

    /**
     * Creates new form AddPatientFrame
     */
    public AddPatientFrame() {
        initComponents();
        loadDoctorId();
     newPatientIdandDate();
      this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        txtGreetings2 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        btnAddPatient = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtMno = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAddress = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtOpd = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtPID = new javax.swing.JTextField();
        txtDate = new javax.swing.JTextField();
        txtage = new javax.swing.JTextField();
        txtCity = new javax.swing.JTextField();
        jcDoctorId = new javax.swing.JComboBox<>();
        jcGender = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jcStatus = new javax.swing.JComboBox<>();
        jButton10 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtSName = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(AddPatientFrame.class.getResource("hms.png")));

        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel21.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(388, 260, -1, -1));

        jPanel23.setBackground(new java.awt.Color(51, 51, 51));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel23.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 160, 440));

        jLabel25.setBackground(new java.awt.Color(41, 57, 80));
        jLabel25.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("       Reception");
        jLabel25.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(41, 57, 80), null));
        jLabel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel25MouseEntered(evt);
            }
        });
        jPanel23.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 160, 50));

        jPanel21.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 660));

        jPanel24.setBackground(new java.awt.Color(71, 120, 198));

        jLabel22.setText("jLabel3");

        txtGreetings2.setBackground(new java.awt.Color(51, 51, 51));
        txtGreetings2.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        txtGreetings2.setForeground(new java.awt.Color(255, 255, 255));
        txtGreetings2.setText("                                       Add Patient");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addComponent(txtGreetings2, javax.swing.GroupLayout.PREFERRED_SIZE, 968, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGreetings2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel21.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 110));

        jPanel25.setBackground(new java.awt.Color(71, 120, 198));

        jPanel26.setBackground(new java.awt.Color(102, 124, 255));

        jPanel27.setBackground(new java.awt.Color(71, 110, 198));

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 538, Short.MAX_VALUE)
        );

        jButton3.setBackground(java.awt.Color.red);
        jButton3.setForeground(new java.awt.Color(51, 51, 51));
        jButton3.setText("Logout");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon("C:\\Users\\g\\Desktop\\lecture\\project java\\icon\\Receptionist.jpg")); // NOI18N

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jButton3))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel6)))
                .addGap(52, 52, 52)
                .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(220, 220, 220))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel21.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 180, 600));

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel21.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, 550, 60));

        jLabel24.setBackground(new java.awt.Color(41, 57, 80));
        jLabel24.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("      ADMIN");
        jLabel24.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(41, 57, 80), null));
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel24MouseEntered(evt);
            }
        });
        jPanel21.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 160, 50));

        btnAddPatient.setText("Add");
        btnAddPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPatientActionPerformed(evt);
            }
        });
        jPanel21.add(btnAddPatient, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 560, 80, 40));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("OPD");
        jPanel21.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, 50, 30));

        txtMno.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel21.add(txtMno, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 550, 120, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Age");
        jPanel21.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 340, 50, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("DoctorId");
        jPanel21.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 210, 90, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Patient Id");
        jPanel21.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 260, 90, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Ph NO");
        jPanel21.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 530, 70, 40));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("First Name");
        jPanel21.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 300, 100, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Gender");
        jPanel21.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 390, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Marital Status");
        jPanel21.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 390, 120, 30));

        txtAddress.setColumns(20);
        txtAddress.setRows(5);
        jScrollPane1.setViewportView(txtAddress);

        jPanel21.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 440, 360, 50));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Address");
        jPanel21.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 420, 80, 60));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("City");
        jPanel21.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 500, 50, 40));

        txtOpd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtOpd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOpdActionPerformed(evt);
            }
        });
        jPanel21.add(txtOpd, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 170, 120, 30));

        txtName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel21.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 310, 120, -1));

        txtPID.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel21.add(txtPID, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 270, 120, -1));

        txtDate.setEditable(false);
        jPanel21.add(txtDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 270, 130, -1));

        txtage.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel21.add(txtage, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 350, 120, -1));

        txtCity.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel21.add(txtCity, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 510, 120, -1));

        jcDoctorId.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel21.add(jcDoctorId, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 220, 120, -1));

        jcGender.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female", "Other" }));
        jPanel21.add(jcGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 390, 120, 20));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Date");
        jPanel21.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 270, -1, -1));

        jcStatus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Married", "UnMarried" }));
        jPanel21.add(jcStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 390, 120, 20));

        jButton10.setText("Back");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel21.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 560, 80, 40));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Last Name");
        jPanel21.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 310, -1, -1));
        jPanel21.add(txtSName, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 310, 130, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 1004, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 659, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel24MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel24MouseEntered

    private void btnAddPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPatientActionPerformed
 if(validateInput()==false)
       {
           JOptionPane.showMessageDialog(null, "Error", "please fill all field first!!",JOptionPane.ERROR_MESSAGE);
           return;
       }
       if(age<=0)
       {
           JOptionPane.showMessageDialog(null, "error", "please input valid age!!", JOptionPane.ERROR_MESSAGE);
           return;
           
       }
       addPatientDetails();
   




    }//GEN-LAST:event_btnAddPatientActionPerformed

    private void txtOpdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOpdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOpdActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
 ReceptionistOptionsFrame fr=new  ReceptionistOptionsFrame();
        fr.setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jLabel25MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel25MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel25MouseEntered

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddPatientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddPatientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddPatientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddPatientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddPatientFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddPatient;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcDoctorId;
    private javax.swing.JComboBox<String> jcGender;
    private javax.swing.JComboBox<String> jcStatus;
    private javax.swing.JTextArea txtAddress;
    private javax.swing.JTextField txtCity;
    private javax.swing.JTextField txtDate;
    private javax.swing.JLabel txtGreetings2;
    private javax.swing.JTextField txtMno;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtOpd;
    private javax.swing.JTextField txtPID;
    private javax.swing.JTextField txtSName;
    private javax.swing.JTextField txtage;
    // End of variables declaration//GEN-END:variables
}
