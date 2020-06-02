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

import models.Reservation;
import models.Session;
import utils.DataSource;
import utils.Statics;

/**
 *
 * @author asus
 */
public class Reservation_Service {

    private ConnectionRequest request;
    Reservation rr = new Reservation();
    private boolean responseResult;
    public ArrayList<Reservation> Reservations;

    public Reservation_Service() {
        request = DataSource.getInstance().getRequest();
    }

    public ArrayList<Reservation> getAllReservations() throws Exception {
        String url = Statics.BASE_URL + "api/Reservation/all?ID_U=" + Session.getCurrentSession();

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    Reservations = parseReservation(new String(request.getResponseData()));
                } catch (ParseException ex) {

                }
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Reservations;
    }

    public ArrayList<Reservation> parseReservation(String jsonText) throws ParseException {

        try {
            Reservations = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {

                int ID_U = (int) Float.parseFloat(obj.get("iDU").toString());
                int idV = (int) Float.parseFloat(obj.get("idV").toString());
                int idR = (int) Float.parseFloat(obj.get("id").toString());
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

                Float prix = Float.parseFloat(obj.get("prix").toString());
                Reservations.add(new Reservation(ID_U, idV, datefin, datedebut, prix, idR));

            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return Reservations;
    }

    public ArrayList<Reservation> parseaa(String jsonText) throws ParseException {

        try {
            Reservations = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {

                Float prix = Float.parseFloat(obj.get("prix").toString());
                Reservation a = new Reservation();
                a.setPrix(prix);
                Reservations.add(a);

            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return Reservations;
    }

    public Reservation getaa(Reservation r) {

        String url = Statics.BASE_URL + "api/Reservation/verif?idV=" + r.getIdV() + "&Date_D=" + r.getDATE_D() + "&Date_F=" + r.getDATE_F();

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    Reservations = parseaa(new String(request.getResponseData()));
                } catch (ParseException ex) {

                }
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Reservations.get(0);
    }

    public int verif_reservation(Reservation r) {
      System.out.println(getaa(r));
        if (getaa(r).getPrix() == 0) {
          
            return 1;
        }
        return 0;

    }

    public boolean removeReservation(int id) {

        String url = Statics.BASE_URL + "api/Reservation/remove?id=" + id;

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

    public boolean Reserver(Reservation r) throws Exception {

        String url = Statics.BASE_URL + "api/Reservation/add?idR=" + r.getIdR() + "&idV=" + r.getIdV() + "&ID_U=" + r.getID_U() + "&prix=" + r.getPrix() + "&Date_D=" + r.getDATE_D() + "&Date_F=" + r.getDATE_F() + "&ID_U=" + Session.getCurrentSession();

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
