/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.datatransfer.DropTarget;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import java.io.IOException;
import models.Accessoire;
import models.Categorie;
import models.Site;
import services.Accessoire_Service;
import services.Categorie_Service;
import services.Site_Service;

/**
 *
 * @author aymen
 */
public class addAccessoireForm extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");

    public addAccessoireForm(Form previous) {
        super(BoxLayout.y());
        this.getToolbar().setUIID("tb");
        Label logi = new Label("LES ACCESSOIRES");
        logi.setUIID("login");
        this.add(logi);
        for (Accessoire c : new Accessoire_Service().getAllAccessoires()) {

            this.add(addItem(c));

        }
        this.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evt)
                -> {
            new Choix_categorie_Produit(this).show();
        });

        this.getToolbar().addCommandToOverflowMenu("Add Accessoire", null, ev -> {

            Form ajout = new Form(BoxLayout.y());
            Label AJOUT = new Label("ADD ACCESSOIRES");
            AJOUT.setUIID("login");
            ajout.add(AJOUT);
            TextField Libelle = new TextField("", "Libelle", 20, TextArea.ANY);
            Libelle.setUIID("txtn");
            TextField Description = new TextField("", "Description", 20, TextArea.ANY);
            Description.setUIID("txtn");
            TextField Quantite = new TextField("", "Quantite", 20, TextArea.NUMERIC);
            Quantite.setUIID("txtn");
            TextField Prix = new TextField("", "Prix", 20, TextArea.NUMERIC);
            Prix.setUIID("txtn");
            ComboBox cmb_lib_c = new ComboBox();
            cmb_lib_c.setUIID("txtn");
            ComboBox cmb_lib_s = new ComboBox();
            cmb_lib_s.setUIID("txtn");
            for (Categorie c : new Categorie_Service().getAllCategories()) {

                if (c.getType().equals("Accessoire")) {
                    cmb_lib_c.addItem(c.getLib_C());
                }

            }
            for (Site c : new Site_Service().getAllsites()) {

                cmb_lib_s.addItem(c.getLib_S());

            }
            Label LIBELLE = new Label("LIBELLE");
            Label DESCRIPTION = new Label("DESCRIPTION");
            Label PRIX = new Label("PRIX");
            Label QUANTITE = new Label("QUANTITE");
            Label CATEGORIE = new Label("CATEGORIE");
            Label SITE = new Label("SITE");

            LIBELLE.setUIID("pass");
            DESCRIPTION.setUIID("pass");
            PRIX.setUIID("pass");
            QUANTITE.setUIID("pass");
            CATEGORIE.setUIID("pass");
            SITE.setUIID("pass");

            ajout.add(LIBELLE).add(Libelle);
            ajout.add(DESCRIPTION).add(Description);
            ajout.add(PRIX).add(Prix);
            ajout.add(QUANTITE).add(Quantite);
            ajout.add(CATEGORIE).add(cmb_lib_c);
            ajout.add(SITE).add(cmb_lib_s);
              Validator val_Libelle = new Validator();

            val_Libelle.addConstraint(Libelle, new LengthConstraint(8));

            String text_saisir_des_caracteres = "^[0-9]+$";

            val_Libelle.addConstraint(Libelle, new RegexConstraint(text_saisir_des_caracteres, ""));

            Validator val_Description = new Validator();

            val_Description.addConstraint(Description, new LengthConstraint(8));

            val_Description.addConstraint(Description, new RegexConstraint(text_saisir_des_caracteres, ""));

            Validator val_Prix = new Validator();

            val_Prix.addConstraint(Prix, new LengthConstraint(8));

            val_Prix.addConstraint(Prix, new RegexConstraint(text_saisir_des_caracteres, ""));

            Validator val_Quantite = new Validator();

            val_Quantite.addConstraint(Quantite, new LengthConstraint(8));

            val_Quantite.addConstraint(Quantite, new RegexConstraint(text_saisir_des_caracteres, ""));
            ajout.getToolbar().setUIID("tb");
            ajout.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
            Label PHOTO = new Label("DRAG YOUR PHOTO HERE");
            PHOTO.setUIID("pass");
            ajout.addComponent(PHOTO);

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

                            ajout.add(img);
                            Label a = new Label("    ");
                            ajout.addAll(a, submit);

                            ajout.revalidate();

                        
                            submit.addActionListener(lll
                                    -> {

                                           if (Libelle.getText().equals("")) {
                                    Dialog.show("Erreur", "Champ vide de Libelle ", "OK", null);

                                } else if (val_Libelle.isValid()) {
                                    Dialog.show("Erreur Libelle !", "il faut saisir des caracteres  !", "OK", null);
                                } else if (Description.getText().equals("")) {
                                    Dialog.show("Erreur", "Champ vide de Description ", "OK", null);

                                } else if (val_Description.isValid()) {
                                    Dialog.show("Erreur Description !", "il faut saisir des caracteres  !", "OK", null);
                                } else if (Prix.getText().equals("")) {
                                    Dialog.show("Erreur", "Champ vide de Prix ", "OK", null);

                                } else if (!val_Prix.isValid()) {
                                    Dialog.show("Erreur Prix !", "il faut saisir des numbers", "OK", null);

                                } else if (Integer.valueOf(Prix.getText()) <= 0) {
                                    Dialog.show("Erreur Prix !", "Prix n'est pas acceptable", "OK", null);

                                } else if (Quantite.getText().equals("")) {
                                    Dialog.show("Erreur", "Champ vide de Quantite ", "OK", null);

                                } else if (!val_Quantite.isValid()) {
                                    Dialog.show("Erreur Prix !", "il faut saisir des numbers", "OK", null);

                                } else if (Integer.valueOf(Quantite.getText()) <= 0) {
                                    Dialog.show("Erreur Quantite !", "Quantite n'est pas acceptable", "OK", null);

                                }
                                           else
                                {
                                     Accessoire ab = new Accessoire(Libelle.getText(), cmb_lib_c.getSelectedItem().toString(), cmb_lib_s.getSelectedItem().toString(), nomImage, Description.getText(), Float.valueOf(Prix.getText()), Integer.valueOf(Quantite.getText()));
                                new Accessoire_Service().addAccessoire(ab);
                                new addAccessoireForm(this).show();   
                                }
                                
                                
                            

                            }
                            );

                        } catch (IOException ex) {
                            Log.e(ex);
                        }

                    }

                }, Display.GALLERY_IMAGE);
            }

            ajout.show();

        });

    }

    public Container addItem(Accessoire c) {

        Container cn1 = new Container(new BorderLayout());
        Container cn2 = new Container(BoxLayout.y());
        Label lab = new Label(c.getLibelle());
        Button btn = new Button(c.getLib_C());
        lab.setUIID("libC");
        btn.setUIID("btn");

        String url = "http://localhost/bike/web/uploads/images/" + c.getImage();
        ImageViewer imgv;
        Image imge;
        EncodedImage enc;

        enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
        imge = URLImage.createToStorage(enc, url, url);
        imgv = new ImageViewer(imge);

        cn2.add(lab).add(btn);
        cn1.add(BorderLayout.WEST, cn2);
        cn1.add(BorderLayout.EAST, imgv);

        btn.addActionListener(e -> {

            Form f2 = new Form(BoxLayout.y());
            Label AJOUT = new Label("DETAILS");
            AJOUT.setUIID("login");
            f2.add(AJOUT);
            Label type1 = new Label("LIBELLE");
            Label libelle = new Label(c.getLibelle());
            Label type2 = new Label("LIBELLE CATEGORIE");
            Label categorie = new Label(c.getLib_C());
            Label type3 = new Label("LIBELLE SITE");
            Label site = new Label(c.getLib_S());
            Label type4 = new Label("QUANTITE");
            Label quantite = new Label(String.valueOf(c.getQuantite()));
            Label type5 = new Label("DESCRIPTION");
            Label Description = new Label(c.getDescription());
            Label type6 = new Label("PRIX");
            Label Prix = new Label(String.valueOf(c.getPrix()));
            type1.setUIID("type1");
            type2.setUIID("type1");
            type3.setUIID("type1");
            type4.setUIID("type1");
            type5.setUIID("type1");
            type6.setUIID("type1");
            libelle.setUIID("type2");
            site.setUIID("type2");
            categorie.setUIID("type2");
            quantite.setUIID("type2");
            Description.setUIID("type2");
            Prix.setUIID("type2");

            Button Modifier = new Button(theme.getImage("edit.png"));
            Button Supprimer = new Button(theme.getImage("remove.png"));
            Modifier.setUIID("mod");
            Supprimer.setUIID("rem");
             Container x = new Container(BoxLayout.x());
                        x.addAll(Modifier, Supprimer);
            String url2 = "http://localhost/bike/web/uploads/images/" + c.getImage();
            ImageViewer imgv2;
            Image imge2;
            EncodedImage enc2;
            enc2 = EncodedImage.createFromImage(theme.getImage("round.png"), false);
            imge2 = URLImage.createToStorage(enc, url, url);
            imgv2 = new ImageViewer(imge2);
            f2.getToolbar().setUIID("tb");
            f2.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evt) -> {
                this.showBack();
            });
            f2.add(type1).add(libelle).add(type2).add(categorie).add(type3).add(site).add(type4).add(quantite).add(type5).add(Description).add(type6).add(Prix).add(imgv2).add(x);

            Supprimer.addActionListener(ev
                    -> {
                new Accessoire_Service().DeleteAccessoire(c.getId_A());
                new addAccessoireForm(this).show();
            }
            );
            Modifier.addActionListener(eva
                    -> {
                Form fmodifier = new Form(BoxLayout.y());
                Label modif = new Label("EDIT Accessoire");
                modif.setUIID("login");
                fmodifier.add(modif);
 fmodifier.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evt) -> {
                this.showBack();
            });
                AutoCompleteTextField Libelle = new AutoCompleteTextField(c.getLibelle());
                Libelle.setMinimumElementsShownInPopup(1);
                Libelle.setUIID("txtn");

                AutoCompleteTextField Description_mod = new AutoCompleteTextField(c.getDescription());
                Description_mod.setMinimumElementsShownInPopup(1);
                Description_mod.setUIID("txtn");

                AutoCompleteTextField Prix_mod = new AutoCompleteTextField(String.valueOf(c.getPrix()));
                Prix_mod.setMinimumElementsShownInPopup(1);
                Prix_mod.setUIID("txtn");

                AutoCompleteTextField Quantite_mod = new AutoCompleteTextField(String.valueOf(c.getQuantite()));
                Quantite_mod.setMinimumElementsShownInPopup(1);
                Quantite_mod.setUIID("txtn");

                ComboBox cmb_lib_c = new ComboBox();
                ComboBox cmb_lib_s = new ComboBox();
                cmb_lib_c.setUIID("txtn");
                cmb_lib_s.setUIID("txtn");
                for (Categorie cat : new Categorie_Service().getAllCategories()) {

                    if (cat.getType().equals("Accessoire")) {
                        cmb_lib_c.addItem(cat.getLib_C());
                    }

                }
                for (Site siteq : new Site_Service().getAllsites()) {

                    cmb_lib_s.addItem(siteq.getLib_S());

                }
                Label lib = new Label("LIBELLE");
                lib.setUIID("pass");
                fmodifier.add(lib).add(Libelle);

                Label des = new Label("DESCRIPTION");
                des.setUIID("pass");
                fmodifier.add(des).add(Description_mod);

                Label ty = new Label("PRIX");
                ty.setUIID("pass");
                fmodifier.add(ty).add(Prix_mod);

                Label QU = new Label("QUANTITE");
                QU.setUIID("pass");
                fmodifier.add(QU).add(Quantite_mod);

                Label LC = new Label("LIBELLE CATEGORIE");
                LC.setUIID("pass");
                fmodifier.add(LC).add(cmb_lib_c);

                Label LS = new Label("LIBELLE SITE");
                LS.setUIID("pass");
                fmodifier.add(LS).add(cmb_lib_s);
  Validator val_Libelle = new Validator();

            val_Libelle.addConstraint(Libelle, new LengthConstraint(8));

            String text_saisir_des_caracteres = "^[0-9]+$";

            val_Libelle.addConstraint(Libelle, new RegexConstraint(text_saisir_des_caracteres, ""));

            Validator val_Description = new Validator();

            val_Description.addConstraint(Description_mod, new LengthConstraint(8));

            val_Description.addConstraint(Description_mod, new RegexConstraint(text_saisir_des_caracteres, ""));

            Validator val_Prix = new Validator();

            val_Prix.addConstraint(Prix_mod, new LengthConstraint(8));

            val_Prix.addConstraint(Prix_mod, new RegexConstraint(text_saisir_des_caracteres, ""));

            Validator val_Quantite = new Validator();

            val_Quantite.addConstraint(Quantite_mod, new LengthConstraint(8));

            val_Quantite.addConstraint(Quantite_mod, new RegexConstraint(text_saisir_des_caracteres, ""));
                Label PHOTO = new Label("DRAG YOUR PHOTO HERE");
                PHOTO.setUIID("pass");
                fmodifier.addComponent(PHOTO);

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

                                fmodifier.add(img);

                                fmodifier.add(submit);

                                fmodifier.revalidate();
                                fmodifier.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evtx) -> {
                                    this.showBack();
                                });

                                                              submit.addActionListener(lll
                                        -> {
                                       if (Libelle.getText().equals("")) {
                                    Dialog.show("Erreur", "Champ vide de Libelle ", "OK", null);

                                } else if (val_Libelle.isValid()) {
                                    Dialog.show("Erreur Libelle !", "il faut saisir des caracteres  !", "OK", null);
                                } else if (Description_mod.getText().equals("")) {
                                    Dialog.show("Erreur", "Champ vide de Description ", "OK", null);

                                } else if (val_Description.isValid()) {
                                    Dialog.show("Erreur Description !", "il faut saisir des caracteres  !", "OK", null);
                                } else if (Prix_mod.getText().equals("")) {
                                    Dialog.show("Erreur", "Champ vide de Prix ", "OK", null);

                                } else if (!val_Prix.isValid()) {
                                    Dialog.show("Erreur Prix !", "il faut saisir des numbers", "OK", null);

                                } else if (Integer.valueOf(Prix_mod.getText()) <= 0) {
                                    Dialog.show("Erreur Prix !", "Prix n'est pas acceptable", "OK", null);

                                } else if (Quantite_mod.getText().equals("")) {
                                    Dialog.show("Erreur", "Champ vide de Quantite ", "OK", null);

                                } else if (!val_Quantite.isValid()) {
                                    Dialog.show("Erreur Prix !", "il faut saisir des numbers", "OK", null);

                                } else if (Integer.valueOf(Quantite_mod.getText()) <= 0) {
                                    Dialog.show("Erreur Quantite !", "Quantite n'est pas acceptable", "OK", null);

                                }
                                       else
                                {
                                    Accessoire ab = new Accessoire(Libelle.getText(), cmb_lib_c.getSelectedItem().toString(), cmb_lib_s.getSelectedItem().toString(), nomImage, Description_mod.getText(), Float.valueOf(Prix_mod.getText()), Integer.valueOf(Quantite_mod.getText()));

                                    new Accessoire_Service().ModifierAccessoire(ab, c.getId_A());
                                    new addAccessoireForm(this).showBack();  
                                }

                                  

                                }
                                );

                            } catch (IOException ex) {
                                Log.e(ex);
                            }

                        }

                    }, Display.GALLERY_IMAGE);
                }

                fmodifier.show();
            }
            );

            f2.show();

        });

        cn1.setLeadComponent(btn);
        return cn1;

    }
}
