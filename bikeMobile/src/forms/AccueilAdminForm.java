/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;

import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;

import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

import models.Session;
import models.User;

/**
 *
 * @author aymen
 */
public class AccueilAdminForm extends Form {

    Resources theme;

    public AccueilAdminForm(Form previous) {
        super(BoxLayout.y());
        this.getAllStyles().setBgColor(0xffffff);
        theme = UIManager.initFirstTheme("/theme");
        Button b, btn_responsable_Produit, btn_responsable_evenment, btn_Responsable_site, btn_responsable_reservation, btn_responsable_Maintenance;

        btn_responsable_Produit = new Button(theme.getImage("PRO.png")); // hedha fih produit(abonnment,piece,accessoirte,velo) , categorie
        btn_responsable_Produit.setUIID("btnre");
        btn_responsable_evenment = new Button(theme.getImage("event.png"));
        btn_responsable_evenment.setUIID("btnre");
        btn_Responsable_site = new Button(theme.getImage("parking.png"));
        btn_Responsable_site.setUIID("btnre");
        btn_responsable_Maintenance = new Button(theme.getImage("piece.png"));
        btn_responsable_Maintenance.setUIID("btnre");

        Label lab = new Label("RESPONSABLE PRODUIT");
        lab.setUIID("res");
        Label laba = new Label("RESPONSABLE EVENEMENT");
        laba.setUIID("rese");
        Label labaa = new Label("RESPONSABLE SITE");
        labaa.setUIID("ress");
        Label labaaa = new Label("RESPONSABLE PIECE");
        labaaa.setUIID("resp");
        this.add(btn_responsable_Produit);
        this.add(lab);
        this.add(btn_Responsable_site);
        this.add(labaa);
        this.add(btn_responsable_Maintenance);
        this.add(labaaa);
        this.add(btn_responsable_evenment);
        this.add(laba);

        btn_responsable_Maintenance.addActionListener(pro
                -> {

            new Admin_MaintenanceForm(this).show();
        }
        );

        btn_responsable_Produit.addActionListener(pro
                -> {

            new Choix_categorie_Produit(this).show();
        }
        );

        btn_Responsable_site.addActionListener(l
                -> {

            new addSiteForm(this).show();
        }
        );

        this.getToolbar().setUIID("tb");
       
        this.getToolbar().addCommandToOverflowMenu("Users", null, ev -> {

            new ListUsersForm(this).show();
        });
        
        this.getToolbar().addCommandToOverflowMenu("Info", null, ev -> {

            new Detail_ProfilForm(this).show();

        });
        this.getToolbar().addCommandToOverflowMenu("Reclamations", null, ev -> {

            try {
                new Admin_ReclamationUsersForm(this).show();
            } catch (Exception ex) {

            }
        });
        this.getToolbar().addCommandToOverflowMenu("Logout", null, ev -> {
            new LoginForm().show();
        });

    }

}
