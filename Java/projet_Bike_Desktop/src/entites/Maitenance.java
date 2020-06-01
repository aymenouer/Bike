/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Date;

/**
 *
 * @author aymen
 */
public class Maitenance {
    private int id;
    private int id_u;
    private float prix;
    private String problem;
    private String etat;
    private String image;
    private String type;
    private Date DATE_D;
    private Date DATE_F;

    public Maitenance(float prix, String etat) {
        this.prix = prix;
        this.etat = etat;
    }

    public Maitenance(int id_u, float prix, String problem, String etat, String image, String type, Date DATE_D, Date DATE_F) {
        this.id_u = id_u;
        this.prix = prix;
        this.problem = problem;
        this.etat = etat;
        this.image = image;
        this.type = type;
        this.DATE_D = DATE_D;
        this.DATE_F = DATE_F;
    }

    public Maitenance(int id, int id_u, float prix, String problem, String etat, String image, String type, Date DATE_D, Date DATE_F) {
        this.id = id;
        this.id_u = id_u;
        this.prix = prix;
        this.problem = problem;
        this.etat = etat;
        this.image = image;
        this.type = type;
        this.DATE_D = DATE_D;
        this.DATE_F = DATE_F;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_u() {
        return id_u;
    }

    public void setId_u(int id_u) {
        this.id_u = id_u;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDATE_D() {
        return DATE_D;
    }

    public void setDATE_D(Date DATE_D) {
        this.DATE_D = DATE_D;
    }

    public Date getDATE_F() {
        return DATE_F;
    }

    public void setDATE_F(Date DATE_F) {
        this.DATE_F = DATE_F;
    }
    
    
}
