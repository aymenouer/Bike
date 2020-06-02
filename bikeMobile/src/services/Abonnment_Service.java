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
import utils.DataSource;
import utils.Statics;

/**
 *
 * @author aymen
 */
public class Abonnment_Service {
     private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Abonnement> Abonnements;
     public Abonnment_Service() {
        request = DataSource.getInstance().getRequest();
    }

   public boolean addAbonnement(Abonnement abonnment) {
     
        String url = Statics.BASE_URL + "api/Abonnments/new?libelle=" + abonnment.getLibelle()+ "&image=" + abonnment.getImage()+ "&description=" + abonnment.getDescription()+ "&prix=" + abonnment.getPrix()+ "&libc=" + abonnment.getLib_C()+ "&libs=" + abonnment.getLib_S()+ "&quantite=" + abonnment.getQuantite();

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

   public boolean ModifierAbonnement(Abonnement abonnment,int id) {
     
        String url = Statics.BASE_URL + "api/Abonnments/update?libelle=" + abonnment.getLibelle()+ "&image=" + abonnment.getImage()+ "&description=" + abonnment.getDescription()+ "&prix=" + abonnment.getPrix()+ "&libc=" + abonnment.getLib_C()+ "&libs=" + abonnment.getLib_S()+ "&quantite=" + abonnment.getQuantite()+"&id=" +id;

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
  
     
    
     
       public boolean DeleteAbonnement(int id) {
     
        String url = Statics.BASE_URL + "api/Abonnments/remove?id=" + id;

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
     
        public ArrayList<Abonnement> parseStat(String jsonText) {
        
        try {
            Abonnements = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) 
            {           
          
             
            String LibC = obj.get("Lib_C").toString();     
          
      

            int quantite = (int)Float.parseFloat(obj.get("nombre").toString());
            
    
            Abonnement ab = new Abonnement();
            ab.setQuantite(quantite);
            ab.setLib_C(LibC);
            Abonnements.add(ab);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return Abonnements;
    }
         public ArrayList<Abonnement> getStat() {
        String url = Statics.BASE_URL + "api/Abonnments/stat";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Abonnements = parseStat(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Abonnements;
    }
     
     
     
     
    public ArrayList<Abonnement> getAllAbonnements() {
        String url = Statics.BASE_URL + "api/Abonnments/all";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Abonnements = parseAbonnements(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Abonnements;
    }

    public ArrayList<Abonnement> parseAbonnements(String jsonText) {
        
        try {
            Abonnements = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) 
            {           
            String Libelle = obj.get("Libelle").toString(); 
             
            String LibC = obj.get("Lib_C").toString();     
          
            String LibS = obj.get("Lib_S").toString();  
            
            String image = obj.get("Image").toString();
        
            String Description = obj.get("Description").toString();

            int quantite = (int)Float.parseFloat(obj.get("quantite").toString());
            
            Float prix = Float.parseFloat(obj.get("Prix").toString());
            
            int ID_A = (int)Float.parseFloat(obj.get("ID_A").toString());  
            
            int ID_P = (int)Float.parseFloat(obj.get("ID_P").toString());  
            
            Abonnements.add(new Abonnement(quantite,ID_A,ID_P,Libelle,LibC,LibS,image,Description,prix));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return Abonnements;
    }
 
      public Abonnement  get_Abonnement(int id)
      {
          for( Abonnement u :getAllAbonnements()  )
          {
             if (u.getId_A()==id)
              {
                  return u;
              }
          }
          return null;
  
      }
}
