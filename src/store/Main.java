// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store;

import store.engine.StoreEngine;
import store.products.*;
import store.gui.StoreWindow;

import javax.swing.*;
import java.awt.Color;

/**
 * Entry point of the store application.
 * Responsible for initializing demo data and launching the GUI.
 */
public class Main {

    /**
     * Starts the store application.
     * Creates the StoreEngine, loads initial demo products
     * and opens the main StoreWindow.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        StoreEngine engine = new StoreEngine();

        // Demo products (you can replace with loading from file if needed)
        engine.addProduct(new BookProduct(
                "Java Basics",
                120.0,
                10,
                "Intro to Java programming",
                Category.BOOKS,
                Color.BLUE,
                "images/java.png",
                "John Doe",
                350
        ));

        engine.addProduct(new ClothingProduct(
                "Pink Hoodie",
                180.0,
                6,
                "Warm oversized hoodie",
                Category.CLOTHING,
                Color.PINK,
                "images/hoodie.png",
                "M"
        ));

        engine.addProduct(new ElectronicsProduct(
                "Wireless Headphones",
                299.0,
                4,
                "Noise cancelling headphones",
                Category.ELECTRONICS,
                Color.BLACK,
                "images/headphones.png",
                24,
                "Sony"
        ));

        SwingUtilities.invokeLater(() -> {
            StoreWindow window = new StoreWindow(engine);
            window.setVisible(true);
        });
    }
}
