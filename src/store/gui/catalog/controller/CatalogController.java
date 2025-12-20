// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.gui.catalog.controller;

import store.engine.StoreEngine;
import store.products.Category;
import store.products.Product;
import store.gui.catalog.ProductCatalogPanel;

import javax.swing.*;
import java.util.List;

/**
 * Controller responsible for managing catalog interactions.
 * Connects the catalog GUI with the StoreEngine.
 */
public class CatalogController {

    private final StoreEngine engine;

    /**
     * Creates a new CatalogController.
     *
     * @param engine store engine reference
     */
    public CatalogController(StoreEngine engine) {
        this.engine = engine;
    }

    /**
     * Creates and returns the catalog panel.
     *
     * @return catalog panel
     */
    public JPanel createCatalogPanel() {
        List<Product> products = engine.getAvailableProducts();
        return new ProductCatalogPanel(products, this);
    }

    /**
     * Filters products by text and category.
     *
     * @param text search text
     * @param category selected category
     * @return filtered list of products
     */
    public List<Product> filter(String text, Category category) {
        return engine.getAvailableProducts().stream()
                .filter(p ->
                        text == null || text.isEmpty() ||
                                p.getName().toLowerCase().contains(text.toLowerCase())
                )
                .filter(p ->
                        category == null || p.getCategory() == category
                )
                .toList();
    }
}
