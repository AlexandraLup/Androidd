package com.example.lab2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SensorsActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        ListView viewSensors = (ListView) findViewById(R.id.sensor);

        ArrayList<String> arrayList = new ArrayList<String>();
        for(Sensor s:sensors) {
            arrayList.add("Name: " +s.getName()+"\n" +"Type: " + s.getStringType()+ "\n"+ "Version: "+s.getVersion() + "\n" + "Vendor: " + s.getVendor()+"\n");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
        viewSensors.setAdapter(adapter);
    }
}
