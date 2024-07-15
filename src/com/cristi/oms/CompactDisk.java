package com.cristi.oms;

import java.util.List;

public class CompactDisk extends Media implements Playable {
    private String artist;
    private Library<Track> trackLibrary;

    public CompactDisk(String title, String category, double cost, String artist) {
        super(title, category, cost);
        this.artist = artist;
        this.trackLibrary = new Library<>();
    }

    public String getArtist() {
        return artist;
    }

    public List<Track> getTracks() {
        return trackLibrary.getItems();
    }

    public void setArtist(String artist) {
        if (artist == null || artist.trim().isEmpty()) {
            throw new IllegalArgumentException("Artist cannot be null or empty.");
        }
        this.artist = artist;
    }

    public void addTrack(Track track) {
        if (track == null) {
            throw new IllegalArgumentException("Track cannot be null.");
        }
        trackLibrary.addItem(track);
    }

    public void removeTrack(Track track) {
        trackLibrary.removeItem(track);
    }

    @Override
    public String toString() {
        return "CompactDisk{" +
                "artist='" + artist + '\'' +
                ", tracks=" + trackLibrary.getItems() +
                "} " + super.toString();
    }

    @Override
    public void play() throws PlayerException {
        System.out.println("Playing CD: " + getTitle());
        System.out.println("Artist: " + artist);
        for (Track track : trackLibrary.getItems()) {
            try {
                track.play();
            } catch (PlayerException e) {
                System.err.println("Error playing track: " + e.getMessage());
            }
        }
    }
}
