/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entites;

import java.sql.Date;
import java.util.Objects;


/**
 *
 * @author asus
 */
public class Reservation {
 private int idR; 
 private int ID_U;
 private int idV;
 private Date DATE_D;
 private Date DATE_F;
 private float prix;  

    public Reservation(int ID_U, int idV, Date DATE_D, Date DATE_F, float prix) {
        this.ID_U = ID_U;
        this.idV = idV;
        this.DATE_D = DATE_D;
        this.DATE_F = DATE_F;
        this.prix = prix;
    }
    public Reservation(int ID_U, int idV, Date DATE_D, Date DATE_F, float prix,int idR) {
        this.ID_U = ID_U;
        this.idV = idV;
        this.DATE_D = DATE_D;
        this.DATE_F = DATE_F;
        this.prix = prix;
             this.idR = idR;
    }
    public void setIdR(int idR) {
        this.idR = idR;
    }

    public int getIdR() {
        return idR;
    }

    public int getID_U() {
        return ID_U;
    }

    public void setID_U(int ID_U) {
        this.ID_U = ID_U;
    }

    public int getIdV() {
        return idV;
    }

    public void setIdV(int idV) {
        this.idV = idV;
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

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Reservation{" + "ID_U=" + ID_U + ", idV=" + idV + ", DATE_D=" + DATE_D + ", DATE_F=" + DATE_F + ", prix=" + prix + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.ID_U;
        hash = 53 * hash + this.idV;
        hash = 53 * hash + Objects.hashCode(this.DATE_D);
        hash = 53 * hash + Objects.hashCode(this.DATE_F);
        hash = 53 * hash + Float.floatToIntBits(this.prix);
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
        final Reservation other = (Reservation) obj;
        if (this.ID_U != other.ID_U) {
            return false;
        }
        if (this.idV != other.idV) {
            return false;
        }
        if (Float.floatToIntBits(this.prix) != Float.floatToIntBits(other.prix)) {
            return false;
        }
        if (!Objects.equals(this.DATE_D, other.DATE_D)) {
            return false;
        }
        if (!Objects.equals(this.DATE_F, other.DATE_F)) {
            return false;
        }
        return true;
    }
 
 

  
  
    
    
    
}
