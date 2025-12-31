package store;

import store.engine.StoreEngine;
import store.gui.StoreWindow;
import store.gui.admin.thread.AdminRunnable;
import store.products.*;
import java.awt.Color;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        StoreEngine engine = new StoreEngine();

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

        // חלון החנות על EDT
        SwingUtilities.invokeLater(() -> new StoreWindow(engine).setVisible(true));

        // חלון מנהל בתוך Thread נפרד, כמו דרישת התרגיל
        new Thread(new AdminRunnable(engine)).start();
    }
}
