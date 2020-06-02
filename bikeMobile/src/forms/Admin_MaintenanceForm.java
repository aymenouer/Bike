/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import models.Maitenance;
import services.Maintenance_Service;

/**
 *
 * @author aymen
 */
public class Admin_MaintenanceForm extends Form {
    Resources theme = UIManager.initFirstTheme("/theme");
 public Admin_MaintenanceForm(Form previous) {
               super("Mainetance", BoxLayout.y());
              
               
                       this.add(new InfiniteProgress());
Display.getInstance().scheduleBackgroundTask(()-> {
    // this will take a while...

    Display.getInstance().callSerially(() -> {
        this.removeAll();
    
        try {
            for(Maitenance c : new Maintenance_Service().getAllMaintenance_Admin()) {
                
                
                System.out.println(c);
                
                this.add(addIteam_Maintenance(c));
            }
        } catch (Exception ex) {
           
        }
       
       
        this.revalidate();
    });
});
        
        

    this.getToolbar().addCommandToLeftBar("back", theme.getImage("back-command.png"), ev -> {
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

            Form f2 = new Form("Details", BoxLayout.y());
      
            Label problem = new Label(c.getProblem());
            Label type = new Label(c.getType());
            Label etat = new Label(c.getEtat());
            Label dated = new Label(String.valueOf(c.getDATE_D()));
            Label datef = new Label(String.valueOf(c.getDATE_F()));
              Label Prix= new Label("");
              Button sup = new Button("supprimer");
              
                 TextField prix = new TextField("", "Prix", 20, TextArea.NUMERIC);
               Button affecter = new Button("affecter");
               String url2 = "http://localhost/bike/web/uploads/images/Maitenance/" + c.getImage();
            ImageViewer imgv2;
            Image imge2;
            EncodedImage enc2;
            enc2 = EncodedImage.createFromImage(theme.getImage("round.png"), false);
            imge2 = URLImage.createToStorage(enc, url, url);
            imgv2 = new ImageViewer(imge2);
            if (c.getPrix()==0)
            {
                Prix.setText("pas encore");
            }
            else
            {
               Prix.setText(String.valueOf(c.getPrix()));
            }
           if (c.getEtat().equals("repare")) {
                  etat.getStyle().setFgColor(0x1b7021);
                
            }
                  else if (c.getEtat().equals("accept") ||c.getEtat().equals("en cours de reparation") ) {
                  etat.getStyle().setFgColor(0xFFA500);
                
            }
                  else {
               etat.getStyle().setFgColor(0xd61f1f);
            }
           
           
         
              sup.addActionListener(supq -> 
              
              {
                try {
                    new Maintenance_Service().DeleteMaitenance(c);
                } catch (Exception ex) {
  
                }
              }
              
              );
         

            f2.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
                this.showBack();
            });
            f2.add(imgv2).add("Type : ").add(type).add("Problem : ").add(problem).add("etat : ").add(etat).add("Date Debut : ").add(dated).add("Date Fin : ").add(datef).add("Prix : ").add(Prix).add(sup);
                    
                if (c.getPrix()==0)
            {
                f2.add("prix :").add(prix).add(affecter);

            }
affecter.addActionListener(af ->

{
    
    c.setPrix(Float.valueOf(prix.getText()));
    new Maintenance_Service().UpdateMaitenance(c, 1);
    new Admin_MaintenanceForm(f2).showBack();
    
}
);
            f2.show();

        }
                    
                    );
                    return m;
    }


}
