package store.gui.orders;

import store.engine.StoreEngine;
import store.orders.Order;

import javax.swing.*;

/**
 * Displays order history.
 */
public class OrderHistoryWindow extends JFrame{

    /**
     * Creates order history window.
     * @param engine store engine
     */
    public OrderHistoryWindow(StoreEngine engine){
        super("Order History");

        DefaultListModel<String> model=new DefaultListModel<>();
        for(Order o:engine.getOrderHistory()){
            model.addElement("Order #"+o.getOrderID()+" - ₪"+o.getTotalAmount());
        }

        add(new JScrollPane(new JList<>(model)));
        setSize(300,400);
        setLocationRelativeTo(null);
    }
}
