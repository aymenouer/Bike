/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author Rajia
 */
public class Accessoire extends Produit{
    
  private int quantite;
  private int id_A;

    public int getId_A() {
        return id_A;
    }

    public void setId_A(int id_A) {
        this.id_A = id_A;
    }

    public Accessoire(int quantite, int id_A, String libelle, String Lib_C, String Lib_S, String image, String description, Float prix) {
        super(libelle, Lib_C, Lib_S, image, description, prix);
        this.quantite = quantite;
        this.id_A = id_A;
    }

    public Accessoire(int quantite, int id_A, int id_p, String libelle, String Lib_C, String Lib_S, String image, String description, Float prix) {
        super(id_p, libelle, Lib_C, Lib_S, image, description, prix);
        this.quantite = quantite;
        this.id_A = id_A;
    }
  
    public Accessoire(String libelle, String Lib_C, String Lib_S, String image, String description, Float prix, int quan) 
    {
        super(libelle, Lib_C, Lib_S, image, description, prix);
        quantite=quan;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    public int getQuantite() {
        return quantite;
    }

    @Override
    public String toString() {
        return "Accessoire{" + super.toString() +"quantite=" + quantite + '}';
    }
    
  
    
}
