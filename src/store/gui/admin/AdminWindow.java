package store.gui.admin;

import store.engine.StoreEngine;
import store.gui.admin.controller.AdminController;

import javax.swing.*;

public class AdminWindow extends JFrame {

    private final StoreEngine engine;
    private final AdminController controller;

    public AdminWindow(StoreEngine engine) {
        super("Admin");
        this.engine = engine;
        this.controller = new AdminController(engine);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(new AdminPanel(controller));
        pack();
        setLocationRelativeTo(null);
    }
}
