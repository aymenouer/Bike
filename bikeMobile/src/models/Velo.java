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
public class Velo extends Produit {
        private String type; 
    private int age; 
    private String couleur; 
    private String etat; 
    private int id_v;

    public int getId_v() {
        return id_v;
    }

    public Velo() {
        super(null, null, null, null, null, null);
    }
    

    public Velo(String type, int age, String couleur, String etat, int id_v, String libelle, String Lib_C, String Lib_S, String image, String description, Float prix) {
        super(libelle, Lib_C, Lib_S, image, description, prix);
        this.type = type;
        this.age = age;
        this.couleur = couleur;
        this.etat = etat;
        this.id_v = id_v;
    }

    public Velo(String type, int age, String couleur, String etat, String libelle, String Lib_C, String Lib_S, String image, String description, Float prix) {
        super(libelle, Lib_C, Lib_S, image, description, prix);
        this.type = type;
        this.age = age;
        this.couleur = couleur;
        this.etat = etat;
    }

  
   public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Velo{" + "type=" + type + ", age=" + age + ", couleur=" + couleur + ", etat=" + etat + '}';
    }


    
    
}
