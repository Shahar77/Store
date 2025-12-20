// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.gui.cart;

import store.cart.Cart;
import store.cart.CartItem;

import javax.swing.*;
import java.awt.*;

/**
 * Panel that displays the shopping cart and its contents.
 * Allows checkout and item management.
 */
public class CartPanel extends JPanel {

    private Cart cart;
    private JPanel itemsPanel;
    private JLabel totalLabel;
    private JButton checkoutButton;

    /**
     * Creates a new CartPanel for the given cart.
     *
     * @param cart shopping cart
     */
    public CartPanel(Cart cart) {
        this.cart = cart;
        setLayout(new BorderLayout());

        initTop();
        initCenter();
        initBottom();

        refresh();
    }

    /**
     * Initializes the top section of the panel.
     */
    private void initTop() {
        JLabel title = new JLabel("Your Cart", SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);
    }

    /**
     * Initializes the center section containing cart items.
     */
    private void initCenter() {
        itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        add(new JScrollPane(itemsPanel), BorderLayout.CENTER);
    }

    /**
     * Initializes the bottom section with total and checkout button.
     */
    private void initBottom() {
        JPanel bottom = new JPanel(new BorderLayout());

        totalLabel = new JLabel();
        checkoutButton = new JButton("Checkout");

        bottom.add(totalLabel, BorderLayout.WEST);
        bottom.add(checkoutButton, BorderLayout.EAST);

        add(bottom, BorderLayout.SOUTH);
    }

    /**
     * Refreshes the cart panel UI.
     */
    public void refresh() {
        itemsPanel.removeAll();

        for (CartItem item : cart.getItems()) {
            itemsPanel.add(new JLabel(
                    item.getProduct().getName() +
                            " x" + item.getQuantity()
            ));
        }

        totalLabel.setText("Total: ₪" + cart.calculateTotal());

        revalidate();
        repaint();
    }
}
