// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.io.orders;

import store.orders.Order;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Writes order history entries to a CSV file.
 */
public class OrderHistoryWriter{
    /**
     * Appends an order to file.
     * @param order order
     * @param path file path
     * @throws IOException if file write fails
     */
    public static void write(Order order,String path) throws IOException{
        FileWriter writer=new FileWriter(path,true);
        writer.write(order.getOrderID()+","+order.getTotalAmount()+","+order.getCreatedAt()+","+order.getItems().size()+"\n");
        writer.close();
    }
}
