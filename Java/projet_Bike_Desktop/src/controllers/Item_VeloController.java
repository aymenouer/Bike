/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Service.ReservationService;
import Service.Velo_Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import static controllers.FrontCrontroller.ACOUNT_SID;
import static controllers.FrontCrontroller.AUTH_TOKEN;
import static controllers.FrontCrontroller.indiceVelo;
import entites.Reservation;
import entites.Session;
import entites.Velo;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class Item_VeloController implements Initializable {

    @FXML
    private HBox itemC;
    @FXML
    private Label libelle;
    @FXML
    private ImageView imagevelo;
    @FXML
    private Button reserver;
    @FXML
    private Label description;
    @FXML
    private Label categorie;
    @FXML
    private Label prix;
    @FXML
    private Label site;
    @FXML
    private DatePicker date_D;
    @FXML
    private DatePicker date_f;
    Velo_Service service = new Velo_Service();
    Velo velo = null;

    ReservationService service_reservation = new ReservationService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            service_reservation.mise_a_jour();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        velo = service.get_vel(indiceVelo);
        libelle.setText(velo.getLibelle());
        description.setText(velo.getDescription());
        categorie.setText(velo.getLib_C());
        prix.setText(String.valueOf(velo.getPrix()) + " DT");
        String ImageUrl = "http://localhost/bike/web/uploads/images/";
        Image image = new Image(ImageUrl + velo.getImage());
        site.setText(velo.getLib_S());
        imagevelo.setImage(image);
        reserver.setOnAction(w
                -> {

            float res1 = 0;
            float res2 = 0;
            LocalDate d = LocalDate.now();
            Date date = java.sql.Date.valueOf(d);
            if (date_D.getValue() != null && date_f.getValue() != null) {

                Date dd = new java.sql.Date(new Date(date_D.getEditor().getText()).getTime());

                Date df = new java.sql.Date(new Date(date_f.getEditor().getText()).getTime());

                long diff1 = df.getTime() - dd.getTime();

                long diff = dd.getTime() - date.getTime();
                res1 = (diff / (1000 * 60 * 60 * 24));
                System.out.println(res1);
                res2 = (diff1 / (1000 * 60 * 60 * 24));
                System.out.println(res2);
            }

            if (date_f.getValue() == null || date_D.getValue() == null) {

                AlertDialog.showNotification("Erreur Date !", "Vérifier votre champs !", AlertDialog.image_cross);
            } else if ((res1 < 0)) {

                AlertDialog.showNotification("Erreur Date !", "Vérifier votre date !", AlertDialog.image_cross);

            } else if ((res2 < 0)) {

                AlertDialog.showNotification("Erreur Date !", "Aujourd'hui est " + date, AlertDialog.image_cross);

            } else {

                Twilio.init(ACOUNT_SID, AUTH_TOKEN);
                String numero_user = "+216" + String.valueOf(Session.get().getNumber());
                Message message = Message
                        .creator(new PhoneNumber(numero_user), // to
                                new PhoneNumber("+13213254739"), // from
                                "reservation effectué avec success")
                        .create();
                Date dd = new java.sql.Date(new Date(date_D.getEditor().getText()).getTime());

                Date df = new java.sql.Date(new Date(date_f.getEditor().getText()).getTime());

                try {
                    Reservation r = new Reservation(Session.getCurrentSession(), velo.getId_v(), (java.sql.Date) dd, (java.sql.Date) df, res2 * velo.getPrix());
                    service_reservation.ajouterR(r);
                    AlertDialog.showNotification("Reservation", "Reserve ", AlertDialog.image_checked);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

            }

        }
        );

    }

}
