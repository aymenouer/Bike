/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Alert.AlertDialog;
import IService.IService;
import Utils.MyConnexion;
import entites.Abonnement;
import entites.Accessoire;
import entites.Panier;
import entites.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.logging.Logger;

/**
 *
 * @author aymen
 */
public class Panier_Service implements IService<Panier>{
       private Connection c = MyConnexion.getInsCon().getcnx();

       public float porixtotal()
       {
           float total=0;
               String requete = "SELECT SUM(Prix_P * Quantite_P) as total FROM `panier`";
                   
                     try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                total= rs.getFloat("total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }    
           
           return total;
       }
       
       
       
       
       
       
    @Override
    public void Ajouter(Panier t) throws SQLException {
        Produit p =null;

        
        Produit_Service service = new Produit_Service();
           try {
               p=service.get_produit(t.getID_P());
           
                   String requete = "INSERT INTO `panier`( `ID_P`, `Libelle_P`, `Quantite_P`, `Prix_P`) VALUES (?,?,?,?)";
                   
                   
                   
                   PreparedStatement pst = c.prepareStatement(requete);
           
                       pst.setInt(1, t.getID_P());
                        pst.setString(2, p.getLibelle());
                         pst.setInt(3, t.getQuantite_P());
                          pst.setFloat(4, p.getPrix());

            pst.execute();
           
           
           
            AlertDialog.showNotification("ajout !","produit dans panier",AlertDialog.image_checked);
      
           
           
           
           
           } catch (Exception ex) {
              AlertDialog.showNotification("Error !",ex.getMessage(),AlertDialog.image_cross);
      }
        
        
    }

    @Override
    public void Supprimer(int t) throws SQLException {
               PreparedStatement ps;    
        String query = "DELETE FROM `panier` WHERE id_panier=? ";
        ps = c.prepareStatement(query);
        ps.setInt(1, t);
        ps.execute();
    }
  public void emtypanier() throws SQLException {
               PreparedStatement ps;    
        String query = "DELETE FROM `panier`";
        ps = c.prepareStatement(query);
   
        ps.execute();
    }
    @Override
    public void Modifier(Panier t, int id) throws SQLException {
         PreparedStatement ps;    
        String query = "UPDATE `panier` SET `Quantite_P`=? WHERE id_panier=? ";
        ps = c.prepareStatement(query);
        ps.setInt(1, t.getQuantite_P());
        ps.setInt(2, id);
        ps.execute();
   
    }

    @Override
    public ObservableList<Panier> Affichertout() throws SQLException {
          ObservableList<Panier> list = FXCollections.observableArrayList();
     String requete = "SELECT * FROM `panier`  ";
      try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new Panier(rs.getInt("id_panier"), rs.getInt("ID_P"), rs.getString("Libelle_P"), rs.getInt("Quantite_P"), rs.getFloat("Prix_P")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
    return list;
    }
}
