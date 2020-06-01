/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entites.*;
import java.sql.SQLDataException;
import java.util.List; 

/**
 *
 * @author dell
 */
public interface SponsorServiceInterface {
    
    public void addSponsor(Sponsor s) throws SQLDataException;
    public void modifieSponsor(Sponsor s)throws SQLDataException;
    public boolean deleteSponsor(int idSponsor) throws SQLDataException;
    public List<Sponsor> afficheSponsor(String nom) throws SQLDataException;
    public List<Sponsor> AllSponsor ()throws SQLDataException;
}
