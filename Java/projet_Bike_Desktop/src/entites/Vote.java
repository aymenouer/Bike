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
public class Vote {
    
    private int id ;
    private int id_evenement  ;
    private int id_client;
    private int type_vote;

    public Vote() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getType_vote() {
        return type_vote;
    }

    public void setType_vote(int type_vote) {
        this.type_vote = type_vote;
    }

    public Vote(int id_evenement, int id_client, int type_vote) {
        this.id_evenement = id_evenement;
        this.id_client = id_client;
        this.type_vote = type_vote;
    }

    
    
}
