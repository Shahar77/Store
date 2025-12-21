// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.orders;

import store.cart.CartItem;
import store.core.Persistable;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single order in the store system.
 * Contains order items, total amount and order status.
 */
public class Order implements Persistable {

    private int orderID;
    private List<CartItem> items;
    private double totalAmount;
    private OrderStatus status;

    /**
     * Creates a new Order.
     *
     * @param orderID unique order identifier
     * @param items list of items in the order
     * @param totalAmount total price of the order
     */
    public Order(int orderID, List<CartItem> items, double totalAmount) {
        this.orderID = orderID;
        this.items = new ArrayList<>();
        for (CartItem ci : items) {
            this.items.add(ci);
        }
        this.totalAmount = totalAmount;
        this.status = OrderStatus.NEW;
    }

    /**
     * Returns the order ID.
     *
     * @return order ID
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * Returns the total amount of the order.
     *
     * @return total amount
     */
    public double getTotalAmount() {
        return totalAmount;
    }

    /**
     * Returns the current status of the order.
     *
     * @return order status
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * Returns a copy of the order items.
     *
     * @return list of cart items
     */
    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    /**
     * Marks the order as paid.
     *
     * @return true if status was changed successfully
     */
    public boolean pay() {
        if (status == OrderStatus.NEW) {
            status = OrderStatus.PAID;
            return true;
        }
        return false;
    }

    /**
     * Marks the order as shipped.
     *
     * @return true if status was changed successfully
     */
    public boolean ship() {
        if (status == OrderStatus.PAID) {
            status = OrderStatus.SHIPPED;
            return true;
        }
        return false;
    }

    /**
     * Marks the order as delivered.
     *
     * @return true if status was changed successfully
     */
    public boolean deliver() {
        if (status == OrderStatus.SHIPPED) {
            status = OrderStatus.DELIVERED;
            return true;
        }
        return false;
    }

    /**
     * Saves the order to a file.
     * Not implemented in this version.
     *
     * @param path file path
     */
    @Override
    public void saveToFile(String path) {
        // optional implementation
    }

    /**
     * Returns a textual representation of the order.
     *
     * @return order description
     */
    @Override
    public String toString() {
        return "Order{orderID=" + orderID +
                ", status=" + status +
                ", totalAmount=" + totalAmount + "}";
    }
}
