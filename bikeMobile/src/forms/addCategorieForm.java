/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import models.Categorie;
import services.Categorie_Service;

/**
 *
 * @author aymen
 */
public class addCategorieForm extends Form {
Resources theme;
    public addCategorieForm(Form previous) {

        super( BoxLayout.y());
        
        theme = UIManager.initFirstTheme("/theme");
 this.getToolbar().setUIID("tb");
  Label logi = new Label("LES CATEGORIES");
        logi.setUIID("login");
        this.add(logi);
        this.getToolbar().addCommandToOverflowMenu("Add Categorie", null, ev -> {
 Label AJOUT = new Label("ADD CATEGORIE");
        AJOUT.setUIID("login");
        
            Form ajout = new Form( BoxLayout.y());

            TextField Libelle = new TextField("", "Libelle", 20, TextArea.ANY);
            Libelle.setUIID("txtn");
            TextField Description = new TextField("", "Description", 20, TextArea.ANY);
            Description.setUIID("txtn");
            ComboBox cmb = new ComboBox("Abonnement", "Accessoire", "Piece", "Velo");
             cmb.setUIID("txtn");
            Button submit = new Button("Submit");
            submit.setUIID("vtnvalid");
            Validator val_Libelle = new Validator();
            val_Libelle.addConstraint(Libelle, new LengthConstraint(8));
            String text_saisir_des_caracteres = "^[0-9]+$";
            val_Libelle.addConstraint(Libelle, new RegexConstraint(text_saisir_des_caracteres, ""));

            Validator val_Description = new Validator();
            val_Description.addConstraint(Description, new LengthConstraint(8));

            val_Description.addConstraint(Description, new RegexConstraint(text_saisir_des_caracteres, ""));
            ajout.add(AJOUT);
             
            Label type1=new Label("TYPE");
            Label Description2=new Label("DESCRIPTION");
            Label Libelle1=new Label("LIBELLE");
            
 type1.setUIID("pass");     
 Description2.setUIID("pass");
 Libelle1.setUIID("pass");
Label a=new Label("    ");
            ajout.add(Libelle1).add(Libelle);
            ajout.add(Description2).add(Description);
            ajout.add(type1).add(cmb);
            
            ajout.addAll(a,submit);
            ajout.getToolbar().setUIID("tb");
            ajout.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.show();
            });
            submit.addActionListener(aj
                    -> {
                   if (Libelle.getText().equals("")) {
                    Dialog.show("Erreur", "Champ vide de Libelle ", "OK", null);

                } else if (val_Libelle.isValid()) {
                    Dialog.show("Erreur Libelle !", "il faut saisir des caracteres  !", "OK", null);
                } else if (Description.getText().equals("")) {
                    Dialog.show("Erreur", "Champ vide de Lieu ", "OK", null);

                } else if (val_Description.isValid()) {
                    Dialog.show("Erreur Lieu !", "il faut saisir des caracteres  !", "OK", null);
                }  else {
                   Categorie s = new Categorie(Libelle.getText(), Description.getText(), cmb.getSelectedItem().toString());
                new Categorie_Service().addCategorie(s);
                new addCategorieForm(this).show();
                }
                
            }
            );

            ajout.show();

        });

        for (Categorie c : new Categorie_Service().getAllCategories()) {

            this.add(addItem(c));

        }
       
        this.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), l -> {
            new Choix_categorie_Produit(this).showBack();
        });
        this.show();

    }

    public Container addItem(Categorie c) {
        Container cn1 = new Container(new BorderLayout());
        Container cn2 = new Container(BoxLayout.y());
        Label lab = new Label(c.getLib_C());
        Button btn = new Button(c.getDescription());
        Label type = new Label(String.valueOf(c.getType()));
        
        lab.setUIID("libC");
        btn.setUIID("btn");
        type.setUIID("type");
               

        cn2.add(lab).add(btn);
        cn1.add(BorderLayout.WEST, cn2);
        cn1.add(BorderLayout.EAST, type);

        btn.addActionListener(e -> {
            Button Modifier = new Button(theme.getImage("edit.png"));
            Button Supprimer = new Button(theme.getImage("remove.png"));
Modifier.setUIID("mod");
Supprimer.setUIID("rem");
 Label AJOUT = new Label("DETAILS");
        AJOUT.setUIID("login");
            Form f2 = new Form( BoxLayout.y());
            
            Label type1=new Label("TYPE");
            Label Lieu = new Label(c.getType());
            
            Label Description2=new Label("DESCRIPTION");
            Label capacite = new Label( String.valueOf(c.getDescription()));
            
            Label Libelle1=new Label("LIBELLE");
            Label Lib_S = new Label(String.valueOf(c.getLib_C()));
 type1.setUIID("type1");
 Lieu .setUIID("type2");        
 Description2.setUIID("type1");
 capacite.setUIID("type2");
 Libelle1.setUIID("type1");
 Lib_S.setUIID("type2");
         
            Modifier.addActionListener(mod
                    -> {
                Label modif = new Label("EDIT CATEGORIE");
        modif.setUIID("login");
        
                Form fmodifier = new Form( BoxLayout.y());
fmodifier.add(modif);
                Button submit = new Button("Submit");
                submit.setUIID("vtnvalid");
                
                AutoCompleteTextField Libelle = new AutoCompleteTextField(c.getLib_C());
                Libelle.setMinimumElementsShownInPopup(1);
                 Libelle.setUIID("txtn");
                AutoCompleteTextField Description = new AutoCompleteTextField(c.getDescription());
                Description.setMinimumElementsShownInPopup(1);
                Description.setUIID("txtn");
                ComboBox cmb = new ComboBox("Abonnement", "Accessoire", "Piece", "Velo");
                cmb.setUIID("txtn");
                
                Label lib=new Label("LIBELLE");
                lib.setUIID("pass");
                fmodifier.add(lib).add(Libelle);
                Label des=new Label("DESCRIPTION");
                 des.setUIID("pass");
                fmodifier.add(des).add(Description);
                Label ty=new Label("TYPE");
                 ty.setUIID("pass");
                fmodifier.add(ty).add(cmb);
                Label a=new Label("    ");
                fmodifier.addAll(a,submit);
                fmodifier.getToolbar().setUIID("tb");
                fmodifier.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evt) -> {
                    this.show();
                });
                submit.addActionListener(sub
                        -> {
                    Categorie catnew = new Categorie(Libelle.getText(), Description.getText(), cmb.getSelectedItem().toString());
                    new Categorie_Service().ModifierCategorie(catnew, c.getID_C());
                    new addCategorieForm(this).show();

                }
                );

                fmodifier.show();
            }
            );

            Supprimer.addActionListener(sup
                    -> {
                new Categorie_Service().DeleteCategorie(c.getID_C());
                new addCategorieForm(this).show();

            }
            );
f2.getToolbar().setUIID("tb");
            f2.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evt) -> {
                this.show();
            });
            f2.add(AJOUT).add(Libelle1).add(Lib_S).add(type1).add(Lieu).add(Description2).add(capacite).add(Modifier).add(Supprimer);
            f2.show();

        });

        cn1.setLeadComponent(btn);
        return cn1;

    }

}
