package com.sanket.safewe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;
    SharedPreference sharedPreference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mapView = findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        sharedPreference = new SharedPreference(this);
        mapView.getMapAsync(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }



    @Override
    public void onMapReady(GoogleMap map) {
        /*googleMap.setMinZoomPreference(4.5);
        googleMap.setMaxZoomPreference(18.5);
*/

        map.setPadding(20, 20, 20, 20);

        double lat = Double.parseDouble(sharedPreference.get_saved_lat());
        double longi = Double.parseDouble(sharedPreference.get_saved_longi());
        map.addMarker(new MarkerOptions().position(new LatLng(
                lat, longi)).title("SOS LOCATION \n" + "lat : " + String.valueOf(lat) + "  long : " + longi));

        /* this is done for animating/moving camera to particular position */
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(
                lat, longi)).zoom(10).tilt(0).build();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


    }
}