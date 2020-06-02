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
import models.Maitenance;


import models.Session;
import utils.DataSource;
import utils.Statics;

/**
 *
 * @author aymen
 */
public class Maintenance_Service {

    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Maitenance> maintenances;

    public Maintenance_Service() {
        request = DataSource.getInstance().getRequest();
    }
       public boolean addMaitenance(Maitenance maintenance) throws Exception {
    
        String url = Statics.BASE_URL + "api/Maintenances/Add_maintenance_user?image="+maintenance.getImage()+"&problem="+maintenance.getProblem()+"&ID_U="+Session.getCurrentSession()+"&type="+maintenance.getType();
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
       
       
             public boolean UpdateMaitenance(Maitenance maintenance,int cas)  {
    
        String url = Statics.BASE_URL + "api/Maintenances/Update?id="+maintenance.getId()+"&prix="+maintenance.getPrix()+"&cas="+cas;
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
          public boolean DeleteMaitenance(Maitenance maintenance) throws Exception {
    
        String url = Statics.BASE_URL + "api/Maintenances/Delete?id="+maintenance.getId()+"&ID_U="+Session.getCurrentSession();
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
    public ArrayList<Maitenance> getAllMaintenance_User() throws Exception {
        String url = Statics.BASE_URL + "api/Maintenances/List_MaintenanceUser?ID_U=" + Session.getCurrentSession();

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    maintenances = parseMaintenance(new String(request.getResponseData()));
                } catch (Exception ex) {

                }
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return maintenances;
    }

    public ArrayList<Maitenance> parseMaintenance(String jsonText) throws ParseException {
        try {
            maintenances = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                System.out.println("eee");

                int ID_M = (int) Float.parseFloat(obj.get("IDM").toString());
                String Problem = obj.get("Problem").toString();
                Float Prix = Float.parseFloat(obj.get("prix").toString());
                String etat = obj.get("etat").toString();
                String image = obj.get("image").toString();
                String type = obj.get("type").toString();

                Map map1 = ((Map) obj.get("dateD"));
                Map map = ((Map) obj.get("dateF"));
                Date date1 = new Date((((Double) map1.get("timestamp")).longValue() * 1000));
                Date date = new Date((((Double) map.get("timestamp")).longValue() * 1000));
                Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                String s = formatter.format(date);
                String s1 = formatter.format(date1);
              DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
                Date datedebut = new Date();
                Date datefin = new Date();

                datedebut = df.parse(s);

                datefin = df.parse(s1);

                maintenances.add(new Maitenance(ID_M, 0, Prix, Problem, etat, image, type, datedebut, datefin));
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return maintenances;
    }
    public ArrayList<Maitenance> parseMaintenance_admin(String jsonText) throws ParseException {
        try {
            maintenances = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                System.out.println("eee");

   
     
      
 

  
                int ID_M = (int) Float.parseFloat(obj.get("IDM").toString());
                String Problem = obj.get("Problem").toString();
                Float Prix = Float.parseFloat(obj.get("prix").toString());
                String etat = obj.get("etat").toString();
                String image = obj.get("image").toString();
                String type = obj.get("type").toString();

                Map map1 = ((Map) obj.get("dateD"));
                Map map = ((Map) obj.get("dateF"));
                Date date1 = new Date((((Double) map1.get("timestamp")).longValue() * 1000));
                Date date = new Date((((Double) map.get("timestamp")).longValue() * 1000));
                Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                String s = formatter.format(date);
                String s1 = formatter.format(date1);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date datedebut = new Date();
                Date datefin = new Date();

                datedebut = df.parse(s);

                datefin = df.parse(s1);

                maintenances.add(new Maitenance(ID_M, 0, Prix, Problem, etat, image, type, datedebut, datefin));
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return maintenances;
    }
   public ArrayList<Maitenance> getAllMaintenance_Admin()  {
        String url = Statics.BASE_URL + "api/Maintenances/ListMaintenanceAdmin";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    maintenances = parseMaintenance_admin(new String(request.getResponseData()));
                } catch (Exception ex) {

                }
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return maintenances;
    }
}
