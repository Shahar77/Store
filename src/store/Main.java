/**
 * Submitted by:
 * Sarah Gabay - ID 329185771
 * Shahar Ezra - ID 329186118
 */
package store;

import store.engine.StoreEngine;
import store.gui.launcher.LauncherWindow;
import store.products.Category;
import store.products.Product;
import store.products.SimpleProduct;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Application entry point.
 * Initializes the store engine, seeds initial products
 * and launches the GUI.
 */
public class Main {

    /**
     * Main method of the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        StoreEngine engine=new StoreEngine();
        seedProducts(engine);

        LauncherWindow w=new LauncherWindow(engine);
        w.setVisible(true);
    }

    /**
     * Seeds the store with initial products.
     *
     * @param engine store engine instance
     */
    private static void seedProducts(StoreEngine engine) {
        List<Product> products=new ArrayList<>();

        products.add(new SimpleProduct(
                "T-Shirt",
                59.9,
                20,
                "Basic t-shirt",
                Category.CLOTHING,
                Color.WHITE,
                "images/default.png"
        ));

        products.add(new SimpleProduct(
                "Book",
                39.9,
                15,
                "Nice book",
                Category.BOOKS,
                Color.WHITE,
                "images/default.png"
        ));

        products.add(new SimpleProduct(
                "Headphones",
                199.0,
                10,
                "Wireless headphones",
                Category.ELECTRONICS,
                Color.WHITE,
                "images/default.png"
        ));

        engine.setProducts(products);
    }
}
