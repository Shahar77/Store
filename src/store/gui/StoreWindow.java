// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.gui;

import store.engine.StoreEngine;
import store.gui.catalog.controller.CatalogController;

import javax.swing.*;
import java.awt.*;

/**
 * Main window of the store application.
 * Hosts the catalog view and serves as the primary GUI container.
 */
public class StoreWindow extends JFrame {

    /**
     * Creates the main store window and initializes the catalog panel.
     *
     * @param engine store engine instance
     */
    public StoreWindow(StoreEngine engine) {

        setTitle("Store");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        CatalogController controller = new CatalogController(engine);
        JPanel catalogPanel = controller.createCatalogPanel();

        add(new JScrollPane(catalogPanel), BorderLayout.CENTER);
    }
}
