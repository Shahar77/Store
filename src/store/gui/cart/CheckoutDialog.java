package store.gui.cart;

import store.engine.StoreEngine;
import store.orders.Order;

import javax.swing.*;
import java.awt.*;

/**
 * Dialog responsible for checkout confirmation.
 */
public class CheckoutDialog extends JDialog{

    private final StoreEngine engine;

    /**
     * Creates a checkout dialog.
     * @param parent parent frame
     * @param engine store engine
     */
    public CheckoutDialog(JFrame parent,StoreEngine engine){
        super(parent,"Checkout",true);
        this.engine=engine;
        initUI();
    }

    /**
     * Initializes checkout UI.
     */
    private void initUI(){
        setLayout(new BorderLayout());
        add(new JLabel("Confirm checkout?"),BorderLayout.CENTER);

        JButton confirmButton=new JButton("Confirm");
        confirmButton.addActionListener(e->confirmCheckout());
        add(confirmButton,BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(getParent());
    }

    /**
     * Confirms checkout and creates an order.
     */
    private void confirmCheckout(){
        Order order=engine.checkout();
        if(order==null){
            JOptionPane.showMessageDialog(this,"Cart is empty");
        }else{
            JOptionPane.showMessageDialog(this,"Order created successfully");
            dispose();
        }
    }
}
