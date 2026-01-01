/**
 * Submitted by:
 * Sarah Gabay - ID 329185771
 * Shahar Ezra - ID 329186118
 */
package store.orders;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages all orders in the system.
 * Provides thread-safe access to orders and order IDs.
 */
public class OrderManager {

    /**
     * List of all orders.
     */
    private static final List<Order> orders=new ArrayList<>();

    /**
     * Next order ID.
     */
    private static int id=1;

    /**
     * Adds a new order in a thread-safe manner.
     *
     * @param order order to add
     */
    public static synchronized void add(Order order) {
        orders.add(order);
    }

    /**
     * Returns a copy of all orders.
     *
     * @return list of orders
     */
    public static synchronized List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    /**
     * Generates the next unique order ID.
     *
     * @return next order ID
     */
    public static synchronized int nextId() {
        return id++;
    }
}
