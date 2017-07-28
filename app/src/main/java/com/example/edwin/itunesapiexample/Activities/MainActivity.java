package com.example.edwin.itunesapiexample.Activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.*;


import com.example.edwin.itunesapiexample.*;
import com.example.edwin.itunesapiexample.Adapters.ListAdapter;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog mProgressDialog;
    private ListView mListView;
    private ArrayList<Song> songList = new ArrayList<>();
    private com.example.edwin.itunesapiexample.Adapters.ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mListView = (ListView) findViewById(R.id.listView1);

        adapter = new ListAdapter(MainActivity.this, songList);
        mListView.setAdapter(adapter);


        new GetResponseTask().execute(URLHelper.buildUrl());

    }

    private class GetResponseTask extends AsyncTask<URL, Void, ArrayList<Song>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setMessage("Loading data...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected ArrayList<Song> doInBackground(URL... params) {
            String jsonResults;
            ArrayList<Song> results = null;
            try {
                jsonResults = URLHelper.getResponseFromHttpUrl(params[0]);
                results = URLHelper.parseJSON(jsonResults);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                 e.printStackTrace();
             }
            return results;
        }

        @Override
        protected void onPostExecute(final ArrayList<Song> data)  {

            if(data != null){
                for(Song s: data){
                    songList.add(s);
                }
                adapter.notifyDataSetChanged();

            }
            else{
                Snackbar.make(findViewById(R.id.coordinatorLayout), "An error occurred please try again", Snackbar.LENGTH_LONG)
                        .show();
            }
            mProgressDialog.dismiss();
        }
    }


}
