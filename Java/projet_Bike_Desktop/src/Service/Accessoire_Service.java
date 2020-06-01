/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Alert.AlertDialog;
import IService.IService;
import Utils.MyConnexion;
import entites.Accessoire;
import entites.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Rajia
 */
public class Accessoire_Service implements IService<Accessoire> {
    private Connection c=MyConnexion.getInsCon().getcnx();

    @Override
    public void Ajouter(Accessoire t) throws SQLException {
       Produit_Service serviceproduit = new Produit_Service();
        Produit p = (Produit) t ;
         serviceproduit.Ajouter(p);
        int id=serviceproduit.recuperer_ID_P( p);
       try {
            String requete = "INSERT INTO `accessoire`(`ID_P`, `quantite`) VALUES (?,?)";
             PreparedStatement pst = c.prepareStatement(requete);
                         pst.setInt(1, id);
   pst.setInt(2, t.getQuantite());   
            pst.execute();
                 if (id != 0)
            {
                       AlertDialog.showNotification("Ajout", "saye", AlertDialog.image_checked);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }

    @Override
    public void Supprimer(int t) throws SQLException {
        Produit_Service services = new Produit_Service();
        services.Supprimer(recuperer_id_P_accessoire(t));
         String requete = "DELETE FROM `accessoire` WHERE `ID_A`=" + String.valueOf(t) + "";
        try {

            PreparedStatement pst = c.prepareStatement(requete);
            pst.execute(requete);
            System.out.println("service supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void Modifier(Accessoire t, int id_A) throws SQLException {
       PreparedStatement ps;
        String lib = t.getLibelle();
        String image = t.getImage();
        String des = t.getDescription();
        float prix = t.getPrix();
        String Lib_c = t.getLib_C();
        String Lib_s = t.getLib_S();
        int quantite= t.getQuantite();
        int id_P = recuperer_id_P_accessoire(id_A);
       Produit_Service services = new Produit_Service();
       services.Modifier((Produit) t, id_P);
         String query = "UPDATE `accessoire` SET `quantite`=? WHERE ID_A=?";
     ps = c.prepareStatement(query);
       ps.setInt(2, id_A);
        ps.setInt(1, quantite);
        ps.execute();
    }


    public int recuperer_id_P_accessoire(int id_accessoire)
    {
        int id=0;
        
        String requete = "SELECT `ID_P` FROM `accessoire` WHERE ID_A=?";
        try {
              PreparedStatement ps = c.prepareStatement(requete);
              ps.setInt(1, id_accessoire);
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

    @Override
    public ObservableList<Accessoire> Affichertout() throws SQLException {
 ObservableList<Accessoire> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `accessoire` a Inner JOIN produit p where a.ID_P=p.ID_P";
        try {
                 PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                list.add(new Accessoire(rs.getInt("quantite"),rs.getInt("ID_A"), rs.getString("Libelle"), rs.getString("Lib_C"), rs.getString("Lib_S"), rs.getString("Image"), rs.getString("Description"), rs.getFloat("Prix")));


            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list ;  
    }
    
    
    public ObservableList<Accessoire> serach(String cas) throws SQLException {
        ObservableList<Accessoire> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `accessoire` a Inner JOIN produit p where a.ID_P=p.ID_P AND  ( p.Libelle LIKE '%" + cas + "%'or  p.Description LIKE '%" + cas + "%' or  p.Prix LIKE '%" + cas + "%' or  p.Lib_C LIKE '%" + cas + "%' or  p.Lib_S LIKE '%" + cas + "%' or  a.quantite LIKE '%" + cas + "%' )";
       try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                  list.add(new Accessoire(rs.getInt("quantite"),rs.getInt("ID_A"), rs.getString("Libelle"), rs.getString("Lib_C"), rs.getString("Lib_S"), rs.getString("Image"), rs.getString("Description"), rs.getFloat("Prix")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
         public int Affichertaille(String lib_s) throws SQLException {
        int nbr = 0;        
        String requete = "SELECT COUNT(*) as nbr FROM `accessoire` a Inner JOIN produit p where ( a.ID_P=p.ID_P AND p.Lib_S=\""+lib_s+"\" AND a.quantite!=0)"       ;
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
         
         public Accessoire get_Accessoire_affichage(int i,String lib_s) {
        Accessoire p = null;
        int nombre = 0;
      String requete = "SELECT * FROM `accessoire` a Inner JOIN produit p where ( a.ID_P=p.ID_P AND p.Lib_S=\""+lib_s+"\" AND a.quantite!=0)"       ;
         try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                if (i == nombre) {
                    
       p=new Accessoire(rs.getInt("quantite"),rs.getInt("ID_A"), rs.getString("Libelle"), rs.getString("Lib_C"), rs.getString("Lib_S"), rs.getString("Image"), rs.getString("Description"), rs.getFloat("Prix"));

               
                }
                nombre++;
                         }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return p;

    }
                        
                        public Accessoire get_accessoire_id_p(int id_p) throws SQLException
                        {
                            Accessoire p = null; 
                           String requete = "SELECT * FROM `accessoire` a inner join produit p  where ( ID_P="+String.valueOf(id_p)+")"       ;
          
                           
                            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

                           while (rs.next()) {
              
                    
     
                    p = new Accessoire(rs.getString("Libelle"),rs.getString("Lib_C"),rs.getString("Lib_S"),rs.getString("Image"),rs.getString("Description"),rs.getFloat("Prix"),rs.getInt("quantite"));
           
             
                         }
                           
                           
                           
                           
                           
                           return p;
                        }
                        
                        
    
}
