/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import IService.IService;
import Utils.MyConnexion;
import entites.Commande;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author aymen
 */
public class Commande_service implements IService<Commande> {
private Connection c = MyConnexion.getInsCon().getcnx();
    @Override
    public void Ajouter(Commande t) throws SQLException {
        try
        {
            String req = "INSERT INTO commande (Libelle_P,Prix_P,Quantite_P) SELECT Libelle_P,Prix_P,Quantite_P FROM panier";
   PreparedStatement sp = c.prepareStatement(req);
            
               sp.execute();
                              String req2 = "UPDATE `commande` SET`Adresse`=?,`Ville`=?,`Telephone`=? WHERE id = 1";
            PreparedStatement sp1 = c.prepareStatement(req2);
                         sp1.setString(1, t.getAdresse());
             sp1.setString(2, t.getVille());
              sp1.setInt(3, t.getTelephone());
              sp1.execute();
        }
        catch (Exception e)
        {
            System.out.println(e);
            
        }

    

    }
      public void emtyCommande() throws SQLException {
               PreparedStatement ps;    
        String query = "DELETE FROM `commande`";
        ps = c.prepareStatement(query);
   
        ps.execute();
    }

    @Override
    public void Supprimer(int id_commande) throws SQLException {
                     PreparedStatement ps;    
        String query = "DELETE FROM `commande` WHERE ID_Commande=? ";
        ps = c.prepareStatement(query);
        ps.setInt(1, id_commande);
        ps.execute();
    
    }

    @Override
    public void Modifier(Commande t, int id_commande) throws SQLException {

    
             PreparedStatement ps;    
        String query = "UPDATE `commande` SET `Adresse`=?,`Ville`=?,`Telephone`=? WHERE ID_Commande=? ";
        ps = c.prepareStatement(query);
        ps.setString(1, t.getAdresse());
             ps.setString(2, t.getVille());
        ps.setInt(3, t.getTelephone());
        ps.setInt(4, id_commande);

        ps.execute();
    }

    @Override
    public ObservableList<Commande> Affichertout() throws SQLException {
               ObservableList<Commande> list = FXCollections.observableArrayList();
     String requete = "SELECT * FROM `commande`";
      try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new Commande(rs.getInt("ID_Commande"), rs.getString("Libelle_P"), rs.getFloat("Prix_P"), rs.getInt("Quantite_P"), rs.getString("Adresse"), rs.getString("Ville"), rs.getInt("Telephone")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
    return list;
    } 
           public float prixtotal()
       {
           float total=0;
               String requete = "SELECT SUM(Prix_P * Quantite_P) as total FROM `commande`";
                   
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
    
    
}
