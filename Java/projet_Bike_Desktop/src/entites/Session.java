/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import Service.User_Service;

/**
 *
 * @author aymen
 */
public class Session {
         private static int idUser;
   

    public static void start(int currentUserID) {
        idUser = currentUserID;
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
        User_Service u = new User_Service();
        
        User user = u.get_User(idUser);
        return user;
        
    }

}
