/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

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
import Utils.*;

/**
 *
 * @author dell
 */

public class CommentService implements CommentServiceInterface{
    
     Connection cn=MyConnexion.getInsCon().getcnx();

    @Override
    public void addComment(CommentEvenement c) throws SQLDataException {
        
   String query ="INSERT INTO `comment`( `idevenement`, `iduser`, `commentaire`) VALUES (?,?,?)";
 
         PreparedStatement st;
        
        try {
            st = cn.prepareStatement(query);
            System.out.println("hehehehe"+c.getId().getId());
                        System.out.println(c.getIdEvt().getId());
                st.setInt(1,c.getIdEvt().getId());
                st.setInt(2,c.getId().getId());
                st.setString(3,c.getComment());
                st.executeUpdate();

        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        } catch (Exception ex) {
             Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }

    @Override
    public void modifieComment(CommentEvenement c) throws SQLDataException {
       
         try {
             PreparedStatement pst=cn.prepareStatement("UPDATE `comment` SET `commentaire`=? WHERE `idcomment` = ?");
             pst.setString(1,c.getComment());
             pst.setInt(2,c.getIdComment());
             pst.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
         }

    }

    @Override
    public void deleteComment(int idComment) throws SQLDataException {
    try {
             Statement st=cn.createStatement();
             String req= "DELETE FROM `comment` WHERE `idcomment` ="+idComment;  
             st.executeUpdate(req);
         } catch (SQLException ex) {
             Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
  
    public List<CommentEvenement> getAllComment() throws SQLDataException {
         try {
             List<CommentEvenement> list=new ArrayList<CommentEvenement>();
             int c=0;
             String req="select * from comment";
             Statement st=cn.createStatement();
             ResultSet rs=st.executeQuery(req);
             
             while(rs.next())
             {
                 CommentEvenement cm=new CommentEvenement();
                 cm.setIdComment(rs.getInt(1));
                 cm.setIdEvt(findEvenementById(rs.getInt(2)));
                 cm.setId(findUserById(rs.getInt(3)));
                 cm.setComment(rs.getString(4));
                 
                 list.add(cm);
                 c++;
                 
             }
             if (c == 0)
             {
                 return null;
             }else {
                 return list;
             }
         } catch (SQLException ex) {
             Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
             return null;
        
         }
    }

  

    @Override
    public Evenement findEvenementById(int id) {
        
       try {
             Evenement e=new Evenement();
             int c=0;
             String req="select * from evenements where id="+id;
             Statement st=cn.createStatement();
             ResultSet rs=st.executeQuery(req);
             while(rs.next())
             {
                            e.setId(rs.getInt("id"));
                e.setLieu(rs.getString("lieu"));
                e.setTitre(rs.getString("titre"));
                e.setNbreplaces(rs.getInt("nbreplaces"));
                e.setNbreparticipants(rs.getInt("nbreparticipants"));
                e.setDateDebut(rs.getDate("DateDebut"));
                e.setDateFin(rs.getDate("DateFin"));
                  e.setImage(rs.getString("image"));
                 c++;
                         }
             if(c==0)
             {
                 return null;
             }else {
                 return e;
             }
         } catch (SQLException ex) {
             Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
             return null;

         }
       } 
    

    @Override
    public User findUserById(int id) {
          try {
     
        
             String req="SELECT * FROM `user` WHERE (id =" + String.valueOf(id) + ")";
             Statement st=cn.createStatement();
        
             ResultSet rs=st.executeQuery(req);
             while(rs.next())
             {
                 
                 
           User us = new User(rs.getInt("id"),rs.getString("First_Name"), rs.getString("Last_Name"), rs.getString("email"), rs.getInt("User_Number"), rs.getString("username"), (rs.getString("password").replace("$2y$", "$2a$")), rs.getString("roles"), rs.getInt("User_age"), rs.getString("User_Image"));
             
                return us; 
               
                        
                         }
              System.err.println("hello aymen");
                 return null;
             
         } catch (SQLException ex) {
             Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
             return null;

         }
       }

    @Override
    public ObservableList<CommentEvenement> getAllCommentByEvent(Evenement e) throws SQLDataException {
     try {

             List<CommentEvenement> list=new ArrayList<CommentEvenement>();
             int c=0;
             String req="select * from comment where idevenement = "+String.valueOf(e.getId());
             Statement st=cn.createStatement();
             ResultSet rs=st.executeQuery(req);
             
             while(rs.next())
             {
                 CommentEvenement cm=new CommentEvenement();
                 cm.setIdComment(rs.getInt(1));
                 cm.setIdEvt(findEvenementById(rs.getInt(2)));
                 cm.setId(findUserById(rs.getInt(3)));
                 cm.setComment(rs.getString(4));
                 
                 list.add(cm);
                 c++;
                 
             }
             if (c == 0)
             {
                 return null;
             }else {
                              ObservableList lc = FXCollections.observableArrayList(list);

                 return lc;
             }
         } catch (SQLException ex) {
             Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
             return null;
        
         }    }

    @Override
    public CommentEvenement finCommentById(int id) {
    try {
             CommentEvenement cmt=new CommentEvenement();
             int c=0;
             String req="select * from comment where idcomment="+id;
             Statement st=cn.createStatement();
             ResultSet rs=st.executeQuery(req);
             while(rs.next())
             {
                 cmt.setIdComment(rs.getInt(1));
                 cmt.setIdEvt(findEvenementById(rs.getInt(2)));
                 cmt.setId(findUserById(rs.getInt(3)));
                 cmt.setComment(rs.getString(4));
               
                 c++;
                         }
             if(c==0)
             {
                 return null;
             }else {
                 return cmt;
             }
         } catch (SQLException ex) {
             Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
             return null;

         }    }
  public int propre_comment(int id_u,int id_c) throws SQLException
         {
             
              String query = "select * from comment where ( iduser = "+id_u +" and idcomment = "+id_c+" )";
                    Statement st  = cn.createStatement();
                    ResultSet rs = st.executeQuery(query);
                     while(rs.next()){
                         
                         
                  return 1;
                     
                     }
             
             return 0;
         }
   
    }
    

  

           //  String req="select * from comment where idevenement="+e.getId();

    

