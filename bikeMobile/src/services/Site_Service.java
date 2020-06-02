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
import models.Site;
import utils.DataSource;
import utils.Statics;

/**
 *
 * @author aymen
 */
public class Site_Service {
        private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Site> sites;
     public Site_Service() {
        request = DataSource.getInstance().getRequest();
    }

   public boolean addSite(Site site) {
     
        String url = Statics.BASE_URL + "api/sites/new?lib_s=" + site.getLib_S()+ "&lieu=" + site.getLieu()+ "&capacite=" + site.getCapacite();

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

   public boolean ModifierSite(Site site,int id) {
     
        String url = Statics.BASE_URL + "api/sites/update?lib_s=" + site.getLib_S()+ "&lieu=" + site.getLieu()+ "&capacite=" + site.getCapacite()+ "&id=" +id;

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
  
     
     
     
       public boolean DeleteSite(int id) {
     
        String url = Statics.BASE_URL + "api/sites/remove?id=" + id;

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
     
     
     
     
     
     
     
    public ArrayList<Site> getAllsites() {
        String url = Statics.BASE_URL + "api/sites/all";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                sites = parsesites(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return sites;
    }

    public ArrayList<Site> parsesites(String jsonText) {
        try {
            sites = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
              
                int id = (int)Float.parseFloat(obj.get("idS").toString());
            
             
                String libS = obj.get("libS").toString();
               
                String lieu = obj.get("lieu").toString();
              
              
           
                  int capacite = (int)Float.parseFloat(obj.get("capacite").toString());
       
                
       
              
              
             
                  
                sites.add(new Site(capacite,libS,lieu,id));
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return sites;
    }
 
      public Site  get_Site(int id)
      {
          for( Site u :getAllsites()  )
          {
              if (u.getId()==id)
              {
                  return u;
              }
          }
          return null;
  
      }
}
