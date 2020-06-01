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
public class Velo extends Produit {
        private String type; 
    private int age; 
    private String couleur; 
    private String etat; 
    private int id_v;

    public int getId_v() {
        return id_v;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.type);
        hash = 83 * hash + this.age;
        hash = 83 * hash + Objects.hashCode(this.couleur);
        hash = 83 * hash + Objects.hashCode(this.etat);
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
        final Velo other = (Velo) obj;
        if (this.age != other.age) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.couleur, other.couleur)) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        return true;
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
