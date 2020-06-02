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
import models.Piece;
import utils.DataSource;
import utils.Statics;


/**
 *
 * @author aymen
 */
public class Piece_Service {
          private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Piece> pieces;
     public Piece_Service() {
        request = DataSource.getInstance().getRequest();
    }
     
     
     
        public boolean addPiece(Piece Piece) {
     
        String url = Statics.BASE_URL + "api/Pieces/newPiece?libelle=" + Piece.getLibelle()+ "&image=" + Piece.getImage()+ "&description=" + Piece.getDescription()+ "&prix=" + Piece.getPrix()+ "&libc=" + Piece.getLib_C()+ "&libs=" + Piece.getLib_S()+ "&quantite=" + Piece.getQuantite()+"&etat="+ Piece.getEtat();

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
        
        
        
           public boolean ModifierPiece(Piece Piece,int id) {
     
        String url = Statics.BASE_URL + "api/Pieces/UpdatePiece?libelle=" + Piece.getLibelle()+ "&image=" + Piece.getImage()+ "&description=" + Piece.getDescription()+ "&prix=" + Piece.getPrix()+ "&libc=" + Piece.getLib_C()+ "&libs=" + Piece.getLib_S()+ "&quantite=" + Piece.getQuantite()+"&etat="+ Piece.getEtat()+"&id=" +id;

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
        
        
        
        
          public boolean DeletePiece(int id) {
     
        String url = Statics.BASE_URL + "api/Pieces/DeletePiece?id=" + id;

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
          
    public ArrayList<Piece> getAllPieces() {
        String url = Statics.BASE_URL + "api/Pieces/AllPieces";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                pieces = parsePieces(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return pieces;
    }

    public ArrayList<Piece> parsePieces(String jsonText) {
        
        try {
            pieces = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
       
            
            
            
            
           for (Map<String, Object> obj :  list) 
            {           
             
            String Libelle = obj.get("Libelle").toString(); 
             String etat = obj.get("etat").toString(); 
            String LibC = obj.get("Lib_C").toString();     
          
            String LibS = obj.get("Lib_S").toString();  
            
            String image = obj.get("Image").toString();
        
            String Description = obj.get("Description").toString();

            int quantite = (int)Float.parseFloat(obj.get("quantite").toString());
            
            Float prix = Float.parseFloat(obj.get("Prix").toString());
            
            int ID = (int)Float.parseFloat(obj.get("ID_Pi").toString());  
            
            int ID_P = (int)Float.parseFloat(obj.get("ID_P").toString());   
            
            pieces.add(new Piece(quantite,ID,etat,ID_P,Libelle,LibC,LibS,image,Description,prix));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return pieces;
    }
     
     
     
     
     
}
