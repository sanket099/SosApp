package com.sanket.safewe;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class near extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap gmapView;
    private MapView mapView;
    SharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near);

        sharedPreference = new SharedPreference(this);

        mapView = (MapView)findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);


    }
   /* @Override
    public void onMapReady(MapboxMap mapboxMap) {

        mapboxMap.setMinZoomPreference(4.5);
        mapboxMap.setMaxZoomPreference(18.5);


        mapboxMap.setPadding(20, 20, 20, 20);


    }*/

   /* @Override
    public void onMapError(int i, String s) {
    }*/

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
    public void onMapReady(GoogleMap mMap) {


        gmapView = mMap;

        // For showing a move to my location button
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            requestPermissions();
            return;
        }
       // gmapView.setMyLocationEnabled(true);
        gmapView.getUiSettings().setMyLocationButtonEnabled(true);
        gmapView.setIndoorEnabled(false);

        mapView.setPadding(0,150,0,0);

        double lat = Double.parseDouble(sharedPreference.get_saved_lat());
        double longi = Double.parseDouble(sharedPreference.get_saved_longi());

        Call<NearByListClass> call  = retrofit_client.getInstance().getapi().getnear(String.valueOf(lat), String.valueOf(longi));
        call.enqueue(new Callback<NearByListClass>() {
            @Override
            public void onResponse(Call<NearByListClass> call, Response<NearByListClass> response) {

                NearByListClass nearme = response.body();
                System.out.println("hello");
                System.out.println(nearme);
                List<NearbyClass> ans = nearme.getAns();




                for(int i = 0; i < ans.size() ; i++) {
                    System.out.println(i);
                    System.out.println(ans.get(i).getLat());
                    System.out.println(ans.get(i).getLongi());
                    System.out.println(ans.get(i).getUid());

                    gmapView.addMarker(new MarkerOptions().position
                            (new LatLng(Double.parseDouble(ans.get(i).getLat()) , Double.parseDouble(ans.get(i).getLongi()) ))
                            .title("USER LOCATION \n" + "lat : " + ans.get(i).getLat() + "  long : " + ans.get(i).getLongi()));
                }

            }

            @Override
            public void onFailure(Call<NearByListClass> call, Throwable t) {
                Toast.makeText(near.this,t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        System.out.println(lat + " " + longi);

        gmapView.addMarker(new MarkerOptions().position(new LatLng(
                lat, longi)).title("SOS LOCATION \n" + "lat : " + String.valueOf(lat) + "  long : " + longi));

        /* this is done for animating/moving camera to particular position */
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(
                lat, longi)).zoom(10).tilt(0).build();
        gmapView.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }
    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                11
        );
    }
}
