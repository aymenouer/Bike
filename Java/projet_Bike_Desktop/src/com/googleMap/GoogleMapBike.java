/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googleMap;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;

import com.lynden.gmapsfx.javascript.object.*;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author aymen
 */
public class GoogleMapBike extends Application implements MapComponentInitializedListener {

  GoogleMapView mapView;
GoogleMap map;

    @Override
    public void start(Stage stage) throws Exception {
        mapView = new GoogleMapView();
    mapView.addMapInializedListener(this);

    Scene scene = new Scene(mapView);

    stage.setTitle("Google Maps Bike");
    stage.setScene(scene);
    stage.show();
    
    }

  @Override
 public void mapInitialized() {
    //Set the initial properties of the map.
    MapOptions mapOptions = new MapOptions();

    mapOptions.center(new LatLong(47.6097, -122.3331))
            
            .overviewMapControl(false)
            .panControl(false)
            .rotateControl(false)
            .scaleControl(false)
            .streetViewControl(false)
            .zoomControl(false)
            .zoom(12);

    map = mapView.createMap(mapOptions);

    //Add a marker to the map
    MarkerOptions markerOptions = new MarkerOptions();

    markerOptions.position( new LatLong(47.6, -122.3) )
                .visible(Boolean.TRUE)
                .title("My Marker");

    Marker marker = new Marker( markerOptions );

    map.addMarker(marker);

}

public static void main(String[] args) {
    launch(args);
}
    
}
