    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Utils.MyConnexion;
import entites.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author dell
 */
public class ParticiperService {
    
         Connection cn=MyConnexion.getInsCon().getcnx();
      public void addParticiper(Participer c) throws SQLDataException {
        
   String query ="INSERT INTO `participer`(`idEvenement`,`iduser`) VALUES (?,?)";
 
         PreparedStatement st;
        
        try {
            st = cn.prepareStatement(query);
                st.setInt(1,c.getIdEvent());
                st.setInt(2,c.getIdUuser());
                st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
      
      
          
    public Participer getParticipant(int e, int u) throws SQLDataException, SQLException {

                        Participer p = new Participer();
             int c=0;

     try {
         
               String requete="select * from participer where ( idEvenement ="+e+" and iduser ="+u+" )";
        
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(requete);
                         
                while(rs.next())
             {
                 p.setId(rs.getInt(1));
                 p.setIdEvent(rs.getInt(2));
                 p.setIdUuser(rs.getInt(3));
                 c++;
                         }
             if(c==0)
             {
                 return null;
             }else {
                 return p;
             }  
         
     }
     catch (SQLException ex)
     {
         
         System.out.println(ex.getMessage());
         return null ;
     }
        
    }

       
            
    
   
           

      
      
    
}
