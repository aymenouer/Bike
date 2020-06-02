/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.codename1.codescan.CodeScanner;
import com.codename1.codescan.ScanResult;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

import models.Session;
import org.littlemonkey.qrscanner.QRScanner;

/**
 *
 * @author aymen
 */
public class LoginForm extends Form {

    Form h = this;
    Resources theme;

    public LoginForm() {
        super( BoxLayout.y());

        String url = "http://127.0.0.1/bike/mobile/cnx.php";
        theme = UIManager.initFirstTheme("/theme");
        this.getAllStyles().setBgColor(0xffffff);
        Label img = new Label(theme.getImage("LOGO_BLACK.png"));
        TextField txtn, txtpass;
        Button btnvalid, btninscrire;

        img.setUIID("img");
        Label logi = new Label("ACCOUNT LOGIN");
        logi.setUIID("login");
        Label labUser = new Label("USERNAME");
        labUser.setUIID("aaa");
        txtn = new TextField("", "Username", 5, TextArea.ANY);
        txtn.setUIID("txtn");
        Label labpASSWORD = new Label("PASSWORD");
        labpASSWORD.setUIID("pass");
        txtpass = new TextField("", "Password", 5, TextArea.PASSWORD);
        txtpass.setUIID("txtpass");
        btnvalid = new Button("Valider");
        btnvalid.setUIID("vtnvalid");
        btninscrire = new Button("Sign up");
        btninscrire.setUIID("btnins");

        Label a = new Label("                                      ");
        Label b = new Label("    ");
        a.setUIID("aa");
        //   b.setUIID("bb");

        this.add(logi).add(img).add(labUser).add(txtn).add(labpASSWORD).add(txtpass).add(b).add(btnvalid).add(a).add(btninscrire);
                //.add(new ScaleImageLabel(GifImage.decode(getResourceAsStream("accueil.gif"), 1177720)));

       

        btninscrire.addActionListener(l
                -> {
            new SignUpForm(this).show();
        }
        );

        btnvalid.addActionListener(e -> {
            ConnectionRequest cnreq = new ConnectionRequest();
            cnreq.setPost(false);
            cnreq.addArgument("name", txtn.getText());
            cnreq.addArgument("password", txtpass.getText());
            cnreq.setUrl(url);
            cnreq.addResponseListener(ev -> {
                String chaine = new String(cnreq.getResponseData());
                System.out.println(chaine);
                if (chaine.equalsIgnoreCase("-1")) {

                    Dialog.show("Erreur", "Verifier votre USername and password", "OK", null);

                } else {
                    int id = Integer.valueOf(chaine);
                    Session.start(id);
                    System.out.println(Session.get());

                    if (Session.get().getEnabled() == 1) {

                        if (Session.get().getRole().equals("[ROLE_USER]")) {

                            new UserInterfaceForm(this).show();

                        } else {

                            new AccueilAdminForm(this).show();

                        }

                    } else {
                        Dialog.show("Erreur", "Compte Desactive", "OK", null);
                    }

                }

            });
            NetworkManager.getInstance().addToQueueAndWait(cnreq);
        });

    }

}
