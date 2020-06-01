/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Service.Abonnement_Service;
import Service.Achat_Service;
import Service.Commande_service;
import Service.EvenementService;
import Service.Maintenance_Service;
import Service.Panier_Service;
import Service.ReservationService;
import Service.Site_Service;
import Service.User_Service;
import Service.Velo_Service;
import Service.reclamation_Service;
import Utils.mail;
import com.chat.client.ClientApplication;
import com.googleMap.GoogleMapBike;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import doryan.windowsnotificationapi.fr.Notification;
import entites.Abonnement;
import entites.Achat;
import entites.BadWords;
import entites.Maitenance;
import entites.Reservation;
import entites.Session;
import entites.User;
import entites.Velo;
import entites.reclamation;
import java.awt.TrayIcon;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class FrontCrontroller implements Initializable {

    @FXML
    private Pane pnl_Reservation;
    @FXML
    private ScrollPane scrollpaneVelo;
    @FXML
    private HBox hox_Velo;
    @FXML
    private Pane pnl_Home_Sites;
    @FXML
    private ScrollPane scrollpaneSite;
    @FXML
    private HBox hbox_site;
    @FXML
    private Pane pnl_Abonnement;
    @FXML
    private ScrollPane scrollpane_Abonnment;
    @FXML
    private HBox hbox_event;
    @FXML
    private Pane pnl_Event;
    @FXML
    private ScrollPane scrollpaneEvent;
    @FXML
    private Pane pnl_edit;
    @FXML
    private TextField txtFirstname;
    @FXML
    private TextField txtLastname;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtUSername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtNumber;
    @FXML
    private ImageView imageviwerphoto;
    @FXML
    private ComboBox<Integer> txtage;
    @FXML
    private Button edit_profil;
    @FXML
    private ImageView imageview_user;
    @FXML
    private Pane pnl_Maintenance;
    @FXML
    private TableView<Maitenance> tab_Maintenance;
    @FXML
    private TableColumn<Maitenance, Integer> col_id_main;
    @FXML
    private TableColumn<Maitenance, Date> col_DateD_main;
    @FXML
    private TableColumn<Maitenance, Date> col_DateF_main;
    @FXML
    private TableColumn<Maitenance, String> col_Problem_main;
    @FXML
    private TableColumn<Maitenance, Float> col_prix_main;
    @FXML
    private TableColumn<Maitenance, String> col_Etat_main;
    @FXML
    private TableColumn<Maitenance, String> col_Type_main;
    @FXML
    private Button btn_ajout_main;
    @FXML
    private TextArea txt_contenu_maintenance;
    @FXML
    private ComboBox<String> combo_type_maintenance;
    @FXML
    private ImageView imageview_maintenance;
    @FXML
    private Button btn_accepter_prix;
    @FXML
    private ImageView Image_Maintenance;
    @FXML
    private TableView<reclamation> tab_Reclamation;
    @FXML
    private Pane pnl_Reclamation;
    @FXML
    private TableColumn<reclamation, Integer> col_id_rec;
    @FXML
    private TableColumn<reclamation, String> col_contenu_rec;
    @FXML
    private TableColumn<reclamation, String> col_Etat_rec;
    @FXML
    private TableColumn<reclamation, String> col_Type_rec;
    @FXML
    private TableColumn<reclamation, String> col_Traite_rec;
    @FXML
    private Button btn_ajout_reclamation;
    @FXML
    private TextArea txt_contenu_reclamation;
    @FXML
    private ComboBox<String> combo_type_reclamation;
    @FXML
    private ComboBox<String> combo_etat_reclamation;
    @FXML
    private ImageView imageview_reclamation;
    @FXML
    private ImageView Image_Reclamation;
    @FXML
    private Button pdf;
    @FXML
    private ImageView image_user;
    @FXML
    private Label username;
    @FXML
    private Button btn_Home;
    @FXML
    private ImageView btn_Acceuil;
    @FXML
    private Button btn_Product_Abonnement;
    @FXML
    private Button btn_Book_Reservation;
    @FXML
    private Button btn_Maintenance;
    @FXML
    private Button btn_Reclamation;
    @FXML
    private Button btn_Event;
    @FXML
    private Button btn_Edit;
    @FXML
    private Button btn_Sign_Out;
    @FXML
    private HBox hbox_abonnment;
    @FXML
    private Pane pnl_mon_offre;
    @FXML
    private ImageView imageviwerphoto1;
    @FXML
    private Button Delete_offre_user;
    @FXML
    private ImageView imageview_abonnment_offre_qr;
    @FXML
    private Label txt_label_offre_abonnment;
    @FXML
    private Label txt_description_offre_abonnment;
    @FXML
    private Label txt_categorie_offre_abonnment;
    @FXML
    private Label txt_site_offre_abonnment;
    @FXML
    private Label txt_dateD_offre_abonnment;
    @FXML
    private Label txt_dateF_offre_abonnment;
    @FXML
    private Button btn_Mon_offre;
    @FXML
    private Pane pnl_mes_reservation;
    @FXML
    private ImageView imageviwerphoto11;
    @FXML
    private Button btn_Mes_Reservations;
    @FXML
    private TableView<Reservation> tab_mes_reservation;
    @FXML
    private TableColumn<Reservation, Integer> col_id_reservation;
    @FXML
    private TableColumn<Reservation, Float> col_prix_reservation;
    @FXML
    private TableColumn<Reservation, Date> col_Dated_reservation;
    @FXML
    private TableColumn<Reservation, Date> col_Datef_reservation;
    @FXML
    private ImageView image_velo_reservation;
    @FXML
    private Label lbl_prix_total_reservation;
    private TableColumn<Reservation, String> col_btnDelet_resrvation;
    User usersession = Session.get();
    private String nomImage_user = "";
    private String nomImage_Reclamation = "";
    private String nomImage_Maintenance = "";
    User_Service service_user = new User_Service();
    Achat_Service service_achat = new Achat_Service();
    Abonnement_Service service_abonnment = new Abonnement_Service();
    Site_Service service_site = new Site_Service();
    Velo_Service service_velo = new Velo_Service();
    ReservationService service_reservation = new ReservationService();
    reclamation_Service service_reclamation = new reclamation_Service();
    Maintenance_Service service_maintenance = new Maintenance_Service();
       EvenementService service_Event = new EvenementService();
    private int taille_abonment = 0;
    private int taille_site = 0;
    private int taille_velo = 0;
     private int taille_Event = 0;
    private Maitenance maintenance_user=null;

    /*---------------------*/
    // statics
    static int indiceVelo = 0;
    static int indiceSite = 0;
    static int indiceAbonnement = 0;
     static int indiceEvent = 0;
    public static final String ACOUNT_SID = "ACce87cf249118c8fe7b077ae54a10209a";
    public static final String AUTH_TOKEN = "49a3d84ce854d2e88c1e55af43b98134";
    @FXML
    private Button btn_Supprimer_maintenance;

    /*-------------*/
    public void initialize(URL url, ResourceBundle rb) {
        
        /*--------------------*/
        // passer premieum
        try {
            service_achat.passerPremieum(Session.getCurrentSession());
           
        } catch (Exception ex) {
            Logger.getLogger(FrontCrontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
 /*-------------------*/
            //reclamation
        try {
            combo_type_reclamation.getItems().addAll("Abonnement", "Accessoire", "Velo", "Piece", "Site", "Maintenance", "Panier", "Others");
            combo_etat_reclamation.getItems().addAll("Mauvais", "Bien");

            combo_type_reclamation.getSelectionModel().select(0);
            combo_etat_reclamation.getSelectionModel().select(0);
            refrech_reclamation();

            setCellfromtabletoImage_reclamation();
        } catch (Exception ex) {

        }

        /*-------------------*/
        //maintenance
        try {
            combo_type_maintenance.getItems().addAll("accessoire", "Velo", "Piece", "Others");

            combo_type_maintenance.getSelectionModel().select(0);
            refrech_Maintenance();
            setCellfromtabletoImage_maintenance();
        } catch (Exception ex) {

        }

        /*-------------------*/
        //reservation 
        try {
            refrech_reservation();

        } catch (Exception e) {

        }
        setCellfromtabletoImage_reservation();

        col_btnDelet_resrvation = new TableColumn("Supprimer");
        javafx.util.Callback<TableColumn<Reservation, String>, TableCell<Reservation, String>> cellFactory
                = new Callback<TableColumn<Reservation, String>, TableCell<Reservation, String>>() {
            public TableCell call(final TableColumn<Reservation, String> param) {
                final TableCell<Reservation, String> cell = new TableCell<Reservation, String>() {

                    final Button btn = new Button("supprimer");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Reservation u = getTableView().getItems().get(getIndex());
                                service_reservation.supprimerReservation(u);

                                AlertDialog.showNotification("suppression confirmée!", "suppression a été bien faite", AlertDialog.image_checked);

                                try {
                                    refrech_reservation();
                                    String ImageUrl = "http://localhost/bike/web/uploads/images/";

                                    Image image = new Image(ImageUrl);
                                    image_velo_reservation.setImage(image);
                                } catch (Exception ex) {

                                }

                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        col_btnDelet_resrvation.setCellFactory(cellFactory);
        tab_mes_reservation.getColumns().add(col_btnDelet_resrvation);
        /*--------------*/

        try {
            /*-------------------*/
            // abonment
            taille_abonment = service_abonnment.Affichertaille();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        Node[] nodes_abonnment = new Node[taille_abonment];
        scrollpane_Abonnment.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        for (indiceAbonnement = 0; indiceAbonnement < taille_abonment; indiceAbonnement++) {
            try {

                nodes_abonnment[indiceAbonnement] = FXMLLoader.load(getClass().getResource("/GUI/Item_abonnment.fxml"));

                hbox_abonnment.getChildren().add(nodes_abonnment[indiceAbonnement]);
            } catch (IOException e) {

            }
        }
      /*--------------*/

        try {
            /*-------------------*/
            // Event
            taille_Event = service_Event.Affichertaille();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        Node[] nodes_Event = new Node[taille_Event];
        scrollpaneEvent.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        for (indiceEvent = 0; indiceEvent < taille_Event; indiceEvent++) {
            try {

                nodes_Event[indiceEvent] = FXMLLoader.load(getClass().getResource("/GUI/Item_Event.fxml"));

                hbox_event.getChildren().add(nodes_Event[indiceEvent]);
            } catch (IOException e) {

            }
        }
        /*-----------------*/
        try {
            /*-------------------*/
            // site
            taille_site = service_site.Affichertaille();
            System.out.println(taille_site);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        Node[] nodes_site = new Node[taille_site];
        scrollpaneSite.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        for (indiceSite = 0; indiceSite < taille_site; indiceSite++) {
            try {

                nodes_site[indiceSite] = FXMLLoader.load(getClass().getResource("/GUI/Item_Site.fxml"));

                hbox_site.getChildren().add(nodes_site[indiceSite]);
            } catch (IOException e) {

            }
        }
        /*-----------------*/
        try {
            /*-------------------*/
            // velo
            taille_velo = service_velo.Affichertaille();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        Node[] nodes_velo = new Node[taille_velo];
        scrollpaneVelo.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        for (indiceVelo = 0; indiceVelo < taille_velo; indiceVelo++) {
            try {

                nodes_velo[indiceVelo] = FXMLLoader.load(getClass().getResource("/GUI/Item_Velo.fxml"));

                hox_Velo.getChildren().add(nodes_velo[indiceVelo]);
            } catch (IOException e) {

            }
        }

        /*-----------------*/
        //user
        String ImageUrl = "http://localhost/bike/web/uploads/images/";
        Image image = new Image(ImageUrl + usersession.getImage());
        image_user.setImage(image);
        username.setText(usersession.getFname() + " " + usersession.getLname());
        txtEmail.setText(usersession.getMail());
        txtFirstname.setText(usersession.getFname());
        txtLastname.setText(usersession.getLname());
        txtUSername.setText(usersession.getUsername());
        txtNumber.setText(String.valueOf(usersession.getNumber()));
        txtage.getItems().addAll(18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40);
        txtage.getSelectionModel().select(0);

        /*------------*/
        // user offre
        try {
            service_achat.mise_a_jour();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        refrech_offre();
        /*-----------------------*/

    }

    private void refrech_offre() {
        try {
            Achat achat = service_achat.recap_offre(Session.getCurrentSession());
            if (achat != null) {
                Abonnement ab = new Abonnement_Service().get_abonnment(achat.getID_A());
                txt_label_offre_abonnment.setText(ab.getLibelle());
                txt_description_offre_abonnment.setText(ab.getDescription());
                txt_dateD_offre_abonnment.setText(String.valueOf(achat.getDate_d()));
                txt_dateF_offre_abonnment.setText(String.valueOf(achat.getDate_f()));
                txt_site_offre_abonnment.setText(String.valueOf(ab.getLib_S()));
                txt_categorie_offre_abonnment.setText(String.valueOf(ab.getLib_C()));
                String ImageUrl_offre = "http://localhost/bike/web/uploads/images/qruser/";
                System.out.println(achat.getImage());
                Image image_offre = new Image(ImageUrl_offre + achat.getImage());

                imageview_abonnment_offre_qr.setImage(image_offre);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void update_profial(ActionEvent event) throws Exception {

        if (txtFirstname.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de firstname", AlertDialog.image_cross);
        } else if (txtFirstname.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur firstname !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        } else if (txtFirstname.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur lastname !", "il faut saisir des caracteres !", AlertDialog.image_cross);
        } else if (txtLastname.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de lastname", AlertDialog.image_cross);
        } else if (txtEmail.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de email", AlertDialog.image_cross);
        } else if (!txtEmail.getText().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            AlertDialog.showNotification("Erreur Email !", "email incorrect ", AlertDialog.image_cross);
        } else if (txtUSername.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de Username", AlertDialog.image_cross);
        } else if (txtPassword.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de password", AlertDialog.image_cross);
        } else if (txtNumber.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de Number", AlertDialog.image_cross);
        } else if (txtNumber.getText().matches("^[a-zA-Z]+$")) {
            AlertDialog.showNotification("Erreur Telephone !", "numero de telephone incorrect", AlertDialog.image_cross);
        } else if (txtNumber.getText().length() != 8) {
            AlertDialog.showNotification("Erreur Telephone !", "il faut 8 chiffres ", AlertDialog.image_cross);
        } else if (nomImage_user.equals("")) {
            AlertDialog.showNotification("Erreur Image !", "Il faut inserer une photo", AlertDialog.image_cross);
        } else {
            String code = Abonnement_Service.getMd5(txtFirstname.getText() + txtLastname.getText()) + nomImage_user;

            User u = new User(txtFirstname.getText(), txtLastname.getText(), txtEmail.getText(), Integer.parseInt(txtNumber.getText()), txtUSername.getText(), txtPassword.getText(), "user", Integer.valueOf(txtage.getSelectionModel().getSelectedItem()), code);

            InputStream inStream = null;
            OutputStream outStream = null;

            try {

                File afile = new File("./src/images/" + nomImage_user);
                File bfile = new File("C:/xampp/htdocs/bike/web/uploads/images/" + code);

                inStream = new FileInputStream(afile);
                outStream = new FileOutputStream(bfile);

                byte[] buffer = new byte[1024];

                int length;
                //copy the file content in bytes
                while ((length = inStream.read(buffer)) > 0) {

                    outStream.write(buffer, 0, length);

                }

                inStream.close();
                outStream.close();

                System.out.println("File is copied successful!");

            } catch (IOException e) {
                e.printStackTrace();
            }

            service_user.Modifier(u, Session.getCurrentSession());

            User usersessionnew = Session.get();
            String ImageUrl = "http://localhost/bike/web/uploads/images/";
            Image image = new Image(ImageUrl + usersessionnew.getImage());

            image_user.setImage(image);
            username.setText(usersessionnew.getFname() + " " + usersessionnew.getLname());
            // mail.envoi(txtEmail.getText(), "COMPTE MODIFIé", "Bike vous annonce , <br> que votre compte a été modifié  \n ");
            Notification.sendNotification("Modification du Client " + usersession.getUsername() + " ", "Le client " + usersession.getUsername() + " a modifié ses informations. ", TrayIcon.MessageType.INFO);

        }

    }

    @FXML
    private void ajouter_maintenance(ActionEvent event) throws SQLException, Exception {
        BadWords.loadConfigs();

        {

            if (txt_contenu_maintenance.getText().equals("")) {
                AlertDialog.showNotification("Error !", "champ vide de contenu", AlertDialog.image_cross);

            } else if (BadWords.filterText(txt_contenu_maintenance.getText())) {

                AlertDialog.showNotification("Error !", "cette application n'autorise pas ces termes", AlertDialog.image_cross);

            } else if (nomImage_Maintenance.equals("")) {

                AlertDialog.showNotification("Error !", "il faut insee une photo", AlertDialog.image_cross);

            } else {

                String code = Abonnement_Service.getMd5(txt_contenu_maintenance.getText()) + nomImage_Maintenance;

                Maitenance man = new Maitenance(Session.getCurrentSession(), 0, txt_contenu_maintenance.getText(), "non réparé", code, combo_type_maintenance.getSelectionModel().getSelectedItem(), new Date(), new Date());

                service_maintenance.Ajouter(man);
                InputStream inStream = null;
                OutputStream outStream = null;

                try {

                    File afile = new File("./src/images/" + nomImage_Maintenance);
                    File bfile = new File("C:/xampp/htdocs/bike/web/uploads/images/Maitenance/" + code);

                    inStream = new FileInputStream(afile);
                    outStream = new FileOutputStream(bfile);

                    byte[] buffer = new byte[1024];

                    int length;
                    //copy the file content in bytes
                    while ((length = inStream.read(buffer)) > 0) {

                        outStream.write(buffer, 0, length);

                    }

                    inStream.close();
                    outStream.close();

                    System.out.println("File is copied successful!");

                } catch (IOException e) {
                    e.printStackTrace();
                }

                refrech_Maintenance();

                AlertDialog.showNotification("Maintenance", "Maintenance ajouter", AlertDialog.image_checked);

            }

        }
    }

    @FXML
    private void accepter_prix_maintenance(ActionEvent event) throws SQLException, Exception {
        
        if (maintenance_user!=null)
        {
            if (maintenance_user.getPrix()== 0)
            {
               AlertDialog.showNotification("Error !", "en attendent l acceptation de l admin", AlertDialog.image_cross);
     
            }
            else
            {
                
                
                  if (maintenance_user.getEtat().equals("accept"))
                {
                     maintenance_user.setEtat("en cours de reparation");
                service_maintenance.Modifier_user(maintenance_user, maintenance_user.getId());
                   AlertDialog.showNotification("Maintenance", "en cours de reparation", AlertDialog.image_checked);
     refrech_Maintenance();
                }
                else
                {
                         AlertDialog.showNotification("Erreur!!", "deja en reparation ", AlertDialog.image_cross);
      
                }
                
                
                
                
              
            }
        }
        else
        {
             AlertDialog.showNotification("Error !", "il faut selectionner une ligne", AlertDialog.image_cross);
   
        }
        
        
        
    }

    @FXML
    private void ajouter_reclamation(ActionEvent event) throws SQLException, Exception {
        BadWords.loadConfigs();

        {

            if (txt_contenu_reclamation.getText().equals("")) {
                AlertDialog.showNotification("Error !", "champ vide de contenu", AlertDialog.image_cross);

            } else if (BadWords.filterText(txt_contenu_reclamation.getText())) {

                AlertDialog.showNotification("Error !", "cette application n'autorise pas ces termes", AlertDialog.image_cross);

            } else if (nomImage_Reclamation.equals("")) {

                AlertDialog.showNotification("Error !", "il faut insee une photo", AlertDialog.image_cross);

            } else {

                String code = Abonnement_Service.getMd5(txt_contenu_reclamation.getText()) + nomImage_Reclamation;

                String traite = "Non Traite";
                if (combo_etat_reclamation.getSelectionModel().getSelectedItem().equals("Bien")) {
                    traite = "Traite";
                }

                reclamation rec = new reclamation(0, Session.getCurrentSession(), txt_contenu_reclamation.getText(), code, combo_type_reclamation.getSelectionModel().getSelectedItem(), combo_etat_reclamation.getSelectionModel().getSelectedItem(), traite);

                service_reclamation.Ajouter(rec);
                InputStream inStream = null;
                OutputStream outStream = null;

                try {

                    File afile = new File("./src/images/" + nomImage_Reclamation);
                    File bfile = new File("C:/xampp/htdocs/bike/web/uploads/images/" + code);

                    inStream = new FileInputStream(afile);
                    outStream = new FileOutputStream(bfile);

                    byte[] buffer = new byte[1024];

                    int length;
                    //copy the file content in bytes
                    while ((length = inStream.read(buffer)) > 0) {

                        outStream.write(buffer, 0, length);

                    }

                    inStream.close();
                    outStream.close();

                    System.out.println("File is copied successful!");

                } catch (IOException e) {
                    e.printStackTrace();
                }

                refrech_reclamation();

                AlertDialog.showNotification("Recalamtion", "Reclamation ajouter", AlertDialog.image_checked);

            }

        }
    }

    @FXML
    private void fairepdf_reclalamtion(ActionEvent event) {

        Document document = new Document();
        try {

            PdfWriter.getInstance(document, new FileOutputStream("reclamation_user.pdf"));
            document.open();
            Paragraph ph1 = new Paragraph("Bienvenue a Bike !");
            Paragraph ph2 = new Paragraph(".");
            PdfPTable table = new PdfPTable(5);

            com.itextpdf.text.Image image1 = com.itextpdf.text.Image.getInstance("/xampp/htdocs/bike/web/projet_Bike_Desktop/src/images/logo_bike.png");

            //Scale to new height and new width of image
            image1.scaleAbsolute(200, 200);
            //Add to document
            document.add(image1);

            //On créer l'objet cellule.
            PdfPCell cell;

            //contenu du tableau.
            table.addCell("Type : ");
            table.addCell("Contenu : ");
            table.addCell("etat : ");
            table.addCell("Traite :");
            table.addCell("Image : ");

            service_reclamation.Affichertout_user(Session.getCurrentSession()).forEach(e
                    -> {
                table.addCell(e.getType());

                table.setHorizontalAlignment(Element.ALIGN_CENTER);

                table.addCell(String.valueOf(e.getContenu()));
                table.addCell(String.valueOf(e.getEtat()));
                table.addCell(e.getEtat());

                try {
                    com.itextpdf.text.Image image_table = com.itextpdf.text.Image.getInstance("/xampp/htdocs/bike/web/uploads/images/" + e.getImage());
                    image_table.scaleAbsolute(200, 200);
                    table.addCell(image_table);
                } catch (BadElementException ex) {
                    Logger.getLogger(home_AbonemetController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(home_AbonemetController.class.getName()).log(Level.SEVERE, null, ex);
                }

                //Scale to new height and new width of image
                //Add to document
            }
            );
            document.add(ph1);
            document.add(ph2);
            document.add(table);
            document.addAuthor("Bike");
            AlertDialog.showNotification("Creation PDF ", "Votre fichier PDF a ete cree avec success", AlertDialog.image_checked);
        } catch (Exception e) {
            System.out.println(e);
        }
        document.close();

    }

    @FXML
    private void handleClicks(ActionEvent event) throws Exception {
        
          if (event.getSource() == btn_Event) {
          
         pnl_Event.toFront();
        }
        
        
        if (event.getSource() == btn_Mon_offre) {
            pnl_mon_offre.toFront();
            refrech_offre();
        }
      
        if (event.getSource() == btn_Edit) {
            pnl_edit.toFront();
        }
        if (event.getSource() == btn_Maintenance) {
            pnl_Maintenance.toFront();
        }
        if (event.getSource() == btn_Mes_Reservations) {
            pnl_mes_reservation.toFront();
        }
        if (event.getSource() == btn_Reclamation) {
            pnl_Reclamation.toFront();
        }
        if (event.getSource() == btn_Book_Reservation) {
            pnl_Reservation.toFront();
        }
        if (event.getSource() == btn_Product_Abonnement) {
            pnl_Abonnement.toFront();
        }
        if (event.getSource() == btn_Home) {
            pnl_Home_Sites.toFront();
        }
        if (event.getSource() == btn_Sign_Out) {
            Session.close();
            new Panier_Service().emtypanier();
            new Commande_service().emtyCommande();
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Login.fxml")));
            stage.setScene(scene);
            stage.show();

        }

    }

    @FXML
    private void chat(ActionEvent event) throws Exception {

        ClientApplication s = new ClientApplication();
        s.start(new Stage());

    }

    @FXML
    private void handleDragOver_user(DragEvent event) throws FileNotFoundException {

        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }

    }

    @FXML
    private void handleDrop_user(DragEvent event) throws FileNotFoundException {
        List<File> files = event.getDragboard().getFiles();
        Image img = new Image(new FileInputStream(files.get(0)));
        imageview_user.setImage(img);
        nomImage_user = files.get(0).getName();
    }

    @FXML
    private void handleDragOver_reclamation(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    private void handleDrop_reclamation(DragEvent event) throws FileNotFoundException {
        List<File> files = event.getDragboard().getFiles();
        Image img = new Image(new FileInputStream(files.get(0)));
        imageview_reclamation.setImage(img);
        nomImage_Reclamation = files.get(0).getName();
    }

    @FXML
    private void handleDragOver_maintenance(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    private void handleDrop_maintenance(DragEvent event) throws FileNotFoundException {
        List<File> files = event.getDragboard().getFiles();
        Image img = new Image(new FileInputStream(files.get(0)));
        imageview_maintenance.setImage(img);
        nomImage_Maintenance = files.get(0).getName();
    }

    @FXML
    private void delete_offre(ActionEvent event) {
        try {
            Achat achat = service_achat.recap_offre(Session.getCurrentSession());
            if (achat != null) {

                service_achat.supprimer_offre(achat.getId());

                txt_label_offre_abonnment.setText("none");
                txt_description_offre_abonnment.setText("none");
                txt_dateD_offre_abonnment.setText("none");
                txt_dateF_offre_abonnment.setText("none");
                txt_site_offre_abonnment.setText("none");
                txt_categorie_offre_abonnment.setText("none");
                String ImageUrl_offre = "http://localhost/bike/web/uploads/images/";

                Image image_offre = new Image(ImageUrl_offre + achat.getImage());
                File f = new File("C:/xampp/htdocs/bike/web/uploads/images/qruser/" + achat.getImage());

                System.out.println(f.delete());

                imageview_abonnment_offre_qr.setImage(image_offre);

                AlertDialog.showNotification("Supprition", "offre supprimer", AlertDialog.image_checked);

            } else {
                AlertDialog.showNotification("Error !", "vous n'avez pas d'offre", AlertDialog.image_cross);

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void refrech_reservation() throws Exception {
        col_id_reservation.setCellValueFactory(new PropertyValueFactory<>("idR"));

        col_prix_reservation.setCellValueFactory(new PropertyValueFactory<>("prix"));

        col_Dated_reservation.setCellValueFactory(new PropertyValueFactory<>("DATE_D"));

        col_Datef_reservation.setCellValueFactory(new PropertyValueFactory<>("DATE_F"));

        tab_mes_reservation.getItems().clear();

        tab_mes_reservation.setItems(service_reservation.afficher_reservation_user(Session.getCurrentSession()));
        lbl_prix_total_reservation.setText(String.valueOf(service_reservation.calcul_total_reservation(Session.getCurrentSession()) + " DT"));

    }

    private void refrech_reclamation() throws Exception {

        col_id_rec.setCellValueFactory(new PropertyValueFactory<>("id"));

        col_contenu_rec.setCellValueFactory(new PropertyValueFactory<>("contenu"));

        col_Etat_rec.setCellValueFactory(new PropertyValueFactory<>("etat"));

        col_Type_rec.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_Traite_rec.setCellValueFactory(new PropertyValueFactory<>("traite"));
        tab_Reclamation.getItems().clear();

        tab_Reclamation.setItems(service_reclamation.Affichertout_user(Session.getCurrentSession()));

    }

    private void setCellfromtabletoImage_reservation() {
        tab_mes_reservation.setOnMouseClicked(e -> {

            Reservation res = tab_mes_reservation.getItems().get(tab_mes_reservation.getSelectionModel().getSelectedIndex());
            Velo v = service_velo.get_vel_par_id_v(res.getIdV());

            String ImageUrl = "http://localhost/bike/web/uploads/images/";

            Image image = new Image(ImageUrl + v.getImage());
            image_velo_reservation.setImage(image);
        }
        );

    }

    private void setCellfromtabletoImage_reclamation() {
        tab_Reclamation.setOnMouseClicked(e -> {

            reclamation res = tab_Reclamation.getItems().get(tab_Reclamation.getSelectionModel().getSelectedIndex());

            String ImageUrl = "http://localhost/bike/web/uploads/images/";

            Image image = new Image(ImageUrl + res.getImage());
            Image_Reclamation.setImage(image);
        }
        );

    }

    private void refrech_Maintenance() throws Exception {

        col_id_main.setCellValueFactory(new PropertyValueFactory<>("id"));

        col_DateD_main.setCellValueFactory(new PropertyValueFactory<>("DATE_D"));
        col_DateF_main.setCellValueFactory(new PropertyValueFactory<>("DATE_F"));
        col_Problem_main.setCellValueFactory(new PropertyValueFactory<>("problem"));

        col_prix_main.setCellValueFactory(new PropertyValueFactory<>("prix"));
        col_Etat_main.setCellValueFactory(new PropertyValueFactory<>("etat"));
        col_Type_main.setCellValueFactory(new PropertyValueFactory<>("type"));

        tab_Maintenance.getItems().clear();

        tab_Maintenance.setItems(service_maintenance.Affichertout_user(Session.getCurrentSession()));

    }

    private void setCellfromtabletoImage_maintenance() {
        tab_Maintenance.setOnMouseClicked(e -> {

            Maitenance main = tab_Maintenance.getItems().get(tab_Maintenance.getSelectionModel().getSelectedIndex());

            String ImageUrl = "http://localhost/bike/web/uploads/images/Maitenance/";

            Image image = new Image(ImageUrl + main.getImage());
            Image_Maintenance.setImage(image);
            
            
         maintenance_user=main;
            
            
            
            
        }
        );

    }

    @FXML
    private void Supprimer_maintenance(ActionEvent event) throws SQLException, Exception {
         if (maintenance_user!=null)
        {
            if (maintenance_user.getPrix()== 0 )
            {
               AlertDialog.showNotification("Error !", "en attendent l acceptation de l admin", AlertDialog.image_cross);
     
            }
            else
            {
                
                if (maintenance_user.getEtat().equals("accept"))
                {
                     
                service_maintenance.Supprimer( maintenance_user.getId());
                    AlertDialog.showNotification("Maintenance", "Annulation ", AlertDialog.image_checked);
           refrech_Maintenance();
                }
                else
                {
                         AlertDialog.showNotification("Erreur!!", "deja en reparation ", AlertDialog.image_cross);
      
                }
             
            }
        }
        else
        {
             AlertDialog.showNotification("Error !", "il faut selectionner une ligne", AlertDialog.image_cross);
   
        }
    }
}
