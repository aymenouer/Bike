/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import IService.IService;
import Utils.MyConnexion;
import entites.Maitenance;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author aymen
 */
public class Maintenance_Service implements IService<Maitenance> {
  private Connection c = MyConnexion.getInsCon().getcnx();
    @Override
    public void Ajouter(Maitenance t) throws SQLException {
        
                try {
            String requete = "INSERT INTO `maitenance`( `ID_U`, `Problem`, `Prix`, `etat`, `image`, `DATE_D`, `DATE_F`, `type`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setInt(1, t.getId_u());
            pst.setString(2, t.getProblem());
            pst.setFloat(3, t.getPrix());
            pst.setString(4, t.getEtat());
            pst.setString(5, t.getImage());
            pst.setDate(6, new java.sql.Date(new Date().getTime()));
                 Calendar C = Calendar.getInstance();
            if (t.getType().equals("accessoire"))
            {
                  C.add(Calendar.DATE, 7);
            }
            else if (t.getType().equals("Velo"))
            {
                 C.add(Calendar.DATE, 14);
            }
            else if (t.getType().equals("Piece"))
            {
                 C.add(Calendar.DATE, 5);
            }
            else
            {
                 C.add(Calendar.DATE, 12); 
            }
         
          
              pst.setDate(7, new java.sql.Date(C.getTime().getTime()));
                pst.setString(8, t.getType());
            pst.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
        
        
        
        
  
    }

    @Override
    public void Supprimer(int t) throws SQLException {
          
   
         String requete = "DELETE FROM `maitenance` WHERE `ID_M`=" + String.valueOf(t) + "";
        try {

            PreparedStatement pst = c.prepareStatement(requete);
            pst.execute(requete);
            System.out.println("service supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
  
    }

    @Override
    public void Modifier(Maitenance t, int id) throws SQLException {
           PreparedStatement ps;
  
 
       
 
         String query = "UPDATE `maitenance` SET `Prix`=?,`etat`=? WHERE ID_M=?";
     ps = c.prepareStatement(query);
   
        ps.setFloat(1, t.getPrix());
        ps.setString(2, t.getEtat());
            ps.setInt(3, id);
        ps.execute();
        
        
  
    }
    public void Modifier_user(Maitenance t, int id) throws SQLException {
           PreparedStatement ps;
  
 
       
 
         String query = "UPDATE `maitenance` SET `etat`=? WHERE ID_M=?";
     ps = c.prepareStatement(query);
   
    
        ps.setString(1, t.getEtat());
            ps.setInt(2, id);
        ps.execute();
        
        
  
    }
    @Override
    public ObservableList<Maitenance> Affichertout() throws SQLException {
        
                                    ObservableList<Maitenance> list = FXCollections.observableArrayList();

            String requete = "select * from maitenance ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
        
list.add(new Maitenance(rs.getInt("ID_M"), rs.getInt("ID_U"), rs.getFloat("Prix"), rs.getString("Problem"), rs.getString("etat"), rs.getString("image"), rs.getString("type"), rs.getDate("DATE_D"), rs.getDate("DATE_F")));
            
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return list; 

        
  
    }
    
        public ObservableList<Maitenance> Affichertout_user(int id) throws SQLException {
        
                                    ObservableList<Maitenance> list = FXCollections.observableArrayList();

            String requete = "select * from maitenance where ID_U = "+String.valueOf(id);
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
        
list.add(new Maitenance(rs.getInt("ID_M"), rs.getInt("ID_U"), rs.getFloat("Prix"), rs.getString("Problem"), rs.getString("etat"), rs.getString("image"), rs.getString("type"), rs.getDate("DATE_D"), rs.getDate("DATE_F")));
            
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return list; 

        
  
    }
    
    
    
    
    
    
    
    
       public ObservableList<Maitenance> serach(String cas) throws SQLException {
        ObservableList<Maitenance> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `maitenance`  where  Problem LIKE '%" + cas + "%'or  Prix LIKE '%" + cas + "%' or  etat LIKE '%" + cas + "%' or  DATE_D LIKE '%" + cas + "%' or  DATE_F LIKE '%" + cas + "%' or  type LIKE '%" + cas + "%' ";
       try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                 list.add(new Maitenance(rs.getInt("ID_M"), rs.getInt("ID_U"), rs.getFloat("Prix"), rs.getString("Problem"), rs.getString("etat"), rs.getString("image"), rs.getString("type"), rs.getDate("DATE_D"), rs.getDate("DATE_F")));
  
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
       public void mise_a_jour() throws SQLException
       {
          PreparedStatement ps;
  
 
       
 
         String query = "UPDATE `maitenance` SET `etat`=\"repare\" WHERE DATEDIFF(DATE_F, NOW()) < 0 AND etat =\"en cours de reparation\\";
     ps = c.prepareStatement(query);
        ps.execute();  
       }
    
    
}
