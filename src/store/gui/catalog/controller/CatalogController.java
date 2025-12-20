// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.gui.cart.controller;

import store.cart.Cart;
import store.products.Product;

/**
 * Controller for cart operations.
 */
public class CartController{
    private final Cart cart;

    /**
     * Creates controller.
     * @param cart cart
     */
    public CartController(Cart cart){
        this.cart=cart;
    }

    /**
     * Increases quantity of product by 1.
     * @param p product
     */
    public void increase(Product p){
        cart.addItem(p,1);
    }

    /**
     * Decreases quantity of product by 1.
     * @param p product
     */
    public void decrease(Product p){
        cart.decreaseItem(p);
    }

    /**
     * Removes product from cart.
     * @param p product
     */
    public void remove(Product p){
        cart.removeItem(p);
    }
}
