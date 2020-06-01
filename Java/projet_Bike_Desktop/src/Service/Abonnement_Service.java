/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Alert.AlertDialog;
import IService.IService;
import Utils.MyConnexion;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import doryan.windowsnotificationapi.fr.Notification;
import entites.Abonnement;
import entites.Produit;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.TrayIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.imageio.ImageIO;

/**
 *
 * @author aymen
 */
public class Abonnement_Service implements IService<Abonnement> {

    private Connection c = MyConnexion.getInsCon().getcnx();
    //hedha khalih
public static String getMd5(String input) 
    { 
        try { 
  
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    } 
//hedha khalih
    @Override
    public void Ajouter(Abonnement t) throws SQLException {

 



        Produit_Service serviceproduit = new Produit_Service();
        String code = getMd5(t.getLibelle());
           t.setImage(code+".png");
        Produit p = (Produit) t;
        serviceproduit.Ajouter(p);
        int id = serviceproduit.recuperer_ID_P(p);
           QRCodeWriter qrCodeWriter = new QRCodeWriter();
            
            String data = "Libelle : "+t.getLibelle()+"<br>  Description : "+ t.getDescription()+" <br>  prix :"+ String.valueOf(t.getPrix()) +" DT <br> Categorie : "+ t.getLib_C() +" <br> Site : "+ t.getLib_S()+"<br> Merci pour votre confiance &#128525;";
;
            int width = 300;
            int height = 300;
            
            BufferedImage bufferedImage = null;
            try {
                BitMatrix byteMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height);
                bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                bufferedImage.createGraphics();
                
                Graphics2D image = (Graphics2D) bufferedImage.getGraphics();
                image.setColor(Color.WHITE);
                image.fillRect(0, 0, width, height);
                image.setColor(Color.BLACK);
                
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        if (byteMatrix.get(i, j)) {
                            image.fillRect(i, j, 1, 1);
                        }
                    }
                }
                if (ImageIO.write(bufferedImage, "png", new File("C:/xampp/htdocs/bike/web/uploads/images/qrproduit/"+code+".png")))
            {
                System.out.println("-- saved");
            }
                System.out.println("QR created successfully....");
                
            } catch (WriterException ex) {
              System.out.println(ex); 
            } catch (IOException ex) {
                System.out.println(ex);   }   
            
     
        try {
            String requete = "INSERT INTO `abonnement`(`ID_P`, `quantite`) VALUES (?,?)";
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setInt(1, id);
            pst.setInt(2, t.getQuantite());

            pst.execute();
            if (id != 0)
            {
                       AlertDialog.showNotification("Ajout", "Abonnment Ajoutee", AlertDialog.image_checked);
            }


        } catch (SQLException ex) {
           System.out.println(ex); 
        }
    }
    //hedha khalih

    @Override
    public void Supprimer(int t) throws SQLException {

        //Acheter_Service s = new Acheter_Service();
      //  s.Supprimer(t);

        Produit_Service services = new Produit_Service();
        services.Supprimer(recuperer_id_P_Abonnement(t));
        String requete = "DELETE FROM `abonnement` WHERE `ID_A`=" + String.valueOf(t) + "";
        try {

            PreparedStatement pst = c.prepareStatement(requete);
            pst.execute(requete);
            System.out.println("abonnement supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void Supprimer_par_id_p(int t) throws SQLException {

        //Acheter_Service s = new Acheter_Service();
      //  s.Supprimer(t);

        Produit_Service services = new Produit_Service();
        services.Supprimer(t);
        String requete = "DELETE FROM `abonnement` WHERE `ID_P`=" + String.valueOf(t) + "";
        try {

            PreparedStatement pst = c.prepareStatement(requete);
            pst.execute(requete);
            System.out.println("abonnement supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    //hedha khalih
    @Override
    public void Modifier(Abonnement t, int id_A) throws SQLException {
        PreparedStatement ps;
        String lib = t.getLibelle();
        String image = t.getImage();
        String des = t.getDescription();
        float prix = t.getPrix();
        String Lib_c = t.getLib_C();
        String Lib_s = t.getLib_S();
        int quantite = t.getQuantite();
        int id_P = recuperer_id_P_Abonnement(id_A);
        Produit_Service services = new Produit_Service();
        services.Modifier((Produit) t, id_P);
        String query = "UPDATE `abonnement` SET `quantite`=? WHERE ID_A=?";
        ps = c.prepareStatement(query);
        ps.setInt(2, id_A);
        ps.setInt(1, quantite);
        ps.execute();
    }

    /*
    @Override
    public void Affichertout() throws SQLException {
        System.out.println("les abonnements : ");
        String requete = "SELECT * FROM `abonnement`";
        int id = 0;
        Produit_Service services = new Produit_Service();
        try {
            PreparedStatement ps = c.prepareStatement(requete);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("ID_P");
                services.afficher_par_id(id);
                System.out.print(rs.getInt("quantite"));

                System.out.println("");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
     */
    public int recuperer_id_P_Abonnement(int id_Abonnement) {
        int id = 0;
        String requete = "SELECT `ID_P` FROM `abonnement` WHERE ID_A=?";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ps.setInt(1, id_Abonnement);
            ResultSet rs = ps.executeQuery();
            while (rs.first()) {
                id = rs.getInt("ID_P");
                break;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;

    }


    
    
    
    


    public void acheterunabonmentunmois(int idA, int idU) throws SQLException {

        Statement ste;
        ste = c.createStatement();
        float prix = 0;
        int ID_P = recuperer_id_P_Abonnement(idA);
        ResultSet rs = ste.executeQuery("select * from produit where ID_P=" + String.valueOf(ID_P) + " ");
        while (rs.next()) {
            prix = rs.getFloat("Prix");

        }

      //  Acheter a = new Acheter(idU, idA, new Date(), new Date(), prix);

       // Acheter_Service s = new Acheter_Service();

    //    s.Ajouter(a);

    }

    public void acheterunabonmenttroismois(int idA, int idU) throws SQLException {
        Statement ste;
        ste = c.createStatement();
        float prix = 0;
        int ID_P = recuperer_id_P_Abonnement(idA);
        ResultSet rs = ste.executeQuery("select * from produit where ID_P=" + String.valueOf(ID_P) + " ");
        while (rs.next()) {
            prix = rs.getFloat("Prix");

        }

     //   Acheter a = new Acheter(idU, idA, new Date(), new Date(), prix);

       // Acheter_Service s = new Acheter_Service();

   //     s.ajoutertroismois(a);

    }

    public void acheterunabonmentunans(int idA, int idU) throws SQLException {

        Statement ste;
        ste = c.createStatement();
        float prix = 0;
        int ID_P = recuperer_id_P_Abonnement(idA);
        ResultSet rs = ste.executeQuery("select * from produit where ID_P=" + String.valueOf(ID_P) + " ");
        while (rs.next()) {
            prix = rs.getFloat("Prix");

        }

       // Acheter a = new Acheter(idU, idA, new Date(), new Date(), prix);

       // Acheter_Service s = new Acheter_Service();

     //   s.ajouterunans(a);

    }

    // hedhi tabka
    @Override
    public ObservableList<Abonnement> Affichertout() throws SQLException {
        ObservableList<Abonnement> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `abonnement` a Inner JOIN produit p where a.ID_P=p.ID_P";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Abonnement(rs.getInt("quantite"),rs.getInt("ID_A"), rs.getString("Libelle"), rs.getString("Lib_C"), rs.getString("Lib_S"), rs.getString("Image"), rs.getString("Description"), rs.getFloat("Prix")));

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;

    }

   
// hedhi tabka
    public ObservableList<Abonnement> serach(String cas) throws SQLException {
        ObservableList<Abonnement> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `abonnement` a Inner JOIN produit p where a.ID_P=p.ID_P AND  ( p.Libelle LIKE '%" + cas + "%'or  p.Description LIKE '%" + cas + "%' or  p.Prix LIKE '%" + cas + "%' or  p.Lib_C LIKE '%" + cas + "%' or  p.Lib_S LIKE '%" + cas + "%' or  a.quantite LIKE '%" + cas + "%' )";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                    list.add(new Abonnement(rs.getInt("quantite"),rs.getInt("ID_A"), rs.getString("Libelle"), rs.getString("Lib_C"), rs.getString("Lib_S"), rs.getString("Image"), rs.getString("Description"), rs.getFloat("Prix")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    
    public void  return_quantite() throws Exception
    {
        int alerte=0;
              int quantite = 0;
              int id_p=0;
        String requete = "SELECT * FROM `abonnement` a Inner JOIN produit p where a.ID_P=p.ID_P ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
             quantite=rs.getInt("quantite");
     
             
             if (quantite==5)
             {
                 Notification.sendNotification("Quantité abonnement presque epuisé", "La quantite de l'abonnement "+rs.getString("Libelle")+" est égale à 5",TrayIcon.MessageType.INFO);
                
             }
                    if (quantite==0)
             {
                  Notification.sendNotification("Supprition automatique", "La quantite de l'abonnement "+rs.getString("Libelle")+" est égale à 0",TrayIcon.MessageType.INFO);
                
                  Supprimer_par_id_p(rs.getInt("ID_P"));
           
              
               
             }
              

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
    public Abonnement get_Abonnement_affichage(int i) {
        Abonnement p = null;
        int nombre = 0;
        String requete = "SELECT * FROM `abonnement` a Inner JOIN produit p  where (a.ID_P=p.ID_P  AND a.quantite!=0)";
           try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                if (i == nombre) {
     
                    p = new Abonnement(rs.getInt("quantite"),rs.getInt("ID_A"), rs.getString("Libelle"), rs.getString("Lib_C"), rs.getString("Lib_S"), rs.getString("Image"), rs.getString("Description"), rs.getFloat("Prix"));

                }
                nombre++;
                         }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return p;

    }
             public int Affichertaille() throws SQLException {
        int nbr = 0;
        String requete = "SELECT COUNT(*) as nbr FROM `abonnement` a Inner JOIN produit p  where (a.ID_P=p.ID_P   AND a.quantite!=0)"       ;
                try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
              nbr=rs.getInt("nbr");

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return nbr;

    }
             
             
             public Abonnement get_abonnment( int id_a)
             {
               
        
         String requete = "SELECT * FROM `abonnement` a Inner JOIN produit p where ( a.ID_P=p.ID_P and a.	ID_A =" + String.valueOf(id_a) + ")";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
               
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
        
                Abonnement    p = new Abonnement(rs.getInt("quantite"),rs.getInt("ID_A"), rs.getString("Libelle"), rs.getString("Lib_C"), rs.getString("Lib_S"), rs.getString("Image"), rs.getString("Description"), rs.getFloat("Prix"));
return p;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
                 
                 
                 return null;
                 
             }

}
