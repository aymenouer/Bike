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
import models.Abonnement;
import models.User;
import models.Velo;
import utils.DataSource;
import utils.Statics;

/**
 *
 * @author asus
 */
public class Velo_Service {
    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Velo> velos;
    
    

    public Velo_Service() {
        request = DataSource.getInstance().getRequest();
    }

    public ArrayList<Velo> getAllvelos() {
        String url = Statics.BASE_URL + "api/Velo/all";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                velos = parsvelos(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return velos;
    }

    public ArrayList<Velo> parsvelos(String jsonText) {
    try {
            velos = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                
                String Libelle = obj.get("Libelle").toString();
               String LibC = obj.get("Lib_C").toString();
                String LibS = obj.get("Lib_S").toString();
                 String image = obj.get("Image").toString();
                String Description = obj.get("Description").toString();
              String type = obj.get("type").toString();
                       String etat = obj.get("etat").toString();
                          String couleur = obj.get("couleur").toString();
            int age = (int)Float.parseFloat(obj.get("age").toString());
            Float prix = Float.parseFloat(obj.get("Prix").toString());
               int ID_v = (int)Float.parseFloat(obj.get("id").toString());   
                 int ID_P = (int)Float.parseFloat(obj.get("idA").toString());    
                 
               velos.add(new Velo( type,  age,  couleur,  etat,  ID_v,  Libelle,  LibC,  LibS,  image,  Description,  prix));
           
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return velos;
    }
 
     
     public boolean addVelo(Velo v) {
     
        String url = Statics.BASE_URL + "api/Velo/add?libelle=" + v.getLibelle()+ "&image=" + v.getImage()+ "&description=" + v.getDescription()+ "&prix=" + v.getPrix()+ "&libc=" + v.getLib_C()+ "&libs=" + v.getLib_S()+ "&age=" + v.getAge()+ "&couleur=" + v.getCouleur()+ "&etat=" + v.getEtat()+ "&type=" + v.getType();

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
     public boolean removeVelo(int id) {
     
        String url = Statics.BASE_URL + "api/Velo/remove?id=" + id;

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


        public boolean ModifierAccessoire(Velo v,int id) {
     
        String url = Statics.BASE_URL +"api/Velo/edit?libelle=" + v.getLibelle()+ "&image=" + v.getImage()+ "&description=" + v.getDescription()+ "&prix=" + v.getPrix()+ "&libc=" + v.getLib_C()+ "&libs=" + v.getLib_S()+ "&age=" + v.getAge()+ "&couleur=" + v.getCouleur()+ "&etat=" + v.getEtat()+ "&type=" + v.getType()+"&id=" +id;

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
    public Velo  get_velo(int id)
      {
          for( Velo u :getAllvelos())
          {
             if (u.getId_v()==id)
              {
                  return u;
              }
          }
          return null;
  
      } 
     
public String  get_libC(int id)
      {
          for( Velo u :getAllvelos())
          {
             if (u.getId_v()==id)
              {
                  return u.getLib_C();
              }
          }
          return null;
  
      } 
public String  getlib(int id)
      {
          for( Velo u :getAllvelos())
          {
             if (u.getId_v()==id)
              {
                  return u.getLibelle();
              }
          }
          return null;
  
      } 
      
   public String  get_Image(int id)
      {
          for( Velo u :getAllvelos())
          {
             if (u.getId_v()==id)
              {
                  return u.getImage();
              }
          }
          return null;
  
      }  
         public ArrayList<Velo> parseStat(String jsonText) {
        
        try {
            velos = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) 
            {           
          
             
            String LibC = obj.get("Lib_C").toString();     
          
      

            int quantite = (int)Float.parseFloat(obj.get("nombre").toString());
            
    
            Velo ab = new Velo();
            ab.setAge(quantite);
            ab.setLib_C(LibC);
            velos.add(ab);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return velos;
    }
         public ArrayList<Velo> getStat() {
        String url = Statics.BASE_URL + "api/Velo/stat";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                velos = parseStat(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return velos;
    }
}
