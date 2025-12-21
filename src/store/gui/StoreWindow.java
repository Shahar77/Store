package store.gui;

import store.engine.StoreEngine;
import store.cart.Cart;
import store.gui.cart.CartPanel;
import store.gui.cart.controller.CartController;
import store.gui.catalog.controller.CatalogController;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import store.gui.orders.OrderHistoryWindow;
import store.orders.OrderManager;

public class StoreWindow extends JFrame {

    public StoreWindow(StoreEngine engine) {

        setTitle("Store");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Cart cart = new Cart();
        CartController cartController = new CartController(cart);

        CatalogController catalogController =
                new CatalogController(engine, cartController);

        JPanel catalogPanel = catalogController.createCatalogPanel();
        CartPanel cartPanel = new CartPanel(cart, cartController, engine);
        cartController.setCartPanel(cartPanel);

        JButton historyButton = new JButton("Order History");
        historyButton.addActionListener(e ->
                new OrderHistoryWindow(
                        OrderManager.getOrders()
                )
        );
        add(historyButton, BorderLayout.NORTH);


        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(catalogPanel),
                cartPanel
        );

        splitPane.setDividerLocation(650);
        add(splitPane);
    }
}
