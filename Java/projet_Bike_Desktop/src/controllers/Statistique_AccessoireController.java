/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Utils.MyConnexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class Statistique_AccessoireController implements Initializable {

    @FXML
    private PieChart pie;
    @FXML
    private Label caption;

Connection c =  MyConnexion.getInsCon().getcnx();
    /**
     * Initializes the controller class.
     */
    @Override
      public void initialize(URL url, ResourceBundle rb) {
        String req ="SELECT p.Lib_C,count(*) as nombre FROM `accessoire` a INNER JOIN produit p WHERE p.ID_P=a.ID_p GROUP by p.Lib_C";
 PreparedStatement ste;
      
          
        try {
              ste = (PreparedStatement) c.prepareStatement(req);
              ResultSet rs = ste.executeQuery();
            while (rs.next()){
                pie.getData().add(new  PieChart.Data(rs.getString(1), rs.getInt(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
        


caption.setTextFill(Color.WHITE);
caption.setStyle("-fx-font: 24 arial;");

for (final PieChart.Data data : pie.getData()) {

    data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
        new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                
                caption.setTranslateX(e.getSceneX()-20);
                caption.setTranslateY(e.getSceneY()-80);
              
                caption.setText(String.valueOf("Nbr : "+(int)  data.getPieValue()));
             }
        });
           
       
          
    }
    }
     
       
    
}
