/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Alert.AlertDialog;
import IService.IService;
import Utils.MyConnexion;
import entites.Produit;
import entites.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Rajia
 */
public class Produit_Service implements IService<Produit> {

    private Connection c = MyConnexion.getInsCon().getcnx();

    @Override
    public void Supprimer(int id) throws SQLException {
        String requete = "DELETE FROM `produit` WHERE `ID_P`=" + String.valueOf(id) + "";
        try {

            PreparedStatement pst = c.prepareStatement(requete);
            pst.execute(requete);
            System.out.println("Produit supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void Ajouter(Produit p) throws SQLException {
        try {
            String requete = "INSERT INTO `produit`( `Libelle`, `Image`, `Description`, `Prix`, `Lib_C`, `Lib_S`) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = c.prepareStatement(requete);

            pst.setString(1, p.getLibelle());
            pst.setString(2, p.getImage());

            pst.setString(3, p.getDescription());
            pst.setFloat(4, p.getPrix());
            pst.setString(5, p.getLib_C());
            pst.setString(6, p.getLib_S());

            pst.execute();
            System.out.println("Produit ajoutée");
        } catch (SQLException ex) {
               AlertDialog.showNotification("Error !", ex.getMessage(), AlertDialog.image_cross);
       
        }
    }

    @Override
    public void Modifier(Produit t, int id) throws SQLException {

        PreparedStatement ps;
        String query = "UPDATE `produit` SET `Libelle`=?,`Image`=?,`Description`=?,`Prix`=?,`Lib_C`=?,`Lib_S`=? WHERE `ID_P`=?";

        String lib = t.getLibelle();
        String image = t.getImage();
        String des = t.getDescription();
        float prix = t.getPrix();
        String Lib_c = t.getLib_C();
        String Lib_s = t.getLib_S();
        ps = c.prepareStatement(query);
        ps.setString(1, lib);
        ps.setString(2, image);
        ps.setString(3, des);
        ps.setFloat(4, prix);
        ps.setString(5, Lib_c);
        ps.setString(6, Lib_s);
        ps.setInt(7, id);
        ps.execute();

    }
    
   
   

    public int recuperer_ID_P(Produit t) {
        String requete = "SELECT `ID_P` FROM `produit` WHERE (Libelle=? AND Image=? AND Description = ? AND Prix=? AND Lib_C = ? AND Lib_S = ?)";
        String lib = t.getLibelle();
        String image = t.getImage();
        String des = t.getDescription();
        float prix = t.getPrix();
        String Lib_c = t.getLib_C();
        String Lib_s = t.getLib_S();
        int id=0;
        try {
              PreparedStatement ps = c.prepareStatement(requete);
              ps.setString(1, lib);
        ps.setString(2, image);
        ps.setString(3, des);
        ps.setFloat(4, prix);
        ps.setString(5, Lib_c);
        ps.setString(6, Lib_s);
      
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
    public List<Produit> Affichertout() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Produit get_produit (int id_p) throws Exception
    {
        Produit p =null;
           PreparedStatement ps = c.prepareStatement("SELECT * FROM `produit` where (ID_P = "+String.valueOf(id_p)+")");
        ResultSet rs = ps.executeQuery();
        while (rs.next())
        {
            
            p = new Produit(rs.getString("Libelle"), rs.getString("Lib_C"), rs.getString("Lib_S"), rs.getString("Image"), rs.getString("Description"), rs.getFloat("Prix"));
        }
        
        
        return p;
    }
    
     

    


    
}
