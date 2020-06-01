/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entites.*;
import Service.*;
import javafx.scene.control.ListCell;

/**
 *
 * @author dell
 */
public class ListViewEvent extends ListCell<Evenement> {
    
    
     @Override
     public void updateItem(Evenement e, boolean empty)
    {
        super.updateItem(e,empty);
        if(e != null)
        {
            
            EventItemController data = new EventItemController();
            data.setInfo(e);
            setGraphic(data.getBox());
        }
    }
    
}
