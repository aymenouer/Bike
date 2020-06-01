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
import entites.reclamation;
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
public class reclamation_Service implements IService<reclamation> {

    private Connection c = MyConnexion.getInsCon().getcnx();

    @Override
    public void Ajouter(reclamation t) throws SQLException {

        try {
            String requete = "INSERT INTO `reclamation`( `ID_U`, `Contenu`, `Type`, `etat`, `image`, `traite`) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setInt(1, t.getIdu());
            pst.setString(2, t.getContenu());
            pst.setString(3, t.getType());
            pst.setString(4, t.getEtat());
            pst.setString(5, t.getImage());
            pst.setString(6, t.getTraite());
            pst.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void Supprimer(int t) throws SQLException {

        String requete = "DELETE FROM `reclamation` WHERE `ID`=" + String.valueOf(t) + "";
        try {

            PreparedStatement pst = c.prepareStatement(requete);
            pst.execute(requete);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void Modifier(reclamation t, int id) throws SQLException {

        PreparedStatement ps;

        String query = "UPDATE `reclamation` SET `traite`=? where ID = ?";
        ps = c.prepareStatement(query);
        ps.setInt(2, id);
        ps.setString(1, t.getTraite());
        ps.execute();
    }

    @Override
    public ObservableList<reclamation> Affichertout() throws SQLException {
        ObservableList<reclamation> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `reclamation` ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new reclamation(rs.getInt("ID"), rs.getInt("ID_U"), rs.getString("Contenu"), rs.getString("image"), rs.getString("Type"), rs.getString("etat"), rs.getString("traite")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;

    }

    public ObservableList<reclamation> Affichertout_user(int id) throws SQLException {
        ObservableList<reclamation> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `reclamation` where ID_U = " + String.valueOf(id);
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new reclamation(rs.getInt("ID"), rs.getInt("ID_U"), rs.getString("Contenu"), rs.getString("image"), rs.getString("Type"), rs.getString("etat"), rs.getString("traite")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;

    }

    public ObservableList<reclamation> serach(String cas) throws SQLException {
        ObservableList<reclamation> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `reclamation` where Contenu LIKE '%" + cas + "%'or Type LIKE '%" + cas + "%' or  etat LIKE '%" + cas + "%' or  traite LIKE '%" + cas + "%' ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new reclamation(rs.getInt("ID"), rs.getInt("ID_U"), rs.getString("Contenu"), rs.getString("image"), rs.getString("Type"), rs.getString("etat"), rs.getString("traite")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
}
