/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
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
import models.Session;
import models.User;
import services.User_Service;

/**
 *
 * @author aymen
 */
public class ListUsersForm extends Form {

    Resources theme;

    public ListUsersForm(Form previous) {
        super(BoxLayout.y());
        this.getToolbar().setUIID("tb");
        Label logi = new Label("LES UTILISATEURS");
        logi.setUIID("login");
        this.add(logi);

        theme = UIManager.initFirstTheme("/theme");

        for (User c : new User_Service().getAllusers()) {

            if (c.getRole().equals("[ROLE_USER]")) {
                this.add(addItem(c));
            }

        }

        this.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evt) -> {
            new AccueilAdminForm(this).show();
        });
    }

    public Container addItem(User c) {
        Container cn1 = new Container(new BorderLayout());
        Container cn2 = new Container(BoxLayout.y());
        Label lab = new Label(c.getFname());
        Button btn = new Button(c.getMail());
        lab.setUIID("libC");
        btn.setUIID("btn");
        String url = "http://localhost/bike/web/uploads/images/" + c.getImage();
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

            Form f2 = new Form(BoxLayout.y());
            Label AJOUT = new Label("DETAILS");
            AJOUT.setUIID("login");
            f2.add(AJOUT);

            Label USERNAME = new Label("USERNAME");
            Label lname = new Label(c.getLname());
            Label USERMAIL = new Label("USERMAIL");
            Label lemail = new Label(c.getMail());
            Label USERNUMBER = new Label("USERNUMBER");
            Label lnum = new Label(String.valueOf(c.getNumber()));
            Label USERAGE = new Label("USERAGE");
            Label lage = new Label(String.valueOf(c.getAge()));

           

            lname.setUIID("type2");
            lemail.setUIID("type2");
            lnum.setUIID("type2");
            lage.setUIID("type2");
            USERNAME.setUIID("type1");
            USERMAIL.setUIID("type1");
            USERNUMBER.setUIID("type1");
            USERAGE.setUIID("type1");

            String url2 = "http://localhost/bike/web/uploads/images/" + c.getImage();
            ImageViewer imgv2;
            Image imge2;
            EncodedImage enc2;
            enc2 = EncodedImage.createFromImage(theme.getImage("round.png"), false);
            imge2 = URLImage.createToStorage(enc, url, url);
            imgv2 = new ImageViewer(imge2);
            Button btn_desactiver_activer;
            
            if (c.getEnabled() == 1) {
                btn_desactiver_activer = new Button("DESACTIVER");
            } else {
                btn_desactiver_activer = new Button("ACTIVER");
            }
            
btn_desactiver_activer.setUIID("vtnvalid");
            btn_desactiver_activer.addActionListener(l
                    -> {
                if (c.getEnabled() == 1) {

                    new User_Service().DesactiverUser(c.getId());
                    Dialog.show("Desactivation", "Desactivation aves succsess ", "OK", null);

                    new ListUsersForm(f2).show();

                } else {
                    new User_Service().activerUser(c.getId());
                    Dialog.show("activation", "Activation aves succsess ", "OK", null);

                    new ListUsersForm(f2).show();

                }

            }
            );
           f2.getToolbar().setUIID("tb");
            f2.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evt) -> {
                this.show();
            });
           
            f2.add(USERNAME).add(lname).add(USERMAIL).add(lemail).add(USERNUMBER).add(lnum).add(USERAGE).add(lage).add(imgv2).add(btn_desactiver_activer);
            f2.show();

        });

        cn1.setLeadComponent(btn);
        return cn1;

    }

}
