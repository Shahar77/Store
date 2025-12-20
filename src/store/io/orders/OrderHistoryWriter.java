// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.io.orders;

import store.orders.Order;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Responsible for writing order history to a file.
 */
public class OrderHistoryWriter{

    /**
     * Appends a single order to a CSV file.
     * Format:
     * orderId,totalAmount,date,itemsCount
     *
     * @param order order to save
     * @param path file path
     * @throws IOException if writing fails
     */
    public static void write(Order order,String path)throws IOException{
        FileWriter writer=new FileWriter(path,true);
        writer.write(
            order.getOrderID()+","+
            order.getTotalAmount()+","+
            order.getCreatedAt()+","+
            order.getItems().size()+"\n"
        );
        writer.close();
    }
}
