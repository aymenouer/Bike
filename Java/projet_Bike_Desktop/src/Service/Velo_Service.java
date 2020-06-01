/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import IService.IService;
import Utils.MyConnexion;
import entites.Produit;
import entites.User;
import entites.Velo;
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
public class Velo_Service  implements IService<Velo> {
 Connection c = MyConnexion.getInsCon().getcnx();
 @Override
    public void Ajouter(Velo v) throws SQLException {
        PreparedStatement ps;
        Produit_Service serviceproduit = new Produit_Service();
        Produit p = (Produit) v;
        serviceproduit.Ajouter(p);
        int id = serviceproduit.recuperer_ID_P(p);
        String requete = "INSERT INTO `velo`(`type`, `age`, `couleur`, `idA`,`etat`) VALUES (?,?,?,?,?)";
        try {

            String type = v.getType();
            String couleur = v.getCouleur();
            String age = String.valueOf(v.getAge());

            ps = c.prepareStatement(requete);
            ps.setString(1, type);
            ps.setString(2, age);
            ps.setString(3, couleur);
            ps.setInt(4, id);
            ps.setString(5, "non reservee");

            ps.execute();
            System.out.println("velo ajoutée");
            // JavaMailUtil.sendMail("noura.mnif@esprit.tn");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }

    @Override
    public void Supprimer(int v) throws SQLException {
       Produit_Service services = new Produit_Service();
        services.Supprimer(recuperer_id_velo(v));
        String requete = "DELETE FROM `velo` WHERE `id`=" + String.valueOf(v) + "";
        try {

            PreparedStatement pst = c.prepareStatement(requete);
            pst.execute(requete);
            System.out.println("velo supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    
    }

    @Override
    public ObservableList<Velo> Affichertout() throws SQLException {
    ObservableList<Velo> list = FXCollections.observableArrayList();
        String requete = "select * from velo v Inner JOIN produit p where v.idA=p.ID_P";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new Velo(rs.getString("type"), rs.getInt("age"), rs.getString("couleur"), rs.getString("etat"),rs.getInt("id"),rs.getString("Libelle"),rs.getString("Lib_C"),rs.getString("Lib_S"),rs.getString("Image"),rs.getString("Description"),rs.getFloat("Prix")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
   


 

    public int recuperer_id_velo(int id) {
        int idA = 0;

        String requete = "SELECT `idA` FROM `velo` WHERE id=?";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.first()) {
                idA = rs.getInt("idA");
                break;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return idA;

    }

 @Override
    public void Modifier(Velo t, int id_A) throws SQLException {
        PreparedStatement ps;
        String lib = t.getLibelle();
        String image = t.getImage();
        String des = t.getDescription();       
        float prix = t.getPrix();
        String Lib_c = t.getLib_C();
        String Lib_s = t.getLib_S();
        String type = t.getType();
        String couleur = t.getCouleur();
        String age = String.valueOf(t.getAge());;
        int id_P = recuperer_id_velo(id_A);

             Produit_Service services = new Produit_Service();
       services.Modifier((Produit) t, id_P);
        String query = "UPDATE `velo` SET `type`=?,`age`=?,`couleur`=? WHERE id=?";
        ps = c.prepareStatement(query);
        ps.setInt(4, id_A);
        ps.setString(1, type);
        ps.setString(2, age);
        ps.setString(3, couleur);
        ps.execute();
    }
    
     public void ModifierEtat(String etat ,int idV) throws SQLException {
        PreparedStatement ps;
       
        
        String query = "UPDATE `velo` SET `etat`=? WHERE id=?";
        ps = c.prepareStatement(query);
        
        ps.setString(1, etat);
        ps.setInt(2, idV);
      
        ps.execute();
    }
    


    

   public ObservableList<Velo> serach(String cas) throws SQLException {
        ObservableList<Velo> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `Velo` a Inner JOIN produit p where a.idA=p.ID_P AND  ( p.Libelle LIKE '%" + cas + "%'or  p.Description LIKE '%" + cas + "%' or  p.Prix LIKE '%" + cas + "%' or  p.Lib_C LIKE '%" + cas + "%' or  p.Lib_S LIKE '%" + cas + "%' or  a.age LIKE '%" + cas + "%'or  a.etat LIKE '%" + cas + "%' or  a.couleur LIKE '%" + cas + "%' or  a.type LIKE '%" + cas + "%' ) ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

               list.add(new Velo(rs.getString("type"), rs.getInt("age"), rs.getString("couleur"), rs.getString("etat"),rs.getInt("id"),rs.getString("Libelle"),rs.getString("Lib_C"),rs.getString("Lib_S"),rs.getString("Image"),rs.getString("Description"),rs.getFloat("Prix")));
  }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
   
   public int Affichertaille() throws SQLException {
        int i = 0;
        String requete = "SELECT * FROM `Velo` a Inner JOIN produit p where a.idA=p.ID_P";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(i);
                i++;

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return i;

    }

   
   public Velo get_vel(int i) {
        Velo ab = null;
        int nombre = 0;
        String requete = "SELECT * FROM `velo` a Inner JOIN produit p where (a.idA=p.ID_P)";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                if (i == nombre) {

                    ab = new Velo(rs.getString("type"), rs.getInt("age"), rs.getString("couleur"), rs.getString("etat"),rs.getInt("id"),rs.getString("Libelle"),rs.getString("Lib_C"),rs.getString("Lib_S"),rs.getString("Image"),rs.getString("Description"),rs.getFloat("Prix"));
                    System.out.println(ab.toString());
                }
                nombre++;

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return ab;

    }

   public Velo get_vel_par_id_v(int id) {
        Velo ab = null;
        String requete = "SELECT * FROM `velo` a Inner JOIN produit p where (a.idA=p.ID_P and id = "+ String.valueOf(id)+")";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

              

                    ab = new Velo(rs.getString("type"), rs.getInt("age"), rs.getString("couleur"), rs.getString("etat"),rs.getInt("id"),rs.getString("Libelle"),rs.getString("Lib_C"),rs.getString("Lib_S"),rs.getString("Image"),rs.getString("Description"),rs.getFloat("Prix"));
                    System.out.println(ab.toString());
           
   

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return ab;

    }
}
