/**
 * Submitted by:
 * Sarah Gabay - ID 329185771
 * Shahar Ezra - ID 329186118
 */

package store.gui.catalog.controller;

import store.engine.StoreEngine;
import store.products.Product;
import store.products.Category;
import store.gui.catalog.ProductCatalogPanel;
import store.gui.cart.controller.CartController;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for the product catalog screen.
 * Handles filtering and cart interactions.
 */
public class CatalogController {

    private final StoreEngine engine;
    private final CartController cartController;
    private ProductCatalogPanel catalogPanel;

    public CatalogController(StoreEngine engine, CartController cartController) {
        this.engine = engine;
        this.cartController = cartController;
    }

    public JPanel createCatalogPanel() {
        ProductCatalogPanel panel = new ProductCatalogPanel(engine.getAvailableProducts(), this);
        setCatalogPanel(panel);
        return panel;
    }

    /**
     * Sets the catalog panel reference so we can refresh UI after actions.
     *
     * @param panel catalog panel
     */
    public void setCatalogPanel(ProductCatalogPanel panel) {
        this.catalogPanel = panel;
    }

    public List<Product> filter(String text, Category category) {
        return engine.getAvailableProducts().stream()
                .filter(p -> text == null || text.isEmpty()
                        || p.getName().toLowerCase().contains(text.toLowerCase()))
                .filter(p -> category == null || p.getCategory() == category)
                .collect(Collectors.toList());
    }

    /**
     * Adds a product to cart and refreshes the catalog UI (in-cart highlight + toast).
     *
     * @param product product to add
     */
    public void addToCart(Product product) {
        cartController.increase(product);

        if (catalogPanel != null) {
            catalogPanel.showToast("Added to cart: " + product.getName());
            catalogPanel.refreshFromEngine();
        }
    }

    /**
     * Checks if product is currently inside the cart.
     *
     * @param p product
     * @return true if in cart
     */
    public boolean isInCart(Product p) {
        return cartController.getCart().contains(p);
    }

    /**
     * Returns quantity of product in the cart.
     *
     * @param p product
     * @return quantity in cart
     */
    public int getCartQuantity(Product p) {
        return cartController.getCart().getQuantity(p);
    }
}
