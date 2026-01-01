/**
 * Submitted by:
 * Sarah Gabay - ID 329185771
 * Shahar Ezra - ID 329186118
 */
package store.gui.launcher;

import store.engine.StoreEngine;
import store.gui.admin.AdminWindow;
import store.gui.StoreWindow;

import javax.swing.*;
import java.awt.*;

/**
 * Launcher window that allows choosing between customer and admin modes.
 */
public class LauncherWindow extends JFrame {

    /**
     * Shared store engine.
     */
    private final StoreEngine engine;

    /**
     * Creates a new LauncherWindow.
     *
     * @param engine store engine instance
     */
    public LauncherWindow(StoreEngine engine) {
        super("Store Launcher");
        this.engine=engine;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520,260);
        setLocationRelativeTo(null);

        JLabel title=new JLabel("Choose Mode",SwingConstants.CENTER);
        title.setFont(new Font("Arial",Font.BOLD,26));

        JButton customerBtn=new JButton("Customer");
        JButton adminBtn=new JButton("Admin");

        customerBtn.addActionListener(e -> openCustomer());
        adminBtn.addActionListener(e -> openAdmin());

        JPanel buttons=new JPanel(new FlowLayout(FlowLayout.CENTER,30,20));
        buttons.add(customerBtn);
        buttons.add(adminBtn);

        setLayout(new BorderLayout());
        add(title,BorderLayout.NORTH);
        add(buttons,BorderLayout.CENTER);
    }

    /**
     * Opens the customer window.
     */
    private void openCustomer() {
        try{
            StoreWindow w=new StoreWindow(engine);
            w.setVisible(true);
            dispose();
        } catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    this,
                    "Failed to open customer window: "+ex.getMessage()
            );
        }
    }

    /**
     * Opens the admin window.
     */
    private void openAdmin() {
        try{
            AdminWindow w=new AdminWindow(engine);
            w.setVisible(true);
            dispose();
        } catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    this,
                    "Failed to open admin window: "+ex.getMessage()
            );
        }
    }
}
