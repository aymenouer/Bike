/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Service.Maintenance_Service;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entites.Maitenance;
import entites.Session;
import entites.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
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
public class home_MaintenanceController implements Initializable {

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
    private TableView<Maitenance> tabview;
    @FXML
    private TableColumn<Maitenance, String> col_Type;
    @FXML
    private TableColumn<Maitenance, Date> col_DateD;
    @FXML
    private TableColumn<Maitenance, Date> col_DateF;
    @FXML
    private TableColumn<Maitenance, String> col_Problem;
    @FXML
    private TableColumn<Maitenance, Float> col_prix;
    @FXML
    private TableColumn<Maitenance, String> col_Etat;
    @FXML
    private TableColumn<Maitenance, Integer> col_id;
    @FXML
    private TextField txt_Prix;
    @FXML
    private Button btn_accepter;
    @FXML
    private Button pdf;
    @FXML
    private ImageView Image_Main;
     private User usersession = Session.get();
     private Maintenance_Service service = new Maintenance_Service();
      private TableColumn<Maitenance, String> col_btnDelet;
      Maitenance man;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          tabview.setEditable(true);
             setCellfromtabletoImage();
             
             search();
        try {
            refreche();
        } catch (SQLException ex) {
            Logger.getLogger(home_MaintenanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
           String ImageUrl ="http://localhost/bike/web/uploads/images/";
        Image image = new Image(ImageUrl + usersession.getImage());


   
        imagechange.setImage(image);
        fullName.setText(usersession.getFname() + " " + usersession.getLname());
        
        
        
                col_btnDelet = new TableColumn("Supprimer");
        
                    javafx.util.Callback<TableColumn<Maitenance, String>, TableCell<Maitenance, String>> cellFactory
                = new Callback<TableColumn<Maitenance, String>, TableCell<Maitenance, String>>() {
            public TableCell call(final TableColumn<Maitenance, String> param) {
                final TableCell<Maitenance, String> cell = new TableCell<Maitenance, String>() {

                    final Button btn = new Button("supprimer");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Maitenance u = getTableView().getItems().get(getIndex());
 File f = new File("C:/xampp/htdocs/bike/web/uploads/images/Maitenance/"+u.getImage());
                            
                                System.out.println(f.delete());    
                          
                              
                                try {
                                    service.Supprimer(u.getId());
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
            col_Type.setCellValueFactory(new PropertyValueFactory<>("type"));
  col_DateD.setCellValueFactory(new PropertyValueFactory<>("DATE_D"));
  col_DateF.setCellValueFactory(new PropertyValueFactory<>("DATE_F"));
  col_Problem.setCellValueFactory(new PropertyValueFactory<>("problem"));
         col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
         col_Etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
  col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
      
  
      
        tabview.getItems().clear();

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
    private void accepter_maintenance(ActionEvent event) throws SQLException {
        
        if (txt_Prix.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de Prix", AlertDialog.image_cross);
        } else if (Float.valueOf(txt_Prix.getText()) <= 0) {
            AlertDialog.showNotification("Error !", "Champ de Prix", AlertDialog.image_cross);
        } else if (txt_Prix.getText().matches("^[a-zA-Z]+$")) {
            AlertDialog.showNotification("Erreur  !", "Prix incorrect", AlertDialog.image_cross);
        }
        else
        {
        man.setEtat("accept");
           
            
            man.setPrix(Float.valueOf(txt_Prix.getText()));
            service.Modifier(man, man.getId());
                  AlertDialog.showNotification("Modification", "acceptation", AlertDialog.image_checked);
                  refreche();
        }
            
        
    }
    
    
    
             private void setCellfromtabletoImage() {
        tabview.setOnMouseClicked(e -> {

            Maitenance main = tabview.getItems().get(tabview.getSelectionModel().getSelectedIndex());
            String ImageUrl ="http://localhost/bike/web/uploads/images/Maitenance/";
                man = tabview.getItems().get(tabview.getSelectionModel().getSelectedIndex());
     txt_Prix.setText( String.valueOf(man.getPrix()) );
        Image image = new Image(ImageUrl + main.getImage());
        Image_Main.setImage(image);
        }
        );

    }
    @FXML
    private void fairepdf(ActionEvent event) {
        
        
         Document document = new Document();
        try {

            PdfWriter.getInstance(document, new FileOutputStream("Maintenance_Admin.pdf"));
            document.open();
            Paragraph ph1 = new Paragraph("Bienvenue a Bike !");
            Paragraph ph2 = new Paragraph(".");
            PdfPTable table = new PdfPTable(7);
         
     com.itextpdf.text.Image    image1 =  com.itextpdf.text.Image.getInstance("/Users/aymen/Desktop/projet_Bike_Desktop/src/images/logo_bike.png" );
   
    //Scale to new height and new width of image
    image1.scaleAbsolute(200, 200);
    //Add to document
    document.add(image1);
   
            //On créer l'objet cellule.
            PdfPCell cell;
           
            //contenu du tableau.
            table.addCell("Type : ");
            table.addCell("Date Debut : ");
            table.addCell("Date Fin : ");
            table.addCell("Problem :");
            table.addCell("Prix : ");
               table.addCell("Etat : ");
            table.addCell("Image : ");
             
            service.Affichertout().forEach(e
                    -> {
                 table.addCell(e.getType());
             
		table.setHorizontalAlignment(Element.ALIGN_CENTER);

               
                table.addCell(String.valueOf(e.getDATE_D()));
                table.addCell(String.valueOf(e.getDATE_F()));
                table.addCell(e.getProblem());
                table.addCell(String.valueOf(e.getPrix()));
                  table.addCell(e.getEtat());
                
                try {
                    com.itextpdf.text.Image    image_table =  com.itextpdf.text.Image.getInstance("/xampp/htdocs/bike/web/uploads/images/Maitenance/"+e.getImage() );
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
        public void refreche() throws SQLException {

        col_Type.setCellValueFactory(new PropertyValueFactory<>("type"));
  col_DateD.setCellValueFactory(new PropertyValueFactory<>("DATE_D"));
  col_DateF.setCellValueFactory(new PropertyValueFactory<>("DATE_F"));
  col_Problem.setCellValueFactory(new PropertyValueFactory<>("problem"));
         col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
         col_Etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
  col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
      
  
      
        tabview.getItems().clear();

        tabview.setItems(service.Affichertout());

    }

 
}
