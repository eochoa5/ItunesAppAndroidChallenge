package com.example.edwin.itunesapiexample.Activities;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.edwin.itunesapiexample.R;
import com.example.edwin.itunesapiexample.Song;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView infoText = (TextView) findViewById(R.id.info);
        ImageView img = (ImageView) findViewById(R.id.img);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {

            Type type = new TypeToken<Song>(){}.getType();
            Song s = new Gson().fromJson(extras.getString("song"), type);

            Glide.with(this).load(s.getArtworkUrl100()).into(img);

            String txt = "SONG NAME: " + s.getTrackName() + "\n" + "ARTIST: " +
                    s.getArtistName() + "\nTRACK PRICE: $" + s.getTrackPrice() + "\n" +
                    "RELEASE DATE: " + s.getReleaseDate() + "\nTRACK DURATION: " + s.getTrackTime() +
                    "\nGENRE: " + s.getGenre() + "\nCOLLECTION NAME: " + s.getCollectionName() +
                    "\nCOLLECTION PRICE: $" + s.getCollectionPrice() + "\nPREVIEW URL: " +
                    s.getPreviewUrl();

            infoText.setText(txt);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
