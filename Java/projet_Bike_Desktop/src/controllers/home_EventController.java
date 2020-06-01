/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Service.Abonnement_Service;
import Service.EvenementService;
import entites.Evenement;
import entites.Session;
import entites.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.IntegerStringConverter;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class home_EventController implements Initializable {

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
    private TableView<Evenement> tabview;
    @FXML
    private TableColumn<Evenement, Integer> col_id;
    @FXML
    private TableColumn<Evenement, String> col_Titre;
    @FXML
    private TableColumn<Evenement, String> col_Lieu;
    @FXML
    private TableColumn<Evenement, Date> col_dated;
    @FXML
    private TableColumn<Evenement, Date> col_datef;
    @FXML
    private TableColumn<Evenement, Integer> col_nombre_places;
    @FXML
    private TableColumn<Evenement, Integer> col_nombre_participants;
    @FXML
    private TextField txt_titre;
    @FXML
    private TextField txt_lieu;
    @FXML
    private TextField txt_nbplaces;
    @FXML
    private Button btn_ajout;
    @FXML
    private ImageView imageview;
    @FXML
    private ImageView Image_Acc;
    @FXML
    private DatePicker datedebut;
    @FXML
    private DatePicker datefin;
    EvenementService service = new EvenementService();
    private User usersession = Session.get();
    private TableColumn<Evenement, String> col_btnDelet;
    private String nomImage = "";
    @FXML
    private TextField txt_description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        search();
        tabview.setEditable(true);
        Modifier();
        setCellfromtabletoImage();
        try {
            refreche();
        } catch (SQLException ex) {
            Logger.getLogger(home_EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        col_btnDelet = new TableColumn("Supprimer");
        String ImageUrl = "http://localhost/bike/web/uploads/images/";
        Image image = new Image(ImageUrl + usersession.getImage());

        tabview.setEditable(true);

        imagechange.setImage(image);
        fullName.setText(usersession.getFname() + " " + usersession.getLname());
         javafx.util.Callback<TableColumn<Evenement, String>, TableCell<Evenement, String>> cellFactory
                = new Callback<TableColumn<Evenement, String>, TableCell<Evenement, String>>() {
            public TableCell call(final TableColumn<Evenement, String> param) {
                final TableCell<Evenement, String> cell = new TableCell<Evenement, String>() {

                    final Button btn = new Button("supprimer");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Evenement u = getTableView().getItems().get(getIndex());
 File f = new File("C:/xampp/htdocs/bike/web/Upload/"+u.getImage());
                            
                                System.out.println(f.delete());                             

                          
                              
                                try {
                                    service.deleteEvenement(u.getId());
                                } catch (SQLException ex) {
                                    Logger.getLogger(home_AccessoireController.class.getName()).log(Level.SEVERE, null, ex);
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
                   col_id.setCellValueFactory(new PropertyValueFactory<>("id"));

        col_Titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
  
        col_Lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));


        col_dated.setCellValueFactory(new PropertyValueFactory<>("DateDebut"));

        col_datef.setCellValueFactory(new PropertyValueFactory<>("DateFin"));

        col_nombre_places.setCellValueFactory(new PropertyValueFactory<>("nbreplaces"));


        col_nombre_participants.setCellValueFactory(new PropertyValueFactory<>("nbreparticipants"));

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

    public void refreche() throws SQLException {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));

        col_Titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        col_Titre.setCellFactory(TextFieldTableCell.<Evenement>forTableColumn());
        col_Lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        col_Lieu.setCellFactory(TextFieldTableCell.<Evenement>forTableColumn());

        col_dated.setCellValueFactory(new PropertyValueFactory<>("DateDebut"));
        col_dated.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
        col_datef.setCellValueFactory(new PropertyValueFactory<>("DateFin"));
        col_datef.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
        col_nombre_places.setCellValueFactory(new PropertyValueFactory<>("nbreplaces"));
        col_nombre_places.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        col_nombre_participants.setCellValueFactory(new PropertyValueFactory<>("nbreparticipants"));

        tabview.getItems().clear();

        tabview.setItems(service.getAllEvenement());

    }


    @FXML
    private void handleDragOver(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }
        private void setCellfromtabletoImage() {
        tabview.setOnMouseClicked(e -> {

            Evenement ev = tabview.getItems().get(tabview.getSelectionModel().getSelectedIndex());
            String ImageUrl ="http://localhost/bike/web/Upload/";
      

        Image image = new Image(ImageUrl + ev.getImage());
        Image_Acc.setImage(image);
        }
        );

    }
    @FXML
    private void handleDrop(DragEvent event) throws FileNotFoundException {
        List<File> files = event.getDragboard().getFiles();
        Image img = new Image(new FileInputStream(files.get(0)));
        imageview.setImage(img);
        nomImage = files.get(0).getName();
    }

    @FXML
    private void ajouter_event(ActionEvent event) throws SQLDataException, SQLException {
        float res1 = 0;
        float res2 = 0;
        LocalDate d = LocalDate.now();
        Date date = java.sql.Date.valueOf(d);
        if (datefin.getValue() != null && datedebut.getValue() != null) {

            Date dd = new java.sql.Date(new Date(datedebut.getEditor().getText()).getTime());

            Date df = new java.sql.Date(new Date(datefin.getEditor().getText()).getTime());

            long diff1 = df.getTime() - dd.getTime();

            long diff = dd.getTime() - date.getTime();
            res1 = (diff / (1000 * 60 * 60 * 24));
            res2 = (diff1 / (1000 * 60 * 60 * 24));
        }

        if (txt_titre.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de Titre", AlertDialog.image_cross);
        } else if (txt_titre.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur txt_libelle !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        } else if (datefin.getValue() == null || datedebut.getValue() == null) {

            AlertDialog.showNotification("Erreur Date !", "Vérifier votre champs !", AlertDialog.image_cross);
        } else if ((res1 < 0)) {

            AlertDialog.showNotification("Erreur Date !", "Vérifier votre date !", AlertDialog.image_cross);

        } else if ((res2 < 0)) {

            AlertDialog.showNotification("Erreur Date !", "Aujourd'hui est " + date, AlertDialog.image_cross);

        } else if (txt_lieu.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de Lieu", AlertDialog.image_cross);
        } else if (txt_lieu.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur Lieu !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        } else if (txt_nbplaces.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de nombre_places", AlertDialog.image_cross);
        } else if (Integer.valueOf(txt_nbplaces.getText()) <= 0) {
            AlertDialog.showNotification("Error !", "Champ de nombre_places", AlertDialog.image_cross);
        } else if (txt_nbplaces.getText().matches("^[a-zA-Z]+$")) {
            AlertDialog.showNotification("Erreur  !", "nombre_places incorrect", AlertDialog.image_cross);
        } 
        else if (txt_description.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de Description", AlertDialog.image_cross);
        } else if (txt_description.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur Lieu !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        }
        
        
        
        
        
        else if (nomImage.equals("")) {
            AlertDialog.showNotification("Erreur Image !", "Il faut inserer une photo", AlertDialog.image_cross);
        } else {

            String code = Abonnement_Service.getMd5(txt_titre.getText() + txt_lieu.getText()) + nomImage;
            String t = txt_titre.getText();
            String l = txt_lieu.getText();
            int nbp = Integer.parseInt(txt_nbplaces.getText());

            Date dd = new java.sql.Date(new Date(datedebut.getEditor().getText()).getTime());

            Date df = new java.sql.Date(new Date(datefin.getEditor().getText()).getTime());

            EvenementService Es = new EvenementService();
            Evenement e = new Evenement(nbp, l, t, (java.sql.Date) dd, (java.sql.Date) df, code);

            Es.addEvenement(e,txt_description.getText());
            InputStream inStream = null;
            OutputStream outStream = null;

            try {

                File afile = new File("./src/images/" + nomImage);
                File bfile = new File("C:/xampp/htdocs/bike/web/Upload/" + code);

                inStream = new FileInputStream(afile);
                outStream = new FileOutputStream(bfile);

                byte[] buffer = new byte[1024];

                int length;
                //copy the file content in bytes
                while ((length = inStream.read(buffer)) > 0) {

                    outStream.write(buffer, 0, length);

                }

                inStream.close();
                outStream.close();

                System.out.println("File is copied successful!");

            } catch (IOException exq) {
                exq.printStackTrace();
            }
            refreche();

        }
    }
    
    
       public void Modifier()
    {
         
          


          
            
        
               
                col_Titre.setOnEditCommit((TableColumn.CellEditEvent<Evenement, String> event) -> {
            TablePosition<Evenement, String> pos = event.getTablePosition();
                
            String titre = event.getNewValue();
                 
            int row = pos.getRow();
            Evenement ac = event.getTableView().getItems().get(row);
           
  
            ac.setTitre(titre);
                 
            try {
                service.ModifierEvenenment(ac);
            } catch (SQLDataException ex) {
                Logger.getLogger(home_EventController.class.getName()).log(Level.SEVERE, null, ex);
            }
                   
        });
                
                
            col_Lieu.setOnEditCommit((TableColumn.CellEditEvent<Evenement, String> event) -> {
            TablePosition<Evenement, String> pos = event.getTablePosition();
           
            String lieu = event.getNewValue();
                  
            int row = pos.getRow();
            Evenement ab = event.getTableView().getItems().get(row);
          
  
            ab.setLieu(lieu);
                    try {
                        service.ModifierEvenenment(ab);
                    } catch (SQLException ex) {
                        Logger.getLogger(home_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        });      
              col_dated.setOnEditCommit((TableColumn.CellEditEvent<Evenement, Date> event) -> {
            TablePosition<Evenement, Date> pos = event.getTablePosition();
           
            Date dated = event.getNewValue();
     
 
      
         java.sql.Date dd=  new java.sql.Date(  dated.getTime());
            int row = pos.getRow();
            Evenement ab = event.getTableView().getItems().get(row);
          
  
            ab.setDateDebut( dd);
                    try {
                       service.ModifierEvenenment(ab);
                    } catch (SQLException ex) {
                        Logger.getLogger(home_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        });       
              
              
              
                col_datef.setOnEditCommit((TableColumn.CellEditEvent<Evenement, Date> event) -> {
            TablePosition<Evenement, Date> pos = event.getTablePosition();
           
            Date dated = event.getNewValue();
     
 
      
         java.sql.Date dd=  new java.sql.Date(  dated.getTime());
            int row = pos.getRow();
            Evenement ab = event.getTableView().getItems().get(row);
          
  
            ab.setDateFin(dd);
                    try {
                       service.ModifierEvenenment(ab);
                    } catch (SQLException ex) {
                        Logger.getLogger(home_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        }); 
  
        col_nombre_places.setOnEditCommit((TableColumn.CellEditEvent<Evenement, Integer> event) -> {
            TablePosition<Evenement, Integer> pos = event.getTablePosition();
           
            Integer nbr = event.getNewValue();
                  
            int row = pos.getRow();
            Evenement ab = event.getTableView().getItems().get(row);
          
  
            ab.setNbreplaces(nbr);
                    try {
                         service.ModifierEvenenment(ab);
                    } catch (SQLException ex) {
                        Logger.getLogger(home_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        }); 
       
        
     
     
                
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
