/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Service.Abonnement_Service;
import Service.Maintenance_Service;
import Service.User_Service;
import Utils.MyConnexion;
import entites.Session;
import entites.User;
import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane layersignup;
    @FXML
    private AnchorPane layer1;
    @FXML
    private Label a2;
    @FXML
    private Label a1;
    @FXML
    private Label b2;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField email;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private TextField Number;
    @FXML
    private Button btnsignup;
    @FXML
    private Label b1;
    @FXML
    private Button btnsignin;
    @FXML
    private Label n3;
    @FXML
       private ComboBox<Integer> age;
    @FXML
    private ImageView imageview;
    @FXML
    private TextField n1;
    @FXML
    private PasswordField n2;
    @FXML
    private Label lbl_firstname;
    @FXML
    private Label lbl_lastname;
    @FXML
    private Label lbl_email;
    @FXML
    private Label lbl_username;
    @FXML
    private Label lbl_password;
    @FXML
    private Label lbl_number;
    @FXML
    private Label lbl_age;
    @FXML
    private Label lbl_image;
    @FXML
    private Label lblErrors;
    @FXML
    private AnchorPane layer2;
    @FXML
    private Label l1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;
    @FXML
    private Button signin;
    @FXML
    private Button signup;
    @FXML
    private Label s1;
    @FXML
    private Label s2;
    @FXML
    private Label s3;
 static String nomImage = "";
    private User_Service serviceUser = new User_Service();
    private Connection con = MyConnexion.getInsCon().getcnx();
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    
        try {
            new Maintenance_Service().mise_a_jour();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
          n3.setOnMouseDragOver(e ->
        
                {
                    n3.setStyle("-fx-background-color : #393351;");
                }
        
        );
        
        
        n3.setOnMouseClicked(e ->
        
                {
                    
            try {
                Stage stage = new Stage();
       
               Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/forgetpassword.fxml")));
              stage.setScene(scene);
                    stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
                  
                }
        
        
        
        
        );
        
        
        
        
        age.getItems().addAll(18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40);
        age.getSelectionModel().select(0);

        if (con == null) {
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Server Error : Check");
        } else {
            lblErrors.setTextFill(Color.GREEN);
            lblErrors.setText("Server is up : Good to go");
        }

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(layer2);

        slide.setToX(491);
        slide.play();

        layer1.setTranslateX(-309);
        btnsignin.setVisible(true);
        b1.setVisible(true);
        b2.setVisible(true);

        s1.setVisible(true);
        s2.setVisible(true);
        s3.setVisible(true);
        signup.setVisible(true);
        l1.setVisible(false);
        l2.setVisible(false);
        l3.setVisible(false);
        signin.setVisible(false);
        a1.setVisible(false);
        a2.setVisible(false);
        btnsignup.setVisible(false);
        n1.setVisible(true);
        n2.setVisible(true);
        n3.setVisible(true);
        lblErrors.setVisible(true);
        
        firstname.setVisible(false);
        lastname.setVisible(false);
        email.setVisible(false);
        username.setVisible(false);
        password.setVisible(false);
        Number.setVisible(false);
        age.setVisible(false);
        imageview.setVisible(false);
        lbl_firstname.setVisible(false);
        lbl_lastname.setVisible(false);
        lbl_email.setVisible(false);
        lbl_username.setVisible(false);
        lbl_password.setVisible(false);
        lbl_number.setVisible(false);
        lbl_age.setVisible(false);
        lbl_image.setVisible(false);
        
        slide.setOnFinished((e -> {

        }));
    }    

    @FXML
     private void btnsignup(MouseEvent event) throws SQLException, AWTException, MalformedURLException, Exception {
        if (firstname.getText().equals("")) {
         AlertDialog.showNotification("Error !","Champ vide de firstname",AlertDialog.image_cross);
          }
                else if (firstname.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur firstname !","il faut saisir des caracteres  !",AlertDialog.image_cross);
        }
        else if (lastname.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur lastname !","il faut saisir des caracteres !",AlertDialog.image_cross);
        }
        else if (lastname.getText().equals("")) {
            AlertDialog.showNotification("Error !","Champ vide de lastname",AlertDialog.image_cross);
        } 
        
        else if (email.getText().equals("")) {
           AlertDialog.showNotification("Error !","Champ vide de email",AlertDialog.image_cross);
        } 
        
         else if ( ! email.getText().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
           AlertDialog.showNotification("Erreur Email !","email incorrect ",AlertDialog.image_cross);
        } 
                else if (username.getText().equals("")) {
           AlertDialog.showNotification("Error !","Champ vide de Username",AlertDialog.image_cross);
        }
                 else if (password.getText().equals("")) {
            AlertDialog.showNotification("Error !","Champ vide de password",AlertDialog.image_cross);
        } 
        else if (Number.getText().equals("")) {
       AlertDialog.showNotification("Error !","Champ vide de Number",AlertDialog.image_cross);
        } 
                else if (Number.getText().matches("^[a-zA-Z]+$")) {
            AlertDialog.showNotification("Erreur Telephone !","numero de telephone incorrect",AlertDialog.image_cross);
        }
         else if (Number.getText().length() != 8) {
       AlertDialog.showNotification("Erreur Telephone !","il faut 8 chiffres ",AlertDialog.image_cross);
        }
else if (nomImage.equals("")) {
            AlertDialog.showNotification("Erreur Image !","Il faut inserer une photo",AlertDialog.image_cross);
        } else {
    String code =Abonnement_Service.getMd5(firstname.getText()+ lastname.getText())+nomImage;
            User u = new User(firstname.getText(), lastname.getText(), email.getText(), Integer.parseInt(Number.getText()), username.getText(), password.getText(), "Client", Integer.valueOf(age.getSelectionModel().getSelectedItem()), code);
            
            serviceUser.Ajouter(u);

            
            
                          InputStream inStream = null;
    OutputStream outStream = null;

        try{

            File afile =new File("./src/images/"+nomImage);
            File bfile =new File("C:/xampp/htdocs/bike/web/uploads/images/"+code);
            

            inStream = new FileInputStream(afile);
            outStream = new FileOutputStream(bfile);

            byte[]buffer = new byte[1024];

            int length;
           //copy the file content in bytes
            while ((length = inStream.read(buffer)) > 0){

                outStream.write(buffer, 0, length);

            }

            inStream.close();
            outStream.close();

            System.out.println("File is copied successful!");

        }catch(IOException e){
            e.printStackTrace();
        }
           
            
            
            
            
            
            
            
            
            
            
            
            
            
            
           
             TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(layer2);

        slide.setToX(491);
        slide.play();

        layer1.setTranslateX(-309);
        btnsignin.setVisible(true);
        b1.setVisible(true);
        b2.setVisible(true);

        s1.setVisible(true);
        s2.setVisible(true);
        s3.setVisible(true);
        signup.setVisible(true);
        l1.setVisible(false);
        l2.setVisible(false);
        l3.setVisible(false);
        signin.setVisible(false);
        a1.setVisible(false);
        a2.setVisible(false);
        btnsignup.setVisible(false);
        n1.setVisible(true);
        n2.setVisible(true);
        n3.setVisible(true);
        lblErrors.setVisible(true);
        firstname.setVisible(false);
        lastname.setVisible(false);
        email.setVisible(false);
        username.setVisible(false);
        password.setVisible(false);
        Number.setVisible(false);
        age.setVisible(false);
        imageview.setVisible(false);
        lbl_firstname.setVisible(false);
        lbl_lastname.setVisible(false);
        lbl_email.setVisible(false);
        lbl_username.setVisible(false);
        lbl_password.setVisible(false);
        lbl_number.setVisible(false);
        lbl_age.setVisible(false);
        lbl_image.setVisible(false);
        slide.setOnFinished((e -> {

        }));
          
        }
    }


    @FXML
    private void sign(MouseEvent event) {
    }

    @FXML
    private void click(ActionEvent event) {
       
    
        
        
        
        if (logIn().equals("Success")) {
            try {
                
                User usersession = Session.get();
               
                if (usersession.getRole().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {

                    Node node = (Node) event.getSource();
                    
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                   
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/page_admin.fxml")));
                    stage.setScene(scene);
                    stage.show();
                } else {
                   
   


  Node node = (Node) event.getSource();

                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front.fxml")));
                    stage.setScene(scene);
                    stage.show();
                }

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }

        }

    }


    @FXML
    private void handleDragOver(DragEvent event) {
           if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    private void handleDrop(DragEvent event) throws FileNotFoundException {
          List<File> files = event.getDragboard().getFiles();
        Image img = new Image(new FileInputStream(files.get(0)));
        imageview.setImage(img);
        nomImage = files.get(0).getName();
    }

    @FXML
    private void btn(MouseEvent event) {
         TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(layer2);

        slide.setToX(491);
        slide.play();

        layer1.setTranslateX(-309);
        btnsignin.setVisible(true);
        b1.setVisible(true);
        b2.setVisible(true);

        s1.setVisible(true);
        s2.setVisible(true);
        s3.setVisible(true);
        signup.setVisible(true);
        l1.setVisible(false);
        l2.setVisible(false);
        l3.setVisible(false);
        signin.setVisible(false);
        a1.setVisible(false);
        a2.setVisible(false);
        btnsignup.setVisible(false);
        n1.setVisible(true);
        n2.setVisible(true);
        n3.setVisible(true);
        lblErrors.setVisible(true);
        firstname.setVisible(false);
        lastname.setVisible(false);
        email.setVisible(false);
        username.setVisible(false);
        password.setVisible(false);
        Number.setVisible(false);
        age.setVisible(false);
        imageview.setVisible(false);
        lbl_firstname.setVisible(false);
        lbl_lastname.setVisible(false);
        lbl_email.setVisible(false);
        lbl_username.setVisible(false);
        lbl_password.setVisible(false);
        lbl_number.setVisible(false);
        lbl_age.setVisible(false);
        lbl_image.setVisible(false);
        slide.setOnFinished((e -> {

        }));
    }

    @FXML
    private void btn2(MouseEvent event) {
         TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(layer2);

        slide.setToX(0);
        slide.play();

        layer1.setTranslateX(0);
        btnsignin.setVisible(false);
        b1.setVisible(false);
        b2.setVisible(false);

        s1.setVisible(false);
        s2.setVisible(false);
        s3.setVisible(false);
        signup.setVisible(false);
        l1.setVisible(true);
        l2.setVisible(true);
        l3.setVisible(true);
        signin.setVisible(true);
        a1.setVisible(true);
        a2.setVisible(true);
        btnsignup.setVisible(true);
        n1.setVisible(false);
        n2.setVisible(false);
        n3.setVisible(false);
        lblErrors.setVisible(false);
        firstname.setVisible(true);
        lastname.setVisible(true);
        email.setVisible(true);
        username.setVisible(true);
        password.setVisible(true);
        Number.setVisible(true);
        age.setVisible(true);
        imageview.setVisible(true);
        lbl_firstname.setVisible(true);
        lbl_lastname.setVisible(true);
        lbl_email.setVisible(true);
        lbl_username.setVisible(true);
        lbl_password.setVisible(true);
        lbl_number.setVisible(true);
        lbl_age.setVisible(true);
        lbl_image.setVisible(true);
        slide.setOnFinished((e -> {

        }));
    }
    
       private String logIn() {
           
           
           
           

           
           
           
           
        String status = "Success";
        String email = n1.getText();
        String password = n2.getText();
        if (email.isEmpty() || password.isEmpty()) {
            setLblError(Color.TOMATO, "Empty credentials");
            status = "Error";
        } else {
            
            
            
            
            
            
            
            
       
             String requete = "SELECT * FROM user WHERE username = '" + email +"'";
 

            try {
                PreparedStatement ps = con.prepareStatement(requete);

                ResultSet rs = ps.executeQuery();

                if (!rs.next()) {

                    setLblError(Color.TOMATO, "Enter Correct Email/Password");
                    status = "Error";
                } else {
                  
                       String pw = rs.getString("password");
                
                pw = pw.replace("$2y$", "$2a$");
                if (rs.getInt("enabled") ==1 )
                {
                    if (BCrypt.checkpw(password, pw)   )
                {
                    
                      Session.start(rs.getInt("id"));
                        setLblError(Color.GREEN, "Login Successful..Redirecting..");
                }
                  
                else
                {
                     setLblError(Color.TOMATO, "Enter Correct Email/Password");
                    status = "Error";
                }
                }
                else
                {
                               AlertDialog.showNotification("Error !","Votre Compte est desactive",AlertDialog.image_cross);
 
                }
                
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                status = "Exception";
            }

        }

        return status;
    }

    private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }
    
}
