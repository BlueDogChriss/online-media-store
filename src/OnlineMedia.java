import com.cristi.oms.*;

import java.util.Properties;
import java.util.logging.Logger;

public class OnlineMedia {
    private static final Logger logger = Logger.getLogger(OnlineMedia.class.getName());

    public static void main(String[] args) {
        MemoryDaemon memoryDaemon = new MemoryDaemon();
        Thread memoryThread = new Thread(memoryDaemon);
        memoryThread.setDaemon(true);
        memoryThread.start();

        Properties properties = DataFromProperties.readProperties();

        Order order = new Order();

        CompactDisk cd1 = DataFromProperties.createCompactDisk(properties, "cd1");

        createAndPlayMedia(properties, order, cd1);

        if (cd1 != null) addAndRemoveTrack(cd1);

        logger.info("Order:");
        System.out.println(order);
        logger.info("TOTAL: $" + order.calculateTotal());

        OrderSaver.saveOrder(order, "order.ser");

        Order restoredOrder = OrderSaver.restoreOrder("order.ser");
        if (restoredOrder != null) {
            logger.info("Restored Order:");
            System.out.println(restoredOrder);
        }

        memoryDaemon.stop();
    }

    private static void createAndPlayMedia(Properties properties, Order order, CompactDisk cd1) {
        try {
            DigitalVideoDisk dvd1 = DataFromProperties.createDigitalVideoDisk(properties, "dvd1");
            if (dvd1 != null) {
                dvd1.play();
                order.addItem(dvd1);
            }

            DigitalVideoDisk dvd2 = DataFromProperties.createDigitalVideoDisk(properties, "dvd2");
            if (dvd2 != null) {
                dvd2.play();
                order.addItem(dvd2);
            }

            Book book1 = DataFromProperties.createBook(properties, "book1");
            if (book1 != null) {
                order.addItem(book1);
            }

            if (cd1 != null) {
                cd1.play();
                order.addItem(cd1);
            }

        } catch (PlayerException e) {
            logger.severe("Error playing media: " + e.getMessage());
        }
    }

    private static void addAndRemoveTrack(CompactDisk cd1) {
        Track newTrack = new Track("New Song", 300);
        logger.info("Add track:");
        cd1.addTrack(newTrack);
        cd1.getTracks().forEach(track -> logger.info(track.toString()));

        logger.info("Remove track:");
        if (!cd1.getTracks().isEmpty()) {
            cd1.removeTrack(cd1.getTracks().get(0));
        }
        cd1.getTracks().forEach(track -> logger.info(track.toString()));
    }
}
