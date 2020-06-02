/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author aymen
 */
public class Rating_Produit {
    private int IDR;
    private int ID_U;
    private int ID_P;
    private int vote;

    public Rating_Produit(int ID_P, int vote) {
        this.ID_P = ID_P;
        this.vote = vote;
    }

    public Rating_Produit(int ID_U, int ID_P, int vote) {
        this.ID_U = ID_U;
        this.ID_P = ID_P;
        this.vote = vote;
    }

    public Rating_Produit(int IDR, int ID_U, int ID_P, int vote) {
        this.IDR = IDR;
        this.ID_U = ID_U;
        this.ID_P = ID_P;
        this.vote = vote;
    }

    @Override
    public String toString() {
        return "Rating_Produit{" + "IDR=" + IDR + ", ID_U=" + ID_U + ", ID_P=" + ID_P + ", vote=" + vote + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.IDR;
        hash = 23 * hash + this.ID_U;
        hash = 23 * hash + this.ID_P;
        hash = 23 * hash + this.vote;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rating_Produit other = (Rating_Produit) obj;
        if (this.IDR != other.IDR) {
            return false;
        }
        if (this.ID_U != other.ID_U) {
            return false;
        }
        if (this.ID_P != other.ID_P) {
            return false;
        }
        if (this.vote != other.vote) {
            return false;
        }
        return true;
    }

    public int getIDR() {
        return IDR;
    }

    public void setIDR(int IDR) {
        this.IDR = IDR;
    }

    public int getID_U() {
        return ID_U;
    }

    public void setID_U(int ID_U) {
        this.ID_U = ID_U;
    }

    public int getID_P() {
        return ID_P;
    }

    public void setID_P(int ID_P) {
        this.ID_P = ID_P;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
    
    
    
}
