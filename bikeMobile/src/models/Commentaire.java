/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Rzouga
 */
public class Commentaire {
    
      private int idEvt;
      private int id;
      private int idComment;
      private String Comment;

    public Commentaire() {
    }

    public Commentaire(int idEvt, int id, int idComment, String Comment) {
        this.idEvt = idEvt;
        this.id = id;
        this.idComment = idComment;
        this.Comment = Comment;
    }

    public int getIdEvt() {
        return idEvt;
    }

    public void setIdEvt(int idEvt) {
        this.idEvt = idEvt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "idEvt=" + idEvt + ", id=" + id + ", idComment=" + idComment + ", Comment=" + Comment + '}';
    }
      
      
      
    
}
