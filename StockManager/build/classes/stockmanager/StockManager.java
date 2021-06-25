/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmanager;

/**
 *
 * @author ASUS
 */
public class StockManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
            HomeScreen j = new HomeScreen();
                //j.setVisible(true);
               //j.autoID();
                j.setLocationRelativeTo(null);
       
            LoginScreen ls = new LoginScreen();
                ls.setVisible(true);
                ls.setLocationRelativeTo(null);
                
                
            SignUpScreen ss = new SignUpScreen();
                //ss.setVisible(true);
                ss.setLocationRelativeTo(null);
                
             SellScreen sellscreen = new SellScreen();
                //sellscreen.setVisible(true);
                sellscreen.setLocationRelativeTo(null);
                
             AdminScreen as = new AdminScreen();
                //as.setVisible(true);
                //as.setLocationRelativeTo(null);
    }
    
}
