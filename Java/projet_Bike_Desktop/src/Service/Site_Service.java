/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Alert.AlertDialog;
import IService.IService;
import Utils.MyConnexion;
import entites.Site;
import entites.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

/**
 *
 * @author Rajia
 */
public class Site_Service implements IService<Site> {
private Connection c = MyConnexion.getInsCon().getcnx();
    @Override
    public void Ajouter(Site t) throws SQLException {
               try {
            String requete = "INSERT INTO `site`(`capacite`, `lieu`, `Lib_S`) VALUES (?,?,?)";
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setInt(1, t.getCapacite());
            pst.setString(2, t.getLieu());
            pst.setString(3, t.getLib_S());
            pst.execute();
           AlertDialog.showNotification("Ajout", "saye", AlertDialog.image_checked);
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void Supprimer(int t) throws SQLException {
                String requete = "DELETE FROM `site` WHERE `id_S`=" + String.valueOf(t) + "";
        try {

            PreparedStatement pst = c.prepareStatement(requete);
            pst.execute(requete);
            System.out.println("Site supprimée");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public void Modifier(Site t, int id) throws SQLException {
  PreparedStatement ps;
        String query = "UPDATE `site` SET `capacite`=?,`lieu`=?,`Lib_S`=? WHERE id_S=?";

        int capacite = t.getCapacite();
    
        ps = c.prepareStatement(query);
        ps.setInt(1, capacite);
        ps.setString(2, t.getLieu());
        ps.setString(3, t.getLib_S());
        ps.setFloat(4, id);
        ps.execute();
    }


    @Override
    public ObservableList<Site> Affichertout() throws SQLException {
                      ObservableList<Site> list = FXCollections.observableArrayList();

            String requete = "select * from site ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
list.add(new Site(rs.getInt("capacite"), rs.getString("Lib_S"), rs.getString("lieu"),rs.getInt("id_S")));
            
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return list; 


    }
public ObservableList<String> get_List_sites ()
    {
                ObservableList<String> list = FXCollections.observableArrayList();

            String requete = "select * from site ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
list.add(rs.getString("Lib_S"));
            
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return list; 
    }

public int Affichertaille() throws SQLException {
        int i = 0;
        String requete = "SELECT * FROM `site` ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
               
                i++;

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return i;

    }

public Site get_site(int i) {
        Site site = null;
        int nombre = 0;
        String requete = "SELECT * FROM `site`";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                if (i == nombre) {
                    site= new Site(rs.getInt("capacite"), rs.getString("Lib_S"), rs.getString("lieu"),rs.getInt("id_S"));

                   
                }
                nombre++;

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return site;

    }



    public ObservableList<Site> serach(String cas) throws SQLException {
                      ObservableList<Site> list = FXCollections.observableArrayList();

            String requete = "select * from site  where Lib_S LIKE '%" + cas + "%' or capacite LIKE '%" + cas + "%' or lieu LIKE '%" + cas + "%' ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
list.add(new Site(rs.getInt("capacite"), rs.getString("Lib_S"), rs.getString("lieu"),rs.getInt("id_S")));
            
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return list; 


    }


    
}
