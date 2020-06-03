/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import VeloForm.MesReservationsForm;
import VeloForm.ReserverForm;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.contacts.Contact;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;

import models.Abonnement;
import models.Accessoire;
import models.Piece;
import models.Rating_Produit;
import models.Session;
import models.Velo;
import services.Abonnment_Service;
import services.Accessoire_Service;
import services.Achat_Service;
import services.Piece_Service;
import services.RateProduit_Service;
import services.Velo_Service;

/**
 *
 * @author aymen
 */
public class UserInterfaceForm extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");

    public UserInterfaceForm(Form previous) {
        super(BoxLayout.y());

        this.add(new InfiniteProgress());
        Display.getInstance().scheduleBackgroundTask(() -> {
            // this will take a while...

            Display.getInstance().callSerially(() -> {
                this.removeAll();
                Label logi = new Label("LES PRODUITS");
                logi.setUIID("login");
              //  this.add(logi);

                for (Abonnement c : new Abonnment_Service().getAllAbonnements()) {

                    this.add(addIteam_abonnment(c));
                }
                for (Accessoire c : new Accessoire_Service().getAllAccessoires()) {

                    this.add(addItem_accessoire(c));

                }
                for (Piece c : new Piece_Service().getAllPieces()) {

                    this.add(addItem_Piece(c));

                }
                 for (Velo c : new Velo_Service().getAllvelos()) {

                    this.add(addItem_velo(c));

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
                    mb.setUIIDLine1("libC");
                    mb.setUIIDLine2("btn");
                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1
                            || line2 != null && line2.toLowerCase().indexOf(text) > -1;
                    mb.setHidden(!show);
                    mb.setVisible(show);
                }
                this.getContentPane().animateLayout(150);
            }
        }, 4);
        
 
        this.getToolbar().addCommandToSideMenu("Products", null, e -> {
            this.show();
        });

        this.getToolbar().addCommandToSideMenu("Mon offre", null, e -> {
            new Achat_OffreForm(this).show();
        });
        this.getToolbar().addCommandToSideMenu("Profil", null, e -> {
            new Detail_ProfilForm(this).show();
        });
        this.getToolbar().addCommandToSideMenu("Mes Reservations", null, e -> {
            try {
                new MesReservationsForm(this).show();
            } catch (Exception ex) {

            }
        });
        this.getToolbar().addCommandToSideMenu("Reserver", null, e -> {
            new ReserverForm(this).show();
        });
        this.getToolbar().addCommandToSideMenu("Mes Maintenance", null, e -> {
            new User_MaintenanceForm(this).show();
        });
        this.getToolbar().addCommandToSideMenu("Events", null, e -> {
         try {
                new EventUser(this).show();
            } catch (IOException ex) {
            }
        });

        this.getToolbar().addCommandToSideMenu("Mes Reclamations", null, e -> {
            try {
                new User_ReclamationForm(this).show();
            } catch (Exception ex) {

            }
        });

        this.getToolbar().addCommandToSideMenu("Logout", null, e -> {
            new LoginForm().show();
        });

    }

    public MultiButton addIteam_abonnment(Abonnement c) {
        MultiButton m = new MultiButton();
        String textline3 = "";
        String url = "";
        if (c.getQuantite() >= 10) {

            url = "http://localhost/bike/web/uploads/images/smile.png";
            textline3 = "Disponible";
             m.setUIIDLine3("vert");

        } else if (c.getQuantite() > 5 && c.getQuantite() < 10) {

            url = "http://localhost/bike/web/uploads/images/limite.png";
            textline3 = "Limite";
  m.setUIIDLine3("orange");
        } else {

            url = "http://localhost/bike/web/uploads/images/epuise.png";
            textline3 = "Epuise";
              m.setUIIDLine3("rouge");

        }
       //m.setUIIDLine1(url);
        m.setTextLine1(c.getLibelle());
        m.setTextLine2(c.getLib_C());
          m.setUIIDLine1("libC2");
                    m.setUIIDLine2("btn");
        m.setTextLine3(textline3);
          
        m.setEmblem(theme.getImage("arrow.png"));
        Image imge;
        EncodedImage enc;
        enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
        imge = URLImage.createToStorage(enc, url, url);
        m.setIcon(imge);
        m.addActionListener(l
                -> {

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
            Button acheter = new Button("acheter");
            acheter.setUIID("vtnvalid");

            type1.setUIID("type1");
            type2.setUIID("type1");
            type3.setUIID("type1");
            type4.setUIID("type1");
            type5.setUIID("type1");
            type6.setUIID("type1");
            
            libelle.setUIID("type2");
            categorie.setUIID("type2");
            site.setUIID("type2");
            quantite.setUIID("type2");
            Description.setUIID("type2");
            Prix.setUIID("type2");

            acheter.addActionListener(xxx
                    -> {
                if (new Achat_Service().verif_Achat(Session.get().getId()) == null) {

                    new User_Achat_Form(this, c.getId_A()).show();

                } else {

                    Dialog.show("Erreur", "Vous avez deja un abonnment", "OK", null);
                }
            }
            );

            f2.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evt) -> {
                this.showBack();
            });
            type1.setUIID("type1");
            type2.setUIID("type1");
            type3.setUIID("type1");
            type4.setUIID("type1");
            type5.setUIID("type1");
            type6.setUIID("type1");
       
             
            f2.add(type1).add(libelle).add(type2).add(categorie).add(type3).add(site).add(type4).add(quantite).add(type5).add(Description).add(type6).add(Prix).add(acheter);

            f2.show();

        }
        );
        return m;
    }

    public MultiButton addItem_accessoire(Accessoire c) {
        MultiButton m = new MultiButton();
        m.setTextLine1(c.getLibelle());
        m.setTextLine2(c.getLib_C());
          m.setUIIDLine1("libC2");
                    m.setUIIDLine2("btn");
        String url = "http://localhost/bike/web/uploads/images/" + c.getImage();
        Slider rate = new Rating_produit().createStarRankSlider();
        Image imge;
        EncodedImage enc;
        enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
        imge = URLImage.createToStorage(enc, url, url);
        m.setIcon(imge);
        m.setEmblem(theme.getImage("arrow.png"));
        m.addActionListener(l
                -> {

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
            Button acheter = new Button("acheter");
            acheter.setUIID("vtnvalid");

            type1.setUIID("type1");
            type2.setUIID("type1");
            type3.setUIID("type1");
            type4.setUIID("type1");
            type5.setUIID("type1");
            type6.setUIID("type1");
            
            libelle.setUIID("type2");
            categorie.setUIID("type2");
            site.setUIID("type2");
            quantite.setUIID("type2");
            Description.setUIID("type2");
            Prix.setUIID("type2");

         

            f2.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evt) -> {
                this.showBack();
            });
            type1.setUIID("type1");
            type2.setUIID("type1");
            type3.setUIID("type1");
            type4.setUIID("type1");
            type5.setUIID("type1");
            type6.setUIID("type1");
       
             
            
            String url2 = "http://localhost/bike/web/uploads/images/" + c.getImage();
            ImageViewer imgv2;
            Image imge2;
            EncodedImage enc2;
            enc2 = EncodedImage.createFromImage(theme.getImage("round.png"), false);
            imge2 = URLImage.createToStorage(enc2, url2, url2);
            imgv2 = new ImageViewer(imge2);

              f2.add(imgv2).add(type1).add(libelle).add(type2).add(categorie).add(type3).add(site).add(type4).add(quantite).add(type5).add(Description).add(type6).add(Prix).add(acheter);

           
            try {
                Rating_Produit rate_p = new RateProduit_Service().verif_Rate(c.getId_p());
                System.out.println("eeeeee" + rate_p);
                System.out.println();
                if (rate_p == null) {
                    f2.add("Rate : ");

                    f2.add(FlowLayout.encloseCenter(rate));
                    Button btn_rate = new Button("Rate");
btn_rate.setUIID("vtnvalid");
                    f2.add(btn_rate);

                    btn_rate.addActionListener(lw
                            -> {

                        try {
                            new RateProduit_Service().addRate(new Rating_Produit(c.getId_p(), rate.getProgress()));
                            Dialog.show("Rate", "Merci pour votre vote", "OK", null);
                            new UserInterfaceForm(f2).showBack();

                        } catch (Exception ex) {

                        }

                    }
                    );
                } else {

                    rate.setEditable(false);
                    rate.setProgress(rate_p.getVote());
                    f2.add("Merci pour votre Rate  : ");

                    f2.add(FlowLayout.encloseCenter(rate));

                }

            } catch (Exception e) {
            }
            f2.show();

        }
        );

        return m;
    }
    public MultiButton addItem_velo(Velo c) {
        MultiButton m = new MultiButton();
        m.setTextLine1(c.getLibelle());
        m.setTextLine2(c.getLib_C());
          m.setUIIDLine1("libC2");
          m.setUIIDLine2("btn");
        String url = "http://localhost/bike/web/uploads/images/" + c.getImage();
        Slider rate = new Rating_produit().createStarRankSlider();
        Image imge;
        EncodedImage enc;
        enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
        imge = URLImage.createToStorage(enc, url, url);
        m.setIcon(imge);
        m.setEmblem(theme.getImage("arrow.png"));
        m.addActionListener(l
                -> {

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

            Button acheter = new Button("RESERVER");
            acheter.setUIID("vtnvalid");
              acheter.addActionListener(xxx
                    -> {
               new ReserverForm(this).show();
            }
            );
            String url2 = "http://localhost/bike/web/uploads/images/" + c.getImage();
            ImageViewer imgv2;
            Image imge2;
            EncodedImage enc2;
            enc2 = EncodedImage.createFromImage(theme.getImage("round.png"), false);
            imge2 = URLImage.createToStorage(enc2, url2, url2);
            imgv2 = new ImageViewer(imge2);

            f2.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evt) -> {
                this.showBack();
            });
             f2.add(imgv2).add(type1).add(libelle).add(type9).add(categorie).add(type2).add(site).add(type3).add(age).add(type4).add(couleur).add(type5).add(etat).add(type6).add(type).add(type7).add(Description).add(type8).add(Prix).add(acheter);
            try {
                Rating_Produit rate_p = new RateProduit_Service().verif_Rate(c.getId_p());
                System.out.println("eeeeee" + rate_p);
                System.out.println();
                if (rate_p == null) {
                   

                    f2.add(FlowLayout.encloseCenter(rate));
                    Button btn_rate = new Button("Rate");

                    f2.add(btn_rate);

                    btn_rate.addActionListener(lw
                            -> {

                        try {
                            new RateProduit_Service().addRate(new Rating_Produit(c.getId_p(), rate.getProgress()));
                            Dialog.show("Rate", "Merci pour votre vote", "OK", null);
                            new UserInterfaceForm(f2).showBack();

                        } catch (Exception ex) {

                        }

                    }
                    );
                } else {

                    rate.setEditable(false);
                    rate.setProgress(rate_p.getVote());
                    f2.add("Merci pour votre Rate  : ");

                    f2.add(FlowLayout.encloseCenter(rate));

                }

            } catch (Exception e) {
            }
            f2.show();

        }
        );

        return m;
    }

    public MultiButton addItem_Piece(Piece c) {
        MultiButton m = new MultiButton();
        m.setTextLine1(c.getLibelle());
        m.setTextLine2(c.getLib_C());
        String url = "http://localhost/bike/web/uploads/images/" + c.getImage();

        Image imge;
        EncodedImage enc;
        enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
        imge = URLImage.createToStorage(enc, url, url);
        m.setIcon(imge);
        m.setEmblem(theme.getImage("arrow.png"));
        m.addActionListener(l
                -> {

            Form f2 = new Form("Details", BoxLayout.y());
            Label libelle = new Label(c.getLibelle());
            Label categorie = new Label(c.getLib_C());
            Label site = new Label(c.getLib_S());
            Label quantite = new Label(String.valueOf(c.getQuantite()));
            Label Description = new Label(c.getDescription());
            Label Prix = new Label(String.valueOf(c.getPrix()));
            Label etat = new Label(c.getEtat());
            Button acheter = new Button("acheter");
            String url2 = "http://localhost/bike/web/uploads/images/" + c.getImage();
            ImageViewer imgv2;
            Image imge2;
            EncodedImage enc2;
            enc2 = EncodedImage.createFromImage(theme.getImage("round.png"), false);
            imge2 = URLImage.createToStorage(enc, url, url);
            imgv2 = new ImageViewer(imge2);

            f2.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
                this.showBack();
            });
            f2.add(imgv2).add("Libelle : ").add(libelle).add("Categorie : ").add(categorie).add("Site : ").add(site).add("Quantite : ").add(quantite).add("Description : ").add(Description).add("Prix : ").add(Prix).add("Etat : ").add(etat).add(acheter);

            f2.show();

        }
        );
        return m;
    }

}
