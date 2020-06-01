/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import static controller.ShowDetailsEventController.idC;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import entites.*;
import Service.*;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class UpdateCommentController implements Initializable {

    @FXML
    private TextArea contenu_reponse;
    @FXML
    private Button Cancel;
    @FXML
    private Button backbtn;
    @FXML
    private Button UpdateComment;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                     CommentService cs = new CommentService();
                             CommentEvenement c = new CommentEvenement();
                                    c = cs.finCommentById(CommentItemController.idcomment.getIdComment());



        contenu_reponse.setText(c.getComment());
        // TODO
    }    


    @FXML
    private void handleClose(ActionEvent event) {
          Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("/gui/ShowDetailsEvent.fxml"));
            Stage myWindow =  (Stage) contenu_reponse.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Show Details");
            myWindow.show();
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @FXML
    private void back(ActionEvent event) {
          Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("/GUI/ShowDetailsEvent.fxml"));
            Stage myWindow =  (Stage) contenu_reponse.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Show Details");
            myWindow.show();
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
        
    }

    @FXML
    private void UpdateComment(ActionEvent event) throws SQLDataException {
        
        CommentService cs = new CommentService();
        CommentEvenement c = new CommentEvenement();
        System.out.println("controller.UpdateCommentController.UpdateComment()zzzzzzzzzzzzzzzzzzzzzzzzzzz"+idC);
       c = cs.finCommentById(CommentItemController.idcomment.getIdComment());
       c.setComment(contenu_reponse.getText());
    
       cs.modifieComment(c);
       
        Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("/GUI/ShowDetailsEvent.fxml"));
            Stage myWindow =  (Stage) contenu_reponse.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Show Details");
            myWindow.show();
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
       
    }
    
}
