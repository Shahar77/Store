package store.gui.cart.controller;

import store.cart.Cart;
import store.engine.StoreEngine;
import store.gui.cart.CartPanel;
import store.gui.cart.CheckoutDialog;
import store.products.Product;

import javax.swing.JFrame;

public class CartController {

    private final JFrame parent;
    private final StoreEngine engine;
    private final Cart cart;

    private CartPanel cartPanel;

    public CartController(JFrame parent, StoreEngine engine, Cart cart) {
        this.parent = parent;
        this.engine = engine;
        this.cart = cart;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCartPanel(CartPanel cartPanel) {
        this.cartPanel = cartPanel;
        refreshCartView();
    }

    public void increase(Product p) {
        if (p == null) return;
        cart.addItem(p, 1);
        refreshCartView();
    }

    public void decrease(Product p) {
        if (p == null) return;
        cart.decreaseItem(p);
        refreshCartView();
    }

    public void remove(Product p) {
        if (p == null) return;
        cart.removeItem(p);
        refreshCartView();
    }

    public void checkout() {
        CheckoutDialog dialog = new CheckoutDialog(parent, engine, cart);
        dialog.setVisible(true);
        refreshCartView();
    }

    private void refreshCartView() {
        if (cartPanel == null) return;
        cartPanel.refreshItems();
        cartPanel.updateTotal();
    }
}
