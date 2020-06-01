/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Service.Panier_Service;
import Service.Piece_Service;
import static controllers.Item_SiteController.site_pour_affichage_produit;
import static controllers.affichageproduit_site_Front.indicePiece;
import entites.Panier;
import entites.Piece;
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
public class Item_PieceController implements Initializable {

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
    Piece piece = null;

    Piece_Service service_piece = new Piece_Service();
    Panier_Service service = new Panier_Service();
    @FXML
    private ImageView image_piece;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        piece = service_piece.get_Accessoire_affichage(indicePiece, site_pour_affichage_produit.getLib_S());
        libelle.setText(piece.getLibelle());
        description.setText(piece.getDescription());
        categorie.setText(piece.getLib_C());
        prix.setText(String.valueOf(piece.getPrix()));
        site.setText(piece.getLib_S());
          String ImageUrl = "http://localhost/bike/web/uploads/images/";
        Image image = new Image(ImageUrl + piece.getImage());
      
        image_piece.setImage(image);
    }

    @FXML
    private void acheter(ActionEvent event) throws SQLException {

        if (txt_quantite.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de quantite", AlertDialog.image_cross);
        } else if (txt_quantite.getText().matches("^[a-zA-Z]+$")) {
            AlertDialog.showNotification("Error !", "quantite incorrect", AlertDialog.image_cross);
        } else {
            Panier p = new Panier(1, service_piece.recuperer_id_P_Piece(piece.getId_Pi()), "cc", Integer.valueOf(txt_quantite.getText()), 55.2f);
            service.Ajouter(p);

        }
    }

}
