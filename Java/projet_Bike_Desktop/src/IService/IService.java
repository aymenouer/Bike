/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;


import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author House
 */
public interface IService<T> {
    void Ajouter(T t) throws SQLException;
    void Supprimer(int t) throws SQLException;
    void Modifier(T t , int id) throws SQLException;
    List<T> Affichertout() throws SQLException;
}

