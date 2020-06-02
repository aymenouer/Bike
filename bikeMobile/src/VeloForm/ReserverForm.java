/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VeloForm;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.datatransfer.DropTarget;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
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
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import forms.Choix_categorie_Produit;
import forms.UserInterfaceForm;
import java.io.IOException;
import java.util.Date;

import javafx.scene.control.DatePicker;
import models.Categorie;
import models.Reservation;
import models.Session;
import models.Site;
import models.Velo;
import services.Categorie_Service;
import services.Reservation_Service;
import services.Site_Service;
import services.Velo_Service;

/**
 *
 * @author asus
 */
public class ReserverForm extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");

    public ReserverForm(Form previous) {
        super(BoxLayout.y());
        Label logi = new Label("RESERVER UN VELO");
        logi.setUIID("login");
        this.add(logi);
        this.getToolbar().setUIID("tb");
        this.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), ev -> {
            new UserInterfaceForm(previous).showBack();
        });
        for (Velo c : new Velo_Service().getAllvelos()) {

            this.add(addItem(c));

        }
    }

    public Container addItem(Velo c) {

        Container cn1 = new Container(new BorderLayout());
        Container cn2 = new Container(BoxLayout.y());
        Label lab = new Label(c.getLibelle());
        Label prixx = new Label(String.valueOf(c.getPrix()));
        Button btn = new Button("book");
        lab.setUIID("libC");
        btn.setUIID("btn");
        prixx.setUIID("type2");
        btn.setUIID("vtnvalid");
        String url = "http://localhost/bike/web/uploads/images/" + c.getImage();
        ImageViewer imgv;
        Image imge;
        EncodedImage enc;
        enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
        imge = URLImage.createToStorage(enc, url, url);
        imgv = new ImageViewer(imge);
        System.err.println(c.getImage());
        cn2.add(lab).add(prixx).add(btn);
        cn1.add(BorderLayout.WEST, cn2);
        cn1.add(BorderLayout.EAST, imgv);

        btn.addActionListener(e -> {

            Form f2 = new Form(BoxLayout.y());
            Label logi = new Label("RESERVER");
            logi.setUIID("login");
            f2.add(logi);

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
            Picker dateD = new Picker();
            Picker dateF = new Picker();
            Button btn_modifier = new Button("Reserver");
            btn_modifier.setUIID("vtnvalid");

            f2.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evt) -> {
                this.show();
            });
           f2.add(type1).add(libelle).add(type9).add(categorie).add(type2).add(site).add(type3).add(age).add(type4).add(couleur).add(type5).add(etat).add(type6).add(type).add(type7).add(Description).add(type8).add(Prix).add(btn_modifier);


            f2.show();
            btn_modifier.addActionListener(l -> {
                if (dateD.getText().equals("")) {
                    Dialog.show("Erreur", "Champ vide de dateD ", "OK", null);

                } else if (dateF.getText().equals("")) {
                    Dialog.show("Erreur", "Champ vide de dateF ", "OK", null);

                } else {

                    System.out.println("datedd" + dateD.getDate());
                    System.out.println("datefff" + dateF.getDate());

                    DateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
                    Date ddebut = dateD.getDate();
                    String dated = dd.format(ddebut);
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    Date dfin = dateF.getDate();
                    String datef = df.format(dfin);

                    Date datedebut = new Date();
                    Date datefin = new Date();

                    float pr = ((int) (dateF.getDate().getTime() - dateD.getDate().getTime())) * c.getPrix();
                    System.out.println(pr);
                    Reservation ab;

                    try {
                        datedebut = df.parse(dated);
                        datefin = df.parse(datef);
                        ab = new Reservation(Session.getCurrentSession(), c.getId_v(), datedebut, datefin, pr);

                        if (new Reservation_Service().verif_reservation(ab) == 0) {
                            if (new Reservation_Service().Reserver(ab) == true) {
                                Dialog.show("Reservation Succes", "reservation aves success ", "OK", null);

                            } else {
                                Dialog.show("Erreur", "Erreur reservation ", "OK", null);
                            }
                        } else {
                            Dialog.show("Erreur", "Erreur reservation ", "OK", null);
                        }

                    } catch (Exception ex) {

                    }

                }

            });

        });

        cn1.setLeadComponent(btn);
        return cn1;
    }
}
