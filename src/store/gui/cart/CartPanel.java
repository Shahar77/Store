package store.gui.cart;

import store.gui.cart.controller.CartController;
import store.engine.StoreEngine;
import store.cart.CartItem;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Main cart panel showing all cart items and checkout.
 */
public class CartPanel extends JPanel{

    private final CartController controller;
    private final JPanel itemsPanel;
    private final JLabel totalLabel;

    /**
     * Creates cart panel.
     * @param engine store engine
     */
    public CartPanel(StoreEngine engine){
        this.controller=new CartController(engine);
        setLayout(new BorderLayout());

        itemsPanel=new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel,BoxLayout.Y_AXIS));
        add(new JScrollPane(itemsPanel),BorderLayout.CENTER);

        totalLabel=new JLabel();
        JButton checkoutButton=new JButton("Checkout");

        JPanel bottom=new JPanel(new BorderLayout());
        bottom.add(totalLabel,BorderLayout.WEST);
        bottom.add(checkoutButton,BorderLayout.EAST);

        add(bottom,BorderLayout.SOUTH);

        refresh();
    }



    /**
     * Refreshes the cart view from engine data.
     */
    public void refresh(){
        itemsPanel.removeAll();
        for(CartItem item:engine.getCart().getItems()){
            itemsPanel.add(new CartItemView(item,controller,this));
        }
        totalLabel.setText("Total: ₪"+engine.getCart().calculateTotal());
        revalidate();
        repaint();
    }


    checkoutButton.addActionListener(e->{
        new CheckoutDialog(
                (JFrame)SwingUtilities.getWindowAncestor(this),
                engine,
                this
        ).setVisible(true);
    });

}
