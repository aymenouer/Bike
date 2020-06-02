/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import models.Abonnement;
import models.Achat;
import models.Rating_Produit;
import models.Session;
import services.Abonnment_Service;
import services.Achat_Service;
import services.RateProduit_Service;

/**
 *
 * @author aymen
 */
public class Achat_OffreForm extends Form {
 Resources theme=UIManager.initFirstTheme("/theme");
    public Achat_OffreForm(Form previous) {

        super(BoxLayout.y());
         Label logi = new Label("MES OFFRES");
        logi.setUIID("login");
        this.add(logi);
       
        Achat achat = new Achat_Service().verif_Achat(Session.get().getId());
         this.getToolbar().setUIID("tb");
        this.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), ev -> {
            new UserInterfaceForm(previous).showBack();
        });
        if (achat == null) {

             Label login = new Label("VOUS POUVEZ ACHETER UN ABONNEMENT");
        login.setUIID("logi");
        this.add(login);

            Button passer = new Button("Products");
            this.add(passer);
            passer.addActionListener(l
                    -> {
                new UserInterfaceForm(this).show();
            }
            );

        } else {
            

            Abonnement ab = new Abonnment_Service().get_Abonnement(achat.getID_A());
            
            Label type1=new Label("LIBELLE");
            Label libelle = new Label(ab.getLibelle());
            
            Label type2=new Label("LIBELLE CATEGORIE");
            Label categorie = new Label(ab.getLib_C());
            
            Label type3=new Label("LIBELLE SITE");
            Label site = new Label(ab.getLib_S());
            
            Label type4=new Label("QUANTITE");
            Label quantite = new Label(String.valueOf(ab.getQuantite()));
            
            Label type5=new Label("DESCRIPTION");
            Label Description = new Label(ab.getDescription());
            
            Label type6=new Label("PRIX");
            Label Prix = new Label(String.valueOf(ab.getPrix()));
            
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
            String url2 = "http://localhost/bike/web/uploads/images/qruser/" + achat.getImage();
            ImageViewer imgv2;
            Image imge2;
            EncodedImage enc2;
            enc2 = EncodedImage.createFromImage(theme.getImage("round.png"), false);
            imge2 = URLImage.createToStorage(enc2, url2, url2);
            imgv2 = new ImageViewer(imge2);

            Slider rate = new Rating_produit().createStarRankSlider();

            Button btn_sup = new Button(theme.getImage("remove.png"));
            btn_sup.setUIID("rem");
           
  this.add(type1).add(libelle).add(type2).add(categorie).add(type3).add(site).add(type4).add(quantite).add(type5).add(Description).add(type6).add(Prix).add(imgv2);

            try {
                Rating_Produit rate_p = new RateProduit_Service().verif_Rate(ab.getId_p());
                   System.out.println("eeeeee"+rate_p);
                System.out.println();
                if (rate_p == null) {
                    Label A=new Label("RATE");
                    A.setUIID("type1");
                    this.add(A);
                    
                    this.add(FlowLayout.encloseCenter(rate));
                    Button btn_rate = new Button("Rate");
                     btn_rate.setUIID("vtnvalid");
                     this.add(btn_sup);
                      this.add(btn_rate);
                      
                      
                      btn_rate.addActionListener(lw->
                      {
                         
                       
                          
                        try {
                            new RateProduit_Service().addRate(new Rating_Produit(ab.getId_p(), rate.getProgress()));
                      Dialog.show("Rate", "Merci pour votre vote", "OK", null);
                      
                      new Achat_OffreForm(previous).show();
                        
                        } catch (Exception ex) {
                      
                        }
                          
                          
                      }
                      );
                } else {
                    
                    
                    rate.setEditable(false);
                    rate.setProgress(rate_p.getVote());
                    this.add("Merci pour votre Rate  : ");
                   
                    this.add(FlowLayout.encloseCenter(rate));
                  
                }

            } catch (Exception e) {

            }

            

            btn_sup.addActionListener(sss
                    -> {
                new Achat_Service().DeleteAchat(achat.getID_U());
                Dialog.show("Remove ", "suppression de l abonnment", "OK", null);
                new UserInterfaceForm(this).show();
            }
            );
        }

        this.show();

    }

}
