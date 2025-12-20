// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.gui.cart;

import store.cart.CartItem;
import store.gui.cart.controller.CartController;

import javax.swing.*;
import java.awt.*;

/**
 * UI component that displays one cart item with quantity controls.
 */
public class CartItemView extends JPanel{
    private final CartItem item;
    private final CartPanel parent;
    private final CartController controller;
    private final JLabel quantityLabel;
    private final JLabel priceLabel;

    /**
     * Creates item view.
     * @param item cart item
     * @param parent parent panel
     * @param controller cart controller
     */
    public CartItemView(CartItem item,CartPanel parent,CartController controller){
        this.item=item;
        this.parent=parent;
        this.controller=controller;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
        setMaximumSize(new Dimension(Integer.MAX_VALUE,110));

        JLabel nameLabel=new JLabel(item.getProduct().getName());
        quantityLabel=new JLabel("Qty: "+item.getQuantity());
        priceLabel=new JLabel("Price: "+calcPrice()+" ₪");

        JLabel imageLabel=createImageLabel(item.getProduct().getImagePath());

        JButton plusBtn=new JButton("+");
        JButton minusBtn=new JButton("-");
        JButton removeBtn=new JButton("Remove");

        plusBtn.addActionListener(e->increase());
        minusBtn.addActionListener(e->decrease());
        removeBtn.addActionListener(e->removeItem());

        JPanel qtyPanel=new JPanel();
        qtyPanel.add(minusBtn);
        qtyPanel.add(quantityLabel);
        qtyPanel.add(plusBtn);

        JPanel leftPanel=new JPanel(new GridLayout(3,1));
        leftPanel.add(nameLabel);
        leftPanel.add(qtyPanel);
        leftPanel.add(priceLabel);

        JPanel centerPanel=new JPanel(new BorderLayout());
        centerPanel.add(imageLabel,BorderLayout.WEST);
        centerPanel.add(leftPanel,BorderLayout.CENTER);

        add(centerPanel,BorderLayout.CENTER);
        add(removeBtn,BorderLayout.EAST);
    }

    /**
     * Calculates price for display.
     * @return price
     */
    private double calcPrice(){
        return item.getProduct().getPrice()*item.getQuantity();
    }

    /**
     * Increases quantity by controller.
     */
    private void increase(){
        controller.increase(item.getProduct());
        parent.refreshAfterChange();
    }

    /**
     * Decreases quantity by controller.
     */
    private void decrease(){
        controller.decrease(item.getProduct());
        parent.refreshAfterChange();
    }

    /**
     * Removes item by controller.
     */
    private void removeItem(){
        controller.remove(item.getProduct());
        parent.refreshAfterChange();
    }

    /**
     * Creates an image label from path with fallback.
     * @param path path
     * @return label
     */
    private JLabel createImageLabel(String path){
        ImageIcon icon=null;
        if(path!=null&&!path.isEmpty())icon=new ImageIcon(path);

        JLabel label=new JLabel();
        label.setPreferredSize(new Dimension(80,80));

        if(icon==null||icon.getIconWidth()<=0){
            label.setText("No Image");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            return label;
        }

        Image scaled=icon.getImage().getScaledInstance(70,70,Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(scaled));
        return label;
    }
}
