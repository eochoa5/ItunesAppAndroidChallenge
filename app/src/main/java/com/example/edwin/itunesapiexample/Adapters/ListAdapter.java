package com.example.edwin.itunesapiexample.Adapters;

/**
 * Created by Edwin on 6/22/2017.
 */
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.edwin.itunesapiexample.Activities.DetailsActivity;
import com.example.edwin.itunesapiexample.Activities.MainActivity;
import com.example.edwin.itunesapiexample.R;
import com.example.edwin.itunesapiexample.Song;
import com.google.gson.Gson;


import java.util.ArrayList;

public class ListAdapter extends BaseAdapter{
    ArrayList<Song> songList;
    Context context;

    private static LayoutInflater inflater=null;
    public ListAdapter(MainActivity mainActivity, ArrayList<Song> songList) {
        // TODO Auto-generated constructor stub
        this.songList = songList;
        context = mainActivity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return songList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView songTitle;
        TextView artist;
        TextView durAndPrice;
        ImageView artwork;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.listview_songs, null);

        holder.songTitle=(TextView) rowView.findViewById(R.id.songTitle);
        holder.artist=(TextView) rowView.findViewById(R.id.artist);
        holder.durAndPrice=(TextView) rowView.findViewById(R.id.durAndPrice);
        holder.artwork = (ImageView) rowView.findViewById(R.id.artwork);

        holder.songTitle.setText(songList.get(position).getTrackName());
        holder.artist.setText(songList.get(position).getArtistName());

        String durationAndPrice ="Duration: " + songList.get(position).getTrackTime() + "    " +
                "Price: $" + songList.get(position).getTrackPrice();

        Glide.with(context).load(songList.get(position).getArtworkUrl60()).into(holder.artwork);

        holder.durAndPrice.setText(durationAndPrice);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailsActivity.class);
                i.putExtra("song", (new Gson()).toJson(songList.get(position)));

                context.startActivity(i);
            }
        });

        return rowView;
    }

}