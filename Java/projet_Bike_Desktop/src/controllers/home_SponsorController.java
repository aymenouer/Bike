/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Service.EvenementService;
import Service.SponsorService;
import entites.Evenement;
import entites.Session;
import entites.Sponsor;
import entites.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
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
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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
public class home_SponsorController implements Initializable {

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
    private TableView<Sponsor> tabview;
    @FXML
    private TableColumn<Sponsor, String> col_nom;
    @FXML
    private TableColumn<Sponsor, String> col_adresse;
    @FXML
    private TableColumn<Sponsor, String> col_type;
    @FXML
    private TableColumn<Sponsor, Integer> col_numero;
    @FXML
    private TableColumn<Sponsor, Integer> col_id;
    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_adresse;
    @FXML
    private TextField txt_Numero;
    @FXML
    private ComboBox<String> combo_Type;
    @FXML
    private Button btn_ajout;
    @FXML
    private ComboBox<String> combo_evenment;
  private User usersession = Session.get();
          private TableColumn<Sponsor, String> col_btnDelet;
              private EvenementService serviceEvenment = new EvenementService();
              
                     private SponsorService service = new SponsorService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Modifier();
        search();
        try {
            refreche();
        } catch (SQLException ex) {
            Logger.getLogger(home_SponsorController.class.getName()).log(Level.SEVERE, null, ex);
        }
              tabview.setEditable(true);
          String ImageUrl ="http://localhost/bike/web/uploads/images/";
        Image image = new Image(ImageUrl + usersession.getImage());   
        imagechange.setImage(image);
        fullName.setText(usersession.getFname() + " " + usersession.getLname());
        combo_evenment.setItems(serviceEvenment.get_List_Evement_titre());
   
        combo_Type.getItems().addAll("Sponsoring Financier","Sponsoring Technologique","Sponsoring En Nature");
        combo_Type.getSelectionModel().select(0);
          combo_evenment.getSelectionModel().select(0);
                  col_btnDelet = new TableColumn("Supprimer");
                  javafx.util.Callback<TableColumn<Sponsor, String>, TableCell<Sponsor, String>> cellFactory
                = new Callback<TableColumn<Sponsor, String>, TableCell<Sponsor, String>>() {
            public TableCell call(final TableColumn<Sponsor, String> param) {
                final TableCell<Sponsor, String> cell = new TableCell<Sponsor, String>() {

                    final Button btn = new Button("supprimer");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Sponsor u = getTableView().getItems().get(getIndex());

                            
                        
                          
                              
                                try {
                                    service.deleteSponsor(u.getIdSponsor());
                                } catch (SQLException ex) {
                                    Logger.getLogger(home_PieceController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                               
                                AlertDialog.showNotification("suppression confirmée!", "suppression a été bien faite", AlertDialog.image_checked);

                                try {
                                    refreche();
                                } catch (SQLException ex) {
                                    Logger.getLogger(home_AbonemetController.class.getName()).log(Level.SEVERE, null, ex);
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
                     col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));

        col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));

       
        col_numero.setCellValueFactory(new PropertyValueFactory<>("numero"));

        col_id.setCellValueFactory(new PropertyValueFactory<>("idSponsor"));

         tabview.getItems().clear();

                    tabview.setItems(service.serach(txt_Seach.getText()));

                } catch (SQLException ex) {
                    Logger.getLogger(homeController.class.getName()).log(Level.SEVERE, null, ex);
                }
        

            }
        }
        );

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
    private void ajouter_sponsor(ActionEvent event) throws SQLDataException, SQLException {
        
           if (txt_nom.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de nom", AlertDialog.image_cross);
        } else if (txt_nom.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur  !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        }  
        else if (txt_adresse.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de adress", AlertDialog.image_cross);
        } else if (txt_adresse.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur  !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        }  else if (txt_Numero.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de numero", AlertDialog.image_cross);
        } else if (txt_Numero.getText().matches("^[a-zA-Z]+$")) {
            AlertDialog.showNotification("Erreur  !", "numero incorrect", AlertDialog.image_cross);
        } else if (Integer.valueOf(txt_Numero.getText()) <= 0) {
            AlertDialog.showNotification("Error !", "Champ de numero", AlertDialog.image_cross);
        }
            else if (txt_Numero.getText().length() != 8) {
       AlertDialog.showNotification("Erreur Numero !","il faut 8 chiffres ",AlertDialog.image_cross);
        }
           else
            {
                int ide=0;
                
                for (  Evenement e : serviceEvenment.getAllEvenement()   )
                {
                    if (e.getTitre().equals(combo_evenment.getSelectionModel().getSelectedItem()))
                    {
                        ide=e.getId();
                    }
                    
                }
                
                
                
                Sponsor sp = new Sponsor(Integer.valueOf(txt_Numero.getText()), ide, combo_Type.getSelectionModel().getSelectedItem(), txt_adresse.getText(), txt_nom.getText());
            service.addSponsor(sp);
            
            refreche();
            }
   
        
 
        
        
        
    }
    
    
    
    
    
    
          public void refreche() throws SQLException {
       


        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_nom.setCellFactory(TextFieldTableCell.<Sponsor> forTableColumn());
        col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
           col_adresse.setCellFactory(TextFieldTableCell.<Sponsor> forTableColumn());
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
          col_type.setCellFactory(TextFieldTableCell.<Sponsor> forTableColumn());
       
        col_numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
     col_numero.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        col_id.setCellValueFactory(new PropertyValueFactory<>("idSponsor"));
        tabview.getItems().clear();

        tabview.setItems(service.AllSponsor());

    }
               public void Modifier()
    {
         
                   
                  
                   
            
        
               
                col_nom.setOnEditCommit((TableColumn.CellEditEvent<Sponsor, String> event) -> {
            TablePosition<Sponsor, String> pos = event.getTablePosition();
                
            String nom = event.getNewValue();
                 
            int row = pos.getRow();
            Sponsor ac = event.getTableView().getItems().get(row);
           
  
            ac.setNom(nom);
                    try {
                        service.modifieSponsor(ac);
                    } catch (SQLException ex) {
                        Logger.getLogger(home_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        });
                
    col_adresse.setOnEditCommit((TableColumn.CellEditEvent<Sponsor, String> event) -> {
            TablePosition<Sponsor, String> pos = event.getTablePosition();
                
            String adresse = event.getNewValue();
                 
            int row = pos.getRow();
            Sponsor ac = event.getTableView().getItems().get(row);
           
  
            ac.setAdresse(adresse);
                    try {
                        service.modifieSponsor(ac);
                    } catch (SQLException ex) {
                        Logger.getLogger(home_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        });
                
                
                
                
         col_type.setOnEditCommit((TableColumn.CellEditEvent<Sponsor, String> event) -> {
            TablePosition<Sponsor, String> pos = event.getTablePosition();
                
            String type = event.getNewValue();
                 
            int row = pos.getRow();
            Sponsor ac = event.getTableView().getItems().get(row);
           
  
            ac.setType(type);
                    try {
                        service.modifieSponsor(ac);
                    } catch (SQLException ex) {
                        Logger.getLogger(home_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        });       
                
            
        
            
            
          
              
              
            
              
     col_numero.setOnEditCommit((TableColumn.CellEditEvent<Sponsor, Integer> event) -> {
            TablePosition<Sponsor, Integer> pos = event.getTablePosition();
           
            Integer numero = event.getNewValue();
                  
            int row = pos.getRow();
            Sponsor sp = event.getTableView().getItems().get(row);
          
  
            sp.setNumero(numero);
                    try {
                            service.modifieSponsor(sp);
                    } catch (SQLException ex) {
                        Logger.getLogger(home_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        });  
     
   
     
     
     
                
    }
}
