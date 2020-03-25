package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.location.Location;
import android.os.Bundle;
import android.widget.TextView;

public class LocationActivity extends AppCompatActivity {

    TextView l1, l2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        l1 = (TextView) findViewById(R.id.latitudine);
        l2 = (TextView) findViewById(R.id.longitudine);

        ActivityCompat.requestPermissions(LocationActivity.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        LocationListenerNew locatie = new LocationListenerNew(getApplicationContext());
        Location l = locatie.getLocation();

        if( l == null){
            l1.setText("Latitudine: " + 0);
            l2.setText("Longitudine: "+ 0);
        }else {
            double lat = l.getLatitude();
            double lon = l.getLongitude();
            l1.setText("Latitudine: " + lat);
            l2.setText("Longitudine: "+ lon);
        }


    }
}
