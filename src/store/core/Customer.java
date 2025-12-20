// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.core;

import store.cart.Cart;
import store.products.Product;
import store.orders.Order;
import store.engine.StoreEngine;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer in the store system.
 * Each customer owns a cart and order history.
 * Uses StoreEngine to create orders.
 */
public class Customer extends User{

    private Cart cart;
    private List<Order> orderHistory;
    private StoreEngine engine;

    /**
     * Creates a new customer.
     * @param username customer username
     * @param email customer email
     * @param engine reference to store engine
     */
    public Customer(String username,String email,StoreEngine engine){
        super(username,email);
        this.cart=new Cart();
        this.orderHistory=new ArrayList<>();
        this.engine=engine;
    }

    /**
     * @return customer's shopping cart
     */
    public Cart getCart(){
        return cart;
    }

    /**
     * @return list of customer's past orders
     */
    public List<Order> getOrderHistory(){
        return orderHistory;
    }

    /**
     * Adds a product to the cart.
     * @param p product to add
     * @param quantity quantity to add
     * @return true if added successfully
     */
    public boolean addToCart(Product p,int quantity){
        if(p==null||quantity<=0){
            return false;
        }
        if(p.getStock()<quantity){
            return false;
        }
        return cart.addItem(p,quantity);
    }

    /**
     * Removes a product from the cart.
     * @param p product to remove
     * @return true if removed
     */
    public boolean removeFromCart(Product p){
        if(p==null){
            return false;
        }
        return cart.removeItem(p);
    }

    /**
     * Creates an order from the cart using StoreEngine.
     * Clears the cart after successful checkout.
     * @return true if checkout succeeded
     */
    public boolean checkout(){
        Order order=engine.checkout();
        if(order==null){
            return false;
        }
        orderHistory.add(order);
        return true;
    }
}

