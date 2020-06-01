/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Service.Site_Service;
import entites.Session;
import entites.Site;
import entites.User;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class home_SiteController implements Initializable {

    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label fullName;
    @FXML
    private Button btn_affichage;
    @FXML
    private Button btn_back;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnl_abonnement;
    @FXML
    private TextField txt_Seach;
    @FXML
    private TableView<Site> tabview;
    @FXML
    private TableColumn<Site, String> col_libelle;
    @FXML
    private TableColumn<Site, String> col_Lieu;
    @FXML
    private TableColumn<Site, Integer> col_Capacite;
    @FXML
    private TableColumn<Site, Integer> col_id;
    @FXML
    private TextField txt_libelle;
    @FXML
    private TextField txt_capacite;
    @FXML
    private TextField txt_Lieu;
    @FXML
    private Button btn_ajout;
 private TableColumn<Site, String> col_btnDelet;
   private Site_Service service = new Site_Service();
   private User usersession = Session.get();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tabview.setEditable(true);
             Modifier();
       String ImageUrl ="http://localhost/bike/web/uploads/images/";
        Image image = new Image(ImageUrl + usersession.getImage());


   
        imagechange.setImage(image);
         fullName.setText(usersession.getFname() + " " + usersession.getLname());
         search();
        try {
            refreche();
        } catch (SQLException ex) {
            Logger.getLogger(home_SiteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        col_btnDelet = new TableColumn("Supprimer");
        
         javafx.util.Callback<TableColumn<Site, String>, TableCell<Site, String>> cellFactory
                = new Callback<TableColumn<Site, String>, TableCell<Site, String>>() {
            public TableCell call(final TableColumn<Site, String> param) {
                final TableCell<Site, String> cell = new TableCell<Site, String>() {

                    final Button btn = new Button("supprimer");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Site u = getTableView().getItems().get(getIndex());

                              

                              
                                try {
                                    service.Supprimer(u.getId());
                                } catch (SQLException ex) {
                                    Logger.getLogger(home_SiteController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                              

                                AlertDialog.showNotification("suppression confirmée!", "suppression a été bien faite", AlertDialog.image_checked);

                             
                                try {
                                    refreche();
                                } catch (SQLException ex) {
                                    Logger.getLogger(home_SiteController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                               

                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        col_btnDelet.setCellFactory(cellFactory);
        tabview.getColumns().add(col_btnDelet);
        
        
        
        
        
    }    

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
           if (event.getSource() == btn_back) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/page_admin.fxml")));
            stage.setScene(scene);
            stage.show();

        }
        if (event.getSource() == btnSignout) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Login.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void ajouter_Site(ActionEvent event) throws SQLException {
         if (txt_libelle.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de libelle", AlertDialog.image_cross);
        } else if (txt_libelle.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur txt_libelle !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        } else if (txt_capacite.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de Capacite", AlertDialog.image_cross);
        } else if (txt_capacite.getText().matches("^[a-zA-Z]+$")) {
            AlertDialog.showNotification("Erreur Capacite !", "Capacite incorrect", AlertDialog.image_cross);
        } else if (Integer.valueOf(txt_capacite.getText()) <= 0) {
            AlertDialog.showNotification("Error !", "Champ de Capacite", AlertDialog.image_cross);
        } else if (txt_Lieu.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de Lieu", AlertDialog.image_cross);
        } else if (txt_Lieu.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur Lieu !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        } else {
            Site Siteajouter = new Site(Integer.valueOf(txt_capacite.getText()), txt_libelle.getText(), txt_Lieu.getText());
            service.Ajouter(Siteajouter);
            refreche();
        }
    }
     public void search() {
        txt_Seach.setOnKeyReleased(e
                -> {
            if (txt_Seach.getText().equals("") ) {

                try {
                    refreche();
                } catch (SQLException ex) {
                    Logger.getLogger(homeController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {

                try {
                     col_libelle.setCellValueFactory(new PropertyValueFactory<>("Lib_S"));
        col_Lieu.setCellValueFactory(new PropertyValueFactory<>("Lieu"));
        col_Capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                    tabview.getItems().clear();

                    tabview.setItems(service.serach(txt_Seach.getText()));

                } catch (SQLException ex) {
                    Logger.getLogger(homeController.class.getName()).log(Level.SEVERE, null, ex);
                }
        

            }
        }
        );

    }
        
        public void refreche() throws SQLException {

        col_libelle.setCellValueFactory(new PropertyValueFactory<>("Lib_S"));
           col_libelle.setCellFactory(TextFieldTableCell.<Site> forTableColumn());
        col_Lieu.setCellValueFactory(new PropertyValueFactory<>("Lieu"));
        col_Lieu.setCellFactory(TextFieldTableCell.<Site> forTableColumn());
        col_Capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
      col_Capacite.setCellFactory(
    TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

    
   
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabview.getItems().clear();

        tabview.setItems(service.Affichertout());

    }   
        
         public void Modifier()
    {
               
                col_libelle.setOnEditCommit((CellEditEvent<Site, String> event) -> {
            TablePosition<Site, String> pos = event.getTablePosition();
                
            String libelle_Site = event.getNewValue();
                 
            int row = pos.getRow();
            Site site = event.getTableView().getItems().get(row);
           
  
            site.setLib_S(libelle_Site);
                    try {
                        service.Modifier(site,site.getId());
                    } catch (SQLException ex) {
                        Logger.getLogger(home_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        });
                
                
            col_Lieu.setOnEditCommit((CellEditEvent<Site, String> event) -> {
            TablePosition<Site, String> pos = event.getTablePosition();
           
            String Lieu_Site = event.getNewValue();
                  
            int row = pos.getRow();
            Site site = event.getTableView().getItems().get(row);
          
  
            site.setLieu(Lieu_Site);
                    try {
                        service.Modifier(site,site.getId());
                    } catch (SQLException ex) {
                        Logger.getLogger(home_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        });      
                
     col_Capacite.setOnEditCommit((CellEditEvent<Site, Integer> event) -> {
            TablePosition<Site, Integer> pos = event.getTablePosition();
           
            Integer Capacite_Site = event.getNewValue();
                  
            int row = pos.getRow();
            Site site = event.getTableView().getItems().get(row);
          
  
            site.setCapacite(Capacite_Site);
                    try {
                        service.Modifier(site,site.getId());
                    } catch (SQLException ex) {
                        Logger.getLogger(home_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        });      
                
    }
    
        
        
}
