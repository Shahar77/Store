// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.gui.orders;

import store.orders.Order;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Window that displays the customer's order history.
 * Shows all previous orders in a scrollable view.
 */
public class OrderHistoryWindow extends JFrame {

    /**
     * Creates a new OrderHistoryWindow with the given orders.
     *
     * @param orders list of orders to display
     */
    public OrderHistoryWindow(List<Order> orders) {

        setTitle("Order History");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Your Orders", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(title, BorderLayout.NORTH);

        JPanel ordersPanel = new JPanel();
        ordersPanel.setLayout(new BoxLayout(ordersPanel, BoxLayout.Y_AXIS));

        if (orders.isEmpty()) {
            ordersPanel.add(new JLabel("No orders yet"));
        } else {
            for (Order order : orders) {
                ordersPanel.add(new OrderDetailsPanel(order));
                ordersPanel.add(Box.createVerticalStrut(8));
            }
        }

        mainPanel.add(new JScrollPane(ordersPanel), BorderLayout.CENTER);
        add(mainPanel);
    }
}

