// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.gui.cart.controller;
import store.gui.cart.CartPanel;
import store.cart.Cart;
import store.products.Product;

/**
 * Controller responsible for handling cart actions from the GUI.
 * Acts as a bridge between the cart view and the cart model.
 */
public class CartController {

    private Cart cart;
    private CartPanel cartPanel;


    /**
     * Creates a new CartController.
     *
     * @param cart shopping cart to control
     */
    public CartController(Cart cart) {
        this.cart = cart;
    }

    /**
     * Increases the quantity of a product in the cart by one.
     *
     * @param p product to increase
     */
    public void increase(Product p) {
        if (p != null) {
            cart.addItem(p, 1);
            if (cartPanel != null) {
                cartPanel.refresh();
            }
        }
    }


    /**
     * Decreases the quantity of a product in the cart by one.
     *
     * @param p product to decrease
     */
    public void decrease(Product p) {
        if (p != null) {
            cart.decreaseItem(p);
        }
    }
    public void setCartPanel(CartPanel cartPanel) {
        this.cartPanel = cartPanel;
    }

    /**
     * Removes a product completely from the cart.
     *
     * @param p product to remove
     */
    public void remove(Product p) {
        if (p != null) {
            cart.removeItem(p);
        }
    }

    /**
     * Returns the controlled shopping cart.
     *
     * @return cart
     */
    public Cart getCart() {
        return cart;
    }
}
