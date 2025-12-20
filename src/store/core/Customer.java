// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.core;

import store.cart.Cart;
import store.engine.StoreEngine;
import store.orders.Order;
import store.products.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer in the store system.
 * A customer owns a shopping cart and an order history.
 */
public class Customer extends User {

    private Cart cart;
    private List<Order> orderHistory;
    private StoreEngine engine;

    /**
     * Creates a new customer with an empty cart and order history.
     *
     * @param username customer's username
     * @param email customer's email
     * @param engine reference to the store engine
     */
    public Customer(String username, String email, StoreEngine engine) {
        super(username, email);
        this.cart = new Cart();
        this.orderHistory = new ArrayList<>();
        this.engine = engine;
    }

    /**
     * Returns the customer's shopping cart.
     *
     * @return cart
     */
    public Cart getCart() {
        return cart;
    }

    /**
     * Returns the customer's order history.
     *
     * @return list of previous orders
     */
    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    /**
     * Adds a product to the cart with the given quantity.
     *
     * @param p product to add
     * @param quantity quantity to add
     * @return true if added successfully
     */
    public boolean addToCart(Product p, int quantity) {
        if (p == null || quantity <= 0) return false;
        if (p.getStock() < quantity) return false;
        return cart.addItem(p, quantity);
    }

    /**
     * Removes a product from the cart.
     *
     * @param p product to remove
     * @return true if removed
     */
    public boolean removeFromCart(Product p) {
        if (p == null) return false;
        return cart.removeItem(p);
    }

    /**
     * Performs checkout, creates an order from the cart
     * and stores it in the customer's order history.
     *
     * @return true if checkout succeeded
     */
    public boolean checkout() {
        Order order = engine.createOrderFromCart(cart);
        if (order == null) return false;
        orderHistory.add(order);
        return true;
    }
}
