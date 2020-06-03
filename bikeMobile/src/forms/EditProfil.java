/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import ca.weblite.codename1.components.ckeditor.CKeditor;
import com.codename1.components.SpanLabel;
import com.codename1.datatransfer.DropTarget;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.AutoCompleteTextField;
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
import java.io.IOException;
import models.Session;
import models.User;
import services.User_Service;

/**
 *
 * @author aymen
 */
public class EditProfil extends Form {
  Resources theme = UIManager.initFirstTheme("/theme");
    public EditProfil(Form previous) {
        super( BoxLayout.y());
        Label logi = new Label("EDIT Profil");
        logi.setUIID("login");
        this.add(logi);

        String url = "http://127.0.0.1/bike/mobile/cnx.php";
        AutoCompleteTextField FIRSTNAME = new AutoCompleteTextField(Session.get().getFname());
        FIRSTNAME.setMinimumElementsShownInPopup(1);
         FIRSTNAME.setUIID("txtn");

        AutoCompleteTextField LASTNAME = new AutoCompleteTextField(Session.get().getLname());
        LASTNAME.setMinimumElementsShownInPopup(1);
         LASTNAME.setUIID("txtn");

        AutoCompleteTextField USERNUMBER = new AutoCompleteTextField(String.valueOf(Session.get().getNumber()));
        USERNUMBER.setMinimumElementsShownInPopup(1);
         USERNUMBER.setUIID("txtn");

        AutoCompleteTextField USER_AGE = new AutoCompleteTextField(String.valueOf(Session.get().getAge()));
        USER_AGE.setMinimumElementsShownInPopup(1);
         USER_AGE.setUIID("txtn");

        AutoCompleteTextField USERNAME = new AutoCompleteTextField(Session.get().getUsername());
        USERNAME.setMinimumElementsShownInPopup(1);
         USERNAME.setUIID("txtn");

        TextField Password = new TextField("", "Password", 20, TextArea.PASSWORD);
        Password.setUIID("txtn");

        AutoCompleteTextField EMAIL = new AutoCompleteTextField(Session.get().getMail());
        EMAIL.setMinimumElementsShownInPopup(1);
        EMAIL.setUIID("txtn");

        Button btn = new Button("Submit");
        btn.setUIID("vtnvalid");
        
        Label des = new Label("FIRSTNAME");
                des.setUIID("pass");
                this.add(des).add(FIRSTNAME);

                Label ty = new Label("LASTNAME");
                ty.setUIID("pass");
                this.add(ty).add(LASTNAME);

                Label QU = new Label("USERNUMBER");
                QU.setUIID("pass");
                this.add(QU).add(USERNUMBER);

                Label LC = new Label("USER_AGE");
                LC.setUIID("pass");
                this.add(LC).add(USER_AGE);

                Label LS = new Label("USERNAME");
                LS.setUIID("pass");
                this.add(LS).add(USERNAME);
                
                Label LS1 = new Label("EMAIL");
                LS1.setUIID("pass");
                this.add(LS1).add(EMAIL);
                
                
                Label LS2 = new Label("PASSWORD");
                LS2.setUIID("pass");
                this.add(LS2).add(Password);
       

        Label PHOTO = new Label("DRAG YOUR PHOTO HERE");
                PHOTO.setUIID("pass");
                this.addComponent(PHOTO);
        
        this.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evt) -> {
            previous.showBack();
        });
        
        
        
        
        
        
                        if (DropTarget.isSupported()) {

                    DropTarget dnd = DropTarget.create((evt) -> {

                        String srcFile = (String) evt.getSource();
                        System.out.println("Src file is " + srcFile);

                        System.out.println("Location: " + evt.getX() + ", " + evt.getY());
                        if (srcFile != null) {
                            try {

                                Image img = Image.createImage(FileSystemStorage.getInstance().openInputStream(srcFile)).scaled(300, 300);
                                Button submit = new Button("Submit");
                                submit.setUIID("vtnvalid");
                                String nomImage = srcFile.substring(19, srcFile.length());

                                this.add(img);

                                this.add(submit);

                                this.revalidate();
                                this.getToolbar().setUIID("tb");
                                this.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evtx) -> {
                                    this.showBack();
                                });

                                submit.addActionListener(lll
                                        -> {

                                        User u = new User(FIRSTNAME.getText(), LASTNAME.getText(), EMAIL.getText(), Integer.valueOf(USERNUMBER.getText()), USERNAME.getText(), Password.getText(), Integer.valueOf(USER_AGE.getText()), nomImage);
                            if (new User_Service().ModifierUser(u, Session.get().getId()) == true) {
                                Dialog.show("Modifier", "Modification  ", "OK", null);
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

                                    previous.showBack();

                                });
                                NetworkManager.getInstance().addToQueueAndWait(cnreq);

                            } else {
                                Dialog.show("Erreur", "Compte existe ", "OK", null);
                            }

                                }
                                );

                            } catch (IOException ex) {
                                Log.e(ex);
                            }

                        }

                    }, Display.GALLERY_IMAGE);
                }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
       
       

    }

}
