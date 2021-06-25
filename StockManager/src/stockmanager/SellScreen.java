/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stockmanager;

import java.sql.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import static jdk.nashorn.internal.runtime.JSType.toInteger;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author ASUS
 */
public class SellScreen extends javax.swing.JFrame {

    /** Creates new form SellScreen */
    Connection con = null;
    PreparedStatement pst = null;
    Statement st = null;
    
    
    HomeScreen hs = new HomeScreen();
    
    ProductSell psl = new ProductSell();
    InvoiceDetail id = new InvoiceDetail();
    
    String cgender;
    
    
    public SellScreen() {
        initComponents();
        
        setLocationRelativeTo(null);
         
        customerAutoID();
        
        currentDate();
        
        invoiceAutoID();
        
        id.invoiceDetailAutoID();
        
    }

    
    
//inner class Product sell
class ProductSell{
        
        public void showProductSell(){
            try{
                            
                //Connection con =  DriverManager.getConnection(URL , user , pass);
                con =  DB_Connection.createConnection();
                Statement st=con.createStatement();  

                String selectQuery="SELECT * FROM Product";
                ResultSet rs = st.executeQuery(selectQuery);
                
                while(rs.next())  {
                                
                    String pNo = String.valueOf(rs.getInt("ProductNo"));
                    String pName = rs.getString("ProductName");
                    String cNo = String.valueOf(rs.getInt("CategoryNo"));
                    String pQuantity = rs.getString("Quantity");
                    //String pUnitPrice = String.valueOf(rs.getInt("UnitPrice"));
                    String pUnitPrice = rs.getString("UnitPrice");
                    
                            
                    String tbData [] = {pNo, pName, cNo, pQuantity, pUnitPrice};//add into table
                    DefaultTableModel tblModel = (DefaultTableModel)table_productsell.getModel();
                                    
                    tblModel.addRow(tbData);
                }
                        
            }catch (Exception e) {
                  throw new Error("Problem", e);
            }
        }
        
        
        //Clear in table product
        public void clearTableProductSell(){
            table_productsell.setModel(new DefaultTableModel (null, new String[] {"Product No", "Product Name ", "Category No", "Quantity", "Unit Price"}));
        }
        
        
        // Execute The Insert Update And Delete Querys
        public void executeSQlQuery_Category(String query, String message){
       
            try{
                //con = DriverManager.getConnection(URL , user , pass);
                con =  DB_Connection.createConnection();

                st = con.createStatement();

                //this codition if selected record on table (if select = 1)
                if((st.executeUpdate(query)) == 1){
               
                    DefaultTableModel model1 = (DefaultTableModel)table_productsell.getModel();
                    
                    //int num=  JOptionPane.showConfirmDialog(null, "Are you sure "+message+"?");
           
                    //if(num==0){
                        model1.setRowCount(0); //clear old data on table 
                        clearTableProductSell();
                        showProductSell();

                        //JOptionPane.showMessageDialog(null, "Data "+message+" Succefully");
                    //}else{

                    //}
         

                }else{//if table not select row
                    JOptionPane.showMessageDialog(null, "Data Not "+message);
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        
        
        //Update Product Sell
        public void updateProductSell(){
            String query = "UPDATE Product SET Quantity='"+text_minus.getText()+"' WHERE ProductNo = "+text_productNo.getText();
            executeSQlQuery_Category(query, "Updated");
        }
        
        
        //Add data into table Product_Add_Sell
        public void add_ProductAddSell(){
            String pName = text_productname.getText();
            String pName1 = text_productNo.getText();
            String data[] = {text_inDetail.getText(), text_cusID.getText(), iNo.getText(), pName, text_productquantity.getText(), text_producttotal.getText()};
            DefaultTableModel tblModel = (DefaultTableModel)table_addSell.getModel();
            if(pName == pName1){
                System.out.println("Please Update");
            }else{
                tblModel.addRow(data);
            }
            
                            
        }
        
}//end inner class 'ProductSell'
    

//inner class Invoice Detail
class InvoiceDetail{
        
        
        int lastid;
        //get auto id
        public void invoiceDetailAutoID(){
                try{
                        //Connection con =  DriverManager.getConnection(URL , user , pass);
                        con =  DB_Connection.createConnection();
                        Statement st = con.createStatement();

                        String selectQuery = "SELECT max(InDetail) FROM InvoiceDetail";
                        ResultSet rs = st.executeQuery(selectQuery);
                        //System.out.println(rs);
                        if(rs.next()){
                                //System.out.println(rs.getInt(1));
                                lastid = rs.getInt(1);
                                //System.out.println(lastid);
                                lastid++;

                                text_inDetail.setText(Integer.toString(lastid));
                                
                        }

                }catch (Exception e) {
                      throw new Error("Problem", e);
                      //e.printStackTrace();
                }
        }
        
        
        //add invoice detail into dataBase
        public void addInvoiceDetail(){
        
            DefaultTableModel tblModel = (DefaultTableModel)table_addSell.getModel();

            if(tblModel.getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Table Empty. ");
            }else{
                String inDetailID, cID, invoiceNo, productNo, saleQty, amount;

                try{

                    //con =  DriverManager.getConnection(URL , user , pass);
                    con =  DB_Connection.createConnection();
                    st=con.createStatement();

                    for(int i=0; i<tblModel.getRowCount(); i++){
                        //9invoiceDetailAutoID();
                        inDetailID = tblModel.getValueAt(i, 0).toString();
                        cID = tblModel.getValueAt(i, 1).toString();
                        invoiceNo = tblModel.getValueAt(i, 2).toString();
                        productNo = tblModel.getValueAt(i,3).toString();
                        saleQty = tblModel.getValueAt(i, 4).toString();
                        amount = tblModel.getValueAt(i, 5).toString();
                        
                        String query = "INSERT INTO InvoiceDetail VALUES(?, ?, ?, ?, ?, ?)";
                        pst = con.prepareStatement(query);
                       
                        pst.setString(1, inDetailID);
                        pst.setString(2, cID);
                        pst.setString(3, invoiceNo);
                        pst.setString(4, productNo);
                        pst.setString(5, saleQty);
                        pst.setString(6, amount);
                        
                        //invoiceDetailAutoID();
                        
                        pst.executeUpdate();
                        
                        try{
                            con =  DB_Connection.createConnection();
                            st=con.createStatement();
                            String selectQuery = "SELECT max(InDetail) FROM InvoiceDetail";
                            ResultSet rs = st.executeQuery(selectQuery);
                        }catch (Exception e) {
                            throw new Error("Problem", e);
                        }
                        
                    }

                }catch (Exception e) {
                    throw new Error("Problem", e);
                }
            }
        }
        
        //clear data in Table Invoice Detail
        public void clearRecordInDetail(){
            //clear all data in table
            table_addSell.setModel(new DefaultTableModel (null, new String[] {"ID", "CusID", "Invoice", "Product", "Quantity" ,"Amount"}));
    
        }
        
        
        //Calculate Discount, Tax, Total
        public void Cal_Discount_Tax_Total(){
            String subAmount1 = iSubAmount.getText();
            String discount1 = iDiscount.getText();
            String tax1 = iTax.getText();
        
       
            if(!subAmount1.isEmpty()){ //if Subamount have data
            
                Double subAmount = Double.parseDouble(iSubAmount.getText());
                System.out.println("Not empty");
            
                if(discount1.isEmpty()){//if discount text is empty
                    if((discount1.isEmpty() || tax1.isEmpty()) == true){
                        System.out.println("discount and tax empty");
                        String total = String.valueOf(subAmount);
                        iTotal.setText(total);

                        iDiscount.setText("0");
                        iTax.setText("0");

                    }
                    if(!tax1.isEmpty()){ //if tax text is not empty
                        System.out.println("discount and tax not empty");
                        Double tax = Double.parseDouble(iTax.getText());
                        String amountTax = String.valueOf(subAmount * (tax/100) );
                        text_amountTax.setText(amountTax);
                        Double resultAmountTax = Double.parseDouble(text_amountTax.getText());

                        String total = String.valueOf(resultAmountTax+subAmount);
                        iTotal.setText(total);

                    }
                
                }else if(tax1.isEmpty()){ //if tax text is empty
                    if((discount1.isEmpty() || tax1.isEmpty()) == true){
                        System.out.println("'tax' discount and tax empty");
                        String total = String.valueOf(subAmount);
                        iTotal.setText(total);

                        iTax.setText("0");

                    }
                    if(!discount1.isEmpty()){ //if discount text is not empty
                        System.out.println("'tax' discount not empty");
                        Double discount = Double.parseDouble(iDiscount.getText());
                        String amountDiscount = String.valueOf(subAmount * (discount/100) );
                        text_amountDiscount.setText(amountDiscount);
                        Double resultAmountDiscount = Double.parseDouble(text_amountDiscount.getText());

                        String total = String.valueOf(subAmount-resultAmountDiscount);
                        iTotal.setText(total);

                    }
                
                }else if((!tax1.isEmpty() || !discount1.isEmpty()) == true){  //if tax text and discount text is not empty
                    System.out.println("Not emapty all");
                    //find amount after tax
                    Double tax = Double.parseDouble(iTax.getText());
                    String amountTax = String.valueOf(subAmount * (tax/100) );
                    text_amountTax.setText(amountTax);
                    Double resultAmountTax = Double.parseDouble(text_amountTax.getText());

                    //find amount after discount
                    Double discount = Double.parseDouble(iDiscount.getText());
                    String amountDiscount = String.valueOf(subAmount * (discount/100) );
                    text_amountDiscount.setText(amountDiscount);
                    Double resultAmountDiscount = Double.parseDouble(text_amountDiscount.getText());


                    String total = String.valueOf(resultAmountTax+subAmount-resultAmountDiscount);
                    iTotal.setText(total);

                }
            
            }else if(subAmount1.isEmpty()){ //if subamount don't have data

                JOptionPane.showMessageDialog(null,"Please Select Product.");
            }
        }
        
        
        //find mnus that have after buy
        public void quantity_After_Buy(){
            
            int productQuantity = Integer.parseInt(text_productQuantity.getText());
            int productQuantitySell = Integer.parseInt(text_productquantity.getText());
            String totalMinus = String.valueOf(productQuantity - productQuantitySell);
            text_minus.setText(totalMinus);
        }
        
        
       //find Amount
        public void find_Amount(){
            
            Double unitPrice = Double.parseDouble(text_unitPrice.getText());
            Double quantitySell = Double.parseDouble(text_productquantity.getText());
            String amount = String.valueOf(unitPrice * quantitySell);
            text_producttotal.setText(amount);
        }
        
        
        //find sub Amount
        public void find_SubAmount(){
            
            double sum=0;
            for(int i=0; i<table_addSell.getRowCount(); i++){
                 sum= sum+Double.parseDouble(table_addSell.getValueAt(i,5).toString());
            }
            iSubAmount.setText(Double.toString(sum));
        }
        
}//end inner class InvoiceDetail
    
    
    
    public void setTextEmpty(){
        iSubAmount.setText("0");
        iDiscount.setText("0");
        iTax.setText("0");
        iTotal.setText("0");
    }
    
    
    int lastid;
    //get auto id
    public void customerAutoID(){
            try{
                    //Connection con =  DriverManager.getConnection(URL , user , pass);
                    con =  DB_Connection.createConnection();
                    Statement st = con.createStatement();
            
                    String selectQuery = "SELECT max(CusID) FROM Customer";
                    ResultSet rs = st.executeQuery(selectQuery);
                    //System.out.println(rs);
                    if(rs.next()){
                            //System.out.println(rs.getInt(1));
                            lastid = rs.getInt(1);
                            //System.out.println(lastid);
                            lastid++;
                        
                            cusNo.setText(Integer.toString(lastid));
                    }
                
            }catch (Exception e) {
                  throw new Error("Problem", e);
                  //e.printStackTrace();
            }
    }
    
    
    //get auto id
    public void invoiceAutoID(){
        try{
            //Connection con =  DriverManager.getConnection(URL , user , pass);
            con =  DB_Connection.createConnection();
            Statement st = con.createStatement();
            
            String selectQuery = "SELECT max(InvoiceNo) FROM Invoice";
            ResultSet rs = st.executeQuery(selectQuery);
                    
            if(rs.next()){
                lastid = rs.getInt(1);
                lastid++;
                iNo.setText(Integer.toString(lastid));
            }
                
        }catch (Exception e) {
            throw new Error("Problem", e);
        }
    }
    
    
    
//reset textfiled
    public void resetTextCustomer(){
        
        cusNo.setText("");
        text_cusName.setText("");
        cusPhone.setText("");
        cusAddress.setText("");
        
        customerAutoID();
        invoiceAutoID();
    }
    
    
    
    public void currentDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime ldt = LocalDateTime.now();
        iDate.setText(dtf.format(ldt));
    }
    
    
    //Add Update Delete into Database
    public void executeSQlQuery(String query, String message){
       
       try{
           //con = DriverManager.getConnection(URL , user , pass);
           con =  DB_Connection.createConnection();
           
           st = con.createStatement();
            
           //this codition if selected record on table (if select = 1)
           if((st.executeUpdate(query)) == 1){
               
                // refresh jtable data
                DefaultTableModel model1 = (DefaultTableModel)table_productsell.getModel();
                
                
                int num=  JOptionPane.showConfirmDialog(null, "Are you sure "+message+"?");
           
                if(num==0){
                    //HomeScreen hs = new OuterClass();
                    HomeScreen.Product pd = hs.new Product();
                    model1.setRowCount(0); //clear old data on table 
                    pd.clearProductTableData();
                    pd.show_Product();
                    
                    
                    //txtcategoryname.setText(" ");
                
                    JOptionPane.showMessageDialog(null, "Data "+message+" Succefully");
                }else{
               
                }
         
                
           }else{//if table not select row
               JOptionPane.showMessageDialog(null, "Data Not "+message);
           }
       }catch(Exception ex){
           ex.printStackTrace();
       }
   }
    
    
    //Calculate Total Amount
    public void getSum(){
        
        double sum=0;
        for(int i=0; i<table_addSell.getRowCount(); i++){
            sum= sum+Double.parseDouble(table_addSell.getValueAt(i, 2).toString());
        }
        iSubAmount.setText(Double.toString(sum));
        
    }
    
   
    //add to Invoice
    public void addIntoInvoice(){
        
        try{

            con =  DB_Connection.createConnection();
            st=con.createStatement();

            String invoiceNo = iNo.getText();
            String invoiceDate = iDate.getText();
            String subAmount = iSubAmount.getText();
            String discount = iDiscount.getText();
            String tax = iTax.getText();
            String total = iTotal.getText();
            String staffID = staffId.getText();
            String cusID = text_cusID.getText();

            String insertQuery="INSERT INTO Invoice VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            pst = con.prepareStatement (insertQuery);

            pst.setString(1, invoiceNo);
            pst.setString(2, invoiceDate);
            pst.setString(3, cusID);
            pst.setString(4, staffID);
            pst.setString(5, subAmount);
            pst.setString(6, discount);
            pst.setString(7, tax);
            pst.setString(8, total);
            
            pst.executeUpdate();
            invoiceAutoID();
            //JOptionPane.showMessageDialog(null,"Insert Invoice Data successfully");

            //customerAutoID();

        }catch (Exception e) {
            throw new Error("Problem", e);
        }
    }
    
    
    //add to Customer
    public void addIntoCustomer(){
        
        try{
                            
            con =  DB_Connection.createConnection();
            st=con.createStatement();  

            String cid = cusNo.getText();
            String cname = text_cusName.getText();
            if(rbmale.isSelected()){
                cgender = "M";
            }
            if(rbfemale.isSelected()){
                cgender = "F";
            }
            String cphone = cusPhone.getText();
            String caddress = cusAddress.getText();
            
            String insertQuery="insert into Customer (CusID, CusName, CusGender, CusPhone, CusAddress) values (?, ?, ?, ?, ?)";
            pst = con.prepareStatement (insertQuery);
              
            //use to check codition 'if data already have is true' 
            if(iSubAmount.getText().isEmpty() ){
                
            }else{      
               
                try{
                    con =  DB_Connection.createConnection();
                    st=con.createStatement(); 
                    String selectCusNoQuery = "select CusID from Customer WHERE CusID ="+text_cusID.getText();
                    ResultSet rs = st.executeQuery(selectCusNoQuery);
                    
                    if(rs.next()){
                        //JOptionPane.showMessageDialog(null,"11111. Old User.");
                    }else{
                        //JOptionPane.showMessageDialog(null,"1 New User.");
                        pst.setString(1, cid);
                        pst.setString(2, cname);
                        pst.setString(3, cgender);
                        pst.setString(4, cphone);
                        pst.setString(5, caddress);

                        pst.executeUpdate();
                    }
                }catch (Exception e) {
                    throw new Error("Problem", e);
                }
                
            }
                  
        }catch (Exception e) {
            throw new Error("Problem", e);
        }
    }
    
    
    //public all text
    public void resetAllText(){
        text_productname.setText("");
        text_productquantity.setText("");
        text_producttotal.setText("0");
        iSubAmount.setText("");
        iDiscount.setText("");
        iTax.setText("");
        iTotal.setText("");
        
        id.clearRecordInDetail();
        
        resetTextCustomer();
        
        text_cusID.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel26 = new javax.swing.JPanel();
        historysearch_textfield1 = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        text_cusName = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        cusPhone = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        cusAddress = new javax.swing.JTextField();
        btn_addCustomer = new javax.swing.JButton();
        btn_resetTextCustomer = new javax.swing.JButton();
        jLabel56 = new javax.swing.JLabel();
        cusNo = new javax.swing.JTextField();
        rbmale = new javax.swing.JRadioButton();
        rbfemale = new javax.swing.JRadioButton();
        btn_lagOut = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        staff_namelbl = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        table_productsell = new javax.swing.JTable();
        button_finish = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        iNo = new javax.swing.JTextField();
        iDate = new javax.swing.JTextField();
        text_cusID = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        iDiscount = new java.awt.TextField();
        iTax = new java.awt.TextField();
        iSubAmount = new java.awt.TextField();
        iTotal = new java.awt.TextField();
        jPanel4 = new javax.swing.JPanel();
        textField3 = new java.awt.TextField();
        textTamporary_amount = new java.awt.TextField();
        text_discount = new javax.swing.JTextField();
        text_tax = new javax.swing.JTextField();
        text_amountTax = new javax.swing.JTextField();
        text_amountDiscount = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        text_productQuantity = new javax.swing.JTextField();
        text_productSellQuantity = new javax.swing.JTextField();
        text_minus = new javax.swing.JTextField();
        text_productNo = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        text_productquantity = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        text_producttotal = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        text_productname = new javax.swing.JTextField();
        btn_addSell = new javax.swing.JButton();
        btn_updateSell = new javax.swing.JButton();
        btn_deleteSell = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_addSell = new javax.swing.JTable();
        text_unitPrice = new javax.swing.JTextField();
        text_inDetail = new javax.swing.JTextField();
        text_addSellQuantity = new javax.swing.JTextField();
        staffId = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(100, 100));
        setPreferredSize(new java.awt.Dimension(1200, 600));
        setResizable(false);

        jPanel26.setBackground(new java.awt.Color(204, 204, 204));
        jPanel26.setPreferredSize(new java.awt.Dimension(162, 590));

        historysearch_textfield1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        historysearch_textfield1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        historysearch_textfield1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                historysearch_textfield1KeyReleased(evt);
            }
        });

        jLabel45.setText("Search");

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel46.setText("Cus Name :");

        text_cusName.setBackground(new java.awt.Color(204, 204, 204));
        text_cusName.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        text_cusName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel47.setText("Cus Gender :");

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel48.setText("Cus Phone :");

        cusPhone.setBackground(new java.awt.Color(204, 204, 204));
        cusPhone.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cusPhone.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel49.setText("Cus Address :");

        cusAddress.setBackground(new java.awt.Color(204, 204, 204));
        cusAddress.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cusAddress.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        btn_addCustomer.setBackground(new java.awt.Color(102, 102, 102));
        btn_addCustomer.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btn_addCustomer.setForeground(new java.awt.Color(255, 255, 255));
        btn_addCustomer.setText("Add");
        btn_addCustomer.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        btn_addCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addCustomerActionPerformed(evt);
            }
        });

        btn_resetTextCustomer.setBackground(new java.awt.Color(102, 102, 102));
        btn_resetTextCustomer.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btn_resetTextCustomer.setForeground(new java.awt.Color(255, 255, 255));
        btn_resetTextCustomer.setText("Reset");
        btn_resetTextCustomer.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        btn_resetTextCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetTextCustomerActionPerformed(evt);
            }
        });

        jLabel56.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel56.setText("Cus No :");

        cusNo.setBackground(new java.awt.Color(204, 204, 204));
        cusNo.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cusNo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        rbmale.setBackground(new java.awt.Color(204, 204, 204));
        rbmale.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rbmale.setText("Male");
        rbmale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbmaleActionPerformed(evt);
            }
        });

        rbfemale.setBackground(new java.awt.Color(204, 204, 204));
        rbfemale.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rbfemale.setText("Female");
        rbfemale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbfemaleActionPerformed(evt);
            }
        });

        btn_lagOut.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        btn_lagOut.setText("Log Out");
        btn_lagOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_lagOutMouseClicked(evt);
            }
        });

        jLabel1.setText("Staff:");

        staff_namelbl.setText("ssd");

        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(staff_namelbl, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 225, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(188, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(staff_namelbl, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout jPanel26Layout = new org.jdesktop.layout.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel26Layout.createSequentialGroup()
                .add(jPanel26Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel26Layout.createSequentialGroup()
                        .add(15, 15, 15)
                        .add(jPanel26Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel26Layout.createSequentialGroup()
                                .add(jLabel45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(6, 6, 6)
                                .add(historysearch_textfield1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 210, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jPanel26Layout.createSequentialGroup()
                                .add(jLabel56)
                                .add(52, 52, 52)
                                .add(cusNo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 150, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jPanel26Layout.createSequentialGroup()
                                .add(jLabel46)
                                .add(31, 31, 31)
                                .add(text_cusName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 150, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jPanel26Layout.createSequentialGroup()
                                .add(jLabel48)
                                .add(30, 30, 30)
                                .add(cusPhone, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 150, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jPanel26Layout.createSequentialGroup()
                                .add(jLabel49)
                                .add(18, 18, 18)
                                .add(cusAddress, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 150, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jPanel26Layout.createSequentialGroup()
                                .add(jLabel47)
                                .add(16, 16, 16)
                                .add(jPanel26Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(rbmale)
                                    .add(jPanel26Layout.createSequentialGroup()
                                        .add(70, 70, 70)
                                        .add(rbfemale))))
                            .add(jPanel26Layout.createSequentialGroup()
                                .add(8, 8, 8)
                                .add(jPanel26Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(btn_addCustomer, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 252, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(btn_resetTextCustomer, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 252, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                    .add(jPanel26Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(btn_lagOut))
                    .add(jPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel26Layout.createSequentialGroup()
                .add(jPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(27, 27, 27)
                .add(jPanel26Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel26Layout.createSequentialGroup()
                        .add(5, 5, 5)
                        .add(jLabel45))
                    .add(historysearch_textfield1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 32, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(35, 35, 35)
                .add(jPanel26Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel56)
                    .add(cusNo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel26Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel46)
                    .add(text_cusName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(23, 23, 23)
                .add(jPanel26Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel47)
                    .add(rbmale)
                    .add(rbfemale))
                .add(13, 13, 13)
                .add(jPanel26Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel48)
                    .add(cusPhone, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel26Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel49)
                    .add(cusAddress, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(49, 49, 49)
                .add(btn_addCustomer, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 42, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(btn_resetTextCustomer, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 42, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(78, 78, 78)
                .add(btn_lagOut)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        table_productsell.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product No", "Product Name", "Category No", "Quantity", "Unit Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_productsell.setAlignmentX(0.8F);
        table_productsell.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_productsellMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(table_productsell);

        button_finish.setBackground(new java.awt.Color(102, 102, 102));
        button_finish.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        button_finish.setForeground(new java.awt.Color(255, 255, 255));
        button_finish.setText("Finish");
        button_finish.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        button_finish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_finishActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel67.setText("Invoice No :");

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel65.setText("Date :");

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel58.setText("Customer ID:");

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel60.setText("Name :");

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel59.setText("Address :");

        iNo.setEditable(false);
        iNo.setBackground(new java.awt.Color(153, 153, 153));
        iNo.setText("jTextField5");
        iNo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)));

        iDate.setBackground(new java.awt.Color(153, 153, 153));
        iDate.setText("jTextField6");
        iDate.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)));

        text_cusID.setBackground(new java.awt.Color(153, 153, 153));
        text_cusID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)));

        jTextField2.setBackground(new java.awt.Color(153, 153, 153));
        jTextField2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)));

        jTextField3.setBackground(new java.awt.Color(153, 153, 153));
        jTextField3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)));

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(45, 45, 45)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jLabel58)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(text_cusID, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 93, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                        .add(jPanel2Layout.createSequentialGroup()
                            .add(jLabel59)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 103, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jLabel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .add(jPanel2Layout.createSequentialGroup()
                            .add(jLabel60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 109, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 58, Short.MAX_VALUE)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jLabel65)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(iDate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jLabel67)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(iNo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel67)
                            .add(jLabel4)
                            .add(iNo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(text_cusID, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(5, 5, 5))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                        .add(jLabel58)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)))
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel65)
                    .add(jLabel60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel5)
                    .add(iDate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jLabel6)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel59)
                    .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(11, 11, 11))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel54.setText("Total ($) :");

        jLabel53.setText("Tax (%):");

        jLabel50.setText("Sub Amount ($) :");

        jLabel52.setText("Discount (%) :");

        iDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iDiscountActionPerformed(evt);
            }
        });

        iSubAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iSubAmountActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(iSubAmount, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 110, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel52, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 111, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(iDiscount, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(42, 42, 42)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(iTax, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel53, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 83, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 37, Short.MAX_VALUE)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(iTotal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel54))
                .add(24, 24, 24))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel50)
                    .add(jLabel52)
                    .add(jLabel53)
                    .add(jLabel54))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(iDiscount, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(iSubAmount, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(1, 1, 1)
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(iTotal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(iTax, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 517, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 52, Short.MAX_VALUE)
        );

        textField3.setText("textField3");

        textTamporary_amount.setText("Amount");

        text_discount.setText("discount");

        text_tax.setText("Tax");

        text_amountTax.setText("amountTax");

        text_amountDiscount.setText("amountDiscount");

        jButton2.setText("Delete");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel7.setText("Invoice");

        jTextField5.setText("2");

        text_productQuantity.setText("ProductQuantity");

        text_productSellQuantity.setText("ProductSellQuantity");

        text_minus.setText("minus");

        text_productNo.setText("ProductSellNo");

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel61.setText("Quantity:");

        text_productquantity.setBackground(new java.awt.Color(204, 204, 204));
        text_productquantity.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        text_productquantity.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        text_productquantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                text_productquantityKeyPressed(evt);
            }
        });

        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel62.setText("Total:");

        text_producttotal.setBackground(new java.awt.Color(204, 204, 204));
        text_producttotal.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        text_producttotal.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)));

        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel57.setText("Product:");

        text_productname.setBackground(new java.awt.Color(204, 204, 204));
        text_productname.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        text_productname.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)));

        btn_addSell.setText("Add");
        btn_addSell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addSellActionPerformed(evt);
            }
        });

        btn_updateSell.setText("Update");
        btn_updateSell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateSellActionPerformed(evt);
            }
        });

        btn_deleteSell.setText("Delete");
        btn_deleteSell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteSellActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(jLabel57)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(text_productname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 341, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(btn_addSell)
                        .add(18, 18, 18)
                        .add(btn_updateSell)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(btn_deleteSell))
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(jLabel61)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(text_productquantity, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 58, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(27, 27, 27)
                        .add(jLabel62)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(text_producttotal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 81, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(139, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel57)
                    .add(text_productname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel61)
                    .add(text_productquantity, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel62)
                    .add(text_producttotal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 23, Short.MAX_VALUE)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btn_addSell)
                    .add(btn_updateSell)
                    .add(btn_deleteSell)))
        );

        table_addSell.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CusID", "Invoice", "Product", "Quantity", "Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_addSell.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_addSellMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(table_addSell);
        if (table_addSell.getColumnModel().getColumnCount() > 0) {
            table_addSell.getColumnModel().getColumn(0).setResizable(false);
        }

        text_unitPrice.setText("unitprice");

        text_inDetail.setText("invoiceDetailID");

        text_addSellQuantity.setText("addsellQuantity");

        staffId.setText("3");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(240, 240, 240)
                        .add(jLabel7))
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(26, 26, 26)
                                .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                .add(jPanel1Layout.createSequentialGroup()
                                    .add(jScrollPane5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 502, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(18, 18, 18)
                                    .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 355, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                                    .add(textTamporary_amount, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(42, 42, 42)
                                    .add(textField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(33, 33, 33)
                                    .add(text_discount, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(37, 37, 37)
                                    .add(text_tax, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(36, 36, 36)
                                    .add(text_amountTax, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 99, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(33, 33, 33)
                                    .add(text_amountDiscount, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(18, 18, 18)
                                    .add(jButton2))
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                                    .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(95, 95, 95)
                                    .add(button_finish, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 115, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(jTextField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(staffId, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                    .add(36, 36, 36)
                                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(text_unitPrice, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(jPanel1Layout.createSequentialGroup()
                                            .add(text_productQuantity, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(text_productSellQuantity, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                            .add(text_minus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(18, 18, 18)
                                            .add(text_productNo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(46, 46, 46)
                                            .add(text_inDetail, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(text_addSellQuantity, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 1289, Short.MAX_VALUE)
                .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(4, 4, 4)
                .add(jLabel7)
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(65, 65, 65)
                        .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .add(jScrollPane5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(19, 19, 19)
                        .add(button_finish, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                        .add(textField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(textTamporary_amount, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(text_discount, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(text_tax, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(text_amountTax, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(text_amountDiscount, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jButton2)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(text_inDetail, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jTextField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(text_productQuantity, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(text_productSellQuantity, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(text_minus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(text_productNo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(text_addSellQuantity, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(staffId, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(text_unitPrice, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(440, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 287, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel26, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1125, Short.MAX_VALUE))
                .add(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void iDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iDiscountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_iDiscountActionPerformed

    private void btn_addCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addCustomerActionPerformed
        
        String cid = cusNo.getText();
        String cname = text_cusName.getText();
        String caddress = cusAddress.getText();
            
        try{

            con =  DB_Connection.createConnection();
            st=con.createStatement();

            String selectCusNameQuery = "select CusID from Customer WHERE CusName = '"+text_cusName.getText()+"' AND CusPhone= '"+cusPhone.getText()+"' ";
            ResultSet rs = st.executeQuery(selectCusNameQuery);

            if(rs.next()){
                JOptionPane.showMessageDialog(null,"Old Customer.");
                String cno = rs.getString("CusID");
                text_cusID.setText(cno); 
                jTextField2.setText(cname);
                jTextField3.setText(caddress);
            }else{
                JOptionPane.showMessageDialog(null,"New Customer.");
                text_cusID.setText(cid); 
                jTextField2.setText(cname);
                jTextField3.setText(caddress);
            }

        }catch (Exception e) {
            throw new Error("Problem", e);
        }
            
            
        
                
    }//GEN-LAST:event_btn_addCustomerActionPerformed

    private void rbmaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbmaleActionPerformed
        // TODO add your handling code here:
        if(rbmale.isSelected()){
            rbfemale.setSelected(false);
        }
    }//GEN-LAST:event_rbmaleActionPerformed

    private void rbfemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbfemaleActionPerformed
        // TODO add your handling code here:
        if(rbfemale.isSelected()){
            rbmale.setSelected(false);
        }
    }//GEN-LAST:event_rbfemaleActionPerformed

    private void btn_resetTextCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetTextCustomerActionPerformed
        // TODO add your handling code here:
        resetTextCustomer();
    }//GEN-LAST:event_btn_resetTextCustomerActionPerformed

    private void btn_addSellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addSellActionPerformed

        // TODO add your handling code here:
        
        String pname = text_productname.getText();
        String pQuantity = text_productquantity.getText();
        String pTotal = text_producttotal.getText();
        String cName = text_cusID.getText();
        
        if(pname.isEmpty()){
            JOptionPane.showMessageDialog(null,"Please Select Product ");
        }else if(cName.isEmpty()){
            JOptionPane.showMessageDialog(null,"Please Select Customer ");
        }else{
            
            //find quantity after buy
            id.quantity_After_Buy();

            //find Amount
            id.find_Amount();
            
            
            int pQuantity1 = Integer.parseInt(text_productQuantity.getText());
            
            if(pQuantity1<=0){
                JOptionPane.showMessageDialog(null,"Product Quantity is Empty.");
            }else{
                
                //JOptionPane.showMessageDialog(null,"Add Sell Product.");
                    
                //Add data into table product add sell
                try{
                    con =  DB_Connection.createConnection();
                    st=con.createStatement();

                    String selectValue = text_productname.getText();
                    String selectQuery = "select ProductNo from Product WHERE ProductName='" +selectValue+ "' ";
                    ResultSet rs = st.executeQuery(selectQuery);

                    if(rs.next()){
                        String pno = rs.getString("ProductNo");
                        text_productname.setText(pno);
                        
                        
                        //add data into table product sell
                        if(text_productname.getText().isEmpty() || text_productquantity.getText().isEmpty() || text_producttotal.getText().isEmpty()){

                        }else{
                            
                            //add data in table Product_Add_Sell
                            psl.add_ProductAddSell();
                            
                            
                            //Update product in to database
                            psl.updateProductSell();

                            
                            //find SubAmount
                            id.find_SubAmount();
             
                            
                            //Increment ID Invoice detail
                            int i=1;
                            int tax = Integer.parseInt(text_inDetail.getText());
                            String amountTax = String.valueOf(tax+i);
                            text_inDetail.setText(amountTax);
                            
                            JOptionPane.showMessageDialog(null,"Add Product Sell.");
                        }

                    }else{
                        JOptionPane.showMessageDialog(null," Don't Have");
                    }

                }catch (Exception e) {
                    throw new Error("Problem", e);
                }
                
                //resetAllText();
                text_productname.setText("");
                text_productquantity.setText("");
                text_producttotal.setText("0");
                
                
            }//end if
            
        }//end if
    
    }//GEN-LAST:event_btn_addSellActionPerformed

    //use for search in sell
    public ArrayList<Product> ListProducts(String valToSearch){
        ArrayList<Product> productList = new ArrayList<Product>();
        
        
        Statement st;
        ResultSet rs;
        try{
            Connection con = DB_Connection.createConnection();
            st = con.createStatement();
            String searchQuery = "SELECT * FROM Product WHERE CONCAT(ProductNo, ProductName, CategoryNo, UnitPrice, Quantity)LIKE '%"+valToSearch+"%'";
            rs = st.executeQuery(searchQuery);
            
            Product product;
            
            while(rs.next()){
                product = new Product(
                        rs.getInt("ProductNo"),
                        rs.getString("ProductName"),
                        rs.getInt("CategoryNo"),
                        rs.getString("UnitPrice"),
                        rs.getString("SellingPrice"),
                        rs.getInt("Quantity"),
                        rs.getDate("ReceivingDate"),
                        rs.getDate("Expire_Date")
                );
                productList.add(product);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return productList;
    }
    
    public void findProduct(){
        ArrayList<Product> product = ListProducts(historysearch_textfield1.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[] {"Product No", "Product Name", "Category No", "Quantity", "Unit Price"} );
        
        Object[] row = new Object[5];
        for(int i=0; i<product.size(); i++){
            row[0] = product.get(i).getID();
            row[1] = product.get(i).getName();
            row[2] = product.get(i).getNo();
            row[3] = product.get(i).getQuantity();
            row[4] = product.get(i).getUnitPrice();
            
            model.addRow(row);
            
        }
        table_productsell.setModel(model);
    }
    
    private void historysearch_textfield1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_historysearch_textfield1KeyReleased
        // TODO add your handling code here:
        findProduct();
        
    }//GEN-LAST:event_historysearch_textfield1KeyReleased

    private void btn_lagOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_lagOutMouseClicked
        // TODO add your handling code here:
        
        int num=  JOptionPane.showConfirmDialog(null, "Are you Login?");
           
        if(num==0){
             
            LoginScreen ls = new LoginScreen();
            ls.setVisible(true);
            ls.setLocationRelativeTo(null);
            setVisible(false);
                    
        }else{
               
        }
    }//GEN-LAST:event_btn_lagOutMouseClicked

    private void button_finishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_finishActionPerformed
        // TODO add your handling code here:
        
        String cusID = text_cusID.getText();
        String pSubAmount = iSubAmount.getText();
        
        if(cusID.isEmpty() || pSubAmount.isEmpty() ){//if CusID Textfield is Empty
            JOptionPane.showMessageDialog(this, "Please Complete Customer and Product.");
            
        }else{
            int num=  JOptionPane.showConfirmDialog(null, "Are you sure 'Finish'?");
            if(num==0){
                id.Cal_Discount_Tax_Total(); //calculate TAX, DISCOUNT, TOTAL in Invoce Detail
                addIntoCustomer(); //add customer in to database
                addIntoInvoice(); //add invoice into database
                id.addInvoiceDetail(); //add invoice detail into database
                JOptionPane.showMessageDialog(this, "Finish.");
                resetAllText();
            }
        }
       
    }//GEN-LAST:event_button_finishActionPerformed

    
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void iSubAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iSubAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_iSubAmountActionPerformed

    private void table_productsellMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_productsellMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) table_productsell.getModel();
        int selectedRow = table_productsell.getSelectedRow();

       text_productNo.setText(model.getValueAt(selectedRow, 0).toString());
       text_productname.setText(model.getValueAt(selectedRow, 1).toString());
       text_productQuantity.setText(model.getValueAt(selectedRow, 3).toString());
       text_unitPrice.setText(model.getValueAt(selectedRow, 4).toString());
       
       text_productquantity.setText("");
       text_producttotal.setText("0");
       
        
    }//GEN-LAST:event_table_productsellMouseClicked

    private void table_addSellMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_addSellMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) table_addSell.getModel();
        int selectedRow = table_addSell.getSelectedRow();

        text_productNo.setText(model.getValueAt(selectedRow, 3).toString());
        text_productquantity.setText(model.getValueAt(selectedRow, 4).toString());
        text_producttotal.setText(model.getValueAt(selectedRow, 5).toString());
        
        textTamporary_amount.setText(model.getValueAt(selectedRow, 5).toString());
        
        try{

            con =  DB_Connection.createConnection();
            st=con.createStatement();
            
            String selectQuery = "select ProductName from Product WHERE ProductNo="+text_productNo.getText() ;
            ResultSet rs = st.executeQuery(selectQuery);

            if(rs.next()){
                String selectProductName = rs.getString("ProductName");
                text_productname.setText(selectProductName);

            }else{
                String selectProductName = rs.getString("ProductName");
                text_productname.setText(selectProductName);
                //JOptionPane.showMessageDialog(null," Don't Have");

            }

        }catch (Exception e) {
            throw new Error("Problem", e);
        }
        
        
        
        
    }//GEN-LAST:event_table_addSellMouseClicked

    private void btn_updateSellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateSellActionPerformed
        // TODO add your handling code here:
        
        if(table_addSell.getSelectedRow()==0){
            
            //find mnus that have after buy
            int productQuantity = Integer.parseInt(text_productQuantity.getText());
            int productQuantitySell = Integer.parseInt(text_productquantity.getText());
            String totalMinus = String.valueOf(productQuantity - productQuantitySell);
            text_minus.setText(totalMinus);
       
        
            //find amount
            Double unitPrice = Double.parseDouble(text_unitPrice.getText());
            Double quantitySell = Double.parseDouble(text_productquantity.getText());
            String amount = String.valueOf(unitPrice * quantitySell);
            text_producttotal.setText(amount);
        
        
            //Add data into table product add sell
            try{

                //con =  DriverManager.getConnection(URL , user , pass);
                con =  DB_Connection.createConnection();
                st=con.createStatement();

                String selectValue = text_productname.getText();
                String selectQuery = "select ProductNo from Product WHERE ProductName='" +selectValue+ "' ";
                ResultSet rs = st.executeQuery(selectQuery);

                if(rs.next()){
                    String pno = rs.getString("ProductNo");
                    text_productname.setText(pno);

                    //add data into table product sell
                    if(text_productname.getText().isEmpty() || text_productquantity.getText().isEmpty() || text_producttotal.getText().isEmpty()){

                    }else{
                    
                        //Update Table Add sell
                        DefaultTableModel tblModel = (DefaultTableModel)table_addSell.getModel();
                        if(table_addSell.getSelectedRowCount()==1){
                            String product = text_productname.getText();
                            String quantity = text_productquantity.getText();
                            String total = text_producttotal.getText();

                            tblModel.setValueAt(product, table_addSell.getSelectedRow(), 3 );
                            tblModel.setValueAt(quantity, table_addSell.getSelectedRow(), 4 );
                            tblModel.setValueAt(total, table_addSell.getSelectedRow(), 5 );
                        }


                        //Increment ID Invoice detail
                        int i=1;
                        int tax = Integer.parseInt(text_inDetail.getText());
                        String amountTax = String.valueOf(tax+i);
                        text_inDetail.setText(amountTax);
                    }
                
                }else{

                    JOptionPane.showMessageDialog(null," Don't Have");

                }
                //id.invoiceDetailAutoID();

            }catch (Exception e) {
                throw new Error("Problem", e);
            }
        
        
            //Find subAmount
            double sum=0;
            for(int i=0; i<table_addSell.getRowCount(); i++){
                sum= sum+Double.parseDouble(table_addSell.getValueAt(i,5).toString());
            }
            iSubAmount.setText(Double.toString(sum));


            //Update product in to database
            psl.updateProductSell();


            text_productname.setText("");
            text_productquantity.setText("");
            text_producttotal.setText("0");
            
        }else{
            if(table_addSell.getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Table is Empty.");
            }else{
                JOptionPane.showMessageDialog(null,"Please Selelct a signle row.");
            }
        }
        
    }//GEN-LAST:event_btn_updateSellActionPerformed

    private void btn_deleteSellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteSellActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tblModel = (DefaultTableModel)table_addSell.getModel();
        
        if(table_addSell.getSelectedRowCount()==1){
            int num = JOptionPane.showConfirmDialog(this,"Delete Record.");
            if(num==1){
                
            }else{
                //delete row in table
                tblModel.removeRow(table_addSell.getSelectedRow());
                
                //calculate value of row
                try{

                    con =  DB_Connection.createConnection();
                    st=con.createStatement();

                    String selectQuery = "select Quantity from Product WHERE ProductNo="+text_productNo.getText() ;
                    ResultSet rs = st.executeQuery(selectQuery);

                    if(rs.next()){
                        
                        //get product quantity
                        String selectProductName = rs.getString("Quantity");
                        textTamporary_amount.setText(selectProductName);
                        
                        //find mnus that have after buy
                        int productQuantity = Integer.parseInt(textTamporary_amount.getText());
                        int quantityAdd = Integer.parseInt(text_productquantity.getText());
                        String totalMinus = String.valueOf(productQuantity + quantityAdd);
                        text_minus.setText(totalMinus);

                        //Update product in to database
                        psl.updateProductSell();
                    
                        resetAllText();
                        
                    }else{
                        String selectProductName = rs.getString("ProductName");
                        text_productname.setText(selectProductName);
                        //JOptionPane.showMessageDialog(null," Don't Have");

                    }

                }catch (Exception e) {
                    throw new Error("Problem", e);
                }
        
            }
            
            
        }else{
            if(table_addSell.getRowCount()==1){
                JOptionPane.showMessageDialog(null,"Table is Empty..");
            }else{
                JOptionPane.showMessageDialog(null,"Please Selelct a signle row.");
            }
        }
    }//GEN-LAST:event_btn_deleteSellActionPerformed

    private void text_productquantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_text_productquantityKeyPressed
        // TODO add your handling code here:
        
        char c = evt.getKeyChar();
        if(Character.isLetter(c)){
            int num=JOptionPane.showConfirmDialog(this,"Please Input Number Only.");
            if(num==0){
                text_productquantity.setText("");
            }
        }else{
            //JOptionPane.showConfirmDialog(this,"Please Input Number Only.");
        }
    }//GEN-LAST:event_text_productquantityKeyPressed

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
            java.util.logging.Logger.getLogger(SellScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SellScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SellScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SellScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SellScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_addCustomer;
    private javax.swing.JButton btn_addSell;
    private javax.swing.JButton btn_deleteSell;
    private javax.swing.JLabel btn_lagOut;
    private javax.swing.JButton btn_resetTextCustomer;
    private javax.swing.JButton btn_updateSell;
    private javax.swing.JButton button_finish;
    private javax.swing.JTextField cusAddress;
    private javax.swing.JTextField cusNo;
    private javax.swing.JTextField cusPhone;
    private javax.swing.JTextField historysearch_textfield1;
    private javax.swing.JTextField iDate;
    private java.awt.TextField iDiscount;
    private javax.swing.JTextField iNo;
    private java.awt.TextField iSubAmount;
    private java.awt.TextField iTax;
    private java.awt.TextField iTotal;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JRadioButton rbfemale;
    private javax.swing.JRadioButton rbmale;
    protected javax.swing.JTextField staffId;
    public javax.swing.JLabel staff_namelbl;
    private javax.swing.JTable table_addSell;
    private javax.swing.JTable table_productsell;
    private java.awt.TextField textField3;
    private java.awt.TextField textTamporary_amount;
    private javax.swing.JTextField text_addSellQuantity;
    private javax.swing.JTextField text_amountDiscount;
    private javax.swing.JTextField text_amountTax;
    private javax.swing.JTextField text_cusID;
    private javax.swing.JTextField text_cusName;
    private javax.swing.JTextField text_discount;
    private javax.swing.JTextField text_inDetail;
    private javax.swing.JTextField text_minus;
    private javax.swing.JTextField text_productNo;
    private javax.swing.JTextField text_productQuantity;
    private javax.swing.JTextField text_productSellQuantity;
    private javax.swing.JTextField text_productname;
    private javax.swing.JTextField text_productquantity;
    private javax.swing.JTextField text_producttotal;
    private javax.swing.JTextField text_tax;
    private javax.swing.JTextField text_unitPrice;
    // End of variables declaration//GEN-END:variables

}
