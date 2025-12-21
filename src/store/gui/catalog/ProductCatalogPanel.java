package store.gui.catalog;

import store.products.Product;
import store.products.Category;
import store.gui.catalog.controller.CatalogController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Panel that displays the product catalog.
 * Allows searching and filtering products by category.
 */
public class ProductCatalogPanel extends JPanel {

    private final CatalogController controller;
    private JPanel gridPanel;
    private JTextField searchField;
    private JComboBox<Category> categoryBox;

    private JLabel toastLabel;
    private Timer toastTimer;

    private ProductCard selectedCard;

    /**
     * Creates a new ProductCatalogPanel.
     *
     * @param products initial list of products to display
     * @param controller catalog controller
     */
    public ProductCatalogPanel(List<Product> products, CatalogController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        initTopPanel();
        initGridPanel();
        initToast();

        updateGrid(products);
    }

    private void initTopPanel() {
        JPanel topPanel = new JPanel(new FlowLayout());

        topPanel.add(new JLabel("Search:"));

        searchField = new JTextField(15);
        topPanel.add(searchField);

        JButton searchButton = new JButton("Search");
        topPanel.add(searchButton);

        categoryBox = new JComboBox<>(Category.values());
        categoryBox.setSelectedItem(null);
        topPanel.add(categoryBox);

        searchButton.addActionListener(e -> applyFilters());
        categoryBox.addActionListener(e -> applyFilters());

        add(topPanel, BorderLayout.NORTH);
    }

    private void initGridPanel() {
        gridPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        add(new JScrollPane(gridPanel), BorderLayout.CENTER);
    }

    private void initToast() {
        toastLabel = new JLabel(" ");
        toastLabel.setHorizontalAlignment(SwingConstants.CENTER);
        toastLabel.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        add(toastLabel, BorderLayout.SOUTH);
    }

    private void applyFilters() {
        String text = searchField.getText();
        Category category = (Category) categoryBox.getSelectedItem();
        List<Product> result = controller.filter(text, category);
        updateGrid(result);
    }

    /**
     * Refreshes catalog using current search/filter values.
     */
    public void refreshFromEngine() {
        applyFilters();
    }

    /**
     * Shows a small message that disappears after 1 second.
     *
     * @param msg message text
     */
    public void showToast(String msg) {
        toastLabel.setText(msg);

        if (toastTimer != null && toastTimer.isRunning()) {
            toastTimer.stop();
        }

        toastTimer = new Timer(1000, e -> toastLabel.setText(" "));
        toastTimer.setRepeats(false);
        toastTimer.start();
    }

    private void updateGrid(List<Product> products) {
        gridPanel.removeAll();
        selectedCard = null;

        for (Product p : products) {
            ProductCard card = new ProductCard(p, controller, this::selectCard);

            boolean inCart = controller.isInCart(p);
            card.setInCart(inCart);

            gridPanel.add(card);
        }

        gridPanel.revalidate();
        gridPanel.repaint();
    }

    private void selectCard(ProductCard card) {
        if (selectedCard != null) {
            selectedCard.setSelected(false);
        }
        selectedCard = card;
        if (selectedCard != null) {
            selectedCard.setSelected(true);
        }
    }
}
