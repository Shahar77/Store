// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.gui;

import store.engine.StoreEngine;
import store.gui.catalog.controller.CatalogController;
import store.gui.cart.CartPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Main application window.
 * Combines product catalog and shopping cart into one screen.
 */
public class StoreWindow extends JFrame{

    private final StoreEngine engine;
    private final CatalogController catalogController;

    /**
     * Creates the main store window.
     * Initializes layout, catalog view and cart panel.
     * @param engine store engine instance
     */
    public StoreWindow(StoreEngine engine){
        super("Store");
        this.engine=engine;
        this.catalogController=new CatalogController(engine);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100,700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        initUI();
    }

    /**
     * Initializes all UI components of the window.
     * Adds catalog to center and cart to the right side.
     */
    private void initUI(){
        addCatalogPanel();
        addCartPanel();
    }

    /**
     * Creates and adds the product catalog panel
     * to the center area of the window.
     */
    private void addCatalogPanel(){
        JPanel catalogPanel=catalogController.createCatalogPanel();
        add(catalogPanel,BorderLayout.CENTER);
    }

    /**
     * Creates and adds the shopping cart panel
     * to the right side of the window.
     */
    private void addCartPanel(){
        CartPanel cartPanel=new CartPanel(engine);
        cartPanel.setPreferredSize(new Dimension(350,0));
        add(cartPanel,BorderLayout.EAST);
    }


    JButton history=new JButton("Orders");
history.addActionListener(e->new OrderHistoryWindow(engine).setVisible(true));
    add(history,BorderLayout.SOUTH);

}
