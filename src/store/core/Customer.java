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
 * Each customer owns a cart and an order history.
 */
public class Customer extends User{
    private Cart cart;
    private List<Order> orderHistory;
    private StoreEngine engine;

    /**
     * Creates a new customer.
     * @param username unique username
     * @param email customer email
     * @param engine reference to store engine
     */
    public Customer(String username,String email,StoreEngine engine){
        super(username,email);
        this.engine=engine;
        cart=new Cart();
        orderHistory=new ArrayList<>();
    }

    /**
     * Returns the customer's cart.
     * @return cart
     */
    public Cart getCart(){
        return cart;
    }

    /**
     * Returns all previous orders of the customer.
     * @return list of orders
     */
    public List<Order> getOrderHistory(){
        return orderHistory;
    }

    /**
     * Adds a product to the cart if stock allows.
     * @param product product to add
     * @param quantity quantity
     * @return true if added successfully
     */
    public boolean addToCart(Product product,int quantity){
        if(product==null)return false;
        if(quantity<=0)return false;
        if(product.getStock()<quantity)return false;
        return cart.addItem(product,quantity);
    }

    /**
     * Removes a product from the cart.
     * @param product product to remove
     * @return true if removed
     */
    public boolean removeFromCart(Product product){
        return cart.removeItem(product);
    }

    /**
     * Performs checkout: creates order and clears cart.
     * @return true if checkout succeeded
     */
    public boolean checkout(){
        Order order=engine.createOrderFromCart(cart);
        if(order==null)return false;
        orderHistory.add(order);
        cart.clear();
        return true;
    }
}
