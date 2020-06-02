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
import models.Categorie;
import utils.DataSource;
import utils.Statics;

/**
 *
 * @author aymen
 */
public class Categorie_Service {
    
     private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Categorie> Categories;
     public Categorie_Service() {
        request = DataSource.getInstance().getRequest();
    }

   public boolean addCategorie(Categorie categorie) {
     
        String url = Statics.BASE_URL + "api/categories/new?lib_c=" + categorie.getLib_C()+ "&Description=" + categorie.getDescription()+ "&type=" + categorie.getType();

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

   public boolean ModifierCategorie(Categorie categorie,int id) {
     
        String url = Statics.BASE_URL + "api/categories/update?lib_c=" + categorie.getLib_C()+ "&Description=" + categorie.getDescription()+ "&type=" + categorie.getType()+"&id=" +id;

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
  
     
     
     
       public boolean DeleteCategorie(int id) {
     
        String url = Statics.BASE_URL + "api/categories/remove?id=" + id;

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
     
     
     
     
     
     
     
    public ArrayList<Categorie> getAllCategories() {
        String url = Statics.BASE_URL + "api/categories/all";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Categories = parseCategories(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Categories;
    }

    public ArrayList<Categorie> parseCategories(String jsonText) {
        try {
            Categories = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
              
                int id = (int)Float.parseFloat(obj.get("idC").toString());
            
             
                String libC = obj.get("libC").toString();
               
                String Description = obj.get("description").toString();
              String type = obj.get("type").toString();
              
           
                  
                Categories.add(new Categorie(id, libC, Description, type));
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return Categories;
    }
 
      public Categorie  get_Categorie(int id)
      {
          for( Categorie u :getAllCategories()  )
          {
              if (u.getID_C()==id)
              {
                  return u;
              }
          }
          return null;
  
      }
    
}
