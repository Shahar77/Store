package store.gui.cart;

import store.engine.StoreEngine;
import store.orders.Order;

import javax.swing.*;

/**
 * Checkout confirmation dialog.
 */
public class CheckoutDialog extends JDialog{

    /**
     * Creates checkout dialog.
     * @param parent parent frame
     * @param engine store engine
     * @param cartPanel cart panel
     */
    public CheckoutDialog(JFrame parent,StoreEngine engine,CartPanel cartPanel){
        super(parent,"Checkout",true);

        JButton confirm=new JButton("Confirm Payment");

        confirm.addActionListener(e->{
            Order order=engine.checkout();
            if(order!=null){
                JOptionPane.showMessageDialog(this,
                        "Order #"+order.getOrderID()+" created!");
                cartPanel.refresh();
                dispose();
            }
        });

        add(confirm);
        pack();
        setLocationRelativeTo(parent);
    }
}
