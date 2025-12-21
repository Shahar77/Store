package store.gui.cart;

import store.cart.CartItem;
import store.gui.cart.controller.CartController;

import javax.swing.*;
import java.awt.*;

/**
 * GUI component representing a single cart item.
 */
public class CartItemView extends JPanel{

    /**
     * Creates a cart item view.
     * @param item cart item
     * @param controller cart controller
     * @param refreshCallback callback to refresh UI
     */
    public CartItemView(CartItem item,CartController controller,Runnable refreshCallback){
        setLayout(new BorderLayout());

        JLabel nameLabel=new JLabel(item.getProduct().getName());
        JLabel quantityLabel=new JLabel("Qty: "+item.getQuantity());

        JButton removeButton=new JButton("Remove");
        removeButton.addActionListener(e->{
            controller.remove(item.getProduct());
            refreshCallback.run();
        });

        add(nameLabel,BorderLayout.WEST);
        add(quantityLabel,BorderLayout.CENTER);
        add(removeButton,BorderLayout.EAST);
    }
}
