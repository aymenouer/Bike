/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.codename1.ui.ComboBox;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import models.Achat;
import models.Session;
import services.Achat_Service;

/**
 *
 * @author aymen
 */
public class User_Achat_Form extends Form {

    public User_Achat_Form(Form previous, int id) {
        super("Achat", BoxLayout.y());
          Label modif = new Label("Achat ");
                            modif.setUIID("login");
                            
                            this.add(modif);
        Resources theme;
        theme = UIManager.initFirstTheme("/theme");
        ComboBox cmb = new ComboBox("Achat 3 mois", "Achat un mois", "Achat un ans");
        cmb.addActionListener(exa -> {

            if (cmb.getSelectedItem().equals("Achat 3 mois")) {
                Achat ac = new Achat(Session.get().getId(), id);
                new Achat_Service().addAchat_trois_mois(ac);

                new Achat_OffreForm(this).showBack();

            }
            if (cmb.getSelectedItem().equals("Achat un mois")) {
                Achat ac = new Achat(Session.get().getId(), id);
                new Achat_Service().addAchat_un_mois(ac);

                new Achat_OffreForm(this).showBack();

            }
            if (cmb.getSelectedItem().equals("Achat un ans")) {
                Achat ac = new Achat(Session.get().getId(), id);
                new Achat_Service().addAchat_un_ans(ac);

                new Achat_OffreForm(this).showBack();

            }

        });
         Label Choix = new Label("Choix : ");
Choix.setUIID("pass");
this.add(Choix);
        this.add(cmb);
            this.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                previous.showBack();
            });
        this.show();
    }

}
