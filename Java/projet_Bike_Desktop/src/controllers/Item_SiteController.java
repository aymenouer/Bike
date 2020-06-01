/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Service.Site_Service;
import com.googleMap.GoogleMapBike;
import static controllers.FrontCrontroller.indiceSite;
import entites.Site;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class Item_SiteController implements Initializable {

    @FXML
    private HBox itemC;
    @FXML
    private Label libelle;
    @FXML
    private Button btn_produit_dispo;
    @FXML
    private Label lieu;
    @FXML
    private Button btn_Google_Map;
    Site_Service service = new Site_Service();
    Site site = null;
static Site site_pour_affichage_produit; 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        site = service.get_site(indiceSite);

        libelle.setText(site.getLib_S());

        lieu.setText(site.getLieu());

    }

    @FXML
    private void handleClicks(ActionEvent event) throws Exception {
        if (event.getSource() == btn_Google_Map) {

            GoogleMapBike m = new GoogleMapBike();

            m.start(new Stage());

        }
         
                if(event.getSource()==btn_produit_dispo)
        {                 
            site_pour_affichage_produit=site;    
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Affichageproduit_site.fxml")));
            stage.setScene(scene);
            stage.show();

        }
    }

}
