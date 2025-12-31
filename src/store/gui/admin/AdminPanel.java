package store.gui.admin;

import store.gui.admin.controller.AdminController;
import store.products.Category;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class AdminPanel extends JPanel {

    private JButton buttonAddProduct;
    private JButton buttonUpdateStock;
    private JButton buttonSave;
    private JButton buttonLoad;

    private final AdminController controller;
    private JLabel title;

    public AdminPanel(AdminController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(10, 10));
        initTop();
        initCenter();
        bindActions();
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
    }

    private void initTop() {
        title = new JLabel("Admin", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);
    }

    private void initCenter() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 1, 10, 10));

        buttonAddProduct = new JButton("Add Product");
        buttonUpdateStock = new JButton("Update Stock");
        buttonSave = new JButton("Save Products To File");
        buttonLoad = new JButton("Load Products From File");

        centerPanel.add(buttonAddProduct);
        centerPanel.add(buttonUpdateStock);
        centerPanel.add(buttonSave);
        centerPanel.add(buttonLoad);

        add(centerPanel, BorderLayout.CENTER);
    }

    private void bindActions() {
        buttonAddProduct.addActionListener(e -> onAddProduct());
        buttonUpdateStock.addActionListener(e -> onUpdateStock());
        buttonSave.addActionListener(e -> onSaveProducts());
        buttonLoad.addActionListener(e -> onLoadProducts());
    }

    private void onAddProduct() {
        String name = JOptionPane.showInputDialog(this, "Product name:");
        if (name == null || name.isBlank()) return;

        String priceStr = JOptionPane.showInputDialog(this, "Price:");
        if (priceStr == null || priceStr.isBlank()) return;

        String stockStr = JOptionPane.showInputDialog(this, "Stock:");
        if (stockStr == null || stockStr.isBlank()) return;

        String description = JOptionPane.showInputDialog(this, "Description (optional):");
        if (description == null) description = "";

        Category category = (Category) JOptionPane.showInputDialog(
                this,
                "Category:",
                "Choose Category",
                JOptionPane.PLAIN_MESSAGE,
                null,
                Category.values(),
                Category.ELECTRONICS
        );
        if (category == null) return;

        double price;
        int stock;
        try {
            price = Double.parseDouble(priceStr.trim());
            stock = Integer.parseInt(stockStr.trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid price or stock");
            return;
        }

        String imagePath = chooseImagePath();
        if (imagePath == null) {
            imagePath = "images/default.png";
        }

        controller.addSimpleProduct(name.trim(), price, stock, description.trim(), category, imagePath);
        JOptionPane.showMessageDialog(this, "Add request sent");
    }

    private void onUpdateStock() {
        String name = JOptionPane.showInputDialog(this, "Product name to update:");
        if (name == null || name.isBlank()) return;

        Category category = (Category) JOptionPane.showInputDialog(
                this,
                "Category:",
                "Choose Category",
                JOptionPane.PLAIN_MESSAGE,
                null,
                Category.values(),
                Category.ELECTRONICS
        );
        if (category == null) return;

        String newStockStr = JOptionPane.showInputDialog(this, "New stock value:");
        if (newStockStr == null || newStockStr.isBlank()) return;

        int newStock;
        try {
            newStock = Integer.parseInt(newStockStr.trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid stock number");
            return;
        }

        controller.updateStockByName(name.trim(), category, newStock);
        JOptionPane.showMessageDialog(this, "Update request sent");
    }

    private void onSaveProducts() {
        String path = chooseCsvSavePath();
        if (path == null) return;

        controller.saveProducts(path);
        JOptionPane.showMessageDialog(this, "Save request sent");
    }

    private void onLoadProducts() {
        String path = chooseCsvOpenPath();
        if (path == null) return;

        controller.loadProducts(path);
        JOptionPane.showMessageDialog(this, "Load request sent");
    }

    private String chooseImagePath() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Choose product image");
        chooser.setFileFilter(new FileNameExtensionFilter("Images", "png", "jpg", "jpeg", "webp"));
        int result = chooser.showOpenDialog(this);
        if (result != JFileChooser.APPROVE_OPTION) return null;

        File file = chooser.getSelectedFile();
        if (file == null) return null;
        return file.getAbsolutePath();
    }

    private String chooseCsvOpenPath() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Load products CSV");
        chooser.setFileFilter(new FileNameExtensionFilter("CSV files", "csv"));
        int result = chooser.showOpenDialog(this);
        if (result != JFileChooser.APPROVE_OPTION) return null;

        File file = chooser.getSelectedFile();
        if (file == null) return null;
        return file.getAbsolutePath();
    }

    private String chooseCsvSavePath() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Save products CSV");
        chooser.setFileFilter(new FileNameExtensionFilter("CSV files", "csv"));
        int result = chooser.showSaveDialog(this);
        if (result != JFileChooser.APPROVE_OPTION) return null;

        File file = chooser.getSelectedFile();
        if (file == null) return null;

        String path = file.getAbsolutePath();
        if (!path.toLowerCase().endsWith(".csv")) path += ".csv";
        return path;
    }
}
