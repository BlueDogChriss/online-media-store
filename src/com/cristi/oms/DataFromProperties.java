package com.cristi.oms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class DataFromProperties {
    private static final String FILE_NAME = "media.properties";
    private static final Logger logger = Logger.getLogger(DataFromProperties.class.getName());

    public static Properties readProperties() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(FILE_NAME)) {
            properties.load(fis);
        } catch (IOException e) {
            logger.severe("Error reading properties file: " + e.getMessage());
        }
        return properties;
    }

    public static void writeProperties(String key, String value) {
        try (FileOutputStream fos = new FileOutputStream(FILE_NAME)) {
            Properties properties = new Properties();
            properties.setProperty(key, value);
            properties.store(fos, null);
        } catch (IOException e) {
            logger.severe("Error writing properties: " + e.getMessage());
        }
    }

    public static DigitalVideoDisk createDigitalVideoDisk(Properties properties, String prefix) {
        try {
            String title = properties.getProperty(prefix + ".title");
            String category = properties.getProperty(prefix + ".category");
            String priceStr = properties.getProperty(prefix + ".price");
            String director = properties.getProperty(prefix + ".director");
            String lengthStr = properties.getProperty(prefix + ".length");

            if (title == null || category == null || priceStr == null || director == null || lengthStr == null) {
                throw new IllegalArgumentException("Missing properties for " + prefix);
            }

            double price = parseDouble(priceStr);
            int length = Integer.parseInt(lengthStr);

            return new DigitalVideoDisk(title, category, price, director, length);
        } catch (Exception e) {
            logger.severe("Error creating DigitalVideoDisk: " + e.getMessage());
            return null;
        }
    }

    public static Book createBook(Properties properties, String prefix) {
        try {
            String title = properties.getProperty(prefix + ".title");
            String category = properties.getProperty(prefix + ".category");
            String priceStr = properties.getProperty(prefix + ".price");
            String authorsStr = properties.getProperty(prefix + ".authors");

            if (title == null || category == null || priceStr == null || authorsStr == null) {
                throw new IllegalArgumentException("Missing properties for " + prefix);
            }

            double price = parseDouble(priceStr);
            ArrayList<String> authors = new ArrayList<>(Arrays.asList(authorsStr.split(",")));
            return new Book(title, category, price, authors);
        } catch (Exception e) {
            logger.severe("Error creating Book: " + e.getMessage());
            return null;
        }
    }

    public static CompactDisk createCompactDisk(Properties properties, String prefix) {
        try {
            String title = properties.getProperty(prefix + ".title");
            String category = properties.getProperty(prefix + ".category");
            String costStr = properties.getProperty(prefix + ".cost");
            String artist = properties.getProperty(prefix + ".artist");

            if (title == null || category == null || costStr == null || artist == null) {
                throw new IllegalArgumentException("Missing properties for " + prefix);
            }

            double cost = parseDouble(costStr);
            CompactDisk cd = new CompactDisk(title, category, cost, artist);

            String trackCountStr = properties.getProperty(prefix + ".track.count");
            int trackCount = Integer.parseInt(trackCountStr);
            for (int i = 1; i <= trackCount; i++) {
                Track track = createTrack(properties, prefix + ".track" + i);
                cd.getTracks().add(track);
            }
            List<Track> tracks = cd.getTracks();
            Collections.sort(tracks);
            return cd;
        } catch (Exception e) {
            logger.severe("Error creating CompactDisk: " + e.getMessage());
            return null;
        }
    }

    public static Track createTrack(Properties properties, String prefix) {
        try {
            String trackTitle = properties.getProperty(prefix + ".title");
            String trackLengthStr = properties.getProperty(prefix + ".length");

            if (trackTitle == null || trackLengthStr == null) {
                throw new IllegalArgumentException("Missing title or length for " + prefix);
            }

            int trackLength = Integer.parseInt(trackLengthStr);
            return new Track(trackTitle, trackLength);
        } catch (Exception e) {
            logger.severe("Error creating Track: " + e.getMessage());
            return null;
        }
    }

    private static double parseDouble(String value) {
        try {
            return Double.parseDouble(value.trim());
        } catch (NullPointerException | NumberFormatException e) {
            logger.severe("Error parsing double: " + e.getMessage());
            return 0.0;
        }
    }
}
