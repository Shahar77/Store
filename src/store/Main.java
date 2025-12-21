/**
 * Submitted by:
 * Sarah Gabay - ID 329185771
 * Shahar Ezra - ID 329186118
 */

package store;

import store.engine.StoreEngine;
import store.gui.StoreWindow;
import store.products.*;
import java.awt.Color;

/**
 * Application entry point.
 */
public class Main{

    /**
     * Starts the store application.
     *
     * @param args program arguments
     */
    public static void main(String[] args){
        StoreEngine engine=new StoreEngine();

        engine.addProduct(
                new BookProduct(
                        "Java Basics",
                        120,
                        10,
                        "Intro to Java programming",
                        Category.BOOKS,
                        Color.WHITE,
                        "images/java.png",
                        "John Doe",
                        350
                )
        );

        engine.addProduct(
                new ClothingProduct(
                        "T-Shirt",
                        80,
                        15,
                        "Cotton shirt",
                        Category.CLOTHING,
                        Color.BLACK,
                        "images/shirt.png",
                        "M"
                )
        );

        engine.addProduct(
                new ElectronicsProduct(
                        "Laptop",
                        3500,
                        5,
                        "Powerful laptop",
                        Category.ELECTRONICS,
                        Color.GRAY,
                        "images/laptop.png",
                        24,
                        "Lenovo"
                )
        );

        new StoreWindow(engine).setVisible(true);
    }
}
