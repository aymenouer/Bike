/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
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
import models.Site;
import services.Site_Service;

/**
 *
 * @author aymen
 */
public class addSiteForm extends Form {

    Resources theme;

    public addSiteForm(Form previous) {

         super( BoxLayout.y());
        
        theme = UIManager.initFirstTheme("/theme");
 this.getToolbar().setUIID("tb");
  Label logi = new Label("LES SITES");
        logi.setUIID("login");
        this.add(logi);
        this.getToolbar().setUIID("tb");
         this.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), l -> {
            new LoginForm().showBack();
        });
        this.getToolbar().addCommandToOverflowMenu("Add Site", null, ev -> {

            Form ajout = new Form( BoxLayout.y());
 Label AJOUT = new Label("ADD SITE");
        AJOUT.setUIID("login");
        ajout.add(AJOUT);
            TextField Libelle = new TextField("", "Libelle", 20, TextArea.ANY);
             Libelle.setUIID("txtn");
            TextField Lieu = new TextField("", "Lieu", 20, TextArea.ANY);
             Lieu.setUIID("txtn");
            TextField Capacite = new TextField("", "Capacite", 4, TextArea.NUMERIC);
             Capacite.setUIID("txtn");
            Button submit = new Button("Submit");
             submit.setUIID("vtnvalid");
            
            Validator val_Libelle = new Validator();
            val_Libelle.addConstraint(Libelle, new LengthConstraint(8));
            String text_saisir_des_caracteres = "^[0-9]+$";
            val_Libelle.addConstraint(Libelle, new RegexConstraint(text_saisir_des_caracteres, ""));

              Validator val_Lieu = new Validator();
            val_Lieu.addConstraint(Lieu, new LengthConstraint(8));
      
            val_Lieu.addConstraint(Lieu, new RegexConstraint(text_saisir_des_caracteres, ""));
 Validator val_capacite = new Validator();
                            val_capacite.addConstraint(Capacite, new LengthConstraint(8));
                            val_capacite.addConstraint(Capacite, new RegexConstraint(text_saisir_des_caracteres, ""));
 Label type1=new Label("CAPACITE");
            Label Description2=new Label("LIEU");
            Label Libelle1=new Label("LIBELLE");
            
 type1.setUIID("pass");     
 Description2.setUIID("pass");
 Libelle1.setUIID("pass");
Label a=new Label("    ");
            ajout.add(Libelle1).add(Libelle);
            ajout.add(Description2).add(Lieu);
            ajout.add(type1).add(Capacite);
            
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
                }
                else if (Lieu.getText().equals("")) {
                    Dialog.show("Erreur", "Champ vide de Lieu ", "OK", null);

                } else if (val_Lieu.isValid()) {
                    Dialog.show("Erreur Lieu !", "il faut saisir des caracteres  !", "OK", null);
                }  
                 else if (Capacite.getText().equals("")) {
                    Dialog.show("Erreur", "Champ vide de Capacite ", "OK", null);

                } 
                 
                       else if (!val_capacite.isValid()) {
                                 Dialog.show("Erreur Capacite !", "il faut saisir des numbers", "OK", null);

                            } 
                  else if (Integer.valueOf(Capacite.getText())<=0) {
                                Dialog.show("Erreur Capacite !", "age n'est pas acceptable", "OK", null);

                            }
                  
                
                else
                {
                             Site s = new Site(Integer.valueOf(Capacite.getText()), Libelle.getText(), Lieu.getText());
                new Site_Service().addSite(s);
                new addSiteForm(this).show();  
                }

                }
                );

                ajout.show();

            });
            this.getToolbar().addCommandToOverflowMenu("Google Map", null, ev -> {
                new map().start(this);

            });

            this.getToolbar().addCommandToOverflowMenu("Logout", null, ev -> {

                new LoginForm().show();
            });

            for (Site c : new Site_Service().getAllsites()) {

                this.add(addItem(c));

            }

        }

    public Container addItem(Site c) {
        Container cn1 = new Container(new BorderLayout());
        Container cn2 = new Container(BoxLayout.y());
        Label lab = new Label(c.getLieu());
        Button btn = new Button(c.getLib_S());
        Label cap = new Label(String.valueOf(c.getCapacite()));

        lab.setUIID("libC");
        btn.setUIID("btn");
        cap.setUIID("type");
        cn2.add(lab).add(btn);
        cn1.add(BorderLayout.WEST, cn2);
        cn1.add(BorderLayout.EAST, cap);

        btn.addActionListener(e -> {

            Form f2 = new Form( BoxLayout.y());
            Label AJOUT = new Label("DETAILS");
        AJOUT.setUIID("login");
        f2.add(AJOUT);
             Label type1=new Label("LIEU");
            Label Lieu = new Label(c.getLieu());
             Label type2=new Label("CAPACITE");
            Label capacite = new Label(String.valueOf(c.getCapacite()));
             Label type3=new Label("LIBELLE");
            Label Lib_S = new Label( String.valueOf(c.getLib_S()));
            
 type1.setUIID("type1");
 Lieu .setUIID("type2");        
 type2.setUIID("type1");
 capacite.setUIID("type2");
 type3.setUIID("type1");
 Lib_S.setUIID("type2");

            Button Modifier = new Button(theme.getImage("edit.png"));
            Button Supprimer = new Button(theme.getImage("remove.png"));
            Modifier.setUIID("mod");
            Supprimer.setUIID("rem");

            Modifier.addActionListener(mod
                    -> {
                Form fmodifier = new Form( BoxLayout.y());
  Label modif = new Label("EDIT SITE");
        modif.setUIID("login");
        
            
fmodifier.add(modif);
                Button submit = new Button("Submit");
                 submit.setUIID("vtnvalid");
                 
                AutoCompleteTextField Libelle = new AutoCompleteTextField(c.getLib_S());
                Libelle.setMinimumElementsShownInPopup(1);
                Libelle.setUIID("txtn");
                
                AutoCompleteTextField Lieumod = new AutoCompleteTextField(c.getLieu());
                Lieumod.setMinimumElementsShownInPopup(1);
                Lieumod.setUIID("txtn");
                
                AutoCompleteTextField capacite_mod = new AutoCompleteTextField(String.valueOf(c.getCapacite()));
                capacite_mod.setMinimumElementsShownInPopup(1);
                capacite_mod.setUIID("txtn");
                
                Label lib=new Label("LIBELLE");
                lib.setUIID("pass");
                fmodifier.add(lib).add(Libelle);
                
                Label des=new Label("LIEU");
                 des.setUIID("pass");
                fmodifier.add(des).add(Lieumod);
                
                Label ty=new Label("CAPACITE");
                 ty.setUIID("pass");
                fmodifier.add(ty).add(capacite_mod);
                
                Label a=new Label("    ");
                fmodifier.addAll(a,submit);
                 fmodifier.getToolbar().setUIID("tb");
                fmodifier.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evt) -> {
                    this.show();
                });

                submit.addActionListener(sub
                        -> {
                    Site snew = new Site(Integer.valueOf(capacite_mod.getText()), Libelle.getText(), Lieumod.getText());
                    new Site_Service().ModifierSite(snew, c.getId());
                    new addSiteForm(this).show();

                }
                );

                fmodifier.show();
            }
            );

            Supprimer.addActionListener(sup
                    -> {
                new Site_Service().DeleteSite(c.getId());
                new addSiteForm(this).show();

            }
            );
     
            f2.getToolbar().setUIID("tb");
            f2.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evt) -> {
                this.show();
            });
            f2.add(type3).add(Lib_S).add(type1).add(Lieu).add(type2).add(capacite).add(Modifier).add(Supprimer);
            f2.show();

        });

        cn1.setLeadComponent(btn);
        return cn1;

    }

}
