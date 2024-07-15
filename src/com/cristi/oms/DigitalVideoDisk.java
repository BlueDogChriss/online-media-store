package com.cristi.oms;

public class DigitalVideoDisk extends Media implements Playable {
    private String director;
    private int length;

    public DigitalVideoDisk(String title, String category, double cost, String director, int length) {
        super(title, category, cost);
        setDirector(director);
        setLength(length);
    }

    public String getDirector() {
        return director;
    }

    public int getLength() {
        return length;
    }

    public void setDirector(String director) {
        if (director == null || director.trim().isEmpty()) {
            throw new IllegalArgumentException("Director cannot be null or empty.");
        }
        this.director = director;
    }

    public void setLength(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("Length cannot be negative.");
        }
        this.length = length;
    }

    @Override
    public String toString() {
        return "DigitalVideoDisk{" +
                "title='" + getTitle() + '\'' +
                ", category='" + getCategory() + '\'' +
                ", cost=" + getCost() +
                ", director='" + director + '\'' +
                ", length=" + length +
                '}';
    }

    @Override
    public void play() throws PlayerException {
        if (length == 0) {
            throw new PlayerException("Cannot play the DVD: " + getTitle() + ". Length is 0.");
        } else {
            System.out.println("Playing DVD: " + getTitle());
            System.out.println("Director: " + getDirector());
            System.out.println("Length: " + length + " minutes");
        }
    }
}
