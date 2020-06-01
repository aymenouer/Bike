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
public class Sponsor {
    
    private int idSponsor,numero,ide;
    private String type,adresse,nom;

    public int getIdSponsor() {
        return idSponsor;
    }

    public int getIde() {
        return ide;
    }

    public void setIde(int ide) {
        this.ide = ide;
    }

    
    public String getNom() {
        return nom;
    }
   
    public int getNumero() {
        return numero;
    }

    public String getType() {
        return type;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setIdSponsor(int idSponsor) {
        this.idSponsor = idSponsor;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.idSponsor;
        return hash;
    }

    public Sponsor(int idSponsor, int numero, String adresse) {
        this.idSponsor = idSponsor;
        this.numero = numero;
        this.adresse = adresse;
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
        final Sponsor other = (Sponsor) obj;
        if (this.idSponsor != other.idSponsor) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sponsor{" + "idSponsor=" + idSponsor + ", numero=" + numero + ", ide=" + ide + ", type=" + type + ", adresse=" + adresse + ", nom=" + nom + '}';
    }

    public Sponsor(int numero, int ide, String type, String adresse, String nom) {
        this.numero = numero;
        this.ide = ide;
        this.type = type;
        this.adresse = adresse;
        this.nom = nom;
    }

    



    

    public Sponsor() {
    }
    
    
   
}
