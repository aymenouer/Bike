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
public class Commande {
    private int ID_Commande;
    private String Libelle_p;
    private float Prix_P;
    private int Quantite_P;
    private String Adresse;
    private String Ville;
    private int Telephone;

    public Commande(int ID_Commande, String Libelle_p, float Prix_P, int Quantite_P, String Adresse, String Ville, int Telephone) {
        this.ID_Commande = ID_Commande;
        this.Libelle_p = Libelle_p;
        this.Prix_P = Prix_P;
        this.Quantite_P = Quantite_P;
        this.Adresse = Adresse;
        this.Ville = Ville;
        this.Telephone = Telephone;
    }

    @Override
    public String toString() {
        return "Commande{" + "ID_Commande=" + ID_Commande + ", Libelle_p=" + Libelle_p + ", Prix_P=" + Prix_P + ", Quantite_P=" + Quantite_P + ", Adresse=" + Adresse + ", Ville=" + Ville + ", Telephone=" + Telephone + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.ID_Commande;
        hash = 71 * hash + Objects.hashCode(this.Libelle_p);
        hash = 71 * hash + Float.floatToIntBits(this.Prix_P);
        hash = 71 * hash + this.Quantite_P;
        hash = 71 * hash + Objects.hashCode(this.Adresse);
        hash = 71 * hash + Objects.hashCode(this.Ville);
        hash = 71 * hash + this.Telephone;
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
        final Commande other = (Commande) obj;
        if (this.ID_Commande != other.ID_Commande) {
            return false;
        }
        if (Float.floatToIntBits(this.Prix_P) != Float.floatToIntBits(other.Prix_P)) {
            return false;
        }
        if (this.Quantite_P != other.Quantite_P) {
            return false;
        }
        if (this.Telephone != other.Telephone) {
            return false;
        }
        if (!Objects.equals(this.Libelle_p, other.Libelle_p)) {
            return false;
        }
        if (!Objects.equals(this.Adresse, other.Adresse)) {
            return false;
        }
        if (!Objects.equals(this.Ville, other.Ville)) {
            return false;
        }
        return true;
    }

    public void setID_Commande(int ID_Commande) {
        this.ID_Commande = ID_Commande;
    }

    public void setLibelle_p(String Libelle_p) {
        this.Libelle_p = Libelle_p;
    }

    public void setPrix_P(float Prix_P) {
        this.Prix_P = Prix_P;
    }

    public void setQuantite_P(int Quantite_P) {
        this.Quantite_P = Quantite_P;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public void setVille(String Ville) {
        this.Ville = Ville;
    }

    public void setTelephone(int Telephone) {
        this.Telephone = Telephone;
    }

    public int getID_Commande() {
        return ID_Commande;
    }

    public String getLibelle_p() {
        return Libelle_p;
    }

    public float getPrix_P() {
        return Prix_P;
    }

    public int getQuantite_P() {
        return Quantite_P;
    }

    public String getAdresse() {
        return Adresse;
    }

    public String getVille() {
        return Ville;
    }

    public int getTelephone() {
        return Telephone;
    }
    
}
