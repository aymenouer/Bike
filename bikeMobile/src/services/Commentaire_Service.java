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
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.Format;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import models.Commentaire;
import models.Evenement;
import models.User;
import utils.DataSource;
import utils.Statics;

/**
 *
 * @author Rzouga
 */
public class Commentaire_Service {
    
       private ConnectionRequest request;
    private boolean responseResult;
    public ArrayList<Commentaire> Comments;

    public Commentaire_Service() {
            request = DataSource.getInstance().getRequest();

    }
       public boolean addEvent(Commentaire commentaire) {
     
        String url = Statics.BASE_URL + "api/Comments/new/"+commentaire.getIdEvt()+"/"+commentaire.getId()+"?commentaire="+ commentaire.getComment();
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
            public ArrayList<Commentaire> getAllCommentsEvent(int id) {
        String url = Statics.BASE_URL + "api/Comments/All/"+id;

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    Comments = parseCommentaire(new String(request.getResponseData()));
                    request.removeResponseListener(this);
                } catch (ParseException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Comments;
    }
            
                    
          public ArrayList<Commentaire> parseCommentaire(String jsonText) throws ParseException {
        try {
            Comments = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
             
            int id = (int)Float.parseFloat(obj.get("id").toString());
            String commentaire = obj.get("commentaire").toString();
           
            Map map = ((Map) obj.get("idUser")) ;
            Map map1 = ((Map) obj.get("idevenement"));
            Double idUser = (Double) map.get("id");
            Double idEvent = (Double) map1.get("id");
         
                  
                Comments.add(new Commentaire(idEvent.intValue(), idUser.intValue(), id, commentaire));
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return Comments;
    }
          
          
          
          public boolean DeleteCommentaire(int id) {
     
        String url = Statics.BASE_URL + "api/Comments/delete/"+id;

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
     
       public boolean ModifierComment(Commentaire c) {
     
        String url = Statics.BASE_URL + "api/Comments/update/"+c.getIdComment()+"?commentaire="+c.getComment();
      
      //  http://localhost/bike/web/app_dev.php/api/Comments/update/18?commentaire=ahhahh
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
       
  
}
