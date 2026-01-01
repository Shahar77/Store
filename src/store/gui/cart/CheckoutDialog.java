/**
 * Submitted by:
 * Sarah Gabay - ID 329185771
 * Shahar Ezra - ID 329186118
 */
package store.gui.cart;

import store.cart.Cart;
import store.engine.StoreEngine;
import store.orders.Order;
import store.orders.OrderManager;

import javax.swing.*;
import java.awt.*;

/**
 * Checkout dialog used to confirm and complete an order.
 */
public class CheckoutDialog extends JDialog {

    /**
     * Shared store engine.
     */
    private final StoreEngine engine;

    /**
     * Shopping cart model.
     */
    private final Cart cart;

    /**
     * Creates a new CheckoutDialog.
     *
     * @param parent parent frame
     * @param engine store engine
     * @param cart shopping cart
     */
    public CheckoutDialog(JFrame parent,StoreEngine engine,Cart cart) {
        super(parent,"Checkout",true);

        this.engine=engine;
        this.cart=cart;

        setSize(420,220);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        initUi();
    }

    /**
     * Initializes the dialog UI components.
     */
    private void initUi() {
        JLabel label=new JLabel("Confirm purchase?",SwingConstants.CENTER);
        add(label,BorderLayout.CENTER);

        JPanel buttons=new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton yes=new JButton("Yes");
        JButton no=new JButton("No");

        yes.addActionListener(e -> confirmCheckout());
        no.addActionListener(e -> dispose());

        buttons.add(yes);
        buttons.add(no);

        add(buttons,BorderLayout.SOUTH);
    }

    /**
     * Confirms checkout and creates an order.
     */
    private void confirmCheckout() {
        if(cart.getItems().isEmpty()){
            JOptionPane.showMessageDialog(this,"Cart is empty");
            return;
        }

        Order order=engine.createOrderFromCart(cart);

        if(order==null){
            JOptionPane.showMessageDialog(this,"Order could not be created");
            return;
        }

        OrderManager.add(order);
        order.pay();

        cart.clear();
        JOptionPane.showMessageDialog(
                this,
                "Order #"+order.getOrderID()+" completed successfully"
        );

        dispose();
    }
}
