// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
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

    public CatalogController(StoreEngine engine, CartController cartController) {
        this.engine = engine;
        this.cartController = cartController;
    }

    public JPanel createCatalogPanel() {
        return new ProductCatalogPanel(engine.getAvailableProducts(), this);
    }

    public List<Product> filter(String text, Category category) {
        return engine.getAvailableProducts().stream()
                .filter(p -> text == null || text.isEmpty()
                        || p.getName().toLowerCase().contains(text.toLowerCase()))
                .filter(p -> category == null || p.getCategory() == category)
                .collect(Collectors.toList());
    }

    public void addToCart(Product product) {
        cartController.increase(product);
    }
}
