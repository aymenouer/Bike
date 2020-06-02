/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VeloForm;

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
import forms.Choix_categorie_Produit;
import forms.UserInterfaceForm;
import models.Reservation;
import services.Reservation_Service;
import services.Velo_Service;

/**
 *
 * @author asus
 */
public class MesReservationsForm extends Form {

    Resources theme;

    public MesReservationsForm(Form previous) throws Exception {
        super( BoxLayout.y());
   Label logi = new Label("MES RESERVATIONS");
         logi.setUIID("login");
         this.add(logi);
         
        theme = UIManager.initFirstTheme("/theme");
        this.getToolbar().setUIID("tb");
        this.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), ev -> {
            new UserInterfaceForm(previous).showBack();
        });
        float x=0;
        for (Reservation c : new Reservation_Service().getAllReservations()) {
            System.out.println(c);
            System.out.println(new Velo_Service().get_velo(c.getIdV()));
            this.add(addItem(c));
            x=x+c.getPrix();
        }
        System.out.print(x);
          Label a = new Label("TOTAL "+String.valueOf(x));
         a.setUIID("type1");
      //   this.add(logi);
        this.add(a);
    }

    public Container addItem(Reservation c) {

        Velo_Service a = new Velo_Service();

        Container cn1 = new Container(new BorderLayout());
        Container cn2 = new Container(BoxLayout.y());
        Label lab = new Label(a.get_libC(c.getIdV()));
        

        Button btn = new Button(a.get_libC(c.getIdV()));
lab.setUIID("libC");
        btn.setUIID("btn");
     
        String url = "http://localhost/bike/web/uploads/images/" + a.get_Image(c.getIdV());
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

            Form f2 = new Form( BoxLayout.y());
              Label logi = new Label("DETAILS");
         logi.setUIID("login");
         f2.add(logi);
            Label type1=new Label("LIBELLE");
            Label libelle = new Label(a.get_libC(c.getIdV()));
            Label type2=new Label("IDR");
            Label idr = new Label(String.valueOf(c.getIdR()));
            Label type3=new Label("PRIX");
            Label prix = new Label(String.valueOf(c.getPrix()));
            Label type4=new Label("DATE DEBUT");
            Label Dated = new Label(String.valueOf(c.getDATE_D()));
            Label type5=new Label("DATE FIN");
            Label DateF = new Label(String.valueOf(c.getDATE_F()));
            
            type1.setUIID("type1");
            type2.setUIID("type1");
            type3.setUIID("type1");
            type4.setUIID("type1");
            type5.setUIID("type1");
       
            
            libelle.setUIID("type2");
            idr.setUIID("type2");
            prix.setUIID("type2");
            Dated.setUIID("type2");
            DateF.setUIID("type2");
            
         
            Button btn_supp = new Button(theme.getImage("remove.png"));
            btn_supp.setUIID("rem");
            btn_supp.addActionListener(ev
                    -> {

                if (new Reservation_Service().removeReservation(c.getIdR()) == true) {
                    Dialog.show("Suppression Reservation", "suppression aves success ", "OK", null);
                    new UserInterfaceForm(this).show();

                } else {
                    Dialog.show("Erreur", "Erreur suppression ", "OK", null);
                }

            }
            );
f2.getToolbar().setUIID("tb");
            f2.getToolbar().addCommandToLeftBar(null,theme.getImage("back.png"), (evt) -> {
                this.show();
            });
            f2.add(type2).add(idr).add(type1).add(libelle).add(type3).add(prix).add(type4).add(Dated).add(type5).add(DateF).add(btn_supp);

            f2.show();

        });

        cn1.setLeadComponent(btn);
        return cn1;
    }

}
