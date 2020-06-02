/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import VeloForm.listVelo;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author aymen
 */
public class Choix_categorie_Produit extends Form {

    public Choix_categorie_Produit(Form previous) {

        super(BoxLayout.y());
        Resources theme;
        theme = UIManager.initFirstTheme("/theme");

        Button catgorie = new Button(theme.getImage("categorie.png"));
        Button Abonnment = new Button(theme.getImage("abo.png"));
        Button Piece = new Button(theme.getImage("piece.png"));
        Button Accessoire = new Button(theme.getImage("acc.png"));
        Button Velo = new Button(theme.getImage("bike.png"));

        Abonnment.setUIID("btnre");
        catgorie.setUIID("btnre");
        Piece.setUIID("btnre");
        Accessoire.setUIID("btnre");
        Velo.setUIID("btnre");

        Label labcatgorie = new Label("CATEGORIE");
        Label labAbonnment = new Label("ABONNEMENT");
        Label labPiece = new Label("PIECE");
        Label labAccessoire = new Label("ACCESSOIRES");
        Label labVelo = new Label("VELO");

        labcatgorie.setUIID("rescf");
        labAbonnment.setUIID("rescc");
        labPiece.setUIID("resce");
        labAccessoire.setUIID("rescc");
        labVelo.setUIID("rescd");
//        this.getToolbar().getLeftSideMenuButton().setUIID("tb");
        this.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evt) -> {
            new AccueilAdminForm(this).show();
        });
        Abonnment.addActionListener(abo
                -> {

            new addAbonnmentForm(this).show();

        }
        );

        Accessoire.addActionListener(abo
                -> {

            new addAccessoireForm(this).show();

        }
        );
        Velo.addActionListener(l -> {
            new listVelo(this).show();
        });

        Piece.addActionListener(abo
                -> {

            new addPieceForm(this).show();

        }
        );

        this.add(catgorie);
        this.add(labcatgorie);
        this.add(Abonnment);
        this.add(labAbonnment);
        this.add(Piece);
        this.add(labPiece);
        this.add(Accessoire);
        this.add(labAccessoire);
        this.add(Velo);
        this.add(labVelo);

        catgorie.addActionListener(c
                -> {
            new addCategorieForm(this).show();
        }
        );

        this.show();
        this.getToolbar().setUIID("tbb");
        this.getToolbar().addCommandToRightBar(null, theme.getImage("log.png"), ev -> {
            new LoginForm().showBack();
        });
    }

}
