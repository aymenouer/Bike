/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import models.Abonnement;
import models.Categorie;
import models.Site;
import services.Abonnment_Service;
import services.Categorie_Service;
import services.Site_Service;

/**
 *
 * @author aymen
 */
public class addAbonnmentForm extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");

    public addAbonnmentForm(Form previous) {
        super(BoxLayout.y());

        Toolbar.setGlobalToolbar(true);

        this.add(new InfiniteProgress());
        Display.getInstance().scheduleBackgroundTask(() -> {
            // this will take a while...

            Display.getInstance().callSerially(() -> {
                this.removeAll();
                Label logi = new Label("LES ABONNEMENTS");
                logi.setUIID("login");

                for (Abonnement c : new Abonnment_Service().getAllAbonnements()) {
                    String url = "http://localhost/bike/web/uploads/images/qrproduit/" + c.getImage();

                    Image imge;
                    EncodedImage enc;

                    enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
                    imge = URLImage.createToStorage(enc, url, url);
//this.add(logi);
                    MultiButton m = new MultiButton();
                    m.setUIIDLine1("libC");
                    m.setUIIDLine2("btn");
                    m.setTextLine1(c.getLibelle());
                    m.setTextLine2(c.getLib_C());
                    m.setEmblem(theme.getImage("round.png"));
                    m.addActionListener(l
                            -> {

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
                        type5.setUIID("type1");
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
                        Button Modifier = new Button(theme.getImage("edit.png"));
                        Button Supprimer = new Button(theme.getImage("remove.png"));
                        Modifier.setUIID("mod");
                        Supprimer.setUIID("rem");
                        Container x = new Container(BoxLayout.x());
                        x.addAll(Modifier, Supprimer);
                        String url2 = "http://localhost/bike/web/uploads/images/qrproduit/" + c.getImage();
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
                            new Abonnment_Service().DeleteAbonnement(c.getId_A());
                            new addAbonnmentForm(this).showBack();
                        }
                        );
                        Modifier.addActionListener(eva
                                -> {
                                Form fmodifier = new Form( BoxLayout.y());
                            Label modif = new Label("EDIT ABONNEMENT");
                            modif.setUIID("login");
                            
                            fmodifier.add(modif);

                            fmodifier.getToolbar().setUIID("tb");
                            fmodifier.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evt) -> {
                                f2.showBack();
                            });
                            Button submit = new Button("Submit");
                            submit.setUIID("vtnvalid");
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

                                if (cat.getType().equals("Abonnement")) {
                                    cmb_lib_c.addItem(cat.getLib_C());
                                }

                            }
                            for (Site siteq : new Site_Service().getAllsites()) {

                                cmb_lib_s.addItem(siteq.getLib_S());

                            }
                            fmodifier.add(type5).add(Libelle);
                            fmodifier.add(Description2).add(Description_mod);
                            fmodifier.add(PRIX).add(Prix_mod);
                            fmodifier.add(QUANTITE).add(Quantite_mod);
                            fmodifier.add(Libelle1).add(cmb_lib_c);
                            fmodifier.add(Libelle5).add(cmb_lib_s);
                            fmodifier.add(submit);

                            submit.addActionListener(lll
                                    -> {
                                String urlab = "http://127.0.0.1/bike/mobile/qrcode.php";

                                ConnectionRequest cnreq = new ConnectionRequest();
                                cnreq.setPost(false);
                                String data = "Libelle : " + Libelle.getText() + "<br>  Description : " + capacite.getText() + " <br>  prix :" + Prix.getText() + " DT <br> Categorie : " + cmb_lib_c.getSelectedItem().toString() + " <br> Site : " + cmb_lib_s.getSelectedItem().toString() + "<br> Merci pour votre confiance &#128525;";

                                cnreq.addArgument("data", data);
                                cnreq.setUrl(urlab);

                                cnreq.addResponseListener(evx
                                        -> {
                                    String Image2 = new String(cnreq.getResponseData());
                                    Abonnement ab = new Abonnement(Integer.valueOf(Quantite_mod.getText()), Libelle.getText(), cmb_lib_c.getSelectedItem().toString(), cmb_lib_s.getSelectedItem().toString(), Image2, Description_mod.getText(), Float.valueOf(Prix_mod.getText()));

                                    new Abonnment_Service().ModifierAbonnement(ab, c.getId_A());
                                    new addAbonnmentForm(this).showBack();

                                }
                                );
                                NetworkManager.getInstance().addToQueueAndWait(cnreq);

                            }
                            );
                            fmodifier.show();
                        }
                        );

                        f2.show();

                    }
                    );
                    m.setIcon(imge);

                    this.add(m);
                }
                this.revalidate();
            });
        });

        this.getToolbar().addSearchCommand(e -> {
            String text = (String) e.getSource();
            if (text == null || text.length() == 0) {
                // clear search
                for (Component cmp : this.getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                this.getContentPane().animateLayout(150);
            } else {
                text = text.toLowerCase();
                for (Component cmp : this.getContentPane()) {
                    MultiButton mb = (MultiButton) cmp;
                    String line1 = mb.getTextLine1();
                    String line2 = mb.getTextLine2();
                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1
                            || line2 != null && line2.toLowerCase().indexOf(text) > -1;
                    mb.setHidden(!show);
                    mb.setVisible(show);
                }
                this.getContentPane().animateLayout(150);
            }
        }, 4);
        this.getToolbar().setUIID("tb");
        this.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evt)
                -> {
            new Choix_categorie_Produit(previous).showBack();

        });
        this.getToolbar().addCommandToOverflowMenu("Stat", null, (evt)
                -> {
            new StatAbonnment().createPieChartForm("Abonnment", new Abonnment_Service().getStat());

        });
        this.getToolbar().addCommandToOverflowMenu("Add Abonnment", null, ev -> {

            Form ajout = new Form(BoxLayout.y());
            Label AJOUT = new Label("ADD ABONNEMENT");
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
            ComboBox cmb_lib_s = new ComboBox();
            for (Categorie c : new Categorie_Service().getAllCategories()) {

                if (c.getType().equals("Abonnement")) {
                    cmb_lib_c.addItem(c.getLib_C());
                }

            }
            for (Site c : new Site_Service().getAllsites()) {

                cmb_lib_s.addItem(c.getLib_S());

            }
            cmb_lib_c.setUIID("txtn");
            cmb_lib_s.setUIID("txtn");
            Button submit = new Button("Submit");
            submit.setUIID("vtnvalid");
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
            ajout.add(submit);
            ajout.getToolbar().setUIID("tb");
            ajout.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
            submit.addActionListener(aj
                    -> {
                String url = "http://127.0.0.1/bike/mobile/qrcode.php";

                ConnectionRequest cnreq = new ConnectionRequest();
                cnreq.setPost(false);
                String data = "Libelle : " + Libelle.getText() + "<br>  Description : " + Description.getText() + " <br>  prix :" + Prix.getText() + " DT <br> Categorie : " + cmb_lib_c.getSelectedItem().toString() + " <br> Site : " + cmb_lib_s.getSelectedItem().toString() + "<br> Merci pour votre confiance &#128525;";

                cnreq.addArgument("data", data);
                cnreq.setUrl(url);
                cnreq.addResponseListener(evx
                        -> {
                    String Image = new String(cnreq.getResponseData());
                    Abonnement ab = new Abonnement(Integer.valueOf(Quantite.getText()), Libelle.getText(), cmb_lib_c.getSelectedItem().toString(), cmb_lib_s.getSelectedItem().toString(), Image, Description.getText(), Float.valueOf(Prix.getText()));
                    new Abonnment_Service().addAbonnement(ab);
                    new addAbonnmentForm(this).show();

                }
                );
                NetworkManager.getInstance().addToQueueAndWait(cnreq);

            }
            );

            ajout.show();

        });

    }

}
