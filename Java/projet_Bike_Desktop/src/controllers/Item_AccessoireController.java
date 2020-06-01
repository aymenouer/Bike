/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Service.Accessoire_Service;
import Service.Panier_Service;
import static controllers.Item_SiteController.site_pour_affichage_produit;
import static controllers.affichageproduit_site_Front.indiceAccessoire;
import entites.Accessoire;
import entites.Panier;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class Item_AccessoireController implements Initializable {

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
    private Label categorie;
    @FXML
    private Label prix;
    @FXML
    private Label site;
    @FXML
    private TextField txt_quantite;
    Accessoire acc = null; 
    Accessoire_Service ser = new Accessoire_Service();
    Panier_Service service = new Panier_Service();
    @FXML
    private ImageView image_acc;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       acc=ser.get_Accessoire_affichage(indiceAccessoire,site_pour_affichage_produit.getLib_S());
      libelle.setText(acc.getLibelle());
      description.setText(acc.getDescription());
      categorie.setText(acc.getLib_C());
      prix.setText(String.valueOf(acc.getPrix()));
      site.setText(acc.getLib_S());
        String ImageUrl = "http://localhost/bike/web/uploads/images/";
        Image image = new Image(ImageUrl + acc.getImage());
      
        image_acc.setImage(image);
    }    

    @FXML
    private void acheter(ActionEvent event) throws SQLException {
        if (txt_quantite.getText().equals("")) {
       AlertDialog.showNotification("Error !","Champ vide de quantite",AlertDialog.image_cross);
        } 
                else if (txt_quantite.getText().matches("^[a-zA-Z]+$")) {
            AlertDialog.showNotification("Error !","quantite incorrect",AlertDialog.image_cross);
        }
                 else
                {
                    Panier p = new Panier(1,ser.recuperer_id_P_accessoire(acc.getId_A()), "cc", Integer.valueOf(txt_quantite.getText()), 55.2f);
                    service.Ajouter(p);
                    
                }
        
    
    
    }
    
}
