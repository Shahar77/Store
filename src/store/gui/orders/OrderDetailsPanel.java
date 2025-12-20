// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.gui.orders;

import store.cart.CartItem;
import store.orders.Order;

import javax.swing.*;
import java.awt.*;

/**
 * Panel that displays a single order details summary.
 */
public class OrderDetailsPanel extends JPanel{
    /**
     * Creates order details panel.
     * @param order order
     */
    public OrderDetailsPanel(Order order){
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY,1),
                BorderFactory.createEmptyBorder(8,8,8,8)
        ));

        JLabel header=new JLabel("Order #"+order.getOrderID()+" | Status: "+order.getStatus()+" | Total: "+order.getTotalAmount()+" ₪");
        add(header,BorderLayout.NORTH);

        JPanel itemsPanel=new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel,BoxLayout.Y_AXIS));

        if(order.getItems().isEmpty()){
            itemsPanel.add(new JLabel("No items in this order"));
        }else{
            for(CartItem item:order.getItems()){
                itemsPanel.add(new JLabel(item.getProduct().getName()+" x"+item.getQuantity()));
            }
        }

        add(itemsPanel,BorderLayout.CENTER);
    }
}
