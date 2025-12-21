package store;

import store.engine.StoreEngine;
import store.gui.StoreWindow;
import store.products.BookProduct;
import store.products.ClothingProduct;
import store.products.ElectronicsProduct;

import javax.swing.*;

/**
 * Application entry point.
 * Initializes the store engine, loads demo data and starts the GUI.
 */
public class Main{

    /**
     * Starts the store application.
     * @param args program arguments
     */
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {

            StoreEngine engine=new StoreEngine();

            engine.addProduct(new BookProduct(
                    "Clean Code",
                    120.0,
                    10,
                    "Robert C. Martin",
                    "images/clean_code.png"
            ));

            engine.addProduct(new ClothingProduct(
                    "T-Shirt",
                    59.9,
                    20,
                    "M",
                    "images/tshirt.png"
            ));

            engine.addProduct(new ElectronicsProduct(
                    "Laptop",
                    4200.0,
                    5,
                    24,
                    "images/laptop.png"
            ));

            StoreWindow window=new StoreWindow(engine);
            window.setVisible(true);
        });
    }
}
