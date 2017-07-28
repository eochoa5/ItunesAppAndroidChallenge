package com.example.edwin.itunesapiexample;

/**
 * Created by Edwin on 6/22/2017.
 */
public class Song {
    private String artistName, trackName, artworkUrl60,
    trackPrice, releaseDate, trackTime, genre, artworkUrl100, previewUrl;

    private String collectionName = "N/A";
    private String collectionPrice = "N/A";

    public Song(String artistName, String trackName, String trackPrice, String trackTime){
        this.artistName = artistName;
        this.trackName = trackName;
        this.trackPrice = trackPrice;
        this.trackTime = trackTime;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void setArtworkUrl60(String artworkUrl60) {
        this.artworkUrl60 = artworkUrl60;
    }

    public void setCollectionPrice(String collectionPrice) {
        this.collectionPrice = collectionPrice;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getArtworkUrl60() {
        return artworkUrl60;
    }

    public String getCollectionPrice() {
        return collectionPrice;
    }

    public String getTrackPrice() {
        return trackPrice;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getTrackTime() {
        return trackTime;
    }

    public String getGenre() {
        return genre;
    }


}
