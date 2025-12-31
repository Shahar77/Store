package store.gui.admin.thread;

import store.engine.StoreEngine;
import store.gui.admin.AdminWindow;

import javax.swing.*;

public class AdminRunnable implements Runnable {

    private final StoreEngine engine;

    public AdminRunnable(StoreEngine engine) {
        this.engine = engine;
    }

    @Override
    public void run() {
        // Swing UI חייב להיפתח על EDT
        SwingUtilities.invokeLater(() -> {
            AdminWindow w = new AdminWindow(engine);
            w.setVisible(true);
        });
    }
}
