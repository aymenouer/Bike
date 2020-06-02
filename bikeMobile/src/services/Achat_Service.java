/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import models.Achat;
import utils.DataSource;
import utils.Statics;

/**
 *
 * @author aymen
 */
public class Achat_Service {
      private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Achat> Achats;
     public Achat_Service() {
        request = DataSource.getInstance().getRequest();
    }

   public boolean addAchat_un_mois (Achat achat) {
     
        String url = Statics.BASE_URL + "api/Achat/mois?ID_U=" + achat.getID_U()+ "&ID_A=" + achat.getID_A();

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
   public boolean addAchat_un_ans (Achat achat) {
     
        String url = Statics.BASE_URL + "api/Achat/un_ans?ID_U=" + achat.getID_U()+ "&ID_A=" + achat.getID_A();

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

   public boolean addAchat_trois_mois (Achat achat) {
     
        String url = Statics.BASE_URL + "api/Achat/trois_mois?ID_U=" + achat.getID_U()+ "&ID_A=" + achat.getID_A();

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

  
     
    
     
       public boolean DeleteAchat(int id) {
     
        String url = Statics.BASE_URL + "api/Achat/remove?idU=" + id;

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
     
     
     
     
     
     
     
    public ArrayList<Achat> getAllAchats() {
        String url = Statics.BASE_URL + "api/Achat/all";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    Achats = parseAchats(new String(request.getResponseData()));
                } catch (ParseException ex) {
               
                }
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Achats;
    }

    public ArrayList<Achat> parseAchats(String jsonText) throws ParseException {
        
        try {
            Achats = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) 
            {           
            
            
            String image = obj.get("image").toString();
                System.out.println(image);
              int id = (int)Float.parseFloat(obj.get("id").toString()); 
             int ID_A = (int)Float.parseFloat(obj.get("idA").toString());  
            
            int ID_U = (int)Float.parseFloat(obj.get("idU").toString()); 
            
            

         
            
            Float prix = Float.parseFloat(obj.get("prix").toString());
            
         
            
            Achats.add(new Achat(id, ID_U, ID_A, prix, image));
              
            
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return Achats;
    }
 
      public Achat  verif_Achat(int id_U)
      {
          for( Achat u :getAllAchats()  )
          {
              if (u.getID_U()==id_U)
              {
                  return u;
              }
          }
          return null;
  
      }
    
}
