/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Alert.AlertDialog;
import IService.IService;
import Utils.MyConnexion;
import entites.Piece;
import entites.Produit;
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
public class Piece_Service implements IService<Piece> {
    private Connection c=MyConnexion.getInsCon().getcnx();
    @Override
    public void Ajouter(Piece t) throws SQLException {
      Produit_Service serviceproduit = new Produit_Service();
        Produit p = (Produit) t ;
         serviceproduit.Ajouter(p);
        int id=serviceproduit.recuperer_ID_P( p);
       try {
            String requete = "INSERT INTO `piece`(`ID_P`, `quantite`,`etat`) VALUES (?,?,?)";
             PreparedStatement pst = c.prepareStatement(requete);
                         pst.setInt(1, id);
   pst.setInt(2, t.getQuantite());   
   pst.setString(3, t.getEtat());   
            pst.execute();
                 if (id != 0)
            {
                       AlertDialog.showNotification("Ajout", "Piece ajoutee", AlertDialog.image_checked);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     }

    @Override
    public void Supprimer(int t) throws SQLException {
         Produit_Service services = new Produit_Service();
        services.Supprimer(recuperer_id_P_Piece(t));
         String requete = "DELETE FROM `piece` WHERE `ID_A`=" + String.valueOf(t) + "";
        try {

            PreparedStatement pst = c.prepareStatement(requete);
            pst.execute(requete);
            System.out.println("service supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }

    @Override
    public void Modifier(Piece t, int ID_Pi) throws SQLException {
        PreparedStatement ps;
        String lib = t.getLibelle();
        String image = t.getImage();
        String des = t.getDescription();
        float prix = t.getPrix();
        String Lib_c = t.getLib_C();
        String Lib_s = t.getLib_S();
        int quantite= t.getQuantite();
        int id_P = recuperer_id_P_Piece(ID_Pi);
       Produit_Service services = new Produit_Service();
       services.Modifier((Produit) t, id_P);
         String query = "UPDATE `piece` SET `quantite`=?,`etat`=? WHERE ID_Pi=?";
     ps = c.prepareStatement(query);
       ps.setInt(3, ID_Pi);
        ps.setInt(1, quantite);
        ps.setString(2, t.getEtat());
        ps.execute();
    
    
    }
   public ObservableList<Piece> serach(String cas) throws SQLException {
        ObservableList<Piece> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `piece` a Inner JOIN produit p where a.ID_P=p.ID_P AND  ( p.Libelle LIKE '%" + cas + "%'or  p.Description LIKE '%" + cas + "%' or  p.Prix LIKE '%" + cas + "%' or  p.Lib_C LIKE '%" + cas + "%' or  p.Lib_S LIKE '%" + cas + "%' or  a.quantite LIKE '%" + cas + "%'or  a.etat LIKE '%" + cas + "%' )";
       try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                   list.add(new Piece(rs.getInt("quantite"),rs.getInt("ID_Pi"),rs.getString("etat"), rs.getString("Libelle"), rs.getString("Lib_C"), rs.getString("Lib_S"), rs.getString("Image"), rs.getString("Description"), rs.getFloat("Prix")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
 
    @Override
    public ObservableList<Piece> Affichertout() throws SQLException {
     ObservableList<Piece> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `piece` a Inner JOIN produit p where a.ID_P=p.ID_P";
        try {
                 PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                list.add(new Piece(rs.getInt("quantite"),rs.getInt("ID_Pi"),rs.getString("etat"), rs.getString("Libelle"), rs.getString("Lib_C"), rs.getString("Lib_S"), rs.getString("Image"), rs.getString("Description"), rs.getFloat("Prix")));


            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list ;  }
           public Piece get_Accessoire_affichage(int i,String lib_s) {
        Piece p = null;
        int nombre = 0;
      String requete = "SELECT * FROM `piece` a Inner JOIN produit p where ( a.ID_P=p.ID_P AND p.Lib_S=\""+lib_s+"\" AND a.quantite!=0)"       ;
         try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                if (i == nombre) {
                    p=  new Piece(rs.getInt("quantite"),rs.getInt("ID_Pi"),rs.getString("etat"), rs.getString("Libelle"), rs.getString("Lib_C"), rs.getString("Lib_S"), rs.getString("Image"), rs.getString("Description"), rs.getFloat("Prix"));

      
               
                }
                nombre++;
                         }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return p;

    }
    
     public int Affichertaille(String lib_s) throws SQLException {
        int nbr = 0;        
        String requete = "SELECT COUNT(*) as nbr FROM `piece` a Inner JOIN produit p where ( a.ID_P=p.ID_P AND p.Lib_S=\""+lib_s+"\" AND a.quantite!=0)"       ;
                try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              nbr=rs.getInt("nbr");
            }
                   
        } catch (SQLException ex) {
            System.out.println(ex);
        }
                        System.out.println("le nombre est : "+nbr);   
        return nbr;

    }
    
    
    
    
    
       public int recuperer_id_P_Piece(int id_piece)
    {
        int id=0;
        
        String requete = "SELECT `ID_P` FROM `piece` WHERE ID_Pi=?";
        try {
              PreparedStatement ps = c.prepareStatement(requete);
              ps.setInt(1, id_piece);
        ResultSet rs = ps.executeQuery();           
            while (rs.first()) {
               id=rs.getInt("ID_P");
               break;
            }
        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
   
    }

}
