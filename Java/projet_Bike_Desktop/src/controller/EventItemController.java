/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entites.*;
import Service.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class EventItemController implements Initializable {

    @FXML
    private HBox Hbox;
    @FXML
    private Text titre_question;
    @FXML
    private Text description_question;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
 
   
    }  
    
    public EventItemController(){
    
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/EventItem.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        
    }

    public HBox getBox() {
        return Hbox;
    }
    
        public void setInfo(Evenement string)  {   
          System.out.println("controller.ListViewEvent.updateItemeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeekkkkkkkkkkkkkkkkkkkk"+string);

            
        titre_question.setText(string.getTitre());
        description_question.setText(string.getLieu());
     
     
}
        
}
