package store.gui.cart;

import store.cart.CartItem;
import store.gui.cart.controller.CartController;

import javax.swing.*;
import java.awt.*;

/**
 * Visual representation of a single cart item.
 */
public class CartItemView extends JPanel{

    /**
     * Creates a cart item row with + / - buttons.
     * @param item cart item
     * @param controller cart controller
     * @param refresh callback to refresh UI
     */
    public CartItemView(CartItem item,CartController controller,Runnable refresh){
        setLayout(new GridLayout(1,5));
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        add(new JLabel(item.getProduct().getName()));
        add(new JLabel("₪"+item.getProduct().getPrice()));
        add(new JLabel("Qty: "+item.getQuantity()));

        JButton plus=new JButton("+");
        JButton minus=new JButton("-");

        plus.addActionListener(e->{
            controller.add(item.getProduct());
            refresh.run();
        });

        minus.addActionListener(e->{
            controller.decrease(item);
            refresh.run();
        });

        add(plus);
        add(minus);
    }
}
