// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.gui.cart;

import store.cart.Cart;
import store.cart.CartItem;
import store.core.Customer;
import store.gui.cart.controller.CartController;

import javax.swing.*;
import java.awt.*;

/**
 * Panel that displays the customer's cart and allows checkout.
 */
public class CartPanel extends JPanel{
    private final Customer customer;
    private final Cart cart;
    private final CartController controller;
    private JPanel itemsPanel;
    private JLabel totalLabel;
    private JButton checkoutButton;

    /**
     * Creates cart panel for a given customer.
     * @param customer customer
     */
    public CartPanel(Customer customer){
        this.customer=customer;
        cart=customer==null?null:customer.getCart();
        controller=cart==null?null:new CartController(cart);

        setLayout(new BorderLayout());
        initTop();
        initCenter();
        initBottom();
        refreshAfterChange();
    }

    /**
     * Initializes top area.
     */
    private void initTop(){
        JLabel title=new JLabel("Your Cart");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title,BorderLayout.NORTH);
    }

    /**
     * Initializes center area.
     */
    private void initCenter(){
        itemsPanel=new JPanel();
        itemsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK,2),
                BorderFactory.createEmptyBorder(10,10,10,10)
        ));
        itemsPanel.setLayout(new BoxLayout(itemsPanel,BoxLayout.Y_AXIS));
        add(new JScrollPane(itemsPanel),BorderLayout.CENTER);
    }

    /**
     * Initializes bottom area.
     */
    private void initBottom(){
        JPanel bottomPanel=new JPanel(new BorderLayout());
        totalLabel=new JLabel();
        checkoutButton=new JButton("Checkout");
        checkoutButton.addActionListener(e->openCheckout());
        bottomPanel.add(totalLabel,BorderLayout.WEST);
        bottomPanel.add(checkoutButton,BorderLayout.EAST);
        add(bottomPanel,BorderLayout.SOUTH);
    }

    /**
     * Refreshes cart items UI.
     */
    private void refreshItems(){
        itemsPanel.removeAll();
        if(cart!=null){
            for(CartItem item:cart.getItems()){
                itemsPanel.add(new CartItemView(item,this,controller));
                itemsPanel.add(Box.createVerticalStrut(6));
            }
        }
        itemsPanel.revalidate();
        itemsPanel.repaint();
    }

    /**
     * Updates total label.
     */
    public void updateTotal(){
        if(cart==null){
            totalLabel.setText("Total: 0 ₪");
            return;
        }
        totalLabel.setText("Total: "+cart.calculateTotal()+" ₪");
    }

    /**
     * Enables/disables checkout depending on cart state.
     */
    private void updateCheckoutEnabled(){
        checkoutButton.setEnabled(cart!=null&&!cart.getItems().isEmpty());
    }

    /**
     * Opens checkout dialog.
     */
    private void openCheckout(){
        if(cart==null||cart.getItems().isEmpty()){
            JOptionPane.showMessageDialog(this,"Cart is empty");
            refreshAfterChange();
            return;
        }
        JFrame frame=(JFrame)SwingUtilities.getWindowAncestor(this);
        CheckoutDialog dialog=new CheckoutDialog(frame,customer);
        dialog.setVisible(true);
        refreshAfterChange();
    }

    /**
     * Refreshes everything after cart change.
     */
    public void refreshAfterChange(){
        refreshItems();
        updateTotal();
        updateCheckoutEnabled();
    }
}
