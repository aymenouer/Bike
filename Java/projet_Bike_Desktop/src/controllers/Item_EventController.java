/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Service.CommentService;
import Service.EvenementService;
import static controllers.FrontCrontroller.indiceEvent;
import entites.Evenement;
import entites.Participer;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class Item_EventController implements Initializable {

    @FXML
    private HBox itemC;
    @FXML
    private Label libelle;
    @FXML
    private ImageView imageEvent;
    @FXML
    private Button Detail;
    @FXML
    private Label Lieu;
    
    EvenementService service_ev = new EvenementService();
    Evenement ev = null;
 public static int idE ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      ev = service_ev.get_Evenment(indiceEvent);
      
      libelle.setText(ev.getTitre());
       Lieu.setText(ev.getLieu());
          String ImageUrl = "http://localhost/bike/web/Upload/";
        Image image = new Image(ImageUrl + ev.getImage());

        imageEvent.setImage(image);

       
       
       
    }   

    @FXML
    private void Detail(ActionEvent event) throws IOException {
            idE=ev.getId();
           
           System.out.println("eeeeeeeeeeeeee"+idE);
   
          Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/ShowDetailsEvent.fxml")));
            
           
            stage.setScene(scene);
           stage.setMaxHeight(616);
          stage.setMaxWidth(993);
            stage.show();
        
    }

    
}
