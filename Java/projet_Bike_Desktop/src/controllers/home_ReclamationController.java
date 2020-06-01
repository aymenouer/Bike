/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Service.reclamation_Service;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entites.Session;
import entites.User;
import entites.reclamation;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class home_ReclamationController implements Initializable {

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
    private TableView<reclamation> tabview;
    @FXML
    private TableColumn<reclamation, Integer> col_id;
    @FXML
    private TableColumn<reclamation, String> col_Contenu;
    @FXML
    private TableColumn<reclamation, String> col_Type;
    @FXML
    private TableColumn<reclamation, String> col_etat;
    @FXML
    private TableColumn<reclamation, String> col_traite;
    @FXML
    private Button btn_Traiter;
    @FXML
    private Button pdf;
    @FXML
    private ImageView Image_rec;
    private User usersession = Session.get();
    private reclamation_Service service = new reclamation_Service();
    private TableColumn<reclamation, String> col_btnDelet;
    reclamation reclamation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        search();
        
        try {
            refreche();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        setCellfromtabletoImage();
        tabview.setEditable(true);
        String ImageUrl = "http://localhost/bike/web/uploads/images/";
        Image image = new Image(ImageUrl + usersession.getImage());
        imagechange.setImage(image);
        fullName.setText(usersession.getFname() + " " + usersession.getLname());
        
         col_btnDelet = new TableColumn("Supprimer");
        
        
        
                    javafx.util.Callback<TableColumn<reclamation, String>, TableCell<reclamation, String>> cellFactory
                = new Callback<TableColumn<reclamation, String>, TableCell<reclamation, String>>() {
            public TableCell call(final TableColumn<reclamation, String> param) {
                final TableCell<reclamation, String> cell = new TableCell<reclamation, String>() {

                    final Button btn = new Button("supprimer");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                reclamation u = getTableView().getItems().get(getIndex());
 File f = new File("C:/xampp/htdocs/bike/web/uploads/images/"+u.getImage());
                            
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

    @FXML
    private void Traiter_Reclamation(ActionEvent event) throws SQLException {
           if (reclamation.getEtat().equals("Bien"))
        {
             AlertDialog.showNotification("erreur", "Etatt est Bien", AlertDialog.image_cross);
                     
        }
        else if (reclamation.getTraite().equals("Traite"))
        {
             AlertDialog.showNotification("erreur", "elle est deja Traite", AlertDialog.image_cross);
                     
        }
           else
        {
            reclamation.setTraite("Traite");
            service.Modifier(reclamation, reclamation.getId());
                      AlertDialog.showNotification("Modifcation", "Modifcation avec success", AlertDialog.image_checked);
       
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
         col_Type.setCellValueFactory(new PropertyValueFactory<>("type"));
          col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
  col_Contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
  col_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
  col_traite.setCellValueFactory(new PropertyValueFactory<>("traite"));
   
      
  
      
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
    private void fairepdf(ActionEvent event) {
        
                
         Document document = new Document();
        try {

            PdfWriter.getInstance(document, new FileOutputStream("reclamation_users_admin.pdf"));
            document.open();
            Paragraph ph1 = new Paragraph("Bienvenue a Bike !");
            Paragraph ph2 = new Paragraph(".");
            PdfPTable table = new PdfPTable(5);
         
     com.itextpdf.text.Image    image1 =  com.itextpdf.text.Image.getInstance("/Users/aymen/Desktop/projet_Bike_Desktop/src/images/logo_bike.png" );
   
    //Scale to new height and new width of image
    image1.scaleAbsolute(200, 200);
    //Add to document
    document.add(image1);
   
            //On créer l'objet cellule.
            PdfPCell cell;
           
            //contenu du tableau.
            table.addCell("Type : ");
            table.addCell("Contenu : ");
            table.addCell("etat : ");
            table.addCell("Traite :");
            table.addCell("Image : ");
             
            service.Affichertout().forEach(e
                    -> {
                 table.addCell(e.getType());
             
		table.setHorizontalAlignment(Element.ALIGN_CENTER);

               
                table.addCell(String.valueOf(e.getContenu()));
                table.addCell(String.valueOf(e.getEtat()));
                  table.addCell(e.getEtat());
                
                try {
                    com.itextpdf.text.Image    image_table =  com.itextpdf.text.Image.getInstance("/xampp/htdocs/bike/web/uploads/images/"+e.getImage() );
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

    private void setCellfromtabletoImage() {
        tabview.setOnMouseClicked(e -> {

            reclamation rec = tabview.getItems().get(tabview.getSelectionModel().getSelectedIndex());
            String ImageUrl = "http://localhost/bike/web/uploads/images/";
            reclamation = tabview.getItems().get(tabview.getSelectionModel().getSelectedIndex());
     
            Image image = new Image(ImageUrl + reclamation.getImage());
        
            Image_rec.setImage(image);
        }
        );

    }
     public void refreche() throws SQLException {

        col_Type.setCellValueFactory(new PropertyValueFactory<>("type"));
          col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
  col_Contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
  col_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
  col_traite.setCellValueFactory(new PropertyValueFactory<>("traite"));
   

      
        tabview.getItems().clear();

        tabview.setItems(service.Affichertout());

    }
}
