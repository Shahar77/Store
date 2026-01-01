/**
 * Submitted by:
 * Sarah Gabay - ID 329185771
 * Shahar Ezra - ID 329186118
 */
package store.gui.admin;

import store.gui.admin.controller.AdminController;
import store.products.Category;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.concurrent.Future;

/**
 * Admin panel GUI.
 * Provides administrative actions such as adding products,
 * updating stock and saving/loading data from files.
 */
public class AdminPanel extends JPanel {

    private JButton buttonAddProduct;
    private JButton buttonUpdateStock;
    private JButton buttonSave;
    private JButton buttonLoad;

    private final AdminController controller;
    private JLabel title;

    /**
     * Creates a new AdminPanel.
     *
     * @param controller admin controller
     */
    public AdminPanel(AdminController controller) {
        this.controller=controller;
        setLayout(new BorderLayout(10,10));
        initTop();
        initCenter();
        bindActions();
        setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
    }

    /**
     * Initializes the top title section.
     */
    private void initTop() {
        title=new JLabel("Admin",SwingConstants.CENTER);
        title.setFont(new Font("Arial",Font.BOLD,18));
        add(title,BorderLayout.NORTH);
    }

    /**
     * Initializes the center buttons panel.
     */
    private void initCenter() {
        JPanel centerPanel=new JPanel();
        centerPanel.setLayout(new GridLayout(4,1,10,10));

        buttonAddProduct=new JButton("Add Product");
        buttonUpdateStock=new JButton("Update Stock");
        buttonSave=new JButton("Save Products To File");
        buttonLoad=new JButton("Load Products From File");

        centerPanel.add(buttonAddProduct);
        centerPanel.add(buttonUpdateStock);
        centerPanel.add(buttonSave);
        centerPanel.add(buttonLoad);

        add(centerPanel,BorderLayout.CENTER);
    }

    /**
     * Binds button actions to handlers.
     */
    private void bindActions() {
        buttonAddProduct.addActionListener(e -> onAddProduct());
        buttonUpdateStock.addActionListener(e -> onUpdateStock());
        buttonSave.addActionListener(e -> onSaveProducts());
        buttonLoad.addActionListener(e -> onLoadProducts());
    }

    /**
     * Waits for an asynchronous admin task and shows a dialog on completion.
     *
     * @param future admin task future
     * @param successMessage message shown on success
     * @param failPrefix prefix shown on failure
     */
    private void waitAndNotify(Future<?> future,String successMessage,String failPrefix) {
        new Thread(() -> {
            try{
                future.get();
                SwingUtilities.invokeLater(
                        () -> JOptionPane.showMessageDialog(this,successMessage)
                );
            } catch(Exception ex){
                SwingUtilities.invokeLater(
                        () -> JOptionPane.showMessageDialog(this,failPrefix+ex.getMessage())
                );
            }
        }).start();
    }

    /**
     * Handles adding a new product via dialogs.
     */
    private void onAddProduct() {
        String name=JOptionPane.showInputDialog(this,"Product name:");
        if(name==null || name.isBlank()){
            return;
        }

        String priceStr=JOptionPane.showInputDialog(this,"Price:");
        if(priceStr==null || priceStr.isBlank()){
            return;
        }

        String stockStr=JOptionPane.showInputDialog(this,"Stock:");
        if(stockStr==null || stockStr.isBlank()){
            return;
        }

        String description=JOptionPane.showInputDialog(this,"Description (optional):");
        if(description==null){
            description="";
        }

        Category category=(Category) JOptionPane.showInputDialog(
                this,
                "Category:",
                "Choose Category",
                JOptionPane.PLAIN_MESSAGE,
                null,
                Category.values(),
                Category.ELECTRONICS
        );
        if(category==null){
            return;
        }

        double price;
        int stock;
        try{
            price=Double.parseDouble(priceStr.trim());
            stock=Integer.parseInt(stockStr.trim());
        } catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(this,"Invalid price or stock");
            return;
        }

        String imagePath=chooseImagePath();
        if(imagePath==null){
            imagePath="images/default.png";
        }

        Future<?> f=controller.addSimpleProduct(
                name.trim(),
                price,
                stock,
                description.trim(),
                category,
                imagePath
        );
        waitAndNotify(f,"Product added","Add failed: ");
    }

    /**
     * Handles updating product stock.
     */
    private void onUpdateStock() {
        String name=JOptionPane.showInputDialog(this,"Product name to update:");
        if(name==null || name.isBlank()){
            return;
        }

        Category category=(Category) JOptionPane.showInputDialog(
                this,
                "Category:",
                "Choose Category",
                JOptionPane.PLAIN_MESSAGE,
                null,
                Category.values(),
                Category.ELECTRONICS
        );
        if(category==null){
            return;
        }

        String newStockStr=JOptionPane.showInputDialog(this,"New stock value:");
        if(newStockStr==null || newStockStr.isBlank()){
            return;
        }

        int newStock;
        try{
            newStock=Integer.parseInt(newStockStr.trim());
        } catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(this,"Invalid stock number");
            return;
        }

        Future<?> f=controller.updateStockByName(name.trim(),category,newStock);
        waitAndNotify(f,"Stock updated","Update failed: ");
    }

    /**
     * Handles saving products to a CSV file.
     */
    private void onSaveProducts() {
        String path=chooseCsvSavePath();
        if(path==null){
            return;
        }

        Future<?> f=controller.saveProducts(path);
        waitAndNotify(f,"Saved successfully","Save failed: ");
    }

    /**
     * Handles loading products from a CSV file.
     */
    private void onLoadProducts() {
        String path=chooseCsvOpenPath();
        if(path==null){
            return;
        }

        Future<?> f=controller.loadProducts(path);
        waitAndNotify(f,"Loaded successfully","Load failed: ");
    }

    /**
     * Opens a file chooser for selecting an image path.
     *
     * @return image file path or null
     */
    private String chooseImagePath() {
        JFileChooser chooser=new JFileChooser();
        chooser.setDialogTitle("Choose product image");
        chooser.setFileFilter(
                new FileNameExtensionFilter("Images","png","jpg","jpeg","webp")
        );
        int result=chooser.showOpenDialog(this);
        if(result!=JFileChooser.APPROVE_OPTION){
            return null;
        }

        File file=chooser.getSelectedFile();
        if(file==null){
            return null;
        }
        return file.getAbsolutePath();
    }

    /**
     * Opens a file chooser for loading a CSV file.
     *
     * @return CSV file path or null
     */
    private String chooseCsvOpenPath() {
        JFileChooser chooser=new JFileChooser();
        chooser.setDialogTitle("Load products CSV");
        chooser.setFileFilter(new FileNameExtensionFilter("CSV files","csv"));
        int result=chooser.showOpenDialog(this);
        if(result!=JFileChooser.APPROVE_OPTION){
            return null;
        }

        File file=chooser.getSelectedFile();
        if(file==null){
            return null;
        }
        return file.getAbsolutePath();
    }

    /**
     * Opens a file chooser for saving a CSV file.
     *
     * @return CSV file path or null
     */
    private String chooseCsvSavePath() {
        JFileChooser chooser=new JFileChooser();
        chooser.setDialogTitle("Save products CSV");
        chooser.setFileFilter(new FileNameExtensionFilter("CSV files","csv"));
        int result=chooser.showSaveDialog(this);
        if(result!=JFileChooser.APPROVE_OPTION){
            return null;
        }

        File file=chooser.getSelectedFile();
        if(file==null){
            return null;
        }

        String path=file.getAbsolutePath();
        if(!path.toLowerCase().endsWith(".csv")){
            path+=".csv";
        }
        return path;
    }
}
