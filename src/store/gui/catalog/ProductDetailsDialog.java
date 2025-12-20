// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.gui.catalog;

import store.products.Product;

import javax.swing.*;
import java.awt.*;

/**
 * Dialog window that displays detailed information about a product.
 */
public class ProductDetailsDialog extends JDialog {

    /**
     * Creates and shows a dialog with product details.
     *
     * @param product product to display
     */
    public ProductDetailsDialog(Product product) {
        setTitle(product.getDisplayName());
        setModal(true);
        setSize(350, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTextArea detailsArea = new JTextArea(product.getDisplayDetails());
        detailsArea.setEditable(false);
        detailsArea.setLineWrap(true);
        detailsArea.setWrapStyleWord(true);

        add(new JScrollPane(detailsArea), BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        add(closeButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
