// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
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

    private CatalogController controller;
    private JPanel gridPanel;
    private JTextField searchField;
    private JComboBox<Category> categoryBox;

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

        updateGrid(products);
    }

    /**
     * Initializes the top panel with search and category filter.
     */
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

    /**
     * Initializes the grid panel used to show products.
     */
    private void initGridPanel() {
        gridPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        add(new JScrollPane(gridPanel), BorderLayout.CENTER);
    }

    /**
     * Applies search and category filters.
     */
    private void applyFilters() {
        String text = searchField.getText();
        Category category = (Category) categoryBox.getSelectedItem();
        List<Product> result = controller.filter(text, category);
        updateGrid(result);
    }

    /**
     * Updates the grid with the given product list.
     *
     * @param products products to display
     */
    private void updateGrid(List<Product> products) {
        gridPanel.removeAll();
        for (Product p : products) {
            gridPanel.add(new ProductCard(p, controller));
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }
}
