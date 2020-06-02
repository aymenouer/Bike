/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.codename1.components.ScaleImageLabel;
import com.codename1.gif.GifImage;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.getResourceAsStream;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;

import java.io.IOException;

/**
 *
 * @author aymen
 */
public class welcomeForm extends Form {

    public welcomeForm() {
          super("Welcome",  new BorderLayout());
       
          Button sign = new Button("Sign");
       try {
    //this.add(CENTER, new ScaleImageLabel(GifImage.decode(getResourceAsStream("/accueil.gif"), 1177720)));

  
       } catch(Exception err) {
           System.out.println(err.getMessage());
} 
          this.add(BOTTOM, sign); 
       sign.addActionListener(ll -> 
       {
           new LoginForm().show();
       }
       
       
       );
       
       
    }
    
    
    
}
