/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VeloForm;


import com.codename1.components.SpanLabel;
import com.codename1.datatransfer.DropTarget;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import forms.ListUsersForm;
import java.io.IOException;
import models.Categorie;
import models.Session;
import models.Site;
import models.User;
import models.Velo;
import services.Categorie_Service;
import services.Site_Service;
import services.User_Service;
import services.Velo_Service;

/**
 *
 * @author asus
 */
public class add_velosForm extends Form {
 Resources theme = UIManager.initFirstTheme("/theme");

    public add_velosForm(Form previous) {
        super( BoxLayout.y());
        Label logi = new Label("ADD VELOS");
        logi.setUIID("login");
        this.add(logi);
        this.getToolbar().setUIID("tb");
        this.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evt) -> {
            new listVelo(this).show();
        });
        TextField Libelle = new TextField("", "Libelle", 20, TextArea.ANY);
        Libelle.setUIID("txtn");
        TextField Description = new TextField("", "Description", 20, TextArea.ANY);
        Description.setUIID("txtn");
        TextField Couleur = new TextField("", "Couleur", 20, TextArea.ANY);
        Couleur.setUIID("txtn");
        TextField etat = new TextField("", "etat", 20, TextArea.ANY);
        etat.setUIID("txtn");
        TextField type = new TextField("", "type", 20, TextArea.ANY);
        type.setUIID("txtn");
        TextField Age = new TextField("", "Age", 20, TextArea.NUMERIC);
        Age.setUIID("txtn");
        TextField Prix = new TextField("", "Prix", 20, TextArea.NUMERIC);
        Prix.setUIID("txtn");
        ComboBox cmb_lib_c = new ComboBox();
        ComboBox cmb_lib_s = new ComboBox();
        cmb_lib_c.setUIID("txtn");
        cmb_lib_s.setUIID("txtn");
        for (Categorie c : new Categorie_Service().getAllCategories()) {

            if (c.getType().equals("Velo")) {
                cmb_lib_c.addItem(c.getLib_C());
            }

        }
        for (Site c : new Site_Service().getAllsites()) {

            cmb_lib_s.addItem(c.getLib_S());

        }
Label type1 = new Label("LIBELLE");
 Label type9 = new Label("LIBELLE CATEGORIE");
  Label type2 = new Label("LIBELLE SITE");
    Label type3 = new Label("AGE");
      Label type4 = new Label("COULEUR");
       Label type5 = new Label("ETAT");
         Label type6 = new Label("TYPE");
         Label type7 = new Label("DESCRIPTION");
          Label type8 = new Label("PRIX");
          type1.setUIID("pass");
              type2.setUIID("pass");
              type3.setUIID("pass");
              type4.setUIID("pass");
              type5.setUIID("pass");
              type6.setUIID("pass");
               type7.setUIID("pass"); type8.setUIID("pass"); type9.setUIID("pass");
        this.add(type1).add(Libelle);
        this.add(type7).add(Description);
        this.add(type8).add(Prix);
        this.add(type3).add(Age);
        this.add(type4).add(Couleur);
        this.add(type5).addAll(etat);
        this.add(type6).add(type);
        this.add(type9).add(cmb_lib_c);
        this.add(type2).add(cmb_lib_s);

        Label PHOTO = new Label("DRAG YOUR PHOTO HERE");
            PHOTO.setUIID("pass");
            this.addComponent(PHOTO);
        if (DropTarget.isSupported()) {
            DropTarget dnd = DropTarget.create((evt) -> {

                String srcFile = (String) evt.getSource();
                System.out.println("Src file is " + srcFile);

                System.out.println("Location: " + evt.getX() + ", " + evt.getY());
                if (srcFile != null) {
                    try {
                        Image img = Image.createImage(FileSystemStorage.getInstance().openInputStream(srcFile)).scaled(300, 300);

                        String nomImage = srcFile.substring(19, srcFile.length());

                        this.add(img);
                        Button btn = new Button("Submit");
                        btn.setUIID("vtnvalid");
                        this.add(btn);

                        this.revalidate();

                        btn.addActionListener(l
                                -> {

                            if (Description.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Description ", "OK", null);

                            } else if (Libelle.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Libelle ", "OK", null);

                            } else if (Age.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de age ", "OK", null);

                            } else {
                                Velo ab = new Velo(type.getText(), Integer.valueOf(Age.getText()), Couleur.getText(), etat.getText(), Libelle.getText(), cmb_lib_c.getSelectedItem().toString(), cmb_lib_s.getSelectedItem().toString(), nomImage, Description.getText(), Float.valueOf(Prix.getText()));

                                if (new Velo_Service().addVelo(ab) == true) {
                                    Dialog.show("Ajout Velo", "ajout aves success ", "OK", null);

                                } else {
                                    Dialog.show("Erreur", "Erreur ajout ", "OK", null);
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
        this.show();
    }

}
