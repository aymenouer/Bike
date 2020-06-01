/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Service.Abonnement_Service;
import Service.User_Service;
import Utils.mail;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entites.Session;
import entites.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class homeController implements Initializable {

    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label fullName;
    @FXML
    private Button btn_affichage;
    @FXML
    private Button btn_modifier_user;
    @FXML
    private Button btn_back;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnl_modifier;
    @FXML
    private TextField txtFirstname_modifier;
    @FXML
    private TextField txtLastname_modifier;
    @FXML
    private TextField txtEmail_modifier;
    @FXML
    private TextField txtUSername_modifier;
    @FXML
    private PasswordField txtPassword_modifier;
    @FXML
    private TextField txtNumber_modifier;
    @FXML
    private ComboBox<Integer> txtage_modifier;
    @FXML
    private Button edit_profil;
    @FXML
    private ImageView imageview_modifier;
    @FXML
    private Pane pnl_affichage_user;
    @FXML
    private TextField txt_Seach;
    @FXML
    private Label Nombre_User;
    
    @FXML
    private Button stat;
    @FXML
    private Button pdf;
    @FXML
    private TableView<User> tabview;
    @FXML
    private TableColumn<User, String> col_fname;
    @FXML
    private TableColumn<User, String> col_lname;
    @FXML
    private TableColumn<User, String>col_mail;
    @FXML
    private TableColumn<User, Integer> col_number;
    @FXML
    private TableColumn<User, String> col_username;

    @FXML
    private TableColumn<User, Integer> col_age;
    @FXML
    private TextField txt_mail_user;
    @FXML
    private Button Desactiver_Client;
      User_Service service = new User_Service();
    private String nomImage = "";
    private User user = null;
     private User usersession = Session.get();
    @FXML
    private Button Activer_Client;
    @FXML
    private Label Nombre_Client_active;
    @FXML
    private Label Nombre_Client_desactive;
    @FXML
    private TableColumn<User, Integer> col_id;
    @FXML
    private TableColumn<User, Integer> col_enabled;
    @FXML
    private ImageView Image_user;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        try {
            search();
        } catch (SQLException ex) {
            Logger.getLogger(homeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setCellfromtabletotxt();
   
           String ImageUrl ="http://localhost/bike/web/uploads/images/";
        Image image = new Image(ImageUrl + usersession.getImage());


   
        imagechange.setImage(image);
        fullName.setText(usersession.getFname() + " " + usersession.getLname());
        txtage_modifier.getItems().addAll(18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40);
        txtage_modifier.getSelectionModel().select(0);
        txtFirstname_modifier.setText(usersession.getFname());
        txtLastname_modifier.setText(usersession.getLname());
        txtEmail_modifier.setText(usersession.getMail());
        txtUSername_modifier.setText(usersession.getUsername());
        txtNumber_modifier.setText(String.valueOf(usersession.getNumber()));
      
        Nombre_Client_active.setText(String.valueOf(service.nombre_user_Client_Active()));
        Nombre_Client_desactive.setText(String.valueOf(service.nombre_user_Client_Desactive()));
        Nombre_User.setText(String.valueOf(service.nombre_user()));
        fullName.setText(usersession.getFname() + " " + usersession.getLname());
        try {
            refreche();
        } catch (SQLException ex) {
            Logger.getLogger(homeController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }    

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
        if (event.getSource() == btn_back) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/page_admin.fxml")));
            stage.setScene(scene);
            stage.show();

        }
        if (event.getSource() == btn_modifier_user) {
            pnl_modifier.toFront();
        }
        if (event.getSource() == btn_affichage) {
            pnl_affichage_user.toFront();
        }
        if (event.getSource() == btnSignout) {
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
    private void update_profil(ActionEvent event) throws Exception {
        
        
              if (txtFirstname_modifier.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de firstname", AlertDialog.image_cross);
        } else if (txtFirstname_modifier.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur firstname !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        } else if (txtLastname_modifier.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur lastname !", "il faut saisir des caracteres !", AlertDialog.image_cross);
        } else if (txtLastname_modifier.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de lastname", AlertDialog.image_cross);
        } else if (txtEmail_modifier.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de email", AlertDialog.image_cross);
        } else if (!txtEmail_modifier.getText().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            AlertDialog.showNotification("Erreur Email !", "email incorrect ", AlertDialog.image_cross);
        } else if (txtUSername_modifier.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de Username", AlertDialog.image_cross);
        } else if (txtPassword_modifier.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de password", AlertDialog.image_cross);
        } else if (txtNumber_modifier.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de Number", AlertDialog.image_cross);
        } else if (txtNumber_modifier.getText().matches("^[a-zA-Z]+$")) {
            AlertDialog.showNotification("Erreur Telephone !", "numero de telephone incorrect", AlertDialog.image_cross);
        } else if (txtNumber_modifier.getText().length() != 8) {
            AlertDialog.showNotification("Erreur Telephone !", "il faut 8 chiffres ", AlertDialog.image_cross);
        } else if (nomImage.equals("")) {
            AlertDialog.showNotification("Erreur Image !", "Il faut inserer une photo", AlertDialog.image_cross);
        } else {
           String code =Abonnement_Service.getMd5(txtFirstname_modifier.getText()+ txtLastname_modifier.getText())+nomImage;
           
            User u = new User(txtFirstname_modifier.getText(), txtLastname_modifier.getText(), txtEmail_modifier.getText(), Integer.parseInt(txtNumber_modifier.getText()), txtUSername_modifier.getText(), txtPassword_modifier.getText(), "admin", Integer.valueOf(txtage_modifier.getSelectionModel().getSelectedItem()), code);
  
            InputStream inStream = null;
    OutputStream outStream = null;

        try{

            File afile =new File("./src/images/"+nomImage);
            File bfile =new File("C:/xampp/htdocs/bike/web/uploads/images/"+code);
            

            inStream = new FileInputStream(afile);
            outStream = new FileOutputStream(bfile);

            byte[]buffer = new byte[1024];

            int length;
           //copy the file content in bytes
            while ((length = inStream.read(buffer)) > 0){

                outStream.write(buffer, 0, length);

            }

            inStream.close();
            outStream.close();

            System.out.println("File is copied successful!");

        }catch(IOException e){
            e.printStackTrace();
        }
           
            service.Modifier(u, Session.getCurrentSession());
      refreche();
            User usersessionnew = Session.get();
                     String ImageUrl ="http://localhost/bike/web/uploads/images/";
        Image image = new Image(ImageUrl + usersessionnew.getImage());
        
          
            imagechange.setImage(image);
            fullName.setText(usersessionnew.getFname() + " " + usersessionnew.getLname());
            mail.envoi(u.getMail(), "COMPTE MODIFIé", "Bike vous annonce , <br> que votre compte a été modifié  \n ");

      

        }
        
        
        
        
        
        
    }

    @FXML
    private void handleDragOver(DragEvent event) {
          if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    private void handleDrop(DragEvent event) throws FileNotFoundException {
          List<File> files = event.getDragboard().getFiles();
        Image img = new Image(new FileInputStream(files.get(0)));
        imageview_modifier.setImage(img);
        nomImage = files.get(0).getName();
    }

    @FXML
    private void faire_stat(ActionEvent event) {
           Scene scene;
        Stage stage = new Stage();
        try {

            scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Statistique.fxml")));
            stage.setScene(scene);
            stage.setTitle("Statistique Users");
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(homeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
   private void faire_PDF(ActionEvent event) {

        Document document = new Document();
        try {

            PdfWriter.getInstance(document, new FileOutputStream("Users.pdf"));
            document.open();
            Paragraph ph1 = new Paragraph("Bienvenue a Bike !");
            Paragraph ph2 = new Paragraph(".");
            PdfPTable table = new PdfPTable(5);
         
     com.itextpdf.text.Image    image1 =  com.itextpdf.text.Image.getInstance("/Users/aymen/Desktop/projet_Bike_Desktop/src/images/logo_bike.png" );
   
    //Scale to new height and new width of image
    image1.scaleAbsolute(200, 200);
    //Add to document
    document.add(image1);
   
            //On créer l'objet cellule.
            PdfPCell cell;
           
            //contenu du tableau.
            table.addCell("Prenom : ");
            table.addCell("Nom : ");
            table.addCell("Mail : ");
            table.addCell("Numero de Telephone :");
            table.addCell("Age : ");
            service.Affichertoutpdf().forEach(e
                    -> {
                table.addCell(e.getFname());
             
		table.setHorizontalAlignment(Element.ALIGN_CENTER);

                table.addCell(e.getLname());
                table.addCell(e.getMail());
                table.addCell(String.valueOf(e.getNumber()));
                table.addCell(String.valueOf(e.getAge()));
             
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
    private void Desactiver(ActionEvent event) throws SQLException {
        if (txt_mail_user.getText()=="")
        {
               AlertDialog.showNotification("Error !","il faut selectionner une coloun",AlertDialog.image_cross);

        }
        else
        {
                service.change_etat_compte(0, user.getId());  
               AlertDialog.showNotification("Desactivation ","Compte Desactive ",AlertDialog.image_checked);
          Nombre_Client_active.setText(String.valueOf(service.nombre_user_Client_Active()));
        Nombre_Client_desactive.setText(String.valueOf(service.nombre_user_Client_Desactive()));
        Nombre_User.setText(String.valueOf(service.nombre_user()));
     try {
                    refreche();
                } catch (SQLException ex) {
                    Logger.getLogger(homeController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }

    @FXML
    private void activer(ActionEvent event) throws SQLException {
          if (txt_mail_user.getText()=="")
        {
               AlertDialog.showNotification("Error !","il faut selectionner une coloun",AlertDialog.image_cross);

        }
        else
        {
                service.change_etat_compte(1, user.getId());  
               AlertDialog.showNotification("Activation ","Compte active ",AlertDialog.image_checked);
         Nombre_Client_active.setText(String.valueOf(service.nombre_user_Client_Active()));
        Nombre_Client_desactive.setText(String.valueOf(service.nombre_user_Client_Desactive()));
        Nombre_User.setText(String.valueOf(service.nombre_user()));
     try {
                    refreche();
                } catch (SQLException ex) {
                    Logger.getLogger(homeController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
    public void refreche() throws SQLException {
       
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_enabled.setCellValueFactory(new PropertyValueFactory<>("enabled"));
        col_lname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        col_mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        col_number.setCellValueFactory(new PropertyValueFactory<>("number"));
        col_username.setCellValueFactory(new PropertyValueFactory<>("username"));   
        col_age.setCellValueFactory(new PropertyValueFactory<>("age"));
        col_fname.setCellValueFactory(new PropertyValueFactory<>("fname"));
         tabview.getItems().clear();       
        tabview.setItems(service.Affichertout());
       
    }
    private void setCellfromtabletotxt() {
        tabview.setOnMouseClicked(e -> {

            user = tabview.getItems().get(tabview.getSelectionModel().getSelectedIndex());
            txt_mail_user.setText(user.getMail());
            
            
           String ImageUrl ="http://localhost/bike/web/uploads/images/";
        Image image = new Image(ImageUrl + user.getImage());
            System.out.println(ImageUrl + user.getImage());

   
        Image_user.setImage(image);
            

        }
        );

    }
     public void search() throws SQLException {
        txt_Seach.setOnKeyReleased(e
                -> {
            if (txt_Seach.getText().equals("")) {

                try {
                    refreche();
                } catch (SQLException ex) {
                    Logger.getLogger(homeController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {

                try {
                     col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                    col_lname.setCellValueFactory(new PropertyValueFactory<>("lname"));
                    col_mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
                    col_number.setCellValueFactory(new PropertyValueFactory<>("number"));
                    col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
                   col_enabled.setCellValueFactory(new PropertyValueFactory<>("enabled"));
                    col_age.setCellValueFactory(new PropertyValueFactory<>("age"));
                    col_fname.setCellValueFactory(new PropertyValueFactory<>("fname"));

                    tabview.setItems(service.serach(txt_Seach.getText()));

                } catch (SQLException ex) {
                    Logger.getLogger(homeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {

                } catch (Exception ex) {
                    Logger.getLogger(homeController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        );

    }

}
