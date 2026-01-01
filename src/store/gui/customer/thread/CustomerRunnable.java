/**
 * Submitted by:
 * Sarah Gabay - ID 329185771
 * Shahar Ezra - ID 329186118
 */
package store.gui.customer.thread;

import store.engine.StoreEngine;
import store.gui.launcher.LauncherWindow;

import javax.swing.*;

/**
 * Runnable responsible for launching the customer GUI.
 * Ensures the launcher window is created on the Swing Event Dispatch Thread.
 */
public class CustomerRunnable implements Runnable {

    /**
     * Shared store engine.
     */
    private final StoreEngine engine;

    /**
     * Creates a new CustomerRunnable.
     *
     * @param engine store engine instance
     */
    public CustomerRunnable(StoreEngine engine) {
        this.engine=engine;
    }

    /**
     * Runs the customer launcher initialization.
     */
    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> {
            LauncherWindow w=new LauncherWindow(engine);
            w.setVisible(true);
        });
    }
}
