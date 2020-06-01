/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author aymen
 */
public class Piece extends Produit{
      private int quantite;
  private int id_Pi;
  private String etat;
    public Piece(int id_p, String libelle, String Lib_C, String Lib_S, String image, String description, Float prix) {
        super(id_p, libelle, Lib_C, Lib_S, image, description, prix);
    }

    public int getId_Pi() {
        return id_Pi;
    }

    public String getEtat() {
        return etat;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setId_Pi(int id_Pi) {
        this.id_Pi = id_Pi;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Piece(int quantite, int id_Pi, String etat, String libelle, String Lib_C, String Lib_S, String image, String description, Float prix) {
        super(libelle, Lib_C, Lib_S, image, description, prix);
        this.quantite = quantite;
        this.id_Pi = id_Pi;
        this.etat = etat;
    }
     public Piece(String libelle, String Lib_C, String Lib_S, String image, String description, Float prix, int quan,String etat) 
    {
        super(libelle, Lib_C, Lib_S, image, description, prix);
        quantite=quan;
          this.etat = etat;
    }

    public Piece(int quantite, int id_Pi, String etat, int id_p, String libelle, String Lib_C, String Lib_S, String image, String description, Float prix) {
        super(id_p, libelle, Lib_C, Lib_S, image, description, prix);
        this.quantite = quantite;
        this.id_Pi = id_Pi;
        this.etat = etat;
    }
    
    
}
