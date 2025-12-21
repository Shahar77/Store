package store.gui.cart;

import store.cart.CartItem;

import javax.swing.*;
import java.awt.*;

/**
 * UI row representing a cart item with quantity controls.
 */
public class CartItemView extends JPanel{

    private final CartItem item;
    private final CartPanel parent;

    /**
     * Creates a cart item view.
     * @param item cart item
     * @param parent cart panel
     */
    public CartItemView(CartItem item,CartPanel parent){
        this.item=item;
        this.parent=parent;

        setLayout(new BorderLayout());

        JLabel name=new JLabel(item.getProduct().getName());
        JLabel qty=new JLabel("x"+item.getQuantity());

        JButton plus=new JButton("+");
        JButton minus=new JButton("-");
        JButton remove=new JButton("X");

        plus.addActionListener(e->{
            item.setQuantity(item.getQuantity()+1);
            parent.refresh();
        });

        minus.addActionListener(e->{
            if(item.getQuantity()>1){
                item.setQuantity(item.getQuantity()-1);
            }
            parent.refresh();
        });

        remove.addActionListener(e->{
            parent.getEngine().getCart().removeItem(item.getProduct());
            parent.refresh();
        });

        JPanel buttons=new JPanel();
        buttons.add(minus);
        buttons.add(qty);
        buttons.add(plus);
        buttons.add(remove);

        add(name,BorderLayout.WEST);
        add(buttons,BorderLayout.EAST);
    }
}
