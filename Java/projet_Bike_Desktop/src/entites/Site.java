/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Objects;

/**
 *
 * @author Rajia
 */
public class Site {
    private int capacite;
    private String Lib_S;
    private String Lieu;
    private int id;

    public Site(int capacite, String Lib_S, String Lieu, int id) {
        this.capacite = capacite;
        this.Lib_S = Lib_S;
        this.Lieu = Lieu;
        this.id = id;
    }
    
    public Site(int capacite, String Lib_S, String Lieu) {
        this.capacite = capacite;
        this.Lib_S = Lib_S;
        this.Lieu = Lieu;
    }

    @Override
    public String toString() {
        return "Site{" + "capacite=" + capacite + ", Lib_S=" + Lib_S + ", Lieu=" + Lieu + '}';
    }


    public int getCapacite() {
        return capacite;
    }

    public String getLib_S() {
        return Lib_S;
    }

    public String getLieu() {
        return Lieu;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public void setLib_S(String Lib_S) {
        this.Lib_S = Lib_S;
    }

    public void setLieu(String Lieu) {
        this.Lieu = Lieu;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.capacite;
        hash = 73 * hash + Objects.hashCode(this.Lib_S);
        hash = 73 * hash + Objects.hashCode(this.Lieu);
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
        final Site other = (Site) obj;
        if (this.capacite != other.capacite) {
            return false;
        }
        if (!Objects.equals(this.Lib_S, other.Lib_S)) {
            return false;
        }
        if (!Objects.equals(this.Lieu, other.Lieu)) {
            return false;
        }
        return true;
    }
    
    
}
