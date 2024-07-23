import com.cristi.oms.*;
import com.cristi.oms.dao.BookDB;
import com.cristi.oms.dao.CompactDiskDB;
import com.cristi.oms.dao.DigitalVideoDB;
import com.cristi.oms.dao.TrackDB;

import java.sql.SQLException;
import java.util.List;

public class OnlineMediaDAO {

    public static void main(String[] args) {

        BookDB bookDB = new BookDB();
        CompactDiskDB compactDiskDB = new CompactDiskDB();
        DigitalVideoDB digitalVideoDB = new DigitalVideoDB();
        TrackDB trackDB = new TrackDB();

        Order order = new Order(10);
        Library<Media> mediaLibrary = new Library<>();

        try {

            DigitalVideoDisk dvd1 = new DigitalVideoDisk(3, "DVD 3 CEVA", "CEVA", 11.99, "CEVA", 120);
//			digitalVideoDB.insert(dvd1);

            DigitalVideoDisk dvd2 = new DigitalVideoDisk(4, "DVD 4 CEVA", "CEVA", 11.99, "CEVA", 150);
//			digitalVideoDB.insert(dvd2);

            Book book1 = new Book(2, "Book 2 CEVA", "CEVA", 11.99, List.of("CEVA", "CEVA2", "CEVA3"));
//			bookDB.insert(book1);

            CompactDisk cd1 = new CompactDisk(3, "CD 3 CEVA", "CEVA", 11.99, "CEVA");
//			compactDiskDB.insert(cd1);

            Track newTrack = new Track(2, "Ceva Song", 690);
//			trackDB.insert(newTrack);

            List<DigitalVideoDisk> dvds = digitalVideoDB.getAll();
            List<Book> books = bookDB.getAll();
            List<CompactDisk> cds = compactDiskDB.getAll();
            List<Track> tracks = trackDB.findAll();

            System.out.println("\nDigital Video Discs:");
            dvds.forEach(System.out::println);

            System.out.println("\nBooks:");
            books.forEach(System.out::println);

            System.out.println("\nCompact Discs:");
            cds.forEach(System.out::println);

            System.out.println("\nTracks:");
            tracks.forEach(System.out::println);

            dvd1.setTitle("Updated DVD");
            digitalVideoDB.update(dvd1);

            compactDiskDB.delete(1);

            dvds = digitalVideoDB.getAll();
            cds = compactDiskDB.getAll();

            System.out.println("\nUpdated Digital Video Discs:");
            dvds.forEach(System.out::println);

            System.out.println("\nRemaining Compact Discs:");
            cds.forEach(System.out::println);

            mediaLibrary.addItem(dvd1);
            mediaLibrary.addItem(cd1);
            mediaLibrary.addItem(book1);
            System.out.println("\nPlaying media:");
            try {
                dvd1.play();
                cd1.play();
            } catch (PlayerException e) {
                System.out.println("Error playing media: " + e.getMessage());
            }

            order.addItem(dvd1);
            order.addItem(cd1);
            order.addItem(book1);
            System.out.println("\nOrder:");
            System.out.println(order);
            System.out.println("TOTAL: $" + order.calculateTotal() + "\n");

            OrderSaver.saveOrder(order, "order.ser");
            Order restoredOrder = OrderSaver.restoreOrder("order.ser");
            if (restoredOrder != null) {
                System.out.println("Restored Order:");
                System.out.println(restoredOrder);
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
