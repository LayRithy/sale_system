/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stockmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class SignUpScreen extends javax.swing.JFrame {

        String URL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=DB2";
        String user = "rithy";
        String pass = "241043";
         
        String sgender;
    
    //Constructor   
    public SignUpScreen() {
        initComponents();
        StaffAutoID();
        jLabel8.setVisible(false);
        
    }

    int lastid;
    //get auto id
    public void StaffAutoID(){
            try{
                    Connection con =  DriverManager.getConnection(URL , user , pass);
                    Statement st = con.createStatement();
            
                    String selectQuery = "SELECT max(StaffID) FROM Staff";
                    ResultSet rs = st.executeQuery(selectQuery);
                    //System.out.println(rs);
                    if(rs.next()){
                            System.out.println(rs.getInt(1));
                            lastid = rs.getInt(1);
                            System.out.println(lastid);
                            lastid++;
                        
                            signup_txtid.setText(Integer.toString(lastid));
                            
                    }
                
            }catch (Exception e) {
                  throw new Error("Problem", e);
                  //e.printStackTrace();
            }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        categoryno_textfield = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        jLabel2 = new javax.swing.JLabel();
        signup_txtname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        signup_txtpassword = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        signup_txtposition = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        signup_txtphone = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        signup_txtaddress = new javax.swing.JTextField();
        signup_btnsignup = new javax.swing.JButton();
        signup_lblsignin = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        signup_txtid = new javax.swing.JTextField();
        rdmale = new javax.swing.JRadioButton();
        rdfemale = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Full Name");

        categoryno_textfield.setBackground(new java.awt.Color(51, 51, 51));
        categoryno_textfield.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        categoryno_textfield.setForeground(new java.awt.Color(153, 153, 153));
        categoryno_textfield.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(380, 550));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setPreferredSize(new java.awt.Dimension(371, 500));

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));

        label1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        label1.setForeground(new java.awt.Color(255, 255, 255));
        label1.setText("Sign Up");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(128, 128, 128)
                .add(label1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(label1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Username");

        signup_txtname.setBackground(new java.awt.Color(51, 51, 51));
        signup_txtname.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        signup_txtname.setForeground(new java.awt.Color(153, 153, 153));
        signup_txtname.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Password");

        signup_txtpassword.setBackground(new java.awt.Color(51, 51, 51));
        signup_txtpassword.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        signup_txtpassword.setForeground(new java.awt.Color(153, 153, 153));
        signup_txtpassword.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Gender");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Position");

        signup_txtposition.setBackground(new java.awt.Color(51, 51, 51));
        signup_txtposition.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        signup_txtposition.setForeground(new java.awt.Color(153, 153, 153));
        signup_txtposition.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        signup_txtposition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signup_txtpositionActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Phone");

        signup_txtphone.setBackground(new java.awt.Color(51, 51, 51));
        signup_txtphone.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        signup_txtphone.setForeground(new java.awt.Color(153, 153, 153));
        signup_txtphone.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Address");

        signup_txtaddress.setBackground(new java.awt.Color(51, 51, 51));
        signup_txtaddress.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        signup_txtaddress.setForeground(new java.awt.Color(153, 153, 153));
        signup_txtaddress.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        signup_btnsignup.setBackground(new java.awt.Color(153, 153, 153));
        signup_btnsignup.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        signup_btnsignup.setForeground(new java.awt.Color(255, 255, 255));
        signup_btnsignup.setText("Sign Up");
        signup_btnsignup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signup_btnsignupActionPerformed(evt);
            }
        });

        signup_lblsignin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        signup_lblsignin.setForeground(new java.awt.Color(255, 255, 255));
        signup_lblsignin.setText("Admin");
        signup_lblsignin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                signup_lblsigninMouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("ID");

        signup_txtid.setBackground(new java.awt.Color(51, 51, 51));
        signup_txtid.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        signup_txtid.setForeground(new java.awt.Color(153, 153, 153));
        signup_txtid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        rdmale.setBackground(new java.awt.Color(51, 51, 51));
        rdmale.setForeground(new java.awt.Color(255, 255, 255));
        rdmale.setText("M");
        rdmale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdmaleActionPerformed(evt);
            }
        });

        rdfemale.setBackground(new java.awt.Color(51, 51, 51));
        rdfemale.setForeground(new java.awt.Color(255, 255, 255));
        rdfemale.setText("F");
        rdfemale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdfemaleActionPerformed(evt);
            }
        });

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Back");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(signup_lblsignin))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(42, 42, 42)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(signup_btnsignup, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(signup_txtid))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(signup_txtname))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(signup_txtpassword))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(signup_txtposition))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(signup_txtphone))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(signup_txtaddress))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(rdmale, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 79, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(rdfemale, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 93, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(62, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .add(0, 0, Short.MAX_VALUE)
                .add(jLabel8)
                .add(40, 40, 40))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel8)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 39, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel9)
                    .add(signup_txtid, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(signup_txtname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(signup_txtpassword, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(rdfemale)
                    .add(rdmale, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(signup_txtposition, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel6)
                    .add(signup_txtphone, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel7)
                    .add(signup_txtaddress, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(36, 36, 36)
                .add(signup_btnsignup, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 47, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(67, 67, 67)
                .add(signup_lblsignin)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 388, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void signup_lblsigninMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signup_lblsigninMouseClicked
        // TODO add your handling code here:
        
        AdminScreen as = new AdminScreen();
            as.setLocationRelativeTo(null);
            as.setVisible(true);
            this.setVisible(false);
    }//GEN-LAST:event_signup_lblsigninMouseClicked

    private void signup_txtpositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signup_txtpositionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_signup_txtpositionActionPerformed

    private void rdmaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdmaleActionPerformed
        // TODO add your handling code here:
        if(rdmale.isSelected()){
            rdfemale.setSelected(false);
        }
    }//GEN-LAST:event_rdmaleActionPerformed

    private void rdfemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdfemaleActionPerformed
        // TODO add your handling code here:
        if(rdfemale.isSelected()){
            rdmale.setSelected(false);
        }
    }//GEN-LAST:event_rdfemaleActionPerformed

    private void signup_btnsignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signup_btnsignupActionPerformed
        // TODO add your handling code here:
        
         try{
                                
            Connection con =  DriverManager.getConnection(URL , user , pass);
            
            Statement st=con.createStatement();  

            String sid = signup_txtid.getText();
            String sname = signup_txtname.getText();
            String spassword = signup_txtpassword.getText();
            
            if(rdmale.isSelected()){
                sgender = "M";
            }
            if(rdfemale.isSelected()){
                sgender = "F";
            }
            String sposition = signup_txtposition.getText();
            String sphone = signup_txtphone.getText();
            String saddress = signup_txtaddress.getText();
                  
            String insertQuery="insert into Staff (StaffID, StaffName, StaffPassword, StaffGender, StaffPosition, StaffPhone, StaffAddress) values (?, ?, ?, ?, ?, ?, ?)";
                  
            PreparedStatement pst = con.prepareStatement (insertQuery);
                  
            String selectQuery = "select * from Staff";
            ResultSet rs = st.executeQuery(selectQuery);
                  
            if(rs.next()){
                
                if(rdmale.isSelected() || rdfemale.isSelected()){
                
                    if(signup_txtid.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"ID is Empty.");
                    }else
                    if(signup_txtname.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"Username is Empty.");
                    }else
                    if(signup_txtpassword.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"Password is Empty.");
                    }else
                    if(signup_txtposition.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"Position is Empty.");
                    }else
                    if(signup_txtphone.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"Phone is Empty.");
                    }else
                    if(signup_txtaddress.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"Address is Empty.");
                    }else{
                    
                    int num=  JOptionPane.showConfirmDialog(null, "Are you sure 'Update'?");
           
                    if(num==0){
             
                        //LoginScreen ls = new LoginScreen();
                        //ls.setVisible(true);
                        //ls.setLocationRelativeTo(null);
                        this.setVisible(false);
                        
                        pst.setString(1, sid);
                        pst.setString(2, sname);
                        pst.setString(3, spassword);
                        pst.setString(4, sgender);
                        pst.setString(5, sposition);
                        pst.setString(6, sphone);
                        pst.setString(7, saddress);
                  
                        pst.executeUpdate();
                        
                        HomeScreen hs = new HomeScreen();
                        HomeScreen.Staff sf = hs.new Staff();
                        sf.clearStaffTableData();
                        sf.show_Staff();
                        
                        JOptionPane.showMessageDialog(null,"Insert Data successfully");
                    }else{
               
                    }
                        
                    }
                
                }else{
                    JOptionPane.showMessageDialog(null,"Gender is Empty.");
                }
                
                
            }else{
                JOptionPane.showMessageDialog(null,"Insert Data not successfully");

            }
         
        }catch (Exception e) {
            throw new Error("Problem", e);
        }
         
    }//GEN-LAST:event_signup_btnsignupActionPerformed

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        
        this.setVisible(false);
        
    }//GEN-LAST:event_jLabel8MouseClicked

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
            java.util.logging.Logger.getLogger(SignUpScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignUpScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignUpScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignUpScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignUpScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField categoryno_textfield;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private java.awt.Label label1;
    protected javax.swing.JRadioButton rdfemale;
    protected javax.swing.JRadioButton rdmale;
    private javax.swing.JButton signup_btnsignup;
    public javax.swing.JLabel signup_lblsignin;
    protected javax.swing.JTextField signup_txtaddress;
    public javax.swing.JTextField signup_txtid;
    public javax.swing.JTextField signup_txtname;
    protected javax.swing.JTextField signup_txtpassword;
    protected javax.swing.JTextField signup_txtphone;
    protected javax.swing.JTextField signup_txtposition;
    // End of variables declaration//GEN-END:variables

}
