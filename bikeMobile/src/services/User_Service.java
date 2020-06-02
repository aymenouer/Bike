/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.db.Cursor;
import com.codename1.db.Database;
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
import models.User;
import utils.DataSource;
import utils.Statics;

/**
 *
 * @author aymen
 */
public class User_Service  {
    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<User> users;
    
    

    public User_Service() {
        request = DataSource.getInstance().getRequest();
    }

   public boolean addUser(User user) {
     
        String url = Statics.BASE_URL + "api/users/new?username=" + user.getUsername()+ "&email=" + user.getMail()+ "&password=" + user.getPass()+ "&firstname=" + user.getFname()+ "&lastname=" + user.getLname()+ "&image=" + user.getImage()+ "&age=" + user.getAge()+ "&number=" + user.getNumber();

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
   public boolean ModifierUser(User user,int id) {
     
        String url = Statics.BASE_URL + "api/users/update?username=" + user.getUsername()+ "&email=" + user.getMail()+ "&password=" + user.getPass()+ "&firstname=" + user.getFname()+ "&lastname=" + user.getLname()+ "&image=" + user.getImage()+ "&age=" + user.getAge()+ "&number=" + user.getNumber()+ "&id=" +id;

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
   
    public ArrayList<User> getAllusers() {
        String url = Statics.BASE_URL + "api/users/all";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseusers(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return users;
    }

    public ArrayList<User> parseusers(String jsonText) {
        try {
            users = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
              
                int id = (int)Float.parseFloat(obj.get("id").toString());
            
             
                String fname = obj.get("firstName").toString();
               
                String lname = obj.get("lastName").toString();
              
                  String mail = obj.get("email").toString();
           
                  int number = (int)Float.parseFloat(obj.get("userNumber").toString());
       
                   String username = obj.get("username").toString();
       
                  int age = (int)Float.parseFloat(obj.get("userAge").toString());
              
                    String image = obj.get("userImage").toString();
    String role = obj.get("roles").toString();
    String password = obj.get("password").toString();
                   String valeur_enabled =obj.get("enabled").toString();
                     int enabled  =  0;
                   if ("true".equals(valeur_enabled))
                   {
                       enabled  =  1;
                   }
                  
                  
                users.add(new User( id,enabled  ,fname ,lname,  mail,  number,  username,  role,  age,  image));
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return users;
    }
 
      public User  get_User(int id)
      {
          for( User u :getAllusers()  )
          {
              if (u.getId()==id)
              {
                  return u;
              }
          }
          return null;
  
      }
      public boolean DesactiverUser(int id) 
      {
            String url = Statics.BASE_URL + "api/users/desactiver?id=" + id;

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

      
       public boolean activerUser(int id) 
      {
            String url = Statics.BASE_URL + "api/users/activer?id=" + id;

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

