// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.gui.catalog;

import store.gui.catalog.controller.CatalogController;
import store.products.Product;

import javax.swing.*;
import java.awt.*;

/**
 * Dialog that shows product details and allows adding to cart.
 */
public class ProductDetailsDialog extends JDialog{
    /**
     * Creates and shows the dialog.
     * @param product product
     * @param controller controller
     */
    public ProductDetailsDialog(Product product,CatalogController controller){
        setTitle(product.getName());
        setModal(true);
        setLayout(new BorderLayout());

        add(new ProductDetailsPanel(product),BorderLayout.CENTER);

        JButton addButton=new JButton("Add To Cart");
        addButton.addActionListener(e->{
            boolean ok=controller.addToCart(product,1);
            if(ok)JOptionPane.showMessageDialog(this,"Added to cart");
            else JOptionPane.showMessageDialog(this,"Cannot add to cart","Error",JOptionPane.ERROR_MESSAGE);
            dispose();
        });

        add(addButton,BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
