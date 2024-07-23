//import com.cristi.oms.*;
//
//import java.util.Properties;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//
//public class OnlineMedia {
//
//    public static void main(String[] args) {
////
////        MemoryDaemon memoryDaemon = new MemoryDaemon();
////        Thread memoryThread = new Thread(memoryDaemon);
////        memoryThread.setDaemon(true);
////        memoryThread.start();
////
////        Properties properties = DataFromProperties.readProperties();
////
////        Order order = new Order(10);
////
////        CompactDisk cd1 = DataFromProperties.createCompactDisk(properties, "cd1");
////
////        try {
////            DigitalVideoDisk dvd1 = DataFromProperties.createDigitalVideoDisk(properties, "dvd1");
////            dvd1.play();
////            order.addItem(dvd1);
////
////            DigitalVideoDisk dvd2 = DataFromProperties.createDigitalVideoDisk(properties, "dvd2");
////            dvd2.play();
////            order.addItem(dvd2);
////
////            Book book1 = DataFromProperties.createBook(properties, "book1");
////            order.addItem(book1);
////
////            cd1.play();
////            order.addItem(cd1);
////
////        } catch (PlayerException e) {
////            System.out.println("Error playing media: " + e.getMessage());
////        }
////
////
////        Track newTrack = new Track("New Song", 300);
////        System.out.println("\nAdd track:");
////        cd1.getTracks().add(newTrack);
////        for (Track track : cd1.getTracks()) {
////            System.out.println(track);
////        }
////        System.out.println("\nRemove track:");
////        if (!cd1.getTracks().isEmpty()) {
////            cd1.getTracks().remove(0);
////        }
////        for (Track track : cd1.getTracks()) {
////            System.out.println(track);
////        }
////
////        System.out.println();
////        System.out.println("Order:");
////        System.out.println(order);
////        System.out.println("TOTAL: $" + order.calculateTotal() + "\n");
////
////        //Serialization
////        OrderSaver.saveOrder(order, "order.ser");
////        //Deserialization
////        Order restoredOrder = OrderSaver.restoreOrder("order.ser");
////        if (restoredOrder != null) {
////            System.out.println("Restored Order:");
////            System.out.println(restoredOrder);
////        }
////
////        memoryDaemon.stop();
////    }
//}