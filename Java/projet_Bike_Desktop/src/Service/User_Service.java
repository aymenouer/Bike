/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Alert.AlertDialog;
import IService.IService;
import Utils.MyConnexion;
import Utils.mail;
import doryan.windowsnotificationapi.fr.Notification;
import entites.User;
import java.awt.TrayIcon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author aymen
 */
public class User_Service implements IService<User> {



    private Connection c = MyConnexion.getInsCon().getcnx();

    
    public String token_user(int id)
    {
        String token="";
          String requete = "SELECT * FROM `user` WHERE (id =" + String.valueOf(id) + ")";

        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

               
                token=rs.getString("confirmation_token");

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return token;
    }
    
    @Override
    public void Ajouter(User u) throws SQLException {
        
        
        String hashedpw = BCrypt.hashpw(u.getPass(), BCrypt.gensalt());
             hashedpw = hashedpw.replace("$2a$", "$2y$");
            String UserRole = "a:0:{}";
      
        
        PreparedStatement ps;
        
        
        String query = "INSERT INTO `user`(`username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `password`,  `roles`, `First_Name`, `User_Number`, `User_Image`, `User_age`, `Last_Name`,`confirmation_token`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = c.prepareStatement(query);
             ps.setString(1, u.getUsername());
             ps.setString(2, u.getUsername());
             ps.setString(3, u.getMail());
             ps.setString(4, u.getMail());
             ps.setInt(5, 1);
             ps.setString(6, hashedpw);
             ps.setString(7, UserRole);
            ps.setString(8, u.getFname());
            ps.setInt(9, u.getNumber());
            ps.setString(10, u.getImage());
            ps.setInt(11, u.getAge());
            ps.setString(12, u.getLname());
       ps.setString(13, BCrypt.hashpw(u.getLname(), BCrypt.gensalt()));
            ps.execute();
            
            System.out.println(u);
   AlertDialog.showNotification("Sign UP","Sign up avec succés",AlertDialog.image_checked);    
   Notification.sendNotification("Client ajouté", "un nouveau client est ajouté ", TrayIcon.MessageType.INFO);
mail.envoi(u.getMail(), "Inscription acceptée", "Bienvenue a Bike, <br> Vous avez été bien ajouté dans l application BIKE <br> votre username : " + u.getUsername() + " <br>  votre password : " + u.getPass() + "   \n ");

        } catch (Exception e) {
              
       
            System.out.println(e);

        }
    }

    @Override
    public void Supprimer(int id) throws SQLException {
        PreparedStatement ps;

        String query = "DELETE FROM `user` WHERE `id`=?  ";
      //  Acheter_Service s = new Acheter_Service();
      //  s.SupprimerU(id);
        try {
            ps = c.prepareStatement(query);

            ps.setInt(1, id);

            ps.execute();

            System.out.println("suppression de USer");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
     public void change_etat_compte(int etat,int id) throws SQLException {
        PreparedStatement ps;

        String query = "UPDATE `user` SET `enabled`=? WHERE `id`=?  ";
      //  Acheter_Service s = new Acheter_Service();
      //  s.SupprimerU(id);
        try {
            ps = c.prepareStatement(query);

            ps.setInt(1, etat);
            ps.setInt(2, id);
            ps.execute();

            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void Modifier(User u, int id) throws SQLException {
        String hashedpw = BCrypt.hashpw(u.getPass(), BCrypt.gensalt());
             hashedpw = hashedpw.replace("$2a$", "$2y$");
        
        PreparedStatement ps;
        String query = "UPDATE `user` SET `username`=?,`username_canonical`=?,`email`=?,`email_canonical`=?,`password`=?,`First_Name`=?,`User_Number`=?,`User_Image`=?,`User_age`=?,`Last_Name`=? WHERE id = ?";
        try {
            
            ps = c.prepareStatement(query);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getUsername());
            ps.setString(3, u.getMail());
            ps.setString(4, u.getMail());
            ps.setString(5, hashedpw);
            ps.setString(6, u.getFname());
            ps.setInt(7, u.getNumber());
            ps.setString(8, u.getImage());
            ps.setInt(9, u.getAge());
             ps.setString(10, u.getLname());
            ps.setInt(11, id);
            ps.execute();
        AlertDialog.showNotification("Modification", "Modification avec sucess", AlertDialog.image_checked);
 

        } catch (Exception e) {
             AlertDialog.showNotification("Erreur ! ", e.getMessage(), AlertDialog.image_cross);
    }
    }

    @Override
    public ObservableList<User> Affichertout() throws SQLException {
        ObservableList<User> list = FXCollections.observableArrayList();
        String requete = "select * from user where roles='a:0:{}'";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new User(rs.getInt("id"),rs.getString("First_Name"), rs.getString("Last_Name"), rs.getString("email"), rs.getInt("User_Number"), rs.getString("username"), rs.getInt("User_age"), rs.getString("User_Image"),rs.getInt("enabled")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
        public ObservableList<User> Affichertoutpdf() throws SQLException {
        ObservableList<User> list = FXCollections.observableArrayList();
        String requete = "select * from user where roles='a:0:{}'";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new User(rs.getInt("id"),rs.getString("First_Name"), rs.getString("Last_Name"), rs.getString("email"), rs.getInt("User_Number"), rs.getString("username"), rs.getInt("User_age"), rs.getString("User_Image"),rs.getInt("enabled")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public int nombre_user() {

        int i = 0;
        String requete = "SELECT COUNT(*) as nbr FROM user where roles='a:0:{}'";

        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                i = rs.getInt("nbr");

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return i;
    }

    public int nombre_user_Client_Desactive() {

        int i = 0;
        String requete = "SELECT COUNT(*) as nbr FROM user where roles='a:0:{}' and enabled=0";

        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                i = rs.getInt("nbr");

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return i;
    }
    public int nombre_user_Client_Active() {

        int i = 0;
        String requete = "SELECT COUNT(*) as nbr FROM user where roles='a:0:{}' and enabled = 1";

        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                i = rs.getInt("nbr");

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return i;
    }


    public User get_User(int id) {
        String requete = "SELECT * FROM `user` WHERE (id =" + String.valueOf(id) + ")";

        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

               
                User us = new User(rs.getString("First_Name"), rs.getString("Last_Name"), rs.getString("email"), rs.getInt("User_Number"), rs.getString("username"), (rs.getString("password").replace("$2y$", "$2a$")), rs.getString("roles"), rs.getInt("User_age"), rs.getString("User_Image"));
             
                return us;

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }



    public ObservableList<User> serach(String cas) throws SQLException {
        ObservableList<User> list = FXCollections.observableArrayList();
        String requete = "select * from user where ( email LIKE '%" + cas + "%' or First_Name LIKE '%" + cas + "%' or Last_Name LIKE '%" + cas + "%' or username LIKE '%" + cas + "%' or User_Number LIKE '%" + cas + "%' or User_age LIKE '%" + cas + "%' or enabled LIKE '%" + cas + "%' ) And  roles='a:0:{}'";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

       list.add(new User(rs.getInt("id"),rs.getString("First_Name"), rs.getString("Last_Name"), rs.getString("email"), rs.getInt("User_Number"), rs.getString("username"), rs.getInt("User_age"), rs.getString("User_Image"),rs.getInt("enabled")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public User forget(String mail) throws SQLException {
        User u = null;
        String requete = "select * from user where email =\"" + mail + "\" ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            
                u = new User(rs.getInt("id"),rs.getString("First_Name"), rs.getString("Last_Name"), rs.getString("email"), rs.getInt("User_Number"), rs.getString("username"), (rs.getString("password").replace("$2y$", "$2a$")), rs.getString("roles"), rs.getInt("User_age"), rs.getString("User_Image"));
         
       
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return u;
    }

}
