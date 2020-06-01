/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Service;
import Service.*;
import Utils.MyConnexion;
import entites.Reservation;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author asus
 */
public class ReservationService {

   private Connection c = MyConnexion.getInsCon().getcnx();
    public void ajouterR(Reservation r) {
        Velo_Service sv = new Velo_Service();
        PreparedStatement ps;
        String requete = "INSERT INTO `reservation`(`Date_D`,`Date_F`, `prix`, `idV`, `ID_U`) VALUES (?,?,?,?,?)";
        try {

            ps = c.prepareStatement(requete);
            ps.setDate(1, r.getDATE_D());
            ps.setDate(2, r.getDATE_F());
            ps.setFloat(3, r.getPrix());
            ps.setInt(4, r.getIdV());
            ps.setInt(5, r.getID_U());
            sv.ModifierEtat("reserve",r.getIdV());
            ps.execute();
            System.out.println("reservation ajoutée");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public void supprimerReservation(Reservation r) {
        PreparedStatement pt;
        Velo_Service sv = new Velo_Service();
        try {
            pt = c.prepareStatement("DELETE FROM `reservation` WHERE `idR`=?");
            pt.setInt(1, r.getIdR());
            pt.executeUpdate();
            sv.ModifierEtat("non reservee",r.getIdV());
        } catch (SQLException ex) {
            Logger.getLogger(Velo_Service.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ObservableList<Reservation> afficher_reservation_user(int id) throws SQLException {
        ObservableList<Reservation> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `reservation` WHERE ID_U = "+String.valueOf(id);
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                    list.add(new Reservation(rs.getInt("ID_U"),rs.getInt("idV"),rs.getDate("Date_D"),rs.getDate("Date_F"),rs.getFloat("prix"),rs.getInt("idR")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public void modifierReservation(float prix, int idd) {
        PreparedStatement pt;
        try {

            pt = c.prepareStatement("update `reservation` set prix=? where idR=?");
            pt.setFloat(1, prix);
            pt.setInt(2, idd);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Velo_Service.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

 public Float calcul_total_reservation(int idd) {
        PreparedStatement pt;
        Float prix = Float.valueOf(0);
             String requete = "SELECT sum(prix) as nbr FROM `reservation` WHERE ID_U = "+String.valueOf(idd);
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
prix=rs.getFloat("nbr");
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
return prix;
    }



 
 

    

    public void mise_a_jour() throws SQLException {
        PreparedStatement ps;
        String query = "DELETE FROM `reservation` WHERE DATEDIFF(DATE_F,NOW() )<0";
        ps = c.prepareStatement(query);
        ps.execute();

    }

}
