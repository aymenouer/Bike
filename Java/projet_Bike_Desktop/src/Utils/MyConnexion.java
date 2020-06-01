/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author dell
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyConnexion {
 private static MyConnexion instanceconnection ;
    private   String url="jdbc:mysql://localhost:3306/bike?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private   String login="root";
    private   String mdp="" ;
    private   Connection conx;
    
    private MyConnexion()
    {
        try {
            conx= DriverManager.getConnection(url,login,mdp);
            System.out.println("connection est établie");
        } catch (SQLException ex) {
            Logger.getLogger(MyConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }
    
    
    public static MyConnexion getInsCon(){
       if (instanceconnection==null){
            instanceconnection = new MyConnexion();
        } 
        return instanceconnection ;
    }
    
    
   public  Connection getcnx(){
       return conx; 
   }
   
   
}    
    

