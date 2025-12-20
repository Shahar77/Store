// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.gui.catalog;

import store.products.Product;
import store.gui.catalog.controller.CatalogController;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

/**
 * Dialog window that displays detailed information about a product.
 * Allows adding the product to the cart.
 */
public class ProductDetailsDialog extends JDialog{

    private Product product;
    private CatalogController controller;

    /**
     * Creates a product details dialog.
     * @param product selected product
     * @param controller catalog controller
     */
    public ProductDetailsDialog(Product product,CatalogController controller){
        this.product=product;
        this.controller=controller;

        setTitle(product.getDisplayName());
        setModal(true);
        setSize(350,300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel infoPanel=new JPanel(new GridLayout(0,1));
        infoPanel.add(new JLabel(product.getDisplayName()));
        infoPanel.add(new JLabel("Price: ₪"+product.getPrice()));
        infoPanel.add(new JLabel("Category: "+product.getCategory()));
        infoPanel.add(new JLabel("Stock: "+product.getStock()));
        infoPanel.add(new JLabel("Description:"));
        infoPanel.add(new JLabel(product.getDescription()));

        add(infoPanel,BorderLayout.CENTER);

        JButton addToCartButton=new JButton("Add To Cart");
        addToCartButton.addActionListener(e->addToCart());

        add(addToCartButton,BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Adds the product to the cart using the controller.
     */
    private void addToCart(){
        boolean success=controller.addToCart(product);
        if(success){
            JOptionPane.showMessageDialog(this,"Product added to cart");
            dispose();
        }else{
            JOptionPane.showMessageDialog(this,"Could not add product to cart");
        }
    }
}
