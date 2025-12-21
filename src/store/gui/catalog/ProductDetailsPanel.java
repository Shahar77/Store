// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.gui.catalog;

import store.products.Product;

import javax.swing.*;
import java.awt.*;

/**
 * Panel that displays detailed information about a product.
 * Can be embedded inside other windows or dialogs.
 */
public class ProductDetailsPanel extends JPanel {

    /**
     * Creates a ProductDetailsPanel for the given product.
     *
     * @param product product to display
     */
    public ProductDetailsPanel(Product product) {

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel(product.getDisplayName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel priceLabel = new JLabel("₪" + product.getPrice());
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        JTextArea detailsArea = new JTextArea(product.getDisplayDetails());
        detailsArea.setEditable(false);
        detailsArea.setLineWrap(true);
        detailsArea.setWrapStyleWord(true);

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.add(nameLabel);
        topPanel.add(priceLabel);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(detailsArea), BorderLayout.CENTER);
    }
}
