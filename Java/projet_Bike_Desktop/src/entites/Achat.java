/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author aymen
 */
public class Achat {
    
    private int id;
    private int ID_U;
    private int ID_A;
    private Date date_d;
    private Date date_f;
    private float prix;
    private String Image;

    public Achat(int ID_U, int ID_A) {
        this.ID_U = ID_U;
        this.ID_A = ID_A;
    }

    public Achat(int id, int ID_U, int ID_A, float prix, String Image) {
        this.id = id;
        this.ID_U = ID_U;
        this.ID_A = ID_A;
        this.prix = prix;
        this.Image = Image;
    }


    

    public Achat(int id, int ID_U, int ID_A, Date date_d, Date date_f, float prix, String Image) {
        this.id = id;
        this.ID_U = ID_U;
        this.ID_A = ID_A;
        this.date_d = date_d;
        this.date_f = date_f;
        this.prix = prix;
        this.Image = Image;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + this.id;
        hash = 31 * hash + this.ID_U;
        hash = 31 * hash + this.ID_A;
        hash = 31 * hash + Objects.hashCode(this.date_d);
        hash = 31 * hash + Objects.hashCode(this.date_f);
        hash = 31 * hash + Float.floatToIntBits(this.prix);
        hash = 31 * hash + Objects.hashCode(this.Image);
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
        final Achat other = (Achat) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.ID_U != other.ID_U) {
            return false;
        }
        if (this.ID_A != other.ID_A) {
            return false;
        }
        if (Float.floatToIntBits(this.prix) != Float.floatToIntBits(other.prix)) {
            return false;
        }
        if (!Objects.equals(this.Image, other.Image)) {
            return false;
        }
        if (!Objects.equals(this.date_d, other.date_d)) {
            return false;
        }
        if (!Objects.equals(this.date_f, other.date_f)) {
            return false;
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getID_U() {
        return ID_U;
    }

    public void setID_U(int ID_U) {
        this.ID_U = ID_U;
    }

    public int getID_A() {
        return ID_A;
    }

    public void setID_A(int ID_A) {
        this.ID_A = ID_A;
    }

    public Date getDate_d() {
        return date_d;
    }

    public void setDate_d(Date date_d) {
        this.date_d = date_d;
    }

    public Date getDate_f() {
        return date_f;
    }

    public void setDate_f(Date date_f) {
        this.date_f = date_f;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }
    
    
}
