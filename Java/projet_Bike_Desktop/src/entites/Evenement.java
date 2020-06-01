
package entites;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author dell
 */
public class Evenement {
    private int id ,nbreplaces,nbreparticipants ;
    private String lieu ,titre , image ;
    private Date DateDebut , DateFin ;

 
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        hash = 37 * hash + this.nbreplaces;
        hash = 37 * hash + this.nbreparticipants;
        hash = 37 * hash + Objects.hashCode(this.lieu);
        hash = 37 * hash + Objects.hashCode(this.titre);
        hash = 37 * hash + Objects.hashCode(this.DateDebut);
        hash = 37 * hash + Objects.hashCode(this.DateFin);
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
        final Evenement other = (Evenement) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.nbreplaces != other.nbreplaces) {
            return false;
        }
        if (this.nbreparticipants != other.nbreparticipants) {
            return false;
        }
        if (!Objects.equals(this.lieu, other.lieu)) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.DateDebut, other.DateDebut)) {
            return false;
        }
        if (!Objects.equals(this.DateFin, other.DateFin)) {
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

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nbreplaces=" + nbreplaces + ", nbreparticipants=" + nbreparticipants + ", lieu=" + lieu + ", titre=" + titre + ", DateDebut=" + DateDebut + ", DateFin=" + DateFin + '}';
    }

    public Evenement() {
    }

    public Evenement(int id, int nbreplaces, String lieu, String titre, Date DateDebut, Date DateFin) {
        this.id = id;
        this.nbreplaces = nbreplaces;
        this.lieu = lieu;
        this.titre = titre;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
    }

    public Evenement(int nbreplaces, String lieu, String titre, Date DateDebut, Date DateFin , String image) {
        this.nbreplaces = nbreplaces;
        this.lieu = lieu;
        this.titre = titre;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.image = image ;
    }

    public Evenement(int id, int nbreplaces, int nbreparticipants, String lieu, String titre, Date DateDebut, Date DateFin) {
        this.id = id;
        this.nbreplaces = nbreplaces;
        this.nbreparticipants = nbreparticipants;
        this.lieu = lieu;
        this.titre = titre;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Evenement(int id, int nbreplaces, int nbreparticipants, String lieu, String titre, String image, Date DateDebut, Date DateFin) {
        this.id = id;
        this.nbreplaces = nbreplaces;
        this.nbreparticipants = nbreparticipants;
        this.lieu = lieu;
        this.titre = titre;
        this.image = image;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
    }

    public Evenement(int nbreplaces, int nbreparticipants, String lieu, String titre, String image, Date DateDebut, Date DateFin) {
        this.nbreplaces = nbreplaces;
        this.nbreparticipants = nbreparticipants;
        this.lieu = lieu;
        this.titre = titre;
        this.image = image;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
    }
    
    
    
}
