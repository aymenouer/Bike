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
public class Categorie {
    private int ID_C;
    private String Lib_C;
    private String Description;
    private String Type;

    public int getID_C() {
        return ID_C;
    }

    public Categorie(int ID_C, String Lib_C, String Description, String Type) {
        this.ID_C = ID_C;
        this.Lib_C = Lib_C;
        this.Description = Description;
        this.Type = Type;
    }

    public Categorie(String Lib_C, String Description, String Type) {
        this.Lib_C = Lib_C;
        this.Description = Description;
        this.Type = Type;
    }

    public String getLib_C() {
        return Lib_C;
    }

    public void setLib_C(String Lib_C) {
        this.Lib_C = Lib_C;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.Lib_C);
        hash = 31 * hash + Objects.hashCode(this.Description);
        hash = 31 * hash + Objects.hashCode(this.Type);
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
        final Categorie other = (Categorie) obj;
        if (!Objects.equals(this.Lib_C, other.Lib_C)) {
            return false;
        }
        if (!Objects.equals(this.Description, other.Description)) {
            return false;
        }
        if (!Objects.equals(this.Type, other.Type)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Categorie{" + "Lib_C=" + Lib_C + ", Description=" + Description + ", Type=" + Type + '}';
    }
    
    
}
