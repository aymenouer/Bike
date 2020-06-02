/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import services.User_Service;

/**
 *
 * @author aymen
 */
public class Session {
         private static int idUser;
    private static   User userstat;

    public static void start(int currentUserID) {
        idUser = currentUserID;
           User_Service u = new User_Service();
     
      userstat = u.get_User(idUser);
    }

    public static int getCurrentSession() throws Exception {
        if (idUser != -1) {
            return idUser;
        } else {
            throw new Exception("Session has not started yet!");
        }
    }

    public static void close() throws Exception {
        if (idUser != -1) {
            idUser = -1;
        } else {
            throw new Exception("Session has not started yet!");
        }
    }
    public static User get()
    {
     
        return userstat;
        
    }

}
