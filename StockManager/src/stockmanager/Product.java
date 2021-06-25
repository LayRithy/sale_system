/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmanager;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class Product {
    private int pID;
    private String pName;
    private int cNO;
    private String pUnitPrice; 
    private String pSellingPrice;
    private int pQuantity;
    private Date pReceivingDate;
    private Date pExpireDate;
    
    public Product(int pID, String pName, int cNO, String pUnitPrice, String pSellingPrice, int pQuantity, Date pReceivingDate, Date pExpireDate){
        this.pID = pID;
        this.pName = pName;
        this.cNO = cNO;
        this.pUnitPrice = pUnitPrice;
        this.pQuantity = pQuantity;
        this.pReceivingDate = pReceivingDate;
        this.pExpireDate = pExpireDate;
    }
    public int getID(){
        return pID;
    }
    public String getName(){
        return pName;
    }
    public int getNo(){
        return cNO;
    }
    public String getUnitPrice(){
        return pUnitPrice;
    }
    public String getSellingPrice(){
        return pSellingPrice;
    }
    public int getQuantity(){
        return pQuantity;
    }
    public Date getReceivingDate(){
        return pReceivingDate;
    }
    public Date getExpireDate(){
        return pExpireDate;
    }
    
    
    public void setID(int pID){
        this.pID=pID;
    }
    public void setName(String pName){
        this.pName=pName;
    }
    public void setNo(int cNO){
        this.cNO=cNO;
    }
    public void setUnitPrice(String pUnitPrice){
        this.pUnitPrice=pUnitPrice;
    }
    public void setSellingPrice(String pSellingPrice){
        this.pSellingPrice=pSellingPrice;
    }
    public void setQuantity(int pQuantity){
        this.pQuantity=pQuantity;
    }
    public void setReceivingDate(Date pReceivingDate){
        this.pReceivingDate=pReceivingDate;
    }
    public void setExpireDate(Date pExpireDate){
        this.pExpireDate=pExpireDate;
    }
       
}
