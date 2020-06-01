/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Service.User_Service;
import Utils.mail;
import entites.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class ForgetpasswordController implements Initializable {

    @FXML
    private TextField txt_mail;
    @FXML
    private Button envoyer_mail;
    User_Service service = new User_Service();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void envoi_mail(ActionEvent event) throws Exception {
        
                      if (txt_mail.getText().equals("")) {
           AlertDialog.showNotification("Error E-mail !","Champ vide de E-mail",AlertDialog.image_cross);
        } 
        
         else if ( ! txt_mail.getText().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
           AlertDialog.showNotification("Error E-mail !","E-mail incorrect ",AlertDialog.image_cross);
        }
              else
         {
             User u =service.forget(txt_mail.getText()); 
             if (u!=null)
             {
                      System.out.println("sss");
                 mail.envoi(txt_mail.getText(), "MOT DE PASSE ", "Bike vous annonce, <br> Vous avez oublié votre mot de passe <br> votre username : " + u.getUsername()+ " <br>  et vous pouvez changer votre password par ce Lien : "+ "http://localhost/bike/web/app_dev.php/resetting/reset/"+ service.token_user(u.getId()) +"   \n ");

               AlertDialog.showNotification("Mail ","Mail envoyé ",AlertDialog.image_checked);
    
             }
             else
             {
               AlertDialog.showNotification("Error !","E-mail n'existe pas  ",AlertDialog.image_cross);
        
             }
              
         }
    }
    
}
