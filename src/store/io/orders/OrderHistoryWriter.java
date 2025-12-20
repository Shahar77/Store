// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.io.orders;

import store.orders.Order;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Utility class responsible for saving order history to a file.
 */
public class OrderHistoryWriter {

    /**
     * Writes a single order to a file.
     * Data is appended to the file if it already exists.
     *
     * @param order order to write
     * @param path destination file path
     * @throws IOException if writing to file fails
     */
    public static void write(Order order, String path) throws IOException {
        if (order == null || path == null) return;

        FileWriter writer = new FileWriter(path, true);
        writer.write(
                order.getOrderID() + "," +
                        order.getTotalAmount() + "," +
                        order.getStatus() + "\n"
        );
        writer.close();
    }
}
