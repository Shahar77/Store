/**
 * Submitted by:
 * Sarah Gabay - ID 329185771
 * Shahar Ezra - ID 329186118
 */
package store.gui.admin.thread;

import store.engine.StoreEngine;
import store.gui.admin.AdminWindow;

import javax.swing.*;

/**
 * Runnable responsible for launching the admin GUI.
 * Ensures the AdminWindow is created on the Swing Event Dispatch Thread.
 */
public class AdminRunnable implements Runnable {

    /**
     * Store engine shared with the admin window.
     */
    private final StoreEngine engine;

    /**
     * Creates a new AdminRunnable.
     *
     * @param engine store engine instance
     */
    public AdminRunnable(StoreEngine engine) {
        this.engine=engine;
    }

    /**
     * Runs the admin window initialization.
     * The GUI is created using SwingUtilities.invokeLater
     * to ensure thread safety.
     */
    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> {
            AdminWindow w=new AdminWindow(engine);
            w.setVisible(true);
        });
    }
}
