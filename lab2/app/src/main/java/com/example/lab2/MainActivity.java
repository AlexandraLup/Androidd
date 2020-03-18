package com.example.lab2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.preference.PreferenceFragment;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String KEY_TEXT = "text_key";
    String products[] = {"TV", "Tel", "Masina", "Laptop","Tel2","Tel3","Tel4"};
    ListView listView;
    Button button;
    TextView t1, t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBut = new Intent(MainActivity.this, Optiuni.class);
                startActivity(intentBut);
            }
        });

        Log.d("lifecycle", "Create");
        listView = (ListView)findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, products);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                t1 = (TextView) findViewById(R.id.textView);
                t1.setText("Elementul" + position );
            }
        });


        t2 = (TextView) findViewById(R.id.textView4);
        setupSharedPreferences();
    }

    private void setupSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if (key.equals("text")) {
            t2.setText(sharedPreferences.getString("text","User"));
        }
        else if (key.equals("checkbox")){
            if (sharedPreferences.getBoolean("checkbox", true) == true){
                Log.d("TRUEE", "TRUE");
            }
            else
                Log.d("FALSEE", "FALSE");
        }
    }





    @Override
    protected void onStart() {
        super.onStart();
        Log.d("lifecycle", "Start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifecycle", "Resume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("lifecycle", "Restart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lifecycle", "Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("lifecycle", "Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle", "Destroy");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.ex_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_login, null);
                final EditText mEmail = (EditText) mView.findViewById(R.id.etEmail);
                final EditText mPassword = (EditText) mView.findViewById(R.id.etPassword);
                Button mLogin = (Button) mView.findViewById(R.id.btnLogin);

                mLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!mEmail.getText().toString().isEmpty() && !mPassword.getText().toString().isEmpty()){
                            Toast.makeText(MainActivity.this,
                                    "Login successful",
                                    Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(MainActivity.this,
                                    "Emplty fields",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                return true;

            case R.id.item2:
                Intent intent2 = new Intent(MainActivity.this, MenuAboutUs.class);
                startActivity(intent2);
                return true;
            case R.id.item3:
                Intent intent3 = new Intent(MainActivity.this, MenuHelp.class);
                startActivity(intent3);
                return true;
            case R.id.item4:
                Intent intent4 = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent4);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putString(KEY_TEXT,  t1.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        t1 = (TextView) findViewById(R.id.textView);
        t1.setText(savedInstanceState.getString(KEY_TEXT));
    }



}
