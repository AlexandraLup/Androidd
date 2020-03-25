package com.example.lab2;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import androidx.core.content.ContextCompat;
import static android.content.Context.LOCATION_SERVICE;


public class LocationListenerNew  implements LocationListener{

    Context context;

    public LocationListenerNew (Context context){
        super();
        this.context = context;
    }

    public Location getLocation(){
        if (ContextCompat.checkSelfPermission( context, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
            Log.e("fist","error");
            return null;
        }
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (isGPSEnabled){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000,10,this);
                Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                return loc;
            }else{
                Log.e("sec","error");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
