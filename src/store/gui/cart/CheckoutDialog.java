// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.gui.cart;

import store.cart.Cart;
import store.engine.StoreEngine;
import store.orders.Order;

import javax.swing.*;
import java.awt.*;
import store.orders.OrderManager;


/**
 * Dialog window that handles the checkout process.
 * Allows the user to confirm payment and create an order.
 */
public class CheckoutDialog extends JDialog {

    private Cart cart;
    private StoreEngine engine;
    private JButton confirmButton;

    /**
     * Creates a new CheckoutDialog.
     *
     * @param parent parent frame
     * @param cart shopping cart
     * @param engine store engine
     */
    public CheckoutDialog(JFrame parent, Cart cart, StoreEngine engine) {
        super(parent, "Checkout", true);
        this.cart = cart;
        this.engine = engine;

        setLayout(new BorderLayout());

        confirmButton = new JButton("Confirm Payment");
        confirmButton.addActionListener(e -> confirmCheckout());

        add(new JLabel("Confirm your order", SwingConstants.CENTER), BorderLayout.NORTH);
        add(confirmButton, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(parent);
    }

    /**
     * Confirms the checkout process.
     * Creates an order from the cart and clears it.
     */
    private void confirmCheckout() {

        if (cart.getItems().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cart is empty");
            return;
        }

        Order order = engine.createOrderFromCart(cart);

        if (order == null) {
            JOptionPane.showMessageDialog(this, "Order could not be created");
            return;
        }

        OrderManager.add(order);

        order.pay();

        cart.clear();
        JOptionPane.showMessageDialog(
                this,
                "Order #" + order.getOrderID() + " completed successfully"
        );

        dispose();
    }

}
