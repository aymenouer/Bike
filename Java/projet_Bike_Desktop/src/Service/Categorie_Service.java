/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Alert.AlertDialog;
import IService.IService;
import Utils.MyConnexion;
import entites.Categorie;
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
 * @author aymen
 */
public class Categorie_Service implements IService<Categorie> {

    private Connection c = MyConnexion.getInsCon().getcnx();

    @Override
    public void Ajouter(Categorie t) throws SQLException {
           try {
            String requete = "INSERT INTO `categorie`(`Lib_C`, `Description`, `Type`) VALUES (?,?,?)";
            PreparedStatement pst = c.prepareStatement(requete);
   pst.setString(1, t.getLib_C());
            pst.setString(2, t.getDescription());
            pst.setString(3, t.getType());
            pst.execute();
           AlertDialog.showNotification("Ajout", "saye", AlertDialog.image_checked);
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    
    
    }

    @Override
    public void Supprimer(int t) throws SQLException {
           PreparedStatement ps;    
        String query = "DELETE FROM `categorie` WHERE ID_C=? ";
        ps = c.prepareStatement(query);
        ps.setInt(1, t);
        ps.execute(); }

    @Override
    public void Modifier(Categorie t, int id) throws SQLException {
       PreparedStatement ps;
        String query = "UPDATE `categorie` SET `Lib_C`=?,`Description`=?,`Type`=? WHERE ID_C=?";

        ps = c.prepareStatement(query);
   ps.setString(1, t.getLib_C());
            ps.setString(2, t.getDescription());
            ps.setString(3, t.getType());
        ps.setInt(4, id);
        ps.execute();
    
    }

    @Override
    public ObservableList<Categorie> Affichertout() throws SQLException {
                            ObservableList<Categorie> list = FXCollections.observableArrayList();

            String requete = "select * from categorie ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
        
list.add(new Categorie(rs.getInt("ID_C"), rs.getString("Lib_C"), rs.getString("Description"),rs.getString("Type")));
            
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return list; 

    
    
    
    
    
    
    
    }
        public ObservableList<Categorie> serach(String cas) throws SQLException {
                            ObservableList<Categorie> list = FXCollections.observableArrayList();

            String requete = "select * from categorie where  Type LIKE '%" + cas + "%' or Lib_C LIKE '%" + cas + "%' or Description LIKE '%" + cas + "%' ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
        
list.add(new Categorie(rs.getInt("ID_C"), rs.getString("Lib_C"), rs.getString("Description"),rs.getString("Type")));
            
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return list; 

    
    
    
    
    
    
    
    }

    public ObservableList<String> get_List_Categories_Abonment() {
        ObservableList<String> list = FXCollections.observableArrayList();

        String requete = "SELECT * FROM `categorie` WHERE Type=\"Abonnement\"";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("Lib_C"));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public ObservableList<String> get_List_Categories_acessoire() {
        ObservableList<String> list = FXCollections.observableArrayList();

        String requete = "select * from categorie WHERE Type=\"Accessoire\" ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("Lib_C"));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
 public ObservableList<String> get_List_Categories_Piece() {
        ObservableList<String> list = FXCollections.observableArrayList();

        String requete = "select * from categorie WHERE Type=\"Piece\" ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("Lib_C"));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public ObservableList<String> get_List_Categories_Velo() {
        ObservableList<String> list = FXCollections.observableArrayList();

            String requete = "select * from categorie WHERE Type=\"Velo\" ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("Lib_C"));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
