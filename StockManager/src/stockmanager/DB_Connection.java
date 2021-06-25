/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmanager;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ASUS
 */
public class DB_Connection {
    public static Connection createConnection() {
        
        Connection con = null;
        String URL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=StockManager";
        String user = "rithy";
        String pass = "123"; 
        try {
            
            con = DriverManager.getConnection(URL, user, pass); //attempting to connect to MySQL database
            
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return con; 
    }
}
