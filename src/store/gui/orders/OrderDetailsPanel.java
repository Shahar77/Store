// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.gui.orders;

import store.orders.Order;
import store.cart.CartItem;

import javax.swing.*;
import java.awt.*;

/**
 * Panel that displays detailed information about a single order.
 * Shows order ID, status, total amount and ordered items.
 */
public class OrderDetailsPanel extends JPanel {

    /**
     * Creates a new OrderDetailsPanel for the given order.
     *
     * @param order order to display
     */
    public OrderDetailsPanel(Order order) {

        setLayout(new BorderLayout());
        setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.GRAY),
                        BorderFactory.createEmptyBorder(8, 8, 8, 8)
                )
        );

        JLabel header = new JLabel(
                "Order #" + order.getOrderID()
                        + " | Status: " + order.getStatus()
                        + " | Total: ₪" + order.getTotalAmount()
        );

        add(header, BorderLayout.NORTH);

        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));

        if (order.getItems().isEmpty()) {
            itemsPanel.add(new JLabel("No items in this order"));
        } else {
            for (CartItem item : order.getItems()) {
                itemsPanel.add(new JLabel(
                        item.getProduct().getName() +
                                " x" + item.getQuantity()
                ));
            }
        }

        add(itemsPanel, BorderLayout.CENTER);
    }
}
