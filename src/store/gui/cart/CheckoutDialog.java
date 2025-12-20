// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.gui.cart;

import store.core.Customer;
import store.gui.cart.controller.OrderController;
import store.io.orders.OrderHistoryWriter;
import store.orders.Order;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Checkout dialog that confirms payment and creates an order.
 */
public class CheckoutDialog extends JDialog{
    private final Customer customer;
    private final JButton confirmButton;

    /**
     * Creates checkout dialog.
     * @param parent parent frame
     * @param customer customer
     */
    public CheckoutDialog(JFrame parent,Customer customer){
        super(parent,"Checkout",true);
        this.customer=customer;

        setLayout(new BorderLayout());
        confirmButton=new JButton("Confirm Payment");
        confirmButton.addActionListener(e->confirmCheckout());
        add(confirmButton,BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(parent);
    }

    /**
     * Confirms checkout, creates order and clears cart.
     */
    private void confirmCheckout(){
        if(customer==null||customer.getCart().getItems().isEmpty()){
            JOptionPane.showMessageDialog(this,"Cart is empty","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }

        confirmButton.setEnabled(false);
        Order order=OrderController.checkout(customer);

        if(order==null){
            JOptionPane.showMessageDialog(this,"Could not create order","Error",JOptionPane.ERROR_MESSAGE);
            confirmButton.setEnabled(true);
            return;
        }

        order.pay();

        try{
            OrderHistoryWriter.write(order,"orders_history.csv");
        }catch(IOException ex){
            JOptionPane.showMessageDialog(this,"Order saved in memory, file write failed","Warning",JOptionPane.WARNING_MESSAGE);
        }

        JOptionPane.showMessageDialog(this,"Order #"+order.getOrderID()+" created successfully!");
        dispose();
    }
}

