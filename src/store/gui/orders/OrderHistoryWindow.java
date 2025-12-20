// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.gui.orders;

import store.core.Customer;
import store.orders.Order;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Window that displays customer's order history.
 */
public class OrderHistoryWindow extends JFrame{
    private final Customer customer;

    /**
     * Creates order history window for a customer.
     * @param customer customer
     */
    public OrderHistoryWindow(Customer customer){
        super("Order History");
        this.customer=customer;

        setSize(520,420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initUI();
    }

    /**
     * Initializes UI.
     */
    private void initUI(){
        getContentPane().removeAll();

        JPanel mainPanel=new JPanel(new BorderLayout());
        JLabel title=new JLabel("Your Orders");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial",Font.BOLD,16));
        mainPanel.add(title,BorderLayout.NORTH);

        JPanel ordersPanel=new JPanel();
        ordersPanel.setLayout(new BoxLayout(ordersPanel,BoxLayout.Y_AXIS));

        List<Order> orders=customer==null?List.of():customer.getOrderHistory();

        if(orders.isEmpty()){
            JLabel emptyLabel=new JLabel("No orders yet");
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            ordersPanel.add(emptyLabel);
        }else{
            for(Order order:orders){
                OrderDetailsPanel panel=new OrderDetailsPanel(order);
                panel.setAlignmentX(Component.LEFT_ALIGNMENT);
                ordersPanel.add(panel);
                ordersPanel.add(Box.createVerticalStrut(8));
            }
        }

        mainPanel.add(new JScrollPane(ordersPanel),BorderLayout.CENTER);
        add(mainPanel);

        revalidate();
        repaint();
    }
}
