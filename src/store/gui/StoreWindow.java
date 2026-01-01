/**
 * Submitted by:
 * Sarah Gabay - ID 329185771
 * Shahar Ezra - ID 329186118
 */

package store.gui;

import store.engine.StoreEngine;
import store.cart.Cart;
import store.gui.cart.CartPanel;
import store.gui.cart.controller.CartController;
import store.gui.catalog.controller.CatalogController;
import store.gui.orders.OrderHistoryWindow;
import store.io.products.ProductCsvLoader;
import store.io.products.ProductCsvWriter;
import store.orders.OrderManager;
import store.products.Product;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

/**
 * Main store window. Displays catalog, cart and top actions (load/save/history).
 */
public class StoreWindow extends JFrame {

    private final StoreEngine engine;
    private final CatalogController catalogController;

    public StoreWindow(StoreEngine engine) {
        this.engine = engine;

        setTitle("Store");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Cart cart = new Cart();
        CartController cartController = new CartController(this, engine, cart);
        catalogController = new CatalogController(engine, cartController);

        JPanel catalogPanel = catalogController.createCatalogPanel();
        CartPanel cartPanel = new CartPanel(cart, cartController, engine);
        cartController.setCartPanel(cartPanel);

        add(buildTopBar(), BorderLayout.NORTH);

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(catalogPanel),
                cartPanel
        );
        splitPane.setDividerLocation(650);
        add(splitPane, BorderLayout.CENTER);
    }

    /**
     * Builds the top action bar (Load/Save/History).
     *
     * @return panel with buttons
     */
    private JPanel buildTopBar() {
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton loadBtn = new JButton("Load Products From File");
        JButton saveBtn = new JButton("Save Products To File");
        JButton historyBtn = new JButton("Order History");

        loadBtn.addActionListener(e -> loadProductsFromFile());
        saveBtn.addActionListener(e -> saveProductsToFile());
        historyBtn.addActionListener(e -> new OrderHistoryWindow(OrderManager.getOrders()).setVisible(true));

        top.add(loadBtn);
        top.add(saveBtn);
        top.add(historyBtn);

        return top;
    }

    /**
     * Opens a file chooser and loads products into the engine.
     * Refresh happens by recreating the catalog panel using engine data.
     */
    private void loadProductsFromFile() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(this);
        if (result != JFileChooser.APPROVE_OPTION) return;

        File file = chooser.getSelectedFile();
        try {
            List<Product> loaded = ProductCsvLoader.load(file.getAbsolutePath());
            engine.setProducts(loaded);

            JOptionPane.showMessageDialog(this, "Loaded " + loaded.size() + " products");

            // refresh whole window content
            getContentPane().removeAll();
            new StoreWindow(engine).setVisible(true);
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to load products: " + ex.getMessage());
        }
    }

    /**
     * Opens a file chooser and saves all products to a CSV file.
     */
    private void saveProductsToFile() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showSaveDialog(this);
        if (result != JFileChooser.APPROVE_OPTION) return;

        File file = chooser.getSelectedFile();
        try {
            ProductCsvWriter.write(engine.getAllProducts(), file.getAbsolutePath());
            JOptionPane.showMessageDialog(this, "Products saved successfully");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to save products: " + ex.getMessage());
        }
    }
}
