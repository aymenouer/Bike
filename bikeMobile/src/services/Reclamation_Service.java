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
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import models.Session;
import models.reclamation;
import utils.DataSource;
import utils.Statics;

/**
 *
 * @author aymen
 */
public class Reclamation_Service {
         private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<reclamation> Reclamations;
       public Reclamation_Service() {
        request = DataSource.getInstance().getRequest();
    }
    
       public boolean addReclamation(reclamation reclamation) throws Exception {
    
        String url = Statics.BASE_URL + "api/Reclamation/AddReclamationUser?image="+reclamation.getImage()+"&etat="+reclamation.getEtat()+"&contenu="+reclamation.getContenu()+"&ID_U="+Session.getCurrentSession()+"&Type="+reclamation.getType();
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

    public ArrayList<reclamation> getAllReclamationsUser() throws Exception {
        String url = Statics.BASE_URL + "api/Reclamation/ListReclamationUser?ID_U="+Session.getCurrentSession();

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Reclamations = parseReclamationsUser(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Reclamations;
    }
    public ArrayList<reclamation> getAllReclamationsUsers() throws Exception {
        String url = Statics.BASE_URL + "api/Reclamation/ListReclamationUsers";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Reclamations = parseReclamationsUsers(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Reclamations;
    }
    public ArrayList<reclamation> parseReclamationsUser(String jsonText) {
        
        try {
            Reclamations = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) 
            {  
                
              int ID = (int)Float.parseFloat(obj.get("ID").toString());    
                
            String Contenu = obj.get("Contenu").toString(); 
             
            String Type = obj.get("Type").toString();     
          
            String etat = obj.get("etat").toString();  
            
            String image = obj.get("image").toString();
        
            String traite = obj.get("traite").toString();


            
            

            
            Reclamations.add(new reclamation(ID, 0, Contenu, image, Type, etat, traite));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return Reclamations;
    }
        public ArrayList<reclamation> parseReclamationsUsers(String jsonText) {
        
        try {
            Reclamations = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) 
            {  
                
              int ID = (int)Float.parseFloat(obj.get("id").toString());    
                
            String Contenu = obj.get("contenu").toString(); 
             
            String Type = obj.get("type").toString();     
          
            String etat = obj.get("etat").toString();  
            
            String image = obj.get("image").toString();
        
            String traite = obj.get("traite").toString();


            
            

            
            Reclamations.add(new reclamation(ID, 0, Contenu, image, Type, etat, traite));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return Reclamations;
    }
        
   
           public boolean Traite_Reclamation(int id) {
    
        String url = Statics.BASE_URL + "api/Reclamation/TraiteReclamation?id="+id;
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
                  public boolean Delete_Reclamation(int id) {
    
        String url = Statics.BASE_URL + "api/Reclamation/DeleteReclamation?id="+id;
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
}
