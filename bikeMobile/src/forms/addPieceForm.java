/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.codename1.components.ImageViewer;
import com.codename1.components.OnOffSwitch;
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
import models.Categorie;
import models.Piece;
import models.Site;
import services.Categorie_Service;
import services.Piece_Service;
import services.Site_Service;


/**
 *
 * @author aymen
 */
public class addPieceForm extends Form{

    Resources theme = UIManager.initFirstTheme("/theme");
        
    public addPieceForm(Form previous) {
            super( BoxLayout.y());
            this.getToolbar().setUIID("tb");
  Label logi = new Label("LES PIECES");
        logi.setUIID("login");
        this.add(logi);
              this.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evt)
                -> {
            new Choix_categorie_Produit(this).show();
        });
            
            
               
                 for (Piece c : new Piece_Service().getAllPieces()) {

            this.add(addItem(c));

        } 
                  this.getToolbar().addCommandToOverflowMenu("Add Piece", null, ev -> {

            Form ajout = new Form( BoxLayout.y());
Label AJOUT = new Label("ADD PIECE");
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

                if (c.getType().equals("Piece")) {
                    cmb_lib_c.addItem(c.getLib_C());
                }

            }
            for (Site c : new Site_Service().getAllsites()) {

                cmb_lib_s.addItem(c.getLib_S());

            }

           Label type1 = new Label("LIBELLE");
            Label Description2 = new Label("DESCRIPTION");
            Label Libelle1 = new Label("PRIX");
            Label qua = new Label("QUANTITE");
            Label cat = new Label("CATEGORIE");
            Label site = new Label("SITE");
            type1.setUIID("pass");
            Description2.setUIID("pass");
            Libelle1.setUIID("pass");
            qua.setUIID("pass");
            cat.setUIID("pass");
            site.setUIID("pass");
             Label a = new Label("    ");
            ajout.add(type1).add(Libelle);
            ajout.add(Description2).add(Description);
            ajout.add(Libelle1).add(Prix);
            ajout.add(qua).add(Quantite);
            ajout.add(cat).add(cmb_lib_c);
            ajout.add(site).add(cmb_lib_s);
            ajout.add(a);
  Container cn_sw = new Container(BoxLayout.x());
                            OnOffSwitch swi = new OnOffSwitch();
                            
                             // like
                   String url_photo_like = "http://localhost/bike/web/uploads/images/like.png" ;
            ImageViewer img_like;
            Image imge_like;
            EncodedImage enc_like;
            enc_like = EncodedImage.createFromImage(theme.getImage("round.png"), false);
            imge_like = URLImage.createToStorage(enc_like, url_photo_like, url_photo_like);
            img_like = new ImageViewer(imge_like);
                //deslike
                  String url_photo_deslike = "http://localhost/bike/web/uploads/images/deslike.png" ;
            ImageViewer img_deslike;
            Image imge_deslike;
            EncodedImage enc_deslike;
            enc_deslike = EncodedImage.createFromImage(theme.getImage("round.png"), false);
            imge_deslike = URLImage.createToStorage(enc_deslike, url_photo_deslike, url_photo_deslike);
            img_deslike = new ImageViewer(imge_deslike);
               // 
                cn_sw.add(img_deslike).add(swi).add(img_like);
                ajout.add(cn_sw);
                         
            ajout.getToolbar().addCommandToLeftBar("back", theme.getImage("back-command.png"), evx -> {
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
                            String nomImage = srcFile.substring(19, srcFile.length());
                            submit.setUIID("vtnvalid");
                            ajout.add(img);

                            ajout.add(submit);
                         
                            ajout.revalidate();

                            submit.addActionListener(lll
                                    -> {
                                    String etat = "Mauvaise etat";
                                if (swi.isValue()) {
                                    etat = "Bonne etat";

                                }
                                Piece p = new Piece(Libelle.getText(), cmb_lib_c.getSelectedItem().toString(), cmb_lib_s.getSelectedItem().toString(), nomImage, Description.getText(), Float.valueOf(Prix.getText()), Integer.valueOf(Quantite.getText()), etat);
                               
                             
                                new Piece_Service().addPiece(p);
                                new addPieceForm(this).showBack();

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

    public Container addItem(Piece c) {

        Container cn1 = new Container(new BorderLayout());
        Container cn2 = new Container(BoxLayout.y());
        Label lab = new Label(c.getLibelle());
        Button btn = new Button(c.getLib_C());

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
               Label det = new Label("DETAILS");
                        det.setUIID("login");
                        f2.add(det);
                         Label type5 = new Label("LIBELLE");
                        Label libelle = new Label(c.getLibelle());

                        Label Description2 = new Label("DESCRIPTION");
                        Label capacite = new Label(c.getDescription());
                               
           Label Libelle1 = new Label("LIBELLE CATEGORIE");
                        Label categorie = new Label(c.getLib_C());
                        Label Libelle5 = new Label("LIBELLE SITE");
                        Label site = new Label(c.getLib_C());
                        Label QUANTITE = new Label("QUANTITE");
                        Label quantite = new Label(String.valueOf(c.getQuantite()));
                        Label PRIX = new Label("PRIX");
                        Label Prix = new Label(String.valueOf(c.getPrix()));
                        
                        libelle.setUIID("type2");
                        Description2.setUIID("type1");
                        capacite.setUIID("type2");
                        Libelle1.setUIID("type1");
                        categorie.setUIID("type2");
                        Libelle5.setUIID("type1");
                        site.setUIID("type2");
                        QUANTITE.setUIID("type1");
                        quantite.setUIID("type2");
                        PRIX.setUIID("type1");
                        Prix.setUIID("type2");
                        Label ETAT = new Label("ETAT");
               Label etat = new Label(c.getEtat());
               type5.setUIID("type1");
                         ETAT.setUIID("type1");
                         etat.setUIID("type2");
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
            f2.add(type5).add(libelle).add(Libelle1).add(categorie).add(Libelle5).add(site).add(QUANTITE).add(quantite).add(Description2).add(capacite).add(PRIX).add(Prix).add(imgv2).add(x);

            Supprimer.addActionListener(ev
                    -> {
                new Piece_Service().DeletePiece(c.getId_Pi());
                new addPieceForm(this).showBack();
            }
            );
            Modifier.addActionListener(eva
                    -> {
                Form fmodifier = new Form("Edit Piece", BoxLayout.y());
                Label modif = new Label("EDIT PIECE");
 modif.setUIID("login");
                            
                            fmodifier.add(modif);

                            fmodifier.getToolbar().setUIID("tb");
                            fmodifier.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evt) -> {
                                f2.showBack();
                            });
                AutoCompleteTextField Libelle = new AutoCompleteTextField(c.getLibelle());
                Libelle.setMinimumElementsShownInPopup(1);
 Libelle.setUIID("txtn");
                AutoCompleteTextField Description_mod = new AutoCompleteTextField(c.getDescription());
                Description_mod.setMinimumElementsShownInPopup(1);

                Description_mod.setUIID("txtn");
                AutoCompleteTextField Prix_mod = new AutoCompleteTextField(String.valueOf(c.getPrix()));
                Description_mod.setMinimumElementsShownInPopup(1);

                Prix_mod.setUIID("txtn");
                AutoCompleteTextField Quantite_mod = new AutoCompleteTextField(String.valueOf(c.getQuantite()));
                Quantite_mod.setMinimumElementsShownInPopup(1);
                Quantite_mod.setUIID("txtn");
                ComboBox cmb_lib_c = new ComboBox();
                ComboBox cmb_lib_s = new ComboBox();
 cmb_lib_c.setUIID("txtn");
                            cmb_lib_s.setUIID("txtn");
                for (Categorie cat : new Categorie_Service().getAllCategories()) {

                    if (cat.getType().equals("Piece")) {
                        cmb_lib_c.addItem(cat.getLib_C());
                    }

                }
                for (Site siteq : new Site_Service().getAllsites()) {

                    cmb_lib_s.addItem(siteq.getLib_S());

                }
                Container cn_sw = new Container(BoxLayout.x());
                OnOffSwitch swi = new OnOffSwitch();
                // like
                   String url_photo_like = "http://localhost/bike/web/uploads/images/like.png" ;
            ImageViewer img_like;
            Image imge_like;
            EncodedImage enc_like;
            enc_like = EncodedImage.createFromImage(theme.getImage("round.png"), false);
            imge_like = URLImage.createToStorage(enc_like, url_photo_like, url_photo_like);
            img_like = new ImageViewer(imge_like);
                //deslike
                  String url_photo_deslike = "http://localhost/bike/web/uploads/images/deslike.png" ;
            ImageViewer img_deslike;
            Image imge_deslike;
            EncodedImage enc_deslike;
            enc_deslike = EncodedImage.createFromImage(theme.getImage("round.png"), false);
            imge_deslike = URLImage.createToStorage(enc_deslike, url_photo_deslike, url_photo_deslike);
            img_deslike = new ImageViewer(imge_deslike);
               // 
                cn_sw.add(img_deslike).add(swi).add(img_like);

                fmodifier.add("Libelle : ").add(Libelle);
                fmodifier.add("Description : ").add(Description_mod);
                fmodifier.add("Prix : ").add(Prix_mod);
                fmodifier.add("Quantite : ").add(Quantite_mod);
                fmodifier.add("Categorie : ").add(cmb_lib_c);
                fmodifier.add("Site : ").add(cmb_lib_s);
                fmodifier.add(cn_sw);
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
       
                fmodifier.addComponent(new SpanLabel("Drag your photo here"));
                if (DropTarget.isSupported()) {

                    DropTarget dnd = DropTarget.create((evt) -> {

                        String srcFile = (String) evt.getSource();
                        System.out.println("Src file is " + srcFile);

                        System.out.println("Location: " + evt.getX() + ", " + evt.getY());
                        if (srcFile != null) {
                            try {

                                Image img = Image.createImage(FileSystemStorage.getInstance().openInputStream(srcFile)).scaled(300, 300);
                                Button submit = new Button("Submit");
                                String nomImage = srcFile.substring(19, srcFile.length());

                                fmodifier.add(img);

                                fmodifier.add(submit);

                                fmodifier.revalidate();
                                fmodifier.getToolbar().addCommandToLeftBar("Return", null, (evtx) -> {
                                    this.show();
                                });

                                submit.addActionListener(lll
                                        -> {
                                    String etat_mod = "Mauvaise etat";
                                    if (swi.isValue()) {
                                        etat_mod = "Bonne etat";

                                    }
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
                                   Piece ab = new Piece(Libelle.getText(), cmb_lib_c.getSelectedItem().toString(), cmb_lib_s.getSelectedItem().toString(), nomImage, Description_mod.getText(), Float.valueOf(Prix_mod.getText()), Integer.valueOf(Quantite_mod.getText()), etat_mod);

                                    new Piece_Service().ModifierPiece(ab, c.getId_Pi());
                                    new addPieceForm(this).showBack();  
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

    
   
    

