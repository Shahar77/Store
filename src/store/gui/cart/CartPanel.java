// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.gui.cart;

import store.cart.Cart;
import store.cart.CartItem;
import store.engine.StoreEngine;
import store.gui.cart.controller.CartController;

import javax.swing.*;
import java.awt.*;

/**
 * Panel that displays the shopping cart and its contents.
 * Allows checkout and item management.
 */
public class CartPanel extends JPanel {

    private final Cart cart;
    private final StoreEngine engine;

    private JPanel itemsPanel;
    private JLabel totalLabel;
    private JButton checkoutButton;

    private final CartController controller;

    public CartPanel(Cart cart, CartController controller, StoreEngine engine) {
        this.cart = cart;
        this.controller = controller;
        this.engine = engine;

        setLayout(new BorderLayout());

        initTop();
        initCenter();
        initBottom();

        refresh();
    }

    private void initTop() {
        JLabel title = new JLabel("Your Cart", SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);
    }

    private void initCenter() {
        itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        add(new JScrollPane(itemsPanel), BorderLayout.CENTER);
    }

    private void initBottom() {
        JPanel bottom = new JPanel(new BorderLayout());

        totalLabel = new JLabel();
        checkoutButton = new JButton("Checkout");

        bottom.add(totalLabel, BorderLayout.WEST);
        bottom.add(checkoutButton, BorderLayout.EAST);

        add(bottom, BorderLayout.SOUTH);

        checkoutButton.addActionListener(e -> controller.checkout());
    }

    /**
     * Refreshes the cart panel UI.
     */
    public void refresh() {
        itemsPanel.removeAll();

        for (CartItem item : cart.getItems()) {
            itemsPanel.add(new CartItemView(item, controller));
        }

        totalLabel.setText("Total: ₪" + cart.calculateTotal());

        revalidate();
        repaint();
    }

    // תוספות בשביל CartController

    public void refreshItems() {
        refresh();
    }

    public void updateTotal() {
        refresh();
    }
}
