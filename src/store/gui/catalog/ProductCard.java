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

        setLayout(new BorderLayout(5,5));
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JLabel nameLabel = new JLabel(product.getName(), SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel priceLabel = new JLabel("₪" + product.getPrice(), SwingConstants.CENTER);

        ImageIcon icon = new ImageIcon("images/default.png");
        Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton addButton = new JButton("Add to Cart");
        addButton.addActionListener(e -> controller.addToCart(product));

        add(nameLabel, BorderLayout.NORTH);
        add(imageLabel, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new GridLayout(2,1));
        bottom.add(priceLabel);
        bottom.add(addButton);

        add(bottom, BorderLayout.SOUTH);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ProductDetailsDialog(product);
            }
        });

    }
}
