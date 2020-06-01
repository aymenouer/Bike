/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Alert.AlertDialog;
import com.jfoenix.controls.JFXButton;
import static controllers.Item_EventController.idE;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import entites.*;
import Service.*;
import javafx.scene.control.Button;
//import service.ParticiperService;
//import service.ServiceVote;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ShowDetailsEventController implements Initializable {

    @FXML
    private ListView<CommentEvenement> listViewQR;
    ObservableList<CommentEvenement> data;
    int i,i2 ;
    public static int idC ;

    @FXML
    private Text question;
    
    @FXML
    private Button type_vote_oui;
    @FXML
    private Button type_vote_non;
    @FXML
    private Button addComment;
    @FXML
    private Button back;
    @FXML
    private ImageView img_ev;
    @FXML
    private TextField commentText;
    @FXML
    private Label nbrLike;
    @FXML
    private Label nbrdeslike;
    @FXML
    private Button participer;
    @FXML
    private Text question1;
 
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
CommentService cs = new CommentService();

ServiceVote v = new ServiceVote();
int lik;
        try {
            lik = v.NumLike(idE);
            
            int des =  v.NumdeLike(idE);
            nbrLike.setText(String.valueOf(des));
nbrdeslike.setText(String.valueOf(lik));
        } catch (SQLException ex) {
            Logger.getLogger(ShowDetailsEventController.class.getName()).log(Level.SEVERE, null, ex);
        }


         EvenementService es = new EvenementService();
         
         
         System.err.println("okiiiiiiiiiiii"+ cs.findEvenementById(idE).getImage());
         
         question.setText(cs.findEvenementById(idE).getTitre());
  String ImageUrl ="http://localhost/bike/web/Upload/";
        Image image = new Image(ImageUrl + cs.findEvenementById(idE).getImage());
       img_ev.setImage(image);
        try {
            data = cs.getAllCommentByEvent(cs.findEvenementById(idE));
        } catch (SQLDataException ex) {
            Logger.getLogger(ShowDetailsEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        System.out.println("ya aymen"+data);
         //supprimer.setVisible(false);
        // Question quest = new Question();
         //quest.setId_client(LoginController.session.getId());

     /*    if (quest.getId_client()==idU){
       supprimer.setVisible(true);
    }*/
         
     

      listViewQR.setItems(data);
       listViewQR.setCellFactory((ListView<CommentEvenement> param) -> new ListViewCommentEvent());
    }

        
        
        
        // TODO
   /*    

    private void DeleteQuestion(ActionEvent event) throws SQLDataException {
        
    CommentService q = new CommentService();
    ObservableList<CommentEvenement> e;
        e = listViewQR.getSelectionModel().getSelectedItems();
        
         for (CommentEvenement m : e) {
            idC=m.getIdComment();
              
        }
         System.out.println("controller.ShowDetailsEventController.DeleteQuestion(ddddddddd)"+i);
        q.deleteComment(idC);
         Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("/gui/ShowDetailsEvent.fxml"));
            Stage myWindow =  (Stage) question.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
    
    }

    private void UpdateReponse(ActionEvent event) {
        
           ObservableList<CommentEvenement> c;
        c = listViewQR.getSelectionModel().getSelectedItems();
        
      
        for (CommentEvenement m : c) {
            idC=m.getIdComment();
            
              
        }
        
         Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("/gui/UpdateComment.fxml"));
            Stage myWindow =  (Stage) question.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Modifier Reponse");
            myWindow.show();
            
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }   */

    @FXML
    private void Like(ActionEvent event) throws SQLException, Exception {
        
        ServiceVote sv  = new ServiceVote();
        if (sv.user_vote(Session.getCurrentSession(),idE)== null)
        {
            Vote v = new Vote ();
        v.setId_client(Session.getCurrentSession());
        v.setType_vote(1);
        v.setId_evenement(idE);
       sv.addVote(v);
        
         Notifications notificationBuilder = Notifications.create()
                .title("Done").text("votre vote est bien enregistrer").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
                       
           Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("/GUI/ShowDetailsEvent.fxml"));
            Stage myWindow =  (Stage) commentText.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("comment");
            myWindow.show();
           
        } catch (IOException ex) {
            Logger.getLogger(CommentItemController.class.getName()).log(Level.SEVERE,null,ex);
           
        } 
        }
        else
        {
              AlertDialog.showNotification("Error !","Vous avez deja fait une reaction",AlertDialog.image_cross);
        
        }
       
        
    }

    @FXML
    private void DesLike(ActionEvent event) throws SQLException, Exception {
        
        
        ServiceVote sv  = new ServiceVote();
  if (sv.user_vote(Session.getCurrentSession(),idE)== null)
  {
       Vote v = new Vote ();
        v.setId_client(Session.getCurrentSession());
        v.setType_vote(2);
        v.setId_evenement(idE);
        sv.addVote(v);
        
         Notifications notificationBuilder = Notifications.create()
                .title("Done").text("votre vote est bien enregistrer").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
                       
           Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("/GUI/ShowDetailsEvent.fxml"));
            Stage myWindow =  (Stage) commentText.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("comment");
            myWindow.show();
           
        } catch (IOException ex) {
            Logger.getLogger(CommentItemController.class.getName()).log(Level.SEVERE,null,ex);
           
        }
  }
  else
  {
         AlertDialog.showNotification("Error !","Vous avez deja fait une reaction",AlertDialog.image_cross);
        
  }
       
        
    }


    @FXML
    private void AddComment(ActionEvent event) throws SQLDataException, Exception {
           
        CommentEvenement e = new CommentEvenement();
        CommentService cs = new CommentService();
        System.out.println(cs.findUserById(Session.getCurrentSession()));
        e.setId(cs.findUserById(Session.getCurrentSession()));
        e.setIdEvt(cs.findEvenementById(idE));

        BadWords.loadConfigs();
      
            {
                
                if (BadWords.filterText(commentText.getText())) {
            
       Notifications notificationBuilder = Notifications.create()
               .title("Alert").text("cette application n'autorise pas ces termes").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
                       
           Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("/GUI/ShowDetailsEvent.fxml"));
            Stage myWindow =  (Stage) commentText.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("comment");
            myWindow.show();
           
        } catch (IOException ex) {
            Logger.getLogger(CommentItemController.class.getName()).log(Level.SEVERE,null,ex);
           
        }

                  
                } 
                else
                
                {
                    
                          
       Notifications notificationBuilder = Notifications.create()
               .title("Succes").text("Votre Commentaire est publier").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
                   notificationBuilder.darkStyle();
                   notificationBuilder.show();
                   e.setComment(commentText.getText());
                   cs.addComment(e);
                   
                   // Alert alert = new Alert(Alert.AlertType.WARNING);
                   
                   /* alert.setTitle("gros mot");
                    alert.setHeaderText("gros mot trouve");
                    alert.setContentText("cette application n'autorise pas ces termes ");
                    alert.showAndWait();*/
                    
                    
            
           Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("/GUI/ShowDetailsEvent.fxml"));
            Stage myWindow =  (Stage) commentText.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("comment");
            myWindow.show();
             
        } catch (IOException ex) {
            Logger.getLogger(CommentItemController.class.getName()).log(Level.SEVERE,null,ex);
           
        }
        
    
            }
    
    }}
      /*    Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("/gui/ShowDetailsEvent.fxml"));
            Stage myWindow =  (Stage) Update.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Ajouter Comment");
            myWindow.show();
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        
    } */
 
    @FXML
    private void back(ActionEvent event) {
         try {
           Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front.fxml"));
            Stage myWindow =  (Stage) addComment.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Ajouter Comment");
            myWindow.show();
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @FXML
    private void Participer(ActionEvent event) throws SQLDataException {
         
           CommentService cs = new CommentService();
         EvenementService es = new EvenementService();
        // ParticiperService ps = new ParticiperService();
         int nbr=0;
          
     
         Participer p = new Participer(1,idE);
     
        
      Evenement  e1=cs.findEvenementById(idE);
      //nbr = e1.getNbreparticipants()+1;
      //e1.setNbreparticipants(nbr);
   
      es.ModifierEvenenmentPlace(e1);
     // ps.addParticiper(p);
        
             AlertDialog.showNotification("Participation","Votre Participation et accepter",AlertDialog.image_checked);   
     
    }

    
}
