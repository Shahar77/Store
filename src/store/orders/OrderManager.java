package store.orders;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {

    private static final List<Order> orders = new ArrayList<>();
    private static int id = 1;

    public static synchronized void add(Order order) {
        orders.add(order);
    }

    public static synchronized List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public static synchronized int nextId() {
        return id++;
    }
}
