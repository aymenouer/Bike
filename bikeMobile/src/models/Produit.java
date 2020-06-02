/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;



/**
 *
 * @author Rajia
 */
public class Produit {
    protected int id_p;
    protected String libelle;
    protected String Lib_C;
    protected String Lib_S;
   protected String image;
   protected String description;
   protected Float prix;

    public void setId_p(int id_p) {
        this.id_p = id_p;
    }

    public int getId_p() {
        return id_p;
    }

    public Produit(int id_p, String libelle, String Lib_C, String Lib_S, String image, String description, Float prix) {
        this.id_p = id_p;
        this.libelle = libelle;
        this.Lib_C = Lib_C;
        this.Lib_S = Lib_S;
        this.image = image;
        this.description = description;
        this.prix = prix;
    }

    public Produit(String libelle, String Lib_C, String Lib_S, String image, String description, Float prix) {
        this.libelle = libelle;
        this.Lib_C = Lib_C;
        this.Lib_S = Lib_S;
        this.image = image;
        this.description = description;
        this.prix = prix;
    }

    public String getLib_S() {
        return Lib_S;
    }

    public void setLib_S(String Lib_S) {
        this.Lib_S = Lib_S;
    }



    public String getLibelle() {
        return libelle;
    }

    public String getLib_C() {
        return Lib_C;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public Float getPrix() {
        return prix;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setLib_C(String Lib_C) {
        this.Lib_C = Lib_C;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }


   
    @Override
    public String toString() {
        return "Produit{" + "libelle=" + libelle + ", Lib_C=" + Lib_C + ", Lib_S=" + Lib_S + ", image=" + image + ", description=" + description + ", prix=" + prix + '}';
    }

 
    
}
