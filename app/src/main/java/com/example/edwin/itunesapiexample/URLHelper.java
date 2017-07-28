package com.example.edwin.itunesapiexample;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by Edwin on 6/22/2017.
 */
public class URLHelper {
    final static String ITUNESAPI_BASE_URL =
            "https://itunes.apple.com/search?";

    final static String PARAM = "term";
    final static String TERM = "Michael+jackson";

    public static URL buildUrl() {
        Uri builtUri = Uri.parse(ITUNESAPI_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM,TERM)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }


    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static ArrayList<Song> parseJSON(String json) throws JSONException {

        ArrayList<Song> results = new ArrayList<>();
        JSONObject resultsJson = new JSONObject(json);
        JSONArray arrayOfSongs = resultsJson.getJSONArray("results");

        for(int i = 0; i < arrayOfSongs.length(); i++){

            JSONObject item = arrayOfSongs.getJSONObject(i);
            String trackName = item.getString("trackName");
            String artistName = item.getString("artistName");
            String trackPrice = item.getString("trackPrice");

            Long time = Long.valueOf(item.getString("trackTimeMillis"));
            int seconds = (int) (time / 1000) % 60 ;
            int minutes = (int) ((time / (1000*60)) % 60);

            String trackTime = minutes + ":"+ seconds;

            Song s = new Song(artistName, trackName , trackPrice, trackTime);
            s.setGenre(item.getString("primaryGenreName"));
            s.setArtworkUrl60(item.getString("artworkUrl60"));
            s.setArtworkUrl100(item.getString("artworkUrl100"));
            s.setPreviewUrl(item.getString("previewUrl"));

            if(item.has("collectionName")) {
                s.setCollectionName(item.getString("collectionName"));
            }

            s.setReleaseDate(item.getString("releaseDate"));

            if(item.has("collectionPrice")) {
                s.setCollectionPrice(item.getString("collectionPrice"));
            }

            results.add(s);

        }

        return results;

    }

}
