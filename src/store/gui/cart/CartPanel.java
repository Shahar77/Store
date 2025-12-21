package store.gui.cart;

import store.engine.StoreEngine;
import store.gui.cart.controller.CartController;
import store.cart.CartItem;

import javax.swing.*;
import java.awt.*;

/**
 * GUI panel that displays the shopping cart.
 */
public class CartPanel extends JPanel{

    private final StoreEngine engine;
    private final CartController controller;
    private JPanel itemsPanel;

    /**
     * Creates a cart panel.
     * @param engine store engine
     */
    public CartPanel(StoreEngine engine){
        this.engine=engine;
        this.controller=new CartController(engine);
        setLayout(new BorderLayout());
        initUI();
    }

    /**
     * Initializes the cart UI.
     */
    private void initUI(){
        itemsPanel=new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel,BoxLayout.Y_AXIS));
        add(new JScrollPane(itemsPanel),BorderLayout.CENTER);

        JButton checkoutButton=new JButton("Checkout");
        checkoutButton.addActionListener(e->openCheckout());
        add(checkoutButton,BorderLayout.SOUTH);

        refreshAfterChange();
    }

    /**
     * Opens the checkout dialog.
     */
    private void openCheckout(){
        JFrame frame=(JFrame)SwingUtilities.getWindowAncestor(this);
        CheckoutDialog dialog=new CheckoutDialog(frame,engine);
        dialog.setVisible(true);
        refreshAfterChange();
    }

    /**
     * Refreshes cart display after changes.
     */
    private void refreshAfterChange(){
        itemsPanel.removeAll();
        for(CartItem item:controller.getCart().getItems()){
            itemsPanel.add(new CartItemView(item,controller,this::refreshAfterChange));
        }
        revalidate();
        repaint();
    }
}
