package store.gui.cart.controller;

import store.engine.StoreEngine;
import store.cart.Cart;
import store.products.Product;

/**
 * Controller for cart-related actions.
 * Acts as a mediator between the cart GUI and the store engine.
 */
public class CartController{

    private final StoreEngine engine;

    /**
     * Creates a cart controller.
     * @param engine store engine
     */
    public CartController(StoreEngine engine){
        this.engine=engine;
    }

    /**
     * Adds one unit of a product to the cart.
     * @param p product to add
     */
    public void add(Product p){
        engine.addToCart(p);
    }

    /**
     * Removes a product completely from the cart.
     * @param p product to remove
     */
    public void remove(Product p){
        engine.getCart().removeItem(p);
    }

    /**
     * Returns the current cart.
     * @return cart instance
     */
    public Cart getCart(){
        return engine.getCart();
    }
}
