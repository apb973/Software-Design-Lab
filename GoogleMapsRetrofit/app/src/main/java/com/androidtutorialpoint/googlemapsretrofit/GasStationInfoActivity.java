package com.androidtutorialpoint.googlemapsretrofit;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class GasStationInfoActivity extends AppCompatActivity {

    TextView gsName,gsAddress,gsPrice;
    ListView restList;
    String[] restArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas_station_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gsName = (TextView) findViewById(R.id.gsName);
        gsName.setText(MapsActivity.clickedStation.getName());

        gsAddress = (TextView) findViewById(R.id.gsAddress);
        gsAddress.setText("   " + MapsActivity.clickedStation.getAddress());

        gsPrice = (TextView) findViewById(R.id.priceTV);
        gsPrice.setText("Price: $ " + MapsActivity.clickedStation.getPrice());

        restList = (ListView) findViewById(R.id.ResturantList);

        restArr = new String[MapsActivity.clickedStation.getRestaurants().size()];
        for(int i = 0; i < MapsActivity.clickedStation.getRestaurants().size(); i++)
        {
            restArr[i] = MapsActivity.clickedStation.getRestaurants().getRestaurant(i);
        }
        ArrayAdapter adapter = new ArrayAdapter(GasStationInfoActivity.this, android.R.layout.simple_list_item_1 ,restArr );
        restList.setAdapter(adapter);

    }

}
