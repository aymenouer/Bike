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
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import models.User;
import models.Vote;
import utils.DataSource;
import utils.Statics;

/**
 *
 * @author Rzouga
 */
public class Vote_Service {
   
    private ConnectionRequest request;
    private boolean responseResult;
    public ArrayList<Vote> Votes;

    public Vote_Service() {
            request = DataSource.getInstance().getRequest();

    }
    
     public boolean Like(Vote v) {
     
        String url = Statics.BASE_URL + "api/Vote/like/"+v.getId_comment()+"/"+v.getId_client();
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
     
         
     public boolean DesLike(Vote v) {
     
        String url = Statics.BASE_URL + "api/Vote/deslike/"+v.getId_comment()+"/"+v.getId_client();
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
     
     public void addRate(int event , int Note){
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = Statics.BASE_URL + "api/Vote/rate";
        ConnectionRequest con = new ConnectionRequest();
        
    
    
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     con.addArgument("rate", Note+"");
     con.addArgument("evenement", event+"");
//     con.addArgument("user", MyApplication.iduser+"");
     
     con.setPost(true);
     System.out.println(url);
     con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "Vous avez déja ajouté votre avis", "Ok", null);
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
     
     
     
            public ArrayList<Vote> getAllVote() {
        String url = Statics.BASE_URL + "api/Vote/All";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    Votes = parseVotes(new String(request.getResponseData()));
                    request.removeResponseListener(this);
                } catch (ParseException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Votes;
    }
            
            
                      public ArrayList<Vote> parseVotes(String jsonText) throws ParseException {
        try {
            Votes = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
             
            int id = (int)Float.parseFloat(obj.get("id").toString());
            Double type = (Double) obj.get("type");
           
            Map map = ((Map) obj.get("idClient")) ;
            Map map1 = ((Map) obj.get("idcomment"));
            Double idUser = (Double) map.get("id");
            Double idComment = (Double) map1.get("id");
         
           Votes.add(new Vote(id, idComment.intValue(), idUser.intValue(),type.intValue()));
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return Votes;
    }
              public int  get_VoteLike(int id)
                      
      {
          int Count = 0  ;
          for( Vote v :getAllVote())
          {
              if (v.getId_comment()==id)
              {
              
                  if(v.getType_vote() == 1){
                       Count = Count+1;


                                return Count;

                  }
                

                  
              }
          }

          return Count;
  
      } 
              
                 public int  get_VoteDelike(int id)
                      
      {
         
          int Count = 0  ;
          for( Vote v :getAllVote())
          {
              if (v.getId_comment()==id)
              {
               
                  if(v.getType_vote() == 2){
                       Count = Count+1;
                    

                                return Count;

                  }
                   

                  
              }
          }
 
          return Count;
  
}
}
