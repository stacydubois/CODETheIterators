package com.CODETheIterators;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.*;
import java.util.ArrayList;
//import android.view.GestureDetector;
//import android.view.MotionEvent;
//import com.google.android.gms.maps.model.LatLngBounds;

//import java.io.File;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    static final LatLng CANADA = new LatLng(60, -95);
    protected GoogleMap map;
    KMLParser kml;


    // protected Kml kml;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        if(map != null)
        {
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(CANADA, 15));
            map.animateCamera(CameraUpdateFactory.zoomIn());
            map.animateCamera(CameraUpdateFactory.zoomTo(3), 4000, null);
            kml = new KMLParser("EventMapLayer.kml");

            populateMap(kml.GetCoords());

        }
    }
    public void populateMap(ArrayList<LatLng> coords)
    {

        for(int i = 0; i < coords.size(); i++) {
            Marker marker = map.addMarker(new MarkerOptions().position(coords.get(i)));
            //marker;
        }
    }
   @Override
    protected void onStart() {
        super.onStart();    //To change body of overridden methods use File | Settings | File Templates.

    }


    @Override
    protected void onRestart() {
        super.onRestart();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected void onResume() {
        super.onResume();    //To change body of overridden methods use File | Settings | File Templates.
       // map.moveCamera(CameraUpdateFactory.newLatLng(CANADA));
    }

    @Override
    protected void onPause() {
        super.onPause();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected void onStop() {
        super.onStop();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
