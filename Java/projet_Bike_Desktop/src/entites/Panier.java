/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Objects;

/**
 *
 * @author aymen
 */
public class Panier {
    private int id_panier;
    private int ID_P;
    private String Libelle_P;
    private int Quantite_P;
    private Float Prix_P;

    @Override
    public String toString() {
        return "Panier{" + "id_panier=" + id_panier + ", ID_P=" + ID_P + ", Libelle_P=" + Libelle_P + ", Quantite_P=" + Quantite_P + ", Prix_P=" + Prix_P + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id_panier;
        hash = 47 * hash + this.ID_P;
        hash = 47 * hash + Objects.hashCode(this.Libelle_P);
        hash = 47 * hash + this.Quantite_P;
        hash = 47 * hash + Objects.hashCode(this.Prix_P);
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
        final Panier other = (Panier) obj;
        if (this.id_panier != other.id_panier) {
            return false;
        }
        if (this.ID_P != other.ID_P) {
            return false;
        }
        if (this.Quantite_P != other.Quantite_P) {
            return false;
        }
        if (!Objects.equals(this.Libelle_P, other.Libelle_P)) {
            return false;
        }
        if (!Objects.equals(this.Prix_P, other.Prix_P)) {
            return false;
        }
        return true;
    }

    public Panier(int id_panier, int ID_P, String Libelle_P, int Quantite_P, Float Prix_P) {
        this.id_panier = id_panier;
        this.ID_P = ID_P;
        this.Libelle_P = Libelle_P;
        this.Quantite_P = Quantite_P;
        this.Prix_P = Prix_P;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public void setID_P(int ID_P) {
        this.ID_P = ID_P;
    }

    public void setLibelle_P(String Libelle_P) {
        this.Libelle_P = Libelle_P;
    }

    public void setQuantite_P(int Quantite_P) {
        this.Quantite_P = Quantite_P;
    }

    public void setPrix_P(Float Prix_P) {
        this.Prix_P = Prix_P;
    }

    public int getId_panier() {
        return id_panier;
    }

    public int getID_P() {
        return ID_P;
    }

    public String getLibelle_P() {
        return Libelle_P;
    }

    public int getQuantite_P() {
        return Quantite_P;
    }

    public Float getPrix_P() {
        return Prix_P;
    }
    

    
}
