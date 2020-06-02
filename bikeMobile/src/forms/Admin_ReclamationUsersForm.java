/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

import models.reclamation;
import services.Reclamation_Service;

/**
 *
 * @author aymen
 */
public class Admin_ReclamationUsersForm extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");

    public Admin_ReclamationUsersForm(Form previous) throws Exception {
        super(BoxLayout.y());
        this.getToolbar().setUIID("tb");
        Label logi = new Label("LES RECLAMATIONS");
        logi.setUIID("login");
        this.add(logi);
        for (reclamation c : new Reclamation_Service().getAllReclamationsUsers()) {

            this.add(addItem_reclamation(c));

        }
          this.getToolbar().setUIID("tb");
        this.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), ev -> {
            new AccueilAdminForm(previous).showBack();
        });
        this.getToolbar().addCommandToOverflowMenu("Logout", null, ev -> {
            new LoginForm().showBack();
        });

    }

    public Container addItem_reclamation(reclamation c) {

        Container cn1 = new Container(new BorderLayout());
        Container cn2 = new Container(BoxLayout.y());
        Label etat = new Label(c.getEtat());
        Button btn = new Button(c.getType());
        etat.setUIID("libC");
        btn.setUIID("btn");
        if (c.getEtat().equals("Mauvais")) {
            etat.getStyle().setFgColor(0xd61f1f);
        } else {
            etat.getStyle().setFgColor(0x1b7021);
        }

        String url = "http://localhost/bike/web/uploads/images/" + c.getImage();
        ImageViewer imgv;
        Image imge;
        EncodedImage enc;

        enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
        imge = URLImage.createToStorage(enc, url, url);
        imgv = new ImageViewer(imge);

        cn2.add(etat).add(btn);
        cn1.add(BorderLayout.WEST, cn2);
        cn1.add(BorderLayout.EAST, imgv);

        btn.addActionListener(e -> {

            Form f2 = new Form(BoxLayout.y());
            Label AJOUT = new Label("DETAILS");
            AJOUT.setUIID("login");
            f2.add(AJOUT);

            Label type1 = new Label("TYPE");
            Label type = new Label(c.getType());
            Label ETAT = new Label("ETAT");
            Label etat_d = new Label(c.getEtat());
            Label CONTENU = new Label("CONTENU");
            Label contenu = new Label(c.getContenu());
            Label TRAITE = new Label("TRAITE");
            Label Traite = new Label(c.getTraite());

        
            type.setUIID("type2");
            etat_d.setUIID("type2");
            contenu.setUIID("type2");
            Traite.setUIID("type2");

            Button btn_Traite = new Button(theme.getImage("edit.png"));
            Button btn_sup = new Button(theme.getImage("remove.png"));
            btn_Traite.setUIID("mod");
            btn_sup.setUIID("rem");
            if (c.getEtat().equals("Mauvais")) {
                etat_d.getStyle().setFgColor(0xd61f1f);
            } else {
                etat.getStyle().setFgColor(0x1b7021);
            }

            if (c.getTraite().equals("Traite")) {
                Traite.getStyle().setFgColor(0x1b7021);
            } else {
                Traite.getStyle().setFgColor(0xd61f1f);
            }

            String url2 = "http://localhost/bike/web/uploads/images/" + c.getImage();
            ImageViewer imgv2;
            Image imge2;
            EncodedImage enc2;
            enc2 = EncodedImage.createFromImage(theme.getImage("round.png"), false);
            imge2 = URLImage.createToStorage(enc, url, url);
            imgv2 = new ImageViewer(imge2);

            f2.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evt) -> {
                this.showBack();
            });
                type1.setUIID("type1");
            ETAT.setUIID("type1");
            CONTENU.setUIID("type1");
            TRAITE.setUIID("type1");
            f2.add(type1).add(type).add(ETAT).add(etat_d).add(CONTENU).add(contenu).add(TRAITE).add(imgv2).add(Traite).add(btn_sup);

            btn_Traite.addActionListener(ll
                    -> {
                try {
                    new Reclamation_Service().Traite_Reclamation(c.getId());

                    Dialog.show("Modification", "Traite", "OK", null);
                    new Admin_ReclamationUsersForm(f2).showBack();

                } catch (Exception ex) {
                }

            }
            );
            btn_sup.addActionListener(ll
                    -> {
                try {
                    new Reclamation_Service().Delete_Reclamation(c.getId());

                    Dialog.show("suppression", "suppression avec Success", "OK", null);
                    new Admin_ReclamationUsersForm(f2).showBack();

                } catch (Exception ex) {
                }

            }
            );

            if (c.getTraite().equals("Traite")) {

            } else {
                f2.add(btn_Traite);
            }
            f2.show();

        });

        cn1.setLeadComponent(btn);
        return cn1;

    }

}
