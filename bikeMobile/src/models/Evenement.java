/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author Rzouga
 */
public class Evenement {
    
    private int id ,nbreplaces,nbreparticipants , note ;
    private String lieu ,titre , image,discription ;
    private Date DateDebut , DateFin ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbreplaces() {
        return nbreplaces;
    }

    public void setNbreplaces(int nbreplaces) {
        this.nbreplaces = nbreplaces;
    }

    public int getNbreparticipants() {
        return nbreparticipants;
    }

    public void setNbreparticipants(int nbreparticipants) {
        this.nbreparticipants = nbreparticipants;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Date getDateDebut() {
        return DateDebut;
    }

    public void setDateDebut(Date DateDebut) {
        this.DateDebut = DateDebut;
    }

    public Date getDateFin() {
        return DateFin;
    }

    public void setDateFin(Date DateFin) {
        this.DateFin = DateFin;
    }

    public Evenement(int nbreplaces, int nbreparticipants, String lieu, String titre, String image, String discription, Date DateDebut, Date DateFin) {
        this.nbreplaces = nbreplaces;
        this.nbreparticipants = nbreparticipants;
        this.lieu = lieu;
        this.titre = titre;
        this.image = image;
        this.discription = discription;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
    }

    public Evenement(int id, int nbreplaces, int nbreparticipants, String lieu, String titre, String image, String discription, Date DateDebut, Date DateFin , int note) {
        this.id = id;
        this.nbreplaces = nbreplaces;
        this.nbreparticipants = nbreparticipants;
        this.lieu = lieu;
        this.titre = titre;
        this.image = image;
        this.discription = discription;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.note = note ;
    }

     public Evenement(int id, int nbreplaces, int nbreparticipants, String lieu, String titre, String image, String discription, Date DateDebut, Date DateFin ) {
        this.id = id;
        this.nbreplaces = nbreplaces;
        this.nbreparticipants = nbreparticipants;
        this.lieu = lieu;
        this.titre = titre;
        this.image = image;
        this.discription = discription;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
    }

    public Evenement(int nbreplaces, String lieu, String titre, String image, String discription, Date DateDebut, Date DateFin) {
        this.nbreplaces = nbreplaces;
        this.lieu = lieu;
        this.titre = titre;
        this.image = image;
        this.discription = discription;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
    }
    
    

    public Evenement() {
    }


    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nbreplaces=" + nbreplaces + ", nbreparticipants=" + nbreparticipants + ", note=" + note + ", lieu=" + lieu + ", titre=" + titre + ", image=" + image + ", discription=" + discription + ", DateDebut=" + DateDebut + ", DateFin=" + DateFin + '}';
    }

    
}
