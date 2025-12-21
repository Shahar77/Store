package store.gui.orders;

import store.engine.StoreEngine;
import store.orders.Order;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Window that displays the order history of the store.
 */
public class OrderHistoryWindow extends JFrame{

    private final StoreEngine engine;

    /**
     * Creates an order history window.
     * @param engine store engine
     */
    public OrderHistoryWindow(StoreEngine engine){
        super("Order History");
        this.engine=engine;
        initUI();
    }

    /**
     * Initializes the user interface.
     */
    private void initUI(){
        setLayout(new BorderLayout());

        List<Order> orders=engine.getOrderHistory();

        if(orders.isEmpty()){
            JLabel emptyLabel=new JLabel("No orders yet");
            emptyLabel.setHorizontalAlignment(SwingConstants.CENTER);
            add(emptyLabel,BorderLayout.CENTER);
        }else{
            JPanel ordersPanel=new JPanel();
            ordersPanel.setLayout(new BoxLayout(ordersPanel,BoxLayout.Y_AXIS));

            for(Order order:orders){
                JLabel orderLabel=new JLabel(
                        "Order #"+order.getOrderID()+" | Total: "+order.getTotalAmount()
                );
                ordersPanel.add(orderLabel);
            }

            JScrollPane scrollPane=new JScrollPane(ordersPanel);
            add(scrollPane,BorderLayout.CENTER);
        }

        setSize(400,300);
        setLocationRelativeTo(null);
    }
}
