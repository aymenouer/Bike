/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Service.Abonnement_Service;
import static Service.Abonnement_Service.getMd5;
import Service.Achat_Service;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import static controllers.FrontCrontroller.indiceAbonnement;
import entites.Abonnement;
import entites.Achat;
import entites.Produit;
import entites.Session;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class Item_abonnmentController implements Initializable {

    @FXML
    private HBox itemC;
    @FXML
    private Label libelle;

    @FXML
    private Label label_offre;
    @FXML
    private Button btn_acheter;
    @FXML
    private Label description;
    @FXML
    private Label label_prix;
    @FXML
    private Label categorie;
    @FXML
    private Label prix;
    @FXML
    private Label site;
    @FXML
    private ComboBox<String> combo_mois;

    @FXML
    private Label etat_produit;
    Abonnement abonnment = null;
    Abonnement_Service service = new Abonnement_Service();
    Achat_Service service_achat = new Achat_Service();
    @FXML
    private ImageView emoji;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        abonnment = service.get_Abonnement_affichage(indiceAbonnement);

        combo_mois.getItems().addAll("mois", "3 mois", "un ans");
        combo_mois.getSelectionModel().select(0);

        libelle.setText(abonnment.getLibelle());
        description.setText(abonnment.getDescription());
        categorie.setText(abonnment.getLib_C());

        if (abonnment.getQuantite() > 50) {

            prix.setText(String.valueOf(abonnment.getPrix() - (abonnment.getPrix() * 0.05)) + " DT");
            label_offre.setText("Offre");
        } else {

            prix.setText(String.valueOf(abonnment.getPrix()) + " DT");
        }

        if (abonnment.getQuantite() >= 10) {
            etat_produit.setStyle("-fx-text-fill:green;");
            etat_produit.setText("Disponible ");
            String ImageUrl = "http://localhost/bike/web/uploads/images/smile.png";
            Image image = new Image(ImageUrl);
            emoji.setImage(image);

        } else if (abonnment.getQuantite() > 5 && abonnment.getQuantite() < 10) {
            etat_produit.setText("Limite ");
            etat_produit.setStyle("-fx-text-fill:darkorange;");
            String ImageUrl = "http://localhost/bike/web/uploads/images/limite.png";
            Image image = new Image(ImageUrl);
            emoji.setImage(image);
        } else {
            etat_produit.setText("Epuise ");
            etat_produit.setStyle("-fx-text-fill:red;");
            String ImageUrl = "http://localhost/bike/web/uploads/images/epuise.png";
            Image image = new Image(ImageUrl);
            emoji.setImage(image);
        }

        site.setText(abonnment.getLib_S());
btn_acheter.setOnAction(l -> 
{
   
    
    
            try {
               
                
                
                Achat achat = service_achat.recap_offre(Session.getCurrentSession()) ;
            
            if (achat == null)
            {
               
                
                 float prix_final=abonnment.getPrix();
    
    if (abonnment.getQuantite() > 50) {

           prix_final = (float) (prix_final - (prix_final * 0.05)) ;
      
        } 
                
                 
        Achat ach = new Achat(0,Session.getCurrentSession(),abonnment.getId_A(),prix_final,"test");   
          
    
 if("mois".equals(combo_mois.getSelectionModel().getSelectedItem()))
      {
      
       service_achat.achat_un_mois(ach, abonnment);
                
                
         
      }
         
 else if("3 mois".equals(combo_mois.getSelectionModel().getSelectedItem()))
      {
      
       service_achat.achat_trois_mois(ach, abonnment);
                
                
         
      }   
 else
 {
      service_achat.achat_un_ans(ach, abonnment);
 }
                
         AlertDialog.showNotification("Achat","Achat avec success",AlertDialog.image_checked);
               
                
                
                
            }
            else
                
            {
                
                
                
                    AlertDialog.showNotification("Error !","vous avez deja  un offre",AlertDialog.image_cross);

                
                
                
            }
            
            
            
            } catch (Exception ex) {
           


            }
    
    
    
    
    
    
    
    
    
}








);
    }

}
