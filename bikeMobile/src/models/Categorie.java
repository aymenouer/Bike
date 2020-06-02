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
    public String toString() {
        return "Categorie{" + "Lib_C=" + Lib_C + ", Description=" + Description + ", Type=" + Type + '}';
    }
    
    
}
