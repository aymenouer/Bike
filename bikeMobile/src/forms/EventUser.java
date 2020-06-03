/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;


import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import models.Evenement;
import models.Participer;
import models.Session;
import services.Evenement_Service;
import services.Participe_Service;
import services.Vote_Service;

/**
 *
 * @author Rzouga
 */
public class EventUser extends Form{
    List l = new ArrayList<Participer>();
     Resources theme;
     public EventUser(Form previous) throws IOException {  
         
         
          super("Evenements", BoxLayout.y());
      
          theme = UIManager.initFirstTheme("/theme");
            this.getToolbar().setUIID("tb");
        Label logi = new Label("LES Evenements");
        logi.setUIID("login");
        this.add(logi);
           this.getToolbar().addCommandToRightBar(null, theme.getImage("back.png"), ev -> {
            previous.showBack();
        });
      
    
     for(Evenement c:new Evenement_Service().getAllEvents()){
           
 
          this.add(addItem(c));   
          forms.Commentaires.idevent = c.getId();
          System.out.println("Evenement actuelle0"+c.getId());
     
 
        }
     }
     
     
  public Container addItem(Evenement e) throws IOException{
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Container cn3 = new Container(BoxLayout.y());
              Label titre=new Label(e.getTitre());
          Label libelle_titre = new Label("LIBELLE");
          libelle_titre.setUIID("type1");
        Label nbrparticipant=new Label(String.valueOf(e.getNbreparticipants()));
        Label libelle_nbrparticipant = new Label("Nombre Participent");
          libelle_nbrparticipant.setUIID("type1");
        Label nbrPlace=new Label(String.valueOf(e.getNbreplaces()));
         Label libelle_nbrPlace = new Label("Nombre Places");
          libelle_nbrPlace.setUIID("type1");
        Label lieu=new Label(e.getLieu());
        Label libelle_lieu = new Label("Lieu");
          libelle_lieu.setUIID("type1");
        Label discription=new Label(e.getDiscription());
          Label libelle_discription = new Label("Description");
          libelle_discription.setUIID("type1");
        Label dd=new Label(String.valueOf(e.getDateDebut()));
        Label libelle_dd = new Label("Date debut");
          libelle_dd.setUIID("type1");
        Label df=new Label(String.valueOf(e.getDateFin()));
        Label libelle_df = new Label("Date Fin");
          libelle_df.setUIID("type1");
        String image = new String("http://localhost/bike/web/Upload/");
        EncodedImage enc = EncodedImage.create("/giphy.gif");
        Image im = URLImage.createToStorage(enc, "local"+e.getImage() , image+e.getImage());
       

       Button btn=new Button("Details");
        btn.setUIID("vtnvalid");

        cn2.add(libelle_titre).add(titre);
        
        cn2.add(libelle_lieu).add(lieu);
        cn2.add(libelle_discription).add(discription);
        cn2.add(libelle_nbrparticipant).add(nbrparticipant);
        cn2.add(libelle_nbrPlace).add(nbrPlace);
        cn2.add(libelle_dd).add(dd);
        cn2.add(libelle_df).add(df);
        cn3.add(btn);
        cn2.add(cn3);
        cn1.add(BorderLayout.WEST, cn2);
      
        btn.addActionListener(e1->{
        
        Form  f2=new Form("Details",BoxLayout.y());
          Label logi = new Label("Dtails");
        logi.setUIID("login");
        f2.add(logi);
        Label titrem=new Label(e.getTitre());
        System.out.println("hahahaha"+e.getTitre());
        Label nbrparticipantm=new Label(String.valueOf(e.getNbreparticipants()));
        Label nbrPlacem=new Label("noboooo"+String.valueOf(e.getNbreplaces()));
        Label lieum=new Label(e.getLieu());
        Label discriptionm=new Label(e.getDiscription());
        Label ddm=new Label(String.valueOf(e.getDateDebut()));
        Label dfm=new Label(String.valueOf(e.getDateFin()));
        Label note=new Label(String.valueOf(e.getNote()));

        Slider rate = createStarRankSlider();
             Container CR = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Button bou = new Button("Donner une note");
            CR.add(FlowLayout.encloseCenter(rate));
            CR.add(FlowLayout.encloseCenter(bou));
        Button Commenter = new Button("Avis");
        Button participer = new Button("participer");
        Button quitter = new Button("quitter");
          Commenter.setUIID("vtnvalid");
         participer.setUIID("vtnvalid");
            quitter.setUIID("vtnvalid");
               bou.setUIID("vtnvalid");
        String image1 = new String("http://localhost/bike/web/Upload/");
            try {
                EncodedImage enc1 = EncodedImage.create("/giphy.gif");
            } catch (IOException ex) {
            }
        Image im1 = URLImage.createToStorage(enc, "local"+e.getImage() , image+e.getImage());
       
     
            try {
                 l = new Participe_Service().getParticipantByUser(e.getId(),Session.getCurrentSession());
               System.out.println("forms.EventUser.addItem()"+l);

            } catch (Exception ex) {
           System.out.println("ereuuuur");

        }
           
       Commenter.addActionListener(comm ->  
       
       {
            try {
                new Commentaires(this , e.getId()).show();
            } catch (Exception ex) {
            }

       });
        
             participer.addActionListener(part ->  
       {
            try {
                Participer pr = new Participer(Session.getCurrentSession(), e.getId());
                new Participe_Service().Participer(pr);
                new Commentaires(this , e.getId()).show();
            } catch (Exception ex) {
            }


       });
                        quitter.addActionListener(quit ->  
       {
            try {
             
                new Participe_Service().QuiterEvenement(e.getId(),Session.getCurrentSession());
                
              new EventUser(f2).show();
            } catch (Exception ex) {
            }


       });
           
         
           f2.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
             Label lib_titre = new Label("Titre");
                lib_titre.setUIID("pass");
      
              Label lib_Lieu = new Label("Lieu");
                lib_Lieu.setUIID("pass");
   
              Label lib_Description = new Label("Description");
                lib_Description.setUIID("pass");
 
     Label lib_nbr_places = new Label("Nombre de places : ");
                lib_nbr_places.setUIID("pass");
   Label lib_nbr_participant = new Label("Nombre de Participant : ");
                lib_nbr_participant.setUIID("pass");
          Label lib_dd = new Label("Date Debut : ");
                lib_dd.setUIID("pass");
       
          Label lib_df = new Label("Date Fin : ");
                lib_df.setUIID("pass");
                      f2.add(im1).add(lib_titre).add(titrem).add(lib_nbr_participant).add(nbrparticipantm).add(lib_nbr_places).add(nbrPlacem).add(lib_Lieu).add(lieum).add(lib_Description).add(discriptionm).add(lib_dd).add(ddm).add(lib_df).add(dfm).add(note);
                      System.out.println("helllo"+l);
            if(l.size()==0){
             f2.add(participer);
            }
            else{
                f2.add(Commenter);
                f2.add(CR);
            f2.add(quitter);
            }
            bou.addActionListener(ev->{
                int avis = rate.getProgress() / 2;
                Vote_Service vs = new Vote_Service();
                vs.addRate(e.getId(), avis);
                System.out.println(avis);
            try {
                new Commentaires(this , e.getId()).show();
            } catch (Exception ex) {
            }

            });
            f2.show();
         
        });
        cn1.setLeadComponent(btn);
        return cn1;
                
    }
  
  private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }
     private Slider createStarRankSlider() {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(10);
         Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
         Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;
    }
    
    
}

      


