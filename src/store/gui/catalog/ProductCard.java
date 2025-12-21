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

    /**
     * Listener used to notify selection in the catalog panel.
     */
    public interface SelectionListener {
        void onSelected(ProductCard card);
    }

    private final Product product;
    private final CatalogController controller;

    private boolean selected;
    private boolean inCart;

    /**
     * Creates a new ProductCard for the given product.
     *
     * @param product product to display
     * @param controller catalog controller
     * @param selectionListener selection callback
     */
    public ProductCard(Product product, CatalogController controller, SelectionListener selectionListener) {
        this.product = product;
        this.controller = controller;

        setLayout(new BorderLayout(5, 5));
        setOpaque(true);

        setToolTipText(product.toString());

        JLabel nameLabel = new JLabel(product.getName(), SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel priceLabel = new JLabel("₪" + product.getPrice(), SwingConstants.CENTER);

        String path = product.getImagePath();
        ImageIcon icon;
        if (path != null && !path.isEmpty()) {
            icon = new ImageIcon(path);
        } else {
            icon = new ImageIcon("images/default.png");
        }

        Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton addButton = new JButton("Add to Cart");
        addButton.addActionListener(e -> controller.addToCart(product));

        add(nameLabel, BorderLayout.NORTH);
        add(imageLabel, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new GridLayout(2, 1));
        bottom.add(priceLabel);
        bottom.add(addButton);

        add(bottom, BorderLayout.SOUTH);

        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (selectionListener != null) selectionListener.onSelected(ProductCard.this);
                new ProductDetailsDialog(product);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!selected) {
                    setBackground(new Color(245, 245, 245));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!selected) {
                    setBackground(Color.WHITE);
                }
            }
        };

        addMouseListener(adapter);
        imageLabel.addMouseListener(adapter);
        nameLabel.addMouseListener(adapter);

        setBackground(Color.WHITE);
        updateVisualState();
    }

    /**
     * Marks this card as selected/unselected.
     *
     * @param value true if selected
     */
    public void setSelected(boolean value) {
        this.selected = value;
        updateVisualState();
    }

    /**
     * Marks this card as in-cart (visual highlight).
     *
     * @param value true if product is in cart
     */
    public void setInCart(boolean value) {
        this.inCart = value;
        updateVisualState();
    }

    private void updateVisualState() {
        if (selected) {
            setBorder(BorderFactory.createLineBorder(new Color(30, 110, 220), 3));
            setBackground(new Color(235, 245, 255));
        } else if (inCart) {
            setBorder(BorderFactory.createLineBorder(new Color(40, 160, 90), 3));
            setBackground(Color.WHITE);
        } else {
            setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            setBackground(Color.WHITE);
        }
        repaint();
    }

    /**
     * Returns product of this card.
     *
     * @return product
     */
    public Product getProduct() {
        return product;
    }
}
