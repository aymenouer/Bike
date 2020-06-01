
package Service;

import Utils.MyConnexion;
import entites.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author dell
 */
public class EvenementService implements EvenementServiceInterface{
    
    Connection cn=MyConnexion.getInsCon().getcnx();
    
    @Override
    public void addEvenement(Evenement q,String Des)throws SQLDataException{
        
         
 String query ="INSERT INTO `evenements`( `lieu`, `titre`, `nbreplaces`,`DateDebut`,`DateFin`,`image`,discription,nbreparticipants,rate,vote) VALUES (?,?,?,?,?,?,?,0,0,0)";
 
         PreparedStatement st;
        
        try {
            st = cn.prepareStatement(query);
                st.setString(1,q.getLieu());
                st.setString(2,q.getTitre());
                st.setInt(3,q.getNbreplaces());
                st.setDate(4,q.getDateDebut());
                st.setDate(5,q.getDateFin());
                st.setString(6,q.getImage());
                st.setString(7,Des);
                st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                

    }

    @Override
    public boolean ModifierEvenenment(Evenement e) throws SQLDataException {

               
                String query = "UPDATE `evenements` SET `lieu`=?,`nbreplaces`=?,`nbreparticipants`=?,`DateDebut`=?,`DateFin`=?,`titre`=? WHERE `id` = ?";
		PreparedStatement st;
        try {
                st = cn.prepareStatement(query);
                st.setString(1,e.getLieu());
                st.setInt(2,e.getNbreplaces());
                st.setInt(3,e.getNbreparticipants());
                st.setDate(4,e.getDateDebut());
                st.setDate(5,e.getDateFin());
                st.setString(6,e.getTitre());
                st.setInt(7,e.getId());
                st.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                
               
    }

    @Override
    public ObservableList<Evenement> getAllEvenement() throws SQLDataException {

        
        List<Evenement> list =new ArrayList<Evenement>();
        int count =0;
        
        String requete="select * from evenements";
         try{
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                Evenement e = new Evenement();
                e.setId(rs.getInt("id"));
                e.setLieu(rs.getString("lieu"));
                e.setTitre(rs.getString("titre"));
                e.setNbreplaces(rs.getInt("nbreplaces"));
                e.setNbreparticipants(rs.getInt("nbreparticipants"));
                e.setDateDebut(rs.getDate("DateDebut"));
                e.setDateFin(rs.getDate("DateFin"));
                  e.setImage(rs.getString("image"));
                
                list.add(e);
                
                count++;
            }
            if(count == 0){
                return null;
           }else{
             ObservableList lc_final = FXCollections.observableArrayList(list);

               return lc_final;
            
           
        }
         }
        catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
           
}
       public Evenement get_Evenment(int i) {
        Evenement e = new Evenement();
        int nombre = 0;
        String requete = "select * from evenements";
        try {
            PreparedStatement ps = cn.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                if (i == nombre) {

                e.setId(rs.getInt("id"));
                e.setLieu(rs.getString("lieu"));
                e.setTitre(rs.getString("titre"));
                e.setNbreplaces(rs.getInt("nbreplaces"));
                e.setNbreparticipants(rs.getInt("nbreparticipants"));
                e.setDateDebut(rs.getDate("DateDebut"));
                e.setDateFin(rs.getDate("DateFin"));
                e.setImage(rs.getString("image"));
                }
                nombre++;

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return e;

    }
    
    
    
        public ObservableList<Evenement> serach(String cas) throws SQLException {
        ObservableList<Evenement> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `evenements` where  titre LIKE '%" + cas + "%'or  lieu LIKE '%" + cas + "%' or  DateDebut LIKE '%" + cas + "%' or  DateFin LIKE '%" + cas + "%' or nbreplaces LIKE '%" + cas + "%' or  nbreparticipants LIKE '%" + cas + "%' ";
       try {
            PreparedStatement ps = cn.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                     Evenement e = new Evenement();
                e.setId(rs.getInt("id"));
                e.setLieu(rs.getString("lieu"));
                e.setTitre(rs.getString("titre"));
                e.setNbreplaces(rs.getInt("nbreplaces"));
                e.setNbreparticipants(rs.getInt("nbreparticipants"));
                e.setDateDebut(rs.getDate("DateDebut"));
                e.setDateFin(rs.getDate("DateFin"));
                  e.setImage(rs.getString("image"));
                
                list.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
    
    
             public int Affichertaille() throws SQLException {
        int nbr = 0;        
        String requete = "SELECT COUNT(*) as nbr FROM evenements"       ;
                try {
            PreparedStatement ps = cn.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              nbr=rs.getInt("nbr");
            }
                   
        } catch (SQLException ex) {
            System.out.println(ex);
        }
                        System.out.println("le nombre est : "+nbr);   
        return nbr;

    }
    
    
    

    @Override
    public boolean deleteEvenement(int idEvenement) throws SQLDataException {

        
        
        try {
            
            Statement st=cn.createStatement();
            String req= "DELETE FROM `evenements` WHERE `id` ="+idEvenement;
            st.executeUpdate(req);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    @Override
    public List<Evenement> afficheEvenement(String titre) throws SQLDataException
    {

       List<Evenement> list=new ArrayList<Evenement>();
           try {
               String req="SELECT * FROM `evenements` where `titre`='"+titre+"'";
               Statement st;
                 st = cn.createStatement();
                 ResultSet rs=st.executeQuery(req);
               
                while(rs.next())
                       {
                           Evenement e= new Evenement();
                           e.setId(rs.getInt("id"));
                e.setLieu(rs.getString("lieu"));
                e.setTitre(rs.getString("titre"));
                e.setNbreplaces(rs.getInt("nbreplaces"));
                e.setNbreparticipants(rs.getInt("nbreparticipants"));
                e.setDateDebut(rs.getDate("DateDebut"));
                e.setDateFin(rs.getDate("DateFin"));
               
                           list.add(e);
            
                       }    
           } catch (SQLException ex) {
               Logger.getLogger(SponsorService.class.getName()).log(Level.SEVERE, null, ex);
           }
          return list;
    }

    @Override
    public boolean ModifierEvenenmentPlace(Evenement e) throws SQLDataException {
        String query = "UPDATE `evenements` SET `nbreparticipants`=? WHERE `id` = ?";
		PreparedStatement st;
        try {
                st = cn.prepareStatement(query);
            
                st.setInt(1,e.getNbreparticipants());
                st.setInt(2,e.getId());
                st.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    
    
    
      public List<Participer> findUSerByIdParticip(int id) throws SQLException {
               List<Participer> list=new ArrayList<Participer>();

     
            
             String req="select * from participer where idEvenement="+id;
             Statement st=cn.createStatement();
             ResultSet rs=st.executeQuery(req);
             while(rs.next())
             {
                 Participer e= new Participer();
                 e.setId(rs.getInt(1));
                 e.setIdEvent(rs.getInt(2));
                 e.setIdUuser(rs.getInt(3));
               
                list.add(e);
                         
          
         } 
    return list ;
    
}
      
      
       public List<Integer> getAllEvent() throws SQLDataException {

        
        List<Integer> list =new ArrayList<Integer>();
        int count =0;
        
        String requete="select id from evenements";
         try{
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                Evenement e = new Evenement();
                e.setId(rs.getInt(1));
               
                  int i = e.getId();
                
                list.add(i);
                
                count++;
            }
            if(count == 0){
                return null;
           }else{

               return list;
            
           
        }
         }
        catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
           
}
    public ObservableList<String> get_List_Evement_titre() {
        ObservableList<String> list = FXCollections.observableArrayList();

        String requete = "SELECT * FROM `evenements` ";
        try {
            PreparedStatement ps = cn.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("titre"));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}

