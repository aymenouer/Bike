/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Service.Abonnement_Service;
import entites.Session;
import entites.User;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class pageadminController implements Initializable {

    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label fullName;
    @FXML
    private Button btnhome;
    @FXML
    private Button btnUser;
    @FXML
    private Button btnEvents;
    @FXML
    private Button btnaccessoire;
    @FXML
    private Button btnvelo;
    @FXML
    private Button btnsite;
    @FXML
    private Button btnabonnement;
    @FXML
    private Button btncategorie;
    @FXML
    private Button btnsponsor;
    @FXML
    private Button btnsignout;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Button btnPiece;
    @FXML
    private Button btnMaintenance;
    @FXML
    private Button btnReclamation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            new Abonnement_Service().return_quantite();
        } catch (Exception ex) {
            Logger.getLogger(pageadminController.class.getName()).log(Level.SEVERE, null, ex);
        }
           User usersession = Session.get();
                 String ImageUrl ="http://localhost/bike/web/uploads/images/";
        Image image = new Image(ImageUrl + usersession.getImage());
               imagechange.setImage(image);
       
   
        fullName.setText(usersession.getFname() + " " + usersession.getLname());
     
    }    

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
                if (event.getSource() == btnUser) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Home.fxml")));
            stage.setScene(scene);
            stage.show();

        }
                
                
                
                        if (event.getSource() == btnReclamation) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Home_Reclamation.fxml")));
            stage.setScene(scene);
            stage.show();

        }        
                
                
                
                
                
                    if (event.getSource() == btnMaintenance) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Home_Maintenance.fxml")));
            stage.setScene(scene);
            stage.show();

        }     
                
                
        if (event.getSource() == btnEvents) {
            
                                            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Home_Event.fxml")));
            stage.setScene(scene);
            stage.show();

        }
         if (event.getSource() == btnPiece) {
            
                                            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Home_Piece.fxml")));
            stage.setScene(scene);
            stage.show();

        }
        if (event.getSource() == btnaccessoire) {
                        Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Home_Accessoire.fxml")));
            stage.setScene(scene);
            stage.show();

        }
        if (event.getSource() == btnvelo) {
                        Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Home_Velo.fxml")));
            stage.setScene(scene);
            stage.show();

        }
        if (event.getSource() == btnsite) {
                                   Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Home_Site.fxml")));
            stage.setScene(scene);
            stage.show();

        }
        if (event.getSource() == btnabonnement) {
               Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Home_Abonemet.fxml")));
            stage.setScene(scene);
            stage.show();
            
            
            

        }
        if (event.getSource() == btncategorie) {
                       Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Home_Categorie.fxml")));
            stage.setScene(scene);
            stage.show();
            

        }
        if (event.getSource() == btnsponsor) {
            
                                   Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Home_Sponsor.fxml")));
            stage.setScene(scene);
            stage.show();
            

        }
        if (event.getSource() == btnsignout) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Login.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }
    
}
