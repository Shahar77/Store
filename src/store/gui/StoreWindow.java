// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.gui;

import store.engine.StoreEngine;
import store.gui.cart.CartPanel;
import store.gui.catalog.controller.CatalogController;

import javax.swing.*;
import java.awt.*;

/**
 * Main application window.
 */
public class StoreWindow extends JFrame{
    private final StoreEngine engine;

    /**
     * Creates the main window.
     * @param engine store engine
     */
    public StoreWindow(StoreEngine engine){
        this.engine=engine;

        setTitle("Store");
        setSize(1000,650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        CatalogController catalogController=new CatalogController(engine);
        JPanel catalogPanel=catalogController.createCatalogPanel();

        JPanel cartPanel=new CartPanel(engine);

        JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,new JScrollPane(catalogPanel),cartPanel);
        split.setDividerLocation(650);

        add(split,BorderLayout.CENTER);
    }
}

