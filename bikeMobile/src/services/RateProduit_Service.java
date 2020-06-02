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
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import models.Rating_Produit;
import models.Session;
import utils.DataSource;
import utils.Statics;

/**
 *
 * @author aymen
 */
public class RateProduit_Service {
         private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Rating_Produit> Rates;
     public RateProduit_Service() {
        request = DataSource.getInstance().getRequest();
    }
       public boolean addRate(Rating_Produit rate) throws Exception {
    
        String url = Statics.BASE_URL + "api/rates/new?ID_U="+Session.getCurrentSession()+"&idP="+rate.getID_P()+"&rate="+rate.getVote();
           System.out.println(url);
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
     
    public ArrayList<Rating_Produit> getAllRates() {
        String url = Statics.BASE_URL + "api/rates/verif";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    Rates = parseRates(new String(request.getResponseData()));
                } catch (Exception ex) {
               
                }
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Rates;
    }

    public ArrayList<Rating_Produit> parseRates(String jsonText)  {
        
        try {
            Rates = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) 
            {           
            
            
          
              int idR = (int)Float.parseFloat(obj.get("idR").toString()); 
             int idU = (int)Float.parseFloat(obj.get("idU").toString());  
            
            int idP = (int)Float.parseFloat(obj.get("idP").toString()); 
             int vote = (int)Float.parseFloat(obj.get("vote").toString()); 
            

         
            
          
            
         
            
            Rates.add(new Rating_Produit(idR, idU, idP, vote));
              
            
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return Rates;
    }
 
      public Rating_Produit  verif_Rate(int id_P) throws Exception
      {
        
          for( Rating_Produit u :getAllRates())
          {
         
              if (u.getID_P()==id_P && u.getID_U()==Session.getCurrentSession())
              {
                  return u;
              }
          }
          return null;
  
      }
}
