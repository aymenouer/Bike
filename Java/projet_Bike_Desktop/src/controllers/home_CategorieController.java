/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Service.Categorie_Service;
import entites.Categorie;
import entites.Session;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class home_CategorieController implements Initializable {

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
    private TableView<Categorie> tabview;
    @FXML
    private TableColumn<Categorie, String> col_libelle;
    @FXML
    private TableColumn<Categorie, String> col_description;
    @FXML
    private TableColumn<Categorie, String> col_type;
    @FXML
    private TextField txt_libelle;
    @FXML
    private TextField txt_description;
    @FXML
    private ComboBox<String> combo_type;
    @FXML
    private Button btn_ajout;

    @FXML
    private TableColumn<Categorie,Integer> col_id;
    private TableColumn<Categorie, String> col_btnDelet;
 private User usersession = Session.get();
  private Categorie_Service service =new Categorie_Service();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         tabview.setEditable(true);
        Modifier();
           search();
            try {
                                        refreche();
                                    } catch (SQLException ex) {
                                        Logger.getLogger(home_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
            combo_type.getItems().addAll("Abonnement","Accessoire","Velo","Piece");
        combo_type.getSelectionModel().select(0);
          String ImageUrl ="http://localhost/bike/web/uploads/images/";
        Image image = new Image(ImageUrl + usersession.getImage());


   
        imagechange.setImage(image);
        fullName.setText(usersession.getFname() + " " + usersession.getLname());
        
        
        
           col_btnDelet = new TableColumn("Supprimer");
        javafx.util.Callback<TableColumn<Categorie, String>, TableCell<Categorie, String>> cellFactory
                = (final TableColumn<Categorie, String> param) -> {
                    final TableCell<Categorie, String> cell = new TableCell<Categorie, String>() {
                        
                        final Button btn = new Button("supprimer");
                        
                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                btn.setOnAction(event -> {
                                    Categorie u = getTableView().getItems().get(getIndex());
                                    
                                    
                                    
                                    
                                    try {
                                        service.Supprimer(u.getID_C());
                                    } catch (SQLException ex) {
                                        Logger.getLogger(home_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    
                                    
                                    AlertDialog.showNotification("suppression confirmée!", "suppression a été bien faite", AlertDialog.image_checked);
                                    
                                    
                                    try {
                                        refreche();
                                    } catch (SQLException ex) {
                                        Logger.getLogger(home_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    
                                    
                                });
                                setGraphic(btn);
                                setText(null);
                            }
                        }
                    };
                    return cell;
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
    private void ajouter_categorie(ActionEvent event) throws SQLException {
                          if (txt_libelle.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de libelle", AlertDialog.image_cross);
        } else if (txt_libelle.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur txt_libelle !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        }  else if (txt_description.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de Description", AlertDialog.image_cross);
        } else if (txt_description.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur Description !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        } 

        else {
 
           Categorie Categorieajouter = new Categorie(txt_libelle.getText(), txt_description.getText(), combo_type.getSelectionModel().getSelectedItem());
            service.Ajouter(Categorieajouter);
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
                      col_libelle.setCellValueFactory(new PropertyValueFactory<>("Lib_C"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("Type"));

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
        col_libelle.setCellValueFactory(new PropertyValueFactory<>("Lib_C"));
        col_libelle.setCellFactory(TextFieldTableCell.<Categorie> forTableColumn());
        col_description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        col_description.setCellFactory(TextFieldTableCell.<Categorie> forTableColumn());
        col_type.setCellValueFactory(new PropertyValueFactory<>("Type"));
 col_type.setCellFactory(TextFieldTableCell.<Categorie> forTableColumn());
       
       
col_id.setCellValueFactory(new PropertyValueFactory<>("ID_C"));
        tabview.getItems().clear();

        tabview.setItems(service.Affichertout());
    }
    public void Modifier()
    {
               
                col_libelle.setOnEditCommit((CellEditEvent<Categorie, String> event) -> {
            TablePosition<Categorie, String> pos = event.getTablePosition();
                System.out.println(pos);
            String libelle_Categorie = event.getNewValue();
                    System.out.println(libelle_Categorie);
            int row = pos.getRow();
            Categorie categorie = event.getTableView().getItems().get(row);
            System.out.println(categorie);
  
            categorie.setLib_C(libelle_Categorie);
                    try {
                        service.Modifier(categorie,categorie.getID_C());
                    } catch (SQLException ex) {
                        Logger.getLogger(home_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        });
                
                
            col_description.setOnEditCommit((CellEditEvent<Categorie, String> event) -> {
            TablePosition<Categorie, String> pos = event.getTablePosition();
                System.out.println(pos);
            String Description_Categorie = event.getNewValue();
                    System.out.println(Description_Categorie);
            int row = pos.getRow();
            Categorie categorie = event.getTableView().getItems().get(row);
            System.out.println(categorie);
  
            categorie.setDescription(Description_Categorie);
                    try {
                        service.Modifier(categorie,categorie.getID_C());
                    } catch (SQLException ex) {
                        Logger.getLogger(home_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        });      
                
           col_type.setOnEditCommit((CellEditEvent<Categorie, String> event) -> {
            TablePosition<Categorie, String> pos = event.getTablePosition();
               
            String Type_Categorie = event.getNewValue();
                   
            int row = pos.getRow();
            Categorie categorie = event.getTableView().getItems().get(row);
           
  
            categorie.setType(Type_Categorie);
                    try {
                        service.Modifier(categorie,categorie.getID_C());
                    } catch (SQLException ex) {
                        Logger.getLogger(home_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        });     
                
                
                
    }
    
}
