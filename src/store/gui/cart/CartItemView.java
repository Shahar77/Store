// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.gui.cart;

import store.cart.CartItem;

import javax.swing.*;
import java.awt.*;

/**
 * Visual component representing a single cart item inside the cart panel.
 * Displays product name, quantity and total price.
 */
public class CartItemView extends JPanel {

    private CartItem item;
    private JLabel quantityLabel;
    private JLabel priceLabel;

    /**
     * Creates a new CartItemView for the given cart item.
     *
     * @param item cart item to display
     */
    public CartItemView(CartItem item) {
        this.item = item;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel nameLabel = new JLabel(item.getProduct().getName());

        quantityLabel = new JLabel("Qty: " + item.getQuantity());
        priceLabel = new JLabel("Price: ₪" + item.getTotalPrice());

        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.add(quantityLabel);
        infoPanel.add(priceLabel);

        add(nameLabel, BorderLayout.WEST);
        add(infoPanel, BorderLayout.EAST);
    }

    /**
     * Refreshes the displayed quantity and price.
     */
    public void refresh() {
        quantityLabel.setText("Qty: " + item.getQuantity());
        priceLabel.setText("Price: ₪" + item.getTotalPrice());
    }
}
