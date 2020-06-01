/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Service.Abonnement_Service;
import Service.Accessoire_Service;
import Service.Achat_Service;
import Service.Commande_service;
import Service.Panier_Service;
import Service.Piece_Service;
import Utils.mail;
import static controllers.Item_SiteController.site_pour_affichage_produit;
import entites.Abonnement;
import entites.Achat;
import entites.Commande;
import entites.Panier;
import entites.Session;
import entites.User;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import print.bill;
import print.billprint;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class affichageproduit_site_Front implements Initializable {

    @FXML
    private Pane pnl_Piece;
    @FXML
    private ScrollPane scrollpanePiece;
    @FXML
    private HBox hboxPiece;
    @FXML
    private Pane pnl_Commande;
    @FXML
    private TableView<Commande> tabCommande;
    @FXML
    private TableColumn<Commande, String> col_lib_produit_Commande;
    @FXML
    private TableColumn<Commande, Integer> col_quantite_produit_Commande;
    @FXML
    private TableColumn<Commande, Float> col_prix_produit_Commande;
    @FXML
    private TableColumn<Commande, String> col_Adresse_Commande;
    @FXML
    private TableColumn<Commande, String>col_Ville_Commande;
    @FXML
    private TableColumn<Commande, Integer> col_telephone_Commande;
    @FXML
    private TextField txt_adresse_Modifier;
    @FXML
    private TextField txt_Ville_Modifier;
    @FXML
    private TextField txt_telephone_Modifier;
    @FXML
    private Button btn_Modifier_Commande;
    @FXML
    private Button btn_Supprimer_Commande;
    @FXML
    private Button btn_printer;
    @FXML
    private Label label_total_Commande;
    @FXML
    private Pane pnl_panier;
    @FXML
    private Label prixTotal;
    @FXML
    private TextField txt_quantite_change;
    @FXML
    private Button btn_Modifier;
    @FXML
    private TextField txt_adresse;
    @FXML
    private TextField txt_ville;
    @FXML
    private TextField txt_telephone;
    @FXML
    private TableView<Panier> tabpanier;
    @FXML
    private TableColumn<Panier, Panier> col_lib_produit;
    @FXML
    private TableColumn<Panier, Integer> col_quantite_produit;
    @FXML
    private TableColumn<Panier, Float> col_prix_produit;
    @FXML
    private Button btn_Supprimer;
    @FXML
    private Button btn_PasserCommande;
    @FXML
    private Pane pnl_accessoire;
    @FXML
    private ScrollPane scrollpaneAccessoire;
    @FXML
    private HBox hboxAccessoire;
    @FXML
    private ImageView image_user;
    @FXML
    private Label username;
    @FXML
    private Button btn_product;
    @FXML
    private Button btn_product1;
    @FXML
    private Button btn_Back_front;
    @FXML
    private Button btn_Panier;
    @FXML
    private Button btn_Sign_Out;
 User usersession = Session.get();
   static int indiceAccessoire = 0;
   static int indicePiece = 0;
   private int tailleAccessoire=0;
   private int taillePiece=0;
   Accessoire_Service service_Accessoire = new Accessoire_Service();
    Piece_Service service_Piece = new Piece_Service();
        Panier_Service service = new Panier_Service();
          Commande comm = null;
            Panier panier = null;
             Commande_service servicecommande = new Commande_service();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           setCellfromtabletotxtcommande();
        setCellfromtabletotxt();
        prixTotal.setText(String.valueOf(service.porixtotal()) + " DT");
          Achat achat;
        try {
            achat = new Achat_Service().recap_offre(Session.getCurrentSession());
                  if (achat != null) {
                Abonnement ab = new Abonnement_Service().get_abonnment(achat.getID_A());
                
                if ( ab.getLib_C().equals("Premium"))
                {
                       prixTotal.setText(String.valueOf(service.porixtotal()-service.porixtotal()*0.15) + " DT");
                 }
                else if ( ab.getLib_C().equals("Essentiel"))
                {
                          prixTotal.setText(String.valueOf(service.porixtotal()-service.porixtotal()*0.10) + " DT");
              }
                  else if ( ab.getLib_C().equals("Standard"))
                {
                       prixTotal.setText(String.valueOf(service.porixtotal()-service.porixtotal()*0.05) + " DT");
                }
        
            }
        } catch (Exception ex) {
        
        }

        col_lib_produit.setCellValueFactory(new PropertyValueFactory<>("Libelle_P"));
        col_quantite_produit.setCellValueFactory(new PropertyValueFactory<>("Quantite_P"));
        col_prix_produit.setCellValueFactory(new PropertyValueFactory<>("Prix_P"));
     String ImageUrl = "http://localhost/bike/web/uploads/images/";
        Image image = new Image(ImageUrl + usersession.getImage());
        image_user.setImage(image);
        username.setText(usersession.getFname() + " " + usersession.getLname());
        /*--------------*/
        //accessoire
        
        try {
            tailleAccessoire = service_Accessoire.Affichertaille(site_pour_affichage_produit.getLib_S());
        } catch (SQLException ex) {
          
        }
          Node[] nodes_accessoire = new Node[tailleAccessoire];
           scrollpaneAccessoire.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
           
           
        for (indiceAccessoire = 0; indiceAccessoire < tailleAccessoire; indiceAccessoire++) {
            try {

                nodes_accessoire[indiceAccessoire] = FXMLLoader.load(getClass().getResource("/GUI/Item_Accessoire.fxml"));

                hboxAccessoire.getChildren().add(nodes_accessoire[indiceAccessoire]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
           
           /*--------------*/
            //piece
        
        try {
            taillePiece = service_Piece.Affichertaille(site_pour_affichage_produit.getLib_S());
        } catch (SQLException ex) {
          
        }
          Node[] nodes_Piece = new Node[taillePiece];
           scrollpanePiece.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
           
           
        for (indicePiece = 0; indicePiece < taillePiece; indicePiece++) {
            try {

                nodes_Piece[indicePiece] = FXMLLoader.load(getClass().getResource("/GUI/Item_Piece.fxml"));

                hboxPiece.getChildren().add(nodes_Piece[indicePiece]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }    

    @FXML
    private void modifier_Commande(ActionEvent event) throws SQLException, Exception {
          if (txt_adresse_Modifier.getText().equals("")) {
         AlertDialog.showNotification("Error !","Champ vide de adresse",AlertDialog.image_cross);
          }
                else if (txt_adresse_Modifier.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Error !","il faut saisir des caracteres  !",AlertDialog.image_cross);
        }
                       else if (txt_Ville_Modifier.getText().equals("") ){
            AlertDialog.showNotification("Error !","Champ vide de ville  !",AlertDialog.image_cross);
        }
                       else if (txt_Ville_Modifier.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Error !","il faut saisir des caracteres  !",AlertDialog.image_cross);
        }
                    else if (txt_telephone_Modifier.getText().equals("")) {
       AlertDialog.showNotification("Error !","Champ vide de Number",AlertDialog.image_cross);
        } 
                else if (txt_telephone_Modifier.getText().matches("^[a-zA-Z]+$")) {
            AlertDialog.showNotification("Error !","numero de telephone incorrect",AlertDialog.image_cross);
        }
         else if (txt_telephone_Modifier.getText().length() != 8) {
       AlertDialog.showNotification("Error !","il faut 8 chiffres ",AlertDialog.image_cross);
        }
          else
         {
             Commande commande = new Commande(1, "c", 1, 2, txt_adresse_Modifier.getText(), txt_Ville_Modifier.getText(), Integer.valueOf(txt_telephone_Modifier.getText()));
                     servicecommande.Modifier(commande, comm.getID_Commande());
                                  col_lib_produit_Commande.setCellValueFactory(new PropertyValueFactory<>("Libelle_p"));;
    col_quantite_produit_Commande.setCellValueFactory(new PropertyValueFactory<>("Quantite_P"));;
  col_prix_produit_Commande.setCellValueFactory(new PropertyValueFactory<>("Prix_P"));;
    col_Adresse_Commande.setCellValueFactory(new PropertyValueFactory<>("Adresse"));;
 col_Ville_Commande.setCellValueFactory(new PropertyValueFactory<>("Ville"));;
 col_telephone_Commande.setCellValueFactory(new PropertyValueFactory<>("Telephone"));;
                 tabCommande.setItems(servicecommande.Affichertout());
                      label_total_Commande.setText(String.valueOf(servicecommande.prixtotal()) + " DT");
                Achat achat = new Achat_Service().recap_offre(Session.getCurrentSession());
                  if (achat != null) {
                Abonnement ab = new Abonnement_Service().get_abonnment(achat.getID_A());
                
                if ( ab.getLib_C().equals("Premium"))
                {
                     label_total_Commande.setText(String.valueOf(servicecommande.prixtotal()-servicecommande.prixtotal()*0.15) + " DT");
                }
                else if ( ab.getLib_C().equals("Essentiel"))
                {
                     label_total_Commande.setText(String.valueOf(servicecommande.prixtotal()-servicecommande.prixtotal()*0.10) + " DT");
                }
                  else if ( ab.getLib_C().equals("Standard"))
                {
                     label_total_Commande.setText(String.valueOf(servicecommande.prixtotal()-servicecommande.prixtotal()*0.05) + " DT");
                }
        
            }
         
         
         }
    }

    @FXML
    private void supprimer_Commande(ActionEvent event) throws SQLException, Exception {
         if (comm == null) {
            AlertDialog.showNotification("Error !", "il faut selectionner un produit", AlertDialog.image_cross);

        } else {
            servicecommande.Supprimer(comm.getID_Commande());
            AlertDialog.showNotification("suppression confirmée", "suppression a été bien faite", AlertDialog.image_checked);
     tabCommande.setItems(servicecommande.Affichertout());
            label_total_Commande.setText(String.valueOf(servicecommande.prixtotal()) + " DT");
                 Achat achat = new Achat_Service().recap_offre(Session.getCurrentSession());
                  if (achat != null) {
                Abonnement ab = new Abonnement_Service().get_abonnment(achat.getID_A());
                
                if ( ab.getLib_C().equals("Premium"))
                {
                     label_total_Commande.setText(String.valueOf(servicecommande.prixtotal()-servicecommande.prixtotal()*0.15) + " DT");
                }
                else if ( ab.getLib_C().equals("Essentiel"))
                {
                     label_total_Commande.setText(String.valueOf(servicecommande.prixtotal()-servicecommande.prixtotal()*0.10) + " DT");
                }
                  else if ( ab.getLib_C().equals("Standard"))
                {
                     label_total_Commande.setText(String.valueOf(servicecommande.prixtotal()-servicecommande.prixtotal()*0.05) + " DT");
                }
        
            }
         }
    }

    @FXML
    private void printer_COmmande(ActionEvent event) throws Exception {
          PrinterJob pj = PrinterJob.getPrinterJob();        
        pj.setPrintable(new billprint(),bill.getPageFormat(pj));
       //   mail.envoi(usersession.getMail(), "Commande", "Bike vous annonce , <br> que votre commande  a été ajoutée  \n ");

        try {
             pj.print();
          
        }
         catch (PrinterException ex) {
                 ex.printStackTrace();
        }
    }

    @FXML
    private void Modifier_quantite(ActionEvent event) throws SQLException {
         if (txt_quantite_change.getText().equals("")) {
            AlertDialog.showNotification("Error !", "champ vide", AlertDialog.image_cross);
        }
        else if (txt_quantite_change.getText().matches("^[a-zA-Z]+$")) {
            AlertDialog.showNotification("Error !", "quantite incorrect", AlertDialog.image_cross);
        } else {
            Panier p = new Panier(1, 1, "cc", Integer.valueOf(txt_quantite_change.getText()), 20.5f);
            service.Modifier(p, panier.getId_panier());
            col_lib_produit.setCellValueFactory(new PropertyValueFactory<>("Libelle_P"));
            col_quantite_produit.setCellValueFactory(new PropertyValueFactory<>("Quantite_P"));
            col_prix_produit.setCellValueFactory(new PropertyValueFactory<>("Prix_P"));
            try {
                tabpanier.setItems(service.Affichertout());
                prixTotal.setText(String.valueOf(service.porixtotal()) + " DT");
                   Achat achat;
        try {
            achat = new Achat_Service().recap_offre(Session.getCurrentSession());
                  if (achat != null) {
                Abonnement ab = new Abonnement_Service().get_abonnment(achat.getID_A());
                
                if ( ab.getLib_C().equals("Premium"))
                {
                       prixTotal.setText(String.valueOf(service.porixtotal()-service.porixtotal()*0.15) + " DT");
                 }
                else if ( ab.getLib_C().equals("Essentiel"))
                {
                          prixTotal.setText(String.valueOf(service.porixtotal()-service.porixtotal()*0.10) + " DT");
              }
                  else if ( ab.getLib_C().equals("Standard"))
                {
                       prixTotal.setText(String.valueOf(service.porixtotal()-service.porixtotal()*0.05) + " DT");
                }
        
            }
        } catch (Exception ex) {
        
        }
            } catch (SQLException ex) {
                Logger.getLogger(affichageproduit_site_Front.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLException {
         if (panier == null) {
            AlertDialog.showNotification("Error !", "il faut selectionner un produit", AlertDialog.image_cross);

        } else {
            service.Supprimer(panier.getId_panier());
            AlertDialog.showNotification("suppression confirmée", "suppression a été bien modifiée", AlertDialog.image_checked);
            tabpanier.setItems(service.Affichertout());
            prixTotal.setText(String.valueOf(service.porixtotal()) + " DT");
      Achat achat;
        try {
            achat = new Achat_Service().recap_offre(Session.getCurrentSession());
                  if (achat != null) {
                Abonnement ab = new Abonnement_Service().get_abonnment(achat.getID_A());
                
                if ( ab.getLib_C().equals("Premium"))
                {
                       prixTotal.setText(String.valueOf(service.porixtotal()-service.porixtotal()*0.15) + " DT");
                 }
                else if ( ab.getLib_C().equals("Essentiel"))
                {
                          prixTotal.setText(String.valueOf(service.porixtotal()-service.porixtotal()*0.10) + " DT");
              }
                  else if ( ab.getLib_C().equals("Standard"))
                {
                       prixTotal.setText(String.valueOf(service.porixtotal()-service.porixtotal()*0.05) + " DT");
                }
        
            }
        } catch (Exception ex) {
        
        }
         
         
         }
    }
    private void setCellfromtabletotxt() {
        tabpanier.setOnMouseClicked(e -> {
            panier = tabpanier.getItems().get(tabpanier.getSelectionModel().getSelectedIndex());
            txt_quantite_change.setText(String.valueOf(panier.getQuantite_P()));
        }
        );

    }
        private void setCellfromtabletotxtcommande() {
        tabCommande.setOnMouseClicked(e -> {
            comm = tabCommande.getItems().get(tabCommande.getSelectionModel().getSelectedIndex());
            txt_telephone_Modifier.setText(String.valueOf(comm.getTelephone()));
            txt_adresse_Modifier.setText(comm.getAdresse());
            txt_Ville_Modifier.setText(comm.getVille());
        }
        );

    }
    @FXML
    private void handleClicks(ActionEvent event) throws IOException, Exception {
           if (event.getSource() == btn_Panier) {
            pnl_panier.toFront();
            tabpanier.setItems(service.Affichertout());
            prixTotal.setText(String.valueOf(service.porixtotal()) + " DT");
        Achat achat;
        try {
            achat = new Achat_Service().recap_offre(Session.getCurrentSession());
                  if (achat != null) {
                Abonnement ab = new Abonnement_Service().get_abonnment(achat.getID_A());
                
                if ( ab.getLib_C().equals("Premium"))
                {
                       prixTotal.setText(String.valueOf(service.porixtotal()-service.porixtotal()*0.15) + " DT");
                 }
                else if ( ab.getLib_C().equals("Essentiel"))
                {
                          prixTotal.setText(String.valueOf(service.porixtotal()-service.porixtotal()*0.10) + " DT");
              }
                  else if ( ab.getLib_C().equals("Standard"))
                {
                       prixTotal.setText(String.valueOf(service.porixtotal()-service.porixtotal()*0.05) + " DT");
                }
        
            }
        } catch (Exception ex) {
        
        }
           }
                if (event.getSource()==btn_PasserCommande)
        {
            
            
             if (txt_adresse.getText().equals("")) {
         AlertDialog.showNotification("Error !","Champ vide de adresse",AlertDialog.image_cross);
          }
                else if (txt_adresse.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Error !","il faut saisir des caracteres  !",AlertDialog.image_cross);
        }
                       else if (txt_ville.getText().equals("") ){
            AlertDialog.showNotification("Error !","Champ vide de ville  !",AlertDialog.image_cross);
        }
                       else if (txt_ville.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Error !","il faut saisir des caracteres  !",AlertDialog.image_cross);
        }
                    else if (txt_telephone.getText().equals("")) {
       AlertDialog.showNotification("Error !","Champ vide de Number",AlertDialog.image_cross);
        } 
                else if (txt_telephone.getText().matches("^[a-zA-Z]+$")) {
            AlertDialog.showNotification("Error !","numero de telephone incorrect",AlertDialog.image_cross);
        }
         else if (txt_telephone.getText().length() != 8) {
       AlertDialog.showNotification("Error !","il faut 8 chiffres ",AlertDialog.image_cross);
        }
            else
         {
                    Commande com = new Commande(1, "s", 1, 1, txt_adresse.getText(), txt_ville.getText(), Integer.valueOf(txt_telephone.getText()));
             pnl_Commande.toFront();
             servicecommande.Ajouter(com); 
             
             

               col_lib_produit_Commande.setCellValueFactory(new PropertyValueFactory<>("Libelle_p"));;
    col_quantite_produit_Commande.setCellValueFactory(new PropertyValueFactory<>("Quantite_P"));;
  col_prix_produit_Commande.setCellValueFactory(new PropertyValueFactory<>("Prix_P"));;
    col_Adresse_Commande.setCellValueFactory(new PropertyValueFactory<>("Adresse"));;
 col_Ville_Commande.setCellValueFactory(new PropertyValueFactory<>("Ville"));;
 col_telephone_Commande.setCellValueFactory(new PropertyValueFactory<>("Telephone"));;
                 tabCommande.setItems(servicecommande.Affichertout());
             
             
                  label_total_Commande.setText(String.valueOf(servicecommande.prixtotal()) + " DT");
              Achat achat = new Achat_Service().recap_offre(Session.getCurrentSession());
                  if (achat != null) {
                Abonnement ab = new Abonnement_Service().get_abonnment(achat.getID_A());
                
                if ( ab.getLib_C().equals("Premium"))
                {
                     label_total_Commande.setText(String.valueOf(servicecommande.prixtotal()-servicecommande.prixtotal()*0.15) + " DT");
                }
                else if ( ab.getLib_C().equals("Essentiel"))
                {
                     label_total_Commande.setText(String.valueOf(servicecommande.prixtotal()-servicecommande.prixtotal()*0.10) + " DT");
                }
                  else if ( ab.getLib_C().equals("Standard"))
                {
                     label_total_Commande.setText(String.valueOf(servicecommande.prixtotal()-servicecommande.prixtotal()*0.05) + " DT");
                }
        
            }
             
             
             
         }
            
            
            
            
            
    
             
        }
          if (event.getSource() == btn_Back_front) {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front.fxml")));
            stage.setScene(scene);
            stage.show();
        }
          
        if (event.getSource() == btn_Sign_Out) {
            Session.close();
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Login.fxml")));
            stage.setScene(scene);
            stage.show();
            new Panier_Service().emtypanier();
            new Commande_service().emtyCommande();
        }
           if (event.getSource() == btn_product) {
            pnl_accessoire.toFront();
        }
              if (event.getSource() == btn_product1) {
            pnl_Piece.toFront();
        }
    }
    
}
