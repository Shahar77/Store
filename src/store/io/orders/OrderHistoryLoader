package store.io.orders;

import store.orders.Order;
import store.orders.OrderStatus;
import store.cart.CartItem;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Loads order history from a CSV file.
 */
public class OrderHistoryLoader{

    /**
     * Loads orders from file.
     * Note: items list is empty (summary load only).
     *
     * @param path file path
     * @return list of orders
     * @throws IOException if reading fails
     */
    public static List<Order> load(String path)throws IOException{
        List<Order> orders=new ArrayList<>();
        BufferedReader reader=new BufferedReader(new FileReader(path));
        String line;
        while((line=reader.readLine())!=null){
            String[] parts=line.split(",");
            int id=Integer.parseInt(parts[0]);
            double total=Double.parseDouble(parts[1]);
            Order o=new Order(id,new ArrayList<CartItem>(),total);
            orders.add(o);
        }
        reader.close();
        return orders;
    }
}
