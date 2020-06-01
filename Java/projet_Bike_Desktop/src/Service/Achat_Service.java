/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import static Service.Abonnement_Service.getMd5;
import Service.Abonnement_Service;
import Utils.MyConnexion;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import static controllers.FrontCrontroller.ACOUNT_SID;
import static controllers.FrontCrontroller.AUTH_TOKEN;
import entites.Abonnement;
import entites.Achat;
import entites.Session;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author aymen
 */
public class Achat_Service {

    private Connection c = MyConnexion.getInsCon().getcnx();

    public void supprimer_offre(int id) throws SQLException {
        PreparedStatement ps;
        String query = "DELETE FROM `achat` WHERE id=? ";
        ps = c.prepareStatement(query);
        ps.setInt(1, id);
        ps.execute();
    }

    public Achat recap_offre(int id_u) {
        System.out.println(id_u);

        String requete = "select * from achat WHERE (ID_U =" + String.valueOf(id_u) + ")";
        try {
            PreparedStatement ps = c.prepareStatement(requete);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Achat achat = new Achat(rs.getInt("id"), rs.getInt("ID_U"), rs.getInt("ID_A"), rs.getDate("DATE_D"), rs.getDate("DATE_F"), rs.getFloat("prix"), rs.getString("image"));
                return achat;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;

    }

    public void achat_un_mois(Achat a, Abonnement abonnment) throws SQLException {

        String message_twilo = "Bonjour,Vous venez d’acheter un abonnement  " + abonnment.getLib_C() + " au nom de " + Session.get().getLname() + " " + Session.get().getFname() + " valable pendant un mois à partir du " + new java.sql.Date(new Date().getTime()) + "Nous vous remercions pour votre précieuse confiance Bienvenue à Bike";
        Twilio.init(ACOUNT_SID, AUTH_TOKEN);
        String numero_user = "+216" + String.valueOf(Session.get().getNumber());
        Message message = Message
                .creator(new PhoneNumber(numero_user), // to
                        new PhoneNumber("+13213254739"), // from
                        message_twilo)
                .create();
        abonnment.setQuantite(abonnment.getQuantite() - 1);

        new Abonnement_Service().Modifier(abonnment, abonnment.getId_A());

        System.out.println(message.getSid());

        String code = getMd5(abonnment.getLibelle() + "un mois" + Session.get().getFname());

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Calendar C1 = Calendar.getInstance();
        C1.add(Calendar.DATE, 30);
        String data = "Libelle : " + abonnment.getLibelle() + "<br>  Description : " + abonnment.getDescription() + " <br>  prix :" + String.valueOf(a.getPrix()) + " DT <br> Categorie : " + abonnment.getLib_C() + " <br> Site : " + abonnment.getLib_S() + "<br> achetée par MR/MD " + Session.get().getLname() + " " + Session.get().getFname() + "<br> Le " + new Date() + "<br> valable jusqu a " + C1.getTime() + "<br> Merci pour votre confiance &#128525;";

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
            if (ImageIO.write(bufferedImage, "png", new File("C:/xampp/htdocs/bike/web/uploads/images/qruser/" + code + ".png"))) {
                System.out.println("-- saved");
            }
            System.out.println("QR created successfully....");

        } catch (WriterException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }

        PreparedStatement ps;
        String query = "INSERT INTO `achat`(`ID_U`, `ID_A`, `DATE_D`, `DATE_F`, `prix`, `Image`) VALUES (?,?,?,?,?,?)";
        try {

            ps = c.prepareStatement(query);

            ps.setInt(1, a.getID_U());
            ps.setInt(2, a.getID_A());
            ps.setDate(3, new java.sql.Date(new Date().getTime()));
            Calendar C = Calendar.getInstance();
            C.add(Calendar.DATE, 30);

            ps.setDate(4, new java.sql.Date(C.getTime().getTime()));
            ps.setString(5, String.valueOf(a.getPrix()));
            ps.setString(6, code + ".png");
            ps.execute();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void achat_trois_mois(Achat a, Abonnement abonnment) throws SQLException {

        String message_twilo = "Bonjour,Vous venez d’acheter un abonnement  " + abonnment.getLib_C() + " au nom de " + Session.get().getLname() + " " + Session.get().getFname() + " valable pendant trois mois à partir du " + new java.sql.Date(new Date().getTime()) + "Nous vous remercions pour votre précieuse confiance Bienvenue à Bike";
        Twilio.init(ACOUNT_SID, AUTH_TOKEN);
        String numero_user = "+216" + String.valueOf(Session.get().getNumber());
        Message message = Message
                .creator(new PhoneNumber(numero_user), // to
                        new PhoneNumber("+13213254739"), // from
                        message_twilo)
                .create();
        abonnment.setQuantite(abonnment.getQuantite() - 1);

        new Abonnement_Service().Modifier(abonnment, abonnment.getId_A());

        System.out.println(message.getSid());

        String code = getMd5(abonnment.getLibelle() + "trois mois" + Session.get().getFname());

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Calendar C1 = Calendar.getInstance();
        C1.add(Calendar.DATE, 90);
        String data = "Libelle : " + abonnment.getLibelle() + "<br>  Description : " + abonnment.getDescription() + " <br>  prix :" + String.valueOf(a.getPrix()) + " DT <br> Categorie : " + abonnment.getLib_C() + " <br> Site : " + abonnment.getLib_S() + "<br> achetée par MR/MD " + Session.get().getLname() + " " + Session.get().getFname() + "<br> Le " + new Date() + "<br> valable jusqu a " + C1.getTime() + "<br> Merci pour votre confiance &#128525;";

        float prix = (float) (a.getPrix() - (a.getPrix() * 0.1));
        a.setPrix(prix);
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
            if (ImageIO.write(bufferedImage, "png", new File("C:/xampp/htdocs/bike/web/uploads/images/qruser/" + code + ".png"))) {
                System.out.println("-- saved");
            }
            System.out.println("QR created successfully....");

        } catch (WriterException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }

        PreparedStatement ps;
        String query = "INSERT INTO `achat`(`ID_U`, `ID_A`, `DATE_D`, `DATE_F`, `prix`, `Image`) VALUES (?,?,?,?,?,?)";
        try {

            ps = c.prepareStatement(query);

            ps.setInt(1, a.getID_U());
            ps.setInt(2, a.getID_A());
            ps.setDate(3, new java.sql.Date(new Date().getTime()));
            Calendar C = Calendar.getInstance();
            C.add(Calendar.DATE, 90);

            ps.setDate(4, new java.sql.Date(C.getTime().getTime()));
            ps.setString(5, String.valueOf(a.getPrix()));
            ps.setString(6, code + ".png");
            ps.execute();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void _trois_mois_passer_premieum(Achat a, Abonnement abonnment) throws SQLException {

        String message_twilo = "Bonjour,Vous avez passer en  un abonnement  " + abonnment.getLib_C() + " au nom de " + Session.get().getLname() + " " + Session.get().getFname() + " valable pendant trois mois à partir du " + new java.sql.Date(new Date().getTime()) + "Nous vous remercions pour votre précieuse confiance Bienvenue à Bike";
        Twilio.init(ACOUNT_SID, AUTH_TOKEN);
        String numero_user = "+216" + String.valueOf(Session.get().getNumber());
        Message message = Message
                .creator(new PhoneNumber(numero_user), // to
                        new PhoneNumber("+13213254739"), // from
                        message_twilo)
                .create();
        abonnment.setQuantite(abonnment.getQuantite() - 1);

        new Abonnement_Service().Modifier(abonnment, abonnment.getId_A());

        System.out.println(message.getSid());

        String code = getMd5(abonnment.getLibelle() + "trois mois" + Session.get().getFname());

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Calendar C1 = Calendar.getInstance();
        C1.add(Calendar.DATE, 90);
        String data = "Libelle : " + abonnment.getLibelle() + "<br>  Description : " + abonnment.getDescription() + " <br>  prix :" + String.valueOf(a.getPrix()) + " DT <br> Categorie : " + abonnment.getLib_C() + " <br> Site : " + abonnment.getLib_S() + "<br> achetée par MR/MD " + Session.get().getLname() + " " + Session.get().getFname() + "<br> Le " + new Date() + "<br> valable jusqu a " + C1.getTime() + "<br> Merci pour votre confiance &#128525;";

        float prix = (float) (a.getPrix() - (a.getPrix() * 0.1));
        a.setPrix(prix);
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
            if (ImageIO.write(bufferedImage, "png", new File("C:/xampp/htdocs/bike/web/uploads/images/qruser/" + code + ".png"))) {
                System.out.println("-- saved");
            }
            System.out.println("QR created successfully....");

        } catch (WriterException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }

        PreparedStatement ps;
        String query = "INSERT INTO `achat`(`ID_U`, `ID_A`, `DATE_D`, `DATE_F`, `prix`, `Image`) VALUES (?,?,?,?,?,?)";
        try {

            ps = c.prepareStatement(query);

            ps.setInt(1, a.getID_U());
            ps.setInt(2, a.getID_A());
            ps.setDate(3, new java.sql.Date(new Date().getTime()));
            Calendar C = Calendar.getInstance();
            C.add(Calendar.DATE, 90);

            ps.setDate(4, new java.sql.Date(C.getTime().getTime()));
            ps.setString(5, String.valueOf(a.getPrix()));
            ps.setString(6, code + ".png");
            ps.execute();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void achat_un_ans(Achat a, Abonnement abonnment) throws SQLException {

        String message_twilo = "Bonjour,Vous venez d’acheter un abonnement  " + abonnment.getLib_C() + " au nom de " + Session.get().getLname() + " " + Session.get().getFname() + " valable pendant un ans à partir du " + new java.sql.Date(new Date().getTime()) + "Nous vous remercions pour votre précieuse confiance Bienvenue à Bike";
        Twilio.init(ACOUNT_SID, AUTH_TOKEN);
        String numero_user = "+216" + String.valueOf(Session.get().getNumber());
        Message message = Message
                .creator(new PhoneNumber(numero_user), // to
                        new PhoneNumber("+13213254739"), // from
                        message_twilo)
                .create();
        abonnment.setQuantite(abonnment.getQuantite() - 1);

        new Abonnement_Service().Modifier(abonnment, abonnment.getId_A());

        System.out.println(message.getSid());

        String code = getMd5(abonnment.getLibelle() + "un ans" + Session.get().getFname());

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Calendar C1 = Calendar.getInstance();
        C1.add(Calendar.DATE, 365);
        String data = "Libelle : " + abonnment.getLibelle() + "<br>  Description : " + abonnment.getDescription() + " <br>  prix :" + String.valueOf(a.getPrix()) + " DT <br> Categorie : " + abonnment.getLib_C() + " <br> Site : " + abonnment.getLib_S() + "<br> achetée par MR/MD " + Session.get().getLname() + " " + Session.get().getFname() + "<br> Le " + new Date() + "<br> valable jusqu a " + C1.getTime() + "<br> Merci pour votre confiance &#128525;";

        float prix = (float) (a.getPrix() - (a.getPrix() * 0.1));
        a.setPrix(prix);
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
            if (ImageIO.write(bufferedImage, "png", new File("C:/xampp/htdocs/bike/web/uploads/images/qruser/" + code + ".png"))) {
                System.out.println("-- saved");
            }
            System.out.println("QR created successfully....");

        } catch (WriterException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }

        PreparedStatement ps;
        String query = "INSERT INTO `achat`(`ID_U`, `ID_A`, `DATE_D`, `DATE_F`, `prix`, `Image`) VALUES (?,?,?,?,?,?)";
        try {

            ps = c.prepareStatement(query);

            ps.setInt(1, a.getID_U());
            ps.setInt(2, a.getID_A());
            ps.setDate(3, new java.sql.Date(new Date().getTime()));
            Calendar C = Calendar.getInstance();
            System.out.println(C.toString());
            C.add(Calendar.DATE, 365);

            ps.setDate(4, new java.sql.Date(C.getTimeInMillis()));
            ps.setString(5, String.valueOf(a.getPrix()));
            ps.setString(6, code + ".png");
            ps.execute();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void mise_a_jour() throws SQLException {
        PreparedStatement ps;
        String query = "DELETE FROM `achat` WHERE DATEDIFF(DATE_F, NOW()) < 0";
        ps = c.prepareStatement(query);

        ps.execute();
    }

    public void passerPremieum(int id_u) throws SQLException {

        if (nombre_reservation(id_u) == 5) {

            Achat a = recap_offre(id_u);
if (a!=null)
{
       supprimer_offre(a.getId());
            int id_a = return_produit_categorie_premium();

            Abonnement abonnment = new Abonnement_Service().get_abonnment(id_a);

            Achat achatnew = new Achat(0, id_u, id_a, 0, "c");
            _trois_mois_passer_premieum(achatnew, abonnment);
   
}
      
        }

    }

    public int return_produit_categorie_premium() {
        int id = 0;
        String requete = "SELECT * FROM `produit` p inner join `abonnement` a where ( p.ID_P=a.ID_P and p.Lib_C='Premium' ) ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                id = rs.getInt("ID_A");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return id;
    }

    public int nombre_reservation(int id_u) {
        int nombre = 0;
        String requete = "SELECT count(*) as nb FROM `reservation` WHERE ID_U = " + String.valueOf(id_u);
        try {
            PreparedStatement ps = c.prepareStatement(requete);

            ResultSet rs = ps.executeQuery();
            while (rs.first()) {
                nombre = rs.getInt("nb");
                break;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return nombre;
    }
}
