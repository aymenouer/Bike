/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Service.Abonnement_Service;
import Service.Categorie_Service;
import Service.Site_Service;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entites.Abonnement;
import entites.Session;
import entites.Site;
import entites.User;
import java.io.File;
import java.io.FileOutputStream;
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
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class home_AbonemetController implements Initializable {

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
    private TableView<Abonnement> tabview;
    @FXML
    private TableColumn<Abonnement, String> col_libelle;
    @FXML
    private TableColumn<Abonnement, String> col_categorie;
    @FXML
    private TableColumn<Abonnement, String> col_site;
    @FXML
    private TableColumn<Abonnement, String> col_description;
    @FXML
    private TableColumn<Abonnement, Float> col_prix;
    @FXML
    private TableColumn<Abonnement, Integer> col_quantite;
    @FXML
    private TableColumn<Abonnement, Integer> col_id;
    @FXML
    private TextField txt_libelle;
    @FXML
    private TextField txt_prix;
    @FXML
    private TextField txt_quantite;
    @FXML
    private TextField txt_description;
    @FXML
    private ComboBox<String> combo_categorie;
    @FXML
    private ComboBox<String> combo_site;
    @FXML
    private Button btn_ajout;
    @FXML
    private Button Print_PDF;
    @FXML
    private ImageView Image_Qr;
  private User usersession = Session.get();
     private Categorie_Service serviceCatgorie = new Categorie_Service();
    private Site_Service servicesite = new Site_Service();
        private Abonnement_Service service = new Abonnement_Service();
           private TableColumn<Abonnement, String> col_btnDelet;
    @FXML
    private Button Faire_Stat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tabview.setEditable(true);
        Modifier();
        search();
        setCellfromtabletoImageQR();
        
        try { 
            refreche();
        } catch (SQLException ex) {
            Logger.getLogger(home_AbonemetController.class.getName()).log(Level.SEVERE, null, ex);
        }
         combo_categorie.setItems(serviceCatgorie.get_List_Categories_Abonment());
        combo_site.setItems(servicesite.get_List_sites());
        combo_categorie.getSelectionModel().select(0);
        combo_site.getSelectionModel().select(0);
          String ImageUrl ="http://localhost/bike/web/uploads/images/";
        Image image = new Image(ImageUrl + usersession.getImage());


   
        imagechange.setImage(image);
        fullName.setText(usersession.getFname() + " " + usersession.getLname());
            col_btnDelet = new TableColumn("Supprimer");
              javafx.util.Callback<TableColumn<Abonnement, String>, TableCell<Abonnement, String>> cellFactory
                = new Callback<TableColumn<Abonnement, String>, TableCell<Abonnement, String>>() {
            public TableCell call(final TableColumn<Abonnement, String> param) {
                final TableCell<Abonnement, String> cell = new TableCell<Abonnement, String>() {

                    final Button btn = new Button("supprimer");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Abonnement u = getTableView().getItems().get(getIndex());
                                File f = new File("C:/xampp/htdocs/bike/web/uploads/images/qrproduit/"+u.getImage());
                            
                                System.out.println(f.delete());                             


                                try {
                                    service.Supprimer(u.getId_A());
                                } catch (SQLException ex) {
                                    Logger.getLogger(home_AbonemetController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                AlertDialog.showNotification("suppression confirmée!", "suppression a été bien faite ", AlertDialog.image_checked);

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
 public void refreche() throws SQLException {

        col_libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
          col_libelle.setCellFactory(TextFieldTableCell.<Abonnement> forTableColumn());
        col_categorie.setCellValueFactory(new PropertyValueFactory<>("Lib_C"));
         col_categorie.setCellFactory(TextFieldTableCell.<Abonnement> forTableColumn());
        col_site.setCellValueFactory(new PropertyValueFactory<>("Lib_S"));
                  col_site.setCellFactory(TextFieldTableCell.<Abonnement> forTableColumn());
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
           col_description.setCellFactory(TextFieldTableCell.<Abonnement> forTableColumn());
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
          col_prix.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        col_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        col_quantite.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        col_id.setCellValueFactory(new PropertyValueFactory<>("id_A"));

        tabview.getItems().clear();

        tabview.setItems(service.Affichertout());

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
                    col_libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
                    col_categorie.setCellValueFactory(new PropertyValueFactory<>("Lib_C"));
                    col_site.setCellValueFactory(new PropertyValueFactory<>("Lib_S"));
                    col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
                    col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
                    col_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
                    col_id.setCellValueFactory(new PropertyValueFactory<>("id_A"));
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
    private void ajouter_abonment(ActionEvent event) throws SQLException {
         if (txt_libelle.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de libelle", AlertDialog.image_cross);
        } else if (txt_libelle.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur txt_libelle !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        } else if (txt_prix.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de Prix", AlertDialog.image_cross);
        } 
         else if (txt_prix.getText().matches("^[a-zA-Z]+$")) {
            AlertDialog.showNotification("Erreur!", "Prix incorrect", AlertDialog.image_cross);
        }
        else if (Float.valueOf(txt_prix.getText()) <= 0) {
            AlertDialog.showNotification("Error !", "Champ de Prix", AlertDialog.image_cross);
        } else if (txt_quantite.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de quantite", AlertDialog.image_cross);
        } else if (txt_quantite.getText().matches("^[a-zA-Z]+$")) {
            AlertDialog.showNotification("Erreur ", "quantite incorrect", AlertDialog.image_cross);
        } else if (Integer.valueOf(txt_quantite.getText()) <= 0) {
            AlertDialog.showNotification("Error !", "Champ de quantite", AlertDialog.image_cross);
        } else if (txt_description.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de Description", AlertDialog.image_cross);
        } else if (txt_description.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur Description !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        }
        
        else {

             Abonnement  abonnementajouter = new Abonnement(Integer.parseInt(txt_quantite.getText()), txt_libelle.getText(), combo_categorie.getSelectionModel().getSelectedItem(), combo_site.getSelectionModel().getSelectedItem(), "c", txt_description.getText(), Float.parseFloat(txt_prix.getText()));
            service.Ajouter(abonnementajouter);
            refreche();
        }
    }

    @FXML
    private void Print_PDF(ActionEvent event) {
          Document document = new Document();
        try {

            PdfWriter.getInstance(document, new FileOutputStream("Abonnment.pdf"));
            document.open();
            Paragraph ph1 = new Paragraph("Bienvenue a Bike !");
            Paragraph ph2 = new Paragraph(".");
            PdfPTable table = new PdfPTable(6);
         
     com.itextpdf.text.Image    image1 =  com.itextpdf.text.Image.getInstance("/Users/aymen/Desktop/projet_Bike_Desktop/src/images/logo_bike.png" );
   
    //Scale to new height and new width of image
    image1.scaleAbsolute(200, 200);
    //Add to document
    document.add(image1);
   
            //On créer l'objet cellule.
            PdfPCell cell;
           
            //contenu du tableau.
            table.addCell("Libelle : ");
            table.addCell("Prix : ");
            table.addCell("Quantite : ");
            table.addCell("Categorie :");
            table.addCell("Site : ");
            table.addCell("Image : ");
             
            service.Affichertout().forEach(e
                    -> {
                 table.addCell(e.getLibelle());
             
		table.setHorizontalAlignment(Element.ALIGN_CENTER);

               
                table.addCell(String.valueOf(e.getPrix()));
                table.addCell(String.valueOf(e.getQuantite()));
                table.addCell(e.getLib_C());
                table.addCell(e.getLib_S());
                try {
                    com.itextpdf.text.Image    image_table =  com.itextpdf.text.Image.getInstance("/xampp/htdocs/bike/web/uploads/images/qrproduit/"+e.getImage() );
                    image_table.scaleAbsolute(200, 200);
   table.addCell(image_table);
                } catch (BadElementException ex) {
                    Logger.getLogger(home_AbonemetController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(home_AbonemetController.class.getName()).log(Level.SEVERE, null, ex);
                }
   
    //Scale to new height and new width of image

    //Add to document

             
            }
            );
            document.add(ph1);
            document.add(ph2);
            document.add(table);
            document.addAuthor("Bike");
            AlertDialog.showNotification("Creation PDF ", "Votre fichier PDF a ete cree avec success", AlertDialog.image_checked);
        } catch (Exception e) {
            System.out.println(e);
        }
        document.close();
    }
        private void setCellfromtabletoImageQR() {
        tabview.setOnMouseClicked(e -> {

            Abonnement ab = tabview.getItems().get(tabview.getSelectionModel().getSelectedIndex());
            String ImageUrl ="http://localhost/bike/web/uploads/images/qrproduit/";
        

        Image image = new Image(ImageUrl + ab.getImage());
        Image_Qr.setImage(image);
        }
        );

    }
              public void Modifier()
    {
         
                   
                  
                   
            
        
               
                col_libelle.setOnEditCommit((TableColumn.CellEditEvent<Abonnement, String> event) -> {
            TablePosition<Abonnement, String> pos = event.getTablePosition();
                
            String libelle_Ab = event.getNewValue();
                 
            int row = pos.getRow();
            Abonnement ab = event.getTableView().getItems().get(row);
           
  
            ab.setLibelle(libelle_Ab);
                    try {
                        service.Modifier(ab,ab.getId_A());
                    } catch (SQLException ex) {
                        Logger.getLogger(home_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        });
                
                
            col_categorie.setOnEditCommit((TableColumn.CellEditEvent<Abonnement, String> event) -> {
            TablePosition<Abonnement, String> pos = event.getTablePosition();
           
            String Categorie_Ab = event.getNewValue();
                  
            int row = pos.getRow();
            Abonnement ab = event.getTableView().getItems().get(row);
          
  
            ab.setLib_C(Categorie_Ab);
                    try {
                        service.Modifier(ab,ab.getId_A());
                    } catch (SQLException ex) {
                        Logger.getLogger(home_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        });      
              col_site.setOnEditCommit((TableColumn.CellEditEvent<Abonnement, String> event) -> {
            TablePosition<Abonnement, String> pos = event.getTablePosition();
           
            String Site_Ab = event.getNewValue();
                  
            int row = pos.getRow();
            Abonnement ab = event.getTableView().getItems().get(row);
          
  
            ab.setLib_S(Site_Ab);
                    try {
                        service.Modifier(ab,ab.getId_A());
                    } catch (SQLException ex) {
                        Logger.getLogger(home_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        });       
              
              
              
                        col_description.setOnEditCommit((TableColumn.CellEditEvent<Abonnement, String> event) -> {
            TablePosition<Abonnement, String> pos = event.getTablePosition();
           
            String Description_Ab = event.getNewValue();
                  
            int row = pos.getRow();
            Abonnement ab = event.getTableView().getItems().get(row);
          
  
            ab.setDescription(Description_Ab);
                    try {
                        service.Modifier(ab,ab.getId_A());
                    } catch (SQLException ex) {
                        Logger.getLogger(home_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        });       
              
     col_quantite.setOnEditCommit((TableColumn.CellEditEvent<Abonnement, Integer> event) -> {
            TablePosition<Abonnement, Integer> pos = event.getTablePosition();
           
            Integer Quantite_Ab = event.getNewValue();
                  
            int row = pos.getRow();
            Abonnement ab = event.getTableView().getItems().get(row);
          
  
            ab.setQuantite(Quantite_Ab);
                    try {
                        service.Modifier(ab,ab.getId_A());
                    } catch (SQLException ex) {
                        Logger.getLogger(home_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        });  
     
     col_prix.setOnEditCommit((TableColumn.CellEditEvent<Abonnement, Float> event) -> {
            TablePosition<Abonnement, Float> pos = event.getTablePosition();
           
            Float Prix_Ab = event.getNewValue();
                  
            int row = pos.getRow();
            Abonnement ab = event.getTableView().getItems().get(row);
          
  
            ab.setPrix(Prix_Ab);
                    try {
                        service.Modifier(ab,ab.getId_A());
                    } catch (SQLException ex) {
                        Logger.getLogger(home_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        });
     
     
     
                
    }

    @FXML
    private void Stat(ActionEvent event) {
                   Scene scene;
        Stage stage = new Stage();
        try {

            scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Statistique_Abonnment.fxml")));
            stage.setScene(scene);
            stage.setTitle("Statistique Abonnment");
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(homeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        
}
