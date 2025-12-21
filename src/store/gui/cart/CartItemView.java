// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.gui.cart;

import store.cart.CartItem;
import store.gui.cart.controller.CartController;
import javax.swing.*;
import java.awt.*;

/**
 * Visual component representing a single cart item inside the cart panel.
 * Displays product name, quantity and total price.
 */
public class CartItemView extends JPanel {

    private CartItem item;
    private CartController controller;
    private JLabel quantityLabel;
    private JLabel priceLabel;

    /**
     * Creates a new CartItemView for the given cart item.
     *
     * @param item cart item to display
     */
    public CartItemView(CartItem item, CartController controller) {
        this.item = item;
        this.controller = controller;

        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel nameLabel = new JLabel(item.getProduct().getName());

        quantityLabel = new JLabel("Qty: " + item.getQuantity());
        priceLabel = new JLabel("₪" + item.getTotalPrice());

        JButton plusBtn = new JButton("+");
        JButton minusBtn = new JButton("-");
        JButton removeBtn = new JButton("X");

        plusBtn.addActionListener(e -> controller.increase(item.getProduct()));
        minusBtn.addActionListener(e -> controller.decrease(item.getProduct()));
        removeBtn.addActionListener(e -> controller.remove(item.getProduct()));

        JPanel left = new JPanel(new GridLayout(2, 1));
        left.add(nameLabel);
        left.add(priceLabel);

        JPanel center = new JPanel();
        center.add(minusBtn);
        center.add(quantityLabel);
        center.add(plusBtn);

        add(left, BorderLayout.WEST);
        add(center, BorderLayout.CENTER);
        add(removeBtn, BorderLayout.EAST);
    }

    /**
     * Refreshes the displayed quantity and price.
     */
    public void refresh() {
        quantityLabel.setText("Qty: " + item.getQuantity());
        priceLabel.setText("₪" + item.getTotalPrice());
    }

}
