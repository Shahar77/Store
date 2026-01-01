/**
 * Submitted by:
 * Sarah Gabay - ID 329185771
 * Shahar Ezra - ID 329186118
 */
package store.gui.cart.controller;

import store.cart.Cart;
import store.engine.StoreEngine;
import store.gui.cart.CartPanel;
import store.gui.cart.CheckoutDialog;
import store.products.Product;

import javax.swing.JFrame;

/**
 * Controller for cart-related actions.
 * Manages cart updates and coordinates between the cart view and model.
 */
public class CartController {

    /**
     * Parent frame for dialogs.
     */
    private final JFrame parent;

    /**
     * Shared store engine.
     */
    private final StoreEngine engine;

    /**
     * Shopping cart model.
     */
    private final Cart cart;

    /**
     * Cart panel view.
     */
    private CartPanel cartPanel;

    /**
     * Creates a new CartController.
     *
     * @param parent parent frame
     * @param engine store engine
     * @param cart shopping cart
     */
    public CartController(JFrame parent,StoreEngine engine,Cart cart) {
        this.parent=parent;
        this.engine=engine;
        this.cart=cart;
    }

    /**
     * Returns the shopping cart.
     *
     * @return cart instance
     */
    public Cart getCart() {
        return cart;
    }

    /**
     * Sets the cart panel view and refreshes it.
     *
     * @param cartPanel cart panel
     */
    public void setCartPanel(CartPanel cartPanel) {
        this.cartPanel=cartPanel;
        refreshCartView();
    }

    /**
     * Increases quantity of a product in the cart.
     *
     * @param p product to increase
     */
    public void increase(Product p) {
        if(p==null){
            return;
        }
        cart.addItem(p,1);
        refreshCartView();
    }

    /**
     * Decreases quantity of a product in the cart.
     *
     * @param p product to decrease
     */
    public void decrease(Product p) {
        if(p==null){
            return;
        }
        cart.decreaseItem(p);
        refreshCartView();
    }

    /**
     * Removes a product from the cart.
     *
     * @param p product to remove
     */
    public void remove(Product p) {
        if(p==null){
            return;
        }
        cart.removeItem(p);
        refreshCartView();
    }

    /**
     * Opens the checkout dialog.
     */
    public void checkout() {
        CheckoutDialog dialog=new CheckoutDialog(parent,engine,cart);
        dialog.setVisible(true);
        refreshCartView();
    }

    /**
     * Refreshes the cart panel view.
     */
    private void refreshCartView() {
        if(cartPanel==null){
            return;
        }
        cartPanel.refreshItems();
        cartPanel.updateTotal();
    }
}
