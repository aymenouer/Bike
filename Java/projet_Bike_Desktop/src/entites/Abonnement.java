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
public class Abonnement extends Produit{
    
        private int quantite;
        private int id_A;

    public Abonnement(int quantite, int id_A, String libelle, String Lib_C, String Lib_S, String image, String description, Float prix) {
        super(libelle, Lib_C, Lib_S, image, description, prix);
        this.quantite = quantite;
        this.id_A = id_A;
    }
  public Abonnement(int quantite, String libelle, String Lib_C, String Lib_S, String image, String description, Float prix) {
        super(libelle, Lib_C, Lib_S, image, description, prix);
        this.quantite = quantite;
        
    }

    public Abonnement(int quantite, int id_A, int id_p, String libelle, String Lib_C, String Lib_S, String image, String description, Float prix) {
        super(id_p, libelle, Lib_C, Lib_S, image, description, prix);
        this.quantite = quantite;
        this.id_A = id_A;
    }
  
  




    public int getQuantite() {
        return quantite;
    }

 

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getId_A() {
        return id_A;
    }

    public void setId_A(int id_A) {
        this.id_A = id_A;
    }



    @Override
    public String toString() {
        return "Abonnement{" + super.toString() + ", quantite=" + quantite + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.quantite;
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
        final Abonnement other = (Abonnement) obj;
        if (this.quantite != other.quantite) {
            return false;
        }
        return true;
    }
    
        
}
