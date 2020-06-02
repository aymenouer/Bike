/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.codename1.components.SpanLabel;
import com.codename1.datatransfer.DropTarget;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import java.io.IOException;
import models.Session;
import models.User;
import services.User_Service;

/**
 *
 * @author aymen
 */
public class SignUpForm extends Form {
Resources theme;
    public SignUpForm(Form previous) {
        super( BoxLayout.y());
        String url = "http://127.0.0.1/bike/mobile/cnx.php";
         theme = UIManager.initFirstTheme("/theme");
        TextField FIRSTNAME = new TextField("", "First Name", 20, TextArea.TEXT_CURSOR);
        TextField LASTNAME = new TextField("", "Last Name", 20, TextArea.ANY);
Label logi = new Label("ACCOUNT REGISTRATION ");
        logi.setUIID("logi");
        TextField USERNUMBER = new TextField("", "Number", 20, TextArea.PHONENUMBER);
        TextField USER_AGE = new TextField("", "age", 4, TextArea.NUMERIC);
        TextField USERNAME = new TextField("", "username", 20, TextArea.ANY);
        TextField EMAIL = new TextField("", "E-Mail", 20, TextArea.EMAILADDR);
        TextField Password = new TextField("", "Password", 20, TextArea.PASSWORD);
        
        FIRSTNAME.setUIID("txtn");
        LASTNAME.setUIID("txtn");
        USERNUMBER.setUIID("txtn");
        USER_AGE.setUIID("txtn");
        USERNAME.setUIID("txtn");
        EMAIL.setUIID("txtn");
        Password.setUIID("txtn");
        
Label labFIRSTNAME = new Label("FIRSTNAME");
        Label labLASTNAME = new Label("LASTNAME");
        Label labEMAIL = new Label("EMAIL");
        Label labUSERAGE = new Label("USERAGE");
        Label labUSERNAME = new Label("USERNAME");
        Label labPASSWORD = new Label("PASSWORD");
        Label labUSERNUMBER = new Label("USERNUMBER");
        labFIRSTNAME.setUIID("pass");
        labLASTNAME.setUIID("pass");
        labEMAIL.setUIID("pass");
        labUSERAGE.setUIID("pass");
        labPASSWORD.setUIID("pass");
        labUSERNUMBER.setUIID("pass");
        labUSERNAME.setUIID("pass");

        this.add(logi);
        this.add(labFIRSTNAME).add(FIRSTNAME);
        this.add(labLASTNAME).add(LASTNAME);
        this.add(labEMAIL).add(EMAIL);
        this.add(labUSERAGE).add(USER_AGE);
        this.add(labUSERNAME).add(USERNAME);
        this.add(labPASSWORD).add(Password);
        this.add(labUSERNUMBER).add(USERNUMBER);
        Label PHOTO = new Label("DRAG YOUR PHOTO HERE");
PHOTO.setUIID("pass");
        this.addComponent(PHOTO);
        if (DropTarget.isSupported()) {
            DropTarget dnd = DropTarget.create((evt) -> {

                String srcFile = (String) evt.getSource();

                System.out.println(FileSystemStorage.getInstance().exists(srcFile));

                System.out.println(evt.getSource());
                System.out.println("Src file is " + srcFile);

                System.out.println("Location: " + evt.getX() + ", " + evt.getY());
                if (srcFile != null) {
                    try {

                        Image img = null;
                        Button btn = new Button("Submit");
                        btn.setUIID("vtnvalid");
                        img = Image.createImage(FileSystemStorage.getInstance().openInputStream(srcFile)).scaled(300, 300);

                        String nomImage = srcFile.substring(19, srcFile.length());

                        this.add(img);

                        this.add(btn);

                        this.revalidate();

                        btn.addActionListener(l
                                -> {

                            // val firstname       
                            Validator val_firstname = new Validator();
                            val_firstname.addConstraint(FIRSTNAME, new LengthConstraint(8));
                            String text_saisir_des_caracteres = "^[0-9]+$";
                            val_firstname.addConstraint(FIRSTNAME, new RegexConstraint(text_saisir_des_caracteres, ""));
                            // val lastname   
                            Validator val_lastname = new Validator();
                            val_lastname.addConstraint(LASTNAME, new LengthConstraint(8));
                            val_lastname.addConstraint(LASTNAME, new RegexConstraint(text_saisir_des_caracteres, ""));

                            String text_mail="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                            
                             // val mail   
                            Validator val_mail = new Validator();
                            val_mail.addConstraint(EMAIL, new LengthConstraint(8));
                            val_mail.addConstraint(EMAIL, new RegexConstraint(text_mail, ""));
                              // val age   
                            Validator val_age = new Validator();
                            val_age.addConstraint(USER_AGE, new LengthConstraint(8));
                            val_age.addConstraint(USER_AGE, new RegexConstraint(text_saisir_des_caracteres, ""));
   // valnumber
                            Validator val_number = new Validator();
                            val_number.addConstraint(USERNUMBER, new LengthConstraint(8));
                            val_number.addConstraint(USERNUMBER, new RegexConstraint(text_saisir_des_caracteres, ""));

                            if (FIRSTNAME.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de firstname ", "OK", null);

                            } else if (val_firstname.isValid()) {
                                Dialog.show("Erreur FIRSTNAME !", "il faut saisir des caracteres  !", "OK", null);

                            } else if (LASTNAME.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de lastname ", "OK", null);

                            }else if (val_lastname.isValid()) {
                                Dialog.show("Erreur LASTNAME !", "il faut saisir des caracteres  !", "OK", null);

                            } 
                            
                            
                            else if (EMAIL.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de email ", "OK", null);

                            } else if (!val_mail.isValid()) {
                                Dialog.show("Erreur EMAIL !", "email incorrect", "OK", null);

                            } 
                            
                            else if (USER_AGE.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de age ", "OK", null);

                            } else if (!val_age.isValid()) {
                                Dialog.show("Erreur age !", "il faut saisir des numbers", "OK", null);

                            }
                            else if (Integer.valueOf(USER_AGE.getText())<=8) {
                                Dialog.show("Erreur age !", "age n'est pas acceptable", "OK", null);

                            }
                            else if (Integer.valueOf(USER_AGE.getText())>80) {
                                Dialog.show("Erreur age !", "age n'est pas acceptable", "OK", null);

                            }
                            
                            else if (USERNAME.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Username ", "OK", null);

                            } else if (Password.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de password ", "OK", null);

                            } else if (USERNUMBER.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Number ", "OK", null);

                            } 
                                  else if (!val_number.isValid()) {
                                 Dialog.show("Erreur number !", "il faut saisir des numbers", "OK", null);

                            } 
                            
                            else if (USERNUMBER.getText().length() != 8) {
                                Dialog.show("Erreur", "il faut 8 chiffres ", "OK", null);

                            } 
                      
                            else {

                                User u = new User(FIRSTNAME.getText(), LASTNAME.getText(), EMAIL.getText(), Integer.valueOf(USERNUMBER.getText()), USERNAME.getText(), Password.getText(), Integer.valueOf(USER_AGE.getText()), nomImage);
                                if (new User_Service().addUser(u) == true) {
                                    Dialog.show("Sign UP", "Sign Up aves success ", "OK", null);
                                    ConnectionRequest cnreq = new ConnectionRequest();
                                    cnreq.setPost(false);
                                    cnreq.addArgument("name", USERNAME.getText());
                                    cnreq.addArgument("password", Password.getText());
                                    cnreq.setUrl(url);
                                    cnreq.addResponseListener(ev -> {
                                        String chaine = new String(cnreq.getResponseData());

                                        int id = Integer.valueOf(chaine);
                                        Session.start(id);
                                        System.out.println(Session.get());

                                        new LoginForm().showBack();

                                    });
                                    NetworkManager.getInstance().addToQueueAndWait(cnreq);

                                } else {
                                    Dialog.show("Erreur", "Compte existe ", "OK", null);
                                }

                            }

                        }
                        );

                    } catch (IOException ex) {
                        Log.e(ex);
                    }

                }

            }, Display.GALLERY_IMAGE);
        }
this.getToolbar().setUIID("tb");
        this.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evt) -> {

            previous.showBack();
        });

    }

}
