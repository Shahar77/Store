// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.gui.catalog;

import store.products.Product;
import store.gui.catalog.controller.CatalogController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Visual card component representing a single product in the catalog grid.
 * Displays basic product information and opens details on click.
 */
public class ProductCard extends JPanel {

    private Product product;
    private CatalogController controller;

    /**
     * Creates a new ProductCard for the given product.
     *
     * @param product product to display
     * @param controller catalog controller
     */
    public ProductCard(Product product, CatalogController controller) {
        this.product = product;
        this.controller = controller;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel nameLabel = new JLabel(product.getDisplayName(), SwingConstants.CENTER);
        JLabel priceLabel = new JLabel("₪" + product.getPrice(), SwingConstants.CENTER);

        add(nameLabel, BorderLayout.NORTH);
        add(priceLabel, BorderLayout.SOUTH);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ProductDetailsDialog(product);
            }
        });
    }
}
