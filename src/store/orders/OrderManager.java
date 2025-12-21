/**
 * Submitted by:
 * Sarah Gabay - ID 329185771
 * Shahar Ezra - ID 329186118
 */

package store.orders;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple in-memory order registry.
 * This is optional; used by UI components that want a global list.
 */
public class OrderManager{
    private static List<Order> orders=new ArrayList<>();

    /**
     * Adds order to global list.
     * @param order order
     */
    public static void add(Order order){
        if(order!=null)orders.add(order);
    }

    /**
     * @return all orders
     */
    public static List<Order> getOrders(){
        return new ArrayList<>(orders);
    }


    /**
     * Clears all stored orders (debug/testing).
     */
    public static void clear(){
        orders.clear();
    }
}

