/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class SponsorService implements SponsorServiceInterface {
       Connection cn= MyConnexion.getInsCon().getcnx();

    /**
     *
     * @param s
     * @throws SQLDataException
     */
    

    @Override
    public void addSponsor(Sponsor s) throws SQLDataException {
         
  
               
         
            String req="INSERT INTO `sponsor`( `ide`, `num`, `typesponsor`, `adresse`, `nomsponsor`) VALUES (?,?,?,?,?)";
             PreparedStatement st;
        
        try {
            st = cn.prepareStatement(req);
                st.setInt(1,s.getIde());
                st.setInt(2,s.getNumero());
                st.setString(3,s.getType());
                st.setString(4,s.getAdresse());
                st.setString(5,s.getNom());
                st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifieSponsor(Sponsor s) throws SQLDataException {
      
           
           try {
               PreparedStatement pst=cn.prepareStatement("UPDATE `sponsor` SET `num`=?,`typesponsor`=?,`nomsponsor`=?,`adresse`=? WHERE `idsponsor` = ?");
               pst.setInt(1,s.getNumero());
               pst.setString(2, s.getType());
               pst.setString(3, s.getNom());
               pst.setString(4, s.getAdresse());
               pst.setInt(5, s.getIdSponsor());
               pst.executeUpdate();

           } catch (SQLException ex) {
               Logger.getLogger(SponsorService.class.getName()).log(Level.SEVERE, null, ex);
           }

       
    }

    @Override
    public boolean deleteSponsor(int idSponsor) throws SQLDataException {
           try {
               Statement st=cn.createStatement();
               String req= "DELETE FROM `sponsor` WHERE `idsponsor`="+idSponsor;
               st.executeUpdate(req);
               return true;
           } catch (SQLException ex) {
               Logger.getLogger(SponsorService.class.getName()).log(Level.SEVERE, null, ex);
               return false;
           }
 }

       @Override
        public List<Sponsor> afficheSponsor(String nom) throws SQLDataException {
        List<Sponsor> list=new ArrayList<Sponsor>();
           try {
               String req="SELECT * FROM `sponsor` where `nomsponsor`='"+nom+"'";
               Statement st;
                 st = cn.createStatement();
                 ResultSet rs=st.executeQuery(req);
               
                while(rs.next())
                       {
                           Sponsor s= new Sponsor();
                           s.setIdSponsor(rs.getInt(1));
                           s.setIde(rs.getInt(2));
                           s.setNumero(rs.getInt(3));
                           s.setType(rs.getString(4));
                           s.setNom(rs.getString(5));
                           s.setAdresse(rs.getString(6));
                           list.add(s);
            
                       }    
           } catch (SQLException ex) {
               Logger.getLogger(SponsorService.class.getName()).log(Level.SEVERE, null, ex);
           }
          return list;
    }

    @Override
    public ObservableList<Sponsor> AllSponsor() throws SQLDataException {

       List<Sponsor> list=new ArrayList<Sponsor>();
       
           try {
               String req="SELECT * FROM `sponsor`";
               Statement st;
                 st = cn.createStatement();
                 ResultSet rs=st.executeQuery(req);
               
                while(rs.next())
                       {
                           Sponsor s= new Sponsor();
                           s.setIdSponsor(rs.getInt("idsponsor"));
                            s.setIde(rs.getInt("ide"));

                           s.setNumero(rs.getInt("num"));
                           s.setType(rs.getString("typesponsor"));
                           s.setNom(rs.getString("nomsponsor"));
                           s.setAdresse(rs.getString("adresse"));
                           
                       
               
                           list.add(s);
            
                       }    
           } catch (SQLException ex) {
               Logger.getLogger(SponsorService.class.getName()).log(Level.SEVERE, null, ex);
           }
                    ObservableList lc_final = FXCollections.observableArrayList(list);
          return lc_final; 
    }
    
    
       public ObservableList<Sponsor> serach(String cas) throws SQLException {
        ObservableList<Sponsor> list = FXCollections.observableArrayList();
           String requete = "SELECT * FROM `sponsor` where  num LIKE '%" + cas + "%'or  typesponsor LIKE '%" + cas  + "%' or  adresse LIKE '%" + cas  + "%' or  nomsponsor LIKE '%" + cas + "%' ";
       try {
            PreparedStatement ps = cn.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                      Sponsor s= new Sponsor();
                           s.setIdSponsor(rs.getInt("idsponsor"));
                            s.setIde(rs.getInt("ide"));

                           s.setNumero(rs.getInt("num"));
                           s.setType(rs.getString("typesponsor"));
                           s.setNom(rs.getString("nomsponsor"));
                           s.setAdresse(rs.getString("adresse"));
                           
                       
               
                           list.add(s);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
     public Sponsor findSponsorById(int id) {
        
       try {
             Sponsor s=new Sponsor();
             int c=0;
             String req="select * from sponsor where idsponsor="+id;
             Statement st=cn.createStatement();
             ResultSet rs=st.executeQuery(req);
             while(rs.next())
             {
                 s.setIdSponsor(rs.getInt(1));
                                            s.setIde(rs.getInt(2));

                 s.setNumero(rs.getInt(3));
                 s.setAdresse(rs.getString(5));
               
                 

                 c++;
                         }
             if(c==0)
             {
                 return null;
             }else {
                 return s;
             }
         } catch (SQLException ex) {
             Logger.getLogger(SponsorService.class.getName()).log(Level.SEVERE, null, ex);
             return null;

         }
       } 
      public Sponsor findSponsorByIdevent(int id) {
        
       try {
             Sponsor s=new Sponsor();
             int c=0;
             String req="select * from sponsor where ide="+id;
             Statement st=cn.createStatement();
             ResultSet rs=st.executeQuery(req);
             while(rs.next())
             {
                 s.setIdSponsor(rs.getInt(1));
                  s.setIde(rs.getInt(2));

                 s.setNumero(rs.getInt(2));
                 s.setAdresse(rs.getString(5));
               
                 

                 c++;
                         }
             if(c==0)
             {
                 return null;
             }else {
                 return s;
             }
         } catch (SQLException ex) {
             Logger.getLogger(SponsorService.class.getName()).log(Level.SEVERE, null, ex);
             return null;

         }
       } 
     
    }
