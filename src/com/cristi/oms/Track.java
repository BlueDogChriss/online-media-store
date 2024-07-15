package com.cristi.oms;

import java.io.Serializable;
import java.util.Objects;
import java.util.logging.Logger;

public class Track implements Playable, Comparable<Track>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(Track.class.getName());

    private String title;
    private int length;

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Track{" +
                "title='" + title + '\'' +
                ", length=" + length +
                '}';
    }

    @Override
    public void play() throws PlayerException {
        if (length == 0) {
            throw new PlayerException("Cannot play the track: " + title + ". Length is 0.");
        } else {
            logger.info("Playing Track: " + title);
            logger.info("Length: " + length + " seconds");
        }
    }

    @Override
    public int compareTo(Track other) {
        if (other == null) {
            throw new NullPointerException("Cannot compare to null.");
        }
        return Integer.compare(this.length, other.length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return length == track.length && Objects.equals(title, track.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, length);
    }
}
