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
import models.Commentaire;
import models.Evenement;
import models.Participer;
import models.Vote;
import utils.DataSource;
import utils.Statics;

/**
 *
 * @author Rzouga
 */
public class Participe_Service {
       private ConnectionRequest request;
    private boolean responseResult;
    public ArrayList<Participer> participers;

    public Participe_Service() {
            request = DataSource.getInstance().getRequest();

    }
    
         public boolean Participer(Participer p) {
     
        String url = Statics.BASE_URL + "api/Participe/new/"+p.getIdEvent()+"/"+p.getIdUuser();
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
         
         
            public boolean QuiterEvenement(int ide, int idu) {
     
        String url = Statics.BASE_URL + "api/Participe/delete/"+ide+"/"+idu;

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
            
     public ArrayList<Participer> getParticipantByUser(int ide , int idu) {
        String url = Statics.BASE_URL + "api/Participe/find/"+ide+"/"+idu;

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    participers = parseEvenement(new String(request.getResponseData()));
                    request.removeResponseListener(this);
                } catch (ParseException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return participers;
    }
     
                
          public ArrayList<Participer> parseEvenement(String jsonText) throws ParseException {
        try {
            participers = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
             
            int id = (int)Float.parseFloat(obj.get("id").toString());
           
            Map map = ((Map) obj.get("idUser")) ;
            Map map1 = ((Map) obj.get("idevenement"));
            Double idUser = (Double) map.get("id");
            Double idEvent = (Double) map1.get("id");
         
                  
                participers.add(new Participer(id,idUser.intValue(),idEvent.intValue() ));
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return participers;
    }
         
}
