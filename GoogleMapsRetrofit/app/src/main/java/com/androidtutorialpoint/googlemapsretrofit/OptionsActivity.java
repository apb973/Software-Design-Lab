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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.maps.GoogleMap;


public class OptionsActivity extends AppCompatActivity implements OnItemSelectedListener
{
    Spinner spinner;
    CheckBox gasPriceCB,restCB,distCB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Spinner element
        spinner = (Spinner) findViewById(R.id.spinner);
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

        //CheckBox
        gasPriceCB = (CheckBox) findViewById(R.id.CheckBoxGP);
        gasPriceCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
//                Search search = new Search();
//                search.setLocation(new Location(MapsActivity.mLastLocation.getLatitude(),MapsActivity.mLastLocation.getLongitude()));
//                search.search();
//                Results results = search.getResults();
//                Toast.makeText(OptionsActivity.this, results.results.get(0).toString() , Toast.LENGTH_LONG).show();

            }
        }
        );
        //CheckBox
        restCB = (CheckBox) findViewById(R.id.CheckBoxRest);
        restCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                Toast.makeText(OptionsActivity.this, "Selected: Restaurant" , Toast.LENGTH_LONG).show();
            }}
        );

        //CheckBox
        distCB = (CheckBox) findViewById(R.id.CheckBoxDist);
        distCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                Toast.makeText(OptionsActivity.this, "Selected: Distance" , Toast.LENGTH_LONG).show();
            }}
        );
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
