package store.gui.cart.controller;

import store.engine.StoreEngine;
import store.cart.CartItem;
import store.products.Product;

import java.util.List;

/**
 * Controller for cart operations.
 * Acts as a bridge between cart GUI and StoreEngine.
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
     * @return all items currently in the cart
     */
    public List<CartItem> getItems(){
        return engine.getCart().getItems();
    }

    /**
     * Adds one unit of product to cart.
     * @param product product to add
     */
    public void add(Product product){
        engine.addToCart(product);
    }

    /**
     * Decreases quantity of a product in cart.
     * @param item cart item
     */
    public void decrease(CartItem item){
        engine.getCart().decreaseItem(item.getProduct());
    }

    /**
     * @return total cart price
     */
    public double getTotal(){
        return engine.getCart().calculateTotal();
    }
}
