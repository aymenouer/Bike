/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Service.User_Service;
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
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class StatistiqueController implements Initializable {

    @FXML
    private PieChart pie;
 Connection c =  MyConnexion.getInsCon().getcnx();
      private User_Service s =new User_Service();
    @FXML
    private Label caption;
            
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String req =" SELECT LEFT(RIGHT(email, LENGTH(email) -  LOCATE('@', email)),  LOCATE('.', RIGHT(email, LENGTH(email) -  LOCATE('@', email))) - 1) AS domain,count(*) as nbr FROM `user` where roles='a:0:{}' GROUP by domain ";
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
