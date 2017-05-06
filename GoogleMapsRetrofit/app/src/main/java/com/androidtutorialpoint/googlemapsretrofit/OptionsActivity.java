package com.androidtutorialpoint.googlemapsretrofit;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import java.util.ArrayList;
import java.util.List;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.google.android.gms.maps.GoogleMap;

public class OptionsActivity extends AppCompatActivity implements OnItemSelectedListener
{
    Spinner spinner;
    CheckBox gasPriceCB,restCB,distCB;
    private RadioButton rbtnDist, rbtnGP, rbtnRest;
    private RadioGroup radioGroup;


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

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.radioBtnGP) {
                    Toast.makeText(getApplicationContext(), "choice: Gas Price", Toast.LENGTH_SHORT).show();
                    MapsActivity.search = new Search();
                    MapsActivity.search.setFiltertoPrice();
                    MapsActivity.search.setLocation(new Location(MapsActivity.latitude,MapsActivity.longitude));
                } else if(checkedId == R.id.radioBtnRest) {
                    Toast.makeText(getApplicationContext(), "choice: Restaurant", Toast.LENGTH_SHORT).show();
                    MapsActivity.search = new Search();
                    MapsActivity.search.setFiltertoRestaurants();
                    MapsActivity.search.setLocation(new Location(MapsActivity.latitude,MapsActivity.longitude));
                } else {
                    Toast.makeText(getApplicationContext(), "choice: Distance", Toast.LENGTH_SHORT).show();
                    MapsActivity.search = new Search();
                    MapsActivity.search.setLocation(new Location(MapsActivity.latitude,MapsActivity.longitude));

                }
            }
        });

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
