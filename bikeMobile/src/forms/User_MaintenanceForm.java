/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import ca.weblite.codename1.components.ckeditor.CKeditor;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.datatransfer.DropTarget;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.Date;
import models.Maitenance;
import services.Maintenance_Service;

/**
 *
 * @author aymen
 */
public class User_MaintenanceForm extends Form  {
Resources theme = UIManager.initFirstTheme("/theme");
    public User_MaintenanceForm(Form previous) {
               super( BoxLayout.y());
               Label logi = new Label("MAINTENANCE");
        logi.setUIID("login");
      
                     this.getToolbar().addCommandToOverflowMenu("Add Maintenance", null, ev -> {

            Form ajout = new Form( BoxLayout.y());
Label login = new Label("ADD MAINTENANCE");
        login.setUIID("login");
        ajout.add(login);
            CKeditor editor = new CKeditor();
            ComboBox Type = new ComboBox( "Accessoire", "Piece", "Velo" , "Others");
            Type.setUIID("txtn");
            editor.initLater();

             Label AA = new Label("TYPE");
           AA.setUIID("pass");
            ajout.add(AA).add(Type);
ajout.getToolbar().setUIID("tb");
            ajout.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
             Label PHOTO = new Label("DRAG YOUR PHOTO HERE");
            PHOTO.setUIID("pass");
            ajout.addComponent(PHOTO);

            if (DropTarget.isSupported()) {

                DropTarget dnd = DropTarget.create((evt) -> {

                    String srcFile = (String) evt.getSource();
                    System.out.println("Src file is " + srcFile);

                    System.out.println("Location: " + evt.getX() + ", " + evt.getY());
                    if (srcFile != null) {
                        try {

                            Image img = Image.createImage(FileSystemStorage.getInstance().openInputStream(srcFile)).scaled(300, 300);

                            String nomImage = srcFile.substring(19, srcFile.length());
                         

                            ajout.add(img);
                             
                               ajout.getToolbar().addCommandToOverflowMenu("Add Maintenance", null, evx -> {

         

                                if (editor.getData().equals("")) {
                                    Dialog.show("erreur", "Problem vide", "OK", null);
                                } else {
                                    String contenu = editor.getData();

                                    contenu = contenu.substring(3);

                                    contenu = contenu.substring(0, contenu.length() - 5);

                                    
                                    Maitenance m = new Maitenance(0, 0, contenu, "non", nomImage, Type.getSelectedItem().toString(), new Date(), new Date());
                                    try {
                              new Maintenance_Service().addMaitenance(m);
                                        Dialog.show("Ajout", "Ajout avec success", "OK", null);
                                        new User_ReclamationForm(previous).showBack();

                                    } catch (Exception ex) {
                                        System.out.println(ex.getMessage());
                                    }
                                }

                            });
                            ajout.add("Problem : ").add(editor);
                            ajout.revalidate();

                        

                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }

                    }

                }, Display.GALLERY_IMAGE);
            }

            ajout.show();

        });

              
               
                       this.add(new InfiniteProgress());
Display.getInstance().scheduleBackgroundTask(()-> {
    // this will take a while...

    Display.getInstance().callSerially(() -> {
        this.removeAll();
     // this.add(logi);
        try {
            for(Maitenance c : new Maintenance_Service().getAllMaintenance_User()) {
                
                
                System.out.println(c);
                
                this.add(addIteam_Maintenance(c));
            }
        } catch (Exception ex) {
           
        }
       
       
        this.revalidate();
    });
});
        
        

    this.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), ev -> {
                previous.showBack();
            });
        
        
        
        this.getToolbar().addSearchCommand(e -> {
    String text = (String)e.getSource();
    if(text == null || text.length() == 0) {
        // clear search
        for(Component cmp : this.getContentPane()) {
            cmp.setHidden(false);
            cmp.setVisible(true);
        }
        this.getContentPane().animateLayout(150);
    } else {
        text = text.toLowerCase();
        for(Component cmp : this.getContentPane()) {
            MultiButton mb = (MultiButton)cmp;
            String line1 = mb.getTextLine1();
            String line2 = mb.getTextLine2();
            boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
                    line2 != null && line2.toLowerCase().indexOf(text) > -1;
            mb.setHidden(!show);
            mb.setVisible(show);
        }
        this.getContentPane().animateLayout(150);
    }
}, 4);
               
    }
    
       public MultiButton addIteam_Maintenance(Maitenance c)
    {
                           MultiButton m = new MultiButton();
              
                
                   
                  
                       String  url = "http://localhost/bike/web/uploads/images/Maitenance/"+c.getImage();
                     
         
                    

       m.setTextLine1(c.getType());
                    m.setTextLine2(c.getEtat());
                   
                    m.setEmblem(theme.getImage("arrow.png"));
        Image imge;
        EncodedImage enc;
         enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
        imge = URLImage.createToStorage(enc, url, url);
          m.setIcon(imge);
                    m.addActionListener(l -> 
                    
                    {

            Form f2 = new Form( BoxLayout.y());
       Label logia = new Label("DETAILS");
        logia.setUIID("login");
        f2.add(logia);
        Label type1 = new Label("PROBLEME");
            Label problem = new Label(c.getProblem());
            Label type2 = new Label("TYPE");
            Label type = new Label(c.getType());
            Label type3 = new Label("ETAT");
            Label etat = new Label(c.getEtat());
            Label type4 = new Label("DATE DEBUT");
            Label dated = new Label(String.valueOf(c.getDATE_D()));
            Label type5 = new Label("DATE FIN");
            Label datef = new Label(String.valueOf(c.getDATE_F()));
            Label type6 = new Label("PRIX");
              Label Prix= new Label("");
              Button sup= new Button(theme.getImage("remove.png"));
              sup.setUIID("rem");
              Button accepter= new Button("accepter");
              accepter.setUIID("vtnvalid");
              
              type1.setUIID("type1");
            type2.setUIID("type1");
            type3.setUIID("type1");
type4.setUIID("type1");type5.setUIID("type1");type6.setUIID("type1");
            type.setUIID("type2");
            problem.setUIID("type2");Prix.setUIID("type2");
            etat.setUIID("type2"); dated.setUIID("type2"); datef.setUIID("type2");
               String url2 = "http://localhost/bike/web/uploads/images/Maitenance/" + c.getImage();
            ImageViewer imgv2;
            Image imge2;
            EncodedImage enc2;
            enc2 = EncodedImage.createFromImage(theme.getImage("round.png"), false);
            imge2 = URLImage.createToStorage(enc, url, url);
            imgv2 = new ImageViewer(imge2);
            if (c.getPrix()==0)
            {
                Prix.setText("en attendent l acceptation de l admin");
            }
            else
            {
               Prix.setText(String.valueOf(c.getPrix()));
            }
                  if (c.getEtat().equals("repare")) {
                  etat.getStyle().setFgColor(0x1b7021);
                
            }
                  else if (c.getEtat().equals("accept") ||c.getEtat().equals("en cours de reparation")  ) {
                  etat.getStyle().setFgColor(0xFFA500);
                
            }
                  else {
               etat.getStyle().setFgColor(0xd61f1f);
            }
           
       
                  
   
f2.getToolbar().setUIID("tb");
            f2.getToolbar().addCommandToLeftBar(null,theme.getImage("back.png"), (evt) -> {
                this.showBack();
            });
            f2.add(imgv2).add(type2).add(type).add(type1).add(problem).add(type3).add(etat).add(type4).add(dated).add(type5).add(datef).add(type6).add(Prix);
              if (c.getPrix()!=0 && c.getEtat().equals("accept"))       
             {
               f2.add(accepter);  
                 
       f2.add(sup);
            }
                
              accepter.addActionListener(acq -> 
              
              {
                try {
                    new Maintenance_Service().UpdateMaitenance(c,0);
                    new UserInterfaceForm(this).showBack();
                } catch (Exception ex) {
  
                }
              }
              
              );
              sup.addActionListener(supq -> 
              
              {
                try {
                    new Maintenance_Service().DeleteMaitenance(c);
                    new UserInterfaceForm(this).showBack();
                } catch (Exception ex) {
  
                }
              }
              
              );
              

            f2.show();

        }
                    
                    );
                    return m;
    }
    
}
