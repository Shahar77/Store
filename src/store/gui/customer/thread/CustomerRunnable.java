package store.gui.customer.thread;

import store.engine.StoreEngine;
import store.gui.launcher.LauncherWindow;

import javax.swing.*;

public class CustomerRunnable implements Runnable {

    private final StoreEngine engine;

    public CustomerRunnable(StoreEngine engine) {
        this.engine = engine;
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> {
            LauncherWindow w = new LauncherWindow(engine);
            w.setVisible(true);
        });
    }
}
