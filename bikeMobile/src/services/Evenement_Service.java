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
import models.Evenement;
import models.User;
import utils.DataSource;
import utils.Statics;

/**
 *
 * @author Rzouga
 */
public class Evenement_Service {
    
    private ConnectionRequest request;
    private boolean responseResult;
    public ArrayList<Evenement> Evenets;

    public Evenement_Service() {
            request = DataSource.getInstance().getRequest();

    }
       public boolean addEvent(Evenement event) {
     
        String url = Statics.BASE_URL + "api/Events/new?titre=" + event.getTitre()+ "&discription=" + event.getDiscription()+ "&lieu=" + event.getLieu()+ "&dated=" + event.getDateDebut()+ "&dateF=" + event.getDateFin()+ "&image=" + event.getImage()+ "&nbrparticipant=" +0+ "&nbrplace=" + event.getNbreplaces();
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

    
        public ArrayList<Evenement> getAllEvents() {
        String url = Statics.BASE_URL + "api/Events/all";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    Evenets = parseEvenement(new String(request.getResponseData()));
                    request.removeResponseListener(this);
                } catch (ParseException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Evenets;
    }
        
          public ArrayList<Evenement> parseEvenement(String jsonText) throws ParseException {
        try {
            Evenets = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
             
            int id = (int)Float.parseFloat(obj.get("id").toString());
            String titre = obj.get("titre").toString();
            String Description = obj.get("discription").toString();
            String lieu = obj.get("lieu").toString();
            String image = obj.get("iamge").toString();
            int nbrplace = (int)Float.parseFloat(obj.get("nbrplace").toString());
            int nbreParticipant = (int)Float.parseFloat(obj.get("nbrparticipant").toString());
            int note = (int)Float.parseFloat(obj.get("rate").toString());
            Map map1 = ((Map) obj.get("dated"));
            Map map = ((Map) obj.get("dateF")) ;
            Date date1 = new Date((((Double)map1.get("timestamp")).longValue()*1000)); 
            Date date = new Date((((Double)map.get("timestamp")).longValue()*1000)); 
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String s = formatter.format(date);
            String s1 = formatter.format(date1);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date datedebut = new Date();
            Date datefin = new Date();
            datedebut = df.parse(s);
            datefin = df.parse(s1);
        

           
                  
                Evenets.add(new Evenement(id, nbrplace, nbreParticipant, lieu, titre, image, Description, datedebut, datefin , note));
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return Evenets;
    }

       public boolean ModifierEvent(Evenement event) {
     
        String url = Statics.BASE_URL + "api/Events/update?titre=" + event.getTitre()+ "&discription=" + event.getDiscription()+ "&lieu=" + event.getLieu()+ "&dated=" + event.getDateDebut()+ "&dateF=" + event.getDateFin()+ "&image=" + event.getImage()+ "&nbrparticipant=" +0+ "&nbrplace=" + event.getNbreplaces()+"&id="+event.getId();
//http://localhost/bike/web/app_dev.php/api/Events/update?titre=aymen&discription=hello&lieu=cc&image=xx&dated=2020-04-08&dateF=2020-04-08&nbrparticipant=50&nbrplace=20&id=10
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
          public boolean DeleteEvenement(int id) {
     
        String url = Statics.BASE_URL + "api/Events/delete/"+id;

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
