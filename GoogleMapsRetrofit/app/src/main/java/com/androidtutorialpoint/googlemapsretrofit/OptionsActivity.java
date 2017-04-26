package com.androidtutorialpoint.googlemapsretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.google.android.gms.maps.GoogleMap;


public class OptionsActivity extends AppCompatActivity implements OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Normal");
        categories.add("Hybrid");
        categories.add("Satellite");
        categories.add("Terrain");
        categories.add("None");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // On selecting a spinner item
        String item = adapterView.getItemAtPosition(i).toString();

        // Showing selected spinner item
        Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

        switch (item){

            case("Normal"):
                MapsActivity.mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case("Satellite"):
                MapsActivity.mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case("Hybrid"):
                MapsActivity.mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case("Terrain"):
                MapsActivity.mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
            case("None"):
                MapsActivity.mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                break;

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
