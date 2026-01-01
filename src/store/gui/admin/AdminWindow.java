/**
 * Submitted by:
 * Sarah Gabay - ID 329185771
 * Shahar Ezra - ID 329186118
 */
package store.gui.admin;

import store.engine.StoreEngine;
import store.gui.admin.controller.AdminController;

import javax.swing.*;

/**
 * Main admin window of the application.
 * Acts as the top-level frame for administrative operations.
 */
public class AdminWindow extends JFrame {

    /**
     * Shared store engine.
     */
    private final StoreEngine engine;

    /**
     * Admin controller.
     */
    private final AdminController controller;

    /**
     * Creates a new AdminWindow.
     *
     * @param engine store engine instance
     */
    public AdminWindow(StoreEngine engine) {
        super("Admin");
        this.engine=engine;
        this.controller=new AdminController(engine);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(new AdminPanel(controller));
        pack();
        setLocationRelativeTo(null);
    }
}
