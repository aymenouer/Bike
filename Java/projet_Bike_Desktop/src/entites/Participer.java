/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author dell
 */
public class Participer {
    
  private int id ;
  private int idUuser ;
  private  int idEvent ;

    public Participer() {
    }
  
  
  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUuser() {
        return idUuser;
    }

    public void setIdUuser(int idUuser) {
        this.idUuser = idUuser;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public Participer(int idUuser, int idEvent) {
        this.idUuser = idUuser;
        this.idEvent = idEvent;
    }

  
  
  
    
}
