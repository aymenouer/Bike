/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Objects;

/**
 *
 * @author dell
 */
public class CommentEvenement {
      private Evenement idEvt;
      private User id;
      private int idComment;
      private String Comment;

    public User getId() {
        return id;
    }

    public int getIdComment() {
        return idComment;
    }

    public String getComment() {
        return Comment;
    }

    public void setId(User id) {
        this.id = id;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    public Evenement getIdEvt() {
        return idEvt;
    }

    public void setIdEvt(Evenement idEvt) {
        this.idEvt = idEvt;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.idEvt);
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + this.idComment;
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
        final CommentEvenement other = (CommentEvenement) obj;
        if (this.idComment != other.idComment) {
            return false;
        }
        if (!Objects.equals(this.idEvt, other.idEvt)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommentEvenement{" + "idEvt=" + idEvt + ", id=" + id + ", idComment=" + idComment + ", Comment=" + Comment + '}';
    }


    public CommentEvenement(Evenement idEvt, User id, int idComment, String Comment) {
        this.idEvt = idEvt;
        this.id = id;
        this.idComment = idComment;
        this.Comment = Comment;
    }

    public CommentEvenement() {
    }

    public CommentEvenement(Evenement idEvt, User id, String Comment) {
        this.idEvt = idEvt;
        this.id = id;
        this.Comment = Comment;
    }

  
    

      
      
}
