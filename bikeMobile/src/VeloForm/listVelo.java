/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VeloForm;

import forms.addSiteForm;
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
import forms.AccueilAdminForm;
import forms.Choix_categorie_Produit;
import forms.ListUsersForm;

import forms.StatVelo;
import java.io.IOException;
import models.Categorie;
import models.Session;
import models.Site;
import models.User;
import models.Velo;
import services.Abonnment_Service;
import services.Categorie_Service;
import services.Site_Service;
import services.User_Service;
import services.Velo_Service;

/**
 *
 * @author asus
 */
public class listVelo extends Form {

    Resources theme;

    public listVelo(Form previous) {
        super(BoxLayout.y());
        Label logi = new Label("LES VELOS");
        logi.setUIID("login");
        this.add(logi);
        theme = UIManager.initFirstTheme("/theme");
        this.getToolbar().setUIID("tbbb");
        this.getToolbar().addCommandToOverflowMenu("Add Velo", null, (evt) -> {
            new add_velosForm(this).show();
        });
        this.getToolbar().addCommandToOverflowMenu("Statistiques",null, (evt) -> {
            new StatVelo().createPieChartForm("Velo", new Velo_Service().getStat());
        });
        

        this.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evt) -> {
            new AccueilAdminForm(this).show();
        });
        for (Velo c : new Velo_Service().getAllvelos()) {

            this.add(addItem(c));

        }

    }

    public Container addItem(Velo c) {

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
        System.err.println(c.getImage());
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
            Label type9 = new Label("LIBELLE CATEGORIE");
            Label categorie = new Label(c.getLib_C());
            Label type2 = new Label("LIBELLE SITE");
            Label site = new Label(c.getLib_S());
            Label type3 = new Label("AGE");
            Label age = new Label(String.valueOf(c.getAge()));
            Label type4 = new Label("COULEUR");
            Label couleur = new Label(String.valueOf(c.getCouleur()));
            Label type5 = new Label("ETAT");
            Label etat = new Label(String.valueOf(c.getEtat()));
            Label type6 = new Label("TYPE");
            Label type = new Label(String.valueOf(c.getType()));
            Label type7 = new Label("DESCRIPTION");
            Label Description = new Label(c.getDescription());
            Label type8 = new Label("PRIX");
            Label Prix = new Label(String.valueOf(c.getPrix()));
            Button btn_modifier = new Button(theme.getImage("edit.png"));
            Button btn_supp = new Button(theme.getImage("remove.png"));
            btn_modifier.setUIID("mod");
            btn_supp.setUIID("rem");
 Container x = new Container(BoxLayout.x());
                        x.addAll(btn_modifier, btn_supp);
            type1.setUIID("type1");
            type2.setUIID("type1");
            type3.setUIID("type1");
            type4.setUIID("type1");
            type5.setUIID("type1");
            type6.setUIID("type1");
            type7.setUIID("type1");
            type8.setUIID("type1");
            type9.setUIID("type1");
            libelle.setUIID("type2");
            site.setUIID("type2");
            categorie.setUIID("type2");

            Description.setUIID("type2");
            Prix.setUIID("type2");
            age.setUIID("type2");
            couleur.setUIID("type2");
            etat.setUIID("type2");
            type.setUIID("type2");

            f2.getToolbar().setUIID("tb");
            f2.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evt) -> {
                this.showBack();
            });
            f2.add(type1).add(libelle).add(type9).add(categorie).add(type2).add(site).add(type3).add(age).add(type4).add(couleur).add(type5).add(etat).add(type6).add(type).add(type7).add(Description).add(type8).add(Prix).add(x);

            f2.show();
            btn_supp.addActionListener(ev
                    -> {

                if (new Velo_Service().removeVelo(c.getId_v()) == true) {
                    Dialog.show("Suppression Velo", "suppression aves success ", "OK", null);
                    new Choix_categorie_Produit(this).show();

                } else {
                    Dialog.show("Erreur", "Erreur suppression ", "OK", null);
                }

            }
            );

            btn_modifier.addActionListener(ev
                    -> {

                Form fmodifier = new Form(BoxLayout.y());

                Label modif = new Label("EDIT VELO");
                modif.setUIID("login");
                fmodifier.add(modif);
                fmodifier.getToolbar().setUIID("tb");
                fmodifier.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evtx) -> {
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

                AutoCompleteTextField Age_mod = new AutoCompleteTextField(String.valueOf(c.getAge()));
                Age_mod.setMinimumElementsShownInPopup(1);
                Age_mod.setUIID("txtn");

                AutoCompleteTextField type_mod = new AutoCompleteTextField(String.valueOf(c.getType()));
                type_mod.setMinimumElementsShownInPopup(1);
                type_mod.setUIID("txtn");

                AutoCompleteTextField etat_mod = new AutoCompleteTextField(String.valueOf(c.getEtat()));
                etat_mod.setMinimumElementsShownInPopup(1);
                etat_mod.setUIID("txtn");

                AutoCompleteTextField couleur_mod = new AutoCompleteTextField(String.valueOf(c.getCouleur()));
                couleur_mod.setMinimumElementsShownInPopup(1);
                couleur_mod.setUIID("txtn");

                ComboBox cmb_lib_c = new ComboBox();
                ComboBox cmb_lib_s = new ComboBox();
                cmb_lib_c.setUIID("txtn");
                cmb_lib_s.setUIID("txtn");

                for (Categorie cat : new Categorie_Service().getAllCategories()) {

                    if (cat.getType().equals("Velo")) {
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

                Label AGE = new Label("AGE");
                AGE.setUIID("pass");
                fmodifier.add(AGE).add(Age_mod);

                Label ETAT = new Label("ETAT");
                ETAT.setUIID("pass");
                fmodifier.add(ETAT).add(etat_mod);

                Label TYPE = new Label("TYPE");
                TYPE.setUIID("pass");
                fmodifier.add(TYPE).add(type_mod);

                Label COULEUR = new Label("COULEUR");
                COULEUR.setUIID("pass");
                fmodifier.add(COULEUR).add(couleur_mod);

                Label LC = new Label("LIBELLE CATEGORIE");
                LC.setUIID("pass");
                fmodifier.add(LC).add(cmb_lib_c);

                Label LS = new Label("LIBELLE SITE");
                LS.setUIID("pass");
                fmodifier.add(LS).add(cmb_lib_s);

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
                                fmodifier.getToolbar().addCommandToLeftBar("Return", null, (evtx) -> {
                                    this.showBack();
                                });

                                submit.addActionListener(lll
                                        -> {

                                    Velo ab = new Velo(type_mod.getText(), Integer.valueOf(Age_mod.getText()), couleur_mod.getText(), etat_mod.getText(), Libelle.getText(), cmb_lib_c.getSelectedItem().toString(), cmb_lib_s.getSelectedItem().toString(), nomImage, Description_mod.getText(), Float.valueOf(Prix_mod.getText()));

                                    if (new Velo_Service().ModifierAccessoire(ab, c.getId_v()) == true) {
                                        Dialog.show("Edit Velo", "edit aves success ", "OK", null);
                                        new listVelo(this).showBack();

                                    } else {
                                        Dialog.show("Erreur", "Erreur edit ", "OK", null);
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

        });

        cn1.setLeadComponent(btn);
        return cn1;
    }
}
